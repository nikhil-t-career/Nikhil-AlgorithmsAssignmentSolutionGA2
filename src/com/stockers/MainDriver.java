package com.stockers;

import java.util.Scanner;

import com.stockers.entity.Stock;
import com.stockers.service.StockService;
import com.stockers.util.MergeSortUtil;

//ORIGINAL REQUIREMENT MENU
//Enter the operation that you want to perform
//1.	Display the companies stock prices in ascending order
//2.	Display the companies stock prices in descending order
//3.	Display the total no of companies for which stock prices rose today
//4.	Display the total no of companies for which stock prices declined today
//5.	Search a specific stock price
//6.	press 0 to exit

//REAL WORLD REQUIREMENT MENU - (DISPLAYING EVERYTHING)
//1.	Display the companies stock prices[PERCENTAGE CHANGE IN PRICE] in ascending order  			- MERGE SORT
//2.	Display the companies stock prices[PERCENTAGE CHANGE IN PRICE] in descending order			- PRINT ABOVE ARRAY INVERTED - BETTER THAN CODE ADJUSTMENT OF < ON LINE 30 OF MergeSortUtil.merge
//3.	Display the total no of companies for which stock prices rose today compared to the Day entered by user
//4.	Display the total no of companies for which stock prices declined today compared to the Day entered by user
//5.	Search a specific stock price
//6.    
public class MainDriver {

	public static String PATH_TRANSACTIONS_CSV = "./resources/MyBhavCopy.csv";
	private static Scanner sca = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("\nReading Stocks from CSV file in RESOURCES directory. Last 6 days price including Today.");
		Stock[] stocks = StockService.readCsvFileAndInitializeStocksArray(PATH_TRANSACTIONS_CSV);

		for (int i = 0; i < stocks.length; i++) {
			System.out.println(stocks[i].toString());
		}

		System.out.println("\nSort Stocks based in percentage change in Stock price.");

		System.out.println("\nCompare Today's stock price with Past 5 days stock Price.\n Enter 1 to 5 :");
		int day = sca.nextInt();

		MergeSortUtil.sort(stocks, 0, stocks.length - 1, --day);
		System.out.println("\n\nSTOCKS in Ascending Order of Percentage change in Stock Price : ");

		int countUps = 0, countDowns = 0;
		for (Stock stock : stocks) {
			float percentageChange = stock.getUpDownPercentage()[day];
			if (percentageChange > 0)
				countUps++;
			System.out.println(stock.getNameId() + "     \t: " + percentageChange + "%");
		}

		System.out.println("\n\nSTOCKS in Descending Order of Percentage change in Stock Price : ");

		for (int i = stocks.length - 1; i >= 0; i--) {
			float percentageChange = stocks[i].getUpDownPercentage()[day];
			
			if (percentageChange < 0)
				countDowns++;
			
			System.out.println(stocks[i].getNameId() + "     \t: " + percentageChange + "%");
		}
		
		System.out.println("\nTotal number of Stocks that increased compared to the Day provided by user : " + countUps);
		System.out.println("\nTotal number of Stocks that decreased compared to the Day provided by user : " + countDowns);

	}

}
