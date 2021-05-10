public class bfsTest {
    public static void main(String args[]) {
        Graph<Character> test = new Graph(3);

       test.setLabel(0, 'a');
       test.setLabel(1, 'b');
       test.setLabel(2, 'c');
        test.addEdge(0, 1);
        test.addEdge(0, 2);
        test.addEdge(1, 2);
        test.addEdge(2, 0);
 
        test.getBreadthFirstTraversal(1);
    }
}
