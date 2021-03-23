/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

class Edge
{	
	int dest,weight;
	Edge next;
	Edge( int dest, int weight, Edge next )
	{	this.dest = dest;
		this.weight = weight;
		this.next = next;
	}
	public String toString()
	{
		return "[" + dest + "," + weight + "]";
	}
}

public class Graph //throws Exception
{
	private final int NO_EDGE = -1; // all real edges are positive
	private Edge G[];              // will point to a 2D array to hold our graph data
	private Edge head;
	private int numEdges;
	public Graph( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
	}

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );
		int dimension = graphFile.nextInt();   	// THE OF OUR N x N GRAPH
		G = new Edge[dimension]; 		
		numEdges=0;
		while ( graphFile.hasNextInt() )
		{
			int index = graphFile.nextInt();
			int dest = graphFile.nextInt();
			int weight = graphFile.nextInt();
			addEdge(index, dest, weight);	
		}
		
	} // END readGraphFile
	
	public boolean contains( int key )
	{
		return search(key) != null;
	}
	public Edge search( int key )
	{
		
		Edge curr = head;
		while (curr !=null)
		{
			if (curr.dest == key)
				return curr;
			curr = curr.next;
		}
		
		return null;
	}


	private void addEdge( int ind, int dest, int weight )
	{
		head=G[ind];
		G[ind]= new Edge(dest,weight,head);
		++numEdges;
	}
	

	private int inDegree(int ind)
	{
		int inDegree=0;
		for(int i=0; i<G.length; i++)
		{
			Edge curr = G[i];
			while(curr!=null)
			{
				if(curr.dest==ind)
					inDegree++;
				curr = curr.next;
			}
			}	
		
		return inDegree;
	}

	private int outDegree(int node)
	{
		int outDegree=0;
		Edge curr = G[node];
		while(curr!=null)
		{ 	
			++outDegree;
			curr = curr.next;
		}
		return outDegree;
	}

	private int degree(int node)
	{
		return inDegree(node) + outDegree(node);
	}
	
	public int maxOutDegree()
	{
		int maxOutDegree = outDegree(0);
		for (int i = 0; i < G.length; i++)
		{
			if (outDegree(i) > maxOutDegree)
			{
				maxOutDegree = outDegree(i);
			}
		}
		return maxOutDegree;
	}

	public int maxInDegree()
	{
		int maxInDegree = inDegree(0);
		for (int i = 0; i < G.length; i++)
		{
			if (inDegree(i) > maxInDegree)
			{
				maxInDegree = inDegree(i);
			}
		}
		return maxInDegree;
	}

	public int minOutDegree()
	{
		int minOutDegree = outDegree(0);
		for (int i = 1; i < G.length; i++)
		{
			if (outDegree(i) < minOutDegree)
			{
				minOutDegree = outDegree(i);
			}
		}
		return minOutDegree;
	}	
	
	public int minInDegree()
	{
		int minInDegree = inDegree(0);
		for (int i = 1; i < G.length; i++)
		{
			if (inDegree(i) < minInDegree)
			{
				minInDegree = inDegree(i);
			}
		}
		return minInDegree;
	}	
	
	public int maxDegree()
	{
		int maxDegree = degree(0);
		for(int i = 0; i < G.length; i++)
		{
			if (degree(i) > maxDegree)
			{
				maxDegree = degree(i);
			}
		}
		return maxDegree;
	}

	public int minDegree()
	{
		int minDegree = degree(0);
		for(int i = 0; i < G.length; i++)
		{
			if (degree(i) < minDegree)
			{
				minDegree = degree(i);
			}
		}
		return minDegree;
	}
	
	public void removeEdge(int ind, int dest)
	{
		for(int i=0; i<G.length; i++)
		{
			//head = G[i];
			Edge curr = G[i];
			if(i==ind)
			{
				if(curr==null)
					return;
				if(curr.next==null)
					G[i]=null;
				while(curr.next != null)
				{
					if(curr.next.dest==dest)
						curr.next = curr.next.next; 
				}	
				
			}	
		}
		try
		{
			if(ind < 0 || dest < 0 || ind >= G.length || dest >= G.length)
			{
				throw new Exception("Non Existent Edge Exception: " + "removeEdge("+ind+","+dest+")");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.exit(0);
		}

		
	}


	public String toString()
	{	
		String the2String = "";
		for (int i=0 ; i < G.length ;++i )
		{		Edge curr = G[i];
				the2String += i+":";
				while(curr!=null)
				{
					the2String+= " -> " +curr.toString() + " ";
					curr = curr.next;
				}	
				the2String += "\n";		
		}
		return the2String;
	} // END TOSTRING

} //EOF

