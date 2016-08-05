package com.github.disparter.poc.poi.cellmerge;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.github.disparter.pojo.MyRow;


public class MergedCells {

    /**
     * Contador de linhas para facilitar o merge de celulas
     */
    public static int rowCount = 0;

    public static void main(String[] args) throws IOException {

        try (HSSFWorkbook wb = new HSSFWorkbook(); 
            FileOutputStream fileOut = new FileOutputStream("workbook.xls")) {
            
            
            HSSFSheet sheet = wb.createSheet("new sheet");

            //Agrupa lista para ficar em esquema de sublist
            Map<String, List<MyRow>> groupedRows = MyRow.getStubCollection().stream()
                    .collect(Collectors.groupingBy(MyRow::getParent));

            groupedRows.forEach((k, v) -> {

                int start = rowCount + 1;

                //Cada iteracao cria uma linha com conteudo repetido
                v.forEach((value) -> {
                    rowCount++;
                    HSSFRow row = sheet.createRow(rowCount);

                    HSSFCell parentCell = row.createCell(1);
                    parentCell.setCellValue(value.getParent());

                    HSSFCell cell = row.createCell(2);
                    cell.setCellValue(value.getValue());

                });

                //Faz o merge do conteudo da primeira coluna
                CellRangeAddress cellRangeAddress = new CellRangeAddress(start, rowCount, 1, 1);
                sheet.addMergedRegion(cellRangeAddress);

            });

            wb.write(fileOut);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}