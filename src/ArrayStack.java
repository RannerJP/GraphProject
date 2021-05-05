import java.util.Arrays;
import java.util.EmptyStackException;

/**
    A class of stacks whose entries are stored in an array.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
public final class ArrayStack<T> implements StackInterface<T>
{
	private T[] stack;    // Array of stack entries
	private int topIndex; // Index of top entry
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
  
   public ArrayStack()
   {
      this(DEFAULT_CAPACITY);
   } // end default constructor
  
   public ArrayStack(int initialCapacity)
   {
      integrityOK = false;
      checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
		topIndex = -1;
      integrityOK = true;
  } // end constructor
  
/**Adds a new entry to the stack*/
    public void push(T newEntry){
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }//end push

    /** removes the top entry
     * @return the top entry */
    public T pop(){
        checkIntegrity();
        if(isEmpty()){
            throw new EmptyStackException();
        }
        else{
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        }//end if
    }//end pop

    /** Checks the top value and returns it*/
    public T peek(){
        checkIntegrity();
        if (isEmpty()){
            throw new EmptyStackException();
        }
        else{
            return stack[topIndex];
        }//end if-else
    }//end peek

    /** Checks if the stack is empty */
    public boolean isEmpty(){
        return topIndex < 0;
    }//end isEmpty

    /**Clears the array of entries */
    public void clear(){
        checkIntegrity();

        //Remove references to the objects in the stack,
        //but do not deallocate the array
        while (topIndex > -1 ){
            stack[topIndex] = null;
            topIndex--;
        }//end while
        //Assertion: topIndex is -1
    }
        //Throws an exception if this object is not initialzed.
    private void checkIntegrity(){
        if(!integrityOK)
            throw new SecurityException("ArrayBag object is corrupt.");
    }// end checkIntegrity

    /** Makes sure that our array can fit the new item, if not, resizes the array */
    private void ensureCapacity(){
        if(topIndex >= stack.length - 1){ // if array is full, double its size
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }//end if
    }// end ensureCapacity

    /**Throws an expection if the client requests a capacity that is too large 
     * @param capacity is the size trying to be made */
    private void checkCapacity(int capacity){
        if(capacity>MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose" +
                                            "capacity exceeds allowed" +
                                            "maxiumum of" + MAX_CAPACITY); 
    }//end clear

} // end ArrayStack
