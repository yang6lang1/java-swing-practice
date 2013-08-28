/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AlexYang
 */
public class EmptyEventListException extends Exception 
{ 
    // Default constructor
    public EmptyEventListException( ) 
    {
    } // end of constructor
    
    // Parameterized constructor
    public EmptyEventListException( String msg )
    {
    	// Question: where is msg stored?
        super( msg );
        
    } // end of constructor
    
} // end of EmptyQueueException class 
