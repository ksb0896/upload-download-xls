# Upload-Download-Excel-File
Upload and download excel files REST API

# Run application
```
mvn spring-boot:run
```
# Getting Started

### What I did
Created an API where you can upload & download an excel file.

### How I did
* Uploading Excel File to the Spring Server & storing data in MySQL Database. (/api/v1/excel/upload)
* Getting list of items from MySQL table. (/api/v1/excel/getAllExcelFiles)
* Downloading MySQL table data as Excel file. (/api/v1/excel/download)

### Technology used
* Java 8
* Spring Boot(with Spring Web MVC)
* Maven
* Apache POI 5.4.0

### Purposes of the files
* ExcelHelper provides functions to read Excel file.
* xls data model class corresponds to entity and table Excel.
* xlsRepo is an interface that extends JpaRepository for persisting data.
* ExcelService uses ExcelHelper and xlsRepo methods to save Excel data to MySQL, load data to Excel file, or get all Excel from MySQL table.
* ExcelController calls ExcelService methods and export Rest APIs: upload Excel file, get data from MySQL database.
* application.properties contains configuration for Spring Data and Servlet Multipart file.
* pom.xml for Spring Boot, MySQL connector, Apache POI dependencies.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.3/maven-plugin/build-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.3/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.3/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.