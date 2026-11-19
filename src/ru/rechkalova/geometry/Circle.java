package ru.rechkalova.geometry;

public class Circle extends Figure {
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Ошибка: радиус круга должен быть больше 0!");
        }
        this.center = new Point(center.getX(), center.getY());
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }
    public double getRadius() {
        return radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Круг с центром в " + center + " и радиусом " + radius;
    }
}
