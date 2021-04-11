package com.firexcel.controller;

import com.firexcel.service.ExcelUtils;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

  private String fileLocation;

  private final ExcelUtils utils;

  @Autowired
  public MainController(ExcelUtils utils) {
    this.utils = utils;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/")
  public String home() {
    return "home";
  }

  @RequestMapping(method = RequestMethod.POST, value = "/uploadExcelFile")
  public void uploadFile(MultipartFile file) throws IOException, InvalidFormatException {
    utils.fireExcel(file);
  }
}
