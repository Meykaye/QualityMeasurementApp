package com.quantity;

/**
 * Standalone LengthUnit enum.
 * Responsible ONLY for unit conversion logic.
 * Base unit: FEET
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

    public double getConversionFactor() {
        return conversionFactorToFeet;
    }

    /**
     * Convert value in this unit to base unit (FEET).
     */
    public double convertToBaseUnit(double value) {
        return value * conversionFactorToFeet;
    }

    /**
     * Convert value from base unit (FEET) to this unit.
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactorToFeet;
    }
}