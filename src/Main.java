import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/accountdb?useUnicode=true&serverTimezone=UTC";
        String username = "root";
        String password = "admin"; // data to enter the database

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement dropStatement = connection.createStatement();
            if (dropStatement.execute("drop table accounts"))
                System.out.println("Table Accounts has been deleted successfully");
            Statement statementCreateTable = connection.createStatement();
            statementCreateTable.execute("create table accounts(" +
                    "id int auto_increment primary key," +
                    "user_name varchar(40) not null," +
                    "user_password varchar(20) not null," +
                    "user_first_name varchar(40) not null," +
                    "user_last_name varchar(40) not null," +
                    "user_age int not null," +
                    "user_email varchar(50) not null" +
                    ")");
            Statement statementAddData = connection.createStatement();
            statementAddData.execute("insert into accounts(user_name, user_password, user_first_name, user_last_name, user_age, user_email)" + "values ('Lyavon', '152d5638', 'Lyavon', 'Pupkin', 23, 'dsdsds@mail.ru')");
            statementAddData.execute("insert into accounts(user_name, user_password, user_first_name, user_last_name, user_age, user_email)" + "values ('Litvin', 'fg2er22ds', 'Peter', 'Gamburg', 56, 'hskjdhakjd@gmail.com')");
            statementAddData.execute("insert into accounts(user_name, user_password, user_first_name, user_last_name, user_age, user_email)" + "values ('Lyavon83', 'fskljfsdfndlk', 'Leonid', 'Bagdanovich', 37, 'nccsncc@gmail.com')");
            statementAddData.execute("insert into accounts(user_name, user_password, user_first_name, user_last_name, user_age, user_email)" + "values ('anyuta1999', 'anyuta1999', 'Anna', 'Pushkina', 15, 'anna.pushkina99@gmail.com')");
            Statement statementSelectData = connection.createStatement();
            ResultSet resultSet = statementSelectData.executeQuery("select * from accounts where id <> 2");
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt(1) +
                        ", name: "+ resultSet.getString(2) +
                        ", password: " + "Top secret" +
                        ", first name: " + resultSet.getString(4) +
                        ", last name: " + resultSet.getString(5) +
                        ", age: " + resultSet.getInt(6) +
                        ", e-mail: " + "Top secret" +
                        ")");
            }
            ResultSet resultSetAsc = statementSelectData.executeQuery("select * from accounts order by user_age");
            System.out.println("Accounts order by age asc");
            while (resultSetAsc.next()) {
                System.out.println("id: " + resultSetAsc.getInt(1) +
                        ", name: "+ resultSetAsc.getString(2) +
                        ", password: " + "Top secret" +
                        ", first name: " + resultSetAsc.getString(4) +
                        ", last name: " + resultSetAsc.getString(5) +
                        ", age: " + resultSetAsc.getInt(6) +
                        ", e-mail: " + "Top secret" +
                        ")");
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }
}
