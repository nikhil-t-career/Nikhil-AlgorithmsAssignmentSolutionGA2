package com.stockers.util;

import com.stockers.entity.Stock;

public class MergeSortUtil {

	private static void merge(Stock[] stocks, int l, int m, int r, int day) {
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
	public static void sort(Stock[] stocks, int l, int r, int day) {
		if (l < r) {
			// Find the middle point
			int m = l + (r - l) / 2;

			// Sort first and second halves
			sort(stocks, l, m, day);
			sort(stocks, m + 1, r, day);

			// Merge the sorted halves
			merge(stocks, l, m, r, day);
		}
	}

}
