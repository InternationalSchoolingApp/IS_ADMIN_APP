package org.is.isadminapp.model;

public class NotificationModel {
    private final String title;
    private final String timestamp;
    private final String subtitle;
    private final String notificationId;

    public NotificationModel(String title, String subtitle, String timestamp, String notificationId) {
        this.title = title;
        this.subtitle = subtitle;
        this.timestamp = timestamp;
        this.notificationId = notificationId;
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
}
