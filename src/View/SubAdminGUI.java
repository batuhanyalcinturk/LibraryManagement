package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import Model.SubAdmin;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Helper.Helper;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SubAdminGUI extends JFrame {

	static SubAdmin subadmin = new SubAdmin();
	private JPanel w_pane;
	private JTextField fld_membID;
	private JTextField fld_bookID;
	private JTable table_book;
	private DefaultTableModel bookModel = null;
	private Object[] bookData = null;
	private JTable table_member;
	private DefaultTableModel memberModel = null;
	private Object[] memberData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubAdminGUI frame = new SubAdminGUI(subadmin);
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
	public SubAdminGUI(SubAdmin subadmin) throws SQLException {

		memberModel = new DefaultTableModel();
		Object[] colMembName = new Object[5];
		colMembName[0] = "ID";
		colMembName[1] = "Ad";
		colMembName[2] = "Soyad";
		colMembName[3] = "TC No";
		colMembName[4] = "Þifre";
		memberModel.setColumnIdentifiers(colMembName);
		memberData = new Object[5];
		for (int i = 0; i < subadmin.getMemberList().size(); i++) {
			memberData[0] = subadmin.getMemberList().get(i).getId();
			memberData[1] = subadmin.getMemberList().get(i).getName();
			memberData[2] = subadmin.getMemberList().get(i).getSurname();
			memberData[3] = subadmin.getMemberList().get(i).getTcno();
			memberData[4] = subadmin.getMemberList().get(i).getPassword();
			memberModel.addRow(memberData);
		}

		bookModel = new DefaultTableModel();
		Object[] colBookName = new Object[5];
		colBookName[0] = "ID";
		colBookName[1] = "Kitap Ýsmi";
		colBookName[2] = "Sayfa Sayýsý";
		colBookName[3] = "Yazar";
		colBookName[4] = "Kategori";
		bookModel.setColumnIdentifiers(colBookName);
		bookData = new Object[5];
		for (int i = 0; i < subadmin.getBookList().size(); i++) {
			bookData[0] = subadmin.getBookList().get(i).getId();
			bookData[1] = subadmin.getBookList().get(i).getName();
			bookData[2] = subadmin.getBookList().get(i).getPage();
			bookData[3] = subadmin.getBookList().get(i).getWriter();
			bookData[4] = subadmin.getBookList().get(i).getCategory();
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

		JPanel member_tab = new JPanel();
		member_tab.setLayout(null);
		w_tab.addTab("Üye Yönetimi", null, member_tab, null);

		JScrollPane w_scrollMember = new JScrollPane();
		w_scrollMember.setBounds(10, 10, 802, 417);
		member_tab.add(w_scrollMember);

		table_member = new JTable(memberModel);
		w_scrollMember.setViewportView(table_member);
		table_member.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_membID.setText(table_member.getValueAt(table_member.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
				}
			}

		});

		table_member.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_member.getValueAt(table_member.getSelectedRow(), 0).toString());
					String selectName = table_member.getValueAt(table_member.getSelectedRow(), 1).toString();
					String selectSurname = table_member.getValueAt(table_member.getSelectedRow(), 2).toString();
					String selectTcno = table_member.getValueAt(table_member.getSelectedRow(), 3).toString();
					String selectPass = table_member.getValueAt(table_member.getSelectedRow(), 4).toString();

					try {
						boolean control = subadmin.updMember(selectID, selectTcno, selectPass, selectName,
								selectSurname);
						if (control) {
							Helper.showMsg("success");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});

		fld_membID = new JTextField();
		fld_membID.setFont(new Font("SansSerif", Font.PLAIN, 14));
		fld_membID.setEditable(false);
		fld_membID.setColumns(10);
		fld_membID.setBounds(869, 344, 149, 30);
		member_tab.add(fld_membID);

		Label lbl_membID = new Label("ID:");
		lbl_membID.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_membID.setBounds(818, 344, 45, 30);
		member_tab.add(lbl_membID);

		JButton btn_delMember = new JButton("Sil");
		btn_delMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_membID.getText().length() == 0) {
					Helper.showMsg("Lütfen Geçerli Bir Üye Seçiniz! ");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_membID.getText());
						try {
							boolean control = subadmin.delMember(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_membID.setText(null);
								updateMemberModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delMember.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_delMember.setFocusable(false);
		btn_delMember.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_delMember.setBackground(Color.LIGHT_GRAY);
		btn_delMember.setBounds(869, 384, 149, 40);
		member_tab.add(btn_delMember);

		JPanel book_tab = new JPanel();
		book_tab.setLayout(null);
		w_tab.addTab("Kitap Yönetimi", null, book_tab, null);

		JScrollPane w_scrollBook = new JScrollPane();
		w_scrollBook.setBounds(0, 0, 847, 427);
		book_tab.add(w_scrollBook);

		table_book = new JTable(bookModel);
		w_scrollBook.setViewportView(table_book);
		table_book.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_bookID.setText(table_book.getValueAt(table_book.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
				}
			}

		});

		table_book.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table_book.getValueAt(table_book.getSelectedRow(), 0).toString());
					String selectName = table_book.getValueAt(table_book.getSelectedRow(), 1).toString();
					String selectPage = table_book.getValueAt(table_book.getSelectedRow(), 2).toString();
					String selectWriter = table_book.getValueAt(table_book.getSelectedRow(), 3).toString();
					String selectCategory = table_book.getValueAt(table_book.getSelectedRow(), 4).toString();

					try {
						boolean control = subadmin.updBook(selectID, selectName, selectPage, selectWriter,
								selectCategory);
						if (control) {
							Helper.showMsg("success");

						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

				}

			}
		});

		JButton btn_delBook = new JButton("Kitabý Sil");
		btn_delBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_bookID.getText().length() == 0) {
					Helper.showMsg("Lütfen Geçerli Bir Kitap Seçiniz! ");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_bookID.getText());
						try {
							boolean control = subadmin.delBook(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_bookID.setText(null);
								updateBookModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delBook.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_delBook.setBounds(869, 387, 149, 40);
		book_tab.add(btn_delBook);

		fld_bookID = new JTextField();
		fld_bookID.setFont(new Font("SansSerif", Font.PLAIN, 14));
		fld_bookID.setEditable(false);
		fld_bookID.setColumns(10);
		fld_bookID.setBounds(869, 333, 149, 30);
		book_tab.add(fld_bookID);

		Label lbl_bookID = new Label("ID:");
		lbl_bookID.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_bookID.setBounds(869, 296, 40, 30);
		book_tab.add(lbl_bookID);

		JButton btn_addBook = new JButton("Kitap Ekle");
		btn_addBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBookGUI abGUI = new AddBookGUI();
				abGUI.setVisible(true);
			}
		});
		btn_addBook.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_addBook.setBounds(869, 10, 149, 40);
		book_tab.add(btn_addBook);

		JLabel lbl_WelcomePrimeAdmin = new JLabel("Hosgeldiniz Sayýn " + subadmin.getName() + subadmin.getSurname());
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

	public void updateMemberModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_member.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < subadmin.getMemberList().size(); i++) {
			memberData[0] = subadmin.getMemberList().get(i).getId();
			memberData[1] = subadmin.getMemberList().get(i).getName();
			memberData[2] = subadmin.getMemberList().get(i).getSurname();
			memberData[3] = subadmin.getMemberList().get(i).getTcno();
			memberData[4] = subadmin.getMemberList().get(i).getPassword();
			memberModel.addRow(memberData);
		}
	}

	public void updateBookModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_book.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < subadmin.getBookList().size(); i++) {
			bookData[0] = subadmin.getBookList().get(i).getId();
			bookData[1] = subadmin.getBookList().get(i).getName();
			bookData[2] = subadmin.getBookList().get(i).getPage();
			bookData[3] = subadmin.getBookList().get(i).getWriter();
			bookData[4] = subadmin.getBookList().get(i).getCategory();
			bookModel.addRow(bookData);
		}
	}
}
