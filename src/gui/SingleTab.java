package gui;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import entity.Point;
import logic.AddButtonListener;
import logic.StructureType;

public class SingleTab extends JPanel {
	private static final long serialVersionUID = 1L;
	private GUI gui;
	private Map<String, TextField> textFields = new TreeMap<>();
	private TextField group;
	private TextField quality;
	private TextField label;

	public SingleTab(GUI gui) {
		this.gui = gui;
		initPanel();
	}

	private void initPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(getVertexPanel());
		add(getEdgePanel());
		add(getFacePanel());
		add(getBlockPanel());
		add(getInputPanel());
	}

	private JButton initAddButton(StructureType type) {
		JButton result = new JButton("ADD");
		result.setMaximumSize(new Dimension(50, 20));
		result.addActionListener(new AddButtonListener(gui, type));
		return result;
	}

	private TextField getInput(int point, String coord) {
		TextField result = new TextField();
		result.setText(Float.toString(new Random().nextFloat() * 10 - 5));
		result.setMaximumSize(new Dimension(100, 30));
		result.setPreferredSize(new Dimension(100, 30));
		result.setMinimumSize(new Dimension(100, 30));

		textFields.put(coord + point, result);

		return result;
	}

	private JPanel getVertexPanel() {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.LINE_AXIS));
		result.add(new Label("Vertex"));
		result.add(new Label("[P1]"));
		result.add(initAddButton(StructureType.VERTEX));
		return result;
	}

	private JPanel getEdgePanel() {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.LINE_AXIS));
		result.add(new Label("Edge"));
		result.add(new Label("[P1, P2]"));
		result.add(initAddButton(StructureType.EDGE));
		return result;
	}

	private JPanel getFacePanel() {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.LINE_AXIS));
		result.add(new Label("Face"));
		result.add(new Label("[P1, P2, P3]"));
		result.add(initAddButton(StructureType.FACE));
		return result;
	}

	private JPanel getBlockPanel() {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.LINE_AXIS));
		result.add(new Label("Block"));
		result.add(new Label("[P1, P2, P3, P4]"));
		result.add(initAddButton(StructureType.BLOCK));
		return result;
	}

	private JPanel getGroupPanel() {
		JPanel result = new JPanel();
		group = new TextField(Integer.toString(new Random().nextInt(10)));

		result.setLayout(new BoxLayout(result, BoxLayout.LINE_AXIS));
		result.add(new Label("Group:"));
		result.add(group);
		return result;
	}

	private JPanel getQualityPanel() {
		JPanel result = new JPanel();
		quality = new TextField(Float.toString(new Random().nextFloat() * 10 - 5));

		result.setLayout(new BoxLayout(result, BoxLayout.LINE_AXIS));
		result.add(new Label("Quality:"));
		result.add(quality);
		return result;
	}

	private JPanel getInputPanel() {
		JPanel result = new JPanel();
		result.setLayout(new BoxLayout(result, BoxLayout.PAGE_AXIS));

		JPanel headersPanel = new JPanel();
		headersPanel.setLayout(new BoxLayout(headersPanel, BoxLayout.LINE_AXIS));
		headersPanel.add(new Label("coord\\point"));
		headersPanel.add(new Label("P1"));
		headersPanel.add(new Label("P2"));
		headersPanel.add(new Label("P3"));
		headersPanel.add(new Label("P4"));

		JPanel xCoordPanel = new JPanel();
		xCoordPanel.setLayout(new BoxLayout(xCoordPanel, BoxLayout.LINE_AXIS));
		xCoordPanel.add(new Label("x"));
		xCoordPanel.add(getInput(1, "x"));
		xCoordPanel.add(getInput(2, "x"));
		xCoordPanel.add(getInput(3, "x"));
		xCoordPanel.add(getInput(4, "x"));

		JPanel yCoordPanel = new JPanel();
		yCoordPanel.setLayout(new BoxLayout(yCoordPanel, BoxLayout.LINE_AXIS));
		yCoordPanel.add(new Label("y"));
		yCoordPanel.add(getInput(1, "y"));
		yCoordPanel.add(getInput(2, "y"));
		yCoordPanel.add(getInput(3, "y"));
		yCoordPanel.add(getInput(4, "y"));

		JPanel zCoordPanel = new JPanel();
		zCoordPanel.setLayout(new BoxLayout(zCoordPanel, BoxLayout.LINE_AXIS));
		zCoordPanel.add(new Label("z"));
		zCoordPanel.add(getInput(1, "z"));
		zCoordPanel.add(getInput(2, "z"));
		zCoordPanel.add(getInput(3, "z"));
		zCoordPanel.add(getInput(4, "z"));

		Button random = new Button("RANDOM");
		random.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textFields.forEach(
						(i, textField) -> textField.setText(Float.toString(new Random().nextFloat() * 10 - 5)));
				group.setText(Integer.toString(new Random().nextInt(10)));
				quality.setText(Float.toString(new Random().nextFloat() * 10 - 5));
			}
		});

		label = new TextField();

		result.add(random);
		result.add(label);
		result.add(getGroupPanel());
		result.add(getQualityPanel());
		result.add(headersPanel);
		result.add(xCoordPanel);
		result.add(yCoordPanel);
		result.add(zCoordPanel);
		return result;
	}

	public Map<Integer, Point> getPoints() {
		Map<Integer, Point> result = new TreeMap<>();

		for (int i = 1; i <= 4; i++) {
			double x, y, z;
			try {
				x = Double.parseDouble(textFields.get("x" + i).getText());
				y = Double.parseDouble(textFields.get("y" + i).getText());
				z = Double.parseDouble(textFields.get("z" + i).getText());
			} catch (Exception e) {
				x = y = z = 0;
			}
			result.put(i, new Point(x, y, z));
		}

		return result;
	}

	public int getGroup() {
		return Integer.parseInt(group.getText());
	}

	public double getQuality() {
		return Double.parseDouble(quality.getText());
	}

	public String getLabel() {
		return label.getText();
	}
}
