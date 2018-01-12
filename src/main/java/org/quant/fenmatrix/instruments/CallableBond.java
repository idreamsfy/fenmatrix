package org.quant.fenmatrix.instruments;

public class CallableBond extends Bond{
  
  
  protected CallableBond() {
    
  }

  public static CallableBond valueOf() {
    
    CallableBond bond=new CallableBond();
    
    
    return bond;
    
  }
}
