package de.cwkr.intro.bv.spring.domain.training;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TopicRepository extends CrudRepository<Topic, Long> {
    @Override
    List<Topic> findAll();
}
