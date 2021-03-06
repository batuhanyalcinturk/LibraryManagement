package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;
import Helper.*;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import java.awt.Label;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class PrimeAdminGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static PrimeAdmin primeadmin = new PrimeAdmin();

	private JPanel w_pane;
	private JTable table_admin;
	private DefaultTableModel adminModel = null;
	private Object[] adminData = null;
	private JTextField fld_aName;
	private JTextField fld_aSurname;
	private JTextField fld_aTcno;
	private JTextField fld_aPass;
	private JTextField fld_adminID;
	private JTable table_member;
	private DefaultTableModel memberModel = null;
	private Object[] memberData = null;
	private JTextField fld_memberID;
	private JTable table_book;
	private JTextField fld_bookID;
	private DefaultTableModel bookModel = null;
	private Object[] bookData = null;
	private DefaultTableModel confBookModel = null;
	private Object[] confBookData = null;
	private JTextField fld_bName;
	private JTextField fld_bWriter;
	private JTextField fld_bPage;
	private Book book = new Book();
	String[] bookCategory = { "Bilim-Kurgu", "Macera", "Roman", "Hikaye", "Biyografi"};
	private JTextField fld_confBookID;
	private JTable table_confbook;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrimeAdminGUI frame = new PrimeAdminGUI(primeadmin);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PrimeAdminGUI(PrimeAdmin primeadmin) throws SQLException {

		adminModel = new DefaultTableModel();
		Object[] colAdmName = new Object[5];
		colAdmName[0] = "ID";
		colAdmName[1] = "Ad";
		colAdmName[2] = "Soyad";
		colAdmName[3] = "TC No";
		colAdmName[4] = "?ifre";
		adminModel.setColumnIdentifiers(colAdmName);
		adminData = new Object[5];
		for (int i = 0; i < primeadmin.getAdminList().size(); i++) {
			adminData[0] = primeadmin.getAdminList().get(i).getId();
			adminData[1] = primeadmin.getAdminList().get(i).getName();
			adminData[2] = primeadmin.getAdminList().get(i).getSurname();
			adminData[3] = primeadmin.getAdminList().get(i).getTcno();
			adminData[4] = primeadmin.getAdminList().get(i).getPassword();
			adminModel.addRow(adminData);
		}

		memberModel = new DefaultTableModel();
		Object[] colMembName = new Object[6];
		colMembName[0] = "ID";
		colMembName[1] = "Ad";
		colMembName[2] = "Soyad";
		colMembName[3] = "TC No";
		colMembName[4] = "?ifre";
		colMembName[5] = "?yelik T?r?";
		memberModel.setColumnIdentifiers(colMembName);
		memberData = new Object[6];
		for (int i = 0; i < primeadmin.getMemberList().size(); i++) {
			memberData[0] = primeadmin.getMemberList().get(i).getId();
			memberData[1] = primeadmin.getMemberList().get(i).getName();
			memberData[2] = primeadmin.getMemberList().get(i).getSurname();
			memberData[3] = primeadmin.getMemberList().get(i).getTcno();
			memberData[4] = primeadmin.getMemberList().get(i).getPassword();
			memberData[5] = primeadmin.getMemberList().get(i).getType();
			memberModel.addRow(memberData);
		}

		bookModel = new DefaultTableModel();
		Object[] colBookName = new Object[6];
		colBookName[0] = "ID";
		colBookName[1] = "Kitap ?smi";
		colBookName[2] = "Sayfa Say?s?";
		colBookName[3] = "Yazar";
		colBookName[4] = "Kategori";
		colBookName[5] = "??erik";
		bookModel.setColumnIdentifiers(colBookName);
		bookData = new Object[6];
		for (int i = 0; i < primeadmin.getBookList().size(); i++) {
			bookData[0] = primeadmin.getBookList().get(i).getId();
			bookData[1] = primeadmin.getBookList().get(i).getName();
			bookData[2] = primeadmin.getBookList().get(i).getPage();
			bookData[3] = primeadmin.getBookList().get(i).getWriter();
			bookData[4] = primeadmin.getBookList().get(i).getCategory();
			bookData[5] = primeadmin.getBookList().get(i).getInfo();
			bookModel.addRow(bookData);
		}

		confBookModel = new DefaultTableModel();
		Object[] colConfBookName = new Object[6];
		colConfBookName[0] = "ID";
		colConfBookName[1] = "Kitap ?smi";
		colConfBookName[2] = "Sayfa Say?s?";
		colConfBookName[3] = "Yazar";
		colConfBookName[4] = "Kategori";
		colConfBookName[5] = "??erik";
		confBookModel.setColumnIdentifiers(colBookName);
		confBookData = new Object[6];
		for (int i = 0; i < primeadmin.getConfirmBookList().size(); i++) {
			confBookData[0] = primeadmin.getConfirmBookList().get(i).getId();
			confBookData[1] = primeadmin.getConfirmBookList().get(i).getName();
			confBookData[2] = primeadmin.getConfirmBookList().get(i).getPage();
			confBookData[3] = primeadmin.getConfirmBookList().get(i).getWriter();
			confBookData[4] = primeadmin.getConfirmBookList().get(i).getCategory();
			confBookData[5] = primeadmin.getConfirmBookList().get(i).getInfo();
			confBookModel.addRow(confBookData);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 600);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setBounds(5, 5, 1076, 553);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.LIGHT_GRAY);
		w_pane.add(contentPane_1);
		contentPane_1.setLayout(null);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 79, 1033, 464);
		contentPane_1.add(w_tab);

		JPanel admin_tab = new JPanel();
		w_tab.addTab("Admin Y?netimi", null, admin_tab, null);
		admin_tab.setLayout(null);

		JPanel w_paneAdmin = new JPanel();
		w_paneAdmin.setBounds(0, 0, 1043, 464);
		admin_tab.add(w_paneAdmin);
		w_paneAdmin.setLayout(null);
		w_paneAdmin.setBackground(Color.LIGHT_GRAY);

		JButton btn_delSubAdmin = new JButton("Sil");
		btn_delSubAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_adminID.getText().length() == 0) {
					Helper.showMsg("L?tfen Ge?erli Bir Admin Se?iniz! ");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_adminID.getText());
						try {
							boolean control = primeadmin.delAdmin(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_adminID.setText(null);
								updateAdminModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}

		});
		btn_delSubAdmin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_delSubAdmin.setFocusable(false);
		btn_delSubAdmin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_delSubAdmin.setBackground(Color.LIGHT_GRAY);
		btn_delSubAdmin.setBounds(859, 342, 149, 40);
		w_paneAdmin.add(btn_delSubAdmin);

		JPanel w_paneAltAdmin = new JPanel();
		w_paneAltAdmin.setBackground(Color.WHITE);
		w_paneAltAdmin.setBounds(0, 0, 750, 435);
		w_paneAdmin.add(w_paneAltAdmin);
		w_paneAltAdmin.setLayout(null);

		JScrollPane w_scrollAdmin = new JScrollPane();
		w_scrollAdmin.setBounds(0, 0, 750, 435);
		w_paneAltAdmin.add(w_scrollAdmin);

		table_admin = new JTable(adminModel);
		w_scrollAdmin.setViewportView(table_admin);
		table_admin.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_adminID.setText(table_admin.getValueAt(table_admin.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
				}
			}

		});

		table_admin.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table_admin.getValueAt(table_admin.getSelectedRow(), 0).toString());
					String selectName = table_admin.getValueAt(table_admin.getSelectedRow(), 1).toString();
					String selectSurname = table_admin.getValueAt(table_admin.getSelectedRow(), 2).toString();
					String selectTcno = table_admin.getValueAt(table_admin.getSelectedRow(), 3).toString();
					String selectPass = table_admin.getValueAt(table_admin.getSelectedRow(), 4).toString();

					try {
						boolean control = primeadmin.updAdmin(selectID, selectTcno, selectPass, selectName,
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

		JButton btn_addSubAdmin = new JButton("Ekle");
		btn_addSubAdmin.setBounds(859, 180, 149, 40);
		w_paneAdmin.add(btn_addSubAdmin);
		btn_addSubAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_aName.getText().length() == 0 || fld_aSurname.getText().length() == 0
						|| fld_aPass.getText().length() == 0 || fld_aTcno.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = primeadmin.addAdmin(fld_aTcno.getText(), fld_aPass.getText(),
								fld_aName.getText(), fld_aSurname.getText());
						if (control) {
							Helper.showMsg("success");
							fld_aName.setText(null);
							fld_aSurname.setText(null);
							fld_aPass.setText(null);
							fld_aTcno.setText(null);
							updateAdminModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addSubAdmin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_addSubAdmin.setFocusable(false);
		btn_addSubAdmin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_addSubAdmin.setBackground(Color.LIGHT_GRAY);

		Label lbl_tc = new Label("TC No:");
		lbl_tc.setBounds(773, 140, 80, 30);
		w_paneAdmin.add(lbl_tc);
		lbl_tc.setFont(new Font("SansSerif", Font.PLAIN, 15));

		Label lbl_sifre = new Label("Sifre:");
		lbl_sifre.setBounds(773, 100, 80, 30);
		w_paneAdmin.add(lbl_sifre);
		lbl_sifre.setFont(new Font("SansSerif", Font.PLAIN, 15));

		Label lbl_soyad = new Label("Soyad:");
		lbl_soyad.setBounds(773, 60, 80, 30);
		w_paneAdmin.add(lbl_soyad);
		lbl_soyad.setFont(new Font("SansSerif", Font.PLAIN, 15));

		Label lbl_ad = new Label("Ad:");
		lbl_ad.setBounds(773, 20, 80, 30);
		w_paneAdmin.add(lbl_ad);
		lbl_ad.setFont(new Font("SansSerif", Font.PLAIN, 15));

		fld_aName = new JTextField();
		fld_aName.setBounds(859, 20, 149, 30);
		w_paneAdmin.add(fld_aName);
		fld_aName.setFont(new Font("SansSerif", Font.PLAIN, 14));
		fld_aName.setColumns(10);

		fld_aSurname = new JTextField();
		fld_aSurname.setBounds(859, 60, 149, 30);
		w_paneAdmin.add(fld_aSurname);
		fld_aSurname.setFont(new Font("SansSerif", Font.PLAIN, 14));
		fld_aSurname.setColumns(10);

		fld_aPass = new JTextField();
		fld_aPass.setBounds(858, 100, 150, 30);
		w_paneAdmin.add(fld_aPass);
		fld_aPass.setFont(new Font("SansSerif", Font.PLAIN, 14));
		fld_aPass.setColumns(10);

		fld_aTcno = new JTextField();
		fld_aTcno.setBounds(859, 140, 149, 30);
		w_paneAdmin.add(fld_aTcno);
		fld_aTcno.setFont(new Font("SansSerif", Font.PLAIN, 14));
		fld_aTcno.setColumns(10);

		fld_adminID = new JTextField();
		fld_adminID.setEditable(false);
		fld_adminID.setFont(new Font("SansSerif", Font.PLAIN, 14));
		fld_adminID.setColumns(10);
		fld_adminID.setBounds(859, 302, 149, 30);
		w_paneAdmin.add(fld_adminID);

		Label lbl_aID = new Label("ID:");
		lbl_aID.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_aID.setBounds(775, 302, 80, 30);
		w_paneAdmin.add(lbl_aID);

		JPanel member_tab = new JPanel();
		w_tab.addTab("?ye Y?netimi", null, member_tab, null);
		member_tab.setLayout(null);

		JScrollPane w_scrollMember = new JScrollPane();
		w_scrollMember.setBounds(10, 10, 802, 417);
		member_tab.add(w_scrollMember);

		table_member = new JTable(memberModel);
		w_scrollMember.setViewportView(table_member);
		table_member.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_memberID.setText(table_member.getValueAt(table_member.getSelectedRow(), 0).toString());
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
					String selectType = table_member.getValueAt(table_member.getSelectedRow(), 5).toString();

					try {
						boolean control = primeadmin.updMember(selectID, selectTcno, selectPass, selectName,
								selectSurname, selectType);
						if (control) {
							Helper.showMsg("success");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});

		fld_memberID = new JTextField();
		fld_memberID.setFont(new Font("SansSerif", Font.PLAIN, 14));
		fld_memberID.setEditable(false);
		fld_memberID.setColumns(10);
		fld_memberID.setBounds(869, 344, 149, 30);
		member_tab.add(fld_memberID);

		Label lbl_membID = new Label("ID:");
		lbl_membID.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_membID.setBounds(818, 344, 45, 30);
		member_tab.add(lbl_membID);

		JButton btn_delMember = new JButton("Sil");
		btn_delMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_memberID.getText().length() == 0) {
					Helper.showMsg("L?tfen Ge?erli Bir ?ye Se?iniz! ");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_memberID.getText());
						try {
							boolean control = primeadmin.delMember(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_memberID.setText(null);
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
		w_tab.addTab("Kitap Y?netimi", null, book_tab, null);
		book_tab.setLayout(null);

		JScrollPane w_scrollBook = new JScrollPane();
		w_scrollBook.setBounds(0, 0, 800, 363);
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
					String selectInfo = table_book.getValueAt(table_book.getSelectedRow(), 5).toString();

					try {
						boolean control = primeadmin.updBook(selectID, selectName, selectPage, selectWriter,
								selectCategory, selectInfo);
						if (control) {
							updateBookModel();
							Helper.showMsg("success");

						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

				}

			}
		});

		JButton btn_delBook = new JButton("Kitab? Sil");
		btn_delBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_bookID.getText().length() == 0) {
					Helper.showMsg("L?tfen Ge?erli Bir Kitap Se?iniz! ");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_bookID.getText());
						try {
							boolean control = primeadmin.delBook(selectID);
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
		btn_delBook.setBounds(440, 380, 149, 40);
		book_tab.add(btn_delBook);

		fld_bookID = new JTextField();
		fld_bookID.setFont(new Font("SansSerif", Font.PLAIN, 14));
		fld_bookID.setEditable(false);
		fld_bookID.setColumns(10);
		fld_bookID.setBounds(127, 386, 275, 30);
		book_tab.add(fld_bookID);

		Label lbl_bookID = new Label("Kitap Id:");
		lbl_bookID.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_bookID.setBounds(10, 386, 111, 30);
		book_tab.add(lbl_bookID);

		JLabel lbl_bookInfo = new JLabel("Kitap Hakk\u0131nda Bilgi:");
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
					txtar_bInfo.setText(table_book.getValueAt(table_book.getSelectedRow(), 5).toString());
				} catch (Exception ex) {
				}
			}

		});

		JPanel addbook_tab = new JPanel();
		w_tab.addTab("Kitap Ekle", null, addbook_tab, null);
		addbook_tab.setLayout(null);

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
		cmb_bCategory.setModel(new DefaultComboBoxModel(new String[] {"Bilim-Kurgu", "Macera", "Roman", "Hikaye", "Biyografi"}));
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
		txtarea_bInfo.setBounds(417, 212, 220, 173);
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
						boolean control = book.addBook(fld_bName.getText(), fld_bPage.getText(), fld_bWriter.getText(),
								cmb_bCategory.getSelectedItem(), txtarea_bInfo.getText());
						if (control) {
							updateBookModel();
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
		btn_bAdd.setBounds(417, 395, 133, 33);
		addbook_tab.add(btn_bAdd);

		JPanel confirmbook_tab = new JPanel();
		confirmbook_tab.setLayout(null);
		w_tab.addTab("Kitap Onaylama", null, confirmbook_tab, null);

		JScrollPane w_scrollConfBook = new JScrollPane();
		w_scrollConfBook.setBounds(0, 0, 800, 363);
		confirmbook_tab.add(w_scrollConfBook);

		table_confbook = new JTable(confBookModel);
		w_scrollConfBook.setViewportView(table_confbook);
		table_confbook.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_confBookID.setText(table_confbook.getValueAt(table_confbook.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
				}
			}

		});

		JButton btn_rejBook = new JButton("Kitab? Reddet");
		btn_rejBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_confBookID.getText().length() == 0) {
					Helper.showMsg("L?tfen Ge?erli Bir Kitap Se?iniz! ");
				} else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_confBookID.getText());
						try {
							boolean control = primeadmin.rejectBook(selectID);
							if (control) {
								Helper.showMsg("success");
								fld_bookID.setText(null);
								updateConfBookModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_rejBook.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_rejBook.setBounds(600, 380, 149, 40);
		confirmbook_tab.add(btn_rejBook);

		fld_confBookID = new JTextField();
		fld_confBookID.setFont(new Font("SansSerif", Font.PLAIN, 14));
		fld_confBookID.setEditable(false);
		fld_confBookID.setColumns(10);
		fld_confBookID.setBounds(127, 386, 275, 30);
		confirmbook_tab.add(fld_confBookID);

		Label lbl_confBookID = new Label("Kitap Id:");
		lbl_confBookID.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_confBookID.setBounds(10, 386, 111, 30);
		confirmbook_tab.add(lbl_confBookID);

		JLabel lbl_confBookInfo = new JLabel("Kitap Hakk?nda Bilgi:");
		lbl_confBookInfo.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_confBookInfo.setBounds(810, 0, 208, 30);
		confirmbook_tab.add(lbl_confBookInfo);

		JTextArea txtar_conBoInfo = new JTextArea();
		txtar_conBoInfo.setWrapStyleWord(true);
		txtar_conBoInfo.setLineWrap(true);
		txtar_conBoInfo.setBounds(810, 40, 208, 313);
		confirmbook_tab.add(txtar_conBoInfo);
		table_confbook.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					txtar_conBoInfo.setText(table_confbook.getValueAt(table_confbook.getSelectedRow(), 5).toString());
				} catch (Exception ex) {
				}
			}

		});
		
		
		JButton btn_confBook = new JButton("Kitab? Onayla");
		btn_confBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((table_confbook.getValueAt(table_confbook.getSelectedRow(), 1)).toString().length() == 0
						|| (table_confbook.getValueAt(table_confbook.getSelectedRow(), 2)).toString().length() == 0
						|| (table_confbook.getValueAt(table_confbook.getSelectedRow(), 3)).toString().length() == 0
						|| (table_confbook.getValueAt(table_confbook.getSelectedRow(), 4)).toString().length() == 0
						|| (table_confbook.getValueAt(table_confbook.getSelectedRow(), 5)).toString().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = book.addBook(
								(table_confbook.getValueAt(table_confbook.getSelectedRow(), 1).toString()),
								(table_confbook.getValueAt(table_confbook.getSelectedRow(), 2).toString()),
								(table_confbook.getValueAt(table_confbook.getSelectedRow(), 3).toString()),
								(table_confbook.getValueAt(table_confbook.getSelectedRow(), 4)),
								(table_confbook.getValueAt(table_confbook.getSelectedRow(), 5).toString()));
						if (control) {
							updateBookModel();
							int selectID = Integer.parseInt(fld_confBookID.getText());
							Helper.showMsg("success");
							primeadmin.rejectBook(selectID);
							updateConfBookModel();

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
		btn_confBook.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_confBook.setBounds(440, 380, 139, 40);
		confirmbook_tab.add(btn_confBook);

		JLabel lbl_WelcomePrimeAdmin = new JLabel("Hosgeldiniz Say?n " + primeadmin.getName());
		lbl_WelcomePrimeAdmin.setBounds(144, 20, 366, 20);
		lbl_WelcomePrimeAdmin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		contentPane_1.add(lbl_WelcomePrimeAdmin);

		JButton btn_Exit = new JButton("??k?? Yap");
		btn_Exit.setBounds(974, 20, 100, 25);
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
		contentPane_1.add(btn_Exit);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PrimeAdminGUI.class.getResource("/Images/admin.png")));
		lblNewLabel.setBounds(10, 10, 64, 64);
		contentPane_1.add(lblNewLabel);
	}

	public void updateAdminModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_admin.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < primeadmin.getAdminList().size(); i++) {
			adminData[0] = primeadmin.getAdminList().get(i).getId();
			adminData[1] = primeadmin.getAdminList().get(i).getName();
			adminData[2] = primeadmin.getAdminList().get(i).getSurname();
			adminData[3] = primeadmin.getAdminList().get(i).getTcno();
			adminData[4] = primeadmin.getAdminList().get(i).getPassword();
			adminModel.addRow(adminData);
		}
	}

	public void updateMemberModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_member.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < primeadmin.getMemberList().size(); i++) {
			memberData[0] = primeadmin.getMemberList().get(i).getId();
			memberData[1] = primeadmin.getMemberList().get(i).getName();
			memberData[2] = primeadmin.getMemberList().get(i).getSurname();
			memberData[3] = primeadmin.getMemberList().get(i).getTcno();
			memberData[4] = primeadmin.getMemberList().get(i).getPassword();
			memberData[5] = primeadmin.getMemberList().get(i).getType();
			memberModel.addRow(memberData);
		}
	}

	public void updateBookModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_book.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < primeadmin.getBookList().size(); i++) {
			bookData[0] = primeadmin.getBookList().get(i).getId();
			bookData[1] = primeadmin.getBookList().get(i).getName();
			bookData[2] = primeadmin.getBookList().get(i).getPage();
			bookData[3] = primeadmin.getBookList().get(i).getWriter();
			bookData[4] = primeadmin.getBookList().get(i).getCategory();
			bookData[5] = primeadmin.getBookList().get(i).getInfo();
			bookModel.addRow(bookData);
		}
	}
	
	public void updateConfBookModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_confbook.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < primeadmin.getConfirmBookList().size(); i++) {
			confBookData[0] = primeadmin.getConfirmBookList().get(i).getId();
			confBookData[1] = primeadmin.getConfirmBookList().get(i).getName();
			confBookData[2] = primeadmin.getConfirmBookList().get(i).getPage();
			confBookData[3] = primeadmin.getConfirmBookList().get(i).getWriter();
			confBookData[4] = primeadmin.getConfirmBookList().get(i).getCategory();
			confBookData[5] = primeadmin.getConfirmBookList().get(i).getInfo();
			confBookModel.addRow(confBookData);
		}
	}
	
}
