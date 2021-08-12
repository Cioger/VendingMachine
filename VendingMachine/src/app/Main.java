package app;



import classes.controllers.VendingMachineImpl;
import classes.models.Currency;
import classes.models.CurrencyType;
import classes.models.Item;
import classes.views.Dashboard;
import exceptions.NotFullPaidException;
import exceptions.NotSufficientChangeException;


public class Main {

	public static void main(String[] args) {
		Dashboard dash= new Dashboard();
		VendingMachineImpl machine=new VendingMachineImpl();
		machine.getStorage().addMultipleItm(new Item("Snickers",2.50), 20);
		machine.getStorage().addMultipleItm(new Item("Haribo",7.50), 1);
		machine.getStorage().addMultipleItm(new Item("Coca-Cola",3.50), 25);
		machine.getStorage().addMultipleItm(new Item("Water",1.50), 30);
		machine.getStorage().addMultipleItm(new Item("Sandwich",6.50), 5);
		
		machine.getBank().addMultipleItm(new Currency(0.05, CurrencyType.COIN ), 50);
		machine.getBank().addMultipleItm(new Currency(0.10, CurrencyType.COIN ), 50);
		machine.getBank().addMultipleItm(new Currency(0.50, CurrencyType.COIN ), 50);
		machine.getBank().addMultipleItm(new Currency(1, CurrencyType.BANKNOTE ), 50);
		machine.getBank().addMultipleItm(new Currency(5, CurrencyType.BANKNOTE ), 50);
		machine.getBank().addMultipleItm(new Currency(10, CurrencyType.BANKNOTE ), 50);
		
		Item lastItem=new Item();
		int input;
		do {
			
			dash.mainMenu();
			input=dash.chooseInput(1, 3);
			dash.clear();
			if(input==1) {
				
				dash.selectItem(machine.getStorage());
				int selectedItm=dash.chooseInput(0, dash.getNrOfItems(machine.getStorage()));
				dash.clear();
				dash.returnSelectedItem(machine.selectItm(selectedItm));
				double payment=0;
				try {
					payment = machine.paymentProcedure(dash,machine.selectItm(selectedItm));
					double change=0;
					try {
						change=machine.subtractChange(payment,machine.selectItm(selectedItm));
						System.out.println("Change: "+change);
						machine.buyItm(machine.selectItm(selectedItm));
						lastItem=machine.selectItm(selectedItm);
						dash.exitProcedure();
						int choice=dash.chooseInput(1, 2);
						dash.clear();
						if(choice==1) {
							input=4;
						}else input=3;
					} catch (NotSufficientChangeException e) {
						System.out.println("Error: "+e);
					}
					
				} catch (NotFullPaidException e1) {
					System.out.println("Error: "+e1);
				}
				
			}
			else if(input==2) {
				dash.refundProcedure(lastItem);
				int refundChoice = dash.chooseInput(1, 2);
				if(refundChoice==1) {
					try {
						machine.refundItem(lastItem);
						System.out.println("Item Refunded!");
					} catch (NotSufficientChangeException e) {
						System.out.println("Error: "+e);
					}
					
				}else if(refundChoice==2) {
					System.out.println("Refund Aborted!");
				}
				dash.exitProcedure();
				int choice=dash.chooseInput(1, 2);
				dash.clear();
				if(choice==1) {
					input=4;
				}else input=3;
			}
		}while(input!=3);
		System.out.println("Thank you for shopping!");
	}

}
