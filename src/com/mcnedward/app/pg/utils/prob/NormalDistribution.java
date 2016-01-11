package com.mcnedward.app.pg.utils.prob;

import java.util.Properties;

public final class NormalDistribution
  extends ProbabilityDistribution
{
  public static final String MU_PARAM = "mu";
  public static final String SIGMA_PARAM = "sigma";
  private double mu;
  private double sigma;
  
  protected NormalDistribution() {}
  
  public NormalDistribution(Properties paramProperties)
    throws Exception
  {
    setParameters(paramProperties);
  }
  
  protected double inverseDistributionFunction(double paramDouble)
  {
    double d1 = Math.sqrt(-2.0D * Math.log(paramDouble));
    double d2 = 6.283185307179586D * this.r.nextDouble();
    
    double d3 = d1 * Math.cos(d2);
    
    return this.sigma * d3 + this.mu;
  }
  
  public void setParameters(Properties paramProperties)
    throws Exception
  {
    this.mu = Double.parseDouble(paramProperties.getProperty("mu"));
    this.sigma = Double.parseDouble(paramProperties.getProperty("sigma"));
    if (this.sigma < 0.0D) {
      throw new Exception("sigma must be positive");
    }
  }
  
  public String toString()
  {
    return "Normal Distribution (mu=" + this.mu + ",sigma=" + this.sigma + ")";
  }
}
