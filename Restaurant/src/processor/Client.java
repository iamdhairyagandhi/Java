package processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.NumberFormat;
import java.util.TreeMap;

public class Client extends Thread {

	private TreeMap<String, Integer> purchasesHolder;
	private TreeMap<String, ItemList> items;
	private TreeMap<ItemList, Integer> itemsCount;

	private String orderFileName;
	private int clientId;
	private String summary;

	public Client(String orderFileName, TreeMap<String, ItemList> items, TreeMap<ItemList, Integer> itemsCount) {
		this.orderFileName = orderFileName;
		this.items = items;
		this.itemsCount = itemsCount;
		this.summary = null;

		purchasesHolder = new TreeMap<String, Integer>();
	}
	
	public TreeMap<String, Integer> getPurchases() {
		return purchasesHolder;
	}
	
	@Override
	public void run() {
		processOrder();
	}
	public void processOrder() {
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader(new File(orderFileName)));
			clientId = Integer.parseInt(inputStream.readLine().split(" ")[1]);
			System.out.println("Reading order for client with id: " + clientId);

			try {
				while (true) {
					String[] split = inputStream.readLine().split(" ");

					// Adds purchase to client's account
					int purchasedCount = 0;
					try {
						purchasedCount = purchasesHolder.get(split[0]);
					} catch (Exception e) {
					} finally {
						purchasedCount++;
						purchasesHolder.put(split[0], purchasedCount);
					}

					purchasesHolder.put(split[0], purchasedCount);

					// Adds purchase to items total
					ItemList item = items.get(split[0]);

					synchronized (itemsCount) {
						int count = itemsCount.get(item);
						count++;
						itemsCount.put(item, count);
					}
				}
			} catch (Exception e) {
				
			}
			inputStream.close();
		} catch (Exception e) {
			System.out.println("Client file " + orderFileName + " not found");
		}
		buildSummary();
	}

	private void buildSummary() {
		StringBuffer sb = new StringBuffer();
		NumberFormat numFormat = NumberFormat.getCurrencyInstance();
		double orderTotal = 0;
		sb.append("----- Order details for client with Id: ");
		sb.append(clientId);
		sb.append(" -----\n");

		for (String item : purchasesHolder.keySet()) {
			double itemCost = items.get(item).price;
			int itemCount = purchasesHolder.get(item);
			orderTotal += itemCost * itemCount;

			sb.append("Item's name: ");
			sb.append(item);
			sb.append(", Cost per item: ");
			sb.append(numFormat.format(itemCost));
			sb.append(", Quantity: ");
			sb.append(itemCount);
			sb.append(", Cost: ");
			sb.append("" + numFormat.format(itemCost * itemCount));
			sb.append("\n");
		}

		sb.append("Order Total: ");
		sb.append(numFormat.format(orderTotal));
		sb.append("\n");

		summary = sb.toString();
	}

	public String getSummary() {
		return summary;
	}
}