package com.learning.designpatterns.decorator;

import java.util.function.Supplier;

public class TransparentShape<T extends Shape> implements Shape {
    private Shape shape;
    private int transparency;

    public TransparentShape(Supplier<? extends T> ctor, int transparency) {
        shape = ctor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape + " has " + transparency + "% transparency";
    }
}
