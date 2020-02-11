package countryFileIO;

import java.util.Comparator;

/**
 * @author JeannieMcCarthy
 *
 */
public class CountriesNameSort implements Comparator<Country>{

	@Override
	public int compare(Country c1, Country c2) {
		return c1.getName().compareToIgnoreCase(c2.getName());
	}
	
}
