import { describe, expect, it, vi } from "vitest";
import { Seminar } from "./Seminar";
import { ThreeLetterDiscount } from "./ThreeLetterDiscount";

const createSeminarFixture = ({
  title = "Introduction to Test-Driven Development",
  netPrice = 100,
  taxFree = false,
} = {}) => {
  return new Seminar(title, netPrice, taxFree);
};

describe("Seminar", () => {
  it("should calculate correct grossPrices", () => {
    const seminar = new Seminar("OOP", 700, false);

    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.grossPrice).toEqual(1044.2131);

    seminar.netPrice = 300;
    discount.apply();
    expect(seminar.grossPrice).toEqual(373.65999999999997);

    seminar.taxFree = true;
    discount.apply();
    expect(seminar.grossPrice).toEqual(299.75);

    seminar.title = "Objekt-Orientierte Programmierung";
    discount.apply();
    expect(seminar.grossPrice).toEqual(299.75);
  });
});
