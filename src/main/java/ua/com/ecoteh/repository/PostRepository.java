package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.post.PostEntity;

/**
 * The interface provides a set of JPA methods for working
 * {@link PostEntity} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see PostEntity
 */
public interface PostRepository extends ContentRepository<PostEntity> {
}
