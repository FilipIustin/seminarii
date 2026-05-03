package genericsIntro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Examples {

    public static void main(String[] args) {

        System.out.println("=== 1. Generic class ===");
        Box<String> stringBox = new Box<>("Hello");
        Box<Integer> intBox = new Box<>(123);

        System.out.println("String box: " + stringBox.getValue());
        System.out.println("Integer box: " + intBox.getValue());

        System.out.println("\n=== 2. Generic method ===");
        Integer[] numbers = {1, 2, 3, 4};
        String[] words = {"Java", "Generics", "Demo"};

        printArray(numbers);
        printArray(words);

        System.out.println("\n=== 3. Bounded type parameter ===");
        System.out.println("Sum of numbers: " + addNumbers(10, 20));
        System.out.println("Sum of decimals: " + addNumbers(5.5, 4.5));

        System.out.println("\n=== 4. Wildcards ===");
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.5);

        System.out.println("Total of intList: " + sumList(intList));
        System.out.println("Total of doubleList: " + sumList(doubleList));

        System.out.println("\n=== 5. ? extends example ===");
        List<Integer> sourceNumbers = Arrays.asList(10, 20, 30);
        printNumbers(sourceNumbers);

        System.out.println("\n=== 6. ? super example ===");
        List<Number> destination = new ArrayList<>();
        addIntegers(destination);
        System.out.println("Destination after addIntegers: " + destination);
    }

    // 1. Generic class
    static class Box<T> {
        private T value;

        public Box(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    // 2. Generic method
    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // 3. Bounded type parameter
    public static <T extends Number> double addNumbers(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    // 4. Wildcard: accepts a list of any subclass of Number
    public static double sumList(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    // 5. ? extends = good for reading
    public static void printNumbers(List<? extends Number> list) {
        for (Number n : list) {
            System.out.println(n);
        }
    }

    // 6. ? super = good for writing
    public static void addIntegers(List<? super Integer> list) {
        list.add(100);
        list.add(200);
        list.add(300);
    }
}


//// exercitii:
//1. Create a generic class that contains:
//     - private field of type T
//     - constructor
//     - getValue() and setValue()
//and test it at the end
//
//2. Create generic method printTwice(T value) and test it
//
//3. Create a method that introduces bounded types: public static <T extends Comparable<T>> T max(T a, T b)
//Test with Integer and String
//
//4. Create a method countElements that takes in as a parameter a list of generic elements
// and returns the total nr of elements inside that array
//
//5. Copy lists: fix this method:
//public static void copy(List<?> src, List<?> dest) {
//    for (Object o : src) {
//        dest.add(o);   // ❌ error
//    }
//}
//
//6. Create a generic Stack implementation
//  methods: push(T item), pop(), peek(), isEmpty()
//
//
//
//

