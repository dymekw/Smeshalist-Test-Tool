package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Random;

import entity.Point;
import geometry.Point3D;
import geometry.Vertex;
import gui.GUI;

public class AddVerticesButton implements ActionListener {

	private GUI gui;

	public AddVerticesButton(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Map<String, Point> points = gui.getBulkTab().getPoints();
		int numberOfElements = gui.getBulkTab().getNumberOfElements();

		double maxX = points.get("to").getX();
		double minX = points.get("from").getX();
		double maxY = points.get("to").getY();
		double minY = points.get("from").getY();
		double maxZ = points.get("to").getZ();
		double minZ = points.get("from").getZ();

		double minQuality = gui.getBulkTab().minQuality();
		double maxQuality = gui.getBulkTab().maxQuality();

		int minGroup = gui.getBulkTab().minGroup();
		int maxGroup = gui.getBulkTab().maxGroup();

		for (int i = 0; i < numberOfElements; i++) {
			double x = new Random().nextDouble() * (maxX - minX) + minX;
			double y = new Random().nextDouble() * (maxY - minY) + minY;
			double z = new Random().nextDouble() * (maxZ - minZ) + minZ;

			double quality = new Random().nextDouble() * (maxQuality - minQuality) + minQuality;
			int group = new Random().nextInt(maxGroup - minGroup + 1) + minGroup;

			Point p = new Point(x, y, z);
			Vertex v = new Vertex(getPoint3D(p), 0);
			v.setGroupId(group);
			v.setQuality(quality);
			v.setLabel(gui.getSingleTab().getLabel());
			gui.getSmeshalist().addGeometry(v);
		}
	}

	private Point3D getPoint3D(Point p) {
		return new Point3D(p.getX(), p.getY(), p.getZ());
	}
}
