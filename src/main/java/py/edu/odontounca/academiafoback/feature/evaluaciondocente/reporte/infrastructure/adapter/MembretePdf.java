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
 * y la línea "Generado: ... / Pág. X de Y". Se crea una instancia por documento.
 */
class MembretePdf extends PdfPageEventHelper {
    private static final float MARGEN_LATERAL = 56f;
    private static final float DISTANCIA_BORDE = 28f;      // ~10 mm desde el borde de la hoja
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
        float anchoUtil = PageSize.A4.getWidth() - 2 * MARGEN_LATERAL;
        this.cabecera = escalar(cabecera, anchoUtil);
        this.pie = escalar(pie, anchoUtil);
        this.fechaGeneracion = FORMATO_FECHA.format(fechaGeneracion);
        try {
            this.fuente = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
        } catch (IOException ex) {
            throw new IllegalStateException("No se pudo crear la fuente del reporte PDF", ex);
        }
    }

    /** Margen superior que necesita el documento para no superponerse con la cabecera. */
    float altoCabecera() {
        if (this.cabecera == null) return 0f;
        return DISTANCIA_BORDE + this.cabecera.getScaledHeight() + SEPARACION;
    }

    /** Margen inferior que necesita el documento para no superponerse con el pie. */
    float altoPie() {
        float alto = DISTANCIA_BORDE + ALTO_TEXTO_PIE + SEPARACION;
        if (this.pie != null) alto += this.pie.getScaledHeight() + SEPARACION / 2;
        return alto;
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

        float yTexto = pagina.getBottom() + DISTANCIA_BORDE;
        if (this.pie != null) {
            float x = (pagina.getWidth() - this.pie.getScaledWidth()) / 2;
            this.pie.setAbsolutePosition(x, yTexto + ALTO_TEXTO_PIE + SEPARACION / 2);
            cb.addImage(this.pie);
        }

        cb.saveState();
        cb.setColorStroke(COLOR_LINEA);
        cb.setLineWidth(0.5f);
        cb.moveTo(izquierda, yTexto + ALTO_TEXTO_PIE);
        cb.lineTo(derecha, yTexto + ALTO_TEXTO_PIE);
        cb.stroke();

        cb.setColorFill(COLOR_TEXTO);
        cb.beginText();
        cb.setFontAndSize(this.fuente, TAMANHO_FUENTE);
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "Generado: " + this.fechaGeneracion, izquierda, yTexto, 0);
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
