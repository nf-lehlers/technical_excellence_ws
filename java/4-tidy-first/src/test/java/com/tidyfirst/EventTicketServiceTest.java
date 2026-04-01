package com.tidyfirst;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class EventTicketServiceTest {

    private NotificationService notificationService = new NotificationService();
    private EventTicketService service = new EventTicketService(notificationService);

    private HashMap<String, Object> options(double basePrice, int quantity, boolean isEarlyBird, boolean isVip) {
        HashMap<String, Object> opts = new HashMap<>();
        opts.put("basePrice", basePrice);
        opts.put("quantity", quantity);
        opts.put("isEarlyBird", isEarlyBird);
        opts.put("isVip", isVip);
        return opts;
    }

    @Test
    void calculatesNormalPriceWithTax() {
        assertThat(service.calculateTicketPrice(options(100, 1, false, false))).isEqualTo(119.0);
    }

    @Test
    void returnsZeroForZeroQuantity() {
        assertThat(service.calculateTicketPrice(options(100, 0, false, false))).isEqualTo(0);
    }

    @Test
    void appliesEarlyBirdDiscount() {
        assertThat(service.calculateTicketPrice(options(100, 1, true, false))).isEqualTo(101.15);
    }

    @Test
    void appliesVipSurcharge() {
        assertThat(service.calculateTicketPrice(options(100, 1, false, true))).isEqualTo(178.5);
    }

    @Test
    void appliesGroupDiscountForTenOrMoreTickets() {
        assertThat(service.calculateTicketPrice(options(100, 10, false, false))).isEqualTo(1011.5);
    }

    @Test
    void combinesEarlyBirdAndVip() {
        assertThat(service.calculateTicketPrice(options(100, 1, true, true))).isEqualTo(151.73);
    }

    @Test
    void combinesEarlyBirdAndGroupDiscount() {
        assertThat(service.calculateTicketPrice(options(100, 10, true, false))).isEqualTo(859.78);
    }

    @Test
    void combinesVipAndGroupDiscount() {
        assertThat(service.calculateTicketPrice(options(100, 10, false, true))).isEqualTo(1517.25);
    }

    @Test
    void combinesAllDiscounts() {
        assertThat(service.calculateTicketPrice(options(100, 10, true, true))).isEqualTo(1289.66);
    }

    @Test
    void generatesConfirmationMessage() {
        String msg = service.generateConfirmationMessage("Rock Concert", 2, 238.0);
        assertThat(msg).contains("Rock Concert", "2 ticket(s)", "238.00");
    }

    @Test
    void generatesReceiptMessage() {
        String msg = service.generateReceiptMessage("Rock Concert", 2, 238.0, "Max Mustermann");
        assertThat(msg).contains("Max Mustermann", "2x Rock Concert", "238.00");
    }

    @Test
    void generatesSummaryMessage() {
        String msg = service.generateSummaryMessage("Rock Concert", "2026-04-15", "Hamburg Arena");
        assertThat(msg).contains("Rock Concert", "2026-04-15", "Hamburg Arena");
    }

    @Test
    void sendsNotificationViaNotificationService() {
        service.sendBookingConfirmation("test@example.com", "Rock Concert", 2, 238.0);
        assertThat(notificationService.getSentMessages()).hasSize(1);
        assertThat(notificationService.getSentMessages().get(0)).contains("test@example.com", "Rock Concert");
    }
}
