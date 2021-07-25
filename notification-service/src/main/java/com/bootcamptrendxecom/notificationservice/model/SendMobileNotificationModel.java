package com.bootcamptrendxecom.notificationservice.model;

public class SendMobileNotificationModel {
    private String title;
    private String description;
    private long userId;

    public SendMobileNotificationModel() {
    }

    public SendMobileNotificationModel(long userId, String title, String description) {
        this.title = title;
        this.description = description;
        this.userId=userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SendMobileNotificationModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}
