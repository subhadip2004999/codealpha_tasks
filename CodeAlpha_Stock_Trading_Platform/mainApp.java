package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class mainApp {

  // Database connection from my local mysql database
	private static final String DB_URL = "jdbc:mysql://localhost:3306/trading_stocks";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Its private";
	public static void main(String[] args) {
		try{
			Scanner sc = new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        	System.out.println("\n## Connected to Banking Database successfully! ##");
			

			Stocks stocks = new Stocks(con);
			Users users = new Users(con);
			Portfolio portfolio = new Portfolio(con);
		while(true){
			System.out.println("\n\n||---------------------||");
			System.out.println("|| USER AUTHENTICATION ||");
			System.out.println("||---------------------||\n");
			System.out.println("1. Create a new account");
			System.out.println("2. Login into your account");
			System.out.println("3. Exit");
			System.out.print("\nEnter your choice: ");
			int choice1 = sc.nextInt();
			sc.nextLine();
			switch(choice1){
				
				case 1:
					System.out.println("\n\n|-----------------------------|");
					System.out.println("| USER : CREATE A NEW ACCOUNT |");
					System.out.println("|-----------------------------|\n");
					users.userRegister();
					System.out.println("Now Login to your account\n");
					break;

				case 2:
					System.out.println("\n\n|------------------------------|");
					System.out.println("| USER : LOGIN TO YOUR ACCOUNT |");
					System.out.println("|------------------------------|\n");
					System.out.print("Enter Email: ");
    				String email = sc.nextLine();
        			System.out.print("Enter Password: ");
        			String password = sc.nextLine();
					if(users.userLogin(email, password)==true){
						while(true){
							System.out.println("\n\n||---------------------------------||");
							System.out.println("|| WELCOME TO TRADING STOCK SYSTEM ||");
							System.out.println("||---------------------------------||\n");
							System.out.println("1. Display Market Data");
							System.out.println("2. View Portfolio");
							System.out.println("3. Buy Stocks");
							System.out.println("4. Sell Stocks");
							System.out.println("5. Exit");
							System.out.print("\nEnter your choice (1-5): ");
							int choice2 = sc.nextInt();
							sc.nextLine();

							switch(choice2){
								case 1:
									stocks.getStocks();
									sc.nextLine();
									break;

								case 2:
									portfolio.viewPortfolio(email);
									sc.nextLine();
									break;

								case 3:
									System.out.println("\n\n||---------------||");
									System.out.println("|| BUY ANY STOCK ||");
									System.out.println("||---------------||\n");
									System.out.print("Enter the Stock Symbol to buy: ");
									String symbol = sc.nextLine();
									System.out.print("\nEnter the quantity of stocks: ");
									int quantity = sc.nextInt();
									sc.nextLine();

									if(portfolio.buyStocks(email, symbol, quantity)==true){
										System.out.println("\nTransaction completed for Stock: "+symbol+" | Quantity: "+quantity);
										sc.nextLine();
										break;
									}
									else{
										System.out.println("\nTransaction Failed\n");
										sc.nextLine();
										break;
									}
								case 4:
									System.out.println("\n\n||----------------||");
									System.out.println("|| SELL ANY STOCK ||");
									System.out.println("||----------------||\n");
									System.out.print("Enter the Stock Symbol to sell: ");
									String symbol1 = sc.nextLine();
									System.out.print("\nEnter the quantity of stocks: ");
									int quantity1 = sc.nextInt();
									sc.nextLine();

									if(portfolio.sellStocks(email, symbol1, quantity1)==true){
										System.out.println("\nTransaction completed for Stock: "+symbol1+" | Quantity: "+quantity1);
										sc.nextLine();
										break;
									}
									else{
										System.out.println("\nTransaction Failed\n");
										sc.nextLine();
										break;
									}

								case 5:
									System.out.println("\n## EXIT PROGRAM ##\n");
									return;

								default:
									System.out.println("\nEnter valid choice\n");
									sc.nextLine();
									break;
							}
						}
					}
					else{
						System.out.println("\nNo users found\n");
						return;
					}
					

					

				case 3:
					System.out.println("\n## EXIT PROGRAM ##\n");
					return;

				default:
					System.out.println("\nEnter a valid choice\n");
					sc.nextLine();
					break;

			}
			
		}

		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
