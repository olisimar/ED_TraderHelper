package se.good_omens.EliteDangerous_TraderHelper.common.interfaces;

import se.good_omens.EliteDangerous_TraderHelper.common.utils.XML.XmlNode;

/**
 * A class that implements this interface are also meant to produce an saveble representation.
 * The representation is supposed to be transportable regardless of client.
 *
 * @author Werner
 * @date Jun 9, 2015
 */
public interface XmlEntity {

	/**
	 * Should produce a representation that the entity can use to return to its
	 * current state.
	 * @return XmlNode
	 */
	public XmlNode generateXmlRepresentation();
}
