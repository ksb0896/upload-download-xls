package com.ksb.xls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ksb.xls")
public class ExcelUploadDownloadApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelUploadDownloadApplication.class, args);
	}

}
