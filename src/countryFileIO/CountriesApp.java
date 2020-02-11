package countryFileIO;

/**
 * @author JeannieMcCarthy
 *
 */
import java.util.*;

public class CountriesApp {
	// variables
	private static Scanner scnr = new Scanner(System.in);
	private static FileHelper<Country> fileHelper = new FileHelper<Country>("CountriesTextFile",
			new CountryLineConverter());
	public static CountriesApp instance = new CountriesApp();

	// main
	public static void main(String[] args) {
		// variables
		int userChoice;

		initializeFile();
		welcomeUser();
		do {
			userChoice = optionsMenu();
			if (userChoice != 5) {
				instance.doWhatTheUserWants(userChoice);
			} else
				break;
		} while (true);
		sayGoodbye();

	}

	// static methods
	public static void initializeFile() {
		fileHelper.rewrite(Arrays.asList(new Country("Honahlee", "Puff", 1), new Country("Sixpence", "The Baker", 24),
				new Country("Baa Baa", "Black Sheep", 3), new Country("York", "The Duke", 10000),
				new Country("London Bridge", "The Fallen", -8), new Country("The Moon", "The Cow", 6),
				new Country("The Farm", "Old McDonald", 11)));
	}

	private static void welcomeUser() {
		System.out.println("Welcome to the Very True and Real countries tracker.\n");
	}

	private static int optionsMenu() {
		System.out.printf("Options:\n1.  %s\n2.  %s\n3.  %s\n4.  %s\n5.  %s\n", "Display Country info", "Add a Country",
				"Delete a Country", "Edit a Country's Population", "Quit\n");
		int userChoice = scnr.nextInt();
		scnr.nextLine();
		return userChoice;
	}

	public static void sayGoodbye() {
		System.out.println("Thank you, Goodbye");
	}

	// instance methods
	private List<Country> getFileAsList() {
		List<Country> countries = fileHelper.readAll();
		return countries;
	}

	private void doWhatTheUserWants(int userChoice) {
		switch (userChoice) {
		case 1:
			listCountries(getFileAsList());
			break;
		case 2:
			addCountry();
			break;
		case 3:
			deleteCountry(getFileAsList());
			break;
		case 4:
			editPop(getFileAsList());
		}

	}

	// I don't understand why if I move this method up in the code errors explode
	// everywhere but if I leave it where it is it's fine
	private static void listCountries(List<Country> countries) {
		sortList(countries, determineSortOrder());
		int i = 1;
		for (Country country : countries) {
			System.out.println(i + ". " + country);
			i++;
		}
	}

	private static int determineSortOrder() {
		int userSort = Validator.getInt(scnr, "Sort by Country Name(1), Ruling Monarch(2), or Country Population(3):", 1, 3);
		System.out.println(userSort);
		return userSort;
	}

	private static void sortList(List<Country> countries, int userSort) {
		if (userSort == 1) {
			Collections.sort(countries, new CountriesNameSort());
			fileHelper.rewrite(countries);
		} else if (userSort == 2) {
			Collections.sort(countries, new CountriesMonarchSort());
			fileHelper.rewrite(countries);
		} else {
			Collections.sort(countries, new CountriesPopSort());
			fileHelper.rewrite(countries);
		}
	}

	private void addCountry() {
		fileHelper.append(getNewCountry());
		System.out.println("Country Successfully added.");
	}

	private Country getNewCountry() {
		String name = Validator.getString(scnr, "Enter Country Name: \n");
		String rulingMonarch = Validator.getString(scnr, "Enter Ruling Monarch: \n");
		int population = Validator.getInt(scnr, "Enter Population: \n");
		return new Country(name, rulingMonarch, population);
	}

	private void deleteCountry(List<Country> countries) {
		int userDelete = Validator.getInt(scnr, "Enter the number of the country you want to delete: ", 1,
				countries.size());
		if (Validator.booleanYN(scnr, "Are you sure?")) {
			countries.remove(userDelete - 1);
			fileHelper.rewrite(countries);
			System.out.println("Country successfully deleted");
		} else
			return;
	}

	private void editPop(List<Country> countries) {
		// are you sure?
		if (Validator.booleanYN(scnr, "Are you sure?")) {
			int userEdit = Validator.getInt(scnr, "Enter the number of the country you want to edit: ", 1,
					countries.size());
			// Get the name and rulingMonarch for rewrite
			String name = countries.get(userEdit - 1).getName();
			String rulingMonarch = countries.get(userEdit - 1).getRulingMonarch();
			int newPop = Validator.getInt(scnr, "Enter the new Population");
			// remove old entry completely
			countries.remove(userEdit - 1);
			// add new entry
			countries.add(new Country(name, rulingMonarch, newPop));
			fileHelper.rewrite(countries);
			System.out.println("Country successfully edited");
		} else
			return;
	}
}
