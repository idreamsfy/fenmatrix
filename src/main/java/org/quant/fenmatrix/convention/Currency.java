package org.quant.fenmatrix.convention;
/**
 *  Basic definition for currency
 *  
 * @author YunHai Zhou
 */


public class Currency implements Cloneable {

	private String Name;
	
	private int Rounding;
	
	@Override
	protected Currency clone() {
		final Currency currency = new Currency();

		return currency;

	}

	public int getRounding() {
		return Rounding;
	}

	public void setRounding(int rounding) {
		Rounding = rounding;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
