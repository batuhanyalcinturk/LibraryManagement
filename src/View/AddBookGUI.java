package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Model.Book;
import Model.PrimeAdmin;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddBookGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_bName;
	private JTextField fld_bWriter;
	private JTextField fld_bPage;
	private Book book = new Book();
	PrimeAdmin primeadmin = new PrimeAdmin();
	String[] bookCategory = { "Bilim-Kurgu", "Macera", "Polisiye"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookGUI frame = new AddBookGUI();
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
	public AddBookGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_bName = new JLabel("Kitap Ad\u0131:");
		lbl_bName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_bName.setBounds(10, 25, 97, 31);
		contentPane.add(lbl_bName);
		
		fld_bName = new JTextField();
		fld_bName.setBounds(106, 25, 220, 33);
		contentPane.add(fld_bName);
		fld_bName.setColumns(10);
		
		fld_bWriter = new JTextField();
		fld_bWriter.setColumns(10);
		fld_bWriter.setBounds(106, 68, 220, 33);
		contentPane.add(fld_bWriter);
		
		JLabel lbl_bWriter = new JLabel("Yazar Adý:");
		lbl_bWriter.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_bWriter.setBounds(10, 68, 97, 31);
		contentPane.add(lbl_bWriter);
		
		fld_bPage = new JTextField();
		fld_bPage.setColumns(10);
		fld_bPage.setBounds(106, 111, 220, 33);
		contentPane.add(fld_bPage);
		
		JLabel lbl_bPage = new JLabel("Sayfa Sayýsý:");
		lbl_bPage.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_bPage.setBounds(10, 111, 97, 31);
		contentPane.add(lbl_bPage);
		
		JLabel lbl_bCategory = new JLabel("Kategori:");
		lbl_bCategory.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_bCategory.setBounds(10, 154, 97, 31);
		contentPane.add(lbl_bCategory);
		
		JComboBox cmb_bCategory = new JComboBox(bookCategory);
		cmb_bCategory.setBackground(Color.WHITE);
		cmb_bCategory.setBounds(106, 154, 220, 33);
		contentPane.add(cmb_bCategory);
		
		JButton btn_bAdd = new JButton("Ekle");
		btn_bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_bName.getText().length() == 0 || fld_bWriter.getText().length() == 0
						|| fld_bPage.getText().length() == 0 || cmb_bCategory.getSelectedItem().toString().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = book.addBook(fld_bName.getText(), fld_bPage.getText(), fld_bWriter.getText(),
								cmb_bCategory.getSelectedItem());
						if (control) {
							Helper.showMsg("success");
							dispose();
						} else {
							Helper.showMsg("error");
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
			
		});
		btn_bAdd.setFont(new Font("SansSerif", Font.PLAIN, 17));
		btn_bAdd.setBounds(101, 226, 133, 33);
		contentPane.add(btn_bAdd);
	}
	
}
