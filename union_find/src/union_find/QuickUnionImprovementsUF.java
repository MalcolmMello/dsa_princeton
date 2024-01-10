package union_find;

public class QuickUnionImprovementsUF {
	private int[] id;
	private int[] size;
	
	public QuickUnionImprovementsUF(int N)
	{
		id = new int[N];
		size = new int[N];
		
		for(int i = 0; i < N; i++)
		{
			id[i] = i;
			size[i] = 1;
		}
	}
	
	private int root(int element)
	{
		while(element != id[element])
		{
			id[element] = id[id[element]];
			element = id[element];
		};
		return element;
	}
	
	public boolean connected(int p, int q)
	{
		return root(p) == root(q);
	}
	
	public void union(int p, int q)
	{
		int i = root(p);
		int j = root(q);
		if(i == j) return;
		
		if(size[i] < size[j]) { id[i] = j; size[j] += size[i]; }
		else			  { id[j] = i; size[i] += size[j]; }
	}
}
