package com.uv.customlistview;

/**
 * Created by venkatsr on 12/11/15.
 */
public class ContactDetails {

    private String name;

    private String phoneNumber;

    public ContactDetails() {
    }

    public ContactDetails(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
