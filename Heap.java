
public class Heap {

	//Complete this method. This method gets the parent of index i
	private static int getParent(int i) {
		return (int) Math.floor(i/2);
	}

	//Complete this method. This method gets the left child of index i
	private static int getLeft(int i) {
		return 2 * i;
	}

	//Complete this method. This method gets the right child of index i
	private static int getRight(int i) {
		return 2 * i + 1;
	}

	//Complete this method. This method does the maxHeapify operation on ith element of the heap
	public static void maxHeapify(int arr[], int heapSize, int i) {
		int max = i;
		int left = getLeft(i) + 1;
		int right = getRight(i) + 1;
		int temp;
		
		if (left < heapSize && arr[left] > arr[max]) {
			max = left;
		}
		if (right < heapSize && arr[right] > arr[max]) {
			max = right;
		}
		if (max != i) {
			temp = arr[i];
			arr[i] = arr[max];
			arr[max] = temp;
			maxHeapify(arr, heapSize, max);
		}
	}
	
	//Complete this method. This method builds a max-heap from the arr[]
	public static void buildMaxHeap(int arr[]){
		int n = arr.length;
		for (int i = getParent(n); i >= 0; i--) {
			maxHeapify(arr, n, i);
		}
	}

	//Complete this method. This method sorts the array using heap sort
	public static void heapSort(int arr[]){
		int temp;
		
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			maxHeapify(arr, arr.length, i);
		}
		for (int j = arr.length - 1; j > 0; j--) {
			temp = arr[0];
			arr[0] = arr[j];
			arr[j] = temp;
			maxHeapify(arr, j, 0);
		}
	}
	
	
	public static void main(String args[]) {
		int[] list = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
		System.out.println("List");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
		System.out.println();
		System.out.println("Max Heap List");
		buildMaxHeap(list);
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
		System.out.println();
		System.out.println("Heapsort List");
		heapSort(list);
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
		System.out.println();
		int[] list1 = {13, 77, 24, 82, 1, 8, 10, 23, 99, 67};
		
		
		System.out.println("List1");
		for (int i = 0; i < list1.length; i++) {
			System.out.print(list1[i] + " ");
		}
		System.out.println();
		System.out.println("Max Heap List1");
		buildMaxHeap(list1);
		for (int i = 0; i < list1.length; i++) {
			System.out.print(list1[i] + " ");
			//1 8 10 13 23 24 67 77 82 99 
		}
		System.out.println();
		System.out.println("Heapsort List1");
		heapSort(list1);
		for (int i = 0; i < list.length; i++) {
			System.out.print(list1[i] + " ");
		}
	}

}
