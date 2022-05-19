package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.*;
import Model.*;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_userTc;
	private JPasswordField fld_userPass;
	private JTextField fld_adminId;
	private JPasswordField fld_adminPass;
	DbHelper dbHelper = new DbHelper();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("E-Kütüphane Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 400);
		w_pane = new JPanel();
		w_pane.setBackground(Color.LIGHT_GRAY);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setFont(new Font("SansSerif", Font.PLAIN, 15));
		w_tabpane.setFocusable(false);
		w_tabpane.setBounds(10, 95, 484, 268);
		w_pane.add(w_tabpane);

		JPanel w_paneUser = new JPanel();
		w_paneUser.setLayout(null);
		w_paneUser.setBackground(Color.WHITE);
		w_tabpane.addTab("Kullanýcý Giriþi", null, w_paneUser, null);

		JLabel lbl_UserTC = new JLabel("T.C. Kimlik No:");
		lbl_UserTC.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_UserTC.setBounds(84, 20, 100, 20);
		w_paneUser.add(lbl_UserTC);
		
		JLabel lbl_Password_Login = new JLabel("Sifre:");
		lbl_Password_Login.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Password_Login.setBounds(84, 50, 100, 20);
		w_paneUser.add(lbl_Password_Login);

		fld_userTc = new JTextField();
		fld_userTc.setFont(new Font("SansSerif", Font.PLAIN, 13));
		fld_userTc.setColumns(10);
		fld_userTc.setBounds(194, 20, 275, 20);
		w_paneUser.add(fld_userTc);

		fld_userPass = new JPasswordField();
		fld_userPass.setFont(new Font("SansSerif", Font.PLAIN, 13));
		fld_userPass.setBounds(194, 50, 275, 20);
		w_paneUser.add(fld_userPass);

		JButton btn_Register = new JButton("Kayýt Ol");
		btn_Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_Register.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_Register.setFocusable(false);
		btn_Register.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Register.setBackground(Color.LIGHT_GRAY);
		btn_Register.setBounds(256, 162, 150, 40);
		w_paneUser.add(btn_Register);

		JButton btn_Login = new JButton("Giriþ Yap");
		btn_Login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (fld_userTc.getText().length() == 0 || fld_userPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						Connection con = dbHelper.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM register");
						while (rs.next()) {
							if (fld_userTc.getText().equals(rs.getString("tcno"))
									&& fld_userPass.getText().equals(rs.getString("password"))) {
								Member memb = new Member();
								memb.setId(rs.getInt("id"));
								memb.setPassword("password");
								memb.setTcno(rs.getString("tcno"));
								memb.setName(rs.getString("name"));
								memb.setType(rs.getString("type"));
								MemberGUI membGUI = new MemberGUI(memb);
								membGUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});
		btn_Login.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_Login.setFocusable(false);
		btn_Login.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Login.setBackground(Color.LIGHT_GRAY);
		btn_Login.setBounds(71, 162, 150, 40);
		w_paneUser.add(btn_Login);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginGUI.class.getResource("/Images/user.png")));
		lblNewLabel.setBounds(10, 10, 64, 64);
		w_paneUser.add(lblNewLabel);

		JPanel w_paneAdmin = new JPanel();
		w_paneAdmin.setLayout(null);
		w_paneAdmin.setBackground(Color.WHITE);
		w_tabpane.addTab("Yönetici Giriþi", null, w_paneAdmin, null);

		JLabel lbl_adminId = new JLabel("Yonetici ID:");
		lbl_adminId.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_adminId.setBounds(84, 20, 100, 20);
		w_paneAdmin.add(lbl_adminId);

		fld_adminId = new JTextField();
		fld_adminId.setFont(new Font("SansSerif", Font.PLAIN, 13));
		fld_adminId.setColumns(10);
		fld_adminId.setBounds(194, 20, 275, 20);
		w_paneAdmin.add(fld_adminId);

		JLabel lbl_Password_Admin = new JLabel("Sifre:");
		lbl_Password_Admin.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lbl_Password_Admin.setBounds(84, 50, 100, 20);
		w_paneAdmin.add(lbl_Password_Admin);

		fld_adminPass = new JPasswordField();
		fld_adminPass.setFont(new Font("SansSerif", Font.PLAIN, 13));
		fld_adminPass.setBounds(194, 50, 275, 20);
		w_paneAdmin.add(fld_adminPass);

		JButton btn_Login_1 = new JButton("Giris Yap");
		btn_Login_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (fld_adminId.getText().length() == 0 || fld_adminPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						Connection con = dbHelper.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM admin");
						while (rs.next()) {
							if (fld_adminId.getText().equals(rs.getString("tcno"))
									&& fld_adminPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("prime")) {
									PrimeAdmin padm = new PrimeAdmin();
									padm.setId(rs.getInt("id"));
									padm.setPassword("password");
									padm.setTcno(rs.getString("tcno"));
									padm.setName(rs.getString("name"));
									padm.setType(rs.getString("type"));
									PrimeAdminGUI padmGUI = new PrimeAdminGUI(padm);
									padmGUI.setVisible(true);
									dispose();
								}

								if (rs.getString("type").equals("sub")) {
									SubAdmin sadm = new SubAdmin();
									sadm.setId(rs.getInt("id"));
									sadm.setPassword("password");
									sadm.setTcno(rs.getString("tcno"));
									sadm.setName(rs.getString("name"));
									sadm.setType(rs.getString("type"));
									SubAdminGUI sadmGUI = new SubAdminGUI(sadm);
									sadmGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});
		btn_Login_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btn_Login_1.setFocusable(false);
		btn_Login_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btn_Login_1.setBackground(Color.LIGHT_GRAY);
		btn_Login_1.setBounds(164, 130, 150, 40);
		w_paneAdmin.add(btn_Login_1);

		JLabel lbl_ForgottenAdmin = new JLabel(
				"Giris bilgilerinizi hatirlayamiyorsaniz lutfen ilgili birimle iletisime geciniz");
		lbl_ForgottenAdmin.setFont(new Font("SansSerif", Font.ITALIC, 11));
		lbl_ForgottenAdmin.setBackground(Color.WHITE);
		lbl_ForgottenAdmin.setBounds(10, 209, 459, 20);
		w_paneAdmin.add(lbl_ForgottenAdmin);

		JLabel lbl_adminIcon = new JLabel();
		lbl_adminIcon.setIcon(new ImageIcon(LoginGUI.class.getResource("/Images/primex64.png")));
		lbl_adminIcon.setBounds(10, 10, 64, 64);
		w_paneAdmin.add(lbl_adminIcon);

		JLabel lblWelcome = new JLabel("E-Kütüphane Sistemine Hoþgeldiniz");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblWelcome.setBounds(53, 35, 400, 25);
		w_pane.add(lblWelcome);
	}
}
