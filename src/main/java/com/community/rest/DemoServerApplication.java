package com.community.rest;

import com.community.rest.utilities.excel.option.ReadOption;
import com.community.rest.utilities.excel.read.ExcelRead;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoServerApplication.class, args);

        try  {
            ReadOption excelReadOption = new ReadOption();
            excelReadOption.setFilePath("/Users/doheekang/Downloads/EXCEL_TEST/src/main/java/sample_data.xlsx");
            excelReadOption.setOutputColumns("A","B","C","D","E","F");
            excelReadOption.setStartRow(2);

            List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption, 0);

            for(Map<String, String> article: excelContent){
                System.out.print(article.get("A") + " ");
                System.out.print(article.get("B") + " ");
                System.out.print(article.get("C") + " ");
                System.out.print(article.get("D") + " ");
                System.out.println(article.get("E") + " ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
