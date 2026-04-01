package workshop.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BirthdayGreetingsTest {

	@Test
	void sendJohnABirthdayGreetingOnHisBirthday() {

		User john = new User("John", "Doe", LocalDate.now(), "john.doe@netfonds.example");
		StubbedEmailClient emailClient = new StubbedEmailClient(LocalDate.now());
		StubbedUserRepository userRepository = new StubbedUserRepository(LocalDate.now());
		userRepository.addUser(john);

		GreetingsService greetingsService = new GreetingsService(emailClient, userRepository);
		greetingsService.sendBirthdayGreetingsForToday();

		List<Email> lastEmail = emailClient.getLastEmails();
		assertThat(lastEmail.get(0).getMessage()).isEqualTo("""
			Subject: Happy birthday!
			
			Happy birthday, dear John!
			""");
		assertThat(lastEmail.get(0).getAdress()).isEqualTo(john.email());
	}

	@Test
	void sendJohnNoBirthdayGreetingOnNotHisBirthday() {

		User john = new User("John", "Doe", LocalDate.now().plusDays(1), "john.doe@netfonds.example");
		StubbedEmailClient emailClient = new StubbedEmailClient(LocalDate.now());
		StubbedUserRepository userRepository = new StubbedUserRepository(LocalDate.now());
		userRepository.addUser(john);

		GreetingsService greetingsService = new GreetingsService(emailClient, userRepository);
		greetingsService.sendBirthdayGreetingsForToday();

		List<Email> lastEmail = emailClient.getLastEmails();
		assertThat(lastEmail.isEmpty()).isEqualTo(true);
	}

	@Test
	void sendJohnAndJaneABirthdaygreeting() {
		User john = new User("John", "Doe", LocalDate.now(), "john.doe@netfonds.example");
		User jane = new User("Jane", "Doe", LocalDate.now(), "jane.doe@netfonds.example");

		StubbedEmailClient emailClient = new StubbedEmailClient(LocalDate.now());
		StubbedUserRepository userRepository = new StubbedUserRepository(LocalDate.now());
		userRepository.addUser(john);
		userRepository.addUser(jane);

		GreetingsService greetingsService = new GreetingsService(emailClient, userRepository);
		greetingsService.sendBirthdayGreetingsForToday();

		List<Email> lastEmails = emailClient.getLastEmails();
		assertThat(lastEmails.get(0).getMessage()).isEqualTo("""
			Subject: Happy birthday!
			
			Happy birthday, dear John!
			""");
		assertThat(lastEmails.get(0).getAdress()).isEqualTo(john.email());

		assertThat(lastEmails.get(1).getMessage()).isEqualTo("""
			Subject: Happy birthday!
			
			Happy birthday, dear Jane!
			""");
		assertThat(lastEmails.get(1).getAdress()).isEqualTo(jane.email());
	}

	@Test
	void sendGreetingsToFriendsBornOnFebruary29OnThe28InANonLeapYear() {

		User leapYearChild = new User("Leap", "Year", LocalDate.of(2000, 2, 29), "leap@year.example");

		LocalDate februar28 = LocalDate.of(2026, 2, 28);
		StubbedEmailClient emailClient = new StubbedEmailClient(februar28);
		StubbedUserRepository userRepository = new StubbedUserRepository(LocalDate.now());
		GreetingsService greetingsService = new GreetingsService(emailClient, userRepository);
		userRepository.addUser(leapYearChild);

		greetingsService.sendBirthdayGreetingsForToday();

		List<Email> lastEmails = emailClient.getLastEmails();

		assertThat(lastEmails.size()).isEqualTo(1);
		Email leapYearEmail = lastEmails.get(0);
		assertThat(leapYearEmail.getSendDate()).isEqualTo(februar28);
	}

	@Test
	void sendGreetingsToFriendsBornOnFebruary29OnThe29InALeapYear() {

		User leapYearChild = new User("Leap", "Year", LocalDate.of(2000, 2, 29), "leap@year.example");

		LocalDate februar29 = LocalDate.of(2024, 2, 29);
		StubbedEmailClient emailClient = new StubbedEmailClient(februar29);
		StubbedUserRepository userRepository = new StubbedUserRepository(februar29);
		GreetingsService greetingsService = new GreetingsService(emailClient, userRepository);
		userRepository.addUser(leapYearChild);

		greetingsService.sendBirthdayGreetingsForToday();

		List<Email> lastEmails = emailClient.getLastEmails();

		assertThat(lastEmails.size()).isEqualTo(1);
		Email leapYearEmail = lastEmails.get(0);
		assertThat(leapYearEmail.getSendDate()).isEqualTo(februar29);
	}

}
