/**
 * 
 * 
 * */
public class Sorting {
	
	public static void main(String[] args) {
		int size = 10000;	
		Sorting newSorting = new Sorting();

		int[] arrayOne = new int[size];
		//int[] arrayTwo = new int[size];
		for(int i = 0;i<size;i++){
			arrayOne[i]= size-i;
		}

		long start = System.currentTimeMillis();
		//arrayOne = newSorting.normalInsertionSort(arrayOne);
		//arrayOne = newSorting.binaryInsertionSort(arrayOne);
		long end = System.currentTimeMillis();
		System.out.println("Insertion sort: "+ (end-start)+" ms");
		
		for(int i = 0;i<size;i++){
			arrayOne[i]= size-i;
		}
		
		start = System.currentTimeMillis();
		arrayOne = newSorting.mergeSort(arrayOne,0,arrayOne.length-1);
		end = System.currentTimeMillis();
		System.out.println("Merge sort: "+(end-start)+" ms");
		
		for(int i = 0;i<size;i++){
			arrayOne[i]= size-i;
		}
		
		start = System.currentTimeMillis();
		arrayOne = newSorting.heapSort(arrayOne);
		end = System.currentTimeMillis();
		System.out.println("Heap sort: "+(end-start)+" ms");
		
		for(int i = 0;i<arrayOne.length;i++){
			//System.out.println(arrayOne[i]+" ");
		}

	}

	/**
	 * O(logn) complexity for minHeapify
	 * It assumes the leaves are min heaps already
	 * sort the array into increasing order
	 * ***array based***
	 */
	public int[] heapSort(int[] array){
		int[] sortedArray=new int[array.length];
		int size = array.length,temp =0;
		
		//1. build min heap from unsorted array
		this.buildMinHeap(array);
		
		for(int i = size;i>0;i--){
			//2. find min value at array[0]
			sortedArray[array.length-size]=array[0];
			
			//3. swap element array[size-1] with array[0], now min value is at the end of array
			temp = array[0];
			array[0] = array[size-1];
			array[size-1] = temp;
			
			//4. discard array[size-1] by decrementing the heap size
			size--;
			
			//5. new root may violate min heap but its children are min heaps
			this.minHeapify(array, 0,size);
		}

		
		return sortedArray;
		//return sortedArray;
	}
	
	/**
	 * O(logn) complexity for minHeapify
	 * It assumes the leaves are min heaps already
	 * sort the array into increasing order
	 * this is implementation #1
	 * implementation #2 can be found here:
	 * http://ocw.mit.edu/courses/electrical-engineering-and-computer-science/
	 * 6-006-introduction-to-algorithms-fall-2011/lecture-videos/MIT6_006F11_lec04.pdf
	 * ***array based***
	 */
	private void minHeapify(int[] array,int root, int sizeOfArray){
		int left,right; //the roots of left and right subtree
		if((2* root+1)>sizeOfArray-1){// if the root is the leaf, do nothing	
			//System.out.println(root+" is a leaf");
			return;
		}
		left = array[2*root+1];
		if((2*root+2)>sizeOfArray-1){
			if(array[root]>left){
				array[2*root+1]= array[root];
				array[root]=left;
				minHeapify(array,2*root+1,sizeOfArray);
			}
		}else{
			right = array[2*root+2];
			if(array[root]>left || array[root]>right){
				if(left>right){
					array[2*root+2]=array[root];
					array[root]=right;
					minHeapify(array,2*root+2,sizeOfArray);
				}else{
					array[2*root+1]= array[root];
					array[root]=left;
					minHeapify(array,2*root+1,sizeOfArray);
				}
			}
		}

		return;
	}
	
	
	/**
	 * O(n) complexity for buildMinHeap
	 * Build a min heap out of an unsorted array
	 * */
	private void buildMinHeap(int[] array){
		for(int i =array.length/2 -1; i>=0;i--){
			minHeapify(array,i,array.length);
		}
	}
	
	/**
	 * O(nlogn) complexity for merge sort
	 * */
	public int[] mergeSort(int[] array, int front, int end){
		int mid = (front+end)/2;
		
		int[] left=new int[mid-front+1],right=new int[end-mid];
		int[] buffer;
		
		if((end-front)>1){//the array has more than 2 elements
			left=mergeSort(array,front,mid);
			right = mergeSort(array,mid+1,end);
			
		}else if((end-front)==1){//the array has exactly 2 elements
			//increasing order
			if(array[front]>array[end]){
				left[0]= array[end];
				right[0]=array[front];
			}else{
				left[0]= array[front];
				right[0]=array[end];
			}

		}else if(front == end){//the array has exactly 1 element
			//this is the number!
			left[0]=array[front];
			right = null;
		}
		
		//merge subroutine: 2-finger algorithm
		int lp=0, rp=0; //left array and right array pointers
		int[] mergedArray= new int[end-front+1];
		if(right == null){
			return left;
		}
		
		while(lp<left.length || rp<right.length){
			if(left[lp]>right[rp]){
				mergedArray[lp+rp]=right[rp];
				rp++;
				if(rp>=right.length){
					while(lp<left.length){
						mergedArray[lp+rp]=left[lp];
						lp++;
					}
				}
			}else{
				mergedArray[lp+rp]=left[lp];
				lp++;
				if(lp>=left.length){
					while(rp<right.length){
						mergedArray[lp+rp]=right[rp];
						rp++;
					}
				}
			}
			
		}

		return mergedArray;
	}
	
	
	/**
	 * O(n^2) complexity for normal insertion sort
	 * */
	public int[] normalInsertionSort(int[] array){
		int key = 1, temp = 0;
		for(key = 1; key< array.length;key++){
			for(int i =key-1; i>=0;i--){
				if(array[i+1]<array[i]){
					temp = array[i+1];
					array[i+1] = array[i];
					array[i]= temp;
				}
			}
		}
		return array;
	}
	
	/**
	 * binary insertion sort:
	 * O(nlogn) complexity for comparison
	 * O(n^2) complexity for swaps
	 * */
	public int[] binaryInsertionSort(int[] array){
		int key = 1, temp = 0;
		int position=-1;
		for(key = 1; key< array.length;key++){
		
			try{
				position=this.binarySearch(array, array[key],0,key-1);
			}catch(Exception e){
				e.printStackTrace();
			}
			for(int i =key-1; i>=position-1;i--){
				if(array[i+1]<array[i]){
					temp = array[i+1];
					array[i+1] = array[i];
					array[i]= temp;
				}	
			
			}
		}

		return array;
	}
	
	public int binarySearch(int[] array, int target,int front,int end) throws Exception{
		int position=-1;
		if(array == null){
			System.out.println("221323");
			throw new Exception("No elements in array");
		}
		int mid=(front+end)/2;
		if(front == end){
			position=front+1;
			return position;
		}
		
		while(front!=end){
			mid =(front+end)/2;
			if(array[mid] < target){
				front=mid;
			}else if(array[mid] > target){
				end=mid;
			}else{
				position=mid+1;
				return position;
			}
			
			if(front==end){
				position = front+1;
				return position;
			}
		}
	
		return position;
	}
}
