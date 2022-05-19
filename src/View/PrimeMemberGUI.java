package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.PrimeMember;

import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Label;
import java.sql.SQLException;

import javax.swing.JTable;

public class PrimeMemberGUI extends JFrame {
	
	static PrimeMember primember = new PrimeMember();

	private JPanel w_pane;
	private JTextField fld_bookID;
	private JTable table_book;
	private DefaultTableModel bookModel = null;
	private Object[] bookData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrimeMemberGUI frame = new PrimeMemberGUI(primember);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public PrimeMemberGUI(PrimeMember primember) throws SQLException {
		
		bookModel = new DefaultTableModel();
		Object[] colBookName = new Object[5];
		colBookName[0] = "ID";
		colBookName[1] = "Kitap Ýsmi";
		colBookName[2] = "Sayfa Sayýsý";
		colBookName[3] = "Yazar";
		colBookName[4] = "Kategori";
		bookModel.setColumnIdentifiers(colBookName);
		bookData = new Object[5];
		for (int i = 0; i < primember.getBookList().size(); i++) {
			bookData[0] = primember.getBookList().get(i).getId();
			bookData[1] = primember.getBookList().get(i).getName();
			bookData[2] = primember.getBookList().get(i).getPage();
			bookData[3] = primember.getBookList().get(i).getWriter();
			bookData[4] = primember.getBookList().get(i).getCategory();
			bookModel.addRow(bookData);
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 600);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		w_pane.setLayout(new BorderLayout(0, 0));
		setContentPane(w_pane);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.LIGHT_GRAY);
		w_pane.add(contentPane_1, BorderLayout.CENTER);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 79, 1033, 464);
		contentPane_1.add(w_tab);
		
		JPanel book_tab = new JPanel();
		w_tab.addTab("Kitaplar", null, book_tab, null);
		book_tab.setLayout(null);
		
		JScrollPane w_scrollBook = new JScrollPane();
		w_scrollBook.setBounds(0, 0, 847, 427);
		book_tab.add(w_scrollBook);
		
		table_book = new JTable(bookModel);
		w_scrollBook.setViewportView(table_book);
		
		JButton btn_delBook = new JButton("Kitabý Sil");
		btn_delBook.setBounds(869, 387, 149, 40);
		btn_delBook.setFont(new Font("SansSerif", Font.PLAIN, 15));
		book_tab.add(btn_delBook);
		
		fld_bookID = new JTextField();
		fld_bookID.setBounds(869, 333, 149, 30);
		fld_bookID.setFont(new Font("SansSerif", Font.PLAIN, 14));
		fld_bookID.setEditable(false);
		fld_bookID.setColumns(10);
		book_tab.add(fld_bookID);
		
		Label lbl_bookID = new Label("ID:");
		lbl_bookID.setBounds(869, 296, 40, 30);
		lbl_bookID.setFont(new Font("SansSerif", Font.PLAIN, 15));
		book_tab.add(lbl_bookID);
		
		JLabel lbl_WelcomePrimeAdmin = new JLabel("Hosgeldiniz Sayýn " + primember.getName());
		lbl_WelcomePrimeAdmin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_WelcomePrimeAdmin.setBounds(10, 20, 500, 20);
		contentPane_1.add(lbl_WelcomePrimeAdmin);
		
		JButton btn_Exit = new JButton("Çýkýþ Yap");
		btn_Exit.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_Exit.setFocusable(false);
		btn_Exit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Exit.setBackground(Color.CYAN);
		btn_Exit.setBounds(974, 20, 100, 25);
		contentPane_1.add(btn_Exit);
	}

}
