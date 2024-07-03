package com.example.c_on.menu.domain.repository;

import com.example.c_on.menu.domain.Category;
import com.example.c_on.menu.domain.Contain;
import com.example.c_on.menu.domain.ContainId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainsRepository extends JpaRepository<Contain, ContainId> {

    List<Contain> findAllByCategory(Category category);
}
