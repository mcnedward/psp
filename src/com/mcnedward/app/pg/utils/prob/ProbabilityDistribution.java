package com.mcnedward.app.pg.utils.prob;

import java.util.Properties;
import java.util.Random;

import com.mcnedward.app.pg.utils.Factory;

public abstract class ProbabilityDistribution
{
  protected Random r = new Random();
  @SuppressWarnings("rawtypes")
private static final Class[] CONSTRUCTOR_PARAM = { Properties.class };
  private static Factory fact = 
		  new Factory(ProbabilityDistribution.class, CONSTRUCTOR_PARAM, "com/mcnedward/app/pg/utils/prob/probdist");
  public static final String PROB_DIST_REGISTRY = "com/mcnedward/app/pg/utils/prob/probdist";
  public static final String NAME = "name";
  
  public double nextValue()
  {
    return inverseDistributionFunction(this.r.nextDouble());
  }
  
  public static ProbabilityDistribution createProbabilityDistribution(Properties paramProperties)
  {
    Object[] arrayOfObject = { paramProperties };
    
    return (ProbabilityDistribution)fact.createObject(paramProperties.getProperty("name"), arrayOfObject);
  }
  
  protected abstract double inverseDistributionFunction(double paramDouble);
}
