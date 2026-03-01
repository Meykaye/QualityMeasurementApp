package com.quantity;

import java.util.Objects;

/**
 * QuantityMeasurementApp
 *
 * UC1–UC8 Integrated
 * - Equality
 * - Conversion
 * - Addition (implicit target)
 * - Addition (explicit target)
 *
 * LengthUnit is now standalone (UC8).
 */
public class QuantityMeasurementApp {

    private static final double TOLERANCE = 1e-6;

    /**
     * Immutable Value Object for Length.
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

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private double convertToBase() {
            return unit.convertToBaseUnit(value);
        }

        /**
         * Convert this length to another unit.
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double baseValue = convertToBase();
            double converted = targetUnit.convertFromBaseUnit(baseValue);

            return new QuantityLength(converted, targetUnit);
        }

        /**
         * UC6 – Add using first operand unit as target.
         */
        public QuantityLength add(QuantityLength other) {
            return add(other, this.unit);
        }

        /**
         * UC7 – Add with explicit target unit.
         */
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumInBase =
                    this.convertToBase() + other.convertToBase();

            double resultValue =
                    targetUnit.convertFromBaseUnit(sumInBase);

            return new QuantityLength(resultValue, targetUnit);
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
     * Static conversion utility (UC5).
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

        double baseValue = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(baseValue);
    }
}