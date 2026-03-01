package com.quantity;

import java.util.Objects;

public class QuantityMeasurementApp {

    // ================= CONSTANT =================
    private static final double TOLERANCE = 1e-5;

    // ================= LENGTH UNIT ENUM =================
    public enum LengthUnit {

        FEET(1.0),

        INCH(1.0 / 12.0),

        YARD(3.0),

        // 1 cm = 0.393701 inch
        // inch to feet = 1/12
        // so 0.393701 / 12 = 0.0328084167 feet
        CENTIMETER(0.0328084167);

        private final double conversionFactorToFeet;

        LengthUnit(double conversionFactorToFeet) {
            this.conversionFactorToFeet = conversionFactorToFeet;
        }

        public double toFeet(double value) {
            return value * conversionFactorToFeet;
        }
    }

    // ================= GENERIC QUANTITY LENGTH =================
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        private double convertToBase() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

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

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARD);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength inch =
                new QuantityLength(36.0, LengthUnit.INCH);

        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETER);

        QuantityLength inchFromCm =
                new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println("Input: " + yard + " and " + feet);
        System.out.println("Output: Equal (" + yard.equals(feet) + ")");

        System.out.println();

        System.out.println("Input: " + yard + " and " + inch);
        System.out.println("Output: Equal (" + yard.equals(inch) + ")");

        System.out.println();

        System.out.println("Input: " + cm + " and " + inchFromCm);
        System.out.println("Output: Equal (" + cm.equals(inchFromCm) + ")");
    }
}