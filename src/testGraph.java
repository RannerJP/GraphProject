import java.io.FileNotFoundException;

public class testGraph{
    public static void main(String[] args) throws FileNotFoundException{
        Graph<Character> test = new Graph<>(9);
        test.autoSetLabels();
        test.importGraph("TestGraph.csv");
        String result = test.getDepthFirstTraversal(0);
        System.out.println(result);
    }
}
