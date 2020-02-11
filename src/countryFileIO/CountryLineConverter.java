package countryFileIO;
/**
 * @author JeannieMcCarthy
 *
 */
public class CountryLineConverter implements LineConverter<Country>{

	@Override
	public String toLine(Country country) {
		return String.format("%s\t%s\t%d", country.getName(), country.getRulingMonarch(), country.getPopulation());
	}

	@Override
	public Country fromLine(String line) {
		String[] parts = line.split("\t");
		String name = parts[0];
		String rulingMonarch = parts[1];
		int population = Integer.parseInt(parts[2]);
		
		return new Country(name, rulingMonarch, population);
	}

}
