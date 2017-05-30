package com.kthisiscvpv.digitalchaos.panel;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kthisiscvpv.digitalchaos.util.Point;

@SuppressWarnings("serial")
public class CreatePage extends ClientGUI {

	static {
		CreatePage.TITLE_IMAGE = Toolkit.getDefaultToolkit().getImage("create_title.png");
	}

	private static Image TITLE_IMAGE;

	private JLabel titleImage;
	
	private JLabel usernameLabel;
	private JTextField usernameField;
	
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	
	private JLabel addressLabel;
	private JTextField addressField;
	
	private JButton createButton;

	public CreatePage() {
		super("Digital Chaos - Register");
		this.setLayout(null);
		this.setSize(400, 248);
		this.setResizable(false);

		this.titleImage = new JLabel(new ImageIcon(CreatePage.TITLE_IMAGE));
		this.titleImage.setSize(this.titleImage.getPreferredSize());
		Point startTitle = this.getStart((int) this.getSize().getWidth() / 2, 50, CreatePage.TITLE_IMAGE.getWidth(null), CreatePage.TITLE_IMAGE.getHeight(null));
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
		
		this.createButton = new JButton("Create Account");
		this.createButton.setSize(this.createButton.getPreferredSize());
		this.createButton.setLocation(245, 180);
		this.add(this.createButton);
	}
}
