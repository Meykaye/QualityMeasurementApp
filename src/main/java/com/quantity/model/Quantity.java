package com.quantity.model;

import com.quantity.unit.IMeasurable;

import java.util.Objects;

public final class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    private void validate(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");
    }

    // ---------------- ADD ----------------

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validate(other);

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseSum = this.toBaseUnit() + other.toBaseUnit();

        double result = targetUnit.convertFromBaseUnit(baseSum);

        return new Quantity<>(result, targetUnit);
    }

    // ---------------- SUBTRACT ----------------

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validate(other);

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseResult = this.toBaseUnit() - other.toBaseUnit();

        double result = targetUnit.convertFromBaseUnit(baseResult);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    // ---------------- DIVIDE ----------------

    public double divide(Quantity<U> other) {

        validate(other);

        double divisor = other.toBaseUnit();

        if (divisor == 0)
            throw new ArithmeticException("Division by zero");

        return this.toBaseUnit() / divisor;
    }

    // ---------------- CONVERT ----------------

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = toBaseUnit();

        double converted = targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(converted, targetUnit);
    }

    // ---------------- EQUALS ----------------

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity<?> other))
            return false;

        if (!this.unit.getClass().equals(other.unit.getClass()))
            return false;

        double thisBase = this.toBaseUnit();
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBaseUnit() / EPSILON));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}