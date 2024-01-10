package generics;

public class StackArray<T> {
	private T[] s;
	private int N;
	
	public StackArray(int capacity)
	{
		s = (T[]) new Object[capacity];
	}
	
	public boolean isEmpty()
	{
		return N == 0;
	}
	
	public void push(T item)
	{
		if(N == s.length) resize(2 * s.length);
		s[N++] = item;
	}
	
	public T pop()
	{
		T item = s[N--];
		s[N] = null;
		if(N > 0 && N == s.length/4) resize(s.length/2);
		return item;
	}
	
	private void resize(int capacity)
	{
		T[] copy = (T[]) new Object[capacity];
		for(int i = 0; i < N; i++)
		{
			copy[i] = s[i];
		}
		s = copy;
	}
}
