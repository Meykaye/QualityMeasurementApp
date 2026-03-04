package com.quantity;

import com.quantity.model.Quantity;
import com.quantity.unit.LengthUnit;
import com.quantity.unit.WeightUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void testLengthEquality() {

        var feet = new Quantity<>(1.0, LengthUnit.FEET);
        var inches = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
    }

    @Test
    void testLengthConversion() {

        var feet = new Quantity<>(1.0, LengthUnit.FEET);

        var inches =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(12.0,
                inches.getValue(),
                1e-6);
    }

    @Test
    void testLengthAddition() {

        var feet = new Quantity<>(1.0, LengthUnit.FEET);
        var inches = new Quantity<>(12.0, LengthUnit.INCHES);

        var result =
                feet.add(inches, LengthUnit.FEET);

        assertEquals(2.0,
                result.getValue(),
                1e-6);
    }

    @Test
    void testWeightEquality() {

        var kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        var g =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(g));
    }

    @Test
    void testCrossCategoryComparison() {

        var length =
                new Quantity<>(1.0, LengthUnit.FEET);

        var weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }
}