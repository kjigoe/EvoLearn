package com.joshkevingeoff.evolearn;

/**
 * Created by Kevin on 4/3/15.
 */
public class ProblemGenerator {

    private int minNumber;
    private int maxNumber;
    private OperationEnum operation;


    public static Problem computeProblem()
    {
        return new Problem(5, 7);
    }


}
