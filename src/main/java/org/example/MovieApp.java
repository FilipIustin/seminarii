package org.example;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;

public class MovieApp {

    private static final String URL = "jdbc:h2:mem:moviesdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            Server webServer = Server.createWebServer("-webPort", "8082", "-tcpAllowOthers").start();
            System.out.println("H2 Console started at: http://localhost:8082");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                System.out.println("Connected to H2 in-memory database.\n");

                MovieDAO dao = new MovieDAO(connection);
                dao.createTable();

                int idInception = dao.insert(new Movie("Inception", "Christopher Nolan", 2010));
                int idParasite  = dao.insert(new Movie("Parasite", "Bong Joon-ho", 2019));
                int idGodfather = dao.insert(new Movie("The Godfather", "Francis Ford Coppola", 1972));

                System.out.println("=== ALL MOVIES AFTER INSERT ===");
                dao.findAll().forEach(System.out::println);

                Movie toUpdate = dao.findById(idParasite);
                toUpdate.setTitle("Parasite (Director's Cut)");
                dao.update(toUpdate);

                System.out.println("\n=== ALL MOVIES AFTER UPDATE ===");
                dao.findAll().forEach(System.out::println);

                dao.delete(idGodfather);

                System.out.println("\n=== ALL MOVIES AFTER DELETE ===");
                dao.findAll().forEach(System.out::println);

                System.out.println("\n=== FIND BY ID (Inception) ===");
                System.out.println(dao.findById(idInception));

                System.out.println("\nPress ENTER to exit...");
                System.in.read();
            } finally {
                webServer.stop();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}