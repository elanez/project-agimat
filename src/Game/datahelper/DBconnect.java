package Game.datahelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class DBconnect {
    static String url = "jdbc:mysql://localhost:3306/game";

    public static Connection con() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(url, "root", "");
        return c;
    }

    public void putdata(String sql) throws Exception {
        Connection c = DBconnect.con();
        Statement st = c.createStatement();
        st.executeUpdate(sql);
    }

    public ResultSet getdata(String sql) throws Exception {
        Connection c = DBconnect.con();
        Statement st = c.createStatement();
        ResultSet r = st.executeQuery(sql);

        return r;
    }

    public static long compareTo(Date date1,Date date2) {
        return date1.getTime() - date2.getTime();
    }
}
