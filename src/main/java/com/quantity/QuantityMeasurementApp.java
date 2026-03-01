package com.quantity;

import java.util.Objects;

/**
 * QuantityMeasurementApp
 *
 * Supports:
 * - Equality comparison (UC3/UC4)
 * - Unit conversion (UC5)
 * - Addition of two lengths (UC6)
 *
 * Base unit: FEET
 */
public class QuantityMeasurementApp {

    private static final double TOLERANCE = 1e-6;

    /**
     * LengthUnit enum.
     * Conversion factors are defined relative to FEET.
     */
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084167); // 1 cm in feet

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

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        /**
         * Convert this length to another unit.
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double baseValue = convertToBase();
            double converted = targetUnit.fromFeet(baseValue);

            return new QuantityLength(converted, targetUnit);
        }

        /**
         * Add another length to this length.
         * Result is returned in the unit of the first operand.
         */
        public QuantityLength add(QuantityLength other) {

            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }

            double sumInBase =
                    this.convertToBase() + other.convertToBase();

            double resultValue =
                    this.unit.fromFeet(sumInBase);

            return new QuantityLength(resultValue, this.unit);
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
     * Static conversion API.
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
     * Static addition API (optional overload).
     */
    public static QuantityLength add(
            QuantityLength a,
            QuantityLength b) {

        if (a == null || b == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }

        return a.add(b);
    }

    /**
     * Demonstration method for conversion.
     */
    public static void demonstrateLengthConversion(
            double value,
            LengthUnit from,
            LengthUnit to) {

        double result = convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") → " + result);
    }

    /**
     * Demonstration method for addition.
     */
    public static void demonstrateAddition(
            QuantityLength a,
            QuantityLength b) {

        QuantityLength result = a.add(b);
        System.out.println("add(" + a + ", " + b + ") → " + result);
    }

    public static void main(String[] args) {

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);
        demonstrateLengthConversion(3.0, LengthUnit.YARD, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARD);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);

        System.out.println();

        demonstrateAddition(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH));

        demonstrateAddition(
                new QuantityLength(12.0, LengthUnit.INCH),
                new QuantityLength(1.0, LengthUnit.FEET));

        demonstrateAddition(
                new QuantityLength(1.0, LengthUnit.YARD),
                new QuantityLength(3.0, LengthUnit.FEET));

        demonstrateAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET));
    }
}