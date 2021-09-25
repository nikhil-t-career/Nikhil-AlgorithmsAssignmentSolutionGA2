package com.stockers;

import com.stockers.entity.Stock;
import com.stockers.service.StockService;
import com.stockers.util.MergeSortUtil;

public class MainDriver {
	
	public static String PATH_TRANSACTIONS_CSV = "./resources/MyBhavCopy.csv";

	public static void main(String[] args) {
		long millis = System.currentTimeMillis();
		System.out.println(millis);
		Stock[] stocks = StockService.readCsvFileAndInitializeStocksArray(PATH_TRANSACTIONS_CSV);
		System.out.println("CALC PERCENTAGE CHANGE - " + (System.currentTimeMillis() - millis));
		
		millis = System.currentTimeMillis();
		int day = 1;
		
		MergeSortUtil.sort(stocks, 0, stocks.length - 1, --day);
		System.out.println("SORT - " + (System.currentTimeMillis() - millis));
		System.out.println("\n\nSTOCKS in Ascending Order of Growth : ");
		for(Stock stock: stocks) 
		{
			System.out.println(stock.getNameId() + "     \t: " + stock.getUpDownPercentage()[day] + "%");
		}
		
		
		
	}

}
