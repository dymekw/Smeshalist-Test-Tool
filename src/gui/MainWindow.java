package gui;

import java.awt.Button;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import helpers.CoreNotRunningException;
import logic.BreakpointButtonListener;
import logic.FlushButtonListener;
import logic.RenderButtonListener;
import tool.Smeshalist;

public class MainWindow extends JFrame implements GUI {
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane = new JTabbedPane();
	private SingleTab singleTab;
	private BulkTab bulkTab;
	private Smeshalist tool;

	public static void main(String[] argv) throws CoreNotRunningException {
		new MainWindow();
	}

	public MainWindow() throws CoreNotRunningException {
		tool = Smeshalist.getInstance(false);
		setTitle("Smeshalist Test Tool");
		setSize(500, 500);
		initView();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initView() {
		JPanel mainPanel = new JPanel();

		tabbedPane.addTab("Single", initSingleTab());
		tabbedPane.addTab("Bulk", initBulkTab());

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		mainPanel.add(tabbedPane);
		mainPanel.add(getActionButtonsPanel());

		add(mainPanel);
	}

	private SingleTab initSingleTab() {
		singleTab = new SingleTab(this);

		return singleTab;
	}

	private BulkTab initBulkTab() {
		bulkTab = new BulkTab(this);

		return bulkTab;
	}

	private JPanel getActionButtonsPanel() {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.LINE_AXIS));

		Button flush = new Button("Flush");
		flush.addActionListener(new FlushButtonListener(this));

		Button render = new Button("Render");
		render.addActionListener(new RenderButtonListener(this));

		Button breakpoint = new Button("Breakpoint");
		breakpoint.addActionListener(new BreakpointButtonListener(this));

		result.add(flush);
		result.add(render);
		result.add(breakpoint);

		result.setMaximumSize(new Dimension(300, 150));
		return result;
	}

	@Override
	public Smeshalist getSmeshalist() {
		return tool;
	}

	@Override
	public SingleTab getSingleTab() {
		return singleTab;
	}

	@Override
	public BulkTab getBulkTab() {
		return bulkTab;
	}
}
