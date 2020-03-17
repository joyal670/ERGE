package com.example.erge.ApiModel;

import java.util.List;

public class searchStoreModel {

    /**
     * no : 3180
     * name : South Gate Supercenter
     * country : US
     * coordinates : [-118.187613,33.954813]
     * streetAddress : 4651 Firestone Blvd
     * city : South Gate
     * stateProvCode : CA
     * zip : 90280
     * phoneNumber : 323-282-4800
     * sundayOpen : true
     * timezone : PST
     */

    private int no;
    private String name;
    private String country;
    private String streetAddress;
    private String city;
    private String stateProvCode;
    private String zip;
    private String phoneNumber;
    private boolean sundayOpen;
    private String timezone;
    private List<Double> coordinates;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvCode() {
        return stateProvCode;
    }

    public void setStateProvCode(String stateProvCode) {
        this.stateProvCode = stateProvCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isSundayOpen() {
        return sundayOpen;
    }

    public void setSundayOpen(boolean sundayOpen) {
        this.sundayOpen = sundayOpen;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }
}
