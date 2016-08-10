package se.good_omens.EliteDangerous_TraderHelper.tests.utils;

import org.testng.annotations.Test;

import se.good_omens.EliteDangerous_TraderHelper.common.utils.SystemData;

public class TestSystemData {

	@Test
	public void testPrinting() {
		SystemData data = new SystemData();
		System.out.println("WorkingDir: "+ data.getWorkingDirectory());
	}
}
