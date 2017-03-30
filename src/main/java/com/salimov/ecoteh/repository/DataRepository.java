package com.salimov.ecoteh.repository;

import com.salimov.ecoteh.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface provides a set of standard JPA methods
 * for working objects which extends {@link Model} with a database.
 *
 * @param <T> Entity type, extends {@link Model}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface DataRepository<T extends Model> extends JpaRepository<T, Long> {
}
