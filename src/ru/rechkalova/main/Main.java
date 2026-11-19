package ru.rechkalova.main;

import ru.rechkalova.geometry.*;
import ru.rechkalova.tree.Node;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Лабораторная работа 3. Вариант 10");

        // Проверка аргументов командной строки для Задачи 7.3
        if (args.length >= 2) {
            try {
                double res = Utils.power(args[0], args[1]);
                System.out.println("Возведение в степень из аргументов командной строки: " + args[0] + "^" + args[1] + " = " + res);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка в аргументах командной строки: нужны целые числа.");
            }
        }

        while (!exit) {
            System.out.println("\nВыберите задачу:");
            System.out.println("1. Задача 1.3 (Квадрат и его ломаная)");
            System.out.println("2. Задача 2.4 (Бинарное дерево)");
            System.out.println("3. Задача 3.2 (Замкнутая ломаная)");
            System.out.println("4. Задача 4.4 (Фигуры и их площади)");
            System.out.println("5. Задача 5.5 (Сумма длин линий и ломаных)");
            System.out.println("6. Задача 5.7 (Объединение ломаных)");
            System.out.println("7. Задача 6.4 (Сравнение ломаных)");
            System.out.println("8. Задача 7.3 (Возведение в степень)");
            System.out.println("9. Задача 8.4 (Клонирование точки)");
            System.out.println("0. Выход");

            int choice = getValidInt(scanner, "Ваш выбор: ");

            switch (choice) {
                case 1:
                    interactiveSquare(scanner);
                    break;
                case 2:
                    interactiveTree(scanner);
                    break;
                case 3:
                    interactiveClosedPolyline(scanner);
                    break;
                case 4:
                    interactiveFigures(scanner);
                    break;
                case 5:
                    interactiveTotalLength(scanner);
                    break;
                case 6:
                    interactiveMergeChains(scanner);
                    break;
                case 7:
                    interactiveComparePolylines(scanner);
                    break;
                case 8:
                    interactivePower(scanner);
                    break;
                case 9:
                    interactiveClonePoint(scanner);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Неверный пункт.");
            }
        }
        scanner.close();
    }

    // --- Метод для Задачи 1.3 ---
    private static void interactiveSquare(Scanner scanner) {
        System.out.println("\n--- Создание Квадрата ---");
        double x = getValidDouble(scanner, "Введите X левого верхнего угла (например 5): ");
        double y = getValidDouble(scanner, "Введите Y левого верхнего угла (например 3): ");
        double side = getValidDouble(scanner, "Введите длину стороны (например 23): ");

        try {
            Square sq = new Square(x, y, side);
            System.out.println("Создан: " + sq.toString());

            Polyline poly = sq.getPolygonalChain();
            System.out.println("Ломаная квадрата: " + poly.toString());
            System.out.println("Ее длина: " + String.format("%.2f", poly.getLength()));

            Point lastPoint = poly.getPoints().get(poly.getPoints().size() - 1);
            lastPoint.setX(15);
            lastPoint.setY(25);
            System.out.println("Сдвинули последнюю точку в {15;25}.");
            System.out.println("Новая длина ломаной: " + String.format("%.2f", poly.getLength()));

            double newSide = getValidDouble(scanner, "Введите новую сторону (<= 0 для проверки ошибки): ");
            sq.setSide(newSide);
            System.out.println("Обновленный квадрат: " + sq.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // --- Метод для Задачи 2.4 ---
    private static void interactiveTree(Scanner scanner) {
        System.out.println("\n--- Бинарное дерево ---");
        Node root = new Node();

        int count = -1;
        while (count < 0) {
            count = getValidInt(scanner, "Сколько чисел добавить в дерево? (0 и более): ");
        }

        for (int i = 0; i < count; i++) {
            int val = getValidInt(scanner, "  Число " + (i + 1) + ": ");
            root.add(val);
        }

        System.out.println("Дерево (левосторонний обход): " + root.toString().trim());

        int searchVal = getValidInt(scanner, "Какое число проверить на наличие?: ");
        System.out.println("Есть в дереве?: " + root.contains(searchVal));

        int delVal = getValidInt(scanner, "Какое число удалить?: ");
        System.out.println("Удалено?: " + root.remove(delVal));
        System.out.println("Дерево после удаления: " + root.toString().trim());
    }

    // --- Метод для Задачи 3.2 ---
    private static void interactiveClosedPolyline(Scanner scanner) {
        System.out.println("\n--- Создание Замкнутой ломаной ---");
        ClosedPolyline closedPoly = new ClosedPolyline();

        int count = -1;
        while (count < 0) {
            count = getValidInt(scanner, "Сколько точек будет в замкнутой ломаной? (0 и более): ");
        }

        for (int i = 0; i < count; i++) {
            System.out.println("Ввод координат для Точки " + (i + 1) + ":");
            double x = getValidDouble(scanner, "  Координата X: ");
            double y = getValidDouble(scanner, "  Координата Y: ");
            closedPoly.addPoints(new Point(x, y));
        }

        System.out.println("Сформирована ломаная: " + closedPoly.toString());
        System.out.println("Ее длина: " + String.format("%.2f", closedPoly.getLength()));
    }

    // --- Метод для Задачи 4.4 ---
    private static void interactiveFigures(Scanner scanner) {
        System.out.println("\n--- Фигуры и их площади ---");
        try {
            System.out.println("Ввод данных для Круга:");
            double cx = getValidDouble(scanner, "  Центр X: ");
            double cy = getValidDouble(scanner, "  Центр Y: ");
            double r = getValidDouble(scanner, "  Радиус: ");
            Figure circle = new Circle(new Point(cx, cy), r);

            System.out.println("Ввод данных для Квадрата:");
            double sx = getValidDouble(scanner, "  Угол X: ");
            double sy = getValidDouble(scanner, "  Угол Y: ");
            double side = getValidDouble(scanner, "  Сторона: ");
            Figure square = new Square(sx, sy, side);

            System.out.println("Ввод данных для Прямоугольника:");
            double rx = getValidDouble(scanner, "  Угол X: ");
            double ry = getValidDouble(scanner, "  Угол Y: ");
            double w = getValidDouble(scanner, "  Ширина: ");
            double h = getValidDouble(scanner, "  Высота: ");
            Figure rect = new Rectangle(new Point(rx, ry), w, h);

            System.out.println("Ввод данных для Треугольника (3 точки):");
            Point t1 = new Point(getValidDouble(scanner, "  Точка 1 X: "), getValidDouble(scanner, "  Точка 1 Y: "));
            Point t2 = new Point(getValidDouble(scanner, "  Точка 2 X: "), getValidDouble(scanner, "  Точка 2 Y: "));
            Point t3 = new Point(getValidDouble(scanner, "  Точка 3 X: "), getValidDouble(scanner, "  Точка 3 Y: "));
            Figure tri = new Triangle(t1, t2, t3);

            System.out.println("\nРезультаты:");
            Figure[] figures = {circle, square, rect, tri};
            for (Figure f : figures) {
                System.out.println(f.toString() + " -> Площадь: " + String.format("%.2f", f.getArea()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // --- Метод для Задачи 5.5 ---
    private static void interactiveTotalLength(Scanner scanner) {
        System.out.println("\n--- Сумма длин ---");
        System.out.println("Ввод координат для Линии:");
        Line line = new Line(
                getValidDouble(scanner, "  X1: "), getValidDouble(scanner, "  Y1: "),
                getValidDouble(scanner, "  X2: "), getValidDouble(scanner, "  Y2: ")
        );

        System.out.println("Ввод 3 точек для Ломаной:");
        Polyline poly = new Polyline(
                new Point(getValidDouble(scanner, "  Точка 1 X: "), getValidDouble(scanner, "  Точка 1 Y: ")),
                new Point(getValidDouble(scanner, "  Точка 2 X: "), getValidDouble(scanner, "  Точка 2 Y: ")),
                new Point(getValidDouble(scanner, "  Точка 3 X: "), getValidDouble(scanner, "  Точка 3 Y: "))
        );

        System.out.println(line.toString() + " (длина: " + String.format("%.2f", line.getLength()) + ")");
        System.out.println(poly.toString() + " (длина: " + String.format("%.2f", poly.getLength()) + ")");
        System.out.println("Общая длина: " + String.format("%.2f", Utils.totalLength(line, poly)));
    }

    // --- Метод для Задачи 5.7 ---
    private static void interactiveMergeChains(Scanner scanner) {
        System.out.println("\n--- Объединение ломаных ---");
        try {
            double side = getValidDouble(scanner, "Введите сторону квадрата (в точке 0;0): ");
            Square sq = new Square(0, 0, side);

            System.out.println("Введите координаты линии:");
            Line line = new Line(
                    getValidDouble(scanner, "  X1: "), getValidDouble(scanner, "  Y1: "),
                    getValidDouble(scanner, "  X2: "), getValidDouble(scanner, "  Y2: ")
            );

            Triangle tri = new Triangle(new Point(0, 0), new Point(2, 0), new Point(1, 2));

            Polyline merged = Utils.mergeChains(sq, line, tri);
            System.out.println("Объединенная ломаная: " + merged.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // --- Метод для Задачи 6.4 ---
    private static void interactiveComparePolylines(Scanner scanner) {
        System.out.println("\n--- Сравнение ломаных ---");
        System.out.println("Введите 2 точки для первой ломаной:");
        Polyline p1 = new Polyline(
                new Point(getValidDouble(scanner, "  Точка 1 X: "), getValidDouble(scanner, "  Точка 1 Y: ")),
                new Point(getValidDouble(scanner, "  Точка 2 X: "), getValidDouble(scanner, "  Точка 2 Y: "))
        );

        System.out.println("Введите 2 точки для второй ломаной:");
        Polyline p2 = new Polyline(
                new Point(getValidDouble(scanner, "  Точка 1 X: "), getValidDouble(scanner, "  Точка 1 Y: ")),
                new Point(getValidDouble(scanner, "  Точка 2 X: "), getValidDouble(scanner, "  Точка 2 Y: "))
        );

        System.out.println("Ломаная 1: " + p1.toString());
        System.out.println("Ломаная 2: " + p2.toString());
        System.out.println("Одинаковые?: " + p1.equals(p2));
    }

    // --- Метод для Задачи 7.3 ---
    private static void interactivePower(Scanner scanner) {
        System.out.println("\n--- Возведение в степень ---");
        String xStr = getString(scanner, "Введите число X (целое): ");
        String yStr = getString(scanner, "Введите степень Y (целое): ");
        try {
            System.out.println("Результат: " + Utils.power(xStr, yStr));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка! Нужно ввести целые числа.");
        }
    }

    // --- Метод для Задачи 8.4 ---
    private static void interactiveClonePoint(Scanner scanner) {
        System.out.println("\n--- Клонирование точки ---");
        double x = getValidDouble(scanner, "Введите X: ");
        double y = getValidDouble(scanner, "Введите Y: ");

        Point p1 = new Point(x, y);
        Point p2 = p1.clone();

        System.out.println("Оригинал: " + p1.toString());
        System.out.println("Клон: " + p2.toString());
        System.out.println("Ссылки одинаковые (p1 == p2)?: " + (p1 == p2));
        System.out.println("Значения одинаковые (p1.equals(p2))?: " + p1.equals(p2));

        p2.setX(p2.getX() + 10);
        System.out.println("Увеличили X у клона на 10.");
        System.out.println("Оригинал: " + p1.toString());
        System.out.println("Клон: " + p2.toString());
    }

    // Метод для чтения строк
    private static String getString(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    // Безопасный ввод целых чисел
    private static int getValidInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Нужно ввести целое число.");
            }
        }
    }

    // Безопасный ввод вещественных чисел
    private static double getValidDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Нужно ввести число.");
            }
        }
    }
}