package mypackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Portfolio {
    private Connection con;
    
    public Portfolio(Connection con){
        this.con = con;
    }

    public void viewPortfolio(String email){
        try {
               
            PreparedStatement ps = con.prepareStatement("select * from users where email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String username = rs.getString("name");
                String useremail = rs.getString("email");
                double userbalance = rs.getDouble("balance");
                double userstockvalue = rs.getDouble("stockvalue");
                
                System.out.println("\n\n||-----------------------------------------||");
                System.out.println("|| USER PORTFOLIO (DETAILS + TRANSACTIONS) ||");
                System.out.println("||-----------------------------------------||\n");
                System.out.println("Name: "+username);
                System.out.println("Email: "+useremail);
                System.out.println("Total Balance: Rs. "+userbalance);
                System.out.println("Total Stock Value: Rs. "+userstockvalue);

                PreparedStatement ps1 = con.prepareStatement("select * from portfolio where useremail=?");
                ps1.setString(1, email);
                ResultSet rs1 = ps1.executeQuery();
                int i =0;
                System.out.println("\n|--------------|");
                System.out.println("| TRANSACTIONS |");
                System.out.println("|--------------|\n");
                while(rs1.next()){
                    i++;

                    System.out.println(i+".  Stock: "+rs1.getString("stocksymbol")+"  |  Quantity: "+rs1.getInt("quantity")+"  |  Status: "+rs1.getString("buyorsell"));
                    //System.out.println();
                }

            }
        } catch (Exception e) {
        }
    }





    public boolean buyStocks(String email, String symbol, int quantity){
        Scanner sc = new Scanner(System.in);
        String buyorsell = "Bought";
    
        try {
            String query = "insert into portfolio(useremail, stocksymbol, buyorsell, quantity) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, symbol);
            ps.setString(3, buyorsell);
            ps.setInt(4, quantity);
            int result = ps.executeUpdate();
            if (result>0){
                
                if(userbuy(email,symbol, quantity)==true){
                    return true;
                }
            }
            else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }





    public boolean sellStocks(String email, String symbol, int quantity){
        Scanner sc = new Scanner(System.in);
        String buyorsell = "Sold";
    
        try {
            String query = "insert into portfolio(useremail, stocksymbol, buyorsell, quantity) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, symbol);
            ps.setString(3, buyorsell);
            ps.setInt(4, quantity);
            int result = ps.executeUpdate();
            if (result>0){
                if(userbuy(email,symbol, quantity)==true){
                    return true;
                }
            }
            else{
                return false;
            }   
                
        } catch (Exception e) {
            return false;
        }
        return false;
    }





    public boolean userbuy(String email, String symbol, int quantity){
        try {
            PreparedStatement ps = con.prepareStatement("select * from users where email=?");
            ps.setString(1, email);
            ResultSet rs1 = ps.executeQuery();
            if(rs1.next()){
                PreparedStatement ps1 = con.prepareStatement("select price from stocks where symbol=?");
                ps1.setString(1, symbol);
                ResultSet rs2 = ps1.executeQuery();
                if(rs2.next()){
                    double totalprice = quantity*(rs2.getDouble("price"));
                    if(rs1.getDouble("balance")<totalprice){
                        System.out.println("\nInfufficient balance\n");
                    }
                    double newbalance = (rs1.getDouble("balance"))-totalprice;
                    double newstockvalue = (rs1.getDouble("stockvalue"))+totalprice;

                    PreparedStatement ps2 = con.prepareStatement("update users set balance=? , stockvalue=? where email=?");
                    ps2.setDouble(1, newbalance);
                    ps2.setDouble(2, newstockvalue);
                    ps2.setString(3, email);
                    int res4 = ps2.executeUpdate();
                    if(res4>0){
                        return true;
                    }
                }
            }

        } catch (Exception e) {
        }
        return false;
    }






    public boolean usersell(String email, String symbol, int quantity){
        try {
            PreparedStatement ps = con.prepareStatement("select * from users where email=?");
            ps.setString(1, email);
            ResultSet rs1 = ps.executeQuery();
            if(rs1.next()){
                PreparedStatement ps1 = con.prepareStatement("select price from stocks where symbol=?");
                ps1.setString(1, symbol);
                ResultSet rs2 = ps1.executeQuery();
                if(rs2.next()){
                    
                    double totalprice = quantity*(rs2.getDouble("price"));

                    if(rs1.getDouble("stockvalue")<totalprice){
                        System.out.println("\nInfufficient stock value\n");
                    }
                    double newbalance = (rs1.getDouble("balance"))+totalprice;
                    double newstockvalue = (rs1.getDouble("stockvalue"))-totalprice;

                    PreparedStatement ps2 = con.prepareStatement("update users set balance=? , stockvalue=? where email=?");
                    ps2.setDouble(1, newbalance);
                    ps2.setDouble(2, newstockvalue);
                    ps2.setString(3, email);
                    int res4 = ps2.executeUpdate();
                    if(res4>0){
                        return true;
                    }
                }
            }

        } catch (Exception e) {
        }
        return false;
    }

}
