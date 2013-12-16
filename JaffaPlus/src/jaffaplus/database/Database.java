package jaffaplus.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Pres tuto třídu se připojují osatní třídy k databázi Je vytvorena jako
 * Singleton, z důvodu vyvarování se dvojího přístupu do databáze (skončilo by
 * to Exception)
 *
 * @author Piero
 */
public class Database implements DatabazeDAO {

    private Statement statement;
    private Connection connection;
    private static Database instance;

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Automaticky v konstruktoru nastaví připojení dále se přistupuje přes
     * statement
     *
     * @exception SQLException odchytává problémy s připojením k databázi
     */
    private Database() throws SQLException {
    }

    @Override
    public void connectDatabase() throws SQLException {
        
        try {
            // JDBC driver
            Class.forName( "org.apache.derby.jdbc.EmbeddedDriver" );
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: " + e.getMessage());
        }
        
        connection = DriverManager.getConnection("jdbc:derby:JaffaDB", "DAT", "dat");
        this.statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    @Override
    public ResultSet getFeedbacks() throws SQLException {
        connectDatabase();
        ResultSet set = statement.executeQuery("select * from DAT.feedback");
        closeConnection();
        return set;
    }

    @Override
    public ResultSet getListOfOrderedItems() throws SQLException {
        connectDatabase();
        ResultSet set = statement.executeQuery("select * from DAT.list_of_ordered_items");
        closeConnection();
        return set;
    }

    @Override
    public ResultSet getMenus() throws SQLException {
        connectDatabase();
        ResultSet set = statement.executeQuery("select * from DAT.menu");
        closeConnection();
        return set;
    }

    @Override
    public ResultSet getOrders() throws SQLException {
        connectDatabase();
        ResultSet set = statement.executeQuery("select * from DAT.orders");
        closeConnection();
        return set;
    }

    @Override
    public ResultSet getOrderStatuses() throws SQLException {
        connectDatabase();
        ResultSet set = statement.executeQuery("select * from DAT.order_status");
        closeConnection();
        return set;
    }

    @Override
    public ResultSet getReservations() throws SQLException {
        connectDatabase();
        ResultSet set = statement.executeQuery("select * from DAT.reservation");
        closeConnection();
        return set;
    }

    @Override
    public ResultSet getRights() throws SQLException {
        connectDatabase();
        ResultSet set = statement.executeQuery("select * from DAT.rights");
        closeConnection();
        return set;
    }

    @Override
    public ResultSet getTables() throws SQLException {
        connectDatabase();
        ResultSet set = statement.executeQuery("select * from DAT.tables");
        closeConnection();
        return set;
    }

    @Override
    public ResultSet getUsers() throws SQLException {
        connectDatabase();
        ResultSet set = statement.executeQuery("select * from DAT.users");
        closeConnection();
        return set;
    }

    @Override
    public void newFeedback(int waiter_stat, int price_stat, int food_stat, int atmosphere_stat, int waiter_id, int order_id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("INSERT INTO DAT.feedback VALUES (DEFAULT, " + waiter_stat + "," + price_stat + "," + food_stat + "," + atmosphere_stat + "," + waiter_id + "," + order_id + ", DEFAULT)");
        closeConnection();
    }

    @Override
    public void newOrderedItem(int status, int menu_item_id, int order_id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("INSERT INTO DAT.list_of_ordered_items VALUES (" + status + "," + menu_item_id + "," + order_id + ", DEFAULT)");
        closeConnection();
    }

    @Override
    public void newMenuItem(String item_name, int price, int availability) throws SQLException {
        connectDatabase();
        statement.executeUpdate("INSERT INTO DAT.menu VALUES ('" + item_name + "'," + price + "," + availability + ", DEFAULT)");
        closeConnection();
    }

    @Override
    public void newOrder(String order_time, String order_date, int table_id, int order_status_id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("INSERT INTO DAT.orders VALUES ('" + order_time + "','" + order_date + "'," + table_id + "," + order_status_id + ", DEFAULT)");
        closeConnection();
    }

    @Override
    public void newOrderStatus(String status) throws SQLException {
        connectDatabase();
        statement.executeUpdate("INSERT INTO DAT.order_status VALUES ('" + status + "', DEFAULT)");
        closeConnection();
    }

    @Override
    public void newReservation(int table_id, String res_time, String res_date, int number_of_people, String res_name, String note) throws SQLException {
        connectDatabase();
        statement.executeUpdate("INSERT INTO DAT.reservation VALUES (" + table_id + ",'" + res_time + "','" + res_date + "'," + number_of_people + ",'" + res_name + "','" + note + "', DEFAULT)");
        closeConnection();
    }

    @Override
    public void newRight(String status) throws SQLException {
        connectDatabase();
        statement.executeUpdate("INSERT INTO DAT.rights VALUES ('" + status + "', DEFAULT)");
        closeConnection();
    }

    @Override
    public void newTable(int capacity) throws SQLException {
        connectDatabase();
        statement.executeUpdate("INSERT INTO DAT.tables VALUES (" + capacity + ", DEFAULT)");
        closeConnection();
    }

    @Override
    public void newUser(String user_name, int personal_number, String login, String password, int right_id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("INSERT INTO DAT.users VALUES ('" + user_name + "'," + personal_number + ",'" + login + "','" + password + "'," + right_id + ", DEFAULT)");
        closeConnection();
    }

    @Override
    public void deleteFeedback(int id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("DELETE FROM DAT.feedback WHERE ID=" + id);
        closeConnection();
    }

    @Override
    public void deleteOrderedItem(int id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("DELETE FROM DAT.list_of_ordered_items WHERE ID=" + id);
        closeConnection();
    }

    @Override
    public void deleteMenuItem(int id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("DELETE FROM DAT.menu WHERE ID=" + id);
        closeConnection();
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("DELETE FROM DAT.orders WHERE ID=" + id);
        closeConnection();
    }

    @Override
    public void deleteOrderStatus(int id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("DELETE FROM DAT.order_status WHERE ID=" + id);
        closeConnection();
    }

    @Override
    public void deleteReservation(int id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("DELETE FROM DAT.reservation WHERE ID=" + id);
        closeConnection();
    }

    @Override
    public void deleteRight(int id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("DELETE FROM DAT.rights WHERE ID=" + id);
        closeConnection();
    }

    @Override
    public void deleteTable(int id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("DELETE FROM DAT.tables WHERE ID=" + id);
        closeConnection();
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        connectDatabase();
        statement.executeUpdate("DELETE FROM DAT.users WHERE ID=" + id);
        closeConnection();
    }
}
