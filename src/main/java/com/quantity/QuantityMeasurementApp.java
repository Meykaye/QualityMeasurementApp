package com.quantity;

import java.util.Objects;

/**
 * QuantityMeasurementApp
 *
 * Provides equality comparison and explicit unit-to-unit conversion
 * for length measurements using a centralized base-unit normalization strategy.
 */
public class QuantityMeasurementApp {

    private static final double TOLERANCE = 1e-6;

    /**
     * LengthUnit enum.
     * Each unit stores its conversion factor relative to FEET (base unit).
     */
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084167); // 1 cm = 0.0328084167 feet

        private final double conversionFactorToFeet;

        LengthUnit(double conversionFactorToFeet) {
            this.conversionFactorToFeet = conversionFactorToFeet;
        }

        public double toFeet(double value) {
            return value * conversionFactorToFeet;
        }

        public double fromFeet(double feetValue) {
            return feetValue / conversionFactorToFeet;
        }
    }

    /**
     * Immutable value object representing a length.
     */
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        private double convertToBase() {
            return unit.toFeet(value);
        }

        /**
         * Converts this QuantityLength to another unit.
         * Returns a new immutable instance.
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double baseValue = convertToBase();
            double converted = targetUnit.fromFeet(baseValue);

            return new QuantityLength(converted, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Math.abs(
                    this.convertToBase() - other.convertToBase()
            ) < TOLERANCE;
        }

        @Override
        public int hashCode() {
            return Objects.hash(Math.round(convertToBase() / TOLERANCE));
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    /**
     * Static API method for direct conversion.
     */
    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double baseValue = source.toFeet(value);
        return target.fromFeet(baseValue);
    }

    /**
     * Overloaded demonstration method (raw values).
     */
    public static void demonstrateLengthConversion(
            double value,
            LengthUnit from,
            LengthUnit to) {

        double result = convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") → " + result);
    }

    /**
     * Overloaded demonstration method (object-based).
     */
    public static void demonstrateLengthConversion(
            QuantityLength length,
            LengthUnit to) {

        QuantityLength converted = length.convertTo(to);
        System.out.println(length + " converted to " + to + " → " + converted);
    }

    public static void demonstrateLengthEquality(
            QuantityLength a,
            QuantityLength b) {

        System.out.println(a + " equals " + b + " → " + a.equals(b));
    }

    public static void main(String[] args) {

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);
        demonstrateLengthConversion(3.0, LengthUnit.YARD, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARD);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);

        QuantityLength lengthInYards =
                new QuantityLength(2.0, LengthUnit.YARD);

        demonstrateLengthConversion(lengthInYards, LengthUnit.INCH);

        QuantityLength a =
                new QuantityLength(1.0, LengthUnit.YARD);

        QuantityLength b =
                new QuantityLength(3.0, LengthUnit.FEET);

        demonstrateLengthEquality(a, b);
    }
}