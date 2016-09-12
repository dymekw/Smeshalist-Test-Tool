package gui;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.TextField;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import entity.Point;
import logic.AddBlocksButton;
import logic.AddEdgesButton;
import logic.AddFacesButton;
import logic.AddVerticesButton;

public class BulkTab extends JPanel {
	private static final long serialVersionUID = 1L;

	private GUI gui;

	private TextField numberOfElements;

	private TextField xCoordFrom;
	private TextField xCoordTo;
	private TextField yCoordFrom;
	private TextField yCoordTo;
	private TextField zCoordFrom;
	private TextField zCoordTo;

	private TextField groupFrom;
	private TextField groupTo;

	private TextField qualityFrom;
	private TextField qualityTo;

	public BulkTab(GUI gui) {
		this.gui = gui;
		initTab();
	}

	private void initTab() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		add(getButtonsPanel());
		add(getNumberOFElementsPanel());
		add(getGroupRangePanel());
		add(getQualityRangePanel());
		add(getCoordsRangePanel());
	}

	private JPanel getButtonsPanel() {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.PAGE_AXIS));

		Button vertices = new Button("Add vertices");
		vertices.addActionListener(new AddVerticesButton(gui));

		Button edges = new Button("Add edges");
		edges.addActionListener(new AddEdgesButton(gui));

		Button triangles = new Button("Add triangles");
		triangles.addActionListener(new AddFacesButton(gui));

		Button blocks = new Button("Add blocks");
		blocks.addActionListener(new AddBlocksButton(gui));

		result.add(vertices);
		result.add(edges);
		result.add(triangles);
		result.add(blocks);

		return result;
	}

	private JPanel getNumberOFElementsPanel() {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.LINE_AXIS));

		numberOfElements = new TextField(Integer.toString(new Random().nextInt(10)));
		numberOfElements.setColumns(8);
		numberOfElements.setMinimumSize(new Dimension(200, 30));
		numberOfElements.setPreferredSize(new Dimension(200, 30));
		numberOfElements.setMaximumSize(new Dimension(200, 30));

		result.add(new Label("Number of elements:"));
		result.add(numberOfElements);

		return result;
	}

	private JPanel getCoordsRangePanel() {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.PAGE_AXIS));

		JPanel xRange = new JPanel();
		xRange.setLayout(new BoxLayout(xRange, BoxLayout.LINE_AXIS));
		xRange.add(new Label("x:"));
		xCoordFrom = new TextField("-10");
		xRange.add(xCoordFrom);
		xRange.add(new Label(" - "));
		xCoordTo = new TextField("10");
		xRange.add(xCoordTo);

		JPanel yRange = new JPanel();
		yRange.setLayout(new BoxLayout(yRange, BoxLayout.LINE_AXIS));
		yRange.add(new Label("y:"));
		yCoordFrom = new TextField("-10");
		yRange.add(yCoordFrom);
		yRange.add(new Label(" - "));
		yCoordTo = new TextField("10");
		yRange.add(yCoordTo);

		JPanel zRange = new JPanel();
		zRange.setLayout(new BoxLayout(zRange, BoxLayout.LINE_AXIS));
		zRange.add(new Label("z:"));
		zCoordFrom = new TextField("-10");
		zRange.add(zCoordFrom);
		zRange.add(new Label(" - "));
		zCoordTo = new TextField("10");
		zRange.add(zCoordTo);

		result.add(xRange);
		result.add(yRange);
		result.add(zRange);

		return result;
	}

	private JPanel getGroupRangePanel() {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.LINE_AXIS));

		result.add(new Label("Group:"));

		groupFrom = new TextField("0");
		result.add(groupFrom);

		result.add(new Label(" - "));

		groupTo = new TextField("0");
		result.add(groupTo);

		return result;
	}

	private JPanel getQualityRangePanel() {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.LINE_AXIS));

		result.add(new Label("Quality:"));

		qualityFrom = new TextField("-10");
		result.add(qualityFrom);

		result.add(new Label(" - "));

		qualityTo = new TextField("10");
		result.add(qualityTo);

		return result;
	}

	public Map<String, Point> getPoints() {
		Map<String, Point> result = new TreeMap<>();

		result.put("from", new Point(Double.parseDouble(xCoordFrom.getText()), Double.parseDouble(yCoordFrom.getText()),
				Double.parseDouble(zCoordFrom.getText())));

		result.put("to", new Point(Double.parseDouble(xCoordTo.getText()), Double.parseDouble(yCoordTo.getText()),
				Double.parseDouble(zCoordTo.getText())));

		return result;
	}

	public int getNumberOfElements() {
		return Integer.parseInt(numberOfElements.getText());
	}

	public int maxGroup() {
		return Integer.parseInt(groupTo.getText());
	}

	public int minGroup() {
		return Integer.parseInt(groupFrom.getText());
	}

	public double maxQuality() {
		return Double.parseDouble(qualityTo.getText());
	}

	public double minQuality() {
		return Double.parseDouble(qualityFrom.getText());
	}
}
