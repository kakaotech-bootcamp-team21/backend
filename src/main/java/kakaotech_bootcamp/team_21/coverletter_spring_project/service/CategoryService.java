package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Category;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Industry;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor // final or @NonNull 으로 지정된 필드들만을 대상으로 하는 생성자 생성.
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public List<Category> getAllCategory() {

        return categoryRepo.findAll();
    }

    public List<Industry> getRelatedIndustries(Long categoryID) {

        Optional<Category> category = categoryRepo.findById(categoryID);
        return category.get().getIndustries();
    }

}
