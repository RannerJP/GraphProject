import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class testGraph{
    
    public static void main(String[] args) throws FileNotFoundException{
        Result result = JUnitCore.runClasses(testGraph.class);
        for(org.junit.runner.notification.Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

    @Test
    /**Tests the getDepthFirstTraversal method*/
    public void depthTest() throws FileNotFoundException{
        Graph<Character> test = new Graph<>(9);
        test.autoSetLabels();
        test.importGraph("TestGraph.csv");
        String result = test.getDepthFirstTraversal(0);
        assertEquals("aehifcbdg", result);
        System.out.println(result);
    }

    @Test
    /**Tests the getBreadthFirstTraversal method*/
    public void breadthTest() throws FileNotFoundException{
        Graph<Character> test = new Graph<>(9);
        test.autoSetLabels();
        test.importGraph("TestGraph.csv");
        String result = test.getBreadthFirstTraversal(0);
        assertEquals("abdegfhci", result);
        System.out.println(result);
    }
}

