package com.community.rest.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFile {
	private static final Logger LOGGER = LogManager.getLogger(ReadFile.class);
	public static Workbook getWorkbook(String filePath) throws IOException {

		FileInputStream fis = null;
		XSSFWorkbook fisXlsx = null; // xlsx 전용 Workbook Reader

		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Workbook wb = null;
		if (filePath.toUpperCase().endsWith(".XLS")) {
			try {
				wb = new HSSFWorkbook(fis);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		} else if (filePath.toUpperCase().endsWith(".XLSX")) {
			wb = new SXSSFWorkbook(fisXlsx);
		}

		return wb;

	}
}
