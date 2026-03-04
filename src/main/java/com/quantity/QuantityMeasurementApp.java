package com.quantity;

import com.quantity.weight.*;
import com.quantity.length.*;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityWeight w1 =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println(w1.equals(w2));

        QuantityWeight sum =
                w1.add(w2, WeightUnit.GRAM);

        System.out.println(sum);

        QuantityWeight converted =
                w1.convertTo(WeightUnit.POUND);

        System.out.println(converted);
    }
}