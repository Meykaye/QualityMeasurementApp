package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ---------------- CONVERSION TESTS ----------------

    @Test
    void testConversion_FeetToInches() {
        double result = QuantityMeasurementApp.convert(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH
        );

        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConversion_YardsToFeet() {
        double result = QuantityMeasurementApp.convert(
                3.0,
                QuantityMeasurementApp.LengthUnit.YARD,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        assertEquals(9.0, result, EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityMeasurementApp.convert(
                2.54,
                QuantityMeasurementApp.LengthUnit.CENTIMETER,
                QuantityMeasurementApp.LengthUnit.INCH
        );

        assertEquals(1.0, result, 1e-3);
    }

    @Test
    void testConversion_ZeroValue() {
        double result = QuantityMeasurementApp.convert(
                0.0,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH
        );

        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testConversion_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(
                        Double.NaN,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH
                )
        );
    }

    // ---------------- ADDITION TESTS ----------------

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        var b = new QuantityMeasurementApp.QuantityLength(
                2.0,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        var result = a.add(b);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        var b = new QuantityMeasurementApp.QuantityLength(
                12.0,
                QuantityMeasurementApp.LengthUnit.INCH
        );

        var result = a.add(b);

        assertTrue(result.equals(
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                )
        ));
    }

    @Test
    void testAddition_Commutativity() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        var b = new QuantityMeasurementApp.QuantityLength(
                12.0,
                QuantityMeasurementApp.LengthUnit.INCH
        );

        var r1 = a.add(b);
        var r2 = b.add(a);

        double r1InFeet = r1.convertTo(
                QuantityMeasurementApp.LengthUnit.FEET
        ).getValue();

        double r2InFeet = r2.convertTo(
                QuantityMeasurementApp.LengthUnit.FEET
        ).getValue();

        assertEquals(r1InFeet, r2InFeet, EPSILON);
    }

    @Test
    void testAddition_WithZero() {

        var a = new QuantityMeasurementApp.QuantityLength(
                5.0,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        var zero = new QuantityMeasurementApp.QuantityLength(
                0.0,
                QuantityMeasurementApp.LengthUnit.INCH
        );

        var result = a.add(zero);

        assertTrue(a.equals(result));
    }

    @Test
    void testAddition_NegativeValues() {

        var a = new QuantityMeasurementApp.QuantityLength(
                5.0,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        var b = new QuantityMeasurementApp.QuantityLength(
                -2.0,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        var result = a.add(b);

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NullOperand() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        assertThrows(IllegalArgumentException.class,
                () -> a.add(null));
    }

    @Test
    void testAddition_LargeValues() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1e6,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        var b = new QuantityMeasurementApp.QuantityLength(
                1e6,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        var result = a.add(b);

        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_SmallValues() {

        var a = new QuantityMeasurementApp.QuantityLength(
                0.001,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        var b = new QuantityMeasurementApp.QuantityLength(
                0.002,
                QuantityMeasurementApp.LengthUnit.FEET
        );

        var result = a.add(b);

        assertEquals(0.003, result.getValue(), EPSILON);
    }
}