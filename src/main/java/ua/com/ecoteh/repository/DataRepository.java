package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * The interface provides a set of standard JPA methods
 * for working objects which extends {@link Model} with a database.
 *
 * @param <T> Entity type, extends {@link Model}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@NoRepositoryBean
public interface DataRepository<T extends Model> extends JpaRepository<T, Long> {
}
