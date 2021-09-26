package com.stockers.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.stockers.entity.Stock;

public abstract class StockService {

	
	public static Stock[] readCsvFileAndInitializeStocksArray(String PATH_STOCKS_CSV) {

		Stock[] stocks = null;
		int noOfLines = 0;
		FileReader fr;
		BufferedReader br;
		
		try {
			fr = new FileReader(new File(PATH_STOCKS_CSV));
			br = new BufferedReader(fr);
			String line;
			try {
				
				br.mark(500);
				
				//Count no of lines
				while(br.readLine() != null)
				{
					++noOfLines;
				}
				br.reset(); //RESET to First row and start processing again
				stocks = new Stock[noOfLines];
				Stock stock;
				System.out.println("No Of Lines - " + noOfLines);
				
				int index = 0; 
				while ((line = br.readLine()) != null) {
					System.out.println("\nRead - " + line);
					String[] stockDetails = line.split(",");
					stock = new Stock();//Refer for setting fields
					
					stock.setNameId(stockDetails[0]);
					stock.setClosingPrice(Float.valueOf(stockDetails[1]));
					float[] prices = new float[5];
					
					//SET PRICE of PAST 5 DAYS
					prices[0] = Float.valueOf(stockDetails[2]);
					prices[1] = Float.valueOf(stockDetails[3]);
					prices[2] = Float.valueOf(stockDetails[4]);
					prices[3] = Float.valueOf(stockDetails[5]);
					prices[4] = Float.valueOf(stockDetails[6]);
					stock.setPrices(prices);
					
					//TRY Multi-Threading
					stock.calcUpDownPercentage();
					
					stocks[index++] = stock;
					stock.toString();
					
				}

			} catch (IOException e) {
				e.printStackTrace();
			} //
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	return stocks;
		
	}
}
