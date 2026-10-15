package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.adapter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.ExcelGenerator;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeGeneralInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.List;

@Component
public class ApachePOIExcelGenerator implements ExcelGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(ApachePOIExcelGenerator.class);
    private static final String[] TITULOS = {"Docente", "Código", "Materia", "Curso", "Tipo Docente", "Puntaje"};

    @Override
    public byte[] generate(List<PuntajeGeneralInfo> detalles) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("PuntajeGeneralDocentes");

        this.createHeader(workbook, sheet);
        this.fillTable(workbook, sheet, detalles);
        this.setColumnSizes(sheet);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }catch (IOException ex){
            LOG.error("Error al generar EXCEL", ex);
            throw new RuntimeException("Error al generar archivo Excel: " + ex.getMessage());
        }
    }

    private void createHeader(Workbook workbook, Sheet sheet){
        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        //headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        //headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        //font.setFontName("Arial");
        //font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        for(int i = 0; i < TITULOS.length; i++){
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(TITULOS[i]);
            headerCell.setCellStyle(headerStyle);
        }
    }

    private void fillTable(Workbook workbook, Sheet sheet, List<PuntajeGeneralInfo> detalles){
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        for(int i = 0; i < detalles.size(); i++){
            PuntajeGeneralInfo evaluacion = detalles.get(i);
            Row row = sheet.createRow(i + 1);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(evaluacion.evaluado().nombres()+ " " + evaluacion.evaluado().apellidos());
            cell0.setCellStyle(style);

            Cell cell1 = row.createCell(1);
            cell1.setCellValue(evaluacion.materia().codigo() != null ? evaluacion.materia().codigo().strip() : "");
            cell1.setCellStyle(style);

            Cell cell2 = row.createCell(2);
            cell2.setCellValue(evaluacion.materia().descripcion());
            cell2.setCellStyle(style);

            Cell cell3 = row.createCell(3);
            cell3.setCellValue(evaluacion.materia().curso().descripcion());
            cell3.setCellStyle(style);

            Cell cell4 = row.createCell(4);
            cell4.setCellValue(evaluacion.tipoDocente().descripcion());
            cell4.setCellStyle(style);

            Cell cell5 = row.createCell(5);
            cell5.setCellValue(evaluacion.promedioGeneral().setScale(2, RoundingMode.HALF_UP).toString());
            cell5.setCellStyle(style);
        }
    }

    private void setColumnSizes(Sheet sheet){
        for(int i = 0; i < TITULOS.length; i++) sheet.autoSizeColumn(i);
    }
}
