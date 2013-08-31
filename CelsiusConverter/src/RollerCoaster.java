import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class RollerCoaster {

	private long R = 0; // rounds
	private int T = 0; // number of test cases
	private long k = 0; // capacity of the roller coaster
	private int N = 0; // number of groups
	private long income = 0;
	private final long price = 1;
	private long[] g = new long[N]; // collection of groups-> circular array
	private int[][] table = new int[T][1000]; // pre-processing data: code
												// optimization
	private int front = 0, end = N - 1, num_group_inRC = 0;
	// private String fileName="res/inputFile.txt";
	private final String fileName = "res/C-small-practice.txt";

	// private String fileName="res/C-large-practice.txt";

	private void preprocessing(int row) {

	}

	public RollerCoaster() {
		BufferedReader fileInBR = null;
		Scanner fileInput = null;

		try {
			fileInBR = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Exception: " + e);
		}

		fileInput = new Scanner(fileInBR);

		if (fileInput.hasNextInt()) {
			// Get the first line: number of test cases
			T = fileInput.nextInt();
		}

		for (int i = 0; i < T; i++) {
			income = 0;
			// read first line
			if (fileInput.hasNextInt()) {
				// rounds
				R = fileInput.nextInt();
			}

			if (fileInput.hasNextInt()) {
				// capacity
				k = fileInput.nextInt();
			}

			if (fileInput.hasNextInt()) {
				// # of groups
				N = fileInput.nextInt();
			}
			g = new long[N];
			table = new int[T][1000];
			front = 0;
			end = N - 1;
			num_group_inRC = 0;

			// read second line
			for (int j = 0; j < N; j++) {
				g[j] = fileInput.nextInt();
			}

			// fill the table to optimize the code
			preprocessing(i);

			for (int rounds = 0; rounds < R; rounds++) {
				long left_seats = k;
				num_group_inRC = 0;

				if (N == 1) {// special case: only one group
					income = income + price * g[front];
					left_seats = left_seats - g[front];
					num_group_inRC++;
				} else {
					while (left_seats > 0 && num_group_inRC < N) {
						if (left_seats >= g[front]) {
							income = income + price * g[front];
							left_seats = left_seats - g[front];

							front = (front + 1) % N;
							num_group_inRC++;
						} else {
							break;
						}

						if (front == end && left_seats >= g[front]
								&& num_group_inRC < N) {
							income = income + price * g[front];
							left_seats = left_seats - g[front];
							num_group_inRC++;
							break;
						}
					}
				}

				// after the run:
				while (num_group_inRC > 0) {
					end = (end + 1) % N;
					left_seats = left_seats + g[end];
					num_group_inRC--;
				}

			}

			System.out.println("Case #" + (i + 1) + ": " + income);
			/*
			 * //testing: System.out.println(T);
			 * System.out.println(R+" "+k+" "+N); for(int j=0;j<N;j++){
			 * 
			 * System.out.print(g[j]+" ");
			 * 
			 * } System.out.println();
			 */
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RollerCoaster newGame = new RollerCoaster();
	}

}
