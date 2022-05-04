package com.example.myapplication.Designer;

public class ItemModel {
    long Id;
    String Header,Description,Type, Price;
    public ItemModel(){}
    public ItemModel(long id, String header, String description, String type, String price) {
        Id = id;
        Header = header;
        Description = description;
        Type = type;
        Price = price;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}