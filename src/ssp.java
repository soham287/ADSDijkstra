import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class ssp {
	BinomialNode V;
	ArrayList<BinomialNode> verticesList;

	ssp(){

		verticesList = new ArrayList<BinomialNode>();
	}
	
	BinomialNode addVertices(int key,int index){
		V= new BinomialNode(key,index);
		return V;
	}
	
	public void singleSourcePaths(ArrayList<BinomialNode> Vertices,int sourceIndex){
		BinomialNode source = Vertices.get(sourceIndex);
		source.data=0;
		source.prevNode=null;// because it is source
		FibonacciHeap Pqueue = new FibonacciHeap();
		for(BinomialNode v: Vertices){
		Pqueue.insertNode(v);
		Pqueue.nodeCount++; //Buffer to count the number of nodes in the Heap
		}
		while(Pqueue.isEmpty()!=true){
	    BinomialNode u = Pqueue.deleteMin();
	    System.out.println("NEW MIN: "+u.data+"    INDEX:"+u.index);
		HashMap<Integer,Integer> adjList = u.Edge;
		if(!adjList.isEmpty()){
			System.out.println("");
			for(int v:adjList.keySet()){
			int weight = adjList.get(v);
			BinomialNode vnode=Vertices.get(v);
			relax(u,vnode,weight,Pqueue);
		}
		}
		}
		
	}
	public ArrayList<BinomialNode> shortestPath(int destinationIndex){
		 ArrayList<BinomialNode> path = new ArrayList<BinomialNode>();
			BinomialNode dest= verticesList.get(destinationIndex);
			while(dest.prevNode!=null){
				path.add(dest);
				dest=dest.prevNode;
			}
			path.add(dest);
		Collections.reverse(path);
		return path;
	}
	void relax(BinomialNode u, BinomialNode v, int w,FibonacciHeap q){
		if(v.data>(u.data+w)){
			q.decreaseKey(v,u.data+w);
			v.prevNode=u;
		}
	}

public static void main(String[] args) throws IOException {
ssp Graph= new ssp();
int numVertices=5000 ;
int edgecount=0;
for(int j=0; j<numVertices;j++){   // Inserting those number of nodes
	BinomialNode v= new BinomialNode(Integer.MAX_VALUE,j);
	Graph.verticesList.add(v);

}
try { 
	String sCurrentLine;
	BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Soham\\Desktop\\Input1.txt"));
	while ((sCurrentLine = br.readLine()) != null) {
	edgecount++;
	//System.out.println(sCurrentLine);
	char[] a= sCurrentLine.toCharArray();
	int count=0;
	String c= new String();
	while(a[count]!=' '){
		c=c+a[count];
		count++;
		
	}
	count++;
	c.trim();
	int u=Integer.parseInt(c);
	String d= new String();
	while(a[count]!=' '){
		d=d+a[count];
		count++;
		
	}
	count++;
	d.trim();
	int v=Integer.parseInt(d);

	String e= new String();
	while(count<a.length && a[count]!=' '){
		e=e+a[count];
		count++;
		
	}
	count++;
	e.trim();
	int w=Integer.parseInt(e);
	//System.out.println(u);
	Graph.verticesList.get(u).Edge.put(v,w);
	Graph.verticesList.get(v).Edge.put(u,w);
	sCurrentLine = br.readLine();
	}
	
	
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} 
		// TODO Auto-generated method stub
	    Scanner input = new Scanner(System.in);
		System.out.println("INPUT VERTICES");
		//int numVertices = input.nextInt();
		
		System.out.println("INPUT EDGES");
		//int numEdges =input.nextInt();
		int numEdges= 249750;
	/*	System.out.println("INPUT EDGE DETAILS");
		for (int i=0;i<numEdges;i++){
			int u =input.nextInt();
			int v =input.nextInt();
			int w =input.nextInt();
			System.out.println("NEXT SET");
			Graph.verticesList.get(u).Edge.put(v,w);
		}*/
	
		int destinationIndex=4999;
	
		Graph.singleSourcePaths(Graph.verticesList,0);
		ArrayList<BinomialNode> path = Graph.shortestPath(destinationIndex);
		 System.out.println("Distance = "+Graph.verticesList.get(destinationIndex).data);
		 while(!path.isEmpty()){
			System.out.print(path.remove(0).index+ "  ");
		}
			System.out.println("EDGE COUNT"+edgecount);	
	}

}
