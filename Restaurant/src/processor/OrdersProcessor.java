package processor;
import java.util.*; 


public class OrdersProcessor {

	public static void main(String[] args) {
		String outputFileName = "";
		double st = 0.0; 
		
		System.out.println("Enter item's data file name: ");
		Scanner scan = new Scanner(System.in); 
		String inventoryName = scan.next();
		synchronized(inventoryName) {
		System.out.println("Enter 'y' for multiple threads, any other character otherwise: ");
		String input = scan.next(); 

		System.out.println("Enter the number of orders to process: ");
		int orders = scan.nextInt(); 

		System.out.println("Enter order's base file name: ");
		String customerFileName = scan.next(); 

		System.out.println("Enter result's filename: ");
		outputFileName = scan.next(); 

		st = System.currentTimeMillis();
		int type = input.equals("y") || input.equals("Y")? 2 : 1; 
		
			try {
				Processor process = new Processor(type, inventoryName, customerFileName
						, outputFileName, orders); 
				process.processingCode();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		double et = System.currentTimeMillis();
		System.out.println("Processing time (msec): " + (et - st));
		System.out.println("Results can be found in the file: " + outputFileName);
		
		scan.close();
		
	}
}
