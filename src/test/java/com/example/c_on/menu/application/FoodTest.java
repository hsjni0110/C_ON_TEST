package com.example.c_on.menu.application;

import com.example.c_on.common.IntegrationTest;
import com.example.c_on.menu.domain.Category;
import com.example.c_on.menu.domain.Contain;
import com.example.c_on.menu.domain.Food;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static com.example.c_on.menu.fixture.MenuFixture.양식;

@Slf4j
public class FoodTest extends IntegrationTest {

    private Category 양식;


    @BeforeEach
    void setUp() {
        양식 = categoryRepository.save(양식());
    }

    @Test
    void 음식을_저장할_수_있다() {
        // given, when
        foodService.save("돈까스", 10000, "양식");
        log.info("저장 완료됨.");

        // then
        List<Contain> 양식_음식 = containsRepository.findAllByCategory(양식);
        assertThat(양식_음식.get(0).getFood().getFoodName()).isEqualTo("돈까스");
    }
}

