package processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.TreeMap;
// @SuppressWarnings("unused")
public class Processor {
	private TreeMap<String, Double> listItems; 
	private int type; // 1 for single threaded 2 for multi threaded 
	private String customerFileName; 
	private String resultsFileName;
	private int number;
	// this tree map stores customers and the number of items that they purchased.
	TreeMap<Integer, TreeMap<String, Integer>> customers; 


	public Processor() {
		// Default constructor
	}
	/**
	 * Constructor. It prepares the class for orders processing. 
	 * It initializes the variables
	 * @param type
	 * @param inventoryName
	 * @param customerFileName
	 * @param resultsFileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	
	// single threaded or multi threaded
	// customer base file name
	// future name of file where results stored
	// number of files to be evaluated
	// initialization of list of items
	public Processor(int type, String inventoryName, String customerFileName
			,String resultsFileName, int number) throws FileNotFoundException, IOException {
		this.type = type; 
		this.customerFileName = customerFileName; 
		this.resultsFileName = resultsFileName; 
		this.number = number; 
		listItems = new TreeMap<>(); 
		customers = new TreeMap<>();

		File inv = new File(inventoryName); 
		BufferedReader reader = new BufferedReader(new FileReader(inv)); 
		String curr = reader.readLine();
		while (curr != null) {
			int index = curr.indexOf(' '); 
			String item = curr.substring(0, index); 
			double price = Double.parseDouble(curr.substring(index+1));
			listItems.put(item, price); 
			curr = reader.readLine(); 
		}
	}


	public void processingCode() throws FileNotFoundException, 
	IOException {
		if (type == 1) {
			singleThreadedProcessor();
		}
		else {
			multiThreadedProcessor(); 
		}
	}

	public void multiThreadedProcessor() {

		TreeMap<String, Integer> itemsSold = new TreeMap<>();
		ArrayList<Thread> list = new ArrayList<>(); 
		int counter = 1; 
		String file = customerFileName + counter + ".txt";
		while (counter <= number) {
			Thread x = new Thread(new FileProcessor(file, customers, itemsSold)); 
			list.add(x); 
			x.start();
			file =  customerFileName + ++counter + ".txt";
		}
		for (Thread x : list) {
			try {
				x.join();
			} catch (InterruptedException e) {

			}
		}
		try {
			this.writeInFile(itemsSold);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	// Keeping track of number of items sold
	// You have this map ready with all customers ids and number of items they've 
	// purchased 
	@SuppressWarnings("resource")
	public void singleThreadedProcessor() throws FileNotFoundException, IOException {
		String currFile = customerFileName + 1 + ".txt";
		
		TreeMap<String, Integer> itemsSold = new TreeMap<>(); 

		int counter = 1; 
		while (counter <= number) {

			File file = new File(currFile); 
			BufferedReader br = new BufferedReader(new FileReader(file));
			String currLine = br.readLine(); 
			int index = currLine.indexOf(' '); 
			int id = Integer.parseInt(currLine.substring(index+1));
			customers.put(id, new TreeMap<>());
			System.out.println("Reading order for client with id: " + id);
			currLine = br.readLine(); 

			while (currLine != null) {
				String name = currLine.substring(0, currLine.indexOf(' ')); 
				if (customers.get(id).containsKey(name))
					customers.get(id).put(name, customers.get(id).get(name)+1);
				else 
					customers.get(id).put(name, 1); 
				currLine = br.readLine(); 

				if (itemsSold.containsKey(name)) 
					itemsSold.put(name, itemsSold.get(name) + 1); 
				else itemsSold.put(name, 1); 
			}
			currFile = customerFileName + ++counter + ".txt"; 
		}
	

		try {
			writeInFile(itemsSold); 
		}
		catch (Exception E) {
			E.printStackTrace();
		}
	}


	// After this I have individual customer summary. Let's make total summary

	public void writeInFile(TreeMap<String, Integer> itemsSold) throws IOException {
		String fileData = ""; 
		ArrayList<Double> custTotal = new ArrayList<>();
		for (Integer id : customers.keySet()) { 
			double total = 0; 
			fileData += "----- Order details for client with Id: "+id +" -----\n";
			for (String item : customers.get(id).keySet()) {
				double singlePrice = listItems.get(item); 
				fileData += "Item's name: " + item +", Cost per item: "
						+ NumberFormat.getCurrencyInstance().format(singlePrice)
						+", Quantity: " +customers.get(id).get(item) 
						+", Cost: " + NumberFormat.getCurrencyInstance()
						.format(singlePrice*customers.get(id).get(item)) + "\n"; 
				total += singlePrice*customers.get(id).get(item);
			}
			fileData+= "Order Total: " + NumberFormat.getCurrencyInstance()
			.format(total)+ "\n"; 
			custTotal.add(total);
		}

		double grandTotal = 0;
	
		fileData +="***** Summary of all orders *****\n"; 

		for (String item : itemsSold.keySet()) {
			double cost = listItems.get(item); 
			fileData += "Summary - Item's name: " + item +
					", Cost per item: " + NumberFormat.getCurrencyInstance().format(cost) +
					", Number sold: " + itemsSold.get(item) + 
					", Item's Total: " +
					NumberFormat.getCurrencyInstance().format(cost* itemsSold.get(item)) + "\n";
			grandTotal += cost* itemsSold.get(item); 
		}
		fileData += "Summary Grand Total: " +
				NumberFormat.getCurrencyInstance().format(grandTotal)+ "\n"; 
		File file = new File(resultsFileName);
		FileWriter fw = new FileWriter(file); 
		file.createNewFile(); 
		fw.write(fileData);
		fw.close();
	}

}
