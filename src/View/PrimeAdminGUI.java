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

public class PrimeAdminGUI extends JFrame {

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
	public PrimeAdminGUI(PrimeAdmin primeadmin) throws SQLException {

		adminModel = new DefaultTableModel();
		Object[] colAdmName = new Object[5];
		colAdmName[0] = "ID";
		colAdmName[1] = "Ad";
		colAdmName[2] = "Soyad";
		colAdmName[3] = "TC No";
		colAdmName[4] = "Þifre";
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
		colMembName[4] = "Þifre";
		colMembName[5] = "Üyelik Türü";
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
		Object[] colBookName = new Object[5];
		colBookName[0] = "ID";
		colBookName[1] = "Kitap Ýsmi";
		colBookName[2] = "Sayfa Sayýsý";
		colBookName[3] = "Yazar";
		colBookName[4] = "Kategori";
		bookModel.setColumnIdentifiers(colBookName);
		bookData = new Object[5];
		for (int i = 0; i < primeadmin.getBookList().size(); i++) {
			bookData[0] = primeadmin.getBookList().get(i).getId();
			bookData[1] = primeadmin.getBookList().get(i).getName();
			bookData[2] = primeadmin.getBookList().get(i).getPage();
			bookData[3] = primeadmin.getBookList().get(i).getWriter();
			bookData[4] = primeadmin.getBookList().get(i).getCategory();
			bookModel.addRow(bookData);
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
		w_tab.addTab("Admin Yönetimi", null, admin_tab, null);
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
					Helper.showMsg("Lütfen Geçerli Bir Admin Seçiniz! ");
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
		w_tab.addTab("Üye Yönetimi", null, member_tab, null);
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
					Helper.showMsg("Lütfen Geçerli Bir Üye Seçiniz! ");
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
		w_tab.addTab("Kitap Yönetimi", null, book_tab, null);
		book_tab.setLayout(null);

		JScrollPane w_scrollBook = new JScrollPane();
		w_scrollBook.setBounds(0, 0, 847, 427);
		book_tab.add(w_scrollBook);

		table_book = new JTable(bookModel);
		w_scrollBook.setViewportView(table_book);
		table_book.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_bookID.setText(table_book.getValueAt(table_book.getSelectedRow(), 1).toString());
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
						boolean control = primeadmin.updBook(selectID, selectName, selectPage, selectWriter,
								selectCategory);
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

		JButton btn_delBook = new JButton("Kitabý Sil");
		btn_delBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_bookID.getText().length() == 0) {
					Helper.showMsg("Lütfen Geçerli Bir Kitap Seçiniz! ");
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

		JLabel lbl_WelcomePrimeAdmin = new JLabel("Hosgeldiniz Sayýn " + primeadmin.getName());
		lbl_WelcomePrimeAdmin.setBounds(144, 20, 366, 20);
		lbl_WelcomePrimeAdmin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		contentPane_1.add(lbl_WelcomePrimeAdmin);

		JButton btn_Exit = new JButton("Çýkýþ Yap");
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
			bookModel.addRow(bookData);
		}
	}
}
