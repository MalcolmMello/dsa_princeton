import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private Node first, last;
	private int length = 0;
	
	private class Node
	{
		Item item;
		Node next;
	}
	
	// construct an empty deque
	public Deque()
	{
		length = 0;
	}
	
	// is the deque empty?
	public boolean isEmpty()
	{
		return first == null;
	}
	
	// return the number of items on the deque
	public int size()
	{	
		return length;
	}
	
	// add the item to the front
	public void addFirst(Item item)
	{
		if (item == null)
		{
			throw new IllegalArgumentException();
		}
		
		Node oldfirst = first;
		
		first = new Node();
		
		first.item = item;
		first.next = oldfirst;
		
		length++;
	}
	
	// add the item to the back
	public void addLast(Item item)
	{
		if (item == null)
		{
			throw new IllegalArgumentException();
		}
		
		Node oldlast = last;
		
		last = new Node();
		
		last.item = item;
		last.next = null;
		
		if (isEmpty()) first = last;
		else 		oldlast.next = last;
		
		length++;
	}
	
	// remove and return the item from the front
	public Item removeFirst()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		Node remove = first;
		first = first.next;
		length--;
		return remove.item;
	}
	
	// remove and return the item from the back
	public Item removeLast()
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		Node remove = last;
		last = null;
		length--;
		return remove.item;
	}
	
	// return an iterator over items in order from front to back
	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>
	{
		private Node current = first;
		
		public boolean hasNext() { return current != null; }
		public void remove()	 { throw new UnsupportedOperationException(); } 
		public Item next()
		{
		  if (!hasNext())
		  {
        throw new NoSuchElementException();
      }
      
			Item item = current.item;
			current	  = current.next;
			return item;
		}
	}
	
	public static void main(String[] args)
	{
		Deque<String> deque = new Deque<String>();
		
		System.out.println(deque);
		System.out.println(deque.isEmpty());
		System.out.println(deque.size());
		
		deque.addFirst("first");
		deque.addLast("last");
		
		Iterator<String> i = deque.iterator();
		
		while (i.hasNext())
		{
			String s = i.next();
			System.out.println(s);
		}
		
		deque.removeFirst();
		deque.removeLast();
		
		while (i.hasNext())
		{
			String s = i.next();
			System.out.println(s);
		}
		
		System.out.println(deque.size());
	}
}
