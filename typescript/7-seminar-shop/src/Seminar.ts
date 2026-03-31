import {
  RESALE_FEE_LIMIT,
  UPPER_RESALE_FEE,
  LOWER_RESALE_FEE,
  TAX_RATE,
} from "./constants";

export class Seminar {
  constructor(
    public title: string,
    private _netPrice: number,
    public taxFree: boolean
  ) {}

  get grossPrice(): number {
    return this.netPrice * this.taxRate();
  }

  get originalNetPrice(): number {
    return this._netPrice;
  }

  get netPrice(): number {
    return (
      this._netPrice +
      (this._netPrice > RESALE_FEE_LIMIT ? UPPER_RESALE_FEE : LOWER_RESALE_FEE)
    );
  }

  set netPrice(value: number) {
    this._netPrice = value;
  }

  taxRate(): number {
    return this.taxFree ? 1 : TAX_RATE; // /100
  }
}
