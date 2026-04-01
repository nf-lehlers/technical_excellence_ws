import { describe, it, expect } from "vitest";
import { EventTicketService, NotificationService } from "./event-ticket-service";

describe("EventTicketService", () => {
  function createService() {
    const notificationService = new NotificationService();
    const service = new EventTicketService(notificationService);
    return { service, notificationService };
  }

  describe("calculateTicketPrice", () => {
    it("calculates normal price with tax", () => {
      const { service } = createService();
      const price = service.calculateTicketPrice({ basePrice: 100, quantity: 1, isEarlyBird: false, isVip: false });
      expect(price).toBe(119.0);
    });

    it("returns 0 for zero quantity", () => {
      const { service } = createService();
      const price = service.calculateTicketPrice({ basePrice: 100, quantity: 0 });
      expect(price).toBe(0);
    });

    it("applies early bird discount", () => {
      const { service } = createService();
      const price = service.calculateTicketPrice({ basePrice: 100, quantity: 1, isEarlyBird: true, isVip: false });
      expect(price).toBe(101.15);
    });

    it("applies VIP surcharge", () => {
      const { service } = createService();
      const price = service.calculateTicketPrice({ basePrice: 100, quantity: 1, isEarlyBird: false, isVip: true });
      expect(price).toBe(178.5);
    });

    it("applies group discount for 10+ tickets", () => {
      const { service } = createService();
      const price = service.calculateTicketPrice({ basePrice: 100, quantity: 10, isEarlyBird: false, isVip: false });
      expect(price).toBe(1011.5);
    });

    it("combines early bird and VIP", () => {
      const { service } = createService();
      const price = service.calculateTicketPrice({ basePrice: 100, quantity: 1, isEarlyBird: true, isVip: true });
      expect(price).toBe(151.73);
    });

    it("combines early bird and group discount", () => {
      const { service } = createService();
      const price = service.calculateTicketPrice({ basePrice: 100, quantity: 10, isEarlyBird: true, isVip: false });
      expect(price).toBe(859.78);
    });

    it("combines VIP and group discount", () => {
      const { service } = createService();
      const price = service.calculateTicketPrice({ basePrice: 100, quantity: 10, isEarlyBird: false, isVip: true });
      expect(price).toBe(1517.25);
    });

    it("combines all: early bird, VIP, group", () => {
      const { service } = createService();
      const price = service.calculateTicketPrice({ basePrice: 100, quantity: 10, isEarlyBird: true, isVip: true });
      expect(price).toBe(1289.66);
    });
  });

  describe("message generation", () => {
    it("generates confirmation message", () => {
      const { service } = createService();
      const msg = service.generateConfirmationMessage("Rock Concert", 2, 238.0);
      expect(msg).toContain("Rock Concert");
      expect(msg).toContain("2 ticket(s)");
      expect(msg).toContain("238.00");
    });

    it("generates receipt message", () => {
      const { service } = createService();
      const msg = service.generateReceiptMessage("Rock Concert", 2, 238.0, "Max Mustermann");
      expect(msg).toContain("Max Mustermann");
      expect(msg).toContain("2x Rock Concert");
      expect(msg).toContain("238.00");
    });

    it("generates summary message", () => {
      const { service } = createService();
      const msg = service.generateSummaryMessage("Rock Concert", "2026-04-15", "Hamburg Arena");
      expect(msg).toContain("Rock Concert");
      expect(msg).toContain("2026-04-15");
      expect(msg).toContain("Hamburg Arena");
    });
  });

  describe("sendBookingConfirmation", () => {
    it("sends notification via notification service", () => {
      const { service, notificationService } = createService();
      service.sendBookingConfirmation("test@example.com", "Rock Concert", 2, 238.0);
      expect(notificationService.sentMessages).toHaveLength(1);
      expect(notificationService.sentMessages[0]).toContain("test@example.com");
      expect(notificationService.sentMessages[0]).toContain("Rock Concert");
    });
  });
});
