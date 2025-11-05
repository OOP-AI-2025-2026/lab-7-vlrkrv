package ua.opnu;
import java.util.Arrays;
import java.util.function.Predicate;

class Student {
    private String name;
    private String group;
    private int[] marks;

    // Конструктор
    public Student(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    // Гетери
    public String getName() { return name; }
    public String getGroup() { return group; }
    public int[] getMarks() { return marks; }

    // Метод для підрахунку заборгованостей
    public int countDebts() {
        int count = 0;
        for (int mark : marks) {
            if (mark < 60) count++;
        }
        return count;
    }

    // Метод для фільтрації, task2
    public static Student[] filterStudents(Student[] students, Predicate<Student> predicate) {
        Student[] result = new Student[students.length];
        int counter = 0;
        for (Student student : students) {
            if (predicate.test(student)) {
                result[counter] = student;
                counter++;
            }
        }
        return Arrays.copyOf(result, counter);
    }

    // task3
    public static Student[] filterStudentsByTwo(Student[] students, Predicate<Student> pred1, Predicate<Student> pred2) {
        Student[] result = new Student[students.length];
        int counter = 0;
        for (Student student : students) {
            if (pred1.test(student) && pred2.test(student)) {
                result[counter] = student;
                counter++;
            }
        }
        return Arrays.copyOf(result, counter);
    }

    @Override
    public String toString() {
        return name + " (" + group + ") - оцінки: " + Arrays.toString(marks);
    }
}

