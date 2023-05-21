package controller;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import drawGraph.DrawGraph;
import model.Graph;
import model.UnGraph;
import view.MainView;

public class ControllerJFrame implements ActionListener, ListSelectionListener {
	private MainView view;
	public Graph graph;
	private DrawGraph d;

	/**
	 * @param view
	 */
	public ControllerJFrame(MainView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		resetInterfaceWhenEventHandling();
		JFileChooser fileChooser = new JFileChooser();
		if (e.getSource() == view.btn_home) {
			view.dispose();
			view.frame_Draw.dispose();
			MainView frame = new MainView();
			frame.setVisible(true);
		}
		// Constructor <- open file
		else if (e.getSource() == view.bt_openfile) {
			view.myNotepadView.setVisible(true);
		}
		// Xử lý myNotepadView
		else if (e.getSource() == view.myNotepadView.button_Open) {
			// Dat vi tri hien thi hop thoai la E:
			fileChooser.setCurrentDirectory(new File("inputGraph"));

			// select file to open
			int returnval = fileChooser.showOpenDialog(view.myNotepadView);// Hien thi JFilechooser
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				view.myNotepadView.model.setFileName(file.getAbsolutePath());// Lay duong dan tuyet doi
				view.myNotepadView.lb_filepath.setText(view.myNotepadView.model.getFileName());
				String fileName = file.getName();
				if (fileName.endsWith(".txt")) {
					try {
						List<String> allText = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
						String result = "";
						for (String line : allText) {
							result += line;
							result += "\n";
						}
						view.myNotepadView.model.setContent(result);
						view.myNotepadView.textArea.setText(view.myNotepadView.model.getContent());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		} else if (e.getSource() == view.myNotepadView.button_Save) {
			fileChooser.setCurrentDirectory(new File("inputGraph"));
			if (view.myNotepadView.model.getFileName().length() > 0) {
				save(view.myNotepadView.model.getFileName());
				JOptionPane.showMessageDialog(null, "Saved", null, JOptionPane.INFORMATION_MESSAGE);
			} else {
				// select file to save
				int returnval = fileChooser.showSaveDialog(view.myNotepadView);// Hien thi JFilechooser
				if (returnval == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					save(file.getAbsolutePath());
					JOptionPane.showMessageDialog(null, "Saved", null, JOptionPane.INFORMATION_MESSAGE);

					view.myNotepadView.model.setFileName(file.getAbsolutePath());
					view.myNotepadView.lb_filepath.setText(file.getAbsolutePath());
				}
			}
		} else if (e.getSource() == view.myNotepadView.bt_exportGraph) {
			view.myNotepadView.model.setFileName(view.myNotepadView.model.getFileName());
			//
			String pathFile = "";
			pathFile = view.myNotepadView.model.getFileName();
			graph = new UnGraph(pathFile);

			for (int i = 0; i < graph.getNumVers(); i++) {
				graph.getNameVers().put(i, graph.getNameVers().get(i));
			}

			view.tf_sobuudien.setText(graph.getNumVers() + "");
			view.tf_dinhthemten.setText("");
			view.tf_diemdau.setText("");
			view.tf_diemcuoi.setText("");
			view.tf_khoangcach.setText("");
			//
			Map<Integer, String> t = new HashMap<>();
			for (int i = 0; i < graph.getNumVers(); i++) {
				t.put(i, "BĐ " + (i + 1));
			}
			graph.setNameVers(t);
			//
			view.lb_pathfile.setText(pathFile);// set label Path
			view.lb_pathfile.setToolTipText(pathFile);
			showTenBuuDien();
			showMaTranTS();
			// draw
			restartDrawGraph(graph);
			// khoa nhap
			view.bt_sobuudien.setEnabled(false);
			//
			view.myNotepadView.setVisible(false);
			//
			if (graph.checkVoHuong())
				view.bt_initVoHuong.setSelected(true);
			else
				view.bt_initCoHuong.setSelected(true);

			view.bt_initVoHuong.setEnabled(false);
			view.bt_initCoHuong.setEnabled(false);

			//
			checkIG();

		} else if (e.getSource() == view.myNotepadView.button_New) {
			view.myNotepadView.model.setFileName("");
			view.myNotepadView.lb_filepath.setText("File Path!");
			view.myNotepadView.textArea.setText("");
		}

		// nhap so dinh -> graph -> in map<dinh,null>
		else if (e.getSource() == view.bt_sobuudien) {
			if (view.tf_sobuudien.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Số điểm bưu điện trống!", null, JOptionPane.INFORMATION_MESSAGE);
			}

			else if (Integer.parseInt(view.tf_sobuudien.getText()) < 3) {
				JOptionPane.showMessageDialog(null, "Số điểm bưu điện phải lớn hơn 3!", null,
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				Integer sobd = Integer.parseInt(view.tf_sobuudien.getText());
				if (sobd != null) {
					graph = new UnGraph(sobd);
					for (int i = 0; i < sobd; i++) {
						graph.getNameVers().put(i, null);
					}
				}
				view.bt_initVoHuong.setEnabled(false);
				view.bt_initCoHuong.setEnabled(false);
				showTenBuuDien();
				showMaTranTS();
				restartDrawGraph(graph);
				checkIG();
			}
		}
		// nhap tenDinh -> map<dinh, ten da nhap> ->in ra textArea
		else if (e.getSource() == view.bt_themtendinh) {
			Integer x = -1;
			String xname = "";
			if (view.tf_dinhthemten.getText().equals("") == false) {
				x = Integer.parseInt(view.tf_dinhthemten.getText());
				if (1 <= x && x <= graph.getNumVers()) {
					if (view.tf_tendinh.getText().equals("") == false) {
						xname = view.tf_tendinh.getText().trim();

						graph.getNameVers().put(x - 1, xname);

						if (x < graph.getNumVers() - 1 + 1) {
							view.tf_dinhthemten.setText(x + 1 + "");
							view.tf_tendinh.setText("");
						} else {
							view.tf_dinhthemten.setText("");
							view.tf_tendinh.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập tên bưu điện!", null,
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Đỉnh không tồn tại!", null, JOptionPane.INFORMATION_MESSAGE);
				}

			}
			showTenBuuDien();
			restartDrawGraph(graph);
		}
		// nhap canh + kc
		else if (e.getSource() == view.bt_themcanh) {
			if (view.bt_initVoHuong.isSelected())
				graph.setCheckVoHuong(true);
			else if (view.bt_initCoHuong.isSelected())
				graph.setCheckVoHuong(false);
			if (view.tf_diemdau.getText().equals("") || view.tf_diemcuoi.getText().equals("")
					|| view.tf_khoangcach.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập điểm đầu, điểm cuối và khoảng cách 2 điểm!", null,
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				Integer x = Integer.parseInt(view.tf_diemdau.getText());
				Integer y = Integer.parseInt(view.tf_diemcuoi.getText());
				Integer kc = Integer.parseInt(view.tf_khoangcach.getText());
				graph.changeEdge(x - 1, y - 1, kc);
				showMaTranTS();
				restartDrawGraph(graph);
				checkIG();
			}
		}
		// xoa canh
		else if (e.getSource() == view.bt_xoacanh) {
			if (view.tf_diemdau.getText().equals("") || view.tf_diemcuoi.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập điểm đầu và điểm cuối!", null,
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				Integer x = Integer.parseInt(view.tf_diemdau.getText());
				Integer y = Integer.parseInt(view.tf_diemcuoi.getText());
				if (!(1 <= x && x <= graph.getNumVers() && 1 <= y && y <= graph.getNumVers())) {
					JOptionPane.showMessageDialog(null, "Điểm đầu hoặc cuối không tồn tại!", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					graph.removeEdge(x - 1, y - 1);
					showMaTranTS();
					restartDrawGraph(graph);
					checkIG();
				}
			}

		} else if (e.getSource() == view.choice_Path) {
			view.ver_start.setEditable(true);
			view.ver_end.setEditable(true);
		} else if (e.getSource() == view.choice_Cycle) {
			view.ver_start.setEditable(true);
			view.ver_end.setEditable(false);
		}
		// run
		else if (e.getSource() == view.bt_run) {
			eventRun();
		} else if (e.getSource() == view.bt_themdinh) {
			String tendinh = null;
			if (!view.tf_tendinhmoi.getText().equals("")) {
				tendinh = view.tf_tendinhmoi.getText();
			}
			int newSize = graph.getNumVers() + 1;
			Map<Integer, String> newMapTenDinh = graph.getNameVers();
			newMapTenDinh.put(newSize - 1, tendinh);
			int[][] newGraph = new int[newSize][newSize];

			for (int i = 0; i < graph.getMatrixAdj().length; i++) {
				for (int j = 0; j < graph.getMatrixAdj().length; j++) {
					newGraph[i][j] = graph.getMatrixAdj()[i][j];
				}
			}

			graph = new UnGraph(newSize);
			graph.setMatrixAdj(newGraph);
			graph.setNameVers(newMapTenDinh);
			resetTable();
			restartDrawGraph(graph);
			showMaTranTS();
			checkIG();
			showTenBuuDien();
		} else if (e.getSource() == view.bt_xoadinh) {
			if (graph.getNumVers() > 3) {
				if (!view.tf_dinhcanxoa.getText().equals("")) {
					int dinhcanxoa = Integer.parseInt(view.tf_dinhcanxoa.getText()) - 1;
					int newSize = graph.getNumVers() - 1;
					Map<Integer, String> newMapTenDinh = graph.getNameVers();// xoa dinh lon nhat -> ten dinh i - 1 =
																				// ten dinh i
					for (int i = dinhcanxoa; i < graph.getNumVers() - 1; i++) {
						newMapTenDinh.put(i, newMapTenDinh.get(i + 1));
					}
					newMapTenDinh.remove(graph.getNumVers() - 1);
					List<List<Integer>> tempGraph = new ArrayList<>();
					for (int i = 0; i < graph.getMatrixAdj().length; i++) {
						if (i != dinhcanxoa) {
							List<Integer> sub = new ArrayList<>();
							for (int j = 0; j < graph.getMatrixAdj().length; j++) {
								if (j != dinhcanxoa) {
									sub.add(graph.getMatrixAdj()[i][j]);
								}
							}
							tempGraph.add(sub);
						}
					}
					int[][] newGraph = new int[newSize][newSize];
					for (int i = 0; i < tempGraph.size(); i++) {
						int[] sub = new int[tempGraph.get(i).size()];
						for (int j = 0; j < tempGraph.get(i).size(); j++) {
							sub[j] = tempGraph.get(i).get(j);
						}
						newGraph[i] = sub;
					}

					graph = new UnGraph(newSize);
					graph.setMatrixAdj(newGraph);
					graph.setNameVers(newMapTenDinh);
					resetTable();
					restartDrawGraph(graph);
					checkIG();
					showTenBuuDien();
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đỉnh cần xóa!!!", null,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else
				JOptionPane.showMessageDialog(null, "Không thể xóa vì số điểm tối thiểu là 3!!!", null,
						JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == view.bt_saveAs) {
			view.myNotepadView.setVisible(true);

			StringBuilder sb = new StringBuilder();
			sb.append(graph.getNumVers() + "\n");
			for (int i = 0; i < graph.getNumVers(); i++) {
				for (int j = 0; j < graph.getNumVers(); j++) {
					if (j != graph.getNumVers() - 1)
						sb.append(graph.getMatrixAdj()[i][j] + " ");
					else
						sb.append(graph.getMatrixAdj()[i][j] + "\n");
				}
			}

			view.myNotepadView.textArea.setText(sb.toString());
			view.myNotepadView.model.setFileName("");
			view.myNotepadView.lb_filepath.setText("");
		}
	}

	private void eventRun() {
		view.tf_chiphi.setText("");
		resetTable();
		view.textArea.setText("");
		graph.setLastShortestCycle(null);
		restartDrawGraph(graph);
		if (!(view.choice_Cycle.isSelected() || view.choice_Path.isSelected())) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn tìm kiếm đường đi hoặc chu trình ngắn nhất", null,
					JOptionPane.INFORMATION_MESSAGE);
		} else if (view.choice_Path.isSelected()) {
			if (view.ver_start.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu", null, JOptionPane.INFORMATION_MESSAGE);
			} else {
				if (view.ver_end.getText().equals("")) {
					int start = Integer.parseInt(view.ver_start.getText());
					if (!(1 <= start && start <= graph.getNumVers())) {
						JOptionPane.showMessageDialog(null, "Điểm không tồn tại!!!\nVui lòng thử lại", null,
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						if (!graph.isConnected()) {
							JOptionPane.showMessageDialog(null, "Đồ thị không liên thông", null,
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							while (view.model.getRowCount() != 0) {
								view.model.removeRow(0);
							}
							graph.findShortestPath(start - 1);
							//
							view.table.setModel(new DefaultTableModel(new Object[][] {},
									new String[] { "Kết Quả", "Chi Phí", "Tương ứng" }));
							view.model = (DefaultTableModel) view.table.getModel();
							view.table.getColumnModel().getColumn(0).setPreferredWidth(345);
							view.table.getColumnModel().getColumn(1).setPreferredWidth(45);
							view.table.getColumnModel().getColumn(2).setPreferredWidth(365);

							// getShortPathsOrCycles<path,w>
							for (Map.Entry<List<Integer>, Integer> entry : graph.getShortPathsOrCycles().entrySet()) {
								List<Integer> shortestPath = entry.getKey();
								Integer minWeight = entry.getValue();

								String path = "";
								String tenBD = "";
								for (int i = 0; i < shortestPath.size(); i++) {
									if (i < entry.getKey().size() - 1) {
										tenBD += graph.getNameVers().get(entry.getKey().get(i)) + " => ";
										path += entry.getKey().get(i) + 1 + " => ";
									} else {
										tenBD += graph.getNameVers().get(entry.getKey().get(i));
										path += entry.getKey().get(i) + 1;
									}
								}
								view.model.addRow(new Object[] { path, minWeight + "", tenBD });
							}
							view.tf_soKQ.setText(graph.getNumVers() - 1 + "");
						}
					}
				} else {
					int start = Integer.parseInt(view.ver_start.getText());
					int end = Integer.parseInt(view.ver_end.getText());
					if (!(1 <= start && start <= graph.getNumVers() && 1 <= end && end <= graph.getNumVers())) {
						JOptionPane.showMessageDialog(null, "Điểm không tồn tại!!!\nVui lòng thử lại", null,
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						if (!graph.isConnected()) {
							JOptionPane.showMessageDialog(null, "Đồ thị không liên thông", null,
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							while (view.model.getRowCount() != 0) {
								view.model.removeRow(0);
							}
							graph.findShortestPath(start - 1, end - 1);
							// ú òa có 1 pt mà cũng duyệt
							// :)))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
							for (Map.Entry<List<Integer>, Integer> entry : graph.getShortPathsOrCycles().entrySet()) {
								List<Integer> shortestPath = entry.getKey();
								Integer minWeight = entry.getValue();

								String path = "";
								String tenBD = "";
								for (int i = 0; i < shortestPath.size(); i++) {
									if (i < entry.getKey().size() - 1) {
										tenBD += graph.getNameVers().get(entry.getKey().get(i)) + " => ";
										path += entry.getKey().get(i) + 1 + " => ";
									} else {
										tenBD += graph.getNameVers().get(entry.getKey().get(i));
										path += entry.getKey().get(i) + 1;
									}
								}
								view.model.addRow(new Object[] { path, tenBD });
								view.tf_chiphi.setText(minWeight + "");
							}
						}
					}
				}
			}
		} else if (view.choice_Cycle.isSelected()) {
			if (view.ver_start.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập dữ liệu", null, JOptionPane.INFORMATION_MESSAGE);
			} else {
				int start = Integer.parseInt(view.ver_start.getText());
				if (!(1 <= start && start <= graph.getNumVers())) {
					JOptionPane.showMessageDialog(null, "Điểm bắt đầu không tồn tại!!!\nVui lòng thử lại", null,
							JOptionPane.INFORMATION_MESSAGE);

				} else {
					if (!graph.isConnected()) {
						JOptionPane.showMessageDialog(null, "Đồ thị không liên thông", null,
								JOptionPane.INFORMATION_MESSAGE);
						resetTable();
						view.model.addRow(new Object[] { "Do thi khong lien thong", "" });
					} else {
						if (!graph.isHamiltonGraph() && graph.getCheckVoHuong() == true) {
							JOptionPane.showMessageDialog(null, "Không có chu trình!!!", null,
									JOptionPane.INFORMATION_MESSAGE);
							while (view.model.getRowCount() != 0) {
								view.model.removeRow(0);
							}
							view.model.addRow(new Object[] { "Khong co chu trinh di qua tat ca cac diem", "" });
						} else {
							Integer ver = Integer.parseInt(view.ver_start.getText());
							graph.findShortestCycle(ver - 1);

							// showAllCycles
							view.textArea.setFont(new Font("Arial", Font.PLAIN, 14));
							StringBuffer showAllCycles = new StringBuffer(
									"<html><body><pre style=\"font-family: Arial; font-size: 10px;\">");
							showAllCycles.append("<b>Số chu trình xuất phát từ điểm " + ver + " là: "
									+ graph.getAllCycles().size() + "</b><br>");
							for (Map.Entry<List<Integer>, Integer> entry : graph.getAllCycles().entrySet()) {
								if (entry.getValue() == graph.getMinWeightCycle()) {

									showAllCycles.append("<b style=\"font-size: 12px;\">" + showCycles(entry.getKey())
											+ " --- " + entry.getValue() + "</b><br>");
								} else {
									showAllCycles
											.append(showCycles(entry.getKey()) + " --- " + entry.getValue() + "<br>");
								}
							}
							showAllCycles.append("</pre></body></html>");
							view.textArea.setText(showAllCycles.toString());
							//
							resetTable();
							if (graph.getLastShortestCycle() != null) {
								// in ra JTable
								int countRe = 0;
								FontMetrics fm = view.table.getFontMetrics(view.table.getFont());
								int pixelWidthC1 = 385;
								int pixelWidthC2 = 385;
								view.tf_chiphi.setText(graph.getMinWeightCycle() + "");
								for (Map.Entry<List<Integer>, Integer> entry : graph.getShortPathsOrCycles()
										.entrySet()) {
									if (entry.getValue() == graph.getMinWeightCycle()) {
										countRe++;
										String tenBD = "";
										String path = "";

										for (int i = 0; i < entry.getKey().size(); i++) {
											if (i < entry.getKey().size() - 1) {
												tenBD += graph.getNameVers().get(entry.getKey().get(i)) + " => ";
												path += entry.getKey().get(i) + 1 + " => ";
											} else {
												tenBD += graph.getNameVers().get(entry.getKey().get(i));
												path += entry.getKey().get(i) + 1;
											}
										}
										view.model.addRow(new Object[] { path, tenBD });
										if (fm.stringWidth(path) > pixelWidthC1)
											pixelWidthC1 = fm.stringWidth(path);
										if (fm.stringWidth(tenBD) > pixelWidthC1)
											pixelWidthC2 = fm.stringWidth(tenBD);
									}
								}
								view.tf_soKQ.setText(countRe + "");
								view.table.getColumnModel().getColumn(0).setPreferredWidth(pixelWidthC1 + 10);
								view.table.getColumnModel().getColumn(1).setPreferredWidth(pixelWidthC2 + 10);
								//
							} else {
								JOptionPane.showMessageDialog(null, "Không có chu trình!!!", null,
										JOptionPane.INFORMATION_MESSAGE);
								resetTable();
								view.model.addRow(new Object[] { "Khong co chu trinh di qua tat ca cac diem", "" });
							}

						}
					}
				}
			}
		}
	}

	// phu
	private List<Integer> showCycles(List<Integer> l) {
		List<Integer> re = new ArrayList<>();
		for (int i = 0; i < l.size(); i++) {
			re.add(l.get(i) + 1);
		}
		return re;
	}

	public void restartDrawGraph(Graph graph) {
		Component[] components = view.panel_drawGraph.getComponents();
		for (Component component : components) {
			view.panel_drawGraph.remove(component);
			view.panel_drawGraph.validate();
		}
		view.panel_drawGraph.repaint();
		d = new DrawGraph(graph);
		view.panel_drawGraph.add(d);
		view.frame_Draw.repaint();
	}

	private void showMaTranTS() {
		String[] tenCots = new String[graph.getNumVers()];
		for (int i = 0; i < graph.getNumVers(); i++) {
			tenCots[i] = i + 1 + "";
		}
		view.table_matrantrongso.setModel(new DefaultTableModel(new Object[][] {}, tenCots));
		view.model_matrantrongso = (DefaultTableModel) view.table_matrantrongso.getModel();
		String[] rows = new String[graph.getNumVers()];
		for (int i = 0; i < graph.getNumVers(); i++) {
			for (int j = 0; j < graph.getNumVers(); j++) {
				if (graph.getMatrixAdj()[i][j] == 0) {
					rows[j] = "∞";
				} else
					rows[j] = "<html><b>" + graph.getMatrixAdj()[i][j] + "</b></html>";
			}
			view.model_matrantrongso.addRow(rows);
		}

		if (graph.getNumVers() >= 10) {
			for (int i = 0; i < graph.getNumVers(); i++) {
				view.table_matrantrongso.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				view.table_matrantrongso.getColumnModel().getColumn(i).setPreferredWidth(25);
			}
		} else {
			view.table_matrantrongso.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}
	}

	private String mapToAreaText(Map<Integer, String> m) {
		String re = "";
		for (Map.Entry<Integer, String> entry : m.entrySet()) {
			re = re + (entry.getKey() + 1) + " - " + entry.getValue() + "\n";
		}
		return re;

	}

	private void showTenBuuDien() {
		view.textArea_tenbuudien.setText(mapToAreaText(graph.getNameVers()));
	}

	private void resetTable() {
		view.table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Kết Quả", "Tương ứng" }));
		view.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		view.table.getColumnModel().getColumn(0).setPreferredWidth(385);
		view.table.getColumnModel().getColumn(1).setPreferredWidth(385);
		view.tf_soKQ.setText("");
		while (view.model.getRowCount() != 0) {
			view.model.removeRow(0);
		}
		view.model = (DefaultTableModel) view.table.getModel();

	}

	private void checkIG() {
		view.tf_sobuudien.setText(graph.getNumVers() + "");
		view.cb_checkLienThong.setSelected(false);
		view.cb_checkHamilton.setSelected(false);
		view.cb_checkVoHuong.setSelected(false);
		view.cb_checkCoHuong.setSelected(false);

		if (graph.isConnected()) {
			view.cb_checkLienThong.setSelected(true);
		}

		if (graph.isHamiltonGraph() && graph.getCheckVoHuong() == true)
			view.cb_checkHamilton.setSelected(true);
		else if (graph.getCheckVoHuong() == false) {
			graph.findShortestCycle(0);
			if (graph.getLastShortestCycle() != null)
				view.cb_checkHamilton.setSelected(true);
		}
		if (view.bt_initVoHuong.isSelected())
			view.cb_checkVoHuong.setSelected(true);
		else if (view.bt_initCoHuong.isSelected())
			view.cb_checkCoHuong.setSelected(true);

	}

	private void save(String fileName) {
		try {
			PrintWriter pw = new PrintWriter(fileName, "UTF-8");
			String data = view.myNotepadView.textArea.getText();
			pw.print(data);
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void resetInterfaceWhenEventHandling() {
		view.tf_chiphi.setText("");
		view.tf_soKQ.setText("");
		resetTable();
		view.textArea.setText("");
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (view.table.getRowSelectionAllowed()) {
			if (!e.getValueIsAdjusting()) {
				int selectedRow = view.table.getSelectedRow();
				// xử lý khi hàng được chọn
				if (view.choice_Path.isSelected()) {
					List<Integer> temp = getRe(selectedRow);
					if (temp != null) {
						Integer[] re = new Integer[temp.size()];
						for (int i = 0; i < temp.size(); i++) {
							re[i] = temp.get(i);
						}
						// vẽ lại
						Component[] components = view.panel_drawGraph.getComponents();
						for (Component component : components) {
							view.panel_drawGraph.remove(component);
							view.panel_drawGraph.validate();
						}
						view.panel_drawGraph.repaint();
						DrawGraph d = new DrawGraph(graph, Arrays.asList(re));
						view.panel_drawGraph.add(d);

						view.tf_chiphi.setText(graph.getShortPathsOrCycles().get(temp) + "");
						view.tf_Draw.setText(graph.getShortPathsOrCycles().get(temp) + "");
					}
				} else if (view.choice_Cycle.isSelected()) {
					List<Integer> temp = getRe(selectedRow);
					if (temp != null) {
						Integer[] re = new Integer[temp.size()];
						for (int i = 0; i < temp.size(); i++) {
							re[i] = temp.get(i);
						}
						// vẽ lại
						graph.setLastShortestCycle(re);
						Component[] components = view.panel_drawGraph.getComponents();
						for (Component component : components) {
							view.panel_drawGraph.remove(component);
							view.panel_drawGraph.validate();
						}
						view.panel_drawGraph.repaint();
						DrawGraph d = new DrawGraph(graph, Arrays.asList(graph.getLastShortestCycle()));
						view.panel_drawGraph.add(d);

						view.tf_chiphi.setText(graph.getMinWeightCycle() + "");
						view.tf_Draw.setText(graph.getMinWeightCycle() + "");
					}
				}
			}
		}
	}

	private List<Integer> getRe(int index) {

		int count = 0;
		for (Map.Entry<List<Integer>, Integer> entry : graph.getShortPathsOrCycles().entrySet()) {
			if (index == count) {
				return entry.getKey();
			}
			count++;
		}
		return null;
	}
}
