package javalab1;

import java.util.Scanner;

public class javalab1 {
    public static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        // Задача 'Сиракузская последовательность'
        System.out.print("Введите число для задачи 'Сиракузская последовательность': ");
        int n = scanner.nextInt();
        System.out.println("задача 1: " + syracuseSequence(n));

        // Задача 'Сумма ряда'
        System.out.print("Введите количество чисел для задачи 'Сумма ряда': ");
        n = scanner.nextInt();
        System.out.println("задача 2: " + alternatingSum(n));

        // Задача 'Ищем клад'
        System.out.print("Введите координаты клада (x y) для задачи 3 (Ищем клад): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        System.out.println("задача 3: " + findTreasure(x, y));

        // Задача 'Логистический максимин'
        System.out.println("задача 'Логистический максимин' ");
        logisticMaxMin();

        // Задача 'Дважды четное число'
        System.out.print("Введите трехзначное число для задачи 'Дважды четное число': ");
        n = scanner.nextInt();
        System.out.println("задача 5: " + (isDoubleEvenNumber(n) ? "yes" : "no"));

        scanner.close();
    }

    // Метод для задачи 'Сиракузская последовательность'
    public static int syracuseSequence(int n) {
        // Базовый случай: если n равно 1, возвращаем 0 шагов
        if (n == 1) {
            return 0;
        } 
        // Если n чётное, делим на 2 и добавляем шаг
        else if (n % 2 == 0) {
            return 1 + syracuseSequence(n / 2);
        } 
        // Если n нечётное, умножаем на 3, добавляем 1 и добавляем шаг
        else {
            return 1 + syracuseSequence(3 * n + 1);
        }
    }

    // Метод для задачи 'Сумма ряда'
    public static int alternatingSum(int n) {
    int sum = 0;  

    // Цикл для ввода n чисел
    for (int i = 0; i < n; i++) {
        System.out.print("Введите число " + (i + 1) + ": ");
        int num = scanner.nextInt();

        // Если индекс чётный, прибавляем число, иначе вычитаем
        if (i % 2 == 0) {
            sum += num;
        } else {
            sum -= num;
        }
    }

    return sum;
    
    }
    // Метод для задачи 'Ищем клад'
    public static int findTreasure(int x, int y) {
        int stepCounter = 0;
        int tmpX = 0, tmpY = 0;

        while (true) {
            System.out.print("Введите направление (север, юг, восток, запад) или 'стоп' для завершения: ");
            String direction = scanner.next();
            if (direction.equals("стоп")) {
                break;
            }

            System.out.print("Введите количество шагов: ");
            int num = scanner.nextInt();

            switch (direction) {
                case "север":
                    tmpY += num;
                    break;
                case "юг":
                    tmpY -= num;
                    break;
                case "восток":
                    tmpX += num;
                    break; // ← исправлено: восток должен УВЕЛИЧИВАТЬ X
                case "запад":
                    tmpX -= num;
                    break; // ← запад должен УМЕНЬШАТЬ X
                default:
                    System.out.println("Неверное направление");
                    continue;
            }

            stepCounter++;

            if (tmpX == x && tmpY == y) {
                return stepCounter; // клад найден — сразу возвращаем
            }
        }

        return -1; // клад не найден
    }


    // Метод для задачи  'Логистический максимин'
    public static void logisticMaxMin() {
        // Ввод количества дорог
        System.out.print("Введите количество дорог: ");
        int n = scanner.nextInt();
    
        int maxHeight = 0;  
        int bestRoad = 0;  
    
        // Перебор всех дорог
        for (int i = 1; i <= n; i++) {
            System.out.print("Введите количество туннелей для дороги " + i + ": ");
            int tunnels = scanner.nextInt();
    
            int minHeight = Integer.MAX_VALUE; 
    
            // Перебор всех туннелей на дороге
            for (int j = 0; j < tunnels; j++) {
                System.out.print("Введите высоту туннеля " + (j + 1) + ": ");
                int height = scanner.nextInt();
                if (height < minHeight) {
                    minHeight = height; 
                }
            }
    
            // Проверяем, является ли текущая дорога лучшей
            if (minHeight > maxHeight) {
                maxHeight = minHeight;
                bestRoad = i;
            }
        }
    
        // Вывод результата
        System.out.println("Номер дороги: " + bestRoad + ", максимальная высота: " + maxHeight);
    }

    // Метод для задачи 5: Дважды четное число
    public static boolean isDoubleEvenNumber(int n) {
        // Проверка на трёхзначность
        if (n < 100 || n > 999) {
            System.out.println("Число должно быть трехзначным!");
            return false;
        }
    
        int sum = 0;      
        int product = 1;   
    
        // Разбор числа на цифры
        while (n > 0) {
            int digit = n % 10; 
            sum += digit;      
            product *= digit;   
            n /= 10;            
        }
    
        // Проверка чётности суммы и произведения
        return sum % 2 == 0 && product % 2 == 0;
    }
}