
/**
 * Heap is the implementation of priority queue
 * An array, visualized as a nearly complete binary tree
 * Priority Queue:
 * */
public class Heap{
	
	public static enum HeapType{
		Min, Max;
	}
	
	private HeapType type;
	
	public Heap(){
		this(HeapType.Min);
	}
	
	public Heap(HeapType type){
		this.type = type;
	}

	public HeapType getType() {
		return type;
	}

	public void setType(HeapType type) {
		this.type = type;
	}
}
