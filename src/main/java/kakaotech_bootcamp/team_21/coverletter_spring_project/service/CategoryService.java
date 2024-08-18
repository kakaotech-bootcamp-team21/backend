package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Category;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Industry;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor // final or @NonNull 으로 지정된 필드들만을 대상으로 하는 생성자 생성.
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {

        return categoryRepository.findAll();
    }

    public List<Industry> getRelatedIndustries(Long categoryID) {

        Optional<Category> category = categoryRepository.findById(categoryID);
        return category.get().getIndustries();
    }

}
