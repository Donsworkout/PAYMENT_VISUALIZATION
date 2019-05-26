package com.community.rest.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.monitorjbl.xlsx.StreamingReader;

public class ExcelRead {
	private static final Logger LOGGER = LogManager.getLogger(ExcelRead.class);
	 
	public static List<Map<String, String>> read(ExcelReadOption excelReadOption, int sheetNum) {
		// 엑셀 파일서 첫번째 시트 
		
		Workbook wb = null;
        InputStream is = null;
        
		try {
			is = new FileInputStream(excelReadOption.getFilePath());
			wb = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(is);   
		} catch (FileNotFoundException e) {
			LOGGER.error("file not found in getFilePath()");
		}
		
		Sheet sheet = wb.getSheetAt(sheetNum);
		LOGGER.info("Sheet Name: " + wb.getSheetName(0));
		
		int numOfCells = 0;
		String cellName = "";
		Cell cell = null;
		Map<String, String> map = null;
		
		// 한줄을 의미 
		// 각 Row를 리스트에 담는다. 하나의 Row를 하나의 Map으로 표현되며 List에는 모든 Row가 포함될 것이다.
		List<Map<String, String>> result = new ArrayList<>();
		LOGGER.info("Processing sheet: " + sheet.getSheetName());

        for (Row row : sheet) {
        	if (row.getRowNum() > 0) {
				numOfCells = row.getPhysicalNumberOfCells();
				map = new HashMap<>();
				for (int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
					
					cell = row.getCell(cellIndex);
					cellName = ExcelCellRef.getName(cell, cellIndex);
					if (!excelReadOption.getOutputColumns().contains(cellName)) {
						continue;
					}
					
					//map객체의 Cell의 이름을 키(Key)로 데이터를 담는다.
					map.put(cellName, ExcelCellRef.getValue(cell));
				}
				result.add(map);
            }
        }

		return result;

	}
}
