package countryFileIO;

import java.util.Comparator;

/**
 * @author JeannieMcCarthy
 *
 */
public class CountriesMonarchSort implements Comparator<Country>{

	@Override
	public int compare(Country c1, Country c2) {
		return c1.getRulingMonarch().compareToIgnoreCase(c2.getRulingMonarch());
	}
	
}
