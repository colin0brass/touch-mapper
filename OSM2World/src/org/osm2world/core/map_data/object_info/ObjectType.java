package org.osm2world.core.map_data.object_info;

import org.openstreetmap.josm.plugins.graphview.core.data.TagGroup;
import org.osm2world.core.osm.data.OSMElement;

public class ObjectType {
	final MainType maintype;
	final String subtype;
	
	private ObjectType(MainType maintype, String subtype) {
		this.maintype = maintype;
		this.subtype = subtype;
	}
	
	public static ObjectType fromElement(OSMElement element) {
		TagGroup tags = element.tags;
		
		if (tags.contains("highway", "bus_stop")) {
			return poi("bus_stop");
		}
		if (tags.containsKey("public_transport") && tags.contains("bus", "yes")) {
			return poi("bus_stop");
		}
		if (tags.contains("railway", "tram_stop")) {
			return poi("tram_stop");
		}
		if (tags.containsKey("public_transport") && tags.contains("tram", "yes")) {
			return poi("bus_stop");
		}
		if (tags.containsKey("shop")) {
			return poi("shop");
		}
		if (tags.containsKey("cuisine") || tags.contains("amenity", "restaurant")) {
			return poi("restaurant");
		}
		// if (tags.contains("amenity", "community_centre")) {
			// return poi("community_centre");
		// }
		// if (tags.contains("")) {
			// return poi("");
		// }
		return new ObjectType(MainType.UNKNOWN, "");
	}
	
	private static ObjectType poi(String subtype) {
		return new ObjectType(MainType.POI, subtype);
	}
	private static ObjectType way(String subtype) {
		return new ObjectType(MainType.WAY, subtype);
	}
	static enum MainType {
		WAY,         // roads, railways, rivers
		POI,         // bus stops, shops
		UNKNOWN }
}
