package rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dhval on 3/30/15.
 */
@Transactional
public interface PersonRepository extends CrudRepository<Person, Long> {
    Page<Person> findAll(Pageable pageable);

    long count();

    Person findById(Long id);
}