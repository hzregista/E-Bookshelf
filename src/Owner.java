import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Owner extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Owner frame = new Owner();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Owner() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 249, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(42, 43, 66, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(42, 81, 66, 14);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(235, 62, 162, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(118, 40, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(118, 78, 86, 20);
		contentPane.add(passwordField);
		
		connection conn = new connection();
		
		JButton btnNewButton = new JButton("Go To Bookshelf");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textField.getText();
				String password = new String(passwordField.getPassword());
				boolean result = conn.Login(username, password);
				if (result == true)
				{
					Bookshelf bs = new Bookshelf();
					setVisible(false);
					bs.setVisible(true);
				}
				else
				{
					lblNewLabel_1.setText("Login Failed");
				}
				
			}
		});
		btnNewButton.setBounds(54, 124, 131, 23);
		contentPane.add(btnNewButton);
		
		JButton btnManageAdmins = new JButton("Manage Admins");
		btnManageAdmins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textField.getText();
				String password = new String(passwordField.getPassword());
				boolean result = conn.Login(username, password);
				if (result == true)
				{
					Admins ad = new Admins();
					setVisible(false);
					ad.setVisible(true);
				}
				else
				{
					lblNewLabel_1.setText("Login Failed");
				}
		
			}
		});
		btnManageAdmins.setBounds(54, 157, 131, 23);
		contentPane.add(btnManageAdmins);
		
		
	}
}
