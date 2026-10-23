package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.adapter;

import org.openpdf.text.*;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.ReportePuntajeDocentePdfGenerator;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeDocenteInfo;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.Locale;

@Component
public class OpenPdfReportePuntajeDocenteGenerator implements ReportePuntajeDocentePdfGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(OpenPdfReportePuntajeDocenteGenerator.class);

    private static final float MARGEN_LATERAL = 56f;   // ~20 mm
    private static final float MARGEN_VERTICAL = 56f;  // ~20 mm

    private static final Color COLOR_PRIMARIO = new Color(100, 4, 5);
    private static final Color COLOR_ENCABEZADO_TABLA = new Color(242, 217, 223);
    private static final Color COLOR_BORDE = new Color(180, 180, 180);
    private static final Color COLOR_TEXTO_SECUNDARIO = new Color(90, 90, 90);

    private static final Font FUENTE_INSTITUCION = new Font(Font.HELVETICA, 9, Font.NORMAL, COLOR_TEXTO_SECUNDARIO);
    private static final Font FUENTE_TITULO = new Font(Font.HELVETICA, 14, Font.BOLD, COLOR_PRIMARIO);
    private static final Font FUENTE_SUBTITULO = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);
    private static final Font FUENTE_ETIQUETA = new Font(Font.HELVETICA, 9, Font.BOLD, Color.BLACK);
    private static final Font FUENTE_NORMAL = new Font(Font.HELVETICA, 9, Font.NORMAL, Color.BLACK);
    private static final Font FUENTE_MATERIA = new Font(Font.HELVETICA, 10, Font.BOLD, Color.WHITE);
    private static final Font FUENTE_SECCION = new Font(Font.HELVETICA, 9, Font.BOLD, COLOR_PRIMARIO);
    private static final Font FUENTE_VACIO = new Font(Font.HELVETICA, 9, Font.ITALIC, COLOR_TEXTO_SECUNDARIO);

    private static final Locale LOCALE = Locale.of("es", "PY");

    // Imágenes opcionales de membrete; se guardan como bytes para crear un Image nuevo por documento
    private final byte[] cabeceraImagen;
    private final byte[] pieImagen;

    public OpenPdfReportePuntajeDocenteGenerator(
            ResourceLoader resourceLoader,
            @Value("${reporte.pdf.cabecera-imagen:}") String cabeceraImagenPath,
            @Value("${reporte.pdf.pie-imagen:}") String pieImagenPath
    ) {
        this.cabeceraImagen = this.cargarImagen(resourceLoader, cabeceraImagenPath);
        this.pieImagen = this.cargarImagen(resourceLoader, pieImagenPath);
    }

    @Override
    public byte[] generate(PuntajeDocenteInfo docente, String periodo) {
        MembretePdf membrete = new MembretePdf(
                this.crearImagen(this.cabeceraImagen),
                this.crearImagen(this.pieImagen),
                LocalDateTime.now()
        );
        Document document = new Document(
                PageSize.A4,
                MARGEN_LATERAL,
                MARGEN_LATERAL,
                Math.max(MARGEN_VERTICAL, membrete.altoCabecera()),
                Math.max(MARGEN_VERTICAL, membrete.altoPie())
        );
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            writer.setPageEvent(membrete);
            document.addTitle("Reporte de Evaluación Docente");
            document.open();

            this.agregarEncabezado(document, periodo);
            this.agregarDatosDocente(document, docente);
            if (docente.puntajeMateriaList().isEmpty()) {
                document.add(new Paragraph("El docente no tiene materias asignadas en el período.", FUENTE_VACIO));
            }
            for (int i = 0; i < docente.puntajeMateriaList().size(); i++) {
                // Cada materia (a partir de la segunda) inicia en una página nueva
                if (i > 0) {
                    document.newPage();
                }
                this.agregarMateria(document, docente.puntajeMateriaList().get(i));
            }

            document.close();
            return outputStream.toByteArray();
        } catch (DocumentException ex) {
            LOG.error("Error al generar PDF", ex);
            throw new RuntimeException("Error al generar archivo PDF: " + ex.getMessage());
        }
    }

    private void agregarEncabezado(Document document, String periodo) {
        /*Paragraph institucion = new Paragraph("Facultad de Odontología – UNCA", FUENTE_INSTITUCION);
        institucion.setAlignment(Element.ALIGN_CENTER);
        document.add(institucion);*/

        Paragraph titulo = new Paragraph("REPORTE DE EVALUACIÓN DOCENTE", FUENTE_TITULO);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingBefore(2f);
        document.add(titulo);

        Paragraph subtitulo = new Paragraph(periodo, FUENTE_SUBTITULO);
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        subtitulo.setSpacingAfter(12f);
        document.add(subtitulo);
    }

    private void agregarDatosDocente(Document document, PuntajeDocenteInfo docente) {
        PdfPTable tabla = new PdfPTable(new float[]{18f, 32f, 18f, 32f});
        tabla.setWidthPercentage(100f);
        tabla.setSpacingAfter(14f);

        tabla.addCell(this.celdaEtiqueta("Código"));
        tabla.addCell(this.celda(String.valueOf(docente.id()), Element.ALIGN_LEFT));
        tabla.addCell(this.celdaEtiqueta("Nro. Documento"));
        tabla.addCell(this.celda(this.formatearDocumento(docente.ci()), Element.ALIGN_LEFT));

        tabla.addCell(this.celdaEtiqueta("Docente"));
        PdfPCell nombre = this.celda(docente.apellidos() + ", " + docente.nombres(), Element.ALIGN_LEFT);
        nombre.setColspan(3);
        tabla.addCell(nombre);

        document.add(tabla);
    }

    private void agregarMateria(Document document, PuntajeDocenteInfo.PuntajeMateria materia) {
        // Título de la materia y resumen por estamento en una sola tabla para que no se separen
        PdfPTable tabla = new PdfPTable(new float[]{46f, 14f, 20f, 20f});
        tabla.setWidthPercentage(100f);
        tabla.setSpacingBefore(10f);
        tabla.setSpacingAfter(6f);
        tabla.setKeepTogether(true);

        PdfPCell titulo = new PdfPCell(new Phrase(
                materia.materia().descripcion() + " (" + materia.materia().codigo() + ") — " + materia.tipoDocente().descripcion(),
                FUENTE_MATERIA
        ));
        titulo.setColspan(3);
        this.estiloTitulo(titulo);
        tabla.addCell(titulo);

        PdfPCell puntajeGeneral = new PdfPCell(new Phrase("Puntaje: " + this.formatearPuntaje(materia.puntajeGeneral()), FUENTE_MATERIA));
        this.estiloTitulo(puntajeGeneral);
        puntajeGeneral.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tabla.addCell(puntajeGeneral);

        tabla.addCell(this.celdaEncabezado("Estamento", Element.ALIGN_LEFT));
        tabla.addCell(this.celdaEncabezado("Peso", Element.ALIGN_RIGHT));
        tabla.addCell(this.celdaEncabezado("Puntaje", Element.ALIGN_RIGHT));
        tabla.addCell(this.celdaEncabezado("Puntaje Ponderado", Element.ALIGN_RIGHT));

        for (PuntajeDocenteInfo.PuntajeEstamento estamento : materia.puntajePorEstamento()) {
            tabla.addCell(this.celda(estamento.estamento().descripcion(), Element.ALIGN_LEFT));
            tabla.addCell(this.celda(this.formatearPeso(estamento.estamento().porcentajePeso()), Element.ALIGN_RIGHT));
            tabla.addCell(this.celda(this.formatearPuntaje(estamento.puntaje()), Element.ALIGN_RIGHT));
            tabla.addCell(this.celda(this.formatearPuntaje(estamento.puntajePonderado()), Element.ALIGN_RIGHT));
        }
        document.add(tabla);

        for (PuntajeDocenteInfo.PuntajeEstamento estamento : materia.puntajePorEstamento()) {
            this.agregarIndicadores(document, estamento);
        }
    }

    private void agregarIndicadores(Document document, PuntajeDocenteInfo.PuntajeEstamento estamento) {
        PdfPTable tabla = new PdfPTable(new float[]{10f, 76f, 14f});
        tabla.setWidthPercentage(100f);
        tabla.setSpacingBefore(4f);
        tabla.setSpacingAfter(4f);
        // El título de la sección y las columnas se repiten si la tabla continúa en otra página
        tabla.setHeaderRows(2);

        PdfPCell seccion = new PdfPCell(new Phrase(
                "Indicadores — " + estamento.estamento().descripcion() + " (" + this.formatearPeso(estamento.estamento().porcentajePeso()) + ")",
                FUENTE_SECCION
        ));
        seccion.setColspan(3);
        seccion.setBorder(Rectangle.NO_BORDER);
        seccion.setPaddingBottom(4f);
        tabla.addCell(seccion);

        tabla.addCell(this.celdaEncabezado("N°", Element.ALIGN_CENTER));
        tabla.addCell(this.celdaEncabezado("Indicador", Element.ALIGN_LEFT));
        tabla.addCell(this.celdaEncabezado("Puntaje", Element.ALIGN_RIGHT));

        if (estamento.puntajePorIndicador().isEmpty()) {
            PdfPCell vacio = new PdfPCell(new Phrase("Sin evaluaciones registradas", FUENTE_VACIO));
            vacio.setColspan(3);
            this.estiloCelda(vacio, Element.ALIGN_CENTER);
            tabla.addCell(vacio);
        }
        for (PuntajeDocenteInfo.PuntajeIndicador indicador : estamento.puntajePorIndicador()) {
            tabla.addCell(this.celda(String.valueOf(indicador.indicador().nroOrden()), Element.ALIGN_CENTER));
            tabla.addCell(this.celda(indicador.indicador().descripcion(), Element.ALIGN_LEFT));
            tabla.addCell(this.celda(this.formatearPuntaje(indicador.puntaje()), Element.ALIGN_RIGHT));
        }
        document.add(tabla);
    }

    private PdfPCell celda(String texto, int alineacion) {
        PdfPCell cell = new PdfPCell(new Phrase(texto == null ? "-" : texto, FUENTE_NORMAL));
        this.estiloCelda(cell, alineacion);
        return cell;
    }

    private PdfPCell celdaEtiqueta(String texto) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, FUENTE_ETIQUETA));
        this.estiloCelda(cell, Element.ALIGN_LEFT);
        cell.setBackgroundColor(COLOR_ENCABEZADO_TABLA);
        return cell;
    }

    private PdfPCell celdaEncabezado(String texto, int alineacion) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, FUENTE_ETIQUETA));
        this.estiloCelda(cell, alineacion);
        cell.setBackgroundColor(COLOR_ENCABEZADO_TABLA);
        return cell;
    }

    private void estiloCelda(PdfPCell cell, int alineacion) {
        cell.setHorizontalAlignment(alineacion);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorderColor(COLOR_BORDE);
        cell.setPadding(4f);
    }

    private void estiloTitulo(PdfPCell cell) {
        cell.setBackgroundColor(COLOR_PRIMARIO);
        cell.setBorderColor(COLOR_PRIMARIO);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5f);
    }

    private String formatearPuntaje(BigDecimal valor) {
        if (valor == null) return "-";
        return new DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(LOCALE)).format(valor);
    }

    private String formatearPeso(BigDecimal valor) {
        if (valor == null) return "-";
        return new DecimalFormat("#,##0.##", DecimalFormatSymbols.getInstance(LOCALE)).format(valor) + "%";
    }

    private String formatearDocumento(String ci) {
        if (ci == null || ci.isBlank()) return "-";
        try {
            return new DecimalFormat("#,##0", DecimalFormatSymbols.getInstance(LOCALE)).format(Long.parseLong(ci.trim()));
        } catch (NumberFormatException ex) {
            return ci;
        }
    }

    private byte[] cargarImagen(ResourceLoader resourceLoader, String path) {
        if (path == null || path.isBlank()) return null;
        try (InputStream inputStream = resourceLoader.getResource(path).getInputStream()) {
            return inputStream.readAllBytes();
        } catch (IOException ex) {
            throw new IllegalStateException("No se pudo cargar la imagen del reporte PDF: " + path, ex);
        }
    }

    private Image crearImagen(byte[] bytes) {
        if (bytes == null) return null;
        try {
            return Image.getInstance(bytes);
        } catch (IOException ex) {
            throw new IllegalStateException("Imagen del reporte PDF inválida", ex);
        }
    }
}
