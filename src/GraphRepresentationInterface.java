import java.io.FileNotFoundException;

public interface GraphRepresentationInterface {
    /** This function will take a graph from a CSV file and implement it.
     * NOTES: File Must be CSV and only include True/False
     * DO NOT INCLUDE THE LABEL NAMES 
     * An example has been included in the GitHub in csv and excel format
     * @param fileName is the name of the file trying to be read
    */
    public void importGraph(String fileName) throws FileNotFoundException;

    /** Will auto set labels with 0=a, 1=b etc, for the declared size of the graph 
     * NOTE: ONLY WORKS WITH GRAPHS SMALLER THAN 27!!
    */
    public void autoSetLabels();

}
