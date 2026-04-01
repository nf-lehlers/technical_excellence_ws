package workshop.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SeminarTest {

	public class SeminarBuilder {
		private String title = "Test Seminar";
		private double netPrice = 100;
		private boolean taxFree = false;

		public SeminarBuilder withTitle(String title) {
			this.title = title;
			return this;
		}

		public SeminarBuilder withNetPrice(double netPrice) {
			this.netPrice = netPrice;
			return this;
		}

		public SeminarBuilder isTaxFree() {
			this.taxFree = true;
			return this;
		}

		public Seminar build() {
			return new Seminar(title, netPrice, taxFree);
		}
	}

	@Test
	void three_letter_discount_seminar_gets_higher_resale_rate_if_netPrice_is_over_500() {
		Seminar seminar = new SeminarBuilder().withTitle("OOP").withNetPrice(700).build();
//			new Seminar("OOP", 700, false);

		ThreeLetterDiscount discount = new ThreeLetterDiscount(seminar);
		discount.apply();
		assertThat(seminar.getGrossPrice()).isEqualTo(1044.2131);
	}

	@Test
	void three_letter_discount_seminar_gets_lower_resale_rate_if_netPrice_is_under_500() {
		Seminar seminar = new Seminar("OOP", 300, false);
		ThreeLetterDiscount discount = new ThreeLetterDiscount(seminar);

		discount.apply();

		assertThat(seminar.getGrossPrice()).isEqualTo(373.65999999999997);
	}

	@Test
	void three_letter_discount_seminar_gets_lower_resale_rate_if_netPrice_is_under_500_and_is_tax_free() {
		Seminar seminar = new Seminar("OOP", 285, true);
		ThreeLetterDiscount discount = new ThreeLetterDiscount(seminar);

		discount.apply();


		assertThat(seminar.getGrossPrice()).isEqualTo(299.75);
	}

	@Test
	void no_three_letter_discount_seminar_gets_lower_resale_rate_if_netPrice_is_under_500_and_is_tax_free() {
		Seminar seminar = new Seminar("Objekt-Orientierte Programmierung", 270.75, true);
		ThreeLetterDiscount discount = new ThreeLetterDiscount(seminar);

		discount.apply();


		assertThat(seminar.getGrossPrice()).isEqualTo(299.75);
	}
}
