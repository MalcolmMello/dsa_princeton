import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] q;
	private int n;
	// construct an empty randomized queue
    public RandomizedQueue()
    {
    	q = (Item[]) new Object[2];
    	n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
    	return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() 
    {
    	return n;
    }

    // add the item
    public void enqueue(Item item)
    {
    	if (item == null) throw new NullPointerException();
    	if (n == q.length) resize(2 * q.length);
    	if (n == 0)
    	{
    		q[n++] = item;
    		return;
    	}
    	int randomIndex = StdRandom.uniformInt(n);
    	Item temp = q[randomIndex];
    	q[randomIndex] = item;
    	q[n++] = temp;
    }

    // remove and return a random item
    public Item dequeue()
    {
    	if (isEmpty()) throw new NoSuchElementException();
    	if (n == q.length / 4) resize(q.length / 2);
    	int randomIndex = StdRandom.uniformInt(n);
    	Item item = q[randomIndex];
    	q[randomIndex] = q[--n];
    	q[n] = null; // prevent loitering
    	return item;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
    	if (isEmpty()) throw new NoSuchElementException();
    	return q[StdRandom.uniformInt(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
    	return new ArrayIterator();
    }
    
    private class ArrayIterator implements Iterator<Item>
    {
    	private int i;
    	private int[] randomIndices;
    	
    	public ArrayIterator()
    	{
    		i = 0;
    		randomIndices = new int[n];
    		
    		for (int j = 0; j < n; j++)
    		{
    			randomIndices[j] = j;
    		}
    		
    		StdRandom.shuffle(randomIndices);  // rearranges in random order
    	}
    	
    	public boolean hasNext()
    	{
    		return i < n;
    	}
    	
    	public Item next()
    	{
    		if (!hasNext()) throw new NoSuchElementException();
    		return q[randomIndices[i++]];
    	}
    	
    	public void remove()
    	{
    		throw new UnsupportedOperationException();
    	}
    }
    private void resize(int capacity)
    {
    	Item[] temp = (Item[]) new Object[capacity];
    	for (int i = 0; i < n; i++)
    	{
    		temp[i] = q[i]; 
    	}
    	q = temp;
    }

    // unit testing (required)
    public static void main(String[] args)
    {
    	RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
    	
    	for (int i = 0; i < 10; i++)
    	{
    		queue.enqueue(i);
    	}
    	
    	System.out.println(queue.size());
    	
    	for (Integer i : queue)
    	{
    		System.out.println(i);
    	}
    	
    	System.out.println(queue.sample());
    	System.out.println("dequeue");
    	while (!queue.isEmpty()) System.out.println(queue.dequeue());
    	
    	System.out.println(queue.size());
    }
}
