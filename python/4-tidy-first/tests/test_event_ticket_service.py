from event_ticket_service import EventTicketService, NotificationService


def create_service():
    notification_service = NotificationService()
    service = EventTicketService(notification_service)
    return service, notification_service


def opts(base_price, quantity, is_early_bird=False, is_vip=False):
    return {
        "base_price": base_price,
        "quantity": quantity,
        "is_early_bird": is_early_bird,
        "is_vip": is_vip,
    }


class TestCalculateTicketPrice:
    def test_normal_price_with_tax(self):
        service, _ = create_service()
        assert service.calculate_ticket_price(opts(100, 1)) == 119.0

    def test_returns_zero_for_zero_quantity(self):
        service, _ = create_service()
        assert service.calculate_ticket_price(opts(100, 0)) == 0

    def test_applies_early_bird_discount(self):
        service, _ = create_service()
        assert service.calculate_ticket_price(opts(100, 1, is_early_bird=True)) == 101.15

    def test_applies_vip_surcharge(self):
        service, _ = create_service()
        assert service.calculate_ticket_price(opts(100, 1, is_vip=True)) == 178.5

    def test_applies_group_discount(self):
        service, _ = create_service()
        assert service.calculate_ticket_price(opts(100, 10)) == 1011.5

    def test_combines_early_bird_and_vip(self):
        service, _ = create_service()
        assert service.calculate_ticket_price(opts(100, 1, is_early_bird=True, is_vip=True)) == 151.72

    def test_combines_early_bird_and_group(self):
        service, _ = create_service()
        assert service.calculate_ticket_price(opts(100, 10, is_early_bird=True)) == 859.77

    def test_combines_vip_and_group(self):
        service, _ = create_service()
        assert service.calculate_ticket_price(opts(100, 10, is_vip=True)) == 1517.25

    def test_combines_all(self):
        service, _ = create_service()
        assert service.calculate_ticket_price(opts(100, 10, is_early_bird=True, is_vip=True)) == 1289.66


class TestMessageGeneration:
    def test_confirmation_message(self):
        service, _ = create_service()
        msg = service.generate_confirmation_message("Rock Concert", 2, 238.0)
        assert "Rock Concert" in msg
        assert "2 ticket(s)" in msg
        assert "238.00" in msg

    def test_receipt_message(self):
        service, _ = create_service()
        msg = service.generate_receipt_message("Rock Concert", 2, 238.0, "Max Mustermann")
        assert "Max Mustermann" in msg
        assert "2x Rock Concert" in msg
        assert "238.00" in msg

    def test_summary_message(self):
        service, _ = create_service()
        msg = service.generate_summary_message("Rock Concert", "2026-04-15", "Hamburg Arena")
        assert "Rock Concert" in msg
        assert "2026-04-15" in msg
        assert "Hamburg Arena" in msg


class TestSendBookingConfirmation:
    def test_sends_notification(self):
        service, notification_service = create_service()
        service.send_booking_confirmation("test@example.com", "Rock Concert", 2, 238.0)
        assert len(notification_service.sent_messages) == 1
        assert "test@example.com" in notification_service.sent_messages[0]
        assert "Rock Concert" in notification_service.sent_messages[0]
