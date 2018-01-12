package org.quant.fenmatrix.analytic;

import org.quant.fenmatrix.asset.Asset;

public class AssetMeasures {

  private double Duration;

  private double ModifyDur;

  private double convexity;

  private AssetMeasures() {
  };

  public static AssetMeasures valueOf(Asset asset) {
    AssetMeasures assetmeasure = new AssetMeasures();

    return assetmeasure;
  }

  public double getDuration() {
    return Duration;
  }

  public void setDuration(double duration) {
    Duration = duration;
  }

  public double getModifyDur() {
    return ModifyDur;
  }

  public void setModifyDur(double modifyDur) {
    ModifyDur = modifyDur;
  }

  public double getConvexity() {
    return convexity;
  }

  public void setConvexity(double convexity) {
    this.convexity = convexity;
  }

}
