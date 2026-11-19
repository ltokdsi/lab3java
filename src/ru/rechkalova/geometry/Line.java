package ru.rechkalova.geometry;

public class Line implements Measurement, ToPolyline {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public Point getStart() { return start; }
    public Point getEnd() { return end; }

    // Задача 5.5 (измерение длины)
    @Override
    public double getLength() {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    // Задача 5.7 (получение ломанной линии)
    @Override
    public Polyline getPolygonalChain() {
        return new Polyline(
                new Point(start.getX(), start.getY()),
                new Point(end.getX(), end.getY())
        );
    }

    @Override
    public String toString() {
        return "Линия от " + start + " до " + end;
    }
}
