package ru.auto.cian.ApplicationManagers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;
import ru.auto.cian.Helpers.SearchHelper;

import java.util.concurrent.TimeUnit;

/**
 * Created by Иван on 26.06.2017.
 */
public class CianManager {
  WebDriver wd;

  private SearchHelper searchHelper;
  private String browser;

  public CianManager(String browser) {
    this.browser = browser;
  }


  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    } else if (browser.equals(BrowserType.EDGE)) {
      wd = new EdgeDriver();
    } else if (browser.equals(BrowserType.SAFARI)) {
      wd = new SafariDriver();
    } else {
      System.out.println("Browser not supported");
    }

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("https://www.cian.ru/");
    searchHelper = new SearchHelper(wd);
  }

  public void stop() {
    wd.quit();
  }

  public SearchHelper search() {
    return searchHelper;
  }
}
