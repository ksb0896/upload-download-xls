/*
 * Copyright (c) 2025.
 * Purpose of this file --
 * 1) Is to validate the file format
 * 2) Importing data to excel file cells
 * 3) Parsing Excel data
 */

package com.ksb.xls.helper;

import com.ksb.xls.model.xls;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"; //allowed file during upload
    static String[] HEADERs = { "Id", "Title", "Description", "Published" }; //columns name
    static String SHEET = "xls"; //excel sheet name

    //to check weather the file is in 'xls' format
    public static boolean hasExcelFormat(MultipartFile file){
        if (!TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }

    //importing data to xls sheet
    public static ByteArrayInputStream xlsToExcel(List<xls> xls){
        try(Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();){
            Sheet sheet = workbook.createSheet(SHEET);

            //header
            Row headerRow = sheet.createRow(0);

            for(int col = 0; col < HEADERs.length; col++){
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for(xls excel :xls){
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(excel.getId());
                row.createCell(1).setCellValue(excel.getTitle());
                row.createCell(2).setCellValue(excel.getDescription());
                row.createCell(3).setCellValue(excel.isPublished());
            }
            workbook.write(out); //writing the data to xls
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to import data to Excel file: " + e.getMessage());
        }
    }

    //parsing excel - fetching/reading cell data
    public static List<xls> excelToXls(InputStream inputStream){
        try{
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<xls> xls = new ArrayList<xls>();

            int rowNumber = 0;
            while(rows.hasNext()){
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();

                xls xls1 = new xls();

                int cellIdx = 0;
                while(cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            xls1.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            xls1.setTitle(currentCell.getStringCellValue());
                            break;

                        case 2:
                            xls1.setDescription(currentCell.getStringCellValue());
                            break;

                        case 3:
                            xls1.setPublished(currentCell.getBooleanCellValue());
                            break;

                        default:
                            break;
                    }
                    cellIdx++;
                }
                xls.add(xls1);
            }
            workbook.close();
            return xls;
        }catch (IOException e) {
            throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
        }
    }
}
