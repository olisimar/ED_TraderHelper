package se.good_omens.EliteDangerous_TraderHelper.GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class SystemJComboBox<StarSystemSelectorItem> extends JComboBox<StarSystemSelectorItem> {

	private static final long									serialVersionUID	= 1L;
	private ArrayList<StarSystemSelectorItem>	items;
	private StarSystemSelectorItem selected;

	public SystemJComboBox(ArrayList<StarSystemSelectorItem> items, StarSystemSelectorItem selected) {
		this.items = items;
		this.selected = selected;
		this.setPreferredSize(new Dimension(250, 30));

		for (StarSystemSelectorItem entry : items) {
			this.addItem(entry);
		}

		this.setVisible(true);
		this.setEnabled(true);
		this.setEditable(true);
		this.addActionListener(this);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.getSelectedItem() instanceof String) {
			boolean found = false;
			String text = (String) this.getSelectedItem();
			for (StarSystemSelectorItem entry : items) {
				if (entry.toString().toLowerCase().contains(text.toLowerCase())) {
					this.setSelectedItem(entry);
					found = true;
					break;
				}
			}
			if(!found) {
				this.setSelectedItem(this.getModel().getElementAt(0));
			}
		}
		this.firePropertyChange("StarSystemSelected", selected, this.getSelectedItem());
		this.selected = (StarSystemSelectorItem) this.getSelectedItem();
	}
}
