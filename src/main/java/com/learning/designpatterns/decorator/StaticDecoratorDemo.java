package com.learning.designpatterns.decorator;


class StaticDecoratorDemo {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());

        ColoredShape<Square> blueSquare = new ColoredShape<>(() -> new Square(20), "blue");
        System.out.println(blueSquare.info());

        // ugly!
        TransparentShape<ColoredShape<Circle>> myCircle =
                new TransparentShape<>(() ->
                        new ColoredShape<>(() -> new Circle(5), "green"), 50
                );
        System.out.println(myCircle.info());
        // cannot call myCircle.resize()
    }
}
