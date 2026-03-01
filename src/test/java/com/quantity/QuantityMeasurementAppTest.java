package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0,
                LengthUnit.INCH.convertToBaseUnit(12.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0,
                LengthUnit.INCH.convertFromBaseUnit(1.0),
                EPSILON);
    }

    @Test
    void testEquality_CrossUnit() {

        var feet = new QuantityMeasurementApp.QuantityLength(
                1.0, LengthUnit.FEET);

        var inches = new QuantityMeasurementApp.QuantityLength(
                12.0, LengthUnit.INCH);

        assertTrue(feet.equals(inches));
    }

    @Test
    void testConvertTo_Method() {

        var feet = new QuantityMeasurementApp.QuantityLength(
                1.0, LengthUnit.FEET);

        var result = feet.convertTo(LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testAddition_ImplicitTarget() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1.0, LengthUnit.FEET);

        var b = new QuantityMeasurementApp.QuantityLength(
                12.0, LengthUnit.INCH);

        var result = a.add(b);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTarget_Yards() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1.0, LengthUnit.FEET);

        var b = new QuantityMeasurementApp.QuantityLength(
                12.0, LengthUnit.INCH);

        var result = a.add(b, LengthUnit.YARD);

        assertEquals(0.666666, result.getValue(), 1e-3);
        assertEquals(LengthUnit.YARD, result.getUnit());
    }

    @Test
    void testNullUnitValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.QuantityLength(1.0, null));
    }

    @Test
    void testInvalidValueValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.QuantityLength(Double.NaN, LengthUnit.FEET));
    }
}