package com.quantity;

import com.quantity.weight.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityWeightTest {

    @Test
    void testEquality_KilogramToGram() {

        var kg =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        var g =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(kg.equals(g));
    }

    @Test
    void testEquality_KilogramToPound() {

        var kg =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        var lb =
                new QuantityWeight(2.20462, WeightUnit.POUND);

        assertTrue(kg.equals(lb));
    }

    @Test
    void testConversion_KgToGram() {

        var kg =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        var result =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0,
                result.getValue(),
                1e-3);
    }

    @Test
    void testAddition_CrossUnit() {

        var kg =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        var g =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        var result = kg.add(g);

        assertEquals(2.0,
                result.getValue(),
                1e-6);
    }

    @Test
    void testAddition_TargetUnit() {

        var kg =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        var g =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        var result =
                kg.add(g, WeightUnit.GRAM);

        assertEquals(2000.0,
                result.getValue(),
                1e-6);
    }

    @Test
    void testWeightVsLength() {

        var weight =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        var length =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }

}