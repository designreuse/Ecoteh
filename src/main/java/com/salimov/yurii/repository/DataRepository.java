package com.salimov.yurii.repository;

import com.salimov.yurii.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface provides a set of standard JPA methods
 * for working objects which extends {@link Model} with a database.
 *
 * @param <T>  Entity type, extends {@link Model}.
 * @param <E> Entity id type, extends Number.
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see ArticleRepository
 * @see CategoryRepository
 * @see CompanyRepository
 * @see FileRepository
 * @see ResponseRepository
 * @see UserRepository
 * @see Model
 */
public interface DataRepository<T extends Model<E>, E extends Number>
        extends JpaRepository<T, E> {
}
