package mypackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Stocks {
    
    private Connection con;

    public Stocks(Connection con){
        this.con = con;
    }

    public void getStocks(){
        String query = "select * from stocks";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("\n\n|-----------------------|");
            System.out.println("| DISPLAY : MARKET DATA |");
            System.out.println("|-----------------------|\n");

            while(rs.next()){
                System.out.println(rs.getInt("id")+".  "+rs.getString("symbol")+"  |  "+rs.getString("companyname")+"   |   Rs. "+rs.getInt("price"));
                System.out.println();
            }

        } catch (Exception e){
            System.out.println("## DATABASE ERROR !! ##");
        }
    }
}
