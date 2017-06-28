package ru.auto.cian.Helpers;

/**
 * Created by Иван on 26.06.2017.
 */
public class SearchData {
  private String dealType;
  private String propertyType;
  private String rooms;
  private int priceFrom;
  private int priceUpTo;
  private String location;

  public String getDealType() {
    return dealType;
  }

  public void setDealType(String dealType) {
    this.dealType = dealType;
  }

  public String getPropertyType() {
    return propertyType;
  }

  public void setPropertyType(String propertyType) {
    this.propertyType = propertyType;
  }

  public String getRooms() {
    return rooms;
  }

  public void setRooms(String rooms) {
    this.rooms = rooms;
  }

  public int getPriceFrom() {
    return priceFrom;
  }

  public void setPriceFrom(int priceFrom) {
    this.priceFrom = priceFrom;
  }

  public int getPriceUpTo() {
    return priceUpTo;
  }

  public void setPriceUpTo(int priceUpTo) {
    this.priceUpTo = priceUpTo;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
