package py.edu.odontounca.academiafoback.shared.pagination;

import java.util.ArrayList;
import java.util.Arrays;
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
    public static Sort by(Order... orders){ return new Sort(List.of(orders)); }
    public Sort and(String field, Direction direction) {
        List<Order> newOrders = new ArrayList<>(this.orders);
        newOrders.add(new Order(field, direction));
        return new Sort(newOrders);
    }
    public static Sort fromString(String orders){
        if(orders == null || orders.isBlank())
            throw new IllegalArgumentException("La cadena para ordenar el resultado es nula o está vacía");
        return new Sort(
                Arrays.stream(orders.split(","))
                        .map(ord -> Order.fromString(ord))
                        .toList()
        );
    }
    public List<Order> getOrders() {
        return orders;
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }

    public enum Direction{ ASC, DESC }
    public static class Order {
        private String field;
        private Direction direction;

        private Order(String field, Direction direction){
            this.field = field;
            this.direction = direction;
        }

        public String getField() { return field; }
        public Direction getDirection() { return direction; }

        public static Order of(String field, Direction direction){
            return new Order(field, direction);
        }
        public static Order fromString(String order){
            if(order == null || order.isBlank())
                throw new IllegalArgumentException("La cadena de Order es nulo o está en blanco");
            if(order.length() <2 )
                throw new IllegalArgumentException("La cadena de Order debe ser tener una longitud mayor a 1");
            if(!order.startsWith("+") && !order.startsWith("-"))
                throw new IllegalArgumentException("El orden debe iniciar con + o -");
            return Order.of(order.substring(1), order.charAt(0) == '+' ? Direction.ASC : Direction.DESC);
        }
    }
}
