package se.good_omens.EliteDangerous_TraderHelper.GUI;

import java.awt.Dimension;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;

import se.good_omens.EliteDangerous_TraderHelper.DataStore;
import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.StarSystem;

public class PersonalInfoFrame extends JFrame {
	private DataStore dataStore;

	private SystemJComboBox<StarSystem> systemSelector;

	private static final long serialVersionUID = 1L;

	public PersonalInfoFrame(DataStore dataStore) {
		this.dataStore =  dataStore;
		this.setTitle("Crap");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setBounds(100, 100, 500, 500);
		this.add(new PersonalInfoPanel(dataStore));
	}
}
