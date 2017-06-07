package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The interface provides a set of standard JPA methods
 * for working objects which extends {@link ModelEntity} with a database.
 *
 * @param <E> Entity type, extends {@link ModelEntity}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ModelEntity
 */
@NoRepositoryBean
public interface DataRepository<E extends ModelEntity> extends JpaRepository<E, Long> {
}
