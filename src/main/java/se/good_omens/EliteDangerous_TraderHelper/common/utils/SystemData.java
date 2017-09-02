package se.good_omens.EliteDangerous_TraderHelper.common.utils;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SystemData {

	private Path currentRelativePath = null;
	private int screenWidth = -1;
	private int screenHeight = -1;

	public SystemData() {
		currentRelativePath = Paths.get("");
		this.getMonitorDimensions();
	}

	public String getWorkingDirectory() {
		return currentRelativePath.toAbsolutePath().toString();
	}

	private void getMonitorDimensions() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		this.screenWidth = gd.getDisplayMode().getWidth();
		this.screenHeight = gd.getDisplayMode().getHeight();
	}

	public int getScreenWidth() {
		return this.screenWidth;
	}

	public int getScreenHeight() {
		return this.screenHeight;
	}
	
	public String getDirectoryDelimiter() {
		return File.separator;
	}
}
