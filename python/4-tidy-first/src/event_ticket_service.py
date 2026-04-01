class NotificationService:
    def __init__(self):
        self.sent_messages = []
        self._connected = False
        self._host = ""

    def connect(self, host, port, use_tls):
        self._host = host
        self._connected = True

    def send_mail(self, to, subject, body):
        if self._connected:
            self.sent_messages.append(f"To: {to} | Subject: {subject} | Body: {body}")

    def disconnect(self):
        self._connected = False
        self._host = ""


class EventTicketService:
    event_counter = 0

    def generate_confirmation_message(self, event_name, ticket_count, total_price):
        return f'Booking confirmed: {ticket_count} ticket(s) for "{event_name}" – Total: €{total_price:.2f}'

    def calculate_ticket_price(self, options):
        final_price = 0
        if options["quantity"] > 0:
            early_bird_factor = (1 - 0.15) if options.get("is_early_bird") else 1
            vip_factor = 1.5 if options.get("is_vip") else 1
            group_factor = 0.85 if options["quantity"] >= 10 else 1
            final_price = options["base_price"] * options["quantity"] * early_bird_factor * vip_factor * group_factor * 1.19
        return round(final_price, 2)

    def generate_summary_message(self, event_name, date, venue):
        parts = []
        parts.append("Event Summary:")
        parts.append(event_name)
        parts.append("Date: " + date)
        parts.append("Venue: " + venue)
        return " | ".join(parts)

    def send_booking_confirmation(self, email, event_name, ticket_count, total_price):
        self.notification_service.connect("smtp.tickets.example.com", 587, True)
        self.notification_service.send_mail(
            email,
            "Booking Confirmation – " + event_name,
            self.generate_confirmation_message(event_name, ticket_count, total_price)
        )
        self.notification_service.disconnect()

    def format_event_date(self, day, month, year):
        return f"{day:02d}.{month:02d}.{year}"

    def __init__(self, notification_service):
        self.notification_service = notification_service

    def generate_receipt_message(self, event_name, ticket_count, total_price, customer_name):
        return "Receipt for " + customer_name + " – " + str(ticket_count) + "x " + event_name + " – Total: €" + f"{total_price:.2f}"
