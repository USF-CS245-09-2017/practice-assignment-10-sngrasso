import java.util.*;

public class GraphImplementation implements Graph {
    private int V;
    private int[][] adjMatrix;
    private LinkedList<Integer> adj[];

    /*Constructor*/
    public GraphImplementation(int vertices){
        V=vertices;
        adjMatrix = new int[vertices][vertices];
        adj = new LinkedList[V];

        //fill the new linked list with zeros
        for (int i = 0; i < V; ++i){
            adj[i] = new LinkedList();
        }
    }

    /* add edge between two vertices */
    public void addEdge(int v1, int v2){
        //weight is just set to a 1
        //v1 is source and v2 is target
        adjMatrix[v1][v2] = 1;

        adj[v1].add(v2);
    }

    /* part of topological sort */
    public void topologicalSortUtil(int v, boolean visited[], Stack stack) {

        // Mark the current node as visited.
        visited[v] = true;
        Integer i;

        // call recursive for all the vertices adjacent to this
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext())
        {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        // Push current vertex to stack which stores result
        stack.push(new Integer(v));

    }

    /*Topological Sorting*/
    public List<Integer> topologicalSort(){

        //creating arrays and objects to store
        Stack stack = new Stack();
        List<Integer> returning = new ArrayList<>();

        boolean visited[] = new boolean[V];

        //set array to all false
        for (int i = 0; i < V; i++){
            visited[i] = false;
        }

        for (int i = 0; i < V; i++){
            if (visited[i] == false){
                topologicalSortUtil(i, visited, stack);
            }
        }

        //printing but also putting stuff in

        while (stack.empty()==false){
            int idk = (int) stack.pop();
            returning.add(idk);
        }

        return returning;
    }

    /* finds the neighbors */
    public int[] neighbors(int vertex){
        //put the variables into here
        int[] neigh = new int[adj[vertex].size()];

        for(int y = 0; y < adj[vertex].size() ; y++){
            neigh[y] = adj[vertex].get(y);
        }

        //return the list of neighbors

        return neigh;
    }

}
