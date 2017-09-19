package se.good_omens.EliteDangerous_TraderHelper.GUI;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;

public class PersonalInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private volatile RuntimeProperties props;

	public PersonalInfoPanel(RuntimeProperties props) {
		this.props = props;
		this.setBorder(new BevelBorder(BevelBorder.RAISED));
		this.setSize(new Dimension(100, 200));
		this.add(new JButton("Press Me"));
	}
	
	public RuntimeProperties getProperties() {
		return this.props;
	}
}
