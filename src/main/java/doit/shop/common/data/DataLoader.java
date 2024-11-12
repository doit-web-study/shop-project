package doit.shop.common.data;

import doit.shop.repository.category.Category;
import doit.shop.repository.category.CategoryRepository;
import doit.shop.repository.category.CategoryType;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DataLoader {

    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        if (categoryRepository.count() > 0) {
            return;
        }

        Arrays.stream(CategoryType.values())
                .map(Category::create)
                .forEach(categoryRepository::save);
    }
}

