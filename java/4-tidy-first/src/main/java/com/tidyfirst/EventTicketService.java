package com.tidyfirst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventTicketService {

    private int eventCounter = 0;
    private NotificationService notificationService;

    public String generateConfirmationMessage(String eventName, int ticketCount, double totalPrice) {
        return String.format("Booking confirmed: %d ticket(s) for \"%s\" – Total: €%.2f", ticketCount, eventName, totalPrice);
    }

    public double calculateTicketPrice(HashMap<String, Object> options) {
        double finalPrice = 0;
        int quantity = (int) options.get("quantity");
        if (quantity > 0) {
            double basePrice = (double) options.get("basePrice");
            boolean isEarlyBird = options.get("isEarlyBird") != null && (boolean) options.get("isEarlyBird");
            boolean isVip = options.get("isVip") != null && (boolean) options.get("isVip");
            finalPrice = basePrice * quantity * (isEarlyBird ? (1 - 0.15) : 1) * (isVip ? 1.5 : 1) * (quantity >= 10 ? 0.85 : 1) * 1.19;
        }
        return Math.round(finalPrice * 100.0) / 100.0;
    }

    public String generateSummaryMessage(String eventName, String date, String venue) {
        List<String> parts = new ArrayList<>();
        parts.add("Event Summary:");
        parts.add(eventName);
        parts.add("Date: " + date);
        parts.add("Venue: " + venue);
        return String.join(" | ", parts);
    }

    public void sendBookingConfirmation(String email, String eventName, int ticketCount, double totalPrice) {
        notificationService.connect("smtp.tickets.example.com", 587, true);
        notificationService.sendMail(
                email,
                "Booking Confirmation – " + eventName,
                generateConfirmationMessage(eventName, ticketCount, totalPrice)
        );
        notificationService.disconnect();
    }

    public String formatEventDate(int day, int month, int year) {
        return String.format("%02d.%02d.%d", day, month, year);
    }

    public EventTicketService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public String generateReceiptMessage(String eventName, int ticketCount, double totalPrice, String customerName) {
        return "Receipt for " + customerName + " – " + ticketCount + "x " + eventName + " – Total: €" + String.format("%.2f", totalPrice);
    }
}
