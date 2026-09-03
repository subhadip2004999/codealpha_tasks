package mypackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Users {
    private Connection con;
    private Scanner sc;

    public Users(Connection con){
        this.con = con;
    }

    public void userRegister(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        String query = "insert into users(name,email, password) values(?,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int result = ps.executeUpdate();
            if(result>0){
                System.out.println("\nAccount Created");
                return;
            }

        } catch (Exception e){
            System.out.println("LOL");
        }
    }

    public boolean userLogin(String email, String password){
        Scanner sc = new Scanner(System.in);

        
        String query = "select * from users where email=? and password=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
