package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.adapter;

import org.openpdf.text.Document;
import org.openpdf.text.Image;
import org.openpdf.text.PageSize;
import org.openpdf.text.Rectangle;
import org.openpdf.text.pdf.BaseFont;
import org.openpdf.text.pdf.PdfContentByte;
import org.openpdf.text.pdf.PdfPageEventHelper;
import org.openpdf.text.pdf.PdfTemplate;
import org.openpdf.text.pdf.PdfWriter;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Dibuja en cada página la imagen de cabecera, la imagen de pie (ambas opcionales)
 * y los textos "Generado: ..." / "Pág. X de Y" (la imagen de pie va entre ambos). Se crea una instancia por documento.
 */
class MembretePdf extends PdfPageEventHelper {
    private static final float MARGEN_LATERAL = 56f;
    private static final float DISTANCIA_BORDE = 14f;      // ~5 mm desde el borde de la hoja
    private static final float SEPARACION = 12f;
    private static final float ALTO_TEXTO_PIE = 10f;
    private static final float ALTO_MAXIMO_IMAGEN = 60f;
    private static final float TAMANHO_FUENTE = 8f;
    private static final float ANCHO_TOTAL_PAGINAS = 24f;

    private static final Color COLOR_TEXTO = new Color(90, 90, 90);
    private static final Color COLOR_LINEA = new Color(180, 180, 180);
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final Image cabecera;
    private final Image pie;
    private final String fechaGeneracion;
    private final BaseFont fuente;
    private PdfTemplate totalPaginas;

    MembretePdf(Image cabecera, Image pie, LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = FORMATO_FECHA.format(fechaGeneracion);
        try {
            this.fuente = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
        } catch (IOException ex) {
            throw new IllegalStateException("No se pudo crear la fuente del reporte PDF", ex);
        }
        float anchoUtil = PageSize.A4.getWidth() - 2 * MARGEN_LATERAL;
        this.cabecera = escalar(cabecera, anchoUtil);
        // La imagen de pie va entre los textos de los extremos, centrada; se reserva el mismo ancho a cada lado
        float anchoTextoGenerado = this.fuente.getWidthPoint(this.textoGenerado(), TAMANHO_FUENTE);
        float anchoTextoPagina = this.fuente.getWidthPoint("Pág. 999 de ", TAMANHO_FUENTE) + ANCHO_TOTAL_PAGINAS;
        float reservaLateral = Math.max(anchoTextoGenerado, anchoTextoPagina) + SEPARACION;
        this.pie = escalar(pie, anchoUtil - 2 * reservaLateral);
    }

    /** Margen superior que necesita el documento para no superponerse con la cabecera. */
    float altoCabecera() {
        if (this.cabecera == null) return 0f;
        return DISTANCIA_BORDE + this.cabecera.getScaledHeight() + SEPARACION;
    }

    /** Margen inferior que necesita el documento para no superponerse con el pie (franja + línea + separación). */
    float altoPie() {
        return DISTANCIA_BORDE + this.altoFranjaPie() + SEPARACION / 2 + SEPARACION;
    }

    /** Alto de la franja inferior donde van los textos y la imagen de pie. */
    private float altoFranjaPie() {
        return this.pie == null ? ALTO_TEXTO_PIE : Math.max(ALTO_TEXTO_PIE, this.pie.getScaledHeight());
    }

    private String textoGenerado() {
        return "Generado: " + this.fechaGeneracion;
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        this.totalPaginas = writer.getDirectContent().createTemplate(ANCHO_TOTAL_PAGINAS, TAMANHO_FUENTE + 2);
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfContentByte cb = writer.getDirectContent();
        Rectangle pagina = document.getPageSize();
        float izquierda = pagina.getLeft() + MARGEN_LATERAL;
        float derecha = pagina.getRight() - MARGEN_LATERAL;

        if (this.cabecera != null) {
            float x = (pagina.getWidth() - this.cabecera.getScaledWidth()) / 2;
            float y = pagina.getTop() - DISTANCIA_BORDE - this.cabecera.getScaledHeight();
            this.cabecera.setAbsolutePosition(x, y);
            cb.addImage(this.cabecera);
        }

        // Franja inferior: "Generado" a la izquierda, imagen de pie al centro y número de página a la derecha
        float yFranja = pagina.getBottom() + DISTANCIA_BORDE;
        float altoFranja = this.altoFranjaPie();
        if (this.pie != null) {
            float x = (pagina.getWidth() - this.pie.getScaledWidth()) / 2;
            float y = yFranja + (altoFranja - this.pie.getScaledHeight()) / 2;
            this.pie.setAbsolutePosition(x, y);
            cb.addImage(this.pie);
        }
        // Línea base de los textos para que queden centrados verticalmente en la franja
        float altoMayusculas = this.fuente.getFontDescriptor(BaseFont.CAPHEIGHT, TAMANHO_FUENTE);
        float yTexto = yFranja + (altoFranja - altoMayusculas) / 2;
        float yLinea = yFranja + altoFranja + SEPARACION / 2;

        cb.saveState();
        cb.setColorStroke(COLOR_LINEA);
        cb.setLineWidth(0.5f);
        cb.moveTo(izquierda, yLinea);
        cb.lineTo(derecha, yLinea);
        cb.stroke();

        cb.setColorFill(COLOR_TEXTO);
        cb.beginText();
        cb.setFontAndSize(this.fuente, TAMANHO_FUENTE);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, this.textoGenerado(), izquierda, yTexto, 0);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Pág. " + writer.getPageNumber() + " de ", derecha - ANCHO_TOTAL_PAGINAS, yTexto, 0);
        cb.endText();
        cb.restoreState();
        cb.addTemplate(this.totalPaginas, derecha - ANCHO_TOTAL_PAGINAS, yTexto);
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        this.totalPaginas.beginText();
        this.totalPaginas.setColorFill(COLOR_TEXTO);
        this.totalPaginas.setFontAndSize(this.fuente, TAMANHO_FUENTE);
        this.totalPaginas.showText(String.valueOf(writer.getPageNumber() - 1));
        this.totalPaginas.endText();
    }

    private static Image escalar(Image imagen, float anchoUtil) {
        if (imagen == null) return null;
        imagen.scaleToFit(anchoUtil, ALTO_MAXIMO_IMAGEN);
        return imagen;
    }
}
