package com.kthisiscvpv.digitalchaos.panel;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kthisiscvpv.digitalchaos.DigitalChaos;
import com.kthisiscvpv.digitalchaos.util.Point;

@SuppressWarnings("serial")
public class LoginPage extends ClientGUI {

	static {
		LoginPage.TITLE_IMAGE = Toolkit.getDefaultToolkit().getImage("login_title.png");
	}

	private static Image TITLE_IMAGE;

	private JLabel titleImage;

	private JLabel usernameLabel;
	private JTextField usernameField;

	private JLabel passwordLabel;
	private JPasswordField passwordField;

	private JLabel addressLabel;
	private JTextField addressField;

	private JButton loginButton;
	private JButton createButton;

	public LoginPage() {
		super("Digital Chaos - Login");
		this.setLayout(null);
		this.setSize(400, 248);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.titleImage = new JLabel(new ImageIcon(LoginPage.TITLE_IMAGE));
		this.titleImage.setSize(this.titleImage.getPreferredSize());
		Point startTitle = this.getStart((int) this.getSize().getWidth() / 2, 50, LoginPage.TITLE_IMAGE.getWidth(null), LoginPage.TITLE_IMAGE.getHeight(null));
		this.titleImage.setLocation(startTitle.getX(), startTitle.getY());
		this.add(this.titleImage);

		this.usernameLabel = new JLabel("Username:");
		this.usernameLabel.setSize(this.usernameLabel.getPreferredSize());
		this.usernameLabel.setLocation(25, 100);
		this.add(this.usernameLabel);

		this.usernameField = new JTextField(24);
		this.usernameField.setSize(this.usernameField.getPreferredSize());
		this.usernameField.setLocation(100, 100);
		this.add(this.usernameField);

		this.passwordLabel = new JLabel("Password:");
		this.passwordLabel.setSize(this.passwordLabel.getPreferredSize());
		this.passwordLabel.setLocation(25, 125);
		this.add(this.passwordLabel);

		this.passwordField = new JPasswordField(24);
		this.passwordField.setSize(this.passwordField.getPreferredSize());
		this.passwordField.setLocation(100, 125);
		this.add(this.passwordField);

		this.addressLabel = new JLabel("Server IP:");
		this.addressLabel.setSize(this.addressLabel.getPreferredSize());
		this.addressLabel.setLocation(25, 150);
		this.add(this.addressLabel);

		this.addressField = new JTextField(24);
		this.addressField.setSize(this.addressField.getPreferredSize());
		this.addressField.setLocation(100, 150);
		this.add(this.addressField);

		this.loginButton = new JButton("Login");
		this.loginButton.setSize(this.loginButton.getPreferredSize());
		this.loginButton.setLocation(302, 180);
		this.loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				dispose();
				
		        try {
                    new DigitalChaos();
                } catch (Exception e) {
                    e.printStackTrace();
                }
			}
		});
		this.add(this.loginButton);

		this.createButton = new JButton("Create Account");
		this.createButton.setSize(this.createButton.getPreferredSize());
		this.createButton.setLocation(25, 180);
		this.createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new CreatePage().setVisible(true);
			}
		});
		this.add(this.createButton);
	}
}
