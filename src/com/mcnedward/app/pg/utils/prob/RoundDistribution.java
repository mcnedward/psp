package com.mcnedward.app.pg.utils.prob;

import java.util.Properties;

import com.mcnedward.app.pg.utils.PropUtils;

public final class RoundDistribution
  extends ProbabilityDistribution
{
  public static final String DIST_PARAM = "distribution";
  public static final String DIST_PREFIX_PARAM = "distribution.parameter.";
  private ProbabilityDistribution prob;
  
  protected RoundDistribution() {}
  
  public RoundDistribution(Properties paramProperties)
    throws Exception
  {
    setParameters(paramProperties);
  }
  
  protected double inverseDistributionFunction(double paramDouble)
  {
    double d = this.prob.inverseDistributionFunction(paramDouble);
    
    return Math.round(d);
  }
  
  public void setParameters(Properties paramProperties)
    throws Exception
  {
    Properties localProperties = PropUtils.getPropertiesWithPrefix(paramProperties, "distribution.parameter.");
    localProperties.put("name", paramProperties.getProperty("distribution"));
    
    this.prob = ProbabilityDistribution.createProbabilityDistribution(localProperties);
    if (this.prob == null) {
      throw new Exception("Distribution " + paramProperties.getProperty("distribution") + " not found");
    }
  }
  
  public String toString()
  {
    return "Round Distribution (distribution=" + this.prob.toString() + ")";
  }
}
