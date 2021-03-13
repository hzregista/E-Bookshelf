import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class Bookshelf extends JDialog {
	

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTextField textField_4;
	
	DefaultTableModel model;
	connection conn = new connection();
	
	public static void main(String[] args) {
		try {
			Bookshelf dialog = new Bookshelf();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Bookshelf() {
		setResizable(false);
		setBounds(100, 100, 828, 466);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setBounds(73, 41, 183, 25);
			textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(73, 77, 183, 25);
			textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			textField_1.setColumns(10);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(73, 113, 183, 25);
			textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			textField_2.setColumns(10);
			contentPanel.add(textField_2);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(73, 149, 183, 25);
			textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			textField_3.setColumns(10);
			contentPanel.add(textField_3);
		}
		{
			JLabel lblNewLabel = new JLabel("New Book:");
			lblNewLabel.setBounds(10, 11, 77, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblWriter = new JLabel("Writer:");
			lblWriter.setBounds(10, 83, 46, 14);
			contentPanel.add(lblWriter);
		}
		{
			JLabel lblType = new JLabel("Type:");
			lblType.setBounds(10, 119, 46, 14);
			contentPanel.add(lblType);
		}
		{
			JLabel lblPublisher = new JLabel("Publisher:");
			lblPublisher.setBounds(10, 155, 77, 14);
			contentPanel.add(lblPublisher);
		}
		{
			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(10, 47, 77, 14);
			contentPanel.add(lblName);
		}
		{
			JButton btnNewButton = new JButton("ADD");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(textField.getText().equals(""))
					{JOptionPane.showMessageDialog(null, "Please Enter Book Name");}
					else {
					String name = textField.getText();
					String writer = textField_1.getText();
					String type = textField_2.getText();
					String publisher = textField_3.getText();
					conn.bookAdd(name, writer, type, publisher);
					showbook();	
					}
				}
			});
			btnNewButton.setBounds(33, 201, 89, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnUpdate = new JButton("Update");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = textField.getText();
					String writer = textField_1.getText();
					String type = textField_2.getText();
					String publisher = textField_3.getText();
					int sr = table.getSelectedRow();
					
					if(sr != -1 && sr!=0)
					{
						String bname = model.getValueAt(sr, 0).toString();
						String bwriter = model.getValueAt(sr, 1).toString();
						String btype = model.getValueAt(sr, 2).toString();
						String bpublisher = model.getValueAt(sr, 3).toString();
						conn.bookUpdate(name,writer,type,publisher,bname,bwriter,btype,bpublisher);						
						showbook();
					}
					else{JOptionPane.showMessageDialog(null, "Please Choose One of Rows");}
					
				}
			});
			btnUpdate.setBounds(154, 201, 89, 23);
			contentPanel.add(btnUpdate);
		}
		{
			JButton btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int sr = table.getSelectedRow();					
					if(sr != -1 && sr !=0)
					{
						String dname = model.getValueAt(sr, 0).toString();
						String dwriter = model.getValueAt(sr, 1).toString();
						String dtype = model.getValueAt(sr, 2).toString();
						String dpublisher = model.getValueAt(sr, 3).toString();
						conn.bookDelete(dname,dwriter,dtype,dpublisher);
						showbook();
						
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						
					}
					else{JOptionPane.showMessageDialog(null, "Please Choose One of Rows");}

				}
			});
			btnDelete.setBounds(92, 246, 89, 23);
			contentPanel.add(btnDelete);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(330, 41, 456, 307);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
					int s = table.getSelectedRow();
					textField.setText(model.getValueAt(s, 0).toString());
					textField_1.setText(model.getValueAt(s, 1).toString());
					textField_2.setText(model.getValueAt(s, 2).toString());
					textField_3.setText(model.getValueAt(s, 3).toString());
					}
				});
				scrollPane.setViewportView(table);
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Book Name", "Writer", "Type", "Publisher"
					}
				));
			}
		}
		{
			textField_4 = new JTextField();
			textField_4.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					String query = textField_4.getText();
					filter(query);
				}
			});
			textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			textField_4.setColumns(10);
			textField_4.setBounds(49, 323, 183, 25);
			contentPanel.add(textField_4);
		}
		{
			JLabel lblTypeToSearch = new JLabel("Type To Search:");
			lblTypeToSearch.setBounds(49, 298, 183, 14);
			contentPanel.add(lblTypeToSearch);
		}
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		model =(DefaultTableModel)table.getModel();
		showbook();
		
	}
	public void filter(String query) {
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(trs);
		trs.setRowFilter(RowFilter.regexFilter(query));
	}
	public void showbook()
	{
		model.setRowCount(0);
		ArrayList<Books> books = new ArrayList<Books>();
		books = conn.bringbook();
		
		if(books != null)
		{
			for(Books bookx : books)
			{
				Object[] add = {
						bookx.getBookName(),bookx.getBookWriter(),bookx.getBookType(),bookx.getBookPublisher()	
				};
					model.addRow(add);
			}			
		}
	}
			
}
