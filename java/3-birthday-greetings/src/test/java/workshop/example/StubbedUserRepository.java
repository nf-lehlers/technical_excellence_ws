package workshop.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StubbedUserRepository implements UserRpository {
	private final List<User> users = new ArrayList<>();
	private final LocalDate today;

	public StubbedUserRepository(LocalDate now) {
		today = now;
	}

	@Override
	public List<User> getFriendsWhoseBirthdayIsToday() {
		return users.stream()
			.filter(user -> today.getMonth().equals(user.birthday().getMonth())
				&& user.birthday().getDayOfMonth() == today.getDayOfMonth())
			.toList();
	}

	public void addUser(User user) {
		this.users.add(user);
	}
}
