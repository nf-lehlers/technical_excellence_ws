package workshop.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorTest {

	@Test
	@Disabled
	void addsTwoNumbers() {
		assertThat(new Calculator().add(2, 3)).isEqualTo(5);
	}


	@Test
	void emptyShouldReturnZero() {
		String input = "";

		assertThat(new Calculator().sum(input)).isEqualTo(0);
	}

	@Test
	void stringOneReturnsOne() {
		String input = "1";

		assertThat(new Calculator().sum(input)).isEqualTo(1);
	}

	@Test
	void letterThrowsException() {
		String input = "a";


		assertThatThrownBy(() -> new Calculator().sum(input)).isInstanceOf(NumberFormatException.class);
	}

	@Test
	void stringOf2_5_7returns14() {
		String input = "2,5,7";
		assertThat(new Calculator().sum(input)).isEqualTo(14);
	}

	@Test
	void emptyStringRturnsZero() {
		String input = " ";

		assertThat(new Calculator().sum(input)).isEqualTo(0);
	}

	@Test
	void negativNumberThrowsException() {
		String input = "-1";
		assertThatThrownBy(() -> new Calculator().sum(input)).isInstanceOf(NumberFormatException.class);
	}

	@Test
	void nullReturnsZero() {
		String input = null;
		assertThat(new Calculator().sum(input)).isEqualTo(0);
	}

	@Test
	void negativeNumberInListThrowsException() {
		String input = "1,5,-3";
		assertThatThrownBy(() -> new Calculator().sum(input)).isInstanceOf(NumberFormatException.class);
	}

}
