package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AlarmTest {

	@Test
	public void alarmIsOffByDefault() {
		Alarm alarm = new Alarm(new RandomSensor());
		assertFalse(alarm.isAlarmOn());
	}

	@Test
	void alarmIsOnIfPressureIsBelowRange() {

		StubbedSensor sensor = new StubbedSensor();
		Alarm alarm = new Alarm(sensor);
		sensor.setNextPressure(16);

		alarm.check();

		assertTrue(alarm.isAlarmOn());
	}

	@Test
	void alarmIsOnIfPressureIsAboveRange() {

		StubbedSensor sensor = new StubbedSensor();
		Alarm alarm = new Alarm(sensor);
		sensor.setNextPressure(22);

		alarm.check();

		assertTrue(alarm.isAlarmOn());
	}

	@Test
	void alarmIsOffIfPressureIsInRange() {

		StubbedSensor sensor = new StubbedSensor();
		Alarm alarm = new Alarm(sensor);
		sensor.setNextPressure(19);

		alarm.check();

		assertFalse(alarm.isAlarmOn());
	}

	@ParameterizedTest
	@MethodSource("alertOnPressures")
	void alarmIsOffIfPressureIsInRange(double pressure) {

		StubbedSensor sensor = new StubbedSensor();
		Alarm alarm = new Alarm(sensor);
		sensor.setNextPressure(pressure);

		alarm.check();

		assertTrue(alarm.isAlarmOn());
	}

	@ParameterizedTest
	@MethodSource("alertOffSource")
	void alarmIsOffIfPressureIsInRage(double pressure) {

	}

	static Stream<Arguments> alertOffPressures() {
		List<Arguments> args = new ArrayList<>();
		IntStream.range(17, 22)
			.forEach(i -> args.add(arguments(i)));
		return args.stream();
	}

	static Stream<Arguments> alertOnPressures() {
		List<Arguments> args = new ArrayList<>();
		IntStream.range(1, 17)
			.forEach(i -> args.add(arguments(i)));

		IntStream.range(22, 30)
			.forEach(i -> args.add(arguments(i)));


		return args.stream();
//		return Stream.of(
//			arguments(1, true),
//			arguments(2, true),
//			arguments(3, true),
//			arguments(4, true),
//			arguments(5, true),
//			arguments(6, true),
//			arguments(7, true),
//			arguments(8, true),
//			arguments(9, true),
//			arguments(10, true),
//			arguments(11, true),
//			arguments(12, true),
//			arguments(13, true),
//			arguments(14, true),
//			arguments(15, true),
//			arguments(16, true),
//			arguments(17, false),
//			arguments(18, false),
//			arguments(19, false),
//			arguments(20, false),
//			arguments(21, false),
//			arguments(22, true),
//			arguments(23, true),
//			arguments(24, true),
//			arguments(25, true),
//			arguments(26, true),
//			arguments(27, true)
//		);
	}
}
