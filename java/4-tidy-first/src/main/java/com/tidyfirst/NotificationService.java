package com.tidyfirst;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private boolean connected = false;
    private String host = "";
    private final List<String> sentMessages = new ArrayList<>();

    public void connect(String host, int port, boolean useTls) {
        this.host = host;
        this.connected = true;
    }

    public void sendMail(String to, String subject, String body) {
        if (connected) {
            sentMessages.add("To: " + to + " | Subject: " + subject + " | Body: " + body);
        }
    }

    public void disconnect() {
        this.connected = false;
        this.host = "";
    }

    public List<String> getSentMessages() {
        return sentMessages;
    }
}
