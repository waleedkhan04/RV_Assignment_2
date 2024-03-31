package com.example.smd_assignment2;

public class Restaurant {
    private String name;
    private String location;
    private String phone;
    private String description;
    private String rating;

    public Restaurant(String name, String location, String phone, String description, String rating) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.description = description;
        this.rating= rating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
