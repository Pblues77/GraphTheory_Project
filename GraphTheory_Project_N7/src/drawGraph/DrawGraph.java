package drawGraph;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Graph;
import model.UnGraph;

public class DrawGraph extends JPanel {
	private Graph graph;
	private Map<Integer, Point> locationVer = new HashMap<Integer, Point>();
	private Map<Integer, ArrayList<Integer>> keX = new HashMap<Integer, ArrayList<Integer>>();// dinh ke de chi
	private List<Integer> re = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public DrawGraph(Graph graph) {
		this.graph = graph;
		boTriDinh();
		setSize(790, 527);
		setLayout(null);
	}

	public DrawGraph(Graph graph, List<Integer> re) {
		this.graph = graph;
		this.re = re;
		boTriDinh();
		setSize(806, 527);
		setLayout(null);
	}

	// phan bo Dinh sao cho hop ly? x,y
	private void boTriDinh() {
		if (graph.getNumVers() <= 15) {
			switch (graph.getNumVers()) {
			case 3: {
				locationVer.put(0, new Point(232, 130, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(532, 130, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(379, 349, 30, "3", graph.getNameVers().get(2) + ""));
				break;
			}
			case 4: {
				locationVer.put(0, new Point(232, 130, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(532, 130, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(232, 353, 30, "3", graph.getNameVers().get(2) + ""));
				locationVer.put(3, new Point(532, 352, 30, "4", graph.getNameVers().get(3) + ""));
				break;
			}
			case 5: {
				locationVer.put(0, new Point(209, 131, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(444, 131, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(564, 238, 30, "3", graph.getNameVers().get(2) + ""));
				locationVer.put(3, new Point(444, 354, 30, "4", graph.getNameVers().get(3) + ""));
				locationVer.put(4, new Point(209, 354, 30, "5", graph.getNameVers().get(4) + ""));
				break;
			}
			case 6: {
				locationVer.put(0, new Point(209, 131, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(444, 131, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(564, 238, 30, "3", graph.getNameVers().get(2) + ""));
				locationVer.put(3, new Point(444, 354, 30, "4", graph.getNameVers().get(3) + ""));
				locationVer.put(4, new Point(209, 354, 30, "5", graph.getNameVers().get(4) + ""));
				locationVer.put(5, new Point(121, 237, 30, "6", graph.getNameVers().get(5) + ""));
				break;
			}
			case 7: {
				locationVer.put(0, new Point(364, 99, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(524, 141, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(554, 286, 30, "3", graph.getNameVers().get(2) + ""));
				locationVer.put(3, new Point(461, 378, 30, "4", graph.getNameVers().get(3) + ""));
				locationVer.put(4, new Point(286, 378, 30, "5", graph.getNameVers().get(4) + ""));
				locationVer.put(5, new Point(184, 286, 30, "6", graph.getNameVers().get(5) + ""));
				locationVer.put(6, new Point(222, 141, 30, "7", graph.getNameVers().get(6) + ""));
				break;
			}
			case 8: {
				locationVer.put(0, new Point(364, 99, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(524, 141, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(578, 241, 30, "3", graph.getNameVers().get(2) + ""));
				locationVer.put(3, new Point(524, 346, 30, "4", graph.getNameVers().get(3) + ""));
				locationVer.put(4, new Point(364, 378, 30, "5", graph.getNameVers().get(4) + ""));
				locationVer.put(5, new Point(213, 346, 30, "6", graph.getNameVers().get(5) + ""));
				locationVer.put(6, new Point(166, 241, 30, "7", graph.getNameVers().get(6) + ""));
				locationVer.put(7, new Point(213, 141, 30, "8", graph.getNameVers().get(7) + ""));
				break;
			}
			case 9: {
				locationVer.put(0, new Point(364, 99, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(494, 121, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(560, 221, 30, "3", graph.getNameVers().get(2) + ""));
				locationVer.put(3, new Point(523, 330, 30, "4", graph.getNameVers().get(3) + ""));
				locationVer.put(4, new Point(427, 396, 30, "5", graph.getNameVers().get(4) + ""));
				locationVer.put(5, new Point(317, 396, 30, "6", graph.getNameVers().get(5) + ""));
				locationVer.put(6, new Point(221, 330, 30, "7", graph.getNameVers().get(6) + ""));
				locationVer.put(7, new Point(193, 221, 30, "8", graph.getNameVers().get(7) + ""));
				locationVer.put(8, new Point(241, 121, 30, "9", graph.getNameVers().get(8) + ""));
				break;
			}
			case 10: {
				locationVer.put(0, new Point(317, 81, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(427, 81, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(523, 125, 30, "3", graph.getNameVers().get(2) + ""));
				locationVer.put(3, new Point(576, 240, 30, "4", graph.getNameVers().get(3) + ""));
				locationVer.put(4, new Point(523, 366, 30, "5", graph.getNameVers().get(4) + ""));
				locationVer.put(5, new Point(427, 415, 30, "6", graph.getNameVers().get(5) + ""));
				locationVer.put(6, new Point(317, 415, 30, "7", graph.getNameVers().get(6) + ""));
				locationVer.put(7, new Point(221, 366, 30, "8", graph.getNameVers().get(7) + ""));
				locationVer.put(8, new Point(171, 240, 30, "9", graph.getNameVers().get(8) + ""));
				locationVer.put(9, new Point(221, 125, 30, "10", graph.getNameVers().get(9) + ""));
				break;
			}
			case 11: {
				locationVer.put(0, new Point(282, 31, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(472, 31, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(637, 77, 30, "3", graph.getNameVers().get(2) + ""));
				locationVer.put(3, new Point(705, 160, 30, "4", graph.getNameVers().get(3) + ""));
				locationVer.put(4, new Point(705, 268, 30, "5", graph.getNameVers().get(4) + ""));
				locationVer.put(5, new Point(637, 366, 30, "6", graph.getNameVers().get(5) + ""));
				locationVer.put(6, new Point(472, 415, 30, "7", graph.getNameVers().get(6) + ""));
				locationVer.put(7, new Point(282, 415, 30, "8", graph.getNameVers().get(7) + ""));
				locationVer.put(8, new Point(140, 360, 30, "9", graph.getNameVers().get(8) + ""));
				locationVer.put(9, new Point(65, 214, 30, "10", graph.getNameVers().get(9) + ""));
				locationVer.put(10, new Point(140, 77, 30, "11", graph.getNameVers().get(10) + ""));
				break;
			}
			case 12: {
				locationVer.put(0, new Point(282, 31, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(472, 31, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(637, 77, 30, "3", graph.getNameVers().get(2) + ""));
				locationVer.put(3, new Point(705, 160, 30, "4", graph.getNameVers().get(3) + ""));
				locationVer.put(4, new Point(705, 268, 30, "5", graph.getNameVers().get(4) + ""));
				locationVer.put(5, new Point(637, 366, 30, "6", graph.getNameVers().get(5) + ""));
				locationVer.put(6, new Point(472, 415, 30, "7", graph.getNameVers().get(6) + ""));
				locationVer.put(7, new Point(282, 415, 30, "8", graph.getNameVers().get(7) + ""));
				locationVer.put(8, new Point(140, 360, 30, "9", graph.getNameVers().get(8) + ""));
				locationVer.put(9, new Point(60, 268, 30, "10", graph.getNameVers().get(9) + ""));
				locationVer.put(10, new Point(60, 160, 30, "11", graph.getNameVers().get(10) + ""));
				locationVer.put(11, new Point(140, 77, 30, "12", graph.getNameVers().get(11) + ""));
				break;

			}
			case 13: {
				locationVer.put(0, new Point(282, 31, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(472, 31, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(637, 77, 30, "3", graph.getNameVers().get(2) + ""));
				locationVer.put(3, new Point(705, 160, 30, "4", graph.getNameVers().get(3) + ""));
				locationVer.put(4, new Point(705, 268, 30, "5", graph.getNameVers().get(4) + ""));
				locationVer.put(5, new Point(659, 370, 30, "6", graph.getNameVers().get(5) + ""));
				locationVer.put(6, new Point(541, 437, 30, "7", graph.getNameVers().get(6) + ""));
				locationVer.put(7, new Point(430, 459, 30, "8", graph.getNameVers().get(7) + ""));
				locationVer.put(8, new Point(225, 437, 30, "9", graph.getNameVers().get(8) + ""));
				locationVer.put(9, new Point(115, 370, 30, "10", graph.getNameVers().get(9) + ""));
				locationVer.put(10, new Point(60, 268, 30, "11", graph.getNameVers().get(10) + ""));
				locationVer.put(11, new Point(60, 160, 30, "12", graph.getNameVers().get(11) + ""));
				locationVer.put(12, new Point(140, 77, 30, "13", graph.getNameVers().get(12) + ""));
				break;
			}
			case 14: {
				locationVer.put(0, new Point(380, 10, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(541, 31, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(659, 77, 30, "3", graph.getNameVers().get(2) + ""));
				locationVer.put(3, new Point(705, 160, 30, "4", graph.getNameVers().get(3) + ""));
				locationVer.put(4, new Point(705, 268, 30, "5", graph.getNameVers().get(4) + ""));
				locationVer.put(5, new Point(659, 370, 30, "6", graph.getNameVers().get(5) + ""));
				locationVer.put(6, new Point(541, 437, 30, "7", graph.getNameVers().get(6) + ""));
				locationVer.put(7, new Point(380, 459, 30, "8", graph.getNameVers().get(7) + ""));
				locationVer.put(8, new Point(225, 437, 30, "9", graph.getNameVers().get(8) + ""));
				locationVer.put(9, new Point(115, 370, 30, "10", graph.getNameVers().get(9) + ""));
				locationVer.put(10, new Point(60, 268, 30, "11", graph.getNameVers().get(10) + ""));
				locationVer.put(11, new Point(60, 160, 30, "12", graph.getNameVers().get(11) + ""));
				locationVer.put(12, new Point(115, 77, 30, "13", graph.getNameVers().get(12) + ""));
				locationVer.put(13, new Point(225, 31, 30, "14", graph.getNameVers().get(13) + ""));
				break;
			}
			case 15: {
				locationVer.put(0, new Point(380, 10, 30, "1", graph.getNameVers().get(0) + ""));
				locationVer.put(1, new Point(541, 31, 30, "2", graph.getNameVers().get(1) + ""));
				locationVer.put(2, new Point(659, 77, 30, "3", graph.getNameVers().get(2) + ""));
				locationVer.put(3, new Point(705, 160, 30, "4", graph.getNameVers().get(3) + ""));
				locationVer.put(4, new Point(705, 268, 30, "5", graph.getNameVers().get(4) + ""));
				locationVer.put(5, new Point(659, 370, 30, "6", graph.getNameVers().get(5) + ""));
				locationVer.put(6, new Point(569, 437, 30, "7", graph.getNameVers().get(6) + ""));
				locationVer.put(7, new Point(447, 469, 30, "8", graph.getNameVers().get(7) + ""));
				locationVer.put(8, new Point(323, 469, 30, "9", graph.getNameVers().get(8) + ""));
				locationVer.put(9, new Point(207, 437, 30, "10", graph.getNameVers().get(9) + ""));
				locationVer.put(10, new Point(115, 370, 30, "11", graph.getNameVers().get(10) + ""));
				locationVer.put(11, new Point(60, 268, 30, "12", graph.getNameVers().get(11) + ""));
				locationVer.put(12, new Point(60, 160, 30, "13", graph.getNameVers().get(12) + ""));
				locationVer.put(13, new Point(115, 77, 30, "14", graph.getNameVers().get(13) + ""));
				locationVer.put(14, new Point(225, 31, 30, "15", graph.getNameVers().get(14) + ""));
				break;
			}
			}
		}
	}

	public void paint(Graphics g) {
		boolean checkVoHuong = graph.checkVoHuong();
		if (graph.getNumVers() > 15) {
			g.setFont(new Font("Aria", Font.BOLD, 25));
			g.drawString("Chỉ hỗ trợ vẽ đồ thị có nhiều nhất 15 đỉnh", this.getWidth() / 2 - 225, this.getHeight() / 2);
		} else {
			int x = 0;
			int y = 0;
			int d = 0;
			// put map_KeX
			for (int i = 0; i < graph.getNumVers(); i++) {
				ArrayList<Integer> values = new ArrayList<>();
				if (graph.checkVoHuong()) {
					for (int j = i; j < graph.getNumVers(); j++) {
						if (graph.getMatrixAdj()[i][j] != 0) {
							values.add(j);
						}
					}
					keX.put(i, values);

				} else {
					for (int j = 0; j < graph.getNumVers(); j++) {
						if (graph.getMatrixAdj()[i][j] != 0) {
							values.add(j);
						}
					}
					keX.put(i, values);
				}
			}
			// draw Edge + kc
			for (Entry<Integer, ArrayList<Integer>> entry : keX.entrySet()) {
				x = entry.getKey();
				ArrayList<Integer> valuesX = entry.getValue();
				for (int i = 0; i < valuesX.size(); i++) {
					y = valuesX.get(i);
					Point pX = locationVer.get(x);
					Point pY = locationVer.get(y);

					g.setColor(Color.blue);
					g.drawLine(pX.getX() + 15, pX.getY() + 15, pY.getX() + 15, pY.getY() + 15);
					// draw khoang cach
					int avgX = Math.abs(pX.getX() - pY.getX()) / 2;
					if (pX.getX() < pY.getX())
						avgX += pX.getX() + 10;
					else
						avgX += pY.getX() + 10;

					int avgY = Math.abs(pX.getY() - pY.getY()) / 2;
					if (pX.getY() < pY.getY())
						avgY += pX.getY() + 10;
					else
						avgY += pY.getY() + 10;

					g.setColor(Color.black);
					g.setFont(new Font("Aria", Font.BOLD, 14));
					g.drawString(graph.getMatrixAdj()[x][y] + "", avgX, avgY);
					//
					if (!checkVoHuong) {
						g.setColor(Color.blue);
						int dX = Math.abs(avgX - pY.getX()) / 2;
						if (pX.getX() < pY.getX())
							dX += avgX + 5;
						else
							dX += pY.getX() + 5;

						int dY = Math.abs(avgY - pY.getY()) / 2;
						if (pX.getY() < pY.getY())
							dY += avgY + 5;
						else
							dY += pY.getY() + 5;
						g.fillRect(dX, dY, 10, 10);
					}
				}

			}
			// ver
			x = 0;
			y = 0;
			d = 0;
			for (Entry<Integer, Point> entry : locationVer.entrySet()) {
				Point p = entry.getValue();
				x = p.getX();
				y = p.getY();
				d = p.getD();

				// oval
				g.setColor(Color.white);
				g.fillOval(x, y, d, d);
				// ver number
				g.setColor(Color.red);
				g.setFont(new Font("Aria", Font.BOLD, 20));
				if (p.getId().length() == 1) {
					g.drawString(p.getId(), x + 8, y + 22);
				} else {
					g.drawString(p.getId(), x + 4, y + 22);
				}
				// ver name
				g.setColor(Color.black);
				g.setFont(new Font("Aria", Font.BOLD, 15));
				g.drawString(p.getName(), x, y);
			}

			// in ket qua
			if (!re.isEmpty()) {
				for (int i = 0; i < re.size(); i++) {
					if (i != re.size() - 1) {
						Point pX = locationVer.get(re.get(i));
						Point pY = locationVer.get(re.get(i + 1));

						g.setColor(Color.red);
						g.drawLine(pX.getX() + 15, pX.getY() + 15, pY.getX() + 15, pY.getY() + 15);

						// draw ver
						x = 0;
						y = 0;
						d = 0;
						for (int k = 0; k < re.size(); k++) {
							Point p = locationVer.get(re.get(k));
							x = p.getX();
							y = p.getY();
							d = p.getD();

							// oval
							g.setColor(Color.black);
							g.fillOval(x, y, d, d);
							// ver number
							g.setColor(Color.red);
							g.setFont(new Font("Aria", Font.BOLD, 20));
							if (p.getId().length() == 1) {
								g.drawString(p.getId(), x + 8, y + 22);
							} else {
								g.drawString(p.getId(), x + 4, y + 22);
							}
							// ver name
							g.setColor(Color.red);
							g.setFont(new Font("Aria", Font.BOLD, 15));
							g.drawString(p.getName(), x, y);
						}
						int avgX = Math.abs(pX.getX() - pY.getX()) / 2;
						if (pX.getX() < pY.getX())
							avgX += pX.getX() + 10;
						else
							avgX += pY.getX() + 10;

						int avgY = Math.abs(pX.getY() - pY.getY()) / 2;
						if (pX.getY() < pY.getY())
							avgY += pX.getY() + 10;
						else
							avgY += pY.getY() + 10;

						g.setColor(Color.red);
						if (!checkVoHuong) {
							int dX = Math.abs(avgX - pY.getX()) / 2;
							if (pX.getX() < pY.getX())
								dX += avgX + 5;
							else
								dX += pY.getX() + 5;

							int dY = Math.abs(avgY - pY.getY()) / 2;
							if (pX.getY() < pY.getY())
								dY += avgY + 5;
							else
								dY += pY.getY() + 5;
							g.fillRect(dX, dY, 10, 10);
						}
					}
				}
			}

		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame f = new JFrame();
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.setSize(806, 527);
					f.setLocationRelativeTo(null);
					f.getContentPane().setLayout(null);
					f.setBackground(Color.gray);
					UnGraph un = new UnGraph("inputGraph/directedgraph.txt");
					un.printMatrix();
					DrawGraph d = new DrawGraph(un);
					f.getContentPane().add(d);
					f.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
