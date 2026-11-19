package ru.rechkalova.geometry;

public class Triangle extends Figure implements ToPolyline {
    private Point p1;
    private Point p2;
    private Point p3;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = new Point(p1.getX(), p1.getY());
        this.p2 = new Point(p2.getX(), p2.getY());
        this.p3 = new Point(p3.getX(), p3.getY());
    }

    public Point getP1() { return p1; }
    public Point getP2() { return p2; }
    public Point getP3() { return p3; }

    // Задача 4.4 (площадь фигуры, наследование Figure)
    @Override
    public double getArea() {
        return Math.abs(
                (p1.getX() * (p2.getY() - p3.getY()) +
                        p2.getX() * (p3.getY() - p1.getY()) +
                        p3.getX() * (p1.getY() - p2.getY())) / 2.0
        );
    }

    // Задача 5.7 (получение ломанной линии)
    @Override
    public Polyline getPolygonalChain() {
        return new ClosedPolyline(
                new Point(p1.getX(), p1.getY()),
                new Point(p2.getX(), p2.getY()),
                new Point(p3.getX(), p3.getY())
        );
    }

    @Override
    public String toString() {
        return "Треугольник с вершинами " + p1 + ", " + p2 + ", " + p3;
    }
}