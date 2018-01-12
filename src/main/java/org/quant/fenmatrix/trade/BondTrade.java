package org.quant.fenmatrix.trade;

import org.quant.fenmatrix.instruments.Bond;

public class BondTrade  {


  
  private Bond bond;
  
  private BondTrade() {
    
  }

 

  public Bond getBond() {
    return bond;
  }

  public void setBond(Bond bond) {
    this.bond = bond;
  }
  
}
