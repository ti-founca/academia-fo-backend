package py.edu.odontounca.academiafoback.shared.pagination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort {
    private final List<Order> orders;

    private Sort(List<Order> orders){
        this.orders = Collections.unmodifiableList(orders);
    }

    public static Sort by(String field, Direction direction){
        return new Sort(List.of(new Order(field, direction)));
    }

    public static Sort unsorted(){
        return new Sort(List.of());
    }

    public Sort and(String field, Direction direction) {
        List<Order> newOrders = new ArrayList<>(this.orders);
        newOrders.add(new Order(field, direction));
        return new Sort(newOrders);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public enum Direction{ ASC, DESC }
    public static record Order(String field, Direction direction) { }
}
