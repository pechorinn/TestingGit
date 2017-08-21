package musicinstruments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeSet;

import musicinstruments.brass.Clarinet;
import musicinstruments.brass.Flute;

import musicinstruments.brass.Trombone;
import musicinstruments.brass.Trumpet;
import musicinstruments.electronic.BassGuitar;
import musicinstruments.electronic.ElectricViolin;

import musicinstruments.electronic.Synthesizer;
import musicinstruments.keyboard.Accordion;

import musicinstruments.keyboard.Organ;
import musicinstruments.keyboard.Piano;
import musicinstruments.percussion.DjembeDrums;
import musicinstruments.percussion.Drums;

import musicinstruments.percussion.Tambourine;
import musicinstruments.stringed.Contrabass;
import musicinstruments.stringed.Fiddle;
import musicinstruments.stringed.Harp;
import musicinstruments.stringed.Viola;
import musicinstruments.stringed.Violin;

public class Shop {

	private String name;
	private CashDesk cashDesk;

	HashMap<String, ArrayList<MusicInstrument>> products;

	// create the shop with pre-added products
	Shop() {
		this.cashDesk = new CashDesk();
		products = new HashMap<>();
		
		
		this.addInstrument("Stringed", new Violin());
		this.addInstrument("Stringed", new Viola());
		this.addInstrument("Stringed", new Contrabass());
		this.addInstrument("Stringed", new Fiddle());
		this.addInstrument("Stringed", new Harp());
		this.addInstrument("Brass", new Trombone());
		this.addInstrument("Brass", new Trumpet());
		this.addInstrument("Brass", new Flute());
		this.addInstrument("Brass", new Clarinet());
		this.addInstrument("Percussion", new Tambourine());
		this.addInstrument("Percussion", new DjembeDrums());
		this.addInstrument("Percussion", new Drums());
		this.addInstrument("Keyboard", new Piano());
		this.addInstrument("Keyboard", new Organ());
		this.addInstrument("Keyboard", new Accordion());
		this.addInstrument("Electronic", new Synthesizer());
		this.addInstrument("Electronic", new ElectricViolin());
		this.addInstrument("Electronic", new BassGuitar());

	}

	// a method that adds the instruments in the shop when it's being created
	private void addInstrument(String type, MusicInstrument instr) {
		// check if the products contain the given type
		if (!products.containsKey(type)) {
			// if not - put the type as a key and initialize new arraylist with
			// instruments;
			products.put(type, new ArrayList<>());
		}
		// add the instrument in the certain type
		
		products.get(type).add(instr);
	}

	// a method that sells instruments by name and units
	public void sellInstrument(String name, int units) {
		// iterate over the products
	boolean	foundInstrument = false;
		for (Entry<String, ArrayList<MusicInstrument>> instr : products.entrySet()) {
			for (MusicInstrument m : instr.getValue()) {
				// check which one equals the given one
				if (m.getName().equals(name)) {
					foundInstrument = true;
					// check if there is enough units
					if (m.getQuantity() >= units) {
						// if yes - reduce quantity, increase product's number
						// of sales and add money to shop's money
						m.setQuantity(m.getQuantity() - units);
						m.setNumberOfSales(units);
						this.cashDesk.addMoney(m.getPrice() * units);
						System.out.println("==================================================");
						System.out.println("An instrument: " + m.getName() + " in quantity " + m.getQuantity() + " sold.");
						System.out.println("==================================================");

					}
					// if not - syso...
					else {
						System.out.println("==================================================");
						System.out.println("There are not enough instruments of this type.");
						System.out.println("==================================================");

					}
				} 
			}
		}
		
		if(!foundInstrument) {
			System.out.println("==================================================");
			System.out.println("No record of such an instrument - please check spelling and try again.");
			System.out.println("==================================================");

		}
	}

	// a method that accepts instruments from delivery by name and units
	public void acceptInstrument(String name, int units) {
		// check if there is such product and if yes - increase the quantity
		for (Entry<String, ArrayList<MusicInstrument>> instr : products.entrySet()) {
			for (MusicInstrument m : instr.getValue()) {
				if (m.getName().equals(name) && units > 0) {
					m.setQuantity(m.getQuantity() + units);
				}
			}
		}
	}

