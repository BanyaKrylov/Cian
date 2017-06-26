package ru.auto.cian;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * Created by Иван on 26.06.2017.
 */
public class SearchHelper extends HelperBase {
  public SearchHelper(WebDriver wd) {
    super(wd);
  }

  public void homePage() {
    click(By.cssSelector("span.c-header-logo"));
  }

  public void fillSearchForm(SearchData searchData) {
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
      type((By.cssSelector("input[placeholder ='от']")), String.valueOf(searchData.getPriceFrom()));
      type((By.cssSelector("input[placeholder ='до']")), String.valueOf(searchData.getPriceUpTo()));
      click(By.cssSelector("span.c_filters-suggest_input-drop_icon___3gvN5"));
      click(By.xpath("//div[@class='c_filters-suggest_container___1vQFv']//div[.='" + searchData.getLocation() + "']"));
    } else {
      type((By.cssSelector("input[placeholder ='от']")), String.valueOf(searchData.getPriceFrom()));
      type((By.cssSelector("input[placeholder ='до']")), String.valueOf(searchData.getPriceUpTo()));
      click(By.cssSelector("span.c_filters-suggest_input-drop_icon___3gvN5"));
      click(By.xpath("//div[@class='c_filters-suggest_container___1vQFv']//div[.='" + searchData.getLocation() + "']"));
    }
  }
}
