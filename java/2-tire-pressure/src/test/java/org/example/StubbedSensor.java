package org.example;

public class StubbedSensor implements Sensor {
	private double nextPressure;

	@Override
	public double popNextPressurePsiValue() {
		return nextPressure;
	}

	public void setNextPressure(double nextPressure) {
		this.nextPressure = nextPressure;
	}
}
