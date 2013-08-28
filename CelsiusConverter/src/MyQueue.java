/**
 *
 * @author AlexYang
 */
public class MyQueue implements MyQueueInterface {
//********************
// Array Version  
//********************
    final static int INITIAL_SIZE_OF_QUEUE = 1;
    
    private int front; // Current position of starting point in an circular array
    private int num; // Number of elements in the queue
    private int sizeOfArray;
    
    private int[] myArray; // Collection of elements in the Queue
    
    //Default constructor
    public MyQueue()
    {
        this(INITIAL_SIZE_OF_QUEUE);
        sizeOfArray = INITIAL_SIZE_OF_QUEUE;
    }// end default constructor
    
    //Parameterized constructor
    public MyQueue(int sizeOfArray)
    {
        myArray = new int[sizeOfArray];
        front = 0;
        num = 0;
    }// end parameterized constructor
    
    public void enqueue(int element) 
    {
        //Description: Adds an element to the queue
        // Need to consider the array size!
        if (num >= this.size())
        {
        //    sizeOfArray *= 2;
            int[] newArray;
            newArray = new int[sizeOfArray*2];
      //      System.arraycopy(myArray,0,newArray,0,sizeOfArray);
            for (int index = 0; index < sizeOfArray; index++)
            {
                newArray[index] = myArray[index];
            } // end of array expansion
            sizeOfArray *= 2;  // Update the size of array
            myArray = newArray;

        }
          myArray[(front+num)%this.ArraySize()] = element; // Insert the element into the corresponding position
          num++; // Update the number of elements in the Queue
    }
    
    
    public int dequeue() throws EmptyQueueException{
    //Description: Retrieves and removes an element from the queue
    //             (from the front of the queue). It also returns it.
    //Precondition: Queue is not empty
    //Exception: Throws an excception if queue is empty.(this exception
    //               must be called EmptyQueueException).
  
        int temp;
        if(num == 0)
        { throw  new EmptyQueueException( "This Queue is empty.");}
        else
        {
        temp = myArray[front];
        num--;  // Update the number of elements in the Queue
        front = (front+1)%this.ArraySize(); // Update the front position;
        }
        return temp;

    }
  
    public void dequeueAll(){// double check later
    //Description: Removes all elements from the queue.
    //Postcondition: size() returns 0
    
 //       front = (front + num)%this.size();
        num = 0; // I am testing 'num' at the beginning of dequeue and peek.
    }

    public int peek() throws EmptyQueueException {
    //Description: Retrieves an elements from the queue( from the front
    //             of the queue) and returns it.
    //Precondition: Queue is not empty
    //Postcondition: the queue is unchanged
    //Exception: Throws an exception if queue is empty(this exception must
    //               be called EmptyQueueException).
  
        if(num == 0)
        {throw  new EmptyQueueException( "This Queue is empty.");}

        return myArray[front];

    }
    public int ArraySize()
    {
        //Description: returns the size of array in order to be used in other methods
        // such as 'enqueue' and 'dequeue'
        return sizeOfArray;
    }
    public int size()
    {
    //Description: Returns the number of elements currently stored in the queue.
    //PostconditionL Returns 0 if empty otherwise returns the number of elements.
      //  return sizeOfArray;
        return num;
    }
    
    public String toString( )
	// Description: Method to output the element information.
	// Postcondition: A string containing all the element information is returned.
	{
		String theString = "";
		if ( num <= 0 )
			theString = "\nThere are no elements!";        
		else
			for ( int index = 0; index <num; index++ )
				theString += "\n" + myArray[ index ];    

		return theString;

	} // end of toString 

    
}
