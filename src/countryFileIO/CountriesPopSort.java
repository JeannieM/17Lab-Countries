package countryFileIO;

import java.util.Comparator;

public class CountriesPopSort implements Comparator<Country>{

	@Override
	public int compare(Country c1, Country c2) {
		return c2.getPopulation() - c1.getPopulation();
	}
	
}
