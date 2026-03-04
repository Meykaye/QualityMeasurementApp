package com.quantity.model;

import com.quantity.unit.IMeasurable;
import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public final class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

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

    /*
     ----------------------------------
     Arithmetic Operation Enum (UC13)
     ----------------------------------
     */
    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {
            if (b == 0)
                throw new ArithmeticException("Division by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    /*
     ----------------------------------
     Centralized Validation (UC13)
     ----------------------------------
     */
    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired) {

        if (other == null)
            throw new IllegalArgumentException("Quantity operand cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Incompatible measurement categories");

        if (!Double.isFinite(other.value))
            throw new IllegalArgumentException("Operand value must be finite");

        if (targetUnitRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    /*
     ----------------------------------
     Centralized Arithmetic Logic
     ----------------------------------
     */
    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        /* UC14 Validation for unsupported operations */
        this.unit.validateOperationSupport(operation.name());
        other.unit.validateOperationSupport(operation.name());

        double baseValue1 = this.toBaseUnit();
        double baseValue2 = other.toBaseUnit();

        return operation.compute(baseValue1, baseValue2);
    }

    /*
     ----------------------------------
     ADD
     ----------------------------------
     */

    public Quantity<U> add(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(result), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(result), targetUnit);
    }

    /*
     ----------------------------------
     SUBTRACT
     ----------------------------------
     */

    public Quantity<U> subtract(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = unit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(result), unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        return new Quantity<>(roundToTwoDecimals(result), targetUnit);
    }

    /*
     ----------------------------------
     DIVIDE
     ----------------------------------
     */

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    /*
     ----------------------------------
     CONVERSION
     ----------------------------------
     */

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = this.toBaseUnit();

        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(roundToTwoDecimals(convertedValue), targetUnit);
    }

    /*
     ----------------------------------
     Rounding Helper
     ----------------------------------
     */

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    /*
     ----------------------------------
     Equality
     ----------------------------------
     */

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