package workshop.example;

import static workshop.example.Constants.THREE_LETTER_DISCOUNT_RATE;

public class ThreeLetterDiscount {
    private final Seminar seminar;

    public ThreeLetterDiscount(Seminar seminar) {
        this.seminar = seminar;
    }

    public void apply() {
        seminar.setNetPrice(seminar.getOriginalNetPrice() - discount());
    }

    public boolean isGranted() {
        seminar.getTitle();
        return seminar.getTitle().length() <= 3;
    }

    public double discount() {
        return seminar.getOriginalNetPrice() * discountRate();
    }

    public double discountRate() {
        return !isGranted() ? 0 : THREE_LETTER_DISCOUNT_RATE / 100;
    }
}
