package util;

public class ExDetails {
	private String id;
	private String firstName;
	private String lastName;
	private String eMail;

	private static boolean isValidId(String id) {
		id = id.trim();
		if (id.length() != 8 && id.length() != 9)
			return false;
		for (int i = 0; i < id.length(); i++)
			if (!Character.isDigit(id.charAt(i)))
				return false;
		int mult = 1;
		long result = 0;
		for (int i = id.length() - 1; i >= 0; i--) {
			int digit = (id.charAt(i) - '0') * mult;
			result += digit % 10;
			result += digit / 10;
			mult = mult == 2 ? 1 : 2;
		}
		return result % 10 == 0;
	}

	public boolean similar(ExDetails other) {
		return id.equals(other.id) || firstName.equals(other.firstName)
				|| lastName.equals(other.lastName) || eMail.equals(other.eMail);
	}

	public ExDetails(String id, String firstName, String lastName,
			String eMail) {
		if (!isValidId(id))
			throw new Tester.TesterException(id + " is not a valid ID");
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String geteMail() {
		return eMail;
	}

	public String toString() {
		return String.format("%s : %s %s, Email:%s", id, firstName, lastName,
				eMail);
	}

	@Override
	public boolean equals(Object o) {
		return id.equals(((ExDetails) o).id);
	}

	// Put your details here (in English!)
	public static ExDetails firstStudent() {
		return new ExDetails("314877580", "Biran", "Fridman",
				"Biran.Fridman@e.braude.ac.il");
	}

	// Put the second student details here
	public static ExDetails secondStudent() {
		return new ExDetails("316154285", "Ilya", "Lev", "Ilia.Lev@e.braude.ac.il");
		// if there is only one student, then erase the above line, and do:
		// return null;
	}
}
