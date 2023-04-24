package pl.edu.agh.dronka.shop.model.provider;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.Index;
import pl.edu.agh.dronka.shop.model.Item;
import pl.edu.agh.dronka.shop.model.Shop;
import pl.edu.agh.dronka.shop.model.User;
import pl.edu.agh.dronka.shop.model.items.*;

public class ShopProvider {

	public static Shop getExampleShop() {
		Shop shop = new Shop();

		shop.addUser(getExampleUser());

		Index itemsIndex = new Index();

		for (Item item : getExampleItems()) {
			itemsIndex.addItem(item);
		}

		registerExampleCategories(itemsIndex);
		shop.setItemsIndex(itemsIndex);

		return shop;
	}

	public static User getExampleUser() {
		return new User("Jan", "Rejnor");
	}

	public static List<Item> getExampleItems() {
		List<Item> items = new ArrayList<>();

		CSVReader booksReader = new CSVReader("resources/books.csv");
		items.addAll(readItems(booksReader, Category.BOOKS));
		
		CSVReader electronicsReader = new CSVReader("resources/electronics.csv");
		items.addAll(readItems(electronicsReader, Category.ELECTRONICS));
		
		CSVReader foodReader = new CSVReader("resources/food.csv");
		items.addAll(readItems(foodReader, Category.FOOD));
		
		CSVReader musicReader = new CSVReader("resources/music.csv");
		items.addAll(readItems(musicReader, Category.MUSIC));
		
		CSVReader sportReader = new CSVReader("resources/sport.csv");
		items.addAll(readItems(sportReader, Category.SPORT));

		return items;
	}

	public static void registerExampleCategories(Index index) {
		for (Category category : Category.values()) {
			index.registerCategory(category);
		}
	}

	private static List<Item> readItems(CSVReader reader, Category category) {
		List<Item> items = new ArrayList<>();

		try {
			reader.parse();
			List<String[]> data = reader.getData();

			for (String[] dataLine : data) {
	
				String name = reader.getValue(dataLine,"Nazwa");
				int price = Integer.parseInt(reader.getValue(dataLine, "Cena"));
				int quantity = Integer.parseInt(reader.getValue(dataLine,
						"Ilość"));

				boolean isPolish = Boolean.parseBoolean(reader.getValue(
						dataLine, "Tanie bo polskie"));
				boolean isSecondhand = Boolean.parseBoolean(reader.getValue(
						dataLine, "Używany"));

//				Item item = new Item(name, category, price, quantity);
				Item item = switch (category) {
					case BOOKS -> new Book(
							name, category, price, quantity,
							Integer.parseInt(reader.getValue(dataLine,"Liczba stron")),
							Boolean.parseBoolean(reader.getValue(dataLine, "Twarda oprawa"))
							);
					case ELECTRONICS -> new Electronics(
							name, category, price, quantity,
							Boolean.parseBoolean(reader.getValue(dataLine, "Mobilny")),
							Boolean.parseBoolean(reader.getValue(dataLine, "Gwarancja"))
					);
					case FOOD -> new Food(
							name, category, price, quantity,
							LocalDate.parse(reader.getValue(dataLine, "Spozyc do"))
					);
					case MUSIC -> new Music(
							name, category, price, quantity,
							MusicGenre.parse(reader.getValue(dataLine, "Gatunek")),
							Boolean.parseBoolean(reader.getValue(dataLine, "Video"))
					);
					case SPORT -> new Sport(
							name, category, price, quantity
					);
					default -> new Item(name, category, price, quantity);
				};
				item.setPolish(isPolish);
				item.setSecondhand(isSecondhand);

				items.add(item);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return items;
	}

}
