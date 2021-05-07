/** Methods from the GraphInterface not made by us.
 * Taken from slides - Module 12 - graphs
 * Note: Our traversal methods are made by looking at the algorithm but trying to not
 * look at the implementation in slides or books 
 */
public class Graph<E> implements GraphInterface<E>{     
    private boolean[][] edges; // edges[i][j] is true if there is a vertex from i to j     
    private E[] labels; // labels[i] contains the label for vertex i  
     
    public Graph(int n) {         
        edges = new boolean[n][n]; // All values initially false         
        labels = (E[]) new Object[n]; // All values initially null     
    }
     
    // Accessor method to get the label of a vertex of this Graph      
    public E getLabel(int vertex) {
        return labels[vertex];     
    }
     
    // Test whether an edge exists    
    public boolean isEdge(int source, int target) {         
        return edges[source][target];     
    }
     
    // Add an edge     
    public void addEdge(int source, int target) {
        edges[source][target] = true;     
    }
    
    // Obtain a list of neighbors of a specified vertex of this Graph     
    public int[] neighbors(int vertex) {         
        int i;         
        int count = 0;
        int[] answer;         
        for ( i = 0; i < labels.length; i++) {             
            if (edges[vertex][i] == true){
                count++;
            }         
        }         
        answer = new int[count];         
        count = 0;         
        for (i = 0; i < labels.length; i++) {             
            if (edges[vertex][i] == true) {               
                answer[count++] = i;
            }         
        }         
        return answer;     
    }
     
    // Remove an edge    
    public void removeEdge(int source, int target) {         
        edges[source][target] = false;     
    }     
     
    // Change the label of a vertex of this Graph     
    public void setLabel(int vertex, E newLabel) {         
        labels[vertex] = newLabel;    
    }     
     
    // Accessor method to determine the number of vertices in this Graph     
    public int size() {
        return labels.length;     
    }

    /**
     * 
     */
    public String getDepthFirstTraversal(int origin){
        String traversalOrder = "";
        ArrayStack<E> vertexStack = new ArrayStack<>();
        char[] visitedLabels = new char[size()];
        boolean markedNeighbor = true;
        int labelsAdded = 0;
        boolean foundNeighbor = false;
        traversalOrder = traversalOrder + getLabel(origin);
        vertexStack.push(getLabel(origin));
        visitedLabels[labelsAdded] = (char) getLabel(origin);
        labelsAdded++;
        while(!vertexStack.isEmpty()){
            int topVertex = getIndex(vertexStack.peek());
            System.out.println(getLabel(topVertex));
            int[] neighborsArray = neighbors(topVertex);
            int currentNeighbors = 0;
            E nextNeighbor = getLabel(0); //This is only done because we got errors otherwise
            if(neighborsArray.length < 1){
                vertexStack.pop();
                System.out.println("Popped due to no neighbors!");
            }
            else{
                while(markedNeighbor == true && currentNeighbors < neighborsArray.length){
                    while(currentNeighbors < neighborsArray.length){
                        for(int j = 0; j < visitedLabels.length; j++){
                            foundNeighbor = false;
                            System.out.println(((char) getLabel(neighborsArray[currentNeighbors]) + " " + visitedLabels[j]));
                            if(((char) getLabel(neighborsArray[currentNeighbors]) == visitedLabels[j])){
                                System.out.println(getLabel(neighborsArray[currentNeighbors]));
                                foundNeighbor = true;
                                System.out.println("Neighbor was already marked!");
                                j = j + 1000;
                            }
                        }
                        System.out.println(foundNeighbor);
                        if(foundNeighbor == false){
                            nextNeighbor = getLabel(neighborsArray[currentNeighbors]);
                            markedNeighbor = false;
                            System.out.println("This neighbor has not been marked!" + nextNeighbor);
                        }
                        currentNeighbors++;
                    }
                }
                if(markedNeighbor == false){
                    visitedLabels[labelsAdded] = (char) nextNeighbor;
                    labelsAdded++;
                    traversalOrder = traversalOrder + (char) nextNeighbor;
                    vertexStack.push(nextNeighbor);
                    System.out.println("Added the visited neighbor!");
                    markedNeighbor = true;
                }
                else{
                    vertexStack.pop();
                    markedNeighbor = true;
                    System.out.println("Popped due to no unmarked neighbors!");
                }
            } 
        }
        System.out.println("Done!");
        return traversalOrder;
    }
    private int getIndex(E item){
        int index = -1;
        int counter = 0;
        boolean found = false;
        while(found != true && counter < size()){
            if(labels[counter] == item){
                index = counter;
                found = true;
            }
            counter++;
        }
        return index;
    }
}
    
