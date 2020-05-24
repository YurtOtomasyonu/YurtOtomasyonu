package dao;

import entity.User;
import java.io.Serializable;
import login_logout.DBCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import login_logout.DBCon;

public class UsersDAO implements Serializable {

    public User validate(String user) {
        Connection con;
        PreparedStatement ps;

        User user2 = null ;
        
        try {
            con = DBCon.getConnection();
            ps = con.prepareStatement("select * from user  where  uname='"+user+"'");
           

            ResultSet rs = ps.executeQuery();
            rs.next();
            user2= new User ();
            
            
            user2.setUsername(rs.getString("uname"));
            user2.setPassword(rs.getString("password"));
            user2.setUturu(rs.getString("userTuru"));

            
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        } 
      return user2 ;
    }

   /* public static boolean validate2(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBCon.getConnection();
            ps = con.prepareStatement("Select uname,password from User where uname = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //result found, means valid inputs
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DBCon.close(con);
        }
        return false;
    }
*/
    public static void insert(String uname, String password, String userTuru) {
        DBCon.getConnection();
        
        try {
            Statement sts = DBCon.getConnection().createStatement();
            String sql = "INSERT INTO user(uname,password,userTuru) VALUES('" + uname + "','" + password + "','" + userTuru + "')";
            sts.executeUpdate(sql);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
