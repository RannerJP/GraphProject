public class testGraph{
    public static void main(String[] args) {
        Graph<Character> test = new Graph<>(9);
        test.setLabel(0, 'a');
        test.setLabel(1, 'b');
        test.setLabel(2, 'c');
        test.setLabel(3, 'd');
        test.setLabel(4, 'e');
        test.setLabel(5, 'f');
        test.setLabel(6, 'g');
        test.setLabel(7, 'h');
        test.setLabel(8, 'i');
        test.addEdge(0, 3);
        test.addEdge(0, 4);
        test.addEdge(0, 2);
        test.addEdge(0, 5);
        test.addEdge(0, 6);
        test.addEdge(0, 7);
        test.addEdge(0, 8);
        test.addEdge(1, 4);
        test.addEdge(2, 1);
        test.addEdge(3, 6);
        test.addEdge(4, 5);
        test.addEdge(4, 7);
        test.addEdge(5, 2);
        test.addEdge(5, 7);
        test.addEdge(6, 7);
        test.addEdge(7, 8);
        test.addEdge(8, 5);
        String result = test.getDepthFirstTraversal(0);
        System.out.println(result);
    }
}
