package ru.rechkalova.geometry;

public class Rectangle extends Figure {
    private Point topLeft;
    private double width;
    private double height;

    public Rectangle(Point topLeft, double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Ошибка: стороны прямоугольника должны быть больше 0!");
        }
        this.topLeft = new Point(topLeft.getX(), topLeft.getY());
        this.width = width;
        this.height = height;
    }

    public Point getTopLeft() { return topLeft; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Прямоугольник в точке " + topLeft + " со сторонами " + width + " и " + height;
    }
}
