package se.good_omens.EliteDangerous_TraderHelper;

import se.good_omens.EliteDangerous_TraderHelper.common.exceptions.FileMissingException;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.FileReader;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.SystemData;

public class ED_TraderHelper {

	public static void main(String[] args) {
		SystemData sysData = new SystemData();
		System.out.println(sysData.getWorkingDirectory());
		System.out.println(sysData.getScreenWidth());
		System.out.println(sysData.getScreenHeight());
		try {
			String properties = FileReader.readFile(sysData.getWorkingDirectory(), "tradehelper.ini");
			System.out.println(FileReader.readFile(sysData.getWorkingDirectory(), "tradehelper.ini"));
		} catch (FileMissingException e) {
			System.out.println(e.getMessage());
		}
	}
}
