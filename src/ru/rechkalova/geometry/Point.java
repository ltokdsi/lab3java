package ru.rechkalova.geometry;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public double getX() { return x; }
    public double getY() { return y; }

    // Задача 6.4 (сравнение ломанных)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    // Задача 8.4 (клонирование точки)
    @Override
    public Point clone() {
        return new Point(this.x, this.y);
    }

    @Override
    public String toString() {
        return "{" + x + ";" + y + "}";
    }
}
