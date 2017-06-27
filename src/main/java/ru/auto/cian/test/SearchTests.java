package ru.auto.cian.test;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.auto.cian.SearchData;
import ru.auto.cian.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validQueryFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/data.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    List<SearchData> query = (List<SearchData>) xStream.fromXML(xml);
    return query.stream().map((q) -> new Object[]{q}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validQueryFromXml")
  public void Search(SearchData searchData) {
    MANAGER.search().homePage();
    MANAGER.search().fillSearchForm(searchData);
  }

  @Test
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File data = new File("src/main/resources/data.xml");
    System.out.println(data.getAbsolutePath());
    System.out.println("File exists: " + data.exists());
  }
}
