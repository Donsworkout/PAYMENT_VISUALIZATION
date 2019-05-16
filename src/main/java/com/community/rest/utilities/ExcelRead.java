package com.community.rest.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelRead {
	public static List<Map<String, String>> read(ExcelReadOption excelReadOption, int sheet_num) {
		// 파일 읽고 wb 객체에 전달한다 
		Workbook wb = ReadFile.getWorkbook(excelReadOption.getFilePath());
		// 엑셀 파일서 첫번째 시트  
		Sheet sheet = wb.getSheetAt(sheet_num);

		System.out.println("Sheet Name: " + wb.getSheetName(0));
		System.out.println("Valid Sheet num :" + wb.getNumberOfSheets());
		
		// 유용한 row 개수 
		int numOfRows = sheet.getPhysicalNumberOfRows();
		int numOfCells = 0;

		Row row = null;
		Cell cell = null;

		String cellName = "";

		Map<String, String> map = null;
		
		// 한줄을 의미 
		// 각 Row를 리스트에 담는다. 하나의 Row를 하나의 Map으로 표현되며 List에는 모든 Row가 포함될 것이다.
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		

		for (int rowIndex = excelReadOption.getStartRow() - 1; rowIndex < numOfRows; rowIndex++) {
			 // 워크북에서 가져온 시트에서 rowIndex에 해당하는 Row를 가져온다. 하나의 Row는 여러개의 Cell을 가진다.
			row = sheet.getRow(rowIndex);
			if (row != null) {
				//가져온 Row의 Cell의 개수를 구한다.
				numOfCells = row.getPhysicalNumberOfCells();
				map = new HashMap<String, String>();
				for (int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
					//Row에서 CellIndex에 해당하는 Cell을 가져온다.
					cell = row.getCell(cellIndex);
					//현재 Cell의 이름을 가져온다 이름의 예 : A,B,C,D,......
					cellName = ExcelCellRef.getName(cell, cellIndex);
					//추출 대상 컬럼인지 확인한다 추출 대상 컬럼이 아니라면, for로 다시 올라간다
					if (!excelReadOption.getOutputColumns().contains(cellName)) {
						continue;
					}
					//map객체의 Cell의 이름을 키(Key)로 데이터를 담는다.
					map.put(cellName, ExcelCellRef.getValue(cell));
				}
				result.add(map);
			}

		}
		System.out.println(result);
		return result;

	}
}
