package com.disney.DuoDisney.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @author aduo
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharFilterDTO {

    private String name;
    private Integer age;
    private Set<Long> movies;
    private String order;

    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return order.compareToIgnoreCase("DESC") == 0;
    }
}
