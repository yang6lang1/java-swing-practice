/**
 * Filename: MyQueueInterface.java
 * Author: AL
 * Student Number:
 * Lab:
 * Date of creation: June 2012
 * Description: Java interface of a Queue
 */



public interface MyQueueInterface {
	
	public void enqueue( int element );
	// Description: Adds an element to the queue (at the back of the queue).

	public int dequeue( ) throws EmptyQueueException;
	// Description: Retrieves and removes an element from the queue 
	//              (from the front of the queue). It also returns it.
	// Precondition: Queue is not empty.
	// Exception: Throws an EmptyQueueException if queue is empty.

	public int peek( ) throws EmptyQueueException;	
	// Description: Retrieves an element from the queue (from the front of the queue) and returns it.
	// Precondition: Queue is not empty.
	// Postcondition: the queue is unchanged.
	// Exception: Throws an EmptyQueueException if queue is empty.

	public void dequeueAll( );
	// Description: Removes all elements from the queue.
	// Postcondition: size( ) returns 0.

	public int size( );
	// Description: Returns the number of elements currently stored in the queue.
	// Postcondition: Returns 0 if empty otherwise returns the number of elements.

} // end of MyQueueInterface
