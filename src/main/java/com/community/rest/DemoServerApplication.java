package com.community.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.community.rest.domain.Merchant;
import com.community.rest.domain.Trade;
import com.community.rest.repository.MerchantRepository;
import com.community.rest.repository.TradeRepositorty;
import com.community.rest.utilities.excel.option.ReadOption;
import com.community.rest.utilities.excel.read.ExcelRead;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoServerApplication.class, args);

    }

//    @Bean
//    public CommandLineRunner runner(TradeRepositorty tradeRepositorty, MerchantRepository merchantRepository) throws Exception {
//        ReadOption excelReadOption = new ReadOption();
//        excelReadOption.setFilePath("/Users/doheekang/IdeaProjects/demo-server/src/main/resources/sample_data.xlsx");
//        excelReadOption.setOutputColumns("A", "B", "C", "D", "E", "F", "G", "H");
//        excelReadOption.setStartRow(2);
//
//
//        HashMap<Long, Merchant> merchantHashMap = new HashMap<>();
//
//
//        return (args) -> {
//            int sheetNum = 1;
//            List<Map<String, String>> excelContent;
//
//            while (true) {
//                excelContent = ExcelRead.read(excelReadOption, sheetNum);
//
//                if (excelContent == null)
//                    break;
//
//                if (sheetNum == 0) {
//                    for (Map<String, String> article : excelContent) {
//                        Trade trade = tradeRepositorty.save(Trade.builder()
//                                .id(Long.parseLong(article.get("A")))
//                                .tradeDate(article.get("B"))
//                                .tradeType(article.get("C"))
//                                .amount(article.get("D"))
//                                .fee(article.get("E"))
//                                .merchantId(merchantHashMap.get(Long.parseLong(article.get("F"))))
//                                .serviceType(article.get("G"))
//                                .tradeAccess(article.get("H"))
//                                .build());
//                    }
//
//                }
//                else if (sheetNum == 1) {
//                    for (Map<String, String> article : excelContent) {
//                        Merchant merchant = merchantRepository.save(Merchant.builder()
//                                .id(Long.parseLong(article.get("A")))
//                                .merchantName(article.get("B"))
//                                .regDate(article.get("C"))
//                                .serviceType(article.get("D"))
//                                .statusType(article.get("E"))
//                                .address(article.get("F"))
//                                .addressDetail(article.get("G"))
//                                .build());
//
//                        merchantHashMap.put(merchant.getId(), merchant);
//                    }
//                }
//
//                sheetNum--;
//            }
//        };
//    }


    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @EnableWebSecurity
    static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.addAllowedOrigin(CorsConfiguration.ALL);
            configuration.addAllowedMethod(CorsConfiguration.ALL);
            configuration.addAllowedHeader(CorsConfiguration.ALL);
            UrlBasedCorsConfigurationSource source =
                    new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);

            http.httpBasic()
                    .and().authorizeRequests()
                    .anyRequest().permitAll()
                    .and().cors().configurationSource(source)
                    .and().csrf().disable();
        }
    }
}
