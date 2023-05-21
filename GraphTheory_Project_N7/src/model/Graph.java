package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public abstract class Graph {
	protected int numVers;
	protected int[][] matrixAdj;
	protected boolean[] visited;
	protected Map<Integer, String> nameVers = new HashMap<>();
	protected int minWeightCycle;
	protected Integer[] lastShortestCycle;
	protected Map<List<Integer>, Integer> shortestPathsOrCycles = new LinkedHashMap<>();
	protected Map<List<Integer>, Integer> allCycles = new LinkedHashMap<>();
	protected boolean checkVoHuong = false;

	public Graph(String pathGraph) {
		loadGraph(pathGraph);
		visited = new boolean[numVers];
	}

	public Graph(int numVers) {
		this.numVers = numVers;
		matrixAdj = new int[numVers][numVers];
		visited = new boolean[numVers];
	}

	public boolean getCheckVoHuong() {
		return checkVoHuong;
	}

	public void setCheckVoHuong(boolean checkVoHuong) {
		this.checkVoHuong = checkVoHuong;
	}

	public Map<List<Integer>, Integer> getShortPathsOrCycles() {
		return shortestPathsOrCycles;
	}

	public void setShortPathsOrCycles(Map<List<Integer>, Integer> shortestPathsOrCycles) {
		this.shortestPathsOrCycles = shortestPathsOrCycles;
	}

	public int getNumVers() {
		return numVers;
	}

	public void setNumVers(int numVers) {
		this.numVers = numVers;
	}

	public int[][] getMatrixAdj() {
		return matrixAdj;
	}

	public void setMatrixAdj(int[][] matrixAdj) {
		this.matrixAdj = matrixAdj;
	}

	public boolean[] getVisited() {
		return visited;
	}

	public void setVisited(boolean[] visited) {
		this.visited = visited;
	}

	public Map<Integer, String> getNameVers() {
		return nameVers;
	}

	public void setNameVers(Map<Integer, String> nameVers) {
		this.nameVers = nameVers;
	}

	public Integer[] getLastShortestCycle() {
		return lastShortestCycle;
	}

	public void setLastShortestCycle(Integer[] lastShortestCycle) {
		this.lastShortestCycle = lastShortestCycle;
	}

	public int getMinWeightCycle() {
		return minWeightCycle;
	}

	public void setMinWeightCycle(int minWeightCycle) {
		this.minWeightCycle = minWeightCycle;
	}

	public Map<List<Integer>, Integer> getAllCycles() {
		return allCycles;
	}

	// 0 loadGraph method: load file.txt -> matrix
	public void loadGraph(String pathFile) {
		File file = new File(pathFile);

		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			this.numVers = Integer.valueOf(br.readLine());
			this.matrixAdj = new int[numVers][numVers];

			String line = "";
			int row = 0;
			while ((line = br.readLine()) != null) {
				String[] temp = line.split(" ");
				for (int i = 0; i < temp.length; i++) {
					matrixAdj[row][i] = Integer.parseInt(temp[i]);
				}
				row++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printMatrix() {
		for (int i = 0; i < matrixAdj.length; i++) {
			for (int j = 0; j < matrixAdj.length; j++) {
				System.out.print(matrixAdj[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int deg(int v) {
		int deg = 0;
		for (int i = 0; i < this.matrixAdj[v].length; i++) {
			if (matrixAdj[v][i] != 0)
				deg += 1;
		}
		return deg;
	}

	public void resetGraph() {
		visited = new boolean[numVers];
	}

	public boolean checkVoHuong() {
		for (int i = 0; i < matrixAdj.length; i++) {
			for (int j = i + 1; j < matrixAdj[i].length; j++) {
				if (matrixAdj[i][j] != matrixAdj[j][i]) {
					checkVoHuong = false;
					return checkVoHuong;
				}
			}
		}
		checkVoHuong = true;
		return checkVoHuong;
	}

	public abstract int[] DFS(int startV);

	public abstract boolean isConnected();

	public void changeEdge(int v1, int v2, int kc) {
		if (checkVoHuong == true) {
			matrixAdj[v1][v2] = matrixAdj[v2][v1] = kc;
		} else {
			matrixAdj[v2][v1] = 0;
			matrixAdj[v1][v2] = 0;
			matrixAdj[v1][v2] = kc;
		}
	}

	public void removeEdge(int v1, int v2) {
		matrixAdj[v1][v2] = 0;
		matrixAdj[v2][v1] = 0;
	}

	public abstract Map<List<Integer>, Integer> findShortestPath(int start, int end);

	public abstract Map<List<Integer>, Integer> findShortestPath(int src);

	public abstract boolean isHamiltonGraph();

	public abstract Map<List<Integer>, Integer> findShortestCycle(int startVertex);
}
