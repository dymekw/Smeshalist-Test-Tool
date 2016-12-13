package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.GUI;
import helpers.CoreNotRunningException;

public class FlushButtonListener implements ActionListener {
	private GUI gui;

	public FlushButtonListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			gui.getSmeshalist().flushBuffer();
		} catch (CoreNotRunningException e) {
			e.printStackTrace();
		}
	}

}
