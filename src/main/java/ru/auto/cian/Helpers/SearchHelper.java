package ru.auto.cian.Helpers;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Иван on 26.06.2017.
 */
public class SearchHelper extends HelperBase {
  public SearchHelper(WebDriver wd) {
    super(wd);
  }

  List<String> url = new ArrayList<>();
  Workbook workbook = new HSSFWorkbook();
  Sheet sheet = workbook.createSheet("URL");

  public void homePage() {
    wd.get("https://www.cian.ru/");
  }

  public void fillSearchForm(SearchData searchData) throws IOException {
    click(By.xpath("//div[@class='c-filters-form___2RBwa']//button[.='Купить']"));
    click(By.xpath("//div[@class='c-filters-form___2RBwa']//div[.='" + searchData.getDealType() + "']"));
    click(By.xpath("//div[@class='c-filters-form___2RBwa']//button[.='Квартиру']"));
    click(By.xpath("//div[@class='c-filters-form___2RBwa']//span[.='" + searchData.getPropertyType() + "']"));
    if ((searchData.getDealType().equals("Купить") && (searchData.getPropertyType().equals("Квартира") ||
            searchData.getPropertyType().equals("Квартира во вторичке") ||
            searchData.getPropertyType().equals("Квартира в новостройке"))) || (searchData.getDealType().equals("Снять") &&
            searchData.getPropertyType().equals("Квартира")) || (searchData.getDealType().equals("Посуточно") &&
            searchData.getPropertyType().equals("Квартира"))) {
      click(By.xpath("//div[@class='c-filters-form___2RBwa']//button[.='1, 2 комн.']"));
      click(By.xpath("//div[@class='c-filters-form___2RBwa']//label[.='2-комнатная']"));
      click(By.xpath("//div[@class='c-filters-form___2RBwa']//label[.='1-комнатная']"));
      click(By.xpath("//div[@class='c-filters-form___2RBwa']//label[.='" + searchData.getRooms() + "']"));
      searchContinue(searchData);
    } else {
      searchContinue(searchData);
    }
  }

  public void sleep(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {

    }
  }

  public void searchContinue(SearchData searchData) throws IOException {
    type((By.cssSelector("input[placeholder ='от']")), String.valueOf(searchData.getPriceFrom()));
    type((By.cssSelector("input[placeholder ='до']")), String.valueOf(searchData.getPriceUpTo()));
    click(By.cssSelector("span.c_filters-suggest_input-drop_icon___3gvN5"));
    click(By.xpath("//div[@class='c_filters-suggest_container___1vQFv']//div[.='" + searchData.getLocation() + "']"));
    click(By.cssSelector("button.c-filters-field-button___1EBB-"));
    this.sleep(2);
    click(By.linkText("Подробнее"));
    ArrayList tabs = new ArrayList<>(wd.getWindowHandles());
    wd.switchTo().window((String) tabs.get(1));
    takeUrl();
    writeInToExcel("src/main/resources/offers.xls");
    Actions actions = new Actions(wd);
    this.sleep(2);
    WebElement element = wd.findElement(By.cssSelector("img.fotorama__img"));
    actions.moveToElement(element);
    actions.perform();
    this.sleep(2);
    for (int i = 0; i < 5; i++) {
      click(By.cssSelector("div.fotorama__arr.fotorama__arr--next"));
    }
    wd.close();
    wd.switchTo().window((String) tabs.get(0));
  }

  public void takeUrl() {
    url.add(wd.getCurrentUrl());
  }

  public void writeInToExcel(String file) throws IOException {
    for (int i = 0; i < url.size(); i++) {
      Row row = sheet.createRow(i);
      Cell offer = row.createCell(0);
      offer.setCellValue(url.get(i));
      sheet.autoSizeColumn(i);
    }
    workbook.write(new FileOutputStream(file));
    workbook.close();
  }
}
