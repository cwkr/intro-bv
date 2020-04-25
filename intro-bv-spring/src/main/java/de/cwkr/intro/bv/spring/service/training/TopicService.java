package de.cwkr.intro.bv.spring.service.training;

import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;

public interface TopicService {
    Optional<TopicDto> getTopicById(@NotNull Long id);
    List<TopicDto> getTopics();
}
