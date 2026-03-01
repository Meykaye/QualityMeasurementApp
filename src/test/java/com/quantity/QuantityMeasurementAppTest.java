package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    // ================= FEET TESTS =================

    @Test
    void testFeetEquality_SameValue() {
        QuantityMeasurementApp.Feet f1 =
                new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 =
                new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f2));
    }

    @Test
    void testFeetEquality_DifferentValue() {
        QuantityMeasurementApp.Feet f1 =
                new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 =
                new QuantityMeasurementApp.Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    void testFeetEquality_NullComparison() {
        QuantityMeasurementApp.Feet f1 =
                new QuantityMeasurementApp.Feet(1.0);

        assertFalse(f1.equals(null));
    }

    @Test
    void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet f1 =
                new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f1));
    }

    @Test
    void testFeetEquality_TypeSafety() {
        QuantityMeasurementApp.Feet f1 =
                new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Inches i1 =
                new QuantityMeasurementApp.Inches(1.0);

        assertFalse(f1.equals(i1));
    }

    // ================= INCH TESTS =================

    @Test
    void testInchesEquality_SameValue() {
        QuantityMeasurementApp.Inches i1 =
                new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches i2 =
                new QuantityMeasurementApp.Inches(1.0);

        assertTrue(i1.equals(i2));
    }

    @Test
    void testInchesEquality_DifferentValue() {
        QuantityMeasurementApp.Inches i1 =
                new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches i2 =
                new QuantityMeasurementApp.Inches(2.0);

        assertFalse(i1.equals(i2));
    }

    @Test
    void testInchesEquality_NullComparison() {
        QuantityMeasurementApp.Inches i1 =
                new QuantityMeasurementApp.Inches(1.0);

        assertFalse(i1.equals(null));
    }

    @Test
    void testInchesEquality_SameReference() {
        QuantityMeasurementApp.Inches i1 =
                new QuantityMeasurementApp.Inches(1.0);

        assertTrue(i1.equals(i1));
    }
}