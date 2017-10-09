package se.good_omens.EliteDangerous_TraderHelper.GUI;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import se.good_omens.EliteDangerous_TraderHelper.DataStore;
import se.good_omens.EliteDangerous_TraderHelper.GUI.HelperClasses.StarSystemSelectorItem;
import se.good_omens.EliteDangerous_TraderHelper.common.utils.RuntimeProperties;

public class PersonalInfoPanel extends JPanel implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private volatile DataStore dataStore;
	private SystemJComboBox<StarSystemSelectorItem> systemSelector;

	public PersonalInfoPanel(DataStore dataStore) {
		this.dataStore = dataStore;
		this.setBorder(new BevelBorder(BevelBorder.RAISED));
		this.setSize(new Dimension(100, 200));
		ArrayList<StarSystemSelectorItem> items = StarSystemSelectorItem.of(dataStore.getSystemParser().getSystems());
		this.systemSelector = new SystemJComboBox<StarSystemSelectorItem>(items, new StarSystemSelectorItem(dataStore.getUserData().getCurrentSystem()));
		this.add(systemSelector);
		systemSelector.addPropertyChangeListener("StarSystemSelected", this);
	}
	
	public RuntimeProperties getProperties() {
		return this.dataStore.getRuntimeProperties();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		if(arg0.getNewValue() instanceof StarSystemSelectorItem) {
			StarSystemSelectorItem newSystem = (StarSystemSelectorItem)arg0.getNewValue();
			StarSystemSelectorItem oldSystem = (StarSystemSelectorItem)arg0.getOldValue();
			System.out.println("New> "+ newSystem +" || Old> "+ oldSystem);
			dataStore.getUserData().setCurrentSystem(newSystem.getSystem());
		}
	}
}
