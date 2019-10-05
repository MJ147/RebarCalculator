package com.mja.calculator;

import com.mja.materials.ConcreteSlab;
import com.mja.materials.Reinforcement;

public class ReinforcementCalculator {

    private ConcreteSlab concreteSlab;
    private Reinforcement reinforcement;
    private double bendingMoment;
    private double safetyFactor = 1;

    //slab useful height - d(mm)
    public double slabUsefulHeight() {
        int slabHeight = concreteSlab.getSlabHeight();
        int concreteCover = concreteSlab.getConcreteCover();
        int barDiameter = reinforcement.getBarDiameter();

        return ((double)slabHeight - concreteCover - barDiameter / 2) / 1000;
    }

    //section load capacity due to the compression zone - μeff(-)
    public double sectionLoadCapacity() {
        double concreteStrength = concreteSlab.getComputationalConcreteStrength();

        return bendingMoment/(1 * Math.pow(slabUsefulHeight(),2) * concreteStrength * 1000);
    }

    //effective height range of the compression zone - ξeff(-)
    public double effectiveHeightRange() {

        return 1 - Math.sqrt(1 - 2 * sectionLoadCapacity());
    }

    //section area of reinforcement in 1 meter of slab Areq(cm/m)
    public double requiredReinforcementArea() {
        double concreteStrength = 0;
        int steelYielPoint = 0;

        try {
            concreteStrength = concreteSlab.getComputationalConcreteStrength();
            steelYielPoint = reinforcement.getComputationalYieldPoint();
        } catch (NullPointerException e) {
            System.out.println("Reinforcement or concrete is missing");
        }

        return concreteStrength * 1 * effectiveHeightRange() * slabUsefulHeight() / steelYielPoint * 10000;
    }

    //convert section area of reinforcement to specific diameter and spacing of bars
    public String barsDiameterAndSpacing() throws NullPointerException{
        if (reinforcement.getComputationalYieldPoint() == 0) {
            return "0 WARNING: Computational yield point of steel = 0!";
        }
        if (concreteSlab.getSlabHeight() == 0 || concreteSlab.getConcreteCover() == 0 || concreteSlab.getComputationalConcreteStrength() == 0) {
            return "0 WARNING: Slab/Concrete informations missing!";
        }
        if (bendingMoment == 0) {
            return "0 WARNING: Bending moment = 0!";
        }
        int barDiameter = reinforcement.getBarDiameter();
        int barSpacing = 200;
        double requiredReinforcementArea = round(requiredReinforcementArea(), 2);

        while (round(Math.PI * Math.pow(barDiameter / 2, 2) * 1000 / (barSpacing * 100) * safetyFactor, 2) < requiredReinforcementArea) {
            if (barSpacing > 100) {
                barSpacing -= 10;
            } else {
                barSpacing = 200;
                barDiameter += 2;
            }
        }
        return "ϕ" + barDiameter + "s" + barSpacing;
    }

    //round value to any decimal place
    public double round(double value, int decimalPlace) {
        value *= Math.pow(10, decimalPlace);
        value = Math.round(value);
        value /= Math.pow(10, decimalPlace);

        return value;
    }

    public ConcreteSlab getConcreteSlab() {
        return concreteSlab;
    }

    public void setConcreteSlab(ConcreteSlab concreteSlab) {
        this.concreteSlab = concreteSlab;
    }

    public Reinforcement getReinforcement() {
        return reinforcement;
    }

    public void setReinforcement(Reinforcement reinforcement) {
        this.reinforcement = reinforcement;
    }

    public double getBendingMoment() {
        return bendingMoment;
    }

    public void setBendingMoment(double bendingMoment) {
        this.bendingMoment = bendingMoment;
    }

    public double getSafetyFactor() {
        return safetyFactor;
    }

    public void setSafetyFactor(double safetyFactor) {
        this.safetyFactor = safetyFactor;
    }
}
