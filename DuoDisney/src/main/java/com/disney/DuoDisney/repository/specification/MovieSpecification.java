
package com.disney.DuoDisney.repository.specification;

import com.disney.DuoDisney.dto.MovieFilterDTO;
import com.disney.DuoDisney.entity.GenreEntity;
import com.disney.DuoDisney.entity.MovieEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

// @author aduo

@Component
public class MovieSpecification {

public Specification<MovieEntity> getFiltered(MovieFilterDTO movieFilters){

        // Lambda Function:
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // == Title ==
            if(StringUtils.hasLength(movieFilters.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + movieFilters.getTitle().toLowerCase() + "%"
                        )
                );
            }

            // == Genre ==
            if(!CollectionUtils.isEmpty(movieFilters.getGenre())) {
                Join<MovieEntity, GenreEntity> join = root.join("movieGenres", JoinType.INNER);
                Expression<String> genresId = join.get("id");
                predicates.add(genresId.in(movieFilters.getGenre()));
            }
            //Remove duplicates
            query.distinct(true);

            // == Order ==
            String orderByField = "title";
            query.orderBy(
                    movieFilters.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );


            // MAIN RETURN:
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
