package gui;

import java.awt.Button;
import java.awt.Dimension;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import helpers.CoreNotRunningException;
import logic.BreakpointButtonListener;
import logic.CleanButtonListener;
import logic.ConnectButtonListener;
import logic.DisconnectButtonListener;
import logic.FlushButtonListener;
import logic.RenderButtonListener;
import tool.Smeshalist;

public class MainWindow extends JFrame implements GUI {
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane = new JTabbedPane();
	private JComboBox<Boolean> resetOptions;
	private Button connect = new Button("Connect");
	private Button disconnect = new Button("Disconnect");
	private Button flush = new Button("Flush");
	private Button render = new Button("Render");
	private Button breakpoint = new Button("Breakpoint");
	private Button clean = new Button("Clean");
	private SingleTab singleTab;
	private BulkTab bulkTab;
	private Smeshalist tool;

	public static void main(String[] argv) throws CoreNotRunningException {
		new MainWindow();
	}

	public MainWindow() throws CoreNotRunningException {
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

		flush.addActionListener(new FlushButtonListener(this));

		render.addActionListener(new RenderButtonListener(this));

		breakpoint.addActionListener(new BreakpointButtonListener(this));

		JPanel resetPanel = new JPanel();
		resetPanel.setLayout(new BoxLayout(resetPanel, BoxLayout.Y_AXIS));

		JLabel resetLabel = new JLabel("Hard reset");
		resetPanel.add(resetLabel);

		Boolean[] options = { Boolean.TRUE, Boolean.FALSE };
		resetOptions = new JComboBox<>(options);
		resetPanel.add(resetOptions);

		JPanel connectionPanel = new JPanel();
		connectionPanel.setLayout(new BoxLayout(connectionPanel, BoxLayout.Y_AXIS));

		connectionPanel.add(connect);
		connectionPanel.add(disconnect);

		connect.addActionListener(new ConnectButtonListener(this));
		disconnect.addActionListener(new DisconnectButtonListener(this));
		disconnect.setEnabled(false);
		clean.addActionListener(new CleanButtonListener(this));

		result.add(resetPanel);
		result.add(connectionPanel);
		result.add(flush);
		result.add(render);
		result.add(breakpoint);
		result.add(clean);

		result.setMaximumSize(new Dimension(450, 150));
		return result;
	}

	@Override
	public void connectWithSmeshalist() {
		Boolean option = (Boolean) resetOptions.getSelectedItem();
		resetOptions.setEnabled(false);
		connect.setEnabled(false);
		disconnect.setEnabled(true);

		try {
			tool = Smeshalist.getInstance(option);
		} catch (CoreNotRunningException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void disconnectWithSmeshalist() {
		Smeshalist.destroySmeshalist();
		tool = null;

		resetOptions.setEnabled(true);
		connect.setEnabled(true);
		disconnect.setEnabled(false);
	}

	@Override
	public Smeshalist getSmeshalist() {
		if (Objects.isNull(tool)) {
			connectWithSmeshalist();
		}
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
