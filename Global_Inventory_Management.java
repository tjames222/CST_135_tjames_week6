
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.scene.image.Image;

public class Global_Inventory_Management extends Dispenser {

	File csvFile;
	Scanner scanner;
	String line;

	public Global_Inventory_Management() {

		try {
			csvFile = new File("ProductsArray.csv");
			scanner = new Scanner(csvFile);

			for (int i = 0; scanner.hasNext(); i++) {
				line = scanner.nextLine();
				String[] currentProduct = line.split(",");

				switch (currentProduct[0]) {
					case "Candy":
						this.productsForSale[i] = new Candy(currentProduct[1],
							  Double.parseDouble(currentProduct[2]),
							  Integer.parseInt(currentProduct[3]),
							  Integer.parseInt(currentProduct[4]),
							  new Image(currentProduct[5]));
						break;
					case "Chips":
						this.productsForSale[i] = new Chips(currentProduct[1],
							  Double.parseDouble(currentProduct[2]),
							  Integer.parseInt(currentProduct[3]),
							  Integer.parseInt(currentProduct[4]),
							  new Image(currentProduct[5]));
						break;
					case "Drink":
						this.productsForSale[i] = new Drink(currentProduct[1],
							  Double.parseDouble(currentProduct[2]),
							  Integer.parseInt(currentProduct[3]),
							  Integer.parseInt(currentProduct[4]),
							  new Image(currentProduct[5]));
						break;
					case "Gum":
						this.productsForSale[i] = new Gum(currentProduct[1],
							  currentProduct[2],
							  Double.parseDouble(currentProduct[3]),
							  Integer.parseInt(currentProduct[3]),
							  Integer.parseInt(currentProduct[4]),
							  new Image(currentProduct[5]));
						break;
				}
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File " + csvFile.getName() + " not found.");
		}
	}
	
	//Merge sort. Source code template from page 890 in the textbook
	public static void sortName(Product[] sortArray) {
		for (int count = 0; count < sortArray.length; count++) {
			if (sortArray.length > 1) {
				Product[] firstHalf = new Product[sortArray.length / 2];
				System.arraycopy(sortArray, 0, firstHalf, 0, sortArray.length / 2);
				sortName(firstHalf);

				int secondHalfLength = sortArray.length / 2;
				Product[] secondHalf = new Product[secondHalfLength];
				System.arraycopy(sortArray, sortArray.length / 2, secondHalf, 0, secondHalfLength);
				sortName(secondHalf);

				merge(firstHalf, secondHalf, sortArray);
			}
		}
	}

	public static void merge(Product[] array1, Product[] array2, Product[] temp) {
		int current1 = 0;
		int current2 = 0;
		int current3 = 0;

		while (current1 < array1.length && current2 < array2.length) {
			if (array1[current1].name.compareTo(array2[current2].name) < 0) {
				temp[current3++] = array1[current1++];
			} else {
				temp[current3++] = array2[current2++];
			}
		}

		while (current1 < array1.length) {
			temp[current3++] = array1[current1++];
		}

		while (current2 < array2.length) {
			temp[current3++] = array2[current2++];
		}
	}

	//Search any product array for an item by name. Source code adapted from text book
	public static int nameSearch(Product[] searchArray, String key) {
		int low = 0;
		int high = searchArray.length - 1;
		return nameSearch(searchArray, key, low, high);
	}

	public static int nameSearch(Product[] searchArray, String key, int low, int high) {

		if (low > high) {
			return -low - 1;
		}

		int mid = (low + high) / 2;

		if (key.compareTo(searchArray[mid].name) < 0) {
			return nameSearch(searchArray, key, low, mid - 1);

		} else if (key.equalsIgnoreCase(searchArray[mid].name)) {
			writeToCallStackFile();
			return mid;
		} else {
			return nameSearch(searchArray, key, mid + 1, high);
		}
	}

	//Write the call stack to a file.
	public static void writeToCallStackFile() {
		try {
			File file = new File("callStack.txt");
			PrintWriter writer = new PrintWriter(file);
			if (file.exists()) {
				writer.print("");
			}
			for (StackTraceElement line : Thread.currentThread().getStackTrace()) {
				writer.println(line.toString());
			}
			writer.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Could not create or find file");
		}
	}
}
