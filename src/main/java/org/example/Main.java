package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
//        getCustomerDetails();
        insert();
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
}