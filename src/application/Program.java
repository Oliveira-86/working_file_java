package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {
	 
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		
		System.out.println("Enter a folder path: ");
		String strPath = sc.nextLine();
		
		File path = new File(strPath);
		
		boolean sucess = new File(strPath + "\\out").mkdir();
		
		String targetFile = strPath + "\\out\\summary.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			while (line != null) {
				
				String[] fields = line.split(",");
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);
				
				list.add(new Product(name, price, quantity));
				
				line = br.readLine();
				}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))){
				
				for(Product items : list) {
					bw.write(items.getName() + ", " + String.format("%.2f", items.total()));
					bw.newLine();
				}
				
					System.out.println(strPath + " CREATED!");
				
			} catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}

		sc.close();
	}
}