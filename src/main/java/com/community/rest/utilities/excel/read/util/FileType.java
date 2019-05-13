package com.community.rest.utilities.excel.read.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// 확장자 찾는 클래스
// 엑셀파일을 읽어서 Workbook 객체에 리턴
// XLS와 XLSX 확장자 비교
public class FileType {

    // FileInputStream파일의 경로에 있는 파일을 읽어서 Byte로 가져옴
    public static Workbook getWorkbook(String filePath) {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        Workbook wb = null;

        // 파일의 확장자를 체크해서 .XLS 라면 HSSFWorkbook에
        // .XLSX라면 XSSFWorkbook에 각각 초기화
        if(filePath.toUpperCase().endsWith(".XLS")) {
            try {
                wb = new HSSFWorkbook(fis);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        else if(filePath.toUpperCase().endsWith(".XLSX")) {
            try {
                wb = new XSSFWorkbook(fis);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return wb;

    }


}
