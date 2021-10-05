import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@SuppressWarnings("unchecked")
public class HashGraph extends Graph{
    
    int p; // Big Prime (for PolyHash())
    int x; // Small number (for PolyHash())
    
    // This is complete, no need to edit
    public HashGraph(int cap, int p, int x){
        super(cap); // Forward the parameter to Graph's constructor
        this.p = p;
        this.x = x;
    }
    
    
    // Check the slide for the pseudocode
    // If you want to access a char of string s at index i, use s.charAt(i)
    // If you want to get the ASCII code of char c, use (int)c
    public static int polyHash(String s, int p, int x) {
        int hash = 0;
        for(int i = s.length()-1 ; i >= 0; --i){ //Sigma i=|S|-1 -> 0 : (S[i]*x^i) mod p   // from slide: Topic7-2, page36-37
            hash = (hash*x + ((int)s.charAt(i)))%p;
        }
        return hash;
    }
    
    // This function has 2 roles
    // [1] Find an empty space to push the string object
    // [2] Find location of the string
    public int getListIndex(String s) {
        // The algorithm is followed
        // 1. Hash the string and get the index
        int hashedIndex = polyHash(s, p, x);
        hashedIndex %= cap;

        // 2. If the arr[index] is empty, then return index
        // 3. If the arr[index] equals the string, then also return index
        if(vertexList[hashedIndex] == null || vertexList[hashedIndex].strKey == s){
            return hashedIndex;
        }

        // 4. Perform the quadratic probing and repeat 2.-3. until found the location
        int newIndex = hashedIndex;
        for(int i = 0 ; i < cap; ++i){ // from slide: Topic7-3, page85
            newIndex = (newIndex+i)%cap;
            if(vertexList[newIndex] == null || vertexList[newIndex].strKey == s){
                break;
            }
        }
        return newIndex;
    }
    
    
    public void addVertex(String key){
        if (size==cap){
            System.out.println("Vertex list is full. You need to recreate the Graph");
            return;
        }
        
        // Map the String key to the array index (use getListIndex())

        // Create Vertex object and the LinkedList object
        Vertex v = new Vertex(key);
        LinkedList<Integer> list = new LinkedList<Integer>();
        // Add these objects to the corresponding arrays
        int index = getListIndex(key);
        if(vertexList[index] == null){
            vertexList[index] = v;
            adjacencyList[index] = list;
            // finally, size++;
            size++;
        }
    }
    
    public void addEdge(String source, String destination){
        // Map String's source and destination (city name) to Integer's u, v (array index)
        // You may call super.addEdge(u, v); for simpler implementation
        super.addEdge(getListIndex(source), getListIndex(destination));
    }
    

    public void BFS(Vertex s){
        // Set all Vertex.dist to Infinity (Use Integer.MAX_VALUE to represent Infinity)
        for(Vertex v : vertexList){
            if(v != null){
                v.dist = Integer.MAX_VALUE;
            }
        }
        // Set dist of the start vertex (s) to 0
        s.dist = 0;
        // Push the start vertex to an empty queue
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(s);

        while(!q.isEmpty()){
            // [*] Check if the queue is not empty

            // Pop queue and get the current vertex
            Vertex curr = q.poll();
            int currIndex = getListIndex(curr.strKey);
            // Extract the list of all vertices that are connected to current vertex
            LinkedList<Integer> list = adjacencyList[currIndex];
            // Traverse all the list 
            for(int v : list){
                // check if the dist value of anyone are still infinity or not
                if(vertexList[v].dist == Integer.MAX_VALUE){
                    // If yes,  set push that vertex into the queue
                    q.add(vertexList[v]);
                    // set the dist variable of that vertex = curr.dist + 1
                    vertexList[v].dist = curr.dist+1;
                    // set the prev variable of that vertex to the current vertex
                    vertexList[v].prev = curr;
                }
                
            }
            
            // Repeat [*]
        }
        
    }

    
    public Stack<Vertex> getShortestPathList(Vertex S, Vertex U){
        
        // Create a stack
        Stack<Vertex> stk = new Stack<Vertex>();
        // Start from city U
        Vertex curr = U;
        while(curr != S){
            // [*] push the current city into the stack
            stk.add(curr);
            // Go back one city using U.prev
            curr = curr.prev;
            // Repeat [*] until you reach the city S
        }
        // return the stack
        return stk; 
    }
    
    public void printShortestPath(String s_str, String u_str){
        
        // Map city names to index numbers
        int s_index = getListIndex(s_str);
        int u_index = getListIndex(u_str);
        // Get vertices from the vertexList
        Vertex s = vertexList[s_index];
        Vertex u = vertexList[u_index];
        // Run BFS() at the starting city
        BFS(s);
        // Get shortestPathList(starting city, ending city)
        Stack<Vertex> stk = getShortestPathList(s, u);
        // Traverse all the stack and print the city name
        System.out.print(s_str + " -> ");
        while(!stk.empty()){
            System.out.print(stk.pop().strKey);
            System.out.print(" -> ");
        }System.out.println("");
    }
    
    // This function is complete, no need to edit
    public void showVertexList(){
        for (int i=0; i<vertexList.length; i++){
            if (vertexList[i]!=null)
                System.out.println("vertexList["+i+"] contains "+vertexList[i].strKey);
            else
                System.out.println("vertexList["+i+"] null");
        }
    }


    // This is editable test case, but no need to edit
    public static HashGraph constructGraph(){
        
        int p = 101111; // Big Prime (Hash key1)
        int x = 101;    // Small number (Hash key2)
        HashGraph graph = new HashGraph(32, p, x); 
        
        String[] cities = new String[]{"Dublin", "Edinburgh", "Manchester", 
            "Copenhagen", "Lisbon", "London", "Berlin", "Prague", "Madrid", 
            "Paris", "Vienna", "Budapest", "Geneva", "Milan", "Zurich", "Rome"};
        for (int i=0; i<16; i++){
            graph.addVertex(cities[i]);
        }
        
        return graph;
    }
    
    // This is editable test case, but no need to edit
    public static HashGraph connectEdges(HashGraph graph){
        graph.addEdge("Dublin", "Edinburgh");
        graph.addEdge("Dublin", "London");                
        graph.addEdge("Dublin", "Lisbon");
        graph.addEdge("Edinburgh", "Manchester");
        graph.addEdge("Manchester", "London");
        graph.addEdge("Manchester", "Copenhagen");
        graph.addEdge("Copenhagen", "Berlin");
        graph.addEdge("Lisbon", "Madrid");
        graph.addEdge("London", "Paris");
        graph.addEdge("Berlin", "Prague");
        graph.addEdge("Berlin", "Vienna");
        graph.addEdge("Berlin", "Paris");
        graph.addEdge("Prague", "Zurich");
        graph.addEdge("Madrid", "Paris");
        graph.addEdge("Madrid", "Milan");
        graph.addEdge("Madrid", "Geneva");
        graph.addEdge("Vienna", "Zurich");
        graph.addEdge("Budapest", "Rome");
        graph.addEdge("Milan", "Zurich");
        graph.addEdge("Zurich", "Rome");
        return graph;
    }
}
