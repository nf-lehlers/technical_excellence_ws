package workshop.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StubbedEmailClient implements EmailClient {
	private final LocalDate currentDate;
	List<Email> lastEmails = new ArrayList<>();

	public StubbedEmailClient(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	@Override
	public void sendEmail(String subject, String emailAdress) {
		Email email = new Email();
		email.setMessage(subject);
		email.setAdress(emailAdress);
		email.setSendDate(currentDate);
		this.lastEmails.add(email);
	}

	public List<Email> getLastEmails() {
		return lastEmails;
	}
}
