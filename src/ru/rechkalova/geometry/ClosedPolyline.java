package ru.rechkalova.geometry;

import java.util.List;

public class ClosedPolyline extends Polyline {

    // Конструктор без параметров
    public ClosedPolyline() {
        super();
    }

    // Конструктор с точками
    public ClosedPolyline(Point... pts) {
        super(pts);
    }

    // Переопределяем расчет длины
    @Override
    public double getLength() {
        double length = super.getLength();
        List<Point> pts = getPoints();

        if (pts.size() > 2) {
            Point first = pts.get(0);
            Point last = pts.get(pts.size() - 1);
            length += Math.sqrt(Math.pow(first.getX() - last.getX(), 2) + Math.pow(first.getY() - last.getY(), 2));
        }
        return length;
    }

    @Override
    public String toString() {
        return "Замкнутая " + super.toString();
    }
}