	// a method that prints the catalog by type
	public void printCatalog() {
		// for each type
		for (Entry<String, ArrayList<MusicInstrument>> instr : products.entrySet()) {
			System.out.println("=======" + instr.getKey() + "=======");
			// for each instrument
			for (MusicInstrument m : instr.getValue()) {
				System.out.println(m);

			}

		}
	}

	// a private method that sorts the instruments with given comparator and
	// prints the sorted catalgoue
	private void printSortedInstruments(Comparator<MusicInstrument> comp) {
		// put all instruments in treeSet and sort them with the given
		// comparator
		TreeSet<MusicInstrument> instruments = new TreeSet<>(comp);
		for (Entry<String, ArrayList<MusicInstrument>> m : products.entrySet()) {
			instruments.addAll(m.getValue());
		}

		// print the instruments
		for (MusicInstrument musicInstrument : instruments) {
			System.out.println(musicInstrument);
		}
		System.out.println("================================");

	}

	// methods that calls the above method(for sorting and printing) with
	// different comparators;
	public void printCatalogByName() {
		System.out.println("----Sorted by name-----");
		printSortedInstruments(new Comparator<MusicInstrument>() {

			@Override
			public int compare(MusicInstrument o1, MusicInstrument o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	public void printCatalogByAscendingPrice() {
		System.out.println("----Sorted by ascending price-----");
		printSortedInstruments(new Comparator<MusicInstrument>() {

			@Override
			public int compare(MusicInstrument o1, MusicInstrument o2) {
				// double razlika = o1.getPrice() - o2.getPrice();
				// return razlika > 0 ? 1 : (razlika < 0 ? -1 : 0);
				return Double.compare(o1.getPrice(), o2.getPrice());
			}
		});

	}

	public void printCatalogByDescendingPrice() {
		System.out.println("----Sorted by descending price-----");
		printSortedInstruments(new Comparator<MusicInstrument>() {

			@Override
			public int compare(MusicInstrument o1, MusicInstrument o2) {
				// double razlika = (o1.getPrice() - o2.getPrice()) * (-1);
				// return razlika > 0 ? 1 : (razlika < 0 ? -1 : 0);
				return Double.compare(o2.getPrice(), o1.getPrice());
			}
		});
	}

	// a method that prints the available instruments
	public void printAvailableInstruments() {
		// iterate over products and check if the instrument quantity is
		// positive
		for (Entry<String, ArrayList<MusicInstrument>> instr : products.entrySet()) {
			for (MusicInstrument musicInstrument : instr.getValue()) {
				if (musicInstrument.getQuantity() > 0) {
					System.out.println(musicInstrument);
				}

			}

		}
	}

	// a method that creates a new collection that keep all instruments with
	// their number of sales
	private LinkedList<Entry<MusicInstrument, Integer>> putInstrumetsWithTheirNumberOfSalesInList() {
		// create hashmap instrument -> numberofsales and put all instruments
		// and their number of sales
		HashMap<MusicInstrument, Integer> soldInstruments = new HashMap<>();
		for (Entry<String, ArrayList<MusicInstrument>> instr : products.entrySet()) {
			for (MusicInstrument m : instr.getValue()) {
				if (m.getNumberOfSales() > 0) {
					soldInstruments.put(m, m.getNumberOfSales());
				}

			}

		}
		// create a list (for later to sort and iterate) of entries and return
		// it
		LinkedList<Entry<MusicInstrument, Integer>> list = new LinkedList<>(soldInstruments.entrySet());
		return list;
	}

	// method that calls the above method and sorts the instruments by number of
	// sales
	public void printInfoForSoldInstruments() {
		LinkedList<Entry<MusicInstrument, Integer>> list = new LinkedList<>(
				putInstrumetsWithTheirNumberOfSalesInList());
		list.sort(new Comparator<Entry<MusicInstrument, Integer>>() {

			@Override
			public int compare(Entry<MusicInstrument, Integer> o1, Entry<MusicInstrument, Integer> o2) {
				return (o1.getValue() - o2.getValue()) * (-1);
			}
		});

		// print the instruments
		for (Entry<MusicInstrument, Integer> e : list) {
			System.out.println(e.getKey() + ", number of sales=" + e.getValue());
		}
	}

	// use the same method from above and calculate the accumulated money from
	// sales
	public void printInfoForAccumulatdedMoneyFromSales() {
		LinkedList<Entry<MusicInstrument, Integer>> list = new LinkedList<>(
				putInstrumetsWithTheirNumberOfSalesInList());
		double sumFromSales = 0;
		// sum all number of sales multiplied by the price of each product
		for (Entry<MusicInstrument, Integer> entry : list) {
			sumFromSales += entry.getValue() * entry.getKey().getPrice();
		}

		System.out.println("The total sum from sales is: " + sumFromSales);
	}

	// a method that finds the best seller product (uses the above method)
	public void printInfoForBestSellerInstrument() {
		// find the entry with the greatest value
		LinkedList<Entry<MusicInstrument, Integer>> list = new LinkedList<>(
				putInstrumetsWithTheirNumberOfSalesInList());
		Entry<MusicInstrument, Integer> bestSeller = list.getFirst();
		for (Entry<MusicInstrument, Integer> entry : list) {
			if (bestSeller.getValue() < entry.getValue()) {
				bestSeller = entry;
			}
		}
		System.out.println("The best seller is " + bestSeller.getKey() + ", sold " + bestSeller.getValue() + " times");

	}

	// analogical
	public void printInfoForLeastSoldInstrument() {
		LinkedList<Entry<MusicInstrument, Integer>> list = new LinkedList<>(
				putInstrumetsWithTheirNumberOfSalesInList());
		Entry<MusicInstrument, Integer> LeastSoldInstrument = list.getFirst();
		for (Entry<MusicInstrument, Integer> entry : list) {
			if (LeastSoldInstrument.getValue() > entry.getValue()) {
				LeastSoldInstrument = entry;
			}
		}
		System.out.println("The least sold instrument is " + LeastSoldInstrument.getKey() + ", sold "
				+ LeastSoldInstrument.getValue() + " times");

	}

	// a method that finds the type of instruments with greatest number of
	// sales; this method uses the original collection(products) cause it keeps
	// the type of instruments
	public void printInfoForBestSellerTypeOfInstrumentsBySoldUnits() {
		// create a list of entries and iterate to find the
		// entry(type->instruments) with the greatest number of sales
		LinkedList<Entry<String, ArrayList<MusicInstrument>>> list = new LinkedList<>(products.entrySet());
		Entry<String, ArrayList<MusicInstrument>> bestSellerType = list.getFirst();
		double greatestNumberOfSales = 0;
		double currentNumberOfSales = 0;
		for (Entry<String, ArrayList<MusicInstrument>> instr : products.entrySet()) {
			for (MusicInstrument musicInstrument : instr.getValue()) {
				currentNumberOfSales += musicInstrument.getNumberOfSales();
			}
			if (greatestNumberOfSales < currentNumberOfSales) {
				greatestNumberOfSales = currentNumberOfSales;
				currentNumberOfSales = 0;
				bestSellerType = instr;
			}
		}

		System.out.println("Best seller type by number of sales is " + bestSellerType.getKey()
				+ " with total number of sales - " + greatestNumberOfSales);

	}

	// analogical but finds the type with most accumulated money from sales
	public void printInfoForBestSellerTypeOfInstrumentsByAccumulatedMoney() {
		LinkedList<Entry<String, ArrayList<MusicInstrument>>> list = new LinkedList<Entry<String, ArrayList<MusicInstrument>>>(
				products.entrySet());
		Entry<String, ArrayList<MusicInstrument>> bestSellerType = list.getFirst();
		double greatestSumFromSales = 0;
		double currentSumFromSales = 0;
		for (Entry<String, ArrayList<MusicInstrument>> instr : products.entrySet()) {
			for (MusicInstrument musicInstrument : instr.getValue()) {
				currentSumFromSales += (musicInstrument.getNumberOfSales() * musicInstrument.getPrice());
			}
			if (greatestSumFromSales < currentSumFromSales) {
				greatestSumFromSales = currentSumFromSales;
				currentSumFromSales = 0;
				bestSellerType = instr;
			}
		}

		System.out.println("Best seller type by accumulated money is " + bestSellerType.getKey()
				+ " with total number of sales - " + greatestSumFromSales);
	}
}