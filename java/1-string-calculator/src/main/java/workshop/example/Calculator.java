package workshop.example;

import java.util.Arrays;
import java.util.List;

public class Calculator {

	public int add(int a, int b) {
		return a + b;
	}


	public int sum(String input) {
		if (input == null || input.isBlank()) {
			return 0;
		}

		List<Integer> list = Arrays.stream(input.split(","))
			.map(Integer::parseInt).toList();

		if (list.stream().anyMatch(n -> n < 0)) {
			throw new NumberFormatException("Negative Zahlen sind nicht");
		}
		return list.stream().mapToInt(a -> a).sum();
	}
}
