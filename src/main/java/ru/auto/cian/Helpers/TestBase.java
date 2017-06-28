package ru.auto.cian.Helpers;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.auto.cian.ApplicationManagers.CianManager;

/**
 * Created by Иван on 26.06.2017.
 */
public class TestBase {
  protected static final CianManager MANAGER = new CianManager(BrowserType.CHROME);

  @BeforeSuite
  public void setUp() throws Exception {
    MANAGER.init();
  }

  @AfterSuite
  public void tearDown() {
    MANAGER.stop();
  }
}
