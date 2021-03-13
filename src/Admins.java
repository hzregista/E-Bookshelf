import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admins extends JDialog {
	DefaultTableModel model;
	connection conn = new connection();
	public void showadmin()
	{
		
		model.setRowCount(0);	
		
		ArrayList<Managers> managers = new ArrayList<Managers>();
		managers = conn.bringadmin();
		
		if(managers != null)
		{
			for(Managers managerx : managers)
			{
				Object[] add = {
						managerx.getUserName(),managerx.getPassword()
				};
					model.addRow(add);
			}			
		}
		
		
	}
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	
	public static void main(String[] args) {
		try {
			Admins dialog = new Admins();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public Admins() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {							
			}
		});
		scrollPane.setBounds(10, 11, 157, 206);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int s = table.getSelectedRow();
				textField.setText(model.getValueAt(s, 0).toString());
				textField_1.setText(model.getValueAt(s, 1).toString());	
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"User Name", "Password"
			}
		));
		scrollPane.setViewportView(table);
		{
			JButton btnCreateAdmin = new JButton("Create A New Admin");
			btnCreateAdmin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (textField.getText().equals("") || textField_1.getText().equals(""))
					{JOptionPane.showMessageDialog(null, "Please Enter Information");}
					else {
					String username = textField.getText();
					String password = textField_1.getText();
					conn.adminAdd(username, password);
					showadmin();
					textField.setText("");
					textField_1.setText("");
					}
				}
			});
			btnCreateAdmin.setActionCommand("OK");
			btnCreateAdmin.setBounds(237, 143, 131, 23);
			contentPanel.add(btnCreateAdmin);
		}
		
		textField = new JTextField();
		textField.setBounds(255, 51, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(255, 102, 86, 20);
		contentPanel.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("User Name:");
		lblNewLabel.setBounds(254, 26, 68, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(254, 77, 68, 14);
		contentPanel.add(lblPassword);
		
		JButton btnDeleteAdmin = new JButton("Delete Admin");
		btnDeleteAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("admin") && textField_1.getText().equals("password"))
				{
					JOptionPane.showMessageDialog(null, "This Admin Cannot Be Deleted");
				}
				else if (textField.getText().equals("")||textField_1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter Information of Admin");
				}
				else 				
			    {
				int sr = table.getSelectedRow();					
				if(sr != -1 && sr !=0)
				{
					String dusername = model.getValueAt(sr, 0).toString();
					String dpassword = model.getValueAt(sr, 1).toString();
					conn.adminDelete(dusername,dpassword);
					showadmin();
					textField.setText("");
					textField_1.setText("");					
				}
				else{JOptionPane.showMessageDialog(null, "Please Choose One of Admins");}	
				}
			}
		});
		btnDeleteAdmin.setActionCommand("OK");
		btnDeleteAdmin.setBounds(237, 177, 131, 23);
		contentPanel.add(btnDeleteAdmin);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Owner ow = new Owner();
						setVisible(false);
						ow.setVisible(true);
					}
				});
				{
					JButton okButton = new JButton("Go To Bookshelf");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Bookshelf bs = new Bookshelf();
							setVisible(false);
							bs.setVisible(true);
							
						}
					});
					buttonPane.add(okButton);
					okButton.setActionCommand("OK");
					getRootPane().setDefaultButton(okButton);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		model = (DefaultTableModel)table.getModel();
		showadmin();
		
	}
}
