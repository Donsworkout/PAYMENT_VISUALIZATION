package com.community.rest.utilities;

import java.util.ArrayList;
import java.util.List;

public class ExcelReadOption {
	// 엑셀 파일 경로 저장
	private String filePath;
	// 추출 칼럼명 리스트 형태
	private List<String> outputColumns;

	// 추출 시작 row number
	private int startRow;

	// getter / setter
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<String> getOutputColumns() {
		List<String> temp = new ArrayList<>();
		temp.addAll(outputColumns);
		return temp;
	}

	public void setOutputColumns(List<String> outputColumns) {
		List<String> temp = new ArrayList<>();
		temp.addAll(outputColumns);
		this.outputColumns = temp;
	}

	// 가변인자, 스트링 객체가 0개부터 여러개 올 수 있다.
	public void setOutputColumns(String... outputColumns) {
		if (this.outputColumns == null) {
			this.outputColumns = new ArrayList<>();
		}
		for (String outputColumn : outputColumns) {
			this.outputColumns.add(outputColumn);
		}
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
}

