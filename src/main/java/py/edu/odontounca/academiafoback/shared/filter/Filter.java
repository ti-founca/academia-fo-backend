package py.edu.odontounca.academiafoback.shared.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Filter {
    private List<Criteria> criteriaList;

    public Filter() {
        this.criteriaList = new ArrayList<>();
    }
    public Filter(List<Criteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public List<Criteria> getCriteriaList(){
        return Collections.unmodifiableList(this.criteriaList);
    }

    public record Criteria(String field, Operator operator, Object value) { }
    public enum Operator {
        EQUALS, NOT_EQUALS, LIKE, GREATER_THAN, LESS_THAN,
        GREATER_OR_EQUAL, LESS_OR_EQUAL, IN, IS_NULL, IS_NOT_NULL
    }

    public static Filter empty(){
        return new Filter();
    }
    public static Filter of(Criteria... criterias){
        return new Filter(Arrays.stream(criterias).toList());
    }
}
