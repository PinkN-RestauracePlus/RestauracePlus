/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaffaplus.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author piero
 */
public interface DatabazeDAO {

    public void connectDatabase() throws SQLException;
    
    public void closeConnection() throws SQLException;
    
    public ResultSet getFeedbacks() throws SQLException;

    public ResultSet getListOfOrderedItems() throws SQLException;

    public ResultSet getMenus() throws SQLException;

    public ResultSet getOrders() throws SQLException;

    public ResultSet getOrderStatuses() throws SQLException;

    public ResultSet getReservations() throws SQLException;

    public ResultSet getRights() throws SQLException;

    public ResultSet getTables() throws SQLException;

    public ResultSet getUsers() throws SQLException;

    public void newFeedback(int waiter_stat, int price_stat, int food_stat, int atmosphere_stat, int waiter_id, int order_id) throws SQLException;

    public void newOrderedItem(int status, int menu_item_id, int order_id) throws SQLException;

    public void newMenuItem(String item_name, int price, int availability) throws SQLException;

    public void newOrder(String order_time, String order_date, int table_id, int order_status_id) throws SQLException;

    public void newOrderStatus(String status) throws SQLException;

    public void newReservation(int table_id, String res_time, String res_date, int number_of_people, String res_name, String note) throws SQLException;

    public void newRight(String status) throws SQLException;

    public void newTable(int capacity) throws SQLException;

    public void newUser(String user_name, int personal_number, String login, String password, int right_id) throws SQLException;

    public void deleteFeedback(int id) throws SQLException;

    public void deleteOrderedItem(int id) throws SQLException;

    public void deleteMenuItem(int id) throws SQLException;

    public void deleteOrder(int id) throws SQLException;

    public void deleteOrderStatus(int id) throws SQLException;

    public void deleteReservation(int id) throws SQLException;

    public void deleteRight(int id) throws SQLException;

    public void deleteTable(int id) throws SQLException;

    public void deleteUser(int id) throws SQLException;
}
