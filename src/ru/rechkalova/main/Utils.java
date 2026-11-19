package ru.rechkalova.main;

import ru.rechkalova.geometry.*;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

public class Utils {

    // Задание 5.5: сумма длин
    public static double totalLength(Measurement... objects) {
        double sum = 0;
        for (Measurement obj : objects) {
            sum += obj.getLength();
        }
        return sum;
    }

    // Задание 5.7: объединение ломаных
    public static Polyline mergeChains(ToPolyline... objects) {
        Polyline result = new Polyline();
        for (ToPolyline obj : objects) {
            Polyline chain = obj.getPolygonalChain();
            for (Point p : chain.getPoints()) {
                result.addPoints(new Point(p.getX(), p.getY()));
            }
        }
        return result;
    }

    // Задание 7.3: возведение X в степень Y (короткие имена методов)
    public static double power(String xStr, String yStr) {
        int x = parseInt(xStr);
        int y = parseInt(yStr);
        return pow(x, y);
    }
}