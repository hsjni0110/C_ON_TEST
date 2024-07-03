package com.example.c_on.menu.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContainId implements Serializable {

    private String foodName;
    private String categoryName;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContainId containId = (ContainId) o;
        return Objects.equals(foodName, containId.foodName) && Objects.equals(categoryName,
                containId.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodName, categoryName);
    }
}
