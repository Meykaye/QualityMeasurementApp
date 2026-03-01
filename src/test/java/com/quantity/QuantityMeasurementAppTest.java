package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.FEET);

        var b = new QuantityMeasurementApp.QuantityLength(
                12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = a.add(b, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.FEET);

        var b = new QuantityMeasurementApp.QuantityLength(
                12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = a.add(b, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.INCH, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.FEET);

        var b = new QuantityMeasurementApp.QuantityLength(
                12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = a.add(b, QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(0.666666, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.INCH);

        var b = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = a.add(b, QuantityMeasurementApp.LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue(), 1e-2);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.FEET);

        var b = new QuantityMeasurementApp.QuantityLength(
                12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var r1 = a.add(b, QuantityMeasurementApp.LengthUnit.YARD);
        var r2 = b.add(a, QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {

        var a = new QuantityMeasurementApp.QuantityLength(
                5.0, QuantityMeasurementApp.LengthUnit.FEET);

        var zero = new QuantityMeasurementApp.QuantityLength(
                0.0, QuantityMeasurementApp.LengthUnit.INCH);

        var result = a.add(zero, QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(1.666666, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {

        var a = new QuantityMeasurementApp.QuantityLength(
                5.0, QuantityMeasurementApp.LengthUnit.FEET);

        var b = new QuantityMeasurementApp.QuantityLength(
                -2.0, QuantityMeasurementApp.LengthUnit.FEET);

        var result = a.add(b, QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTarget() {

        var a = new QuantityMeasurementApp.QuantityLength(
                1.0, QuantityMeasurementApp.LengthUnit.FEET);

        var b = new QuantityMeasurementApp.QuantityLength(
                12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertThrows(IllegalArgumentException.class,
                () -> a.add(b, null));
    }
}