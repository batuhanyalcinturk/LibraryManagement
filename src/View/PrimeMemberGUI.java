package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class PrimeMemberGUI extends JFrame {

	static PrimeMember primember = new PrimeMember();

	private JPanel w_pane;
	private JTextField fld_bookName;
	private JTable table_book;
	private DefaultTableModel bookModel = null;
	private Object[] bookData = null;
	private JTextField fld_bName;
	private JTextField fld_bWriter;
	private JTextField fld_bPage;
	String[] bookCategory = { "Bilim-Kurgu", "Macera", "Roman", "Hikaye","Biyografi" };

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
	 * 
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PrimeMemberGUI(PrimeMember primember) throws SQLException {

		bookModel = new DefaultTableModel();
		Object[] colBookName = new Object[5];
		colBookName[0] = "Kitap Ýsmi";
		colBookName[1] = "Sayfa Sayýsý";
		colBookName[2] = "Yazar";
		colBookName[3] = "Kategori";
		colBookName[4] = "Ýçerik";
		bookModel.setColumnIdentifiers(colBookName);
		bookData = new Object[5];
		for (int i = 0; i < primember.getBookList().size(); i++) {
			bookData[0] = primember.getBookList().get(i).getName();
			bookData[1] = primember.getBookList().get(i).getPage();
			bookData[2] = primember.getBookList().get(i).getWriter();
			bookData[3] = primember.getBookList().get(i).getCategory();
			bookData[4] = primember.getBookList().get(i).getInfo();
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

		Label lbl_bookID = new Label("Kitap Ad\u0131:");
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

		JPanel addbook_tab = new JPanel();
		addbook_tab.setLayout(null);
		w_tab.addTab("Kitap Ekle", null, addbook_tab, null);

		JLabel lbl_bName = new JLabel("Kitap Ad\u0131:");
		lbl_bName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_bName.setBounds(321, 27, 97, 31);
		addbook_tab.add(lbl_bName);

		fld_bName = new JTextField();
		fld_bName.setColumns(10);
		fld_bName.setBounds(417, 27, 220, 33);
		addbook_tab.add(fld_bName);

		JLabel lbl_bWriter = new JLabel("Yazar Ad\u0131:");
		lbl_bWriter.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_bWriter.setBounds(321, 70, 97, 31);
		addbook_tab.add(lbl_bWriter);

		fld_bWriter = new JTextField();
		fld_bWriter.setColumns(10);
		fld_bWriter.setBounds(417, 70, 220, 33);
		addbook_tab.add(fld_bWriter);

		JLabel lbl_bPage = new JLabel("Sayfa Say\u0131s\u0131:");
		lbl_bPage.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_bPage.setBounds(321, 113, 97, 31);
		addbook_tab.add(lbl_bPage);

		fld_bPage = new JTextField();
		fld_bPage.setColumns(10);
		fld_bPage.setBounds(417, 113, 220, 33);
		addbook_tab.add(fld_bPage);

		JLabel lbl_bCategory = new JLabel("Kategori:");
		lbl_bCategory.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_bCategory.setBounds(321, 156, 97, 31);
		addbook_tab.add(lbl_bCategory);

		JComboBox cmb_bCategory = new JComboBox(bookCategory);
		cmb_bCategory.setBackground(Color.WHITE);
		cmb_bCategory.setBounds(417, 156, 220, 33);
		addbook_tab.add(cmb_bCategory);

		JLabel lbl_bInfo = new JLabel("\u0130\u00E7erik:");
		lbl_bInfo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_bInfo.setBounds(321, 212, 97, 31);
		addbook_tab.add(lbl_bInfo);

		JTextArea txtarea_bInfo = new JTextArea();
		txtarea_bInfo.setWrapStyleWord(true);
		txtarea_bInfo.setLineWrap(true);
		txtarea_bInfo.setBounds(417, 212, 220, 119);
		addbook_tab.add(txtarea_bInfo);

		JButton btn_bAdd = new JButton("Ekle");
		btn_bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_bName.getText().length() == 0 || fld_bWriter.getText().length() == 0
						|| fld_bPage.getText().length() == 0 || cmb_bCategory.getSelectedItem().toString().length() == 0
						|| txtarea_bInfo.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = primember.addBook(fld_bName.getText(), fld_bPage.getText(),
								fld_bWriter.getText(), cmb_bCategory.getSelectedItem(), txtarea_bInfo.getText());
						if (control) {
							Helper.showMsg("success");
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
		btn_bAdd.setBounds(460, 341, 133, 33);
		addbook_tab.add(btn_bAdd);
		table_book.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					txtar_bInfo.setText(table_book.getValueAt(table_book.getSelectedRow(), 4).toString());
				} catch (Exception ex) {
				}
			}

		});

		JLabel lbl_WelcomePrimeAdmin = new JLabel("Hosgeldiniz Sayýn " + primember.getName());
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
