package com.community.rest.utilities.excel.read.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellReference;

public class CellRef {

    public static String getName(Cell cell, int cellIndex) {
        int cellNum = 0;
        if (cell != null) {
            cellNum = cell.getColumnIndex();
        } else {
            cellNum = cellIndex;
        }

        return CellReference.convertNumToColString(cellNum);
    }


    public static String getValue(Cell cell) {
        String value = "";

        if(cell == null) {
            value = "";
        } else {
            if(cell.getCellTypeEnum() == CellType.FORMULA) {
                value = cell.getCellFormula();
            }
            else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
                value = cell.getNumericCellValue()+"";
            }
            else if(cell.getCellTypeEnum() == CellType.STRING) {
                value = cell.getStringCellValue();
            }
            else if(cell.getCellTypeEnum() == CellType.BOOLEAN) {
                value = cell.getBooleanCellValue()+"";
            }
            else if(cell.getCellTypeEnum() == CellType.ERROR) {
                value = cell.getErrorCellValue()+"";
            }
            else if(cell.getCellTypeEnum() == CellType.BLANK) {
                value = "";
            }
            else {
                value = cell.getStringCellValue();
            }
        }

        return value;
    }
}