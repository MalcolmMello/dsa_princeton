package sorting;

public class SelectionSort {

	public static void sort(Comparable[] a) 
	{
		int N = a.length;
		for(int i = 0; i < N; i++)
		{
			int min = i;
			for(int j = i+1; j< N; j++)
			{
				if(less(a[j], a[min]))
				{
					min = j;
				}
			}
			exch(a, i, min);
		}
	}
	
	private static boolean less(Comparable v, Comparable w)
	{
		return v.compareTo(w) < 0;
	}
	
	private static void exch(Comparable[] a, int i, int min)
	{
		Comparable swap = a[i];
		a[i] = a[min];
		a[min] = swap;
	}
}
