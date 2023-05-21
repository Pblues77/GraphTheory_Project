package drawGraph;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Location extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Location frame = new Location();
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
	public Location() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(806, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton b1 = new JButton("New button");
		b1.setBounds(235, 31, 44, 21);
		contentPane.add(b1);
		
		JButton b2 = new JButton("New button");
		b2.setBounds(551, 31, 44, 21);
		contentPane.add(b2);
		
		JButton b3 = new JButton("New button");
		b3.setBounds(669, 77, 44, 21);
		contentPane.add(b3);
		
		JButton b5 = new JButton("New button");
		b5.setBounds(715, 268, 44, 21);
		contentPane.add(b5);
		
		JButton b6 = new JButton("New button");
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		b6.setBounds(669, 370, 44, 21);
		contentPane.add(b6);
		
		JButton b7 = new JButton("New button");
		b7.setBounds(579, 437, 44, 21);
		contentPane.add(b7);
		
		JButton b8 = new JButton("New button");
		b8.setBounds(217, 437, 44, 21);
		contentPane.add(b8);
		
		JButton b9 = new JButton("New button");
		b9.setBounds(125, 370, 44, 21);
		contentPane.add(b9);
		
		JButton b10 = new JButton("New button");
		b10.setBounds(70, 268, 44, 21);
		contentPane.add(b10);
		
		JButton b11 = new JButton("New button");
		b11.setBounds(70, 160,  44, 21);
		contentPane.add(b11);
		
		JButton b12 = new JButton("New button");
		b12.setBounds(125, 77, 44, 21);
		contentPane.add(b12);
		
		JButton b4 = new JButton("New button");
		b4.setBounds(715, 160, 44, 21);
		contentPane.add(b4);
		
		JButton b7_1 = new JButton("New button");
		b7_1.setBounds(457, 469, 44, 21);
		contentPane.add(b7_1);
		
		JButton b1_1 = new JButton("New button");
		b1_1.setBounds(390, 10, 44, 21);
		contentPane.add(b1_1);
		
		JButton b7_1_1 = new JButton("New button");
		b7_1_1.setBounds(333, 469, 44, 21);
		contentPane.add(b7_1_1);
	}
}
