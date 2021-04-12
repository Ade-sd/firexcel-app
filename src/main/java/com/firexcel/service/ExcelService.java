package com.firexcel.service;

import com.firexcel.service.ExcelReader.Row;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

@Service
public class ExcelService {

  public void readExcel(MultipartFile excelFile)
      throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
    //get out excel absolute path
    InputStream in = excelFile.getInputStream();
    File currDir = new File(".");
    String path = currDir.getAbsolutePath();
    String fileLocation = path.substring(0, path.length() - 1) + excelFile.getOriginalFilename();
    startProcess(fileLocation);
  }

  private void startProcess(String fileLocation) {
    System.out.println("Reading a large excel file..");
    ExcelReader excelReader = new ExcelReader(fileLocation);

    try {
      List<Row> data = excelReader.readExcel();
      data.forEach(row -> {
        row.getCells().forEach(cell -> {
          if (cell.getType() == CellType.NUMERIC) {
            System.out.print(cell.getNumericCellValue());
          } else if (cell.getType() == CellType.BOOLEAN) {
            System.out.print(cell.getBooleanCellValue());
          } else if (cell.getType() == CellType.STRING) {
            System.out.print(cell.getStringCellValue());
          }
          System.out.print(",");
        });
        System.out.println();
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}