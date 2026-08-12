package py.edu.odontounca.academiafoback.shared.spring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PaginationConverter {

    public static Pageable toSpringPageable(py.edu.odontounca.academiafoback.shared.pagination.PageRequest pageRequest){
        return PageRequest.of(
                pageRequest.page() - 1, //Spring usa base 0
                pageRequest.size(),
                toSpringSort(pageRequest.sort())
        );
    }

    private static Sort toSpringSort(py.edu.odontounca.academiafoback.shared.pagination.Sort sort){
        if(sort == null || sort.getOrders().isEmpty()) return Sort.unsorted();
        List<Sort.Order> orders = sort.getOrders()
                .stream()
                .map(o -> new Sort.Order(
                        o.getDirection() == py.edu.odontounca.academiafoback.shared.pagination.Sort.Direction.ASC ?
                                Sort.Direction.ASC : Sort.Direction.DESC,
                        o.getField()
                )).toList();
        return Sort.by(orders);
    }

    public static <T> py.edu.odontounca.academiafoback.shared.pagination.Page<T> fromSpringPage(Page<T> springPage){
        return py.edu.odontounca.academiafoback.shared.pagination.Page.of(
                springPage.getContent(),
                springPage.getNumber() + 1, // Convertir a la paginacion de la API
                springPage.getSize(),
                springPage.getTotalElements()
        );
    }
}
