package de.cwkr.intro.bv.spring.service.training;

import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "TopicService")
@RestController
public class TopicServiceController {
    private final TopicService topicService;

    public TopicServiceController(TopicService topicService) {
        this.topicService = topicService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping(path = "topics/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicDto> getTopicById(@PathVariable("id") Long id) {
        return ResponseEntity.of(topicService.getTopicById(id));
    }

    @GetMapping(path = "topics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TopicDto>> getTopics() {
        return ResponseEntity.ok(topicService.getTopics());
    }
}
