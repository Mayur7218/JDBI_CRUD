package org.example;

import org.jdbi.v3.core.Jdbi;

public class DatabaseConfig {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/Demo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Mayur7218@";

    public static Jdbi getJdbiInstance() {
        return Jdbi.create(DB_URL, USERNAME, PASSWORD);
    }
}
