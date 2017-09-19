package se.good_omens.EliteDangerous_TraderHelper.GUI;

import javax.swing.JFrame;

import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;

public class PersonalInfoFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public PersonalInfoFrame(RuntimeProperties props) {
		this.setTitle("Crap");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setBounds(100, 100, 500, 500);
		this.add(new PersonalInfoPanel(props));
	}
}
