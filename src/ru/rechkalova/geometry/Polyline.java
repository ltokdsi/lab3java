package ru.rechkalova.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Polyline implements Measurement, ToPolyline {
    private List<Point> points;

    public Polyline() {
        this.points = new ArrayList<>();
    }

    public Polyline(Point... pts) {
        this.points = new ArrayList<>(Arrays.asList(pts));
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addPoints(Point... pts) {
        this.points.addAll(Arrays.asList(pts));
    }

    // Задача 5.5 (измерение данных)
    @Override
    public double getLength() {
        double length = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            length += Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
        }
        return length;
    }

    // Задача 5.7 (получение ломанной линии)
    @Override
    public Polyline getPolygonalChain() {
        Polyline copy = new Polyline();
        for (Point p : points) {
            copy.addPoints(new Point(p.getX(), p.getY()));
        }
        return copy;
    }

    // Задача 6.4 (сравнение ломанных)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polyline)) return false;
        Polyline polyline = (Polyline) o;
        return Objects.equals(points, polyline.points);
    }

    @Override
    public String toString() {
        return "Линия " + points.toString();
    }
}
