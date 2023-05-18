package pl.edu.agh.internetshop.search;

import pl.edu.agh.internetshop.Order;

import java.util.List;

public class SearchWithMultipleStrategies implements ISearchStrategy {
    private final List<ISearchStrategy> searchStrategies;

    public SearchWithMultipleStrategies(List<ISearchStrategy> searchStrategies) {
        this.searchStrategies = searchStrategies;
    }

    @Override
    public boolean filter(Order order) {
        for (ISearchStrategy searchStrategy : this.searchStrategies)
            if (!searchStrategy.filter(order))
                return false;
        return true;
    }
}
