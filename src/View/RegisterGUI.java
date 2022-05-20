package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Member;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_surname;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Member member = new Member();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("E-Kütüphane Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 358);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblAd = new JLabel("Ad ");
		lblAd.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 11));
		lblAd.setBounds(10, 11, 59, 14);
		w_pane.add(lblAd);

		fld_name = new JTextField();
		fld_name.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		fld_name.setColumns(10);
		fld_name.setBounds(10, 28, 254, 26);
		w_pane.add(fld_name);

		JLabel lblTcNumaras = new JLabel("T.C. Numarasý");
		lblTcNumaras.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 11));
		lblTcNumaras.setBounds(10, 118, 83, 14);
		w_pane.add(lblTcNumaras);

		fld_tcno = new JTextField();
		fld_tcno.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 135, 254, 26);
		w_pane.add(fld_tcno);

		JLabel lblifre = new JLabel("Þifre");
		lblifre.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 11));
		lblifre.setBounds(10, 172, 83, 14);
		w_pane.add(lblifre);

		fld_pass = new JPasswordField();
		fld_pass.setBounds(10, 193, 254, 26);
		w_pane.add(fld_pass);

		JButton btn_register = new JButton("Kayýt Ol");
		btn_register.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (fld_tcno.getText().length() == 0 || fld_pass.getText().length() == 0
						|| fld_name.getText().length() == 0 || fld_surname.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = member.register(fld_tcno.getText(), fld_pass.getText(), fld_name.getText(),
								fld_surname.getText());
						if (control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
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
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_register.setBounds(10, 230, 254, 35);
		w_pane.add(btn_register);

		JButton btn_backto = new JButton("Geri Dön");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();

			}
		});
		btn_backto.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_backto.setBounds(10, 277, 254, 35);
		w_pane.add(btn_backto);

		fld_surname = new JTextField();
		fld_surname.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		fld_surname.setColumns(10);
		fld_surname.setBounds(10, 82, 254, 26);
		w_pane.add(fld_surname);

		JLabel lblSoyad = new JLabel("Soyad");
		lblSoyad.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 11));
		lblSoyad.setBounds(10, 65, 59, 14);
		w_pane.add(lblSoyad);

	}
}
