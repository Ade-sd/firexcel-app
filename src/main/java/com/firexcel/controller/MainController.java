package com.firexcel.controller;

import com.firexcel.service.ExcelService;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

@Controller
public class MainController {

  private String fileLocation;

  private final ExcelService service;

  @Autowired
  public MainController(ExcelService service) {
    this.service = service;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/")
  public String home() {
    return "home";
  }

  @RequestMapping(method = RequestMethod.POST, value = "/uploadExcelFile")
  public void uploadFile(MultipartFile file)
      throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
    service.readExcel(file);
  }
}
