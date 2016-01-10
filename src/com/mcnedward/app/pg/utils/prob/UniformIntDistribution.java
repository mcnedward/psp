package com.mcnedward.app.pg.utils.prob;

import java.util.Properties;

public final class UniformIntDistribution
  extends ProbabilityDistribution
{
  public static final String MINVALUE_PARAM = "minvalue";
  public static final String MAXVALUE_PARAM = "maxvalue";
  private int a;
  private int b;
  
  protected UniformIntDistribution() {}
  
  public UniformIntDistribution(Properties paramProperties)
    throws Exception
  {
    setParameters(paramProperties);
  }
  
  protected double inverseDistributionFunction(double paramDouble)
  {
    long l = Math.round(paramDouble * (this.b - this.a + 1) + this.a - 0.5D);
    if (l < this.a) {
      return this.a;
    }
    if (l > this.b) {
      return this.b;
    }
    return l;
  }
  
  public void setParameters(Properties paramProperties)
    throws Exception
  {
    this.a = Integer.parseInt(paramProperties.getProperty("minvalue"));
    this.b = Integer.parseInt(paramProperties.getProperty("maxvalue"));
    if (this.a > this.b) {
      throw new Exception("a must be less than b");
    }
  }
  
  public String toString()
  {
    return "Uniform Int Distribution (min=" + this.a + ",max=" + this.b + ")";
  }
}
