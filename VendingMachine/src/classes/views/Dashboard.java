package classes.views;

import java.util.Scanner;

import classes.controllers.VendingStorage;
import classes.models.Item;

public class Dashboard {
	public void mainMenu() {
		System.out.println("Bine ati venit, rau ati nimerit!");
		System.out.println("1. Select Product.");
		System.out.println("2. Get Refund.");
		System.out.println("3. Exit.");
		System.out.println();
		
	}
	public void selectItem(VendingStorage strg) {
		System.out.println("Products in stock:");
		int i=0;
		for(var entry: strg.getStorageMap().entrySet()) {
			System.out.println(++i + ". Product: " + entry.getKey().getName()+
					"; Quantity: " + entry.getValue()+";");
		}
		System.out.println("0. Exit.");
		
	}
	public void returnSelectedItem(Item itm) {
		System.out.println("You selected:");
		System.out.println(itm);
	}
	public void selectPaymentMethod() {
		System.out.println("Payment method: ");
		System.out.println("1. Coin");
		System.out.println("2. Bill");
		System.out.println("0. Exit");
	}
	public void paymentValue(int selection) {
		System.out.println("Enter value of inserted currency:");
		if(selection==1) {
			System.out.println("1. 0.50");
			System.out.println("2. 0.10");
			System.out.println("3. 0.05");
			System.out.println("0. Exit");
		}
		else {
			System.out.println("1. 1");
			System.out.println("2. 5");
			System.out.println("3. 10");
			System.out.println("0. Exit");
		}
	}
	public void clear() {
		for(int i=0;i<10;i++) {
			System.out.println();
		}
	}
	public int chooseInput(int start, int finish) {
		int input;
		boolean wrongInput=false;
		do {
			if(wrongInput) {
				System.out.println("Wrong input.\nTry again!");
			}
			System.out.println("Choose option:");
			Scanner scanner=new Scanner(System.in);
			input=scanner.nextInt();
			wrongInput=true;
		}while(input<start || input>finish);
		return input;
	}
	public int getNrOfItems(VendingStorage strg) {
		int i=0;
		for(var entry: strg.getStorageMap().entrySet()) {
			++i;
		}
		return i;
	}
	public void exitProcedure() {
		System.out.println("Thank you for your purchase! Do you wish to continue?");
		System.out.println("1. Yes");
		System.out.println("2. No");
	}
	public void refundProcedure(Item itm) {
		System.out.println("Last purchased item:");
		System.out.println(itm);
		System.out.println("Refund?");
		System.out.println("1. Yes");
		System.out.println("2. No");
	}
	
	
}
