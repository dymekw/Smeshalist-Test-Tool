package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import entity.Point;
import geometry.Block;
import geometry.Edge;
import geometry.Point3D;
import geometry.TriangleFace;
import geometry.Vertex;
import gui.GUI;

public class AddButtonListener implements ActionListener {
	private GUI gui;
	private StructureType structureType;

	public AddButtonListener(GUI gui, StructureType structureType) {
		this.gui = gui;
		this.structureType = structureType;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Map<Integer, Point> points = gui.getSingleTab().getPoints();
		Point p, p1, p2, p3, p4;

		switch (structureType) {
		case BLOCK:
			p1 = points.get(1);
			p2 = points.get(2);
			p3 = points.get(3);
			p4 = points.get(4);
			Block b = new Block(getPoint3D(p1), getPoint3D(p2), getPoint3D(p3), getPoint3D(p4));
			b.setGroupId(gui.getSingleTab().getGroup());
			b.setQuality(gui.getSingleTab().getQuality());
			b.setLabel(gui.getSingleTab().getLabel());
			gui.getSmeshalist().addGeometry(b);
			break;
		case EDGE:
			p1 = points.get(1);
			p2 = points.get(2);
			Edge e = new Edge(getPoint3D(p1), getPoint3D(p2));
			e.setGroupId(gui.getSingleTab().getGroup());
			e.setQuality(gui.getSingleTab().getQuality());
			e.setLabel(gui.getSingleTab().getLabel());
			gui.getSmeshalist().addGeometry(e);
			break;
		case FACE:
			p1 = points.get(1);
			p2 = points.get(2);
			p3 = points.get(3);
			TriangleFace f = new TriangleFace(getPoint3D(p1), getPoint3D(p2), getPoint3D(p3));
			f.setGroupId(gui.getSingleTab().getGroup());
			f.setQuality(gui.getSingleTab().getQuality());
			f.setLabel(gui.getSingleTab().getLabel());
			gui.getSmeshalist().addGeometry(f);
			break;
		case VERTEX:
			p = points.get(1);
			Vertex v = new Vertex(getPoint3D(p), 0);
			v.setGroupId(gui.getSingleTab().getGroup());
			v.setQuality(gui.getSingleTab().getQuality());
			v.setLabel(gui.getSingleTab().getLabel());
			gui.getSmeshalist().addGeometry(v);
			break;
		default:
			break;
		}
	}

	private Point3D getPoint3D(Point p) {
		return new Point3D(p.getX(), p.getY(), p.getZ());
	}

}
