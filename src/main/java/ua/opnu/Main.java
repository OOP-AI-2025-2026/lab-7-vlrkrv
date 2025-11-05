package ua.opnu;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    static void main(String[] args) {
        // task1
        Predicate check = i -> {
            int number = (int) i;
            if (number < 2) return false; // Числа <2 не прості
            for (int j = 2; j < number; j++) {
                if (number % j == 0) return false;
            }
            return true;
        };

        System.out.println("task1");
        System.out.println(check.test(0));
        System.out.println(check.test(3));
        System.out.println(check.test(4));
        System.out.println();

        //task2
        Student[] students = {
                new Student("Іванов Іван", "Група 1", new int[]{88, 90, 79, 92, 43}),
                new Student("Петренко Петро", "Група 1", new int[]{77, 80, 61, 70, 85}),
                new Student("Коваленко Марія", "Група 2", new int[]{95, 88, 92, 65, 67}),
        };

        Predicate<Student> noDebts = student -> student.countDebts() == 0;
        Student[] filteredStudents = Student.filterStudents(students, noDebts);

        System.out.println("task2");
        System.out.println("Студенти без заборгованостей:");
        for (Student student : filteredStudents) {
            System.out.println(student);
        }
        System.out.println();

        // task3
        // умова 1 студент з групи 1
        Predicate<Student> fromGroup1 = student -> student.getGroup().equals("Група 1");

        // умова 2 студент без заборгованостей
        Predicate<Student> noDebts2 = student -> student.countDebts() == 0;

        // фільтр за 2 умовами
        Student[] result = Student.filterStudentsByTwo(students, fromGroup1, noDebts2);

        System.out.println("task3");
        System.out.println("Студенти з групи 1 без заборгованостей:");
        for (Student student : result) {
            System.out.println(student);
        }
        System.out.println();

        // task4
        Consumer<Student> studentNameConsumer = student -> {
            String fullName = student.getName();
            String[] nameParts = fullName.split(" ");
            if (nameParts.length >= 2) {
                System.out.println(nameParts[0] + " " + nameParts[1]);
            } else {
                System.out.println(fullName);
            }
        };
        Consumer<Student[]> forEachStudent = (studentArray) -> {
            for (Student student : studentArray) {
                studentNameConsumer.accept(student);
            }
        };
        System.out.println("task4");
        forEachStudent.accept(students);
        System.out.println();

        //task5
        int[] numbers = {1, 2, 3, 4, 5, 6};
        //лямбда-вираз 1 число ділиться на 3
        Predicate<Integer> divisibleBy3 = n -> n % 3 == 0;

        // лямбда-вираз 2 число парне
        Predicate<Integer> isEven = n -> n % 2 == 0;

        Consumer<Integer> printResult = n -> System.out.println("Число " + n);

        System.out.println("task5");
        System.out.println("Числа, які діляться на 3:");
        task5(numbers, divisibleBy3, printResult);

        System.out.println("\nПарні числа:");
        task5(numbers, isEven, printResult);
        System.out.println();

        //task6
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);

        int[] inputNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[] Result = new int[inputNumbers.length];
        for (int i = 0; i < inputNumbers.length; i++) {
            Result[i] = powerOfTwo.apply(inputNumbers[i]);
        }

        System.out.println("task6");
        System.out.println("n:    " + Arrays.toString(inputNumbers));
        System.out.println("2^n:  " + Arrays.toString(Result));
        System.out.println();

        //task7
        Function<Integer, String> numberWord = n -> {
            String[] words = {"нуль", "один", "два", "три", "чотири", "п'ять", "шість", "сім", "вісім", "дев'ять"};
            return words[n];
        };

        int[] Numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] wordResult = stringify(Numbers, numberWord);

        System.out.println("task7");
        System.out.println("Числа:  " + Arrays.toString(Numbers));
        System.out.println("Словами: " + Arrays.toString(wordResult));
    }

    // метод task5
    public static void task5(int[] numbers, Predicate<Integer> condition, Consumer<Integer> action) {
        for (int number : numbers) {
            if (condition.test(number)) {
                action.accept(number);
            }
        }
    }
    // метод stringify()
    public static String[] stringify(int[] Numbers, Function<Integer, String> function) {
        String[] result = new String[Numbers.length];
        for (int i = 0; i < Numbers.length; i++) {
            result[i] = function.apply(Numbers[i]);
        }
        return result;
    }
}
