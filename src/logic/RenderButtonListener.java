package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.GUI;
import helpers.CoreNotRunningException;

public class RenderButtonListener implements ActionListener {

	private GUI gui;

	public RenderButtonListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			gui.getSmeshalist().render();
		} catch (CoreNotRunningException e) {
			e.printStackTrace();
		}
	}

}
