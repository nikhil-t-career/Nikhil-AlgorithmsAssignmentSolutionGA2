package com.stockers.util;

import java.util.Arrays;

import com.stockers.entity.Stock;

public class MergeSortUtil {

	private static void merge(Stock[] stocks, int l, int m, int r, int day, Option option) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		Stock[] L = new Stock[n1];
		Stock[] R = new Stock[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L[i] = stocks[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = stocks[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarray array
		int k = l;

		// OPTION BASED SORTING in DESCENDING ORDER
		if (option.equals(Option.PRICE)) {
			while (i < n1 && j < n2) {
				if (L[i].getUpDownPercentage()[day] >= R[j].getUpDownPercentage()[day]) {
					stocks[k] = L[i];
					i++;
				} else {
					stocks[k] = R[j];
					j++;
				}
				k++;
			}
		}
		else if (option.equals(Option.NAME)) 
		{
			while (i < n1 && j < n2) {
				
				int lexicalOrder = L[i].getNameId().compareTo(R[j].getNameId());
			
				if (lexicalOrder >= 0) {
					stocks[k] = L[i];
					i++;
				} else {
					stocks[k] = R[j];
					j++;
				}
				k++;
			}
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			stocks[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			stocks[k] = R[j];
			j++;
			k++;
		}
	}

	// Main function that sorts arr[l..r] using
	// merge()
	public static void sort(Stock[] stocks, int l, int r, int day, Option option) {
		if (l < r) {
			// Find the middle point
			int m = l + (r - l) / 2;

			// Sort first and second halves
			sort(stocks, l, m, day, option);
			sort(stocks, m + 1, r, day, option);

			// Merge the sorted halves
			merge(stocks, l, m, r, day, option);
		}
	}
	
	
	/**
	 * get stocks in lexicographically descending order for Binary Search
	 * @param stocks
	 */
	public static Stock[] getStocksSortedByName(Stock[] stocks) {
		Stock[] stocksSortedByName = new Stock[stocks.length];
		
		for(int i=0; i<stocks.length; i++)
		{
			stocksSortedByName[i] = stocks[i];
		}
		
		MergeSortUtil.sort(stocksSortedByName, 0, stocksSortedByName.length - 1, 0, Option.NAME);		
		return stocksSortedByName;
	}

}
