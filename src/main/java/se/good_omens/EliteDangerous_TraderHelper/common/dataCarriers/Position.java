package se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers;

public class Position {

	private final double	x;
	private final double	y;
	private final double	z;

	public Position(String x, String y, String z) {
		this(new Double(x), new Double(y), new Double(z));
	}

	public Position(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getXaxis() {
		return this.x;
	}

	public double getYaxis() {
		return this.y;
	}

	public double getZaxis() {
		return this.z;
	}
	
	public double distanceTo(Position pos) {
		// d = sqr of ( (this.x - pos.x)x2 + (this.y - pos.y)x2 + (this.z - pos.z)x2 )
		double relX = this.x - pos.getXaxis();
		double relY = this.y - pos.getYaxis();
		double relZ = this.z - pos.getZaxis();
		relX = relX * relX;
		relY = relY * relY;
		relZ = relZ * relZ;
		
		return Math.sqrt(relX + relY + relZ);
	}
}
