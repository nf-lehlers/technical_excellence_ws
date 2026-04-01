package workshop.example;

import java.util.List;

public class GreetingsService {
	private final EmailClient emailClient;
	private final UserRpository userRepository;

	public GreetingsService(EmailClient emailClient, UserRpository userRepository) {
		this.emailClient = emailClient;
		this.userRepository = userRepository;
	}

	public void sendBirthdayGreetingsForToday() {
		List<User> birthdayUsers = userRepository.getFriendsWhoseBirthdayIsToday();

		birthdayUsers.forEach(birthdayUser -> {
			String message = """
				Subject: Happy birthday!
				
				Happy birthday, dear %s!
				""".formatted(birthdayUser.vorname());

			emailClient.sendEmail(message, birthdayUser.email());
		});
	}
}
