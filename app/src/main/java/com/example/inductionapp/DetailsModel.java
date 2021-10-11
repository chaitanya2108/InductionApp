package com.example.inductionapp;

public class DetailsModel {

    String name, facultyName, image, description, block, room, button, tempBlock;

    public DetailsModel(){

    }

    public DetailsModel(String name, String facultyName, String image, String description, String block, String room, String button, String tempBlock) {
        this.name = name;
        this.facultyName = facultyName;
        this.image = image;
        this.description = description;
        this.block = block;
        this.room = room;
        this.button = button;
        this.tempBlock = tempBlock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getTempBlock() {
        return tempBlock;
    }

    public void setTempBlock(String tempBlock) {
        this.tempBlock = tempBlock;
    }
}
