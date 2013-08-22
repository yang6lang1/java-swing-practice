/**
 * 
 * 
 * */
public class Sorting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int temp = 0,size = 100000;
		//int temp = 0,size = 11;
		int[] arrayOne = new int[size];
		Sorting newSorting = new Sorting();

		for(int i = 0;i<size;i++){
			arrayOne[i]= size-i;
		}

		int key = 1; // the pointer to the number to be swaped, start with the second element
		
		/*
		//O(n^2) complexity for normal insertion sort
		long start = System.currentTimeMillis();
		for(key = 1; key< arrayOne.length;key++){
			for(int i =key-1; i>=0;i--){
				if(arrayOne[i+1]<arrayOne[i]){
					temp = arrayOne[i+1];
					arrayOne[i+1] = arrayOne[i];
					arrayOne[i]= temp;
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.print(end-start+" ms");*/
		
		//O(nlogn) complexity for binary insertion sort
		long start = System.currentTimeMillis();
		int position=-1;
		for(key = 1; key< arrayOne.length;key++){
		
			try{
				position=newSorting.binarySearch(arrayOne, arrayOne[key],0,key-1);
			}catch(Exception e){
				//TODO:
				e.printStackTrace();
			}
			for(int i =key-1; i>=position-1;i--){
				if(arrayOne[i+1]<arrayOne[i]){
					temp = arrayOne[i+1];
					arrayOne[i+1] = arrayOne[i];
					arrayOne[i]= temp;
				}	
			
			}
		}
		long end = System.currentTimeMillis();
		System.out.print(end-start+" ms");
		
		for(int i = 0;i<arrayOne.length;i++){
			//System.out.println(arrayOne[i]+" ");
		}

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
