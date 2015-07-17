package com.example.list.shop.shoplist.data.models;

import java.io.Serializable;

/**
 * Created by njaka on 7/15/2015.
 */
public class ShopItem implements Serializable {

    private String title;
    private String description;
    private long date;
    private long timeRemind;
    private boolean isArchive;
    private boolean isDelete;
    private int currentId;

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public void setIsArchive(boolean isArchive) {
        this.isArchive = isArchive;
    }


    public boolean isDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public boolean isArchive() {
        return isArchive;
    }

    public void setIsAchive(boolean isAchive) {
        this.isArchive = isAchive;
    }

    public long getTimeRemind() {
        return timeRemind;
    }

    public void setTimeRemind(long timeRemind) {
        this.timeRemind = timeRemind;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
