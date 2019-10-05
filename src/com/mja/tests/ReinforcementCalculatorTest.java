package com.mja.tests;

import com.mja.calculator.ReinforcementCalculator;
import com.mja.materials.ConcreteSlab;
import com.mja.materials.Reinforcement;

public class ReinforcementCalculatorTest {

    public static void main(String[] args) {

        ConcreteSlab concreteSlabTest = new ConcreteSlab();
        concreteSlabTest.setSlabHeight(350);
        concreteSlabTest.setConcreteCover(45);
        concreteSlabTest.setComputationalConcreteStrength(21.43);

        Reinforcement reinforcementTest = new Reinforcement();
        reinforcementTest.setBarDiameter(18);
        reinforcementTest.setComputationalYieldPoint(420);

        ReinforcementCalculator reinforcementCalculatorTest = new ReinforcementCalculator();
        reinforcementCalculatorTest.setConcreteSlab(concreteSlabTest);
        reinforcementCalculatorTest.setReinforcement(reinforcementTest);
        reinforcementCalculatorTest.setBendingMoment(75.74);

        System.out.print("round test: ");
        if (reinforcementCalculatorTest.round(0.1111, 3) == 0.111) {
            System.out.println("POSITIVE");
        } else {
            System.out.println("NEGATIVE");
        }

        System.out.print("Slab useful height test: ");
        if (reinforcementCalculatorTest.slabUsefulHeight() == 0.296) {
            System.out.println("POSITIVE");
        } else {
            System.out.println("NEGATIVE");
        }

        System.out.print("Section load capacity test: ");
        if (reinforcementCalculatorTest.round(reinforcementCalculatorTest.sectionLoadCapacity(), 4) == 0.0403) {
            System.out.println("POSITIVE");
        } else {
            System.out.println("NEGATIVE");
        }

        System.out.print("Effective height range test: ");
        if (reinforcementCalculatorTest.round(reinforcementCalculatorTest.effectiveHeightRange(), 4) == 0.0412) {
            System.out.println("POSITIVE");
        } else {
            System.out.println("NEGATIVE");
        }

        System.out.print("Required reinforcement area test: ");
        if (reinforcementCalculatorTest.round(reinforcementCalculatorTest.requiredReinforcementArea(), 2) == 6.22) {
            System.out.println("POSITIVE");
        } else {
            System.out.println("NEGATIVE");
        }

        System.out.print("Bars diameter and spacing testt: ");
        if ("ϕ18s200".equals(reinforcementCalculatorTest.barsDiameterAndSpacing())) {
            System.out.println("POSITIVE");
        } else {
            System.out.println("NEGATIVE");
        }
    }
}
