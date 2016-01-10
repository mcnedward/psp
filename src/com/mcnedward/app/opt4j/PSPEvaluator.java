package com.mcnedward.app.opt4j;

import java.io.File;

import org.opt4j.core.Objective.Sign;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;

import com.mcnedward.app.PSPMain;

/**
 * @author Edward McNealy <edwardmcn64@gmail.com> - Oct 27, 2015
 *
 */
public class PSPEvaluator implements Evaluator<String> {

	private static int number = 0;
	
	@Override
	public Objectives evaluate(String phenotype) {
		Objectives objectives = new Objectives();

		PSPMain psp = new PSPMain();
		String fileName = PSPMain.RESOURCE_DIRECTORY + PSPMain.OUTPUT_FILE_PREFIX + number + ".txt";
		System.out.println("FILE NAME: " + fileName);
		psp.parse(new File(fileName));
		number++;
		psp.evaluate();
		
		float fitness = psp.calculateFeasibleFitness();
		
		objectives.add("Cost", Sign.MIN, fitness);
		return objectives;
	}

}
