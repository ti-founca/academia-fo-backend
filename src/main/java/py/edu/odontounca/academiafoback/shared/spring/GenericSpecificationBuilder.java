package py.edu.odontounca.academiafoback.shared.spring;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import py.edu.odontounca.academiafoback.shared.filter.Filter;

import java.util.List;
import java.util.Map;

public class GenericSpecificationBuilder<T> {
    private final Map<String, String> fieldMapping;

    public GenericSpecificationBuilder(Map<String, String> fieldMapping){
        this.fieldMapping = fieldMapping;
    }

    public Specification<T> build(Filter filter){
        if(filter == null || filter.getCriteriaList().isEmpty()) {
            //return Specification.where(null);
            return null;
        }
        List<Specification<T>> specs = filter.getCriteriaList()
                .stream()
                .map(this::toSpecification)
                .toList();

        Specification<T> result = specs.get(0);
        for(int i = 1; i < specs.size(); i++) result = result.and(specs.get(i));
        return result;
    }

    private Specification<T> toSpecification(Filter.Criteria filterCriteria){
        String entityField = fieldMapping.getOrDefault(filterCriteria.field(), filterCriteria.field());
        return (root, query, cb) -> {
            Path<Object> path = getPath(root, entityField);
            Object value = filterCriteria.value();

            return switch (filterCriteria.operator()){
                case EQUALS -> cb.equal(path, value);
                case NOT_EQUALS -> cb.notEqual(path, value);
                case LIKE -> cb.like(path.as(String.class), "%" + value + "%");
                case GREATER_THAN -> cb.greaterThan(path.as(Comparable.class), (Comparable) value);
                case LESS_THAN -> cb.lessThan(path.as(Comparable.class), (Comparable) value);
                case GREATER_OR_EQUAL -> cb.greaterThanOrEqualTo(path.as(Comparable.class), (Comparable) value);
                case LESS_OR_EQUAL -> cb.lessThanOrEqualTo(path.as(Comparable.class), (Comparable) value);
                case IN -> path.in((List<?>) value);
                case IS_NULL -> cb.isNull(path);
                case IS_NOT_NULL -> cb.isNotNull(path);
            };
        };
    }

    private Path<Object> getPath(Root<T> root, String field){
        String[] parts = field.split("\\.");
        Path<Object> path = root.get(parts[0]);
        for(int i = 1; i < parts.length; i++) path = path.get(parts[i]);
        return path;
    }
}
