package se.good_omens.EliteDangerous_TraderHelper;

import se.good_omens.EliteDangerous_TraderHelper.common.utils.SystemData;

public class ED_TraderHelper {

	public static void main(String[] args) {
		SystemData sysData = new SystemData();
		System.out.println(sysData.getWorkingDirectory());
		System.out.println(sysData.getScreenHeight());
		System.out.println(sysData.getScreenWidth());
	}
}
