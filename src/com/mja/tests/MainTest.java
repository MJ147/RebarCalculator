package com.mja.tests;

import com.mja.calculator.ReinforcementCalculator;
import com.mja.materials.ConcreteSlab;
import com.mja.materials.Reinforcement;

public class MainTest {

    public static void main(String[] args) {

        double bendingMoment = 65;

        ConcreteSlab concreteSlabTest = new ConcreteSlab();
        concreteSlabTest.setConcreteCover(45);
        concreteSlabTest.setComputationalConcreteStrength(21.43);

        Reinforcement reinforcementTest = new Reinforcement();
        reinforcementTest.setComputationalYieldPoint(420);

        ReinforcementCalculator reinforcementCalculatorTest = new ReinforcementCalculator();
        reinforcementCalculatorTest.setReinforcement(reinforcementTest);
        reinforcementCalculatorTest.setConcreteSlab(concreteSlabTest);

        String result = reinforcementCalculatorTest.barsDiameterAndSpacing();
        System.out.println("Bars diameter and spacing for bending moment " + bendingMoment + " kN/m: " + result );

        concreteSlabTest.setSlabHeight(350);
        reinforcementCalculatorTest.setConcreteSlab(concreteSlabTest);
        result = reinforcementCalculatorTest.barsDiameterAndSpacing();
        System.out.println("Bars diameter and spacing for bending moment " + bendingMoment + " kN/m: " + result );

        reinforcementCalculatorTest.setBendingMoment(bendingMoment);
        result = reinforcementCalculatorTest.barsDiameterAndSpacing();
        System.out.println("Bars diameter and spacing for bending moment " + bendingMoment + " kN/m: " + result );


}
}

