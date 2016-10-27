package online.db;

import com.mysql.jdbc.CommunicationsException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;

/**
 *
 * @author Samintha
 */
public class OnlineDb {

    public static void main(String args[]) {
        if (testInet("http://sql7.freesqldatabase.com:3306")) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7141864", "sql7141864", "AWkZt1kEPB");
//here sonoo is database name, root is username and password  
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from users");
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
                }
                con.close();
            } catch (CommunicationsException e) {
                System.out.println("No internet");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Server not found.");
        }
    }

    public static boolean testInet(String site) {
        try {
            final URL url = new URL(site);
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }
}
