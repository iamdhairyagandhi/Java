package processor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap; 

public class FileProcessor implements Runnable {

	private TreeMap<Integer, TreeMap<String, Integer>> customers; 
	private String fileName;
	private TreeMap<String, Integer> itemsSold; 


	public FileProcessor(String name, TreeMap<Integer, TreeMap<String, 
			Integer>> customers, TreeMap<String, Integer> itemsSold) {
		this.fileName = name;
		this.customers = customers; 
		this.itemsSold = itemsSold; 
	}

	public void run() {
		synchronized(customers){
			try {
				File file = new File(fileName); 
				BufferedReader br = new BufferedReader(new FileReader(file));
				String currLine = br.readLine(); 
				int index = currLine.indexOf(' '); 
				int id = Integer.parseInt(currLine.substring(index+1));
				customers.put(id, new TreeMap<>());
				System.out.println("Reading order for client with id: " + id);
				currLine = br.readLine(); 

				while (currLine != null) {
					String name = currLine.substring(0, currLine.indexOf(' ')); 
					if (customers.get(id)
							.containsKey(name))
						customers.get(id)
						.put(name, customers.get(id).get(name)+1);
					else
						customers.get(id).put(name, 1); 
					currLine = br.readLine(); 

					if (itemsSold.containsKey(name)) 
						itemsSold.put(name, itemsSold.get(name) + 1); 
					else itemsSold.put(name, 1); 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}