package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

interface Identifiable {
    String getId();
}

interface Enrollable {
    void enroll(String course);
}

class Student extends Person implements Identifiable, Enrollable {

    private String studentId;
    private int year;
    public double gpa;

    public Student() {
        super();
        this.studentId = "N/A";
        this.year = 1;
        this.gpa = 0.0;
    }

    public Student(String name) {
        this.name = name;
        this.studentId = "N/A";
        this.year = 1;
        this.gpa = 0.0;
    }

    public Student(String name, int year) {
        this.name = name;
        this.studentId = "N/A";
        this.year = year;
        this.gpa = 0.0;
    }

    public Student(String name, String studentId, int year, double gpa) {
        this.name = name;
        this.studentId = studentId;
        this.year = year;
        this.gpa = gpa;
    }

    public void sayHello() {
        System.out.println("Hello, my name is " + name + " and I am a student.");
    }

    public String greet(String otherPerson) {
        return "Hello " + otherPerson + ", I am " + name + ".";
    }

    private void secretMethod() {
        System.out.println("This is a private method of student " + name + ".");
    }

    @Override
    public String getId() {
        return studentId;
    }

    @Override
    public void enroll(String course) {
        System.out.println(name + " enrolled in " + course + ".");
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', id='" + studentId +
                "', year=" + year + ", gpa=" + gpa + "}";
    }
}

public class rezolvare {

    public static void main(String[] args) {
        try {
            Class<?> studentClass = Student.class;


            Constructor<?> constructor = studentClass.getConstructor();
            Object studentObject = constructor.newInstance();

            Field studentIdField = studentClass.getDeclaredField("studentId");

            studentIdField.setAccessible(true);

            System.out.println("Original private studentId field:");
            System.out.println(studentIdField.get(studentObject));

            studentIdField.set(studentObject, "S12345");

            System.out.println("Modified object:");
            System.out.println(studentObject);
            System.out.println();

            Method secretMethod = studentClass.getDeclaredMethod("secretMethod");

            secretMethod.setAccessible(true);

            System.out.println("Calling private method:");
            secretMethod.invoke(studentObject);
            System.out.println();

            System.out.println("Creating objects using different constructors:");

            Constructor<?> noArgConstructor = studentClass.getConstructor();
            Object student1 = noArgConstructor.newInstance();
            System.out.println("Student(): " + student1);

            Constructor<?> oneArgConstructor = studentClass.getConstructor(String.class);
            Object student2 = oneArgConstructor.newInstance("Ana");
            System.out.println("Student(String name): " + student2);

            Constructor<?> twoArgConstructor = studentClass.getConstructor(String.class, int.class);
            Object student3 = twoArgConstructor.newInstance("Bogdan", 3);
            System.out.println("Student(String name, int year): " + student3);

            System.out.println();

            System.out.println("Inspecting object:");
            inspect(student3);
            System.out.println();

            System.out.println("JSON serialization:");
            System.out.println(toJson(student3));
            System.out.println();

            System.out.println("CSV mapping:");
            String csvHeader = "name,studentId,year,gpa";
            String csvRow = "Cristina,S98765,2,9.45";
            Object studentFromCsv = fromCsv(Student.class, csvHeader, csvRow);
            System.out.println("Object created from CSV: " + studentFromCsv);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void inspect(Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                System.out.println(field.getType().getSimpleName() + " " +
                        field.getName() + " = " + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String toJson(Object obj) {
        StringBuilder json = new StringBuilder("{");

        try {
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                Object value = field.get(obj);

                json.append("\"").append(field.getName()).append("\":");

                if (value instanceof String) {
                    json.append("\"").append(value).append("\"");
                } else {
                    json.append(value);
                }

                if (i < fields.length - 1) {
                    json.append(",");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        json.append("}");
        return json.toString();
    }


    public static Object fromCsv(Class<?> clazz, String header, String row) {
        try {
            Constructor<?> constructor = clazz.getConstructor();
            Object instance = constructor.newInstance();

            String[] columnNames = header.split(",");
            String[] values = row.split(",");

            for (int i = 0; i < columnNames.length; i++) {
                String columnName = columnNames[i].trim();
                String value = values[i].trim();

                Field field = findField(clazz, columnName);
                field.setAccessible(true);

                Class<?> fieldType = field.getType();

                if (fieldType == String.class) {
                    field.set(instance, value);
                } else if (fieldType == int.class || fieldType == Integer.class) {
                    field.set(instance, Integer.parseInt(value));
                } else if (fieldType == double.class || fieldType == Double.class) {
                    field.set(instance, Double.parseDouble(value));
                } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                    field.set(instance, Boolean.parseBoolean(value));
                }
            }

            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper: searches for a field in the class hierarchy (including superclasses)
    private static Field findField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            try {
                return currentClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                currentClass = currentClass.getSuperclass();
            }
        }
        throw new NoSuchFieldException("Field '" + fieldName + "' not found in class hierarchy of " + clazz.getName());
    }
    }
