package com.ksb.xls.controller;

import com.ksb.xls.helper.ExcelHelper;
import com.ksb.xls.message.ResponseMessage;
import com.ksb.xls.model.xls;
import com.ksb.xls.service.ExcelService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("http://localhost:8086")
@Controller
@RequestMapping("/api/v1/excel")
public class ExcelController {

    @Autowired
    ExcelService fileService;

    //uploading excel file
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file){
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)){
            try{
                fileService.save(file);

                message = "Uploaded the file successfully: " +file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " +file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
    @GetMapping("/getAllExcelFiles")
    public ResponseEntity<List<xls>> getAllExcelFile(){
        try{
            List<xls> excelXls = fileService.getAllExcelFile();

            if(excelXls.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(excelXls, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //download excel file
    @GetMapping("/download")
    public ResponseEntity<Resource> getFile(){
        String fileName = "ExcelFile.xlsx";
        InputStreamResource file = new InputStreamResource(fileService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
