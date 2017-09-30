package se.good_omens.EliteDangerous_TraderHelper.GUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import se.good_omens.EliteDangerous_TraderHelper.common.dataCarriers.StarSystem;

@SuppressWarnings({ "hiding"})
public class SystemJComboBox<StarSystem> extends JComboBox<StarSystem> {

	private static final long serialVersionUID = 1L;
	private ArrayList<StarSystem> systems = null;
	private volatile StarSystem getSelectedSystem;
	
	public SystemJComboBox(List<StarSystem> systems) {
		this.systems = (ArrayList<StarSystem>) systems;
	}

	public StarSystem getSelectedSystem() {
		return this.getSelectedSystem;
	}
}
