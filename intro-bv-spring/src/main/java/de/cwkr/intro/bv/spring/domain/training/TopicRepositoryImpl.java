package de.cwkr.intro.bv.spring.domain.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class TopicRepositoryImpl implements TopicRepository {
    private final Map<Long, Topic> topics = new ConcurrentHashMap<>();

    public TopicRepositoryImpl() {
        topics.put(
            110L,
            Topic.builder()
                    .withId(110L)
                    .withDescription("Workshop 110")
                    .withActive(Boolean.TRUE)
                    .build()
        );
        topics.put(
            150L,
            Topic.builder()
                    .withId(110L)
                    .withDescription("Workshop 150")
                    .withActive(Boolean.TRUE)
                    .build()
        );
        topics.put(
            901L,
            Topic.builder()
                    .withId(901L)
                    .withDescription("Workshop 901")
                    .withActive(Boolean.FALSE)
                    .build()
        );
    }

    @Override
    public Optional<Topic> findById(Long id) {
        logger.debug("findById(id = {})", id);
        return Optional.ofNullable(topics.get(id));
    }

    @Override
    public List<Topic> findAll() {
        logger.debug("findAll()");
        return new ArrayList<>(topics.values());
    }

    @Override
    public <S extends Topic> S save(S s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Topic> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long id) {
        return topics.containsKey(id);
    }

    @Override
    public Iterable<Topic> findAllById(Iterable<Long> ids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        return topics.size();
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Topic topic) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Topic> iterable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
