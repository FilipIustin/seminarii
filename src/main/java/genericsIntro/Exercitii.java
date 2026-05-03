package genericsIntro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercitii {
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
    public static <T> void printTwice(T value) {
        System.out.println(value);
        System.out.println(value);
    }


    public static <T extends Comparable<T>> T max(T a, T b) {
        if (a.compareTo(b) >= 0) {
            return a;
        } else {
            return b;
        }
    }

    public static int countElements(List<?> list) {
        return list.size();
    }

    public static <T> void copy(List<? extends T> src, List<T> dest) {
        for (T item : src) {
            dest.add(item);
        }
    }

    static class Stack<T> {
        private ArrayList<T> elements = new ArrayList<>();

        public void push(T item) {
            elements.add(item);
        }

        public T pop() {
            if (isEmpty()) throw new RuntimeException("Nu mai e nimic");
            return elements.remove(elements.size() - 1);
        }

        public T peek() {
            if (isEmpty()) throw new RuntimeException("gol");
            return elements.get(elements.size() - 1);
        }

        public boolean isEmpty() {
            return elements.isEmpty();
        }
    }
    public static void main(String[] args) {

        System.out.println("1");
        Box<String> stringBox = new Box<>("Hello");
        Box<Integer> intBox = new Box<>(42);
        System.out.println("String box: " + stringBox.getValue());
        System.out.println("Integer box: " + intBox.getValue());
        stringBox.setValue("World");
        System.out.println("After setValue: " + stringBox.getValue());

        System.out.println("\n 2");
        printTwice("Java");
        printTwice(99);
        printTwice(3.14);

        System.out.println("\n 3");
        System.out.println("Max(3, 7): " + max(3, 7));

        System.out.println("\n 4");
        List<String> words = Arrays.asList("Java", "Generics", "Demo");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Words count: " + countElements(words));
        System.out.println("Numbers count: " + countElements(numbers));

        System.out.println("\n 5");
        List<Integer> src = Arrays.asList(10, 20, 30);
        List<Number> dest = new ArrayList<>();
        copy(src, dest);
        System.out.println("Destination after copy: " + dest);

        System.out.println("\n 6 ");
        Stack<String> stack = new Stack<>();
        System.out.println("isEmpty: " + stack.isEmpty());
        stack.push("first");
        stack.push("second");
        stack.push("third");
        System.out.println("peek: " + stack.peek());
        System.out.println("pop: " + stack.pop());
        System.out.println("peek after pop: " + stack.peek());
    }
}