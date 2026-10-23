package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.adapter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.PuntajesPorCursoExcelGenerator;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.HojaPuntajesCursoInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeGeneralInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.List;

@Component
public class ApachePOIPuntajesPorCursoExcelGenerator implements PuntajesPorCursoExcelGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(ApachePOIPuntajesPorCursoExcelGenerator.class);
    private static final String[] TITULOS = {"Tipo", "Docente", "Código", "Materia", "Puntaje"};

    @Override
    public byte[] generate(List<HojaPuntajesCursoInfo> hojas) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Estilos estilos = new Estilos(workbook);
            for (HojaPuntajesCursoInfo hoja : hojas) {
                Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName(hoja.nombre()));
                this.createHeader(sheet, estilos);
                this.fillTable(sheet, estilos, hoja.puntajes());
                for (int i = 0; i < TITULOS.length; i++) sheet.autoSizeColumn(i);
            }
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException ex) {
            LOG.error("Error al generar EXCEL", ex);
            throw new RuntimeException("Error al generar archivo Excel: " + ex.getMessage());
        }
    }

    private void createHeader(Sheet sheet, Estilos estilos) {
        Row header = sheet.createRow(0);
        for (int i = 0; i < TITULOS.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(TITULOS[i]);
            cell.setCellStyle(estilos.encabezado);
        }
    }

    private void fillTable(Sheet sheet, Estilos estilos, List<PuntajeGeneralInfo> puntajes) {
        if (puntajes.isEmpty()) {
            sheet.createRow(1).createCell(0).setCellValue("Sin evaluaciones para este curso");
            return;
        }
        for (int i = 0; i < puntajes.size(); i++) {
            PuntajeGeneralInfo puntaje = puntajes.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(puntaje.tipoDocente().descripcion());
            row.createCell(1).setCellValue(puntaje.evaluado().nombres() + " " + puntaje.evaluado().apellidos());
            row.createCell(2).setCellValue(puntaje.materia().codigo() != null ? puntaje.materia().codigo().strip() : "");
            row.createCell(3).setCellValue(puntaje.materia().descripcion());
            Cell cellPuntaje = row.createCell(4);
            cellPuntaje.setCellValue(puntaje.promedioGeneral().setScale(2, RoundingMode.HALF_UP).doubleValue());
            cellPuntaje.setCellStyle(estilos.numero);
        }
    }

    private static class Estilos {
        final CellStyle encabezado;
        final CellStyle numero;

        Estilos(Workbook workbook) {
            Font negrita = workbook.createFont();
            negrita.setBold(true);

            this.encabezado = workbook.createCellStyle();
            this.encabezado.setFont(negrita);

            this.numero = workbook.createCellStyle();
            this.numero.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
        }
    }
}
