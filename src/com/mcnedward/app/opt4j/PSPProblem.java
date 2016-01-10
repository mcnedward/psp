package com.mcnedward.app.opt4j;

import org.opt4j.core.genotype.BooleanGenotype;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Oct 27, 2015
 *
 */
public class PSPProblem {

	private BooleanGenotype genotype;

	/**
	 * @param genotype
	 * @param dataFile
	 */
	public PSPProblem(BooleanGenotype genotype) {
		this.genotype = genotype;
	}

	/**
	 * @return the genotype
	 */
	public BooleanGenotype getGenotype() {
		return genotype;
	}

	/**
	 * @param genotype
	 *            the genotype to set
	 */
	public void setGenotype(BooleanGenotype genotype) {
		this.genotype = genotype;
	}

}
