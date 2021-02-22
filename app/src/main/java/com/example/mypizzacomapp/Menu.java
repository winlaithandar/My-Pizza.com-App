package com.example.mypizzacomapp;

import android.security.identity.IdentityCredentialStore;

public class Menu {

    String menu;
    String descriptions;
    int photo;

    public Menu(String menu, String descriptions, int photo) {
        this.menu = menu;
        this.descriptions = descriptions;
        this.photo = photo;
    }

    public String getMenu() {
        return menu;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public int getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return descriptions;
    }
}
