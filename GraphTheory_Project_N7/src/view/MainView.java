package view;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.ControllerJFrame;
import open_edit_txt.MyNotepadView;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainView extends JFrame {

	/**
	 * Source code(Hold down Ctrl and click):
	 * https://github.com/PhungTTT/GraphTheory_Project.git
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public ImageIcon icon_logo = new ImageIcon("images/graph.png");
	public JPanel panel_drawGraph;
	public JButton btn_home;
	public JTextField tf_sobuudien;
	public JTextField tf_diemdau;
	public JTextField tf_diemcuoi;
	public JTextField tf_khoangcach;
	public JTextArea textArea_tenbuudien;
	public JTextField lb_pathfile;
	public JButton bt_openfile;
	public JButton bt_themcanh;
	public JButton bt_sobuudien;
	public JTextField tf_tendinh;
	public JTextField tf_dinhthemten;
	public JButton bt_themtendinh;
	public JTextField tf_dinhcanxoa;
	public JButton bt_xoacanh;
	public JButton bt_run;
	public JLabel label_nlu;
	public JTable table;
	public JTextField ver_start;
	public JTextField ver_end;
	public DefaultTableModel model;
	public JTable table_matrantrongso;
	public DefaultTableModel model_matrantrongso;
	public JRadioButton choice_Path;
	public JRadioButton choice_Cycle;
	public JTextField tf_tendinhmoi;
	public JEditorPane textArea;
	public JTextField tf_chiphi;
	public JButton bt_themdinh;
	public JPanel panel_dschutrinh;
	public JTextField tf_soKQ;
	public JCheckBox cb_checkLienThong;
	public JCheckBox cb_checkHamilton;
	public JCheckBox cb_checkCoHuong;
	public JCheckBox cb_checkVoHuong;
	public MyNotepadView myNotepadView = new MyNotepadView();
	public JButton bt_xoadinh;
	public JRadioButton bt_initVoHuong;
	public JRadioButton bt_initCoHuong;
	public JButton bt_saveAs;
	public JFrame frame_Draw;

	public Dimension screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
	public JTextField tf_Draw;
	private JMenuBar menuBar;

	/**
	 * Launch the application.
	 */
	//
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
//		Dimension screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
//		System.out.println(screenSize.width+" - " +screenSize.height+" - "+(1920-0.2*1920));
//		if(screenSize.width >=1536 && screenSize.height >=834 && (1920-0.2*1920)==screenSize.width) {
		setBackground(Color.LIGHT_GRAY);
		menubar();
		init();
		eventHandling();
//		}else {
//			JOptionPane.showMessageDialog(null, "This version is only suitable for display resolution equal to 1920 x 1080 and application size equal to 125%", null, JOptionPane.INFORMATION_MESSAGE);
//			if(JOptionPane.OK_OPTION==0) System.exit(0);
//		}

	}

	void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		this.setIconImage(icon_logo.getImage());
		this.setTitle("Bài toán đường đi người đưa thư");
		this.setSize(790, (int) screenSize.getHeight());
		this.setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// panel initGraph
		panel_initGraph();
		// panel editGraph
		panel_editGraph();
		// panel draw
		showFrameDrawGrpah();
		// panel matrantrongso
		panel_matrantrongso();
		// panel run
		panel_run();
		// ds chu trinh
		panel_dschutrinh();
	}

	private void panel_initGraph() {
		JPanel panel_initGraph = new JPanel();
		panel_initGraph.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Kh\u1EDFi t\u1EA1o \u0111\u01A1n \u0111\u1ED3 th\u1ECB", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 255)));
		panel_initGraph.setBounds(0, 0, 285, 94);
		contentPane.add(panel_initGraph);
		panel_initGraph.setLayout(null);

		JLabel lb_sobuudien = new JLabel("Số điểm bưu điện:");
		lb_sobuudien.setFont(new Font("Tahoma", Font.BOLD, 10));
		lb_sobuudien.setBounds(10, 63, 128, 19);
		panel_initGraph.add(lb_sobuudien);

		tf_sobuudien = new JTextField();
		tf_sobuudien.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_sobuudien.setBounds(110, 63, 84, 19);
		panel_initGraph.add(tf_sobuudien);
		tf_sobuudien.setColumns(10);

		bt_sobuudien = new JButton("Nhập");
		bt_sobuudien.setBounds(194, 62, 73, 21);
		panel_initGraph.add(bt_sobuudien);

		bt_openfile = new JButton("Open File");
		bt_openfile.setHorizontalAlignment(SwingConstants.LEFT);
		bt_openfile.setFont(new Font("Tahoma", Font.BOLD, 9));
		bt_openfile.setBounds(10, 15, 80, 21);
		panel_initGraph.add(bt_openfile);

		lb_pathfile = new JTextField("");
		lb_pathfile.setEditable(false);
		lb_pathfile.setFont(new Font("Tahoma", Font.BOLD, 9));
		lb_pathfile.setBounds(95, 16, 180, 19);
		panel_initGraph.add(lb_pathfile);

		JLabel lb3 = new JLabel("HOẶC");
		lb3.setForeground(Color.BLUE);
		lb3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		lb3.setHorizontalAlignment(SwingConstants.CENTER);
		lb3.setBounds(10, 35, 265, 13);
		panel_initGraph.add(lb3);

		ButtonGroup btg_initGraph = new ButtonGroup();
		bt_initVoHuong = new JRadioButton("Vô hướng");
		btg_initGraph.add(bt_initVoHuong);
		bt_initVoHuong.setBounds(10, 40, 103, 21);
		panel_initGraph.add(bt_initVoHuong);

		bt_initCoHuong = new JRadioButton("Có Hướng");
		btg_initGraph.add(bt_initCoHuong);
		bt_initCoHuong.setHorizontalAlignment(SwingConstants.RIGHT);
		bt_initCoHuong.setBounds(164, 40, 103, 21);
		panel_initGraph.add(bt_initCoHuong);

	}

	private void panel_editGraph() {
		JPanel panel_editGraph = new JPanel();
		panel_editGraph.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ch\u1EC9nh s\u1EEDa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel_editGraph.setBounds(0, 94, 285, 259);
		contentPane.add(panel_editGraph);
		panel_editGraph.setLayout(null);

		tf_dinhthemten = new JTextField();
		tf_dinhthemten.setBounds(47, 31, 23, 19);
		panel_editGraph.add(tf_dinhthemten);
		tf_dinhthemten.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_dinhthemten.setColumns(10);

		JLabel lb5 = new JLabel("Đỉnh:");
		lb5.setBounds(20, 34, 30, 13);
		panel_editGraph.add(lb5);
		lb5.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JLabel lb4 = new JLabel("Tên điểm bưu điện:");
		lb4.setBounds(10, 16, 128, 19);
		panel_editGraph.add(lb4);
		lb4.setFont(new Font("Tahoma", Font.BOLD, 10));

		JLabel lb6 = new JLabel("Tên:");
		lb6.setBounds(79, 34, 30, 13);
		panel_editGraph.add(lb6);
		lb6.setFont(new Font("Tahoma", Font.PLAIN, 10));

		tf_tendinh = new JTextField();
		tf_tendinh.setBounds(110, 32, 84, 19);
		panel_editGraph.add(tf_tendinh);
		tf_tendinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_tendinh.setColumns(10);

		bt_themtendinh = new JButton("Nhập");
		bt_themtendinh.setBounds(194, 31, 73, 21);
		panel_editGraph.add(bt_themtendinh);

		JLabel lb_themdinh = new JLabel("Tạo điểm bưu điện mới:");
		lb_themdinh.setBounds(10, 62, 128, 13);
		panel_editGraph.add(lb_themdinh);
		lb_themdinh.setFont(new Font("Tahoma", Font.BOLD, 10));

		JLabel lblNewLabel = new JLabel("Tên bưu điện cần tạo:");
		lblNewLabel.setBounds(10, 85, 128, 13);
		panel_editGraph.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));

		bt_themdinh = new JButton("Nhập");
		bt_themdinh.setBounds(194, 81, 73, 21);
		panel_editGraph.add(bt_themdinh);

		JLabel lb_themdinh_1 = new JLabel("Xóa điểm bưu điện:");
		lb_themdinh_1.setBounds(10, 111, 128, 13);
		panel_editGraph.add(lb_themdinh_1);
		lb_themdinh_1.setFont(new Font("Tahoma", Font.BOLD, 10));

		JLabel lblimBuin = new JLabel("Điểm cần xóa:");
		lblimBuin.setBounds(10, 130, 128, 13);
		panel_editGraph.add(lblimBuin);
		lblimBuin.setFont(new Font("Tahoma", Font.PLAIN, 10));

		tf_dinhcanxoa = new JTextField();
		tf_dinhcanxoa.setBounds(110, 127, 84, 19);
		panel_editGraph.add(tf_dinhcanxoa);
		tf_dinhcanxoa.setColumns(10);

		bt_xoadinh = new JButton("Xóa ");
		bt_xoadinh.setBounds(194, 126, 73, 21);
		panel_editGraph.add(bt_xoadinh);

		JLabel lb_themcanh = new JLabel("Thêm/Xóa đường nối giữa 2 điểm bưu điện:");
		lb_themcanh.setBounds(10, 153, 230, 19);
		panel_editGraph.add(lb_themcanh);
		lb_themcanh.setFont(new Font("Tahoma", Font.BOLD, 10));

		tf_diemdau = new JTextField();
		tf_diemdau.setBounds(72, 174, 37, 19);
		panel_editGraph.add(tf_diemdau);
		tf_diemdau.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_diemdau.setColumns(10);

		tf_diemcuoi = new JTextField();
		tf_diemcuoi.setBounds(72, 199, 37, 19);
		panel_editGraph.add(tf_diemcuoi);
		tf_diemcuoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_diemcuoi.setColumns(10);

		tf_khoangcach = new JTextField();
		tf_khoangcach.setBounds(72, 220, 37, 27);
		panel_editGraph.add(tf_khoangcach);
		tf_khoangcach.setFont(new Font("Tahoma", Font.BOLD, 12));
		tf_khoangcach.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Điểm đầu");
		lblNewLabel_1.setBounds(20, 176, 60, 13);
		panel_editGraph.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JLabel lb1 = new JLabel("Điểm cuối");
		lb1.setBounds(20, 201, 60, 13);
		panel_editGraph.add(lb1);
		lb1.setFont(new Font("Tahoma", Font.PLAIN, 10));

		JLabel lb2 = new JLabel("<html>Khoảng<br>cách</html>");
		lb2.setBounds(20, 220, 46, 27);
		panel_editGraph.add(lb2);
		lb2.setFont(new Font("Tahoma", Font.PLAIN, 10));

		bt_themcanh = new JButton("Thêm");
		bt_themcanh.setBounds(119, 220, 148, 27);
		panel_editGraph.add(bt_themcanh);

		bt_xoacanh = new JButton("Xóa");
		bt_xoacanh.setBounds(194, 177, 73, 35);
		panel_editGraph.add(bt_xoacanh);

		JLabel lb_tendinh = new JLabel("");
		lb_tendinh.setBounds(111, 84, 80, 18);
		panel_editGraph.add(lb_tendinh);

		tf_tendinhmoi = new JTextField();
		tf_tendinhmoi.setBounds(110, 84, 84, 19);
		panel_editGraph.add(tf_tendinhmoi);
		tf_tendinhmoi.setColumns(10);
	}

	public void showFrameDrawGrpah() {
		panel_drawGraph = new JPanel();
		panel_drawGraph.setBackground(Color.gray);
		panel_drawGraph.setLayout(new BorderLayout());

		frame_Draw = new JFrame();
		frame_Draw.setIconImage(icon_logo.getImage());
		frame_Draw.setTitle("Hình vẽ minh họa");
		frame_Draw.setBackground(Color.gray);
		frame_Draw.getContentPane().add(panel_drawGraph, BorderLayout.CENTER);

		tf_Draw = new JTextField();
		tf_Draw.setForeground(Color.red);
		tf_Draw.setBackground(Color.gray);
		tf_Draw.setEditable(false);
		tf_Draw.setFont(new Font("Arial", Font.PLAIN, 50));
		tf_Draw.setHorizontalAlignment(SwingConstants.CENTER);
		frame_Draw.getContentPane().add(tf_Draw, BorderLayout.SOUTH);

		frame_Draw.setSize(790, (int) screenSize.getHeight());
		frame_Draw.setLocation(this.getX() + this.getWidth() - 20, this.getY());
		frame_Draw.setResizable(false);
		frame_Draw.setVisible(true);
		frame_Draw.addWindowListener(new WindowAdapter() {
			// for closing
			@Override
			public void windowClosing(WindowEvent e) {
				frame_Draw = new JFrame();
				frame_Draw.setBackground(Color.gray);
				frame_Draw.addWindowListener(this);
				frame_Draw.add(tf_Draw, BorderLayout.SOUTH);
				frame_Draw.setIconImage(icon_logo.getImage());
				frame_Draw.setTitle("Hình vẽ minh họa");
				frame_Draw.getContentPane().add(panel_drawGraph, BorderLayout.CENTER);
				frame_Draw.setSize(790, (int) screenSize.getHeight());
				frame_Draw.setLocation(getX() + getWidth() - 20, getY());
				frame_Draw.setResizable(false);
				frame_Draw.setVisible(true);
			}
			// for closed
		});
	}

	private void panel_matrantrongso() {
		table_matrantrongso = new JTable();
		table_matrantrongso.setBorder(null);
		table_matrantrongso.setRowSelectionAllowed(false);
		table_matrantrongso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table_matrantrongso.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		model_matrantrongso = (DefaultTableModel) table_matrantrongso.getModel();

		JScrollPane sp_tableMatrantrongso = new JScrollPane(table_matrantrongso);
		sp_tableMatrantrongso.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_tableMatrantrongso.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp_tableMatrantrongso.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ma tr\u1EADn tr\u1ECDng s\u1ED1", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		sp_tableMatrantrongso.setBounds(0, 0, 282, 213);

		JPanel panel_matrantrongso = new JPanel();
		panel_matrantrongso.setBounds(286, 0, 285, 213);
		panel_matrantrongso.setLayout(null);
		panel_matrantrongso.add(sp_tableMatrantrongso);
		contentPane.add(panel_matrantrongso);
	}

	private void panel_dschutrinh() {
		panel_dschutrinh = new JPanel();
		panel_dschutrinh.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh s\u00E1ch c\u00E1c chu tr\u00ECnh", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 255)));
		panel_dschutrinh.setBounds(574, 0, 201, 353);
		contentPane.add(panel_dschutrinh);
		panel_dschutrinh.setLayout(null);

		textArea = new JEditorPane("text/html", "");
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea.setBounds(1, 1, 369, 527);
		panel_dschutrinh.add(textArea);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 23, 181, 320);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel_dschutrinh.add(scrollPane);
		// hiển thị tên bưu điện
		textArea_tenbuudien = new JTextArea();
		textArea_tenbuudien.setEditable(false);
		textArea_tenbuudien.setBounds(1, 1, 283, 219);
		textArea_tenbuudien.setFont(new Font("Courier New", Font.BOLD, 22));
		contentPane.add(textArea_tenbuudien);

		JScrollPane sp_tenbuudien = new JScrollPane(textArea_tenbuudien);
		sp_tenbuudien.setBounds(286, 216, 285, 136);
		contentPane.add(sp_tenbuudien);
		sp_tenbuudien.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00EAn c\u00E1c b\u01B0u \u0111i\u1EC7n t\u01B0\u01A1ng \u1EE9ng", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 255)));

	}

	private void panel_run() {
		JPanel panel_run = new JPanel();
		panel_run.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"K\u1EBFt Qu\u1EA3", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel_run.setBounds(0, 350, 775, 136);
		contentPane.add(panel_run);
		panel_run.setLayout(null);

		JLabel lb7 = new JLabel("Đỉnh bắt đầu:");
		lb7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb7.setBounds(386, 55, 75, 13);
		panel_run.add(lb7);

		JLabel lb8 = new JLabel("Đỉnh đến:");
		lb8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lb8.setBounds(386, 80, 57, 13);
		panel_run.add(lb8);

		ver_start = new JTextField();
		ver_start.setEditable(false);
		ver_start.setFont(new Font("Tahoma", Font.BOLD, 12));
		ver_start.setColumns(10);
		ver_start.setBounds(472, 53, 79, 19);
		panel_run.add(ver_start);

		ver_end = new JTextField();
		ver_end.setEditable(false);
		ver_end.setFont(new Font("Tahoma", Font.BOLD, 12));
		ver_end.setColumns(10);
		ver_end.setBounds(472, 76, 79, 19);
		panel_run.add(ver_end);

		bt_run = new JButton("TÌM");
		bt_run.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bt_run.setBounds(578, 53, 187, 37);
		panel_run.add(bt_run);

		ButtonGroup gr = new ButtonGroup();
		choice_Path = new JRadioButton("Tìm đường đi ngắn nhất");
		choice_Path.setFont(new Font("Tahoma", Font.BOLD, 12));
		choice_Path.setBounds(578, 19, 174, 21);
		gr.add(choice_Path);
		panel_run.add(choice_Path);

		choice_Cycle = new JRadioButton("Tìm chu trình ngắn nhất");
		choice_Cycle.setFont(new Font("Tahoma", Font.BOLD, 12));
		choice_Cycle.setBounds(386, 19, 179, 21);
		gr.add(choice_Cycle);
		panel_run.add(choice_Cycle);

		cb_checkLienThong = new JCheckBox("<html>Đồ thị liên thông</html>");
		cb_checkLienThong.setFont(new Font("Tahoma", Font.BOLD, 14));
		cb_checkLienThong.setEnabled(false);
		cb_checkLienThong.setBounds(189, 21, 192, 21);
		panel_run.add(cb_checkLienThong);

		cb_checkHamilton = new JCheckBox("<html>Đồ thị có chu trình <br>Hamilton</html>");
		cb_checkHamilton.setFont(new Font("Tahoma", Font.BOLD, 14));
		cb_checkHamilton.setEnabled(false);
		cb_checkHamilton.setBounds(189, 50, 179, 34);
		panel_run.add(cb_checkHamilton);

		cb_checkVoHuong = new JCheckBox("<html>Là đồ thị vô hướng</html>");
		cb_checkVoHuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		cb_checkVoHuong.setEnabled(false);
		cb_checkVoHuong.setBounds(6, 21, 174, 21);
		panel_run.add(cb_checkVoHuong);

		cb_checkCoHuong = new JCheckBox("<html>Là đồ thị có hướng</html>");
		cb_checkCoHuong.setFont(new Font("Tahoma", Font.BOLD, 14));
		cb_checkCoHuong.setEnabled(false);
		cb_checkCoHuong.setBounds(6, 56, 174, 21);
		panel_run.add(cb_checkCoHuong);

		JLabel lblNewLabel_2 = new JLabel("Chi Phí (Khoảng cách):");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(6, 105, 145, 21);
		panel_run.add(lblNewLabel_2);

		tf_chiphi = new JTextField();
		tf_chiphi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		tf_chiphi.setEditable(false);
		tf_chiphi.setBounds(161, 104, 223, 21);
		panel_run.add(tf_chiphi);
		tf_chiphi.setColumns(10);

		JLabel llblNewLabel_3 = new JLabel("Số kết quả:");
		llblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		llblNewLabel_3.setBounds(394, 105, 75, 21);
		panel_run.add(llblNewLabel_3);

		tf_soKQ = new JTextField();
		tf_soKQ.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		tf_soKQ.setEditable(false);
		tf_soKQ.setColumns(10);
		tf_soKQ.setBounds(472, 104, 293, 21);
		panel_run.add(tf_soKQ);

		// table
		table = new JTable();
		table.setBorder(null);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Kết Quả", "Tương ứng" }));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(385);
		table.getColumnModel().getColumn(1).setPreferredWidth(385);
		table.setRowHeight(30);
		model = (DefaultTableModel) table.getModel();

		JPanel panel_tableResult = new JPanel();
		contentPane.add(panel_tableResult);

		int taskbarheight = Toolkit.getDefaultToolkit().getScreenSize().height
				- (int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();
//		System.out.println(taskbarheight);
		if (taskbarheight != 0)
			panel_tableResult.setBounds(1, 486, 775, this.getHeight() - 486 - taskbarheight - 35);
		else
			panel_tableResult.setBounds(1, 486, 775, this.getHeight() - 486 - 70);
		panel_tableResult.setLayout(null);

		JScrollPane sp_tableResult = new JScrollPane(table);
		sp_tableResult.setBounds(0, 0, 775, panel_tableResult.getHeight());

		panel_tableResult.add(sp_tableResult);
	}

	void menubar() {
		// menubar
		menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		this.setJMenuBar(menuBar);

		btn_home = new JButton("Tạo mới");
		menuBar.add(btn_home);

		bt_saveAs = new JButton("Save As");
		menuBar.add(bt_saveAs);

	}

	private void eventHandling() {

		ControllerJFrame a = new ControllerJFrame(this);

		btn_home.addActionListener(a);
		bt_openfile.addActionListener(a);
		bt_sobuudien.addActionListener(a);
		bt_themtendinh.addActionListener(a);
		bt_themcanh.addActionListener(a);
		bt_xoacanh.addActionListener(a);
		bt_run.addActionListener(a);
		choice_Path.addActionListener(a);
		choice_Cycle.addActionListener(a);
		bt_themdinh.addActionListener(a);
		bt_xoadinh.addActionListener(a);
		bt_saveAs.addActionListener(a);
		myNotepadView.bt_exportGraph.addActionListener(a);
		myNotepadView.button_Open.addActionListener(a);
		myNotepadView.button_Save.addActionListener(a);
		myNotepadView.button_New.addActionListener(a);
		table.getSelectionModel().addListSelectionListener(a);
	}
}
