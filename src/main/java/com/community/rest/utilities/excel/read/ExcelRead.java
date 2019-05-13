package com.community.rest.utilities.excel.read;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.community.rest.utilities.excel.read.util.CellRef;
import com.community.rest.utilities.excel.read.util.FileType;
import com.community.rest.utilities.excel.option.ReadOption;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelRead {

    public static List<Map<String, String>> read(ReadOption readOption, int index) {

        // 엑셀파일을 읽어 들인다.
        // FileType.getWorkbook() : 파일의 확장자를 구분해서 가져옴
        Workbook wb = FileType.getWorkbook(readOption.getFilePath());

        // 엑셀 파일에서 첫번째 시트를 가져옴
        Sheet sheet;
        try {
            sheet = wb.getSheetAt(index);
            System.out.println(sheet.getSheetName() + "Sheet 입니다.");

        } catch (Exception e) {
            System.out.println("마지막 시트입니다.");
            return null;
        }


        // 시트에서 유효한(데이터가 있는) 행의 갯수를 가져옴
        int numOfRows = sheet.getPhysicalNumberOfRows();
        int numOfCells = 0;

        Row row = null;
        Cell cell = null;

        String cellName = "";

        // key : 컬럼 / value: 데이터
        Map<String, String> map = null;

        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        // Row만큼 반복
        for(int rowIndex = readOption.getStartRow() - 1; rowIndex < numOfRows; rowIndex++) {

            row = sheet.getRow(rowIndex);

            if(row != null) {
                numOfCells = row.getPhysicalNumberOfCells();
                map = new HashMap<String, String>();

                for(int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
                    cell = row.getCell(cellIndex);
                    cellName = CellRef.getName(cell, cellIndex);

                    if( !readOption.getOutputColumns().contains(cellName) ) {
                        continue;
                    }

                    map.put(cellName, CellRef.getValue(cell));
                }
                result.add(map);
            }

        }
        return result;
    }
}