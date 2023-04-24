package pl.edu.agh.dronka.shop.model.util;

import java.util.LinkedHashMap;
import java.util.Map;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.Item;
import pl.edu.agh.dronka.shop.model.items.Book;
import pl.edu.agh.dronka.shop.model.items.Electronics;
import pl.edu.agh.dronka.shop.model.items.Food;
import pl.edu.agh.dronka.shop.model.items.Music;

public class PropertiesHelper {

	public static Map<String, Object> getPropertiesMap(Item item) {
		Map<String, Object> propertiesMap = new LinkedHashMap<>();
		
		propertiesMap.put("Nazwa", item.getName());
		propertiesMap.put("Cena", item.getPrice());
		propertiesMap.put("Kategoria", item.getCategory().getDisplayName()); 
		propertiesMap.put("Ilość", Integer.toString(item.getQuantity()));
		propertiesMap.put("Tanie bo polskie", item.isPolish());
		propertiesMap.put("Używany", item.isSecondhand());

		switch (item.getCategory()) {
			case BOOKS -> {
				propertiesMap.put("Liczba stron", ((Book)item).getNumberOfPages());
				propertiesMap.put("Twarda okładna", ((Book)item).isHardcover());
			}
			case ELECTRONICS -> {
				propertiesMap.put("Urządzenie mobilne", ((Electronics)item).isMobile());
				propertiesMap.put("Gwarancja", ((Electronics)item).hasWarranty());
			}
			case FOOD -> {
				propertiesMap.put("Spożyć do", ((Food)item).getEatByDate());
			}
			case MUSIC -> {
				propertiesMap.put("Gatunek", ((Music)item).getGenre().getDisplayName());
				propertiesMap.put("Dołączone video", ((Music)item).hasVideo());
			}
		}
		
		return propertiesMap;
	}
}
