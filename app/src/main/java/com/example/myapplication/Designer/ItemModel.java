package com.example.myapplication.Designer;

public class ItemModel {
    String order_term,Description,Type,img_print,documentId,status;
    int Price;
    String img_color;
    public ItemModel(){}

    public ItemModel(String order_term, String description, String type, String img_print, String documentId, String status, int price, String img_color) {
        this.order_term = order_term;
        Description = description;
        Type = type;
        this.img_print = img_print;
        this.documentId = documentId;
        this.status = status;
        Price = price;
        this.img_color = img_color;
    }

    public String getOrder_term() {
        return order_term;
    }

    public void setOrder_term(String order_term) {
        this.order_term = order_term;
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

    public String getImg_print() {
        return img_print;
    }

    public void setImg_print(String img_print) {
        this.img_print = img_print;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getImg_color() {
        return img_color;
    }

    public void setImg_color(String img_color) {
        this.img_color = img_color;
    }
}