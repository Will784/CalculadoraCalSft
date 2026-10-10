import java.util.Scanner;

public class Calculator {

    static final String DIVISION_ERROR = "Error: El divisor debe ser diferente de cero, escriba otro numero";
    static final String NEGATIVE_EXPONENT_ERROR = "Error: El exponente debe ser mayor o igual a cero, escriba otro numero";
    static final String OVERFLOW_ERROR = "Error: El resultado es demasiado grande para calcularse";
    static final String INTEGER_ERROR = "Error: Solo se aceptan numeros enteros, intente de nuevo";

    static int passed = 0;
    static int failed = 0;

    static long add(int a, int b) {
        return (long) a + b;
    }

    static long subtract(int a, int b) {
        return (long) a - b;
    }

    static long multiply(int a, int b) {
        return (long) a * b;
    }

    static String divide(int a, int b) {
        if (b == 0) {
            return DIVISION_ERROR;
        }
        double result = (double) a / b;
        return String.valueOf(result);
    }

    static String power(int a, int b) {
        if (b < 0) {
            return NEGATIVE_EXPONENT_ERROR;
        }
        long base = a;
        long result = 1;
        for (int i = 0; i < b; i++) {
            if (base != 0 && Math.abs(result) > Long.MAX_VALUE / Math.abs(base)) {
                return OVERFLOW_ERROR;
            }
            result = result * base;
        }
        return String.valueOf(result);
    }

    static int readInteger(Scanner scanner, String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println(INTEGER_ERROR);
            System.out.print(message);
        }
        return scanner.nextInt();
    }

    static void check(String id, String input, String expected, String obtained) {
        String status;
        if (expected.equals(obtained)) {
            status = "APROBADO";
            passed++;
        } else {
            status = "FALLIDO";
            failed++;
        }
        System.out.println();
        System.out.println(id + " | " + input + " | esperado: " + expected + " | obtenido: " + obtained + " | " + status);
    }

    static void runTests() {
        System.out.println("=== PRUEBAS UNITARIAS ===");

        check("OPARIT001", "7 y 3", "10", String.valueOf(add(7, 3)));
        check("OPARIT002", "25 y 5", "5.0", divide(25, 5));
        check("OPARIT003", "23 y 0", DIVISION_ERROR, divide(23, 0));

        check("OPARIT004", "-4 y -6", "-10", String.valueOf(add(-4, -6)));
        check("OPARIT005", "15 y -20", "-5", String.valueOf(add(15, -20)));
        check("OPARIT006", "0 y 9", "9", String.valueOf(add(0, 9)));
        check("OPARIT007", "2000000000 y 2000000000", "4000000000", String.valueOf(add(2000000000, 2000000000)));

        check("OPARIT008", "10 y 4", "6", String.valueOf(subtract(10, 4)));
        check("OPARIT009", "4 y 10", "-6", String.valueOf(subtract(4, 10)));
        check("OPARIT010", "8 y 8", "0", String.valueOf(subtract(8, 8)));
        check("OPARIT011", "-5 y -3", "-2", String.valueOf(subtract(-5, -3)));

        check("OPARIT012", "6 y 7", "42", String.valueOf(multiply(6, 7)));
        check("OPARIT013", "9 y 0", "0", String.valueOf(multiply(9, 0)));
        check("OPARIT014", "-4 y -5", "20", String.valueOf(multiply(-4, -5)));
        check("OPARIT015", "8 y -3", "-24", String.valueOf(multiply(8, -3)));
        check("OPARIT016", "100000 y 100000", "10000000000", String.valueOf(multiply(100000, 100000)));

        check("OPARIT017", "7 y 2", "3.5", divide(7, 2));
        check("OPARIT018", "0 y 5", "0.0", divide(0, 5));
        check("OPARIT019", "-20 y 4", "-5.0", divide(-20, 4));
        check("OPARIT020", "0 y 0", DIVISION_ERROR, divide(0, 0));

        check("OPARIT021", "texto 'abc' y luego 12", "12", String.valueOf(readInteger(new Scanner("abc\n12\n"), "Ingrese un entero: ")));
        check("OPARIT022", "decimal 3.5 y luego 4", "4", String.valueOf(readInteger(new Scanner("3.5\n4\n"), "Ingrese un entero: ")));
        check("OPARIT023", "-9", "-9", String.valueOf(readInteger(new Scanner("-9\n"), "Ingrese un entero: ")));

        check("OPARIT024", "2 y 3", "8", power(2, 3));
        check("OPARIT025", "5 y 0", "1", power(5, 0));
        check("OPARIT026", "0 y 5", "0", power(0, 5));
        check("OPARIT027", "-2 y 3", "-8", power(-2, 3));
        check("OPARIT028", "-2 y 4", "16", power(-2, 4));
        check("OPARIT029", "2 y -3", NEGATIVE_EXPONENT_ERROR, power(2, -3));
        check("OPARIT030", "10 y 19", OVERFLOW_ERROR, power(10, 19));

        System.out.println();
        System.out.println("Pruebas aprobadas: " + passed);
        System.out.println("Pruebas fallidas: " + failed);
    }

    static void runCalculator() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== CALCULADORA ===");
        int a = readInteger(scanner, "Ingrese el primer numero entero (a): ");
        int b = readInteger(scanner, "Ingrese el segundo numero entero (b): ");

        System.out.println();
        System.out.println("Suma: " + a + " + " + b + " = " + add(a, b));
        System.out.println("Resta: " + a + " - " + b + " = " + subtract(a, b));
        System.out.println("Multiplicacion: " + a + " * " + b + " = " + multiply(a, b));
        System.out.println("Division: " + a + " / " + b + " = " + divide(a, b));
        System.out.println("Potencia: " + a + " ^ " + b + " = " + power(a, b));
        scanner.close();
    }

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("test")) {
            runTests();
        } else {
            runCalculator();
        }
    }
}
