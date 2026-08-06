package py.edu.odontounca.academiafoback.shared.pagination;

import java.util.List;

public record PageRequest(int page, int size, Sort sort) {
    public static PageRequest of(int page, int size){
        return new PageRequest(page, size, Sort.unsorted());
    }

    public static PageRequest of(int page, int size, Sort sort){
        return new PageRequest(page, size, sort);
    }
}
