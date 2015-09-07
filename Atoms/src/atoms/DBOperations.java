package atoms;

import com.mysql.jdbc.PreparedStatement;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class DBOperations {
    String url="jdbc:mysql://127.0.0.1:3306/atomsteamspace?useUnicode=true&characterEncoding=utf-8";//url=jdbc:mysql://hostname/ databaseName
    String username="root";
    String password="";
    String database="gskproject";
    Connection con=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    
    public Connection backupDB(){
        try{
            con=DriverManager.getConnection(url, username, password);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return con;
    }
    
    public boolean addUser(User user){
        try {
            con=DriverManager.getConnection(url, username, password);
            String quary="INSERT INTO user VALUES (?,?,?)";
            pst=(PreparedStatement) con.prepareStatement(quary);
            pst.setString(1, user.getUsername());
            pst.setString(2,user.getPassword());
            pst.setString(3, user.getEmail());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public int isThereUsername(String username){
        try {
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT UserName FROM user";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            while(rs.next()){
                if(rs.getString(1).equals(username)){
                    return 1;//OK
                }
            }return 0;//Miss match
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return 2;//Exception
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
    }
    
    public int checkUsernamePassword(String username,String password){
        try {
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT UserName,Password FROM user";
            pst=(PreparedStatement)con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            while(rs.next()){
                if(rs.getString(1).equals(username) & rs.getString(2).equals(password)){
                    return 1;//OK
                }
            }return 0;//Miss match
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return 2;//Exception
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
    }
    
    public ArrayList<UserGroup> getUserGroups(){
        try {
            ArrayList<UserGroup> list=new ArrayList<UserGroup>();
            con=DriverManager.getConnection(url, username, password);
            String quary="SELECT * FROM usergroup WHERE UserID='"+Atoms.username+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            while(rs.next()){
                UserGroup userGroup=new UserGroup();
                userGroup.setUsername(rs.getString(1));
                userGroup.setGroupID(rs.getInt(2));
                userGroup.setRole(rs.getString(3));
                list.add(userGroup);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public boolean addGroup(Group group){
        try {
            con=DriverManager.getConnection(url, username, password);
            String quary="INSERT INTO group VALUES (?,?,?)";
            pst=(PreparedStatement) con.prepareStatement(quary);
            pst.setInt(1, group.getGroupID());
            pst.setString(2,group.getGroupName());
            pst.setString(3,group.getDescription());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public boolean addUserGroup(UserGroup userGroup){
        try {
            con=DriverManager.getConnection(url, username, password);
            String quary="INSERT INTO usergroup VALUES (?,?,?)";
            pst=(PreparedStatement) con.prepareStatement(quary);
            pst.setString(1, userGroup.getUsername());
            pst.setInt(2,userGroup.getGroupID());
            pst.setString(3, userGroup.getRole());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public int numberOfGroupsForCurrentUser(){
        try {
            ArrayList<Group> list=new ArrayList<Group>();
            con=DriverManager.getConnection(url, username, password);
            String quary="SELECT * FROM usergroup WHERE UserID='"+Atoms.username+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            int count=0;
            while(rs.next()){
                count++;
            }
            return count;
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public int numberOfGroups(){
        try {
            //ArrayList<Group> list=new ArrayList<Group>();
            con=DriverManager.getConnection(url, username, password);
            String quary="SELECT * FROM group";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            int count=0;
            while(rs.next()){
                count++;
            }
            return count;
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public User getCurrentUser(){
        try {
            con = DriverManager.getConnection(url, username, password);
            String quary = "SELECT * FROM user WHERE username='" + Atoms.username + "'";
            pst = (PreparedStatement) con.prepareStatement(quary);
            rs = pst.executeQuery();

            rs.next();
            User user = new User();
            user.setUsername(rs.getString(1));
            user.setEmail(rs.getString(3));
            return user;

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    public int updatePassword(String password,String username){
        try {
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="UPDATE user SET Password='"+password+"' WHERE UserName='"+username+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            pst.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;//Exception
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }    
    }
}