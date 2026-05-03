package readingFiles;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileDemo {

    // 1. Write (overwrite)
    public static void writeFile(Path path) {
        try {
            System.out.println("Writing to file...");
            Files.write(path, List.of(
                    "Ana,20",
                    "Mihai,22",
                    "Elena,19"
            ));
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    // 2. Read file
    public static void readFile(Path path) {
        try {
            System.out.println("\nReading file:");
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // 3. Append to file
    public static void appendToFile(Path path) {
        try {
            System.out.println("\nAppending new student...");
            Files.write(path,
                    List.of("George,21"),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("Error appending file: " + e.getMessage());
        }
    }

    // 4. Check if file exists
    public static void checkIfExists(Path path) {
        System.out.println("\nChecking if file exists...");
        if (Files.exists(path)) {
            System.out.println("File exists!");
        } else {
            System.out.println("File does NOT exist!");
        }
    }


}