package com.joshkevingeoff.evolearn;

import java.util.Random;

/**
 * Created by Kevin on 4/3/15.
 */
public class Problem {
    private final static int SOLUTIONS = 4;

    private int numberA;
    private int numberB;
    private int solution;
    private int solutionIndex;
    private int[] possibleAnswers = new int[SOLUTIONS];
    private OperationEnum op = OperationEnum.PLUS;

    public int getNumberA() {
        return numberA;
    }

    public int getNumberB() {
        return numberB;
    }

    public int getSolution() {
        return solution;
    }

    public int[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public int getSolutionIndex(){return solutionIndex;}

    public OperationEnum getOp(){return op;}

    public Problem(int num1, int num2)
    {
        numberA = num1;
        numberB = num2;

        solution = numberA + numberB;
        loadPossibleAnswers();
    }

    private void loadPossibleAnswers()
    {
        int maxNumber = ((numberA + numberB) * 2) + 1;
        Random r = new Random();
        solutionIndex = r.nextInt(SOLUTIONS);

        for(int i=0; i<possibleAnswers.length; i++)
        {
            if(i == solutionIndex)
            {
                possibleAnswers[i] = solution;
            }
            else
            {
                possibleAnswers[i] = r.nextInt(maxNumber);
            }
        }

    }

}
