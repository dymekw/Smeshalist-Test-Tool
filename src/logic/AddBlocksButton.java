package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Random;

import entity.Point;
import geometry.Block;
import geometry.Point3D;
import gui.GUI;

public class AddBlocksButton implements ActionListener {

	private GUI gui;

	public AddBlocksButton(GUI gui) {
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
			double x1 = new Random().nextDouble() * (maxX - minX) + minX;
			double y1 = new Random().nextDouble() * (maxY - minY) + minY;
			double z1 = new Random().nextDouble() * (maxZ - minZ) + minZ;

			double x2 = new Random().nextDouble() * (maxX - minX) + minX;
			double y2 = new Random().nextDouble() * (maxY - minY) + minY;
			double z2 = new Random().nextDouble() * (maxZ - minZ) + minZ;

			double x3 = new Random().nextDouble() * (maxX - minX) + minX;
			double y3 = new Random().nextDouble() * (maxY - minY) + minY;
			double z3 = new Random().nextDouble() * (maxZ - minZ) + minZ;

			double x4 = new Random().nextDouble() * (maxX - minX) + minX;
			double y4 = new Random().nextDouble() * (maxY - minY) + minY;
			double z4 = new Random().nextDouble() * (maxZ - minZ) + minZ;

			double quality = new Random().nextDouble() * (maxQuality - minQuality) + minQuality;
			int group = new Random().nextInt(maxGroup - minGroup + 1) + minGroup;

			Point p1 = new Point(x1, y1, z1);
			Point p2 = new Point(x2, y2, z2);
			Point p3 = new Point(x3, y3, z3);
			Point p4 = new Point(x4, y4, z4);

			Block b = new Block(getPoint3D(p1), getPoint3D(p2), getPoint3D(p3), getPoint3D(p4));
			b.setGroupId(group);
			b.setQuality(quality);
			b.setLabel(gui.getSingleTab().getLabel());
			gui.getSmeshalist().addGeometry(b);
		}
	}

	private Point3D getPoint3D(Point p) {
		return new Point3D(p.getX(), p.getY(), p.getZ());
	}

}
