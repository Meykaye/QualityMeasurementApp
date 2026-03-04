package com.quantity;

import com.quantity.model.Quantity;
import com.quantity.unit.LengthUnit;
import com.quantity.unit.WeightUnit;
import com.quantity.unit.VolumeUnit;
import com.quantity.unit.TemperatureUnit;
import com.quantity.unit.IMeasurable;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("===== LENGTH OPERATIONS =====");

        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        demonstrateEquality(length1, length2);
        demonstrateConversion(length1, LengthUnit.INCHES);
        demonstrateAddition(length1, length2, LengthUnit.FEET);


        System.out.println("\n===== WEIGHT OPERATIONS =====");

        Quantity<WeightUnit> weight1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        demonstrateEquality(weight1, weight2);
        demonstrateConversion(weight1, WeightUnit.GRAM);
        demonstrateAddition(weight1, weight2, WeightUnit.KILOGRAM);


        System.out.println("\n===== VOLUME OPERATIONS =====");

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        demonstrateEquality(volume1, volume2);
        demonstrateConversion(volume1, VolumeUnit.MILLILITRE);
        demonstrateAddition(volume1, volume2, VolumeUnit.LITRE);


        System.out.println("\n===== TEMPERATURE OPERATIONS =====");

        Quantity<TemperatureUnit> temp1 =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> temp2 =
                new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        demonstrateEquality(temp1, temp2);

        demonstrateConversion(temp1, TemperatureUnit.FAHRENHEIT);

        demonstrateUnsupportedArithmetic(temp1, temp2);
    }


    /*
    --------------------------------
    GENERIC DEMONSTRATION METHODS
    --------------------------------
     */

    public static <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> q1,
            Quantity<U> q2) {

        System.out.println(
                q1 + " == " + q2 + " -> " + q1.equals(q2)
        );
    }


    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit) {

        Quantity<U> converted = quantity.convertTo(targetUnit);

        System.out.println(
                quantity + " converted to " + targetUnit + " -> " + converted
        );
    }


    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        Quantity<U> result = q1.add(q2, targetUnit);

        System.out.println(
                q1 + " + " + q2 + " in " + targetUnit + " -> " + result
        );
    }


    /*
    --------------------------------
    TEMPERATURE ERROR DEMO
    --------------------------------
     */

    public static void demonstrateUnsupportedArithmetic(
            Quantity<TemperatureUnit> t1,
            Quantity<TemperatureUnit> t2) {

        try {

            t1.add(t2);

        } catch (UnsupportedOperationException e) {

            System.out.println(
                    "Temperature arithmetic not supported: "
                            + e.getMessage()
            );
        }
    }
}