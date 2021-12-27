package foodManagement;

/*
 * A SortedListOfImmutables represents a sorted collection of immutable objects
 * that implement the Listable interface.
 * 
 * An array of references to Listable objects is used internally to represent
 * the list.
 * 
 * The items in the list are always kept in alphabetical order based on the
 * names of the items. When a new item is added into the list, it is inserted
 * into the correct position so that the list stays ordered alphabetically by
 * name.
 */
public class SortedListOfImmutables {


	private Listable[] items;

	/*
	 * This constructor creates an empty list by creating an internal array of size
	 * 0.
	 */
	public SortedListOfImmutables() {
		items = new Listable[0];
	}

	/*
	 * Copy constructor. The current object will become a copy of the list that the
	 * parameter refers to.
	 * 
	 * The copy must be made in such a way that future changes to either of these
	 * two lists will not affect the other. In other words, after this constructor
	 * runs, adding or removing things from one of the lists must not have any
	 * effect on the other list.
	 */
	public SortedListOfImmutables(SortedListOfImmutables other) {
		items = new Listable[other.getSize()];
		for (int i = 0; i < items.length; i++) {
			items[i] = other.items[i];
		}
	}

	/*
	 * Returns the number of items in the list.
	 */
	public int getSize() {
		return items.length;
	}

	/*
	 * Returns a reference to the item in the ith position in the list.
	 */
	public Listable get(int i) {
		return items[i];
	}

	/*
	 * Adds an item to the list. This method assumes that the list is already sorted
	 * in alphabetical order based on the names of the items in the list.
	 * 
	 * The new item will be inserted into the list in the appropriate place so that
	 * the list will remain alphabetized by names.
	 * 
	 * In order to accomodate the new item, the internal array must be re-sized so
	 * that it is one unit larger than it was before the call to this method.
	 */
	public void add(Listable itemToAdd) {
		Listable[] tempItems = new Listable[items.length + 1];
		boolean replaced = false;
		for (int a = 0; a < items.length; a++) {
			tempItems[a] = items[a];
		}
		if (items.length == 0) {
			tempItems[0] = itemToAdd;
		} else {
			for (int i = 0; i < items.length; i++) { // checks alphabetically
				if (itemToAdd.getName().compareTo(items[i].getName()) < 0 && replaced == false) {

					tempItems[i] = itemToAdd;
					for (int b = i + 1; b < tempItems.length; b++) {
						tempItems[b] = items[b - 1];
					}
					replaced = true;
				}
			}
			if (replaced == false) {
				tempItems[tempItems.length - 1] = itemToAdd;
			}
		}

		items = new Listable[tempItems.length];
		for (int u = 0; u < tempItems.length; u++) {
			items[u] = tempItems[u];
		}
	}

	/*
	 * Adds an entire list of items to the current list, maintaining the
	 * alphabetical ordering of the list by the names of the items.
	 */
	public void add(SortedListOfImmutables listToAdd) {
		for (int i = 0; i < listToAdd.getSize(); i++) {
			add((listToAdd).get(i));
		}
	}

	/*
	 * Removes an item from the list.
	 * 
	 * If the list contains the same item that the parameter refers to, it will be
	 * removed from the list.
	 * 
	 * If the item appears in the list more than once, just one instance will be
	 * removed.
	 * 
	 * If the item does not appear on the list, then this method does nothing.
	 */
	public void remove(Listable itemToRemove) {

		int x = 0;
		Listable[] removedItemList = new Listable[items.length - 1];

		for (int i = 0; i < items.length; i++) {
			if (items[i].getName().equals(itemToRemove.getName())) {
				x = i;
				for (int j = 0; j < x; j++) {
					removedItemList[j] = items[j];
				}
				for (int k = x + 1; k < items.length; k++) {
					removedItemList[k - 1] = items[k];
				}

				items = removedItemList;
				break;
			}
		}
	}

	/*
	 * Removes an entire list of items from the current list. Any items in the
	 * parameter that appear in the current list are removed; any items in the
	 * parameter that do not appear in the current list are ignored.
	 */
	public void remove(SortedListOfImmutables listToRemove) {
		for (int i = 0; i < listToRemove.getSize(); i++) {
			remove(listToRemove.get(i));

		}
	}

	/*
	 * Returns the sum of the wholesale costs of all items in the list.
	 */
	public int getWholesaleCost() {
		int wholesaleCost = 0;
		for (int i = 0; i < items.length; i++) {
			wholesaleCost += items[i].getWholesaleCost();
		}

		return wholesaleCost;
	}

	/*
	 * Returns the sum of the retail values of all items in the list.
	 */
	public int getRetailValue() {
		int retailValue = 0;
		for (int i = 0; i < items.length; i++) {
			retailValue += items[i].getRetailValue();
		}

		return retailValue;
	}

	/*
	 * Checks to see if a particular item is in the list.
	 */
	public boolean checkAvailability(Listable itemToFind) {
		boolean isAvailable = false;
		for (int i = 0; i < items.length; i++) {
			if ((items[i].getName()).equals(itemToFind.getName())) {
				isAvailable = true;
			}
		}
		return isAvailable;
	}

	/*
	 * Checks if a list of items is contained in the current list. 
	 */
	public boolean checkAvailability(SortedListOfImmutables listToCheck) {
		SortedListOfImmutables ifAvailable = new SortedListOfImmutables(this);

		boolean itemsInList = false;
		for (int i = 0; i < listToCheck.items.length; i++) {
			if (ifAvailable.checkAvailability(listToCheck.items[i]) == false) {
				itemsInList = false;
				break;
			} else {
				itemsInList = true;
				for (int j = 0; j < ifAvailable.items.length; j++) {
					if (ifAvailable.items[j].equals(listToCheck.items[i])) {
						ifAvailable.remove(ifAvailable.items[j]);
						break;
					}
				}
			}
		}
		return itemsInList;
	}

	/*
	 * ToString method which formats the items array as a list
	*/
	public String toString() {
		String retValue = "[ ";
		for (int i = 0; i < items.length; i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += items[i];
		}
		retValue += " ]";
		return retValue;
	}
}
