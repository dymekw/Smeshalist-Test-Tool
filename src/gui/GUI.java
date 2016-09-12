package gui;

import tool.Smeshalist;

public interface GUI {
	SingleTab getSingleTab();

	BulkTab getBulkTab();

	Smeshalist getSmeshalist();
}
