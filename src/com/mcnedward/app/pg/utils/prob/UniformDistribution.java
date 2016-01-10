package com.mcnedward.app.pg.utils.prob;

import java.util.Properties;

public final class UniformDistribution
  extends ProbabilityDistribution
{
  public static final String MINVALUE_PARAM = "minvalue";
  public static final String MAXVALUE_PARAM = "maxvalue";
  private double a;
  private double b;
  
  protected UniformDistribution() {}
  
  public UniformDistribution(Properties paramProperties)
    throws Exception
  {
    setParameters(paramProperties);
  }
  
  protected double inverseDistributionFunction(double paramDouble)
  {
    return paramDouble * (this.b - this.a) + this.a;
  }
  
  public void setParameters(Properties paramProperties)
    throws Exception
  {
    this.a = Double.parseDouble(paramProperties.getProperty("minvalue"));
    this.b = Double.parseDouble(paramProperties.getProperty("maxvalue"));
    if (this.a >= this.b) {
      throw new Exception("a must be less than b");
    }
  }
  
  public String toString()
  {
    return "Uniform Distribution (min=" + this.a + ",max=" + this.b + ")";
  }
}
