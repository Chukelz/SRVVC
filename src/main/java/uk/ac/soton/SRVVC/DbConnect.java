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

    public ArrayList<String> getPs() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                Statement stmt = conn.createStatement();
                ResultSet rset = stmt.executeQuery("SELECT polling_station FROM SRVVCDB.Master");

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

    // Defines the method 'getPass' that takes a 'userName' as a parameter and returns a String.
    public String getPasswordGivenUser(String userName) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            // Establishes a connection to the database using the provided URL, user name, and password.
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                logger.info("Connected");
                // Prepares a SQL statement to select the password of a user where the username matches the parameter.
                PreparedStatement stmt = conn.prepareStatement("SELECT password FROM SRVVCDB.Master WHERE username = ?");
                // Sets the provided username in the prepared statement to replace the placeholder (?).
                stmt.setString(1,userName);
                ResultSet rset = stmt.executeQuery(); // executes the SQL QUERY
                ResultSetMetaData rsmd = rset.getMetaData();
                int columnCount = rsmd.getColumnCount();
                // Initializes an ArrayList to hold the values from the ResultSet.
                ArrayList<String> userList = new ArrayList<>(columnCount);
                while (rset.next()) {
                    int i = 1;
                    while(i <= columnCount) {
                        userList.add(rset.getString(i++));
                    }
                }
                //Returns the first value of the list which is the password since theres only one password
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

    public String getEmail(String userName) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                PreparedStatement stmt = conn.prepareStatement("SELECT email FROM SRVVCDB.Master WHERE username = ?");
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

    public String getPolling(String userName) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                PreparedStatement stmt = conn.prepareStatement("SELECT polling_station FROM SRVVCDB.Master WHERE username = ?");
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

    public String getLGA(String userName) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                PreparedStatement stmt = conn.prepareStatement("SELECT lga FROM SRVVCDB.Master WHERE username = ?");
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

    public ArrayList<String> getVotesState(String lga) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                PreparedStatement stmt = conn.prepareStatement("SELECT Sum(APC),Sum(PDP),Sum(LP),Sum(APGA),Sum(NNPP),Sum(YPP),Sum(SDP),Sum(ADC),Sum(Total) FROM SRVVCDB.Master WHERE lga = ? ");
                stmt.setString(1,lga);
                ResultSet rset = stmt.executeQuery();

                ResultSetMetaData rsmd = rset.getMetaData();
                int columnCount = rsmd.getColumnCount();

                ArrayList<String> votesList = new ArrayList<>(columnCount);
                while (rset.next()) {
                    int i = 1;
                    while(i <= columnCount) {
                        votesList.add(rset.getString(i++));
                    }
                }
                logger.info(votesList);
                return votesList;
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Could not retrieve results");
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

    public ArrayList<String> getVotesFromPollingStation(String ps) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            //Establishes a connection to the database using the provided URL, user name, and password.
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                // Prepares a SQL statement to select the political party votes where the polling station matches the parameter.
                PreparedStatement stmt = conn.prepareStatement("SELECT APC,PDP,LP,APGA,NNPP,YPP,SDP,ADC,lga,electoral_officer,Total FROM SRVVCDB.Master WHERE polling_station = ? ");
                stmt.setString(1,ps);
                ResultSet rset = stmt.executeQuery();
                ResultSetMetaData rsmd = rset.getMetaData();
                int columnCount = rsmd.getColumnCount();
                //Initialize array to store the votes
                ArrayList<String> votesList = new ArrayList<>(columnCount);
                while (rset.next()) {
                    int i = 1;
                    while(i <= columnCount) {
                        votesList.add(rset.getString(i++));
                    }
                }
                logger.info(votesList);
                //return list of votes
                return votesList;
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Could not retrieve results");
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

    public String getTotalVotesFromOfficer(String ps) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                PreparedStatement stmt = conn.prepareStatement("SELECT Total FROM SRVVCDB.Master WHERE username = ? ");
                stmt.setString(1,ps);
                ResultSet rset = stmt.executeQuery();

                ResultSetMetaData rsmd = rset.getMetaData();
                int columnCount = rsmd.getColumnCount();

                ArrayList<String> votesList = new ArrayList<>(columnCount);
                while (rset.next()) {
                    int i = 1;
                    while(i <= columnCount) {
                        votesList.add(rset.getString(i++));
                    }
                }
                logger.info(votesList.get(0));
                return votesList.get(0);
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Could not retrieve results");
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

    public int getTotalZ() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(Total) from SRVVCDB.Master WHERE Total > 0;");
                //stmt.setString(1,ps);
                ResultSet rset = stmt.executeQuery();

                ResultSetMetaData rsmd = rset.getMetaData();
                int columnCount = rsmd.getColumnCount();

                ArrayList<String> votesList = new ArrayList<>(columnCount);
                while (rset.next()) {
                    int i = 1;
                    while(i <= columnCount) {
                        votesList.add(rset.getString(i++));
                    }
                }
                logger.info(Integer.parseInt(votesList.get(0)));
                return Integer.parseInt(votesList.get(0));
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Could not retrieve results");
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
        return 0;
    }

    public ArrayList<String> getVotesTotal() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                PreparedStatement stmt = conn.prepareStatement("SELECT Sum(APC),Sum(PDP),Sum(LP),Sum(APGA),Sum(NNPP),Sum(YPP),Sum(SDP),Sum(ADC),Sum(Total) FROM SRVVCDB.Master");
                //stmt.setString(1,lga);
                ResultSet rset = stmt.executeQuery();

                ResultSetMetaData rsmd = rset.getMetaData();
                int columnCount = rsmd.getColumnCount();

                ArrayList<String> votesList = new ArrayList<>(columnCount);
                while (rset.next()) {
                    int i = 1;
                    while(i <= columnCount) {
                        votesList.add(rset.getString(i++));
                    }
                }
                logger.info(votesList);
                return votesList;
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Could not retrieve results");
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
    // Upload the votes cast by the polling officers to the database
    public void uploadVotesFromOfficers(String logu, int apc, int pdp, int lp, int apga, int nnpp, int ypp, int sdp, int adc)
            throws ClassNotFoundException {
        // calculates sum value to upload to total
        int sum = apc + pdp + lp + apga +nnpp+ypp+sdp+adc;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            // Establishes a connection to the database using the provided URL, user name, and password.
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                // Prepares a SQL statement to upload the votes to the database where the username matches the parameter.
                PreparedStatement stmt = conn.prepareStatement("UPDATE SRVVCDB.Master SET APC = ?,PDP = ?,LP = ?,APGA = ?,NNPP = ?" +
                        ",YPP = ?,SDP = ?,ADC = ?, Total = ? WHERE username = ?");
                //Sets the (?) values into the prepared SQL statement
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
                stmt.executeUpdate();//Executes the SQL update query
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

    public void ClearVotes() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dburl,user,password);
            if (conn != null) {
                System.out.println("Connected to the database");
                logger.info("Connected");
                PreparedStatement stmt = conn.prepareStatement("UPDATE SRVVCDB.Master SET APC = null,PDP = null,LP = null,APGA = null,NNPP = null,YPP = null,SDP = null,ADC = null, Total = null");
                stmt.executeUpdate();
                logger.info("Clearing fields");

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
