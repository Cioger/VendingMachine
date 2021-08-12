package interfaces;

import classes.models.Currency;
import classes.models.CurrencyType;
import classes.models.Item;
import classes.views.Dashboard;
import exceptions.NotFullPaidException;
import exceptions.NotSufficientChangeException;
import exceptions.SoldOutException;

public interface IVendingMachine {
	public Item selectItm(int selection);
	
	public void buyItm(Item itm);
	
	public double paymentProcedure(Dashboard dash, Item itm) throws NotFullPaidException;
	
	public double subtractChange(double paymentTotal,Item itm) throws NotSufficientChangeException;
	
	public void refundItem(Item itm) throws NotSufficientChangeException;
}
