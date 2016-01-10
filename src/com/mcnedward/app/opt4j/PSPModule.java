package com.mcnedward.app.opt4j;

import org.opt4j.core.problem.ProblemModule;
import org.opt4j.core.start.Constant;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Oct 27, 2015
 *
 */
public class PSPModule extends ProblemModule {

	@Constant(value = "populationSize")
	protected int populationSize = 100;

	protected void config() {
		bindProblem(PSPCreator.class, PSPDecoder.class, PSPEvaluator.class);
	}

	/**
	 * @return the populationSize
	 */
	public int getPopulationSize() {
		return populationSize;
	}

	/**
	 * @param populationSize
	 *            the populationSize to set
	 */
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

}
