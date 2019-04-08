package com.learning.designpatterns.decorator;

import java.util.function.Supplier;

class DynamicDecoratorDemo {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());

        ColoredShape blueSquare = new ColoredShape((Supplier) new Square(20), "blue");
        System.out.println(blueSquare.info());

        TransparentShape myCircle = new TransparentShape((Supplier) new ColoredShape((Supplier) new Circle(5), "green"), 50);
        System.out.println(myCircle.info());

        // cannot call myCircle.resize()
    }
}
