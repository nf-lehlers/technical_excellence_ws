// NotificationService handles sending emails
export class NotificationService {
  // the connected flag
  private connected: boolean = false;
  // stores the host
  private host: string = "";
  // list of sent messages
  public sentMessages: string[] = [];

  // connects to the mail server
  connect(host: string, port: number, useTls: boolean): void {
    this.host = host;
    this.connected = true;
  }

  // sends an email
  sendMail(to: string, subject: string, body: string): void {
    if (this.connected) {
      this.sentMessages.push(`To: ${to} | Subject: ${subject} | Body: ${body}`);
    }
  }

  // disconnects from the mail server
  disconnect(): void {
    this.connected = false;
    this.host = "";
  }
}

// EventTicketService manages event tickets
export class EventTicketService {
  // counter for events
  private eventCounter: number = 0;
  // the notification service
  private notificationService: NotificationService;

  // generates a confirmation message
  generateConfirmationMessage(eventName: string, ticketCount: number, totalPrice: number): string {
    return `Booking confirmed: ${ticketCount} ticket(s) for "${eventName}" – Total: €${totalPrice.toFixed(2)}`;
  }

  // calculates the ticket price
  calculateTicketPrice(options: Record<string, any>): number {
    // initialize finalPrice to 0
    let finalPrice = 0;
    // check if quantity is greater than 0
    if (options["quantity"] > 0) {
      finalPrice = options["basePrice"] * options["quantity"] * (options["isEarlyBird"] ? (1 - 0.15) : 1) * (options["isVip"] ? 1.5 : 1) * (options["quantity"] >= 10 ? 0.85 : 1) * 1.19;
    }
    // round to 2 decimal places
    return Math.round(finalPrice * 100) / 100;
  }

  // generates a summary message for the event
  generateSummaryMessage(eventName: string, date: string, venue: string): string {
    // create an array of parts
    const parts: string[] = [];
    parts.push("Event Summary:");
    parts.push(eventName);
    parts.push("Date: " + date);
    parts.push("Venue: " + venue);
    // join with pipe separator
    return parts.join(" | ");
  }

  // sends a booking confirmation email
  sendBookingConfirmation(email: string, eventName: string, ticketCount: number, totalPrice: number): void {
    // connect to smtp server
    this.notificationService.connect("smtp.tickets.example.com", 587, true);
    // send the email
    this.notificationService.sendMail(
      email,
      "Booking Confirmation – " + eventName,
      this.generateConfirmationMessage(eventName, ticketCount, totalPrice)
    );
    // disconnect
    this.notificationService.disconnect();
  }

  // formats a date as dd.mm.yyyy
  formatEventDate(date: Date): string {
    // get the day
    const day = date.getDate().toString().padStart(2, "0");
    // get the month
    const month = (date.getMonth() + 1).toString().padStart(2, "0");
    // get the year
    const year = date.getFullYear();
    return `${day}.${month}.${year}`;
  }

  // constructor
  constructor(notificationService: NotificationService) {
    this.notificationService = notificationService;
  }

  // generates a receipt message
  generateReceiptMessage(eventName: string, ticketCount: number, totalPrice: number, customerName: string): string {
    return "Receipt for " + customerName + " – " + ticketCount.toString() + "x " + eventName + " – Total: €" + totalPrice.toFixed(2);
  }
}
