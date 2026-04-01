package workshop.example;

import java.time.LocalDate;

public class Email {
	private String message;
	private String adress;
	private LocalDate sendDate;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setSendDate(LocalDate sendDate) {
		this.sendDate = sendDate;
	}

	public LocalDate getSendDate() {
		return sendDate;
	}
}