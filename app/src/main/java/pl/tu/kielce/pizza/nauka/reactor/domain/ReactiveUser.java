package pl.tu.kielce.pizza.nauka.reactor.domain;

public class ReactiveUser {

	public static final ReactiveUser SKYLER = new ReactiveUser("swhite", "Skyler", "White");
	public static final ReactiveUser JESSE = new ReactiveUser("jpinkman", "Jesse", "Pinkman");
	public static final ReactiveUser WALTER = new ReactiveUser("wwhite", "Walter", "White");
	public static final ReactiveUser SAUL = new ReactiveUser("sgoodman", "Saul", "Goodman");

	private final String username;

	private final String firstname;

	private final String lastname;

	public ReactiveUser(String username, String firstname, String lastname) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ReactiveUser ReactiveUser = (ReactiveUser) o;

		if (!username.equals(ReactiveUser.username)) {
			return false;
		}
		if (!firstname.equals(ReactiveUser.firstname)) {
			return false;
		}
		return lastname.equals(ReactiveUser.lastname);

	}

	@Override
	public int hashCode() {
		int result = username.hashCode();
		result = 31 * result + firstname.hashCode();
		result = 31 * result + lastname.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Person{" +
				"username='" + username + '\'' +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				'}';
	}
}
