package uk.ac.soton.SRVVC;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class DbConnect {

    public String dburl = "jdbc:mysql://srvvc.cz22s8882bdt.eu-west-2.rds.amazonaws.com:3306";
    public String user = "admin";
    public String password = "Dunamis10";

    private static final Logger logger = LogManager.getLogger(DbConnect.class);

    public DbConnect(){
        this.dburl = dburl;
    }
    public void connect() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            logger.info("error");
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    public ArrayList<String> getUsers() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                Statement stmt = conn.createStatement();
                ResultSet rset = stmt.executeQuery("SELECT username FROM SRVVCDB.Master");

                ResultSetMetaData rsmd = rset.getMetaData();
                int columnCount = rsmd.getColumnCount();

                ArrayList<String> userList = new ArrayList<>(columnCount);
                while (rset.next()) {
                    int i = 1;
                    while(i <= columnCount) {
                        userList.add(rset.getString(i++));
                    }
                }
                logger.info(userList);
                return userList;
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            logger.info("error");
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    logger.info("Closed?");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return null;
    }

    public String getPass(String userName) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                PreparedStatement stmt = conn.prepareStatement("SELECT password FROM SRVVCDB.Master WHERE username = ?");
                stmt.setString(1,userName);
                ResultSet rset = stmt.executeQuery();

                ResultSetMetaData rsmd = rset.getMetaData();
                int columnCount = rsmd.getColumnCount();

                ArrayList<String> userList = new ArrayList<>(columnCount);
                while (rset.next()) {
                    int i = 1;
                    while(i <= columnCount) {
                        userList.add(rset.getString(i++));
                    }
                }
                logger.info(userList);
                return userList.get(0);
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            logger.info("error");
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    logger.info("Closed?");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return null;
    }

    public void UpdateVotes(String logu, int apc, int pdp, int lp, int apga, int nnpp, int ypp, int sdp, int adc) throws ClassNotFoundException {
        int sum = apc + pdp + lp + apga +nnpp+ypp+sdp+adc;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                PreparedStatement stmt = conn.prepareStatement("UPDATE SRVVCDB.Master SET APC = ?,PDP = ?,LP = ?,APGA = ?,NNPP = ?,YPP = ?,SDP = ?,ADC = ?, Total = ? WHERE username = ?");
                stmt.setString(1, String.valueOf(apc));
                stmt.setString(2, String.valueOf(pdp));
                stmt.setString(3, String.valueOf(lp));
                stmt.setString(4, String.valueOf(apga));
                stmt.setString(5, String.valueOf(nnpp));
                stmt.setString(6, String.valueOf(ypp));
                stmt.setString(7, String.valueOf(sdp));
                stmt.setString(8, String.valueOf(adc));
                stmt.setString(9, String.valueOf(sum));
                stmt.setString(10, logu);


                stmt.executeUpdate();
                logger.info("Updating fields");

            }
        } catch (SQLException ex) {
            System.out.println("Couldnt establish connection to server");
            logger.info("error");
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    logger.info("Closed?");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
