package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Category;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Industry;
import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.Specialist;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.CategoryRepo;
import kakaotech_bootcamp.team_21.coverletter_spring_project.repository.IndustryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final or @NonNull 으로 지정된 필드들만을 대상으로 하는 생성자 생성.
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final IndustryRepo industryRepo;

    public List<Category> getAllCategory() {

        return categoryRepo.findAll();
    }

    public List<Industry> getRelatedIndustries(Long categoryId) {

        Optional<Category> category = categoryRepo.findById(categoryId);
        return category.get().getIndustries();
    }

    @Transactional
    public List<Specialist> getRelatedSpecialists(Long categoryId) {

        Category category=categoryRepo.findById(categoryId).get();
        List<Industry> industries = category.getIndustries();

        List<Specialist> bucket=new ArrayList<>();
        for (Industry industry : industries) {
            List<Specialist> tmp = industryRepo.findRelatedSpecialistsById(industry.getId());
            bucket.addAll(tmp);
        }


        return bucket;
    }
}
