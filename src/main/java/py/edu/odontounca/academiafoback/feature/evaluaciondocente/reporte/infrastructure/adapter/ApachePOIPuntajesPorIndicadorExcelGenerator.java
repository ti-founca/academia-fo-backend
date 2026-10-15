package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.adapter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.PuntajesPorIndicadorExcelGenerator;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.HojaPuntajesIndicadorInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeIndicadorInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ApachePOIPuntajesPorIndicadorExcelGenerator implements PuntajesPorIndicadorExcelGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(ApachePOIPuntajesPorIndicadorExcelGenerator.class);
    private static final String[] TITULOS = {"Nº", "Indicador", "Promedio"};
    private static final int ANCHO_INDICADOR = 90 * 256;

    @Override
    public byte[] generate(List<HojaPuntajesIndicadorInfo> hojas) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Estilos estilos = new Estilos(workbook);
            Set<String> nombresUsados = new HashSet<>();

            if (hojas.isEmpty()) {
                workbook.createSheet("Sin datos").createRow(0).createCell(0)
                        .setCellValue("No hay evaluaciones para el periodo seleccionado");
            }
            for (HojaPuntajesIndicadorInfo hoja : hojas) {
                String nombre = this.nombreHoja(hoja.estamento().descripcion() + " - " + hoja.tipoDocente().descripcion(), nombresUsados);
                Sheet sheet = workbook.createSheet(nombre);
                this.createHeader(sheet, estilos);
                this.fillTable(sheet, estilos, hoja);
                sheet.autoSizeColumn(0);
                sheet.setColumnWidth(1, ANCHO_INDICADOR);
                sheet.autoSizeColumn(2);
            }

            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException ex) {
            LOG.error("Error al generar EXCEL", ex);
            throw new RuntimeException("Error al generar archivo Excel: " + ex.getMessage());
        }
    }

    // Excel limita los nombres de hoja a 31 caracteres y no admite repetidos
    private String nombreHoja(String nombre, Set<String> nombresUsados) {
        String base = WorkbookUtil.createSafeSheetName(nombre);
        String candidato = base;
        for (int i = 2; nombresUsados.contains(candidato.toLowerCase()); i++) {
            String sufijo = " (" + i + ")";
            candidato = base.substring(0, Math.min(base.length(), 31 - sufijo.length())) + sufijo;
        }
        nombresUsados.add(candidato.toLowerCase());
        return candidato;
    }

    private void createHeader(Sheet sheet, Estilos estilos) {
        Row header = sheet.createRow(0);
        for (int i = 0; i < TITULOS.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(TITULOS[i]);
            cell.setCellStyle(estilos.encabezado);
        }
    }

    private void fillTable(Sheet sheet, Estilos estilos, HojaPuntajesIndicadorInfo hoja) {
        int nroFila = 1;
        for (PuntajeIndicadorInfo detalle : hoja.indicadores()) {
            Row row = sheet.createRow(nroFila++);
            Cell nro = row.createCell(0);
            nro.setCellValue(detalle.indicador().nroOrden());
            nro.setCellStyle(estilos.texto);

            Cell indicador = row.createCell(1);
            indicador.setCellValue(detalle.indicador().descripcion());
            indicador.setCellStyle(estilos.texto);

            Cell promedio = row.createCell(2);
            promedio.setCellValue(detalle.puntaje().doubleValue());
            promedio.setCellStyle(estilos.numero);
        }

        Row total = sheet.createRow(nroFila);
        Cell etiqueta = total.createCell(1);
        etiqueta.setCellValue("Promedio");
        etiqueta.setCellStyle(estilos.encabezado);
        Cell promedio = total.createCell(2);
        promedio.setCellValue(hoja.promedio().doubleValue());
        promedio.setCellStyle(estilos.numeroNegrita);
    }

    private static class Estilos {
        final CellStyle encabezado;
        final CellStyle texto;
        final CellStyle numero;
        final CellStyle numeroNegrita;

        Estilos(Workbook workbook) {
            Font negrita = workbook.createFont();
            negrita.setBold(true);
            short formatoDecimal = workbook.createDataFormat().getFormat("0.00");

            this.encabezado = workbook.createCellStyle();
            this.encabezado.setFont(negrita);

            this.texto = workbook.createCellStyle();
            this.texto.setWrapText(true);
            this.texto.setVerticalAlignment(VerticalAlignment.TOP);

            this.numero = workbook.createCellStyle();
            this.numero.setDataFormat(formatoDecimal);
            this.numero.setVerticalAlignment(VerticalAlignment.TOP);

            this.numeroNegrita = workbook.createCellStyle();
            this.numeroNegrita.setDataFormat(formatoDecimal);
            this.numeroNegrita.setFont(negrita);
        }
    }
}
