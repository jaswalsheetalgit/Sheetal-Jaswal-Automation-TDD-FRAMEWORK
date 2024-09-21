package com.selenium.maven.base;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class ExcelUtils {

    private Workbook workbook;
    private Sheet sheet;

    public ExcelUtils(String excelFilePath, String sheetName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(excelFilePath);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
    }

    // Get cell data using row name and column name
    public String getCellData(String rowName, String columnName) {
        int rowIndex = -1;
        int columnIndex = -1;

        // Find the column index based on the column name (first row)
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
                columnIndex = i;
                break;
            }
        }

        // Find the row index based on the row name (first column)
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            if (row.getCell(0).getStringCellValue().equalsIgnoreCase(rowName)) {
                rowIndex = row.getRowNum();
                break;
            }
        }

        if (rowIndex != -1 && columnIndex != -1) {
            Cell cell = sheet.getRow(rowIndex).getCell(columnIndex);
            return cell.toString();
        }
        return null;  // Return null if row or column not found
    }

    // Clean up resources
    public void close() throws IOException {
        workbook.close();
    }
}
