package workshop.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SeminarTest {

    @Test
    void shouldCalculateCorrectGrossPrices() {
        Seminar seminar = new Seminar("OOP", 700, false);

        ThreeLetterDiscount discount = new ThreeLetterDiscount(seminar);
        discount.apply();
        assertThat(seminar.getGrossPrice()).isEqualTo(1044.2131);

        seminar.setNetPrice(300);
        discount.apply();
        assertThat(seminar.getGrossPrice()).isEqualTo(373.65999999999997);

        seminar.setTaxFree(true);
        discount.apply();
        assertThat(seminar.getGrossPrice()).isEqualTo(299.75);

        seminar.setTitle("Objekt-Orientierte Programmierung");
        discount.apply();
        assertThat(seminar.getGrossPrice()).isEqualTo(299.75);
    }
}
