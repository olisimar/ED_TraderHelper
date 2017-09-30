package se.good_omens.EliteDangerous_TraderHelper.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.Position;
import se.good_omens.EliteDangerous_TraderHelper.common.exceptions.FileMissingException;
import se.good_omens.EliteDangerous_TraderHelper.common.parsers.ParseInitialFile;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.FileHandler;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.SystemData;

public class testBasicFunctionalities {

	public static String	protocol	= "file:///";
	public static String	filePath	= "C:/Users/TuX/workspace/ED_TraderHelper/rawData/";

	private static SystemData systemData = new SystemData();

	
	@Test
	public void testInitialLoadOfVariables() {
		try {
			RuntimeProperties props = new ParseInitialFile(FileHandler.readFile(systemData.getWorkingDirectory() + systemData.getDirectoryDelimiter(), "tradehelper.ini")).getRuntimeProperties();
			
			System.out.println(" +------------+");
			for(String item : props.listKeys()) {
				System.out.println(item +" : "+ props.getEntry(item));
			}
		} catch (FileMissingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPositionCalculations() {
		Position pos1 = new Position(0, 0, 0);
		Position pos2 = new Position(3,4,0);
		Position pos3 = new Position(0,3,4);
		Position pos4 = new Position(4,0,3);
		Position pos5 = new Position(3,3,3);
		Assert.assertEquals(pos1.distanceTo(pos2), 5.0d);
		Assert.assertEquals(pos1.distanceTo(pos3), 5.0d);
		Assert.assertEquals(pos1.distanceTo(pos4), 5.0d);
		Assert.assertNotEquals(pos1.distanceTo(pos5), 5.0d);
	}
}
