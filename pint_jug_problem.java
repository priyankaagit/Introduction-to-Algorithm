import java.util.*;

class Node {
	int i;
	int j;
	int k;
	Node parent;

	Node() {
	}

	Node(int m, int n, int o, Node parent) {
		i = m;
		j = n;
		k = o;
		this.parent = null;
	}
}

public class Assignment7 {
	int capacity[];
	int filled[];

	Assignment7(int cap[], int fill[]) {
		capacity = cap;
		filled = fill;

	}

	public void solution(int t) throws InterruptedException {
		boolean visited[][][] = new boolean[capacity[0] + 1][capacity[1] + 1][capacity[2] + 1];

		if (capacity[0] <= 0 || capacity[1] <= 0 || capacity[2] <= 0 || filled[0] < 0 || filled[1] < 0
				|| filled[2] < 0) {
			System.out.println(
					"\n\nAttention! INVALID INPUT. Capacity should be greater than 0 and initial volume can't be negative.");
			return;
		}

		System.out.print("Processing");
		for (int i = 0; i < 10; i++) {
			System.out.print(".");
			Thread.sleep(1000);
		}

		if (filled[0] == capacity[0] && filled[1] == capacity[1] && filled[2] == capacity[2]) {
			System.out.println(
					"\n\nAlas! No Solution is available. All glasses are filled. Please try with different inputs.");
			return;
		}

		if (filled[0] + filled[1] + filled[2] < t) {
			System.out.println(
					"\n\nAlas! No Solution is available. Required volume of target is less than total available. Please try with different inputs.");
			return;
		}

		Queue<Node> queue = new LinkedList<Node>();
		Node root = new Node(filled[0], filled[1], filled[2], null);
		Node child = null;
		queue.offer(root);
		boolean solutionFound = false;

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (!visited[current.i][current.j][current.k]) {
				visited[current.i][current.j][current.k] = true;
				int i = current.i;
				int j = current.j;
				int k = current.k;
				
				int c1 = capacity[0];
				int c2 = capacity[1];
				int c3 = capacity[2];

				child = new Node();
				// Case 1 water goes from 1 to 2 (i to j)
				{
					if (i != 0) {
						child = new Node();
						child.parent = current;
						if (i + j > c2) {
							child.i = i - (c2 - j);
							child.j = c2;
							child.k = k;

						} else {
							child.i = 0;
							child.j = i + j;
							child.k = k;
						}

						if (child.i == t || child.j == t || child.k == t) {
							solutionFound = true;
							break;
						}

						if (!visited[child.i][child.j][child.k]) {							
							queue.offer(child);
						}

					}

				}

				// case 2 water goes from 1 to 3 (i to k)
				{
					if (i != 0) {
						child = new Node();
						child.parent = current;
						if (i + k > c3) {

							child.i = i - (c3 - k);
							child.j = j;
							child.k = c3;

						} else {

							child.i = 0;
							child.j = j;
							child.k = i + k;
						}

						if (child.i == t || child.j == t || child.k == t) {
							solutionFound = true;
							break;
						}

						if (!visited[child.i][child.j][child.k]) {							
							queue.offer(child);

						}

					}

				}

				// case 3 water goes from 2 to 3
				{
					if (j != 0) {
						child = new Node();
						child.parent = current;
						if (j + k > c3) {

							child.i = i;
							child.j = j - (c3 - k);
							child.k = c3;

						} else {

							child.i = i;
							child.j = 0;
							child.k = j + k;
						}

						if (child.i == t || child.j == t || child.k == t) {
							solutionFound = true;
							break;
						}

						if (!visited[child.i][child.j][child.k]) {							
							queue.offer(child);
						}

					}

				}

				// case 4 water goes from 2 to 1

				{
					if (j != 0) {
						child = new Node();
						child.parent = current;
						if (j + i > c1) {

							child.i = c1;
							child.j = j - (c1 - i);

							child.k = k;

						} else {

							child.i = i + j;
							child.j = 0;
							child.k = k;

						}

						if (child.i == t || child.j == t || child.k == t) {
							solutionFound = true;
							break;
						}

						if (!visited[child.i][child.j][child.k]) {							
							queue.offer(child);
						}
					}

				}

				// case 5 goes from 3 to 1

				{

					if (k != 0) {
						child = new Node();
						child.parent = current;
						if (k + i > c1) {

							child.i = c1;
							child.j = j;
							child.k = k - (c1 - i);

						} else {

							child.i = i + k;
							child.j = j;
							child.k = 0;

						}

						if (child.i == t || child.j == t || child.k == t) {
							solutionFound = true;
							break;
						}
						if (!visited[child.i][child.j][child.k]) {							
							queue.offer(child);
						}
					}

				}

				// case 6 goes from 3 to 2

				{

					if (k != 0) {
						child = new Node();
						child.parent = current;
						if (k + j > c2) {

							child.i = i;
							child.j = c2;
							child.k = k - (c2 - j);

						} else {

							child.i = i;
							child.j = j + k;
							child.k = 0;

						}
						if (child.i == t || child.j == t || child.k == t) {
							solutionFound = true;
							break;
						}

						if (!visited[child.i][child.j][child.k]) {							
							queue.offer(child);
						}
					}

				}

			}
		}

		if (!solutionFound) {
			System.out.println("Sorry. There is no valid solution available");
			return;
		}

		Stack<Node> stack = new Stack<Node>();
		while (child != null) {
			stack.push(child);
			child = child.parent;
		}

		System.out.println("\nHurray! We got the solution. Here you go! :) \n");
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			System.out.print("(" + node.i + ", " + node.j + ", " + node.k + ")");
			Thread.sleep(2000);
			if (stack.size() >= 1) {
				System.out.print(" -> ");
			}
		}

	}

	public static void main(String args[]) throws InterruptedException {
		int capacity[] = new int[3];
		int filled[] = new int[3];
		Scanner in = new Scanner(System.in);
		System.out.println(
				"*************************************************************************************************************************");
		System.out.println(
				"---------------------================================The Jug Pouring Problem=======================---------------------------");
		System.out.println(
				"*************************************************************************************************************************");
		for (int i = 0; i < 3; i++) {
			System.out.print("Enter the capacity of jug " + (i + 1) + " : ");
			capacity[i] = in.nextInt();
			System.out.println();
		}

		for (int i = 0; i < 3; i++) {
			System.out.print("Enter the initial containt of jug " + (i + 1) + " : ");
			filled[i] = in.nextInt();
			System.out.println();
		}

		System.out.println("Enter the target to achieve :");
		int t = in.nextInt();

		Assignment7 assignemt = new Assignment7(capacity, filled);
		assignemt.solution(t);
		System.out.println(
				"\n***************************************************** Program Execution Ended****************************************************************");
	}
}
