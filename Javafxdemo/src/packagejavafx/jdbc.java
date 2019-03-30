/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packagejavafx;

import java.sql.*;

/**
 *
 * @author perus
 */
public class jdbc {
    public static void jdbcfunc() {
	
        final String url = "jdbc:mysql://localhost:3306/demo";
        final String user ="root";
        final String pass = "Perushenoy@99";
        try {
            
        Connection con= DriverManager.getConnection(url, user, pass);
            
	Statement st = con.createStatement();
	String sql = "CREATE TABLE DEPARTMENT " + "(id INTEGER, " + " DeptName VARCHAR(255), "+ " StudentCount INTEGER) ";
        int result = st.executeUpdate(sql);
	System.out.print(result == 0 ? "Table is created": "Table is not created ");
	} catch (SQLException e) {
            System.out.println(e.getMessage());
	}
    }
}
