package com.stockers.util;

import com.stockers.entity.Stock;

/**
 * Binary Search with SortedArray passed in Descending order
 * 
 * @author fullstack8
 *
 */
public class BinarySearchUtil {

	// If found returns index of Stock in current array
	public static int binarySearch(Stock[] stocks, int left, int right, String stockName) {
		if (right >= left) {
			int mid = left + (right - left) / 2;

			// If the stock is present at the
			// middle itself
			if (stocks[mid].getNameId().equals(stockName))
				return mid;

			int lexicographicalOrder = stocks[mid].getNameId().compareTo(stockName);

			// If stockName is Lexicographically is smaller than mid, then check in left
			// subarray
			if (lexicographicalOrder < 0)
				return binarySearch(stocks, left, mid - 1, stockName);

			// Else check the stockName in right subarray
			return binarySearch(stocks, mid + 1, right, stockName);
		}

		// Element not found
		return -1;
	}
}
