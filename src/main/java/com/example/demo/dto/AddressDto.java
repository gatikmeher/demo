package com.example.demo.dto;

public class AddressDto {


    private Character addressType;
    private String street;
    private String city;

    private String state;
    private String country;

    public Character getAddressType() {
        return addressType;
    }

    public void setAddressType(Character addressType) {
        this.addressType = addressType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
