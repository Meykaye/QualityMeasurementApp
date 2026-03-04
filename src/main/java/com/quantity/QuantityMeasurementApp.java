package com.quantity;

import com.quantity.model.Quantity;
import com.quantity.unit.LengthUnit;
import com.quantity.unit.WeightUnit;
import com.quantity.unit.VolumeUnit;
import com.quantity.unit.IMeasurable;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("----- SUBTRACTION -----");

        Quantity<LengthUnit> lengthA =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> lengthB =
                new Quantity<>(6.0, LengthUnit.INCHES);

        demonstrateSubtraction(lengthA, lengthB, LengthUnit.FEET);
        demonstrateSubtraction(lengthA, lengthB, LengthUnit.INCHES);


        System.out.println("\n----- DIVISION -----");

        Quantity<LengthUnit> lengthC =
                new Quantity<>(24.0, LengthUnit.INCHES);

        Quantity<LengthUnit> lengthD =
                new Quantity<>(2.0, LengthUnit.FEET);

        demonstrateDivision(lengthC, lengthD);


        System.out.println("\n----- WEIGHT DIVISION -----");

        Quantity<WeightUnit> weight1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        demonstrateDivision(weight1, weight2);


        System.out.println("\n----- VOLUME SUBTRACTION -----");

        Quantity<VolumeUnit> v1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        demonstrateSubtraction(v1, v2, VolumeUnit.LITRE);
    }

    public static <U extends IMeasurable> void demonstrateSubtraction(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        Quantity<U> result = q1.subtract(q2, targetUnit);

        System.out.println(
                q1 + " - " + q2 + " in " + targetUnit + " -> " + result
        );
    }

    public static <U extends IMeasurable> void demonstrateDivision(
            Quantity<U> q1,
            Quantity<U> q2) {

        double result = q1.divide(q2);

        System.out.println(
                q1 + " / " + q2 + " -> " + result
        );
    }
}