package org.bashirov.sociallinks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    public static void closeCon(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    public static void closeRsAndSt(ResultSet rs, Statement st) throws SQLException{
        if (rs != null) {
            rs.close();
        }

        if (st != null) {
            st.close();
        }
    }

    public static void closeRs(ResultSet rs) throws SQLException{
        if (rs != null) {
            rs.close();
        }
    }

    public static void closeSt(Statement st) throws SQLException{
        if (st != null) {
            st.close();
        }
    }
}
