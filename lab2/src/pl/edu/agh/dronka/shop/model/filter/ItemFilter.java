package pl.edu.agh.dronka.shop.model.filter;

import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.Item;
import pl.edu.agh.dronka.shop.model.items.Book;
import pl.edu.agh.dronka.shop.model.items.Electronics;
import pl.edu.agh.dronka.shop.model.items.Music;

public class ItemFilter {
	private Item itemSpec = new Item();

	public Item getItemSpec() {
		return itemSpec;
	}
	public void setCategory(Category category) {
		itemSpec = switch (category) {
			case BOOKS -> new Book();
			case ELECTRONICS -> new Electronics();
			case MUSIC -> new Music();
			default -> itemSpec;
		};
		itemSpec.setCategory(category);
	}
	public boolean appliesTo(Item item) {
		if (itemSpec.getName() != null
				&& !itemSpec.getName().equals(item.getName())) {
			return false;
		}
		if (itemSpec.getCategory() != null
				&& !itemSpec.getCategory().equals(item.getCategory())) {
			return false;
		}

		// applies filter only if the flag (secondHand) is true)
		if (itemSpec.isSecondhand() && !item.isSecondhand()) {
			return false;
		}

		// applies filter only if the flag (polish) is true)
		if (itemSpec.isPolish() && !item.isPolish()) {
			return false;
		}

		switch (itemSpec.getCategory()) {
			case BOOKS -> {
				if (((Book)itemSpec).isHardcover() && !((Book)item).isHardcover())
					return false;
			}
			case ELECTRONICS -> {
				if (((Electronics)itemSpec).isMobile() && !((Electronics)item).isMobile())
					return false;
				if (((Electronics)itemSpec).hasWarranty() && !((Electronics)item).hasWarranty())
					return false;
			}
			case MUSIC -> {
				if (((Music)itemSpec).hasVideo() && !((Music)item).hasVideo())
					return false;
			}
		}

		return true;
	}

}