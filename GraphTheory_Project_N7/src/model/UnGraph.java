package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class UnGraph extends Graph {
	private int[] cols;
	private int[] result;

	public UnGraph(String pathGraph) {
		super(pathGraph);
		this.result = new int[numVers];
	}

	public UnGraph(int numVers) {
		super(numVers);
		this.result = new int[numVers];
	}

	@Override
	public int[] DFS(int startV) {
		Stack<Integer> st = new Stack<Integer>();

		int pos = 0;

		st.push(startV);

		while (!st.empty()) {
			int v = st.pop();
			if (visited[v] == false) {
				visited[v] = true;
				result[pos] = v;
				pos++;
			}
			for (int i = numVers - 1; i > 0; i--) {
				if (matrixAdj[v][i] != 0 && visited[i] == false)
					st.push(i);
			}
		}

		return result;
	}

	@Override
	public boolean isConnected() {
		result = new int[numVers];
		visited = new boolean[numVers];

		DFS(0);
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == false)
				return false;
		}
		return true;
	}

	@Override
	public Map<List<Integer>, Integer> findShortestPath(int start, int end) {
		shortestPathsOrCycles = new LinkedHashMap<>();
		if (!isConnected()) {
			System.out.println("Do thi khong lien thong");
			return shortestPathsOrCycles;// java tham chiếu :vvv
		} else {
			int n = numVers;
			int[][] temp = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					temp[i][j] = matrixAdj[i][j];
				}
			}

			boolean[] R = new boolean[n];
			int[] L = new int[n];
			int[] P = new int[n];
			// gắn giá trị ban đầu
			for (int i = 0; i < R.length; i++) {
				L[i] = Integer.MAX_VALUE;
				P[i] = -1;
			}
			// xử lý ma trận trọng số
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (temp[i][j] == 0)
						temp[i][j] = Integer.MAX_VALUE;
				}
			}
			// tìm số đỉnh có L[] min
			L[start] = 0;
			int sodinhdaduyet = 0;
			while (sodinhdaduyet < n - 1) {
				// tìm
				int v = 0;
				int minValue = Integer.MAX_VALUE;
				for (int i = 0; i < n; i++) {
					if (L[i] < minValue && R[i] == false) {
						minValue = L[i];
						v = i;
					}
				}
				// n
				for (int i = 0; i < n; i++) {
					if (L[i] > L[v] + temp[v][i] && temp[v][i] < Integer.MAX_VALUE) {
						L[i] = L[v] + temp[v][i];
						P[i] = v;
					}
				}
				R[v] = true;
				sodinhdaduyet++;
				if (v == end)
					break;
			}
			// kết quả
			shortestPathsOrCycles = new LinkedHashMap<>();
			List<Integer> path = getPath(P, start, end);
			shortestPathsOrCycles.put(path, L[end]);
		}
		return shortestPathsOrCycles;
	}

	@Override
	public Map<List<Integer>, Integer> findShortestPath(int src) {
		shortestPathsOrCycles = new LinkedHashMap<>();
		if (!isConnected()) {
			System.out.println("Do thi khong lien thong");
			return shortestPathsOrCycles;
		} else {
			int n = numVers;
			int[][] temp = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					temp[i][j] = matrixAdj[i][j];
				}
			}

			boolean[] R = new boolean[n];
			int[] L = new int[n];
			int[] P = new int[n];
			// gắn giá trị ban đầu
			for (int i = 0; i < R.length; i++) {
				L[i] = Integer.MAX_VALUE;
				P[i] = -1;
			}
			// xử lý ma trận trọng số
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (temp[i][j] == 0)
						temp[i][j] = Integer.MAX_VALUE;
				}
			}
			// tìm số đỉnh có L[] min
			L[src] = 0;
			int sodinhdaduyet = 0;
			while (sodinhdaduyet < n - 1) {
				// tìm
				int v = 0;
				int minValue = Integer.MAX_VALUE;
				for (int i = 0; i < n; i++) {
					if (L[i] < minValue && R[i] == false) {
						minValue = L[i];
						v = i;
					}
				}
				// n
				for (int i = 0; i < n; i++) {
					if (L[i] > L[v] + temp[v][i] && temp[v][i] < Integer.MAX_VALUE) {
						L[i] = L[v] + temp[v][i];
						P[i] = v;
					}
				}
				R[v] = true;
				sodinhdaduyet++;
			}
			// kết quả
			shortestPathsOrCycles = new LinkedHashMap<>();
			for (int i = 0; i < numVers; i++) {
				if (i != src) {
					shortestPathsOrCycles.put(getPath(P, src, i), L[i]);
				}
			}
		}
		return shortestPathsOrCycles;
	}

	private List<Integer> getPath(int[] p, int a, int b) {
		List<Integer> path = new ArrayList<>();
		int temp = b;
		path.add(0, b);
		while (temp != a) {
			if (p[temp] != a)
				path.add(0, p[temp]);
			temp = p[temp];
		}
		path.add(0, a);
		return path;
	}

	/*
	 * Tim chu trinh
	 */
	// 1. check luong phan + v1=v2
	private void DFSToMau(int i) {
		this.cols = new int[numVers];
		visited[i] = true;
		cols[i] = 1;
		for (int j = 0; j < numVers; j++) {
			if (visited[j] == false && matrixAdj[i][j] != 0) {
				cols[j] = 1 - cols[i];
				DFSToMau(j);
			}
		}
	}

	private boolean checkBipartiteGraphForHamilton() {
		DFSToMau(0);
		int soDinhMau1 = 0;
		int soDinhMau2 = 0;
		for (int i = 0; i < cols.length; i++) {
			if (cols[i] == 1) {
				soDinhMau1++;
			} else {
				soDinhMau2++;
			}
		}
		if (soDinhMau1 == soDinhMau2) {
			for (int i = 0; i < cols.length; i++) {
				if (deg(i) >= soDinhMau2 / 2) {
					return false;
				}
			}
		}
		return true;
	}
	//
	private int demBacHon1() {
		int count = 0;
		for (int i = 0; i < numVers; i++) {
			if (deg(i) > 1)
				count++;
		}
		return count;
	}

	// 3. check Kn
	private boolean checkKn() {
		for (int i = 0; i < numVers; i++) {
			if (deg(i) != numVers - 1)
				return false;
		}
		return true;
	}

	// 4. dirac
	private boolean dirac() {
		double t = (double) numVers / 2;
		for (int i = 0; i < numVers; i++) {
			if (this.deg(i) < t)
				return false;
		}
		return true;
	}

	// 5. ore
	private boolean ore() {
		dl2: for (int i = 0; i < numVers; i++) {
			// j: i+1 -> length-1
			for (int j = i + 1; j < numVers; j++) {
				if (deg(i) + deg(j) < numVers)
					return false;
				break dl2;
			}
		}
		return true;
	}

	@Override
	public boolean isHamiltonGraph() {
		if (checkVoHuong()) {
			if (isConnected() == false)
				return false;
			else {
				if (checkKn() == true)
					return true;
				else if (checkBipartiteGraphForHamilton() == true)
					return true;
				else if (demBacHon1() == numVers)
					return true;
				else if (dirac() == true)
					return true;
				else if (ore() == true)
					return true;
				return false;
			}
		} else {
			// không khả thi với sô lượng lớn mà số lượng lớn dùng thuật toán nào cũng phức tạp
			for (int i = 0; i < getNumVers(); i++) {
				findShortestCycle(i);
				if (getLastShortestCycle() != null)
					return true;
			}
			return false;
		}
	}

	Integer[] cycleVer = new Integer[numVers + 1];
	@Override
	public Map<List<Integer>, Integer> findShortestCycle(int startVertex) {
		if (checkVoHuong() && isHamiltonGraph() == false) {
			System.out.println("G khong co chu trinh Hamilton");
			return null;
		} else {
			// reset
			minWeightCycle = Integer.MAX_VALUE;
			allCycles = new LinkedHashMap<>();
			shortestPathsOrCycles = new LinkedHashMap<>();
			cycleVer = new Integer[numVers + 1];
			lastShortestCycle = null;
			Arrays.fill(visited, false);
			// thuat toan quay lui
			cycleVer[0] = startVertex;
			visited[startVertex] = true;
			expand(1);

			// new:Từ allCycles (toàn bộ ct hamilton) sẽ lấy được KQ trong
			// shortestPathsOrCycles
			for (Map.Entry<List<Integer>, Integer> entry : getAllCycles().entrySet()) {
				if (entry.getValue() == getMinWeightCycle()) {
					shortestPathsOrCycles.put(entry.getKey(), entry.getValue());
				}
			}
			//
		}
		return shortestPathsOrCycles;
	}
	private void expand(int i) {
		for (int j = 0; j < numVers; j++) {
			if (matrixAdj[cycleVer[i - 1]][j] != 0 && visited[j] == false) {
				cycleVer[i] = j;
				if (i < numVers - 1) {
					visited[j] = true;
					expand(i + 1);
					visited[j] = false;
				} else if (matrixAdj[cycleVer[i]][cycleVer[0]] > 0) {
					cycleVer[numVers] = cycleVer[0];
					int w = totalWeight(cycleVer);
					if (minWeightCycle > w) {
						lastShortestCycle = Arrays.copyOf(cycleVer, cycleVer.length);
						minWeightCycle = w;
					}
					allCycles.put(Arrays.asList(Arrays.copyOf(cycleVer, cycleVer.length)), totalWeight(cycleVer));
				}
			}
		}
	}

	private int totalWeight(Integer[] cycle) {
		int w = 0;
		for (int i = 0; i < cycle.length; i++) {
			if (i != cycle.length - 1) {
				w += matrixAdj[cycle[i]][cycle[i + 1]];
			}
		}
		return w;
	}

	public static void main(String[] args) {
		UnGraph un2 = new UnGraph("inputGraph/cycle.txt");

		System.out.println(un2.checkBipartiteGraphForHamilton());
		// Test VoHuong
		System.out.println("---------------------------Vo Huong------------------------------");
		UnGraph un = new UnGraph("inputGraph/cycle.txt");
		un.printMatrix();
		System.out.println("-------------Path--------------");
		un.findShortestPath(0, 5);
		System.out.println(un.getShortPathsOrCycles());
		un.findShortestPath(0);
		System.out.println(un.getShortPathsOrCycles());

		System.out.println("-------------Cycle--------------");
		un.findShortestCycle(0);
//		System.out.println(un.getAllCycles());
		System.out.println(un.getShortPathsOrCycles());

		// Test CoHuong
		un = new UnGraph("inputGraph/directedgraph.txt");
		System.out.println("---------------------------Co Huong------------------------------");
		System.out.println(un.isHamiltonGraph());
		un.findShortestCycle(0);
//		System.out.println(un.getAllCycles());
		System.out.println(un.getShortPathsOrCycles());
	}
}
