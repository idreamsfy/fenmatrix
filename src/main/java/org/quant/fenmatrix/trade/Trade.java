package org.quant.fenmatrix.trade;

import org.quant.fenmatrix.time.Date;

public class Trade {
  private String TradeID;

  private Date TradeDate;
  
  protected Trade() {
    
  }

  public String getTradeID() {
    return TradeID;
  }

  public void setTradeID(String tradeID) {
    TradeID = tradeID;
  }

  public Date getTradeDate() {
    return TradeDate;
  }

  public void setTradeDate(Date tradeDate) {
    TradeDate = tradeDate;
  }
}
