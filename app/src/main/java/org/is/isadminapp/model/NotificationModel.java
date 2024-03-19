package org.is.isadminapp.model;

public class NotificationModel {
    private final String title;
    private final String timestamp;
    private final String subtitle;
    private final String notificationId;
    private final String body;

    public NotificationModel(String title, String subtitle, String timestamp, String body, String notificationId) {
        this.title = title;
        this.subtitle = subtitle;
        this.timestamp = timestamp;
        this.notificationId = notificationId;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getNotificationId() {
        return notificationId;
    }
    public String getBody() {
        return body;
    }
}
