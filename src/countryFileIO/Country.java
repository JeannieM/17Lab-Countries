package countryFileIO;
/**
 * @author JeannieMcCarthy
 *
 */
public class Country {
	private String name;
	private int population;
	private String rulingMonarch;
	
	public Country(String name, String rulingMonarch, int population) {
		super();
		this.name = name;
		this.population = population;
		this.rulingMonarch = rulingMonarch;
	}
	
	public String getName() {
		return name;
	}
	public int getPopulation() {
		return population;
	}
	public String getRulingMonarch() {
		return rulingMonarch;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public void setRulingMonarch(String rulingMonarch) {
		this.rulingMonarch = rulingMonarch;
	}

	@Override
	public String toString() {
		return String.format("%-20s   |  %-20s    |  %-10s\n", name, rulingMonarch, population);
	}
	
	
	
}
