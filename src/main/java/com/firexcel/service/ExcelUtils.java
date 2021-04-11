package com.firexcel.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelUtils {

  private String fileLocation;

  public void fireExcel(MultipartFile excelFile) throws IOException, InvalidFormatException {
    //get out excel absolute path
    InputStream in = excelFile.getInputStream();
    File currDir = new File(".");
    String path = currDir.getAbsolutePath();
    fileLocation = path.substring(0, path.length() - 1) + excelFile.getOriginalFilename();

    File file = new File(fileLocation);
    OPCPackage opcPackage = OPCPackage.open(file);
   // ParseExcelFile parsedFile = new ParseExcelFile(opcPackage,10,Resident.class);
  }
}