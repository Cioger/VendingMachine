package classes.models;


public class Currency implements Comparable<Currency> {
	private double value;
	private CurrencyType type;
	
	public Currency(double value, CurrencyType type) {
		this.value = value;
		this.type = type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public CurrencyType getType() {
		return type;
	}

	public void setType(CurrencyType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Currency [value=");
		builder.append(value);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Currency o) {
		if(this.value>o.value) {
			return -1;
		}else if(this.value<o.value) {
			return 1;
		}else {
			return 0;	
		}
		
	}
}
	
	
