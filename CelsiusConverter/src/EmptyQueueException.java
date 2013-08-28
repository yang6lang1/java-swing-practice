/*
 * File: EmptyQueueException.java
 * Author: AL
 * Student Number:
 * Lab:
 * Created on: June 1, 2012
 * Description: Exception thrown when the Queue is empty (dequeue, peek)
 */


// Class Description 
//   Exception that is thrown when there is insufficient
//   amount of money to carry on an operation such as withdraw

public class EmptyQueueException extends Exception 
{ 
    // Default constructor
    public EmptyQueueException( ) 
    {
    } // end of constructor
    
    // Parameterized constructor
    public EmptyQueueException( String msg )
    {
    	// Question: where is msg stored?
        super( msg );
        
    } // end of constructor
    
} // end of EmptyQueueException class 
