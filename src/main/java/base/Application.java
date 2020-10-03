package base;


import base.conn.JdbcConnection;

import java.sql.Connection;
import java.util.Optional;

public class Application {

    public static void main(String[] args) {

        Optional<Connection> connection = JdbcConnection.getConnection();
        if (connection.isPresent())
            System.out.println("Success");
    }

}
