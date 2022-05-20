package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.Member;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class MemberGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Member member = new Member();

	private JPanel w_pane;
	private JTextField fld_bookName;
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
					MemberGUI frame = new MemberGUI(member);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public MemberGUI(Member member) throws SQLException {

		bookModel = new DefaultTableModel();
		Object[] colBookName = new Object[5];
		colBookName[0] = "Kitap Ýsmi";
		colBookName[1] = "Sayfa Sayýsý";
		colBookName[2] = "Yazar";
		colBookName[3] = "Kategori";
		colBookName[4] = "Ýçerik";
		bookModel.setColumnIdentifiers(colBookName);
		bookData = new Object[5];
		for (int i = 0; i < member.getBookList().size(); i++) {
			bookData[0] = member.getBookList().get(i).getName();
			bookData[1] = member.getBookList().get(i).getPage();
			bookData[2] = member.getBookList().get(i).getWriter();
			bookData[3] = member.getBookList().get(i).getCategory();
			bookData[4] = member.getBookList().get(i).getInfo();
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
		w_scrollBook.setBounds(0, 0, 800, 353);
		book_tab.add(w_scrollBook);

		table_book = new JTable(bookModel);
		w_scrollBook.setViewportView(table_book);
		table_book.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_bookName.setText(table_book.getValueAt(table_book.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
				}
			}

		});

		fld_bookName = new JTextField();
		fld_bookName.setBounds(106, 379, 350, 30);
		fld_bookName.setFont(new Font("SansSerif", Font.PLAIN, 14));
		fld_bookName.setEditable(false);
		fld_bookName.setColumns(2);
		book_tab.add(fld_bookName);

		Label lbl_bookID = new Label("Kitap Adý:");
		lbl_bookID.setBounds(0, 379, 100, 30);
		lbl_bookID.setFont(new Font("SansSerif", Font.PLAIN, 15));
		book_tab.add(lbl_bookID);

		JLabel lbl_bookInfo = new JLabel("Kitap Hakkýnda Bilgi:");
		lbl_bookInfo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_bookInfo.setBounds(810, 0, 208, 30);
		book_tab.add(lbl_bookInfo);
		
		JTextArea txtar_bInfo = new JTextArea();
		txtar_bInfo.setWrapStyleWord(true);
		txtar_bInfo.setLineWrap(true);
		txtar_bInfo.setBounds(810, 40, 208, 313);
		book_tab.add(txtar_bInfo);
		table_book.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					txtar_bInfo.setText(table_book.getValueAt(table_book.getSelectedRow(), 4).toString());
				} catch (Exception ex) {
				}
			}

		});

		JLabel lbl_WelcomePrimeAdmin = new JLabel("Hosgeldiniz Sayýn " + member.getName());
		lbl_WelcomePrimeAdmin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_WelcomePrimeAdmin.setBounds(10, 20, 500, 20);
		contentPane_1.add(lbl_WelcomePrimeAdmin);

		JButton btn_Exit = new JButton("Çýkýþ Yap");
		btn_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lGUI = new LoginGUI();
				lGUI.setVisible(true);
				dispose();
			}
		});
		btn_Exit.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_Exit.setFocusable(false);
		btn_Exit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Exit.setBackground(Color.CYAN);
		btn_Exit.setBounds(974, 20, 100, 25);
		contentPane_1.add(btn_Exit);
	}
}
