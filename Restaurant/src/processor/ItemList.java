package processor;

public class ItemList implements Comparable {

	protected String name;
	protected double price;
	protected int hashCode;
	
	
	public ItemList(String name, double price) {
		this.name = name;
		this.price = price;
		this.hashCode = name.hashCode();
	}


	@Override
	public int hashCode() {
		return hashCode;
	}


	@Override
	public String toString() {
		return name;
	}


	@Override
	public int compareTo(Object o) {
		String nameComp1 = this.name;
		String nameComp2 =((ItemList)o).name;
		return nameComp1.compareToIgnoreCase(nameComp2);
	}


	
	
	
	
}
