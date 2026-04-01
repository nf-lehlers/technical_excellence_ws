package org.example;

import java.util.Random;

public class RandomSensor implements Sensor {

	private static final double OFFSET = 16;

	@Override
	public double popNextPressurePsiValue() {
		double pressureTelemetryValue = samplePressure();
		return OFFSET + pressureTelemetryValue;
	}

	private static double samplePressure() {
		// placeholder implementation that simulates a real sensor in a real tire
		Random basicRandomNumbersGenerator = new Random();
		return 6 * basicRandomNumbersGenerator.nextDouble() * basicRandomNumbersGenerator.nextDouble();
	}
}
