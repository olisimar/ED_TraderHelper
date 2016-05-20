package se.good_omens.EliteDangerous_TraderHelper.common;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SystemData {

	private Path currentRelativePath = null;

	public SystemData() {
		currentRelativePath = Paths.get("");
	}

	public String getWorkingDirectory() {
		return currentRelativePath.toAbsolutePath().toString();
	}
}
