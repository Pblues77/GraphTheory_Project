package open_edit_txt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class MyNotepadView extends JFrame {

	private JPanel contentPane;
	public MyNotepadModel model;
	public JLabel lb_filepath;
	public JTextArea textArea;
	public JButton bt_exportGraph;
	public JButton button_Save;
	public JButton button_Open;
	public JButton button_New;

	/**
	 * Create the frame.
	 */
	public MyNotepadView() {
		this.model = new MyNotepadModel();
		setSize(806, 527);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		JScrollPane scrollPane = new JScrollPane(textArea);
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setPreferredSize(new Dimension(10,50));
		getContentPane().add(panel,BorderLayout.SOUTH);
		panel.setLayout(null);
		
		lb_filepath = new JLabel("File Path!");
		lb_filepath.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lb_filepath.setBounds(10, 11, 418, 28);
		getContentPane().add(lb_filepath,BorderLayout.NORTH);
		
		//
		
		
		button_Open = new JButton("Open");
		button_Open.setFont(new Font("Times New Roman", Font.BOLD, 18));
		button_Open.setBounds(98, 13, 79, 23);
		panel.add(button_Open);
		
		 button_Save = new JButton("Save");
		button_Save.setFont(new Font("Times New Roman", Font.BOLD, 18));
		button_Save.setBounds(187, 13, 79, 23);
		panel.add(button_Save);
		
		bt_exportGraph = new JButton("Export");
		bt_exportGraph.setFont(new Font("Times New Roman", Font.BOLD, 18));
		bt_exportGraph.setBounds(681, 13, 101, 23);
		panel.add(bt_exportGraph);
		
		button_New = new JButton("New");
		button_New.setFont(new Font("Times New Roman", Font.BOLD, 18));
		button_New.setBounds(10, 13, 79, 23);
		panel.add(button_New);
		
		this.setVisible(false);
	}
}
