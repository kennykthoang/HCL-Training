package fixbugs;

import java.util.ArrayList;
import java.util.Random;

public class QuickSort{
	
	public void sort(ArrayList<Integer> arr)
	{
		quickSort(arr, 0,arr.size() - 1);
	}
	
	public static void quickSort(ArrayList<Integer> arr, int low, int high)
	{
		int i = low, j = high;
		Random rand = new Random();
        int temp, pivot = arr.get(rand.nextInt((high+1)-low) + low);;
        
        while (i <= j) 
        {
            while (arr.get(i) < pivot)
                i++;
            while (arr.get(j) > pivot)
                j--;
            if (i <= j) 
            {
                temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
 
                i++;
                j--;
            }
        }
 
        if (low < j)
            quickSort(arr, low, j);
        if (i < high)
            quickSort(arr, i, high);
	}// end quickSort method
} // end QuickSort class