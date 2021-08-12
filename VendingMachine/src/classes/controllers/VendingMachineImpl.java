package classes.controllers;

import classes.models.Currency;
import classes.models.CurrencyType;
import classes.models.Item;
import classes.views.Dashboard;
import exceptions.NotFullPaidException;
import exceptions.NotSufficientChangeException;
import exceptions.SoldOutException;
import interfaces.IVendingMachine;

public class VendingMachineImpl implements IVendingMachine {
		private BankStorage bank=new BankStorage();
		private VendingStorage storage=new VendingStorage();
		public BankStorage getBank() {
			return bank;
		}
		public VendingStorage getStorage() {
			return storage;
		}
		public Item selectItm(int selection) {
			int i=0;
			Item itm=new Item();
			for(var entry: storage.getStorageMap().entrySet()) {
				++i;
				if(i==selection) {
					itm = entry.getKey();
				}
			}
			return itm;
		}
		
		public void buyItm(Item itm) {
			try {
				storage.removeItm(itm);
			} catch (SoldOutException e) {
				System.out.println("Error: "+e);
			}
		}
		
		public double paymentProcedure(Dashboard dash, Item itm) throws NotFullPaidException {
			double paymentTotal=0;
			int selectedMethod=1;
			int selectedValue=1;
			
			while(selectedMethod!=0 && selectedValue!=0 && paymentTotal<=itm.getPrice()){
				dash.selectPaymentMethod();
			selectedMethod=dash.chooseInput(0, 2);

			if(selectedMethod==1) {
				dash.paymentValue(selectedMethod);
				selectedValue=dash.chooseInput(0, 3);
				switch(selectedValue) {
				case 0:{
					break;
				}
				case 1:{
					paymentTotal +=0.5;
					bank.addItm(new Currency(0.5,CurrencyType.COIN));
					break;
				}
				case 2:{
					paymentTotal +=0.1;
					bank.addItm(new Currency(0.1,CurrencyType.COIN));
					break;
				}
				case 3:{
					paymentTotal +=0.05;
					bank.addItm(new Currency(0.05,CurrencyType.COIN));
					break;
				}
				}
			}else if(selectedMethod==2) {
				dash.paymentValue(selectedMethod);
				selectedValue=dash.chooseInput(0, 3);
				switch(selectedValue) {
				case 0:{
					break;
				}
				case 1:{
					paymentTotal +=1;
					bank.addItm(new Currency(1,CurrencyType.BANKNOTE));
					break;
				}
				case 2:{
					paymentTotal +=5;
					bank.addItm(new Currency(5,CurrencyType.BANKNOTE));
					break;
				}
				case 3:{
					paymentTotal +=10;
					bank.addItm(new Currency(10,CurrencyType.BANKNOTE));
					break;
				}
				
				}
			}
			System.out.println();
			System.out.println("Total inserted: "+paymentTotal+ "; Item price: "+itm.getPrice());
			}
			if(paymentTotal<itm.getPrice()) {
				throw new NotFullPaidException("Not enough money inserted.");
			}
			return paymentTotal;
		}
		public double subtractChange(double paymentTotal,Item itm) throws NotSufficientChangeException {
			double change=0;
			paymentTotal-=itm.getPrice();
			
			if(paymentTotal>0) {
				for(var entry: bank.getBankStorage().entrySet()) {
					while(paymentTotal>=entry.getKey().getValue() && entry.getValue()>0) {
						paymentTotal-=entry.getKey().getValue();
						change+=entry.getKey().getValue();
						bank.removeItm(entry.getKey());
						//System.out.println("Platit: "+entry.getKey().getValue()+"; Rest: "+paymentTotal);
					}
				}
				
			}
			if(paymentTotal>0) {
				throw new NotSufficientChangeException("Not enough change in the machine!");
			}	
			return change;
		}
		public void refundItem(Item itm) throws NotSufficientChangeException {
			
			double price=itm.getPrice();
			
				for(var entry: bank.getBankStorage().entrySet()) {
					while(price>=entry.getKey().getValue() && entry.getValue()>0) {
						price-=entry.getKey().getValue();
						bank.removeItm(entry.getKey());
					}
				}
				
			if(price>0) {
				throw new NotSufficientChangeException("Not enough change in the machine!");
			}
			storage.addItm(itm);
		}
		
		
}
