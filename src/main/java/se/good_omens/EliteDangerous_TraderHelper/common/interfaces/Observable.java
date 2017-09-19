package se.good_omens.EliteDangerous_TraderHelper.common.interfaces;

import java.util.Observer;

public interface Observable {

	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers();
}
