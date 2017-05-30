package com.kthisiscvpv.digitalchaos.panel;

import javax.swing.JFrame;

import com.kthisiscvpv.digitalchaos.util.Point;

@SuppressWarnings("serial")
public class ClientGUI extends JFrame {

	public ClientGUI(String title) {
		super(title);
	}

	public Point getStart(int centerX, int centerY, int width, int height) {
		return new Point(centerX - (width / 2), centerY - (height / 2));
	}
}
