package com.quantity.unit;

public interface IMeasurable {

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

    /*
     Default lambda capability
     All units support arithmetic by default
     */

    SupportsArithmetic supportsArithmetic = () -> true;

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    /*
     Default validation for operations
     */

    default void validateOperationSupport(String operation) {
        // Default: all operations allowed
    }
}