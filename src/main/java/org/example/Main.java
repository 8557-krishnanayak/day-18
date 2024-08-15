package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
//        getCustomerDetails();
//        insert();
        setTransition();
    }

    //     Execute a Simple Query
    public static void getCustomerDetails() {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement st = connection.prepareStatement("select * from customers");
             ResultSet resultSet = st.executeQuery();
        ) {


            while (resultSet.next()) {
                String customer_id = resultSet.getString("customer_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                System.out.println(customer_id + "\t\t\t" + name + "\t\t\t\t" + email);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static void insert() {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT into products " +
                     "(product_id, price, product_name, category_id, stock_quantity, discount_price) " +
                     "values (?,?,?,?,?,?);");
        ) {

            st.setInt(1, 99);
            st.setInt(2, 1000);
            st.setString(3, "shoes");
            st.setInt(4, 4);
            st.setInt(5, 3);
            st.setInt(6, 900);
            st.executeUpdate();

            System.out.println("Execution Complete: INSERTION DONE");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    static void setTransition() {
        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            try (
                    PreparedStatement st = connection.prepareStatement("INSERT into products " +
                            "(product_id, price, product_name, category_id, stock_quantity, discount_price) " +
                            "values (?,?,?,?,?,?);");
            ) {
                st.setInt(1, 98);
                st.setInt(2, 1000);
                st.setString(3, "shoes");
                st.setInt(4, 4);
                st.setInt(5, 3);
                st.setInt(6, 900);
                st.executeUpdate();

//                explicit throwing error
                if (false)
                    throw new SQLException("Explicitly Throwing Error");

                System.out.println("Execution Complete: INSERTION DONE");
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                System.out.println("ERROR: " + e.getMessage());
                if (connection != null) {
                    connection.rollback();
                    System.out.println("ROLLBACK PROCESS");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close connection: " + e.getMessage());
                }
            }
        }
    }
}