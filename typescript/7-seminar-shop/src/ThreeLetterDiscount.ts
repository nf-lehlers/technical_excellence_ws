import { Seminar } from "./Seminar";
import { THREE_LATER_DISCOUNT_RATE } from "./constants";

export class ThreeLetterDiscount {
  constructor(private seminar: Seminar) {}

  apply(): void {
    this.seminar.netPrice = this.seminar.originalNetPrice - this.discount();
  }

  isGranted(): boolean {
    this.seminar.title;
    return this.seminar.title.length <= 3;
  }

  discount(): number {
    return this.seminar.originalNetPrice * this.discountRate();
  }

  discountRate(): number {
    return !this.isGranted() ? 0 : THREE_LATER_DISCOUNT_RATE / 100;
  }
}
