package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.response.ResponseEntity;

/**
 * The interface provides a set of JPA methods
 * for working {@link ResponseEntity} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ResponseEntity
 */
public interface ResponseRepository extends DataRepository<ResponseEntity> {
}
