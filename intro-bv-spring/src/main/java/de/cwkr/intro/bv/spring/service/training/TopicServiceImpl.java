package de.cwkr.intro.bv.spring.service.training;

import de.cwkr.intro.bv.spring.domain.training.Topic;
import de.cwkr.intro.bv.spring.domain.training.TopicRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Optional<TopicDto> getTopicById(Long id) {
        logger.debug("getTopicById(id = {})", id);
        return topicRepository.findById(id).map(this::toTopicDto);
    }

    @Override
    public List<TopicDto> getTopics() {
        logger.debug("getTopics()");
        return topicRepository.findAll()
                              .stream()
                              .map(this::toTopicDto).collect(Collectors.toList());
    }

    public TopicDto toTopicDto(Topic topic) {
        logger.debug("toTopicDto(topic = {})", topic);
        return TopicDto.builder()
                       .withId(topic.getId())
                       .withDescription(topic.getDescription())
                       .withActive(topic.getActive())
                       .build();
    }
}
