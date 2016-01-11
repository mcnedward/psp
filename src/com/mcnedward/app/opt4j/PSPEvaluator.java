package com.mcnedward.app.opt4j;

import org.opt4j.core.Objective.Sign;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;

import com.mcnedward.app.PSPMain;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Oct 27, 2015
 *
 */
public class PSPEvaluator implements Evaluator<String> {

	@Override
	public Objectives evaluate(String phenotype) {
		Objectives objectives = new Objectives();

		PSPMain psp = new PSPMain();
		psp.evaluate();
		
		float fitness = psp.calculateFeasibleFitness();
		
		objectives.add("Solution", Sign.MIN, fitness);
		return objectives;
	}

}
