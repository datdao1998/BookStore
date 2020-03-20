package model.connectdb;

// Author: anhnv

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    private static Connection connection;
    private static ConnectDB instance = new ConnectDB();
    private ConnectDB() {
        if(this.connection==null){
            String dbUrl="jdbc:mysql://localhost:3306/bookstore";
            String dbClass = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dbClass);
                this.connection = DriverManager.getConnection (dbUrl, "root", "password");
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ConnectDB getInstance(){
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
