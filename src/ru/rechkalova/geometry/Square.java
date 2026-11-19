package ru.rechkalova.geometry;

public class Square extends Figure implements ToPolyline {
    private Point topLeft;
    private double side;

    // Задача 1.3
    public Square(Point topLeft, double side) {
        this.topLeft = new Point(topLeft.getX(), topLeft.getY());
        setSide(side);
    }

    public Square(double x, double y, double side) {
        this(new Point(x, y), side);
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Ошибка: сторона квадрата должна быть больше 0!");
        }
        this.side = side;
    }

    // Задача 4.4 (площадь фигуры, наследование Figure)
    @Override
    public double getArea() {
        return side * side;
    }

    // Задача 1.3 и 5.7 (получение ломанной из углов)
    @Override
    public Polyline getPolygonalChain() {
        double x = topLeft.getX();
        double y = topLeft.getY();

        Point p1 = new Point(x, y);
        Point p2 = new Point(x + side, y);
        Point p3 = new Point(x + side, y - side);
        Point p4 = new Point(x, y - side);

        return new Polyline(p1, p2, p3, p4);
    }

    @Override
    public String toString() {
        return "Квадрат в точке " + topLeft + " со стороной " + side;
    }
}