import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Methods from the GraphInterface not made by us.
 * Taken from slides - Module 12 - graphs
 * Note: Our traversal methods are made by looking at the algorithm but trying to not
 * look at the implementation in slides or books 
 */
public class Graph<E> implements GraphInterface<E>, GraphRepresentationInterface{     
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
  
    /** Goes through the graph using Breath Traversal
    * @param origin is the poisition of the starting label's array value
    * @return traversalOrder, the order the vertexes were visited
    */
    public String getBreadthFirstTraversal(int origin){
        boolean[] visited = new boolean[size()];
        for(int x = 0; x< size(); x++)
            visited[x] = false;
        String traversalOrder = "";
        traversalOrder = traversalOrder + getLabel(origin);
        Queue<E> vertexQueue = new Queue<>();
        vertexQueue.enqueue(getLabel(origin));
        while(!vertexQueue.isEmpty()){
            int currentVertex = getIndex(vertexQueue.dequeue()); 
            int[] neighbors = neighbors(currentVertex);
            int i = 0;        
            while(i <= neighbors.length -1){ 
                int nextNeighbor = neighbors[i];
                if(!visited[nextNeighbor]){
                    visited[nextNeighbor] = true;
                    vertexQueue.enqueue(getLabel(nextNeighbor));
                    traversalOrder = traversalOrder + String.valueOf(getLabel(nextNeighbor));
                }
                i++;
            }
        }
        return traversalOrder;
    }

    /** Goes through the graph using Depth Traversal
    * @param origin is the poisition of the starting label's array value
    * @return traversalOrder, the order the vertexes were visited
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
            int[] neighborsArray = neighbors(topVertex);
            int currentNeighbors = 0;
            E nextNeighbor = getLabel(0); //This is only done because we got errors otherwise
            if(neighborsArray.length < 1){
                vertexStack.pop();
            }
            else{
                while(markedNeighbor == true && currentNeighbors < neighborsArray.length){
                    while(currentNeighbors < neighborsArray.length){
                        for(int j = 0; j < visitedLabels.length; j++){
                            foundNeighbor = false;
                            if(((char) getLabel(neighborsArray[currentNeighbors]) == visitedLabels[j])){
                                foundNeighbor = true;
                                j = j + 1000;
                            }
                        }
                        if(foundNeighbor == false){
                            nextNeighbor = getLabel(neighborsArray[currentNeighbors]);
                            markedNeighbor = false;
                        }
                        currentNeighbors++;
                    }
                }
                if(markedNeighbor == false){
                    visitedLabels[labelsAdded] = (char) nextNeighbor;
                    labelsAdded++;
                    traversalOrder = traversalOrder + (char) nextNeighbor;
                    vertexStack.push(nextNeighbor);
                    markedNeighbor = true;
                }
                else{
                    vertexStack.pop();
                    markedNeighbor = true;
                }
            } 
        }
        return traversalOrder;
    }
    
    /** This function will take a graph from a CSV file and implement it.
     * NOTES: File Must be CSV and only include True/False
     * DO NOT INCLUDE THE LABEL NAMES 
     * An example has been included in the GitHub in csv and excel format
     * @param fileName is the name of the file trying to be read
    */
    public void importGraph(String fileName) throws FileNotFoundException{
        File inputFile = new File(fileName);
        if(!inputFile.exists()){
            throw new FileNotFoundException();
        }
        Scanner fileReader = new Scanner(inputFile);
        String fileData = "";
        String[][] matrixData = new String[size()][size()];
        int line = 0;
        while(fileReader.hasNextLine()){
            fileData = fileReader.nextLine();
            String[] currentLineValues = fileData.split(",");
            for(int i = 0; i < currentLineValues.length; i++){
                matrixData[line][i] = currentLineValues[i];
            }
            line++;
        }
        fileReader.close();
        for(int i = 0; i < size(); i++){
            for(int j = 0; j < size(); j++){
                String value = matrixData[i][j];
                if(value.length() == 4){
                    addEdge(i, j);
                }
            }
        }
    }
    
    /** Will auto set labels with 0=a, 1=b etc, for the declared size of the graph 
     * NOTE: ONLY WORKS WITH GRAPHS SMALLER THAN 27!!
    */
    public void autoSetLabels(){
        Character[] alphabet = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for(int i = 0; i < size(); i++){
            setLabel(i, (E) alphabet[i]);
        }
    }

    /** Gets the index of an item given the label of the item
    * @param item is the generic label
    * @return the index of the given label item
    */
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
    
