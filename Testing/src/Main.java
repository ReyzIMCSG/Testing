import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // Создаем сканер для чтения ввода с консоли
        Scanner scanner = new Scanner(System.in);
        // Читаем строки, пока есть входные данные
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                // Выводим результат вычислений или исключение
                System.out.println(calc(input));
            } catch (Exception e) {
                // Если возникает исключение, выводим сообщение об ошибке
                System.out.println("Нарушено одно из условий ввода чисел");
            }
        }
        // Закрываем сканер
        scanner.close();
    }

    // Метод для вычисления результата арифметической операции
    public static String calc(String input) throws Exception {
        // Разделяем входную строку на три части
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            // Если частей не три, выбрасываем исключение
            throw new Exception();
        }

        String num1 = parts[0];  // Первое число
        String operator = parts[1];  // Оператор (+, -, *, /)
        String num2 = parts[2];  // Второе число

        // Проверяем, являются ли оба числа римскими или арабскими
        boolean isRoman = isRoman(num1) && isRoman(num2);
        boolean isArabic = isArabic(num1) && isArabic(num2);

        if (!(isRoman || isArabic)) {
            // Если числа смешаны (разных систем) или ни одного из типов, выбрасываем исключение
            throw new Exception();
        }

        int a, b;
        if (isRoman) {
            // Конвертируем римские числа в арабские
            a = romanToArabic(num1);
            b = romanToArabic(num2);
        } else {
            // Конвертируем строковые арабские числа в целые числа
            a = Integer.parseInt(num1);
            b = Integer.parseInt(num2);
        }

        // Проверяем, что числа в допустимом диапазоне от 1 до 10 включительно
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new Exception();
        }

        int result;
        // Выполняем арифметическую операцию в зависимости от оператора
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                // Если оператор не распознан, выбрасываем исключение
                throw new Exception();
        }

        if (isRoman) {
            // Если числа были римскими, проверяем, что результат положительный
            if (result < 1) {
                throw new Exception();
            }
            // Конвертируем результат обратно в римские числа
            return arabicToRoman(result);
        } else {
            // Если числа были арабскими, возвращаем результат как строку
            return Integer.toString(result);
        }
    }

    // Метод для проверки, является ли строка римским числом
    private static boolean isRoman(String value) {
        for (char c : value.toCharArray()) {
            if (c != 'I' && c != 'V' && c != 'X' && c != 'L' && c != 'C' && c != 'D' && c != 'M') {
                return false;
            }
        }
        return true;
    }

    // Метод для проверки, является ли строка арабским числом
    private static boolean isArabic(String value) {
        for (char c : value.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    // Метод для конвертации римского числа в арабское
    private static int romanToArabic(String roman) {
        switch (roman) {
            case "I": return 1;
            case "II": return 2;
            case "III": return 3;
            case "IV": return 4;
            case "V": return 5;
            case "VI": return 6;
            case "VII": return 7;
            case "VIII": return 8;
            case "IX": return 9;
            case "X": return 10;
            default: throw new IllegalArgumentException("Invalid Roman numeral");
        }
    }

    // Метод для конвертации арабского числа в римское
    private static String arabicToRoman(int num) {
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        return tens[num / 10] + units[num % 10];
    }
}

