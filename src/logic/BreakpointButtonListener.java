package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.GUI;

public class BreakpointButtonListener implements ActionListener {

	private GUI gui;

	public BreakpointButtonListener(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		gui.getSmeshalist().breakpoint();
	}

}
