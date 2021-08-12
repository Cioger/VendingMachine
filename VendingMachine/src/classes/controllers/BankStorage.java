package classes.controllers;

import java.util.Map;
import java.util.TreeMap;

import classes.models.Currency;

public class BankStorage {
		private Map<Currency,Integer> bankStorage=new TreeMap<>();
		
		public Map<Currency, Integer> getBankStorage() {
			return bankStorage;
		}

		

		public void printBank() {
			for(var entry: bankStorage.entrySet()) {
				System.out.println("Currency: " + entry.getKey()+
						"; Quantity: " + entry.getValue()+";");
			}
		}



		public void addItm(Currency it) {
			if(bankStorage.containsKey(it)) {
				bankStorage.put(it, bankStorage.get(it)+1);
			}
			else {
				bankStorage.put(it, 1);
			}
		}
		
		public void addMultipleItm(Currency it,Integer nr) {
			if(bankStorage.containsKey(it)) {
				bankStorage.put(it, bankStorage.get(it)+nr);
			}
			else {
				bankStorage.put(it, nr);
			}
		}
		
		public void removeItm(Currency it) {
			if(bankStorage.containsKey(it)) {
				bankStorage.put(it, bankStorage.get(it)-1);
				if(bankStorage.get(it)==0) {
					bankStorage.remove(it);
				}
			}
			else {
				System.out.println("Item not in storage.");
			}
		}
		
		public void removeMultipleItm(Currency it,Integer nr) {
			if(bankStorage.containsKey(it)) {
				if(bankStorage.get(it)>=nr) {
					bankStorage.put(it, bankStorage.get(it)-nr);
				}else {
					System.out.println("Not enough items in storage.");
				}
				
				if(bankStorage.get(it)==0) {
					bankStorage.remove(it);
				}
			}
			else {
				System.out.println("Item not in storage.");
			}
		}
		public Integer getNrOfItms(Currency it) {
			return bankStorage.get(it);
		}
		public void printItms() {
			for(Currency it:bankStorage.keySet()) {
				System.out.println(it);
			}
		}
}
