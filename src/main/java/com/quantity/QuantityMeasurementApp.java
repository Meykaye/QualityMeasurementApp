package com.quantity;

public class QuantityMeasurementApp {

    // ================= FEET CLASS =================
    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    // ================= INCHES CLASS =================
    public static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Inches other = (Inches) obj;

            return Double.compare(this.value, other.value) == 0;
        }
    }

    // ================= SEPARATE METHODS =================
    public static boolean areFeetEqual(double v1, double v2) {
        return new Feet(v1).equals(new Feet(v2));
    }

    public static boolean areInchesEqual(double v1, double v2) {
        return new Inches(v1).equals(new Inches(v2));
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + areFeetEqual(1.0, 1.0) + ")");

        System.out.println();

        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + areInchesEqual(1.0, 1.0) + ")");
    }
}