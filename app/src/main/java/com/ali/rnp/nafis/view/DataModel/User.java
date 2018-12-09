package com.ali.rnp.nafis.view.DataModel;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String capacity;
    private String image_url;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getImage_url(){
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
