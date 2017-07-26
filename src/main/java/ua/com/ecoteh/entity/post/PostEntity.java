package ua.com.ecoteh.entity.post;

import ua.com.ecoteh.entity.content.ContentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link PostEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Entity
@Table(name = "posts")
public class PostEntity extends ContentEntity {

    /**
     * The date of this post entity.
     */
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * Constructor.
     */
    protected PostEntity() {
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "PostEntity{" + super.toString() +
                ", date=" + this.date +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the object
     * argument, false otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final PostEntity other = (PostEntity) object;
            result = this.date.equals(other.date);
        }
        return result;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return super.hashCode() + this.date.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public PostEntity clone() {
        return (PostEntity) super.clone();
    }

    /**
     * Returns a date of the post entity.
     *
     * @return The post entity date (newer null).
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets a new date to the post entity.
     *
     * @param date the new date to the post entity.
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Converts this entity and returns a object
     * of the {@link Post} class.
     *
     * @return The object of the {@link Post} class (newer null).
     * @see PostEntityConverter
     */
    @Override
    public Post convert() {
        return new PostEntityConverter(this).convert();
    }
}
