package com.ksb.xls.service;

import com.ksb.xls.helper.ExcelHelper;
import com.ksb.xls.model.xls;
import com.ksb.xls.repo.xlsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    xlsRepo repo;

    //saving the xls file - POST call
    public void save(MultipartFile file){
    try{
        List<xls> xls = ExcelHelper.excelToXls(file.getInputStream());
        repo.saveAll(xls);
    } catch (IOException e) {
        throw new RuntimeException("Fail to store excel file: " +e.getMessage());
    }
    }

    //get all xls - GET - get all excel file
    public List<xls> getAllExcelFile(){
        return repo.findAll();
    }

    //load the excel(with data) - GET - download
    public ByteArrayInputStream load(){
        List<xls> xls = repo.findAll();

        ByteArrayInputStream inputStream = ExcelHelper.xlsToExcel(xls);
        return inputStream;
    }
}
