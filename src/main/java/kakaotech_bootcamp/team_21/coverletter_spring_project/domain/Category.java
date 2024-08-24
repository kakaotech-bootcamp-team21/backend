package kakaotech_bootcamp.team_21.coverletter_spring_project.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)

public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent=null;

    @OneToMany(mappedBy = "parent")
    private List<Category> child=new ArrayList<>();

    private String name;

    @ManyToMany
    @JoinTable(name = "category_industry",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "industry_id"))
    private List<Industry> industries=new ArrayList<>();

    // -- 생성자 -- //
    public Category(String name) {
        this.name=name;
    }

    // -- 연관 관계 메서드 -- //
    public void addIndustry(Industry industry) {//이 객체에 Industry를 add 할 때, 해당 industry에 이 카테고리를 동시에 저장.
        industries.add(industry);
        industry.getCategories().add(this);
    }
    public void addIndustryList(List<Industry> industryList) {
        for (Industry industry : industryList) {
            addIndustry(industry);
        }
    }
    public void addParentCategory(Category category) {//이 객체에 parentCategory를 설정하면, 부모 카테고리에도 자식 카테고리를 동시에 반영.
        parent=category;
        category.getChild().add(this);
    }

    // -- 비즈니스 로직 -- //

}
