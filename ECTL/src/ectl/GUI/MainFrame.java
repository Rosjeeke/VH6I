package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import Procedures.ProcedureController;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private JButton btnBrowse;
	private JFileChooser fc;
	private JPanel panel;
	private JLabel lblCurrentfile;
	private JLabel lblStatus;
	private File file = null;
	private MainFrame mf = this;
        private String path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setResizable(false);
		setTitle("VH6B2 - ECTL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280,150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Select File", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 10, 250, 100);
		contentPane.add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(12, 23, 140, 20);
		panel.add(textField);
		textField.setColumns(10);

		btnBrowse = new JButton("Browse");
		fc = new JFileChooser();
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Handle open button action.
				if (e.getSource() == btnBrowse) {
					fc = new JFileChooser();
					int returnVal = fc.showOpenDialog(contentPane);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						file = fc.getSelectedFile();
						//This is where the application would open the file.
						textField.setText(file.getAbsolutePath());
                                                path = textField.getText();
						//setText currentfile
						int dotPos = file.getName().lastIndexOf(".");
						String extension = file.getName().substring(dotPos);
						System.out.println("Opening: " + file.getName() + ".");

					} else {
						System.out.println("Open command cancelled by user.");
					}
				} 
			}
		});
		btnBrowse.setBounds(160, 23, 80, 20);
		panel.add(btnBrowse);

		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ProcedureController(file, mf, path);
			}
		});
		btnRun.setBounds(160, 70, 80, 20);
		panel.add(btnRun);

	}

}
