package ua.com.ecoteh.entity.message;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.ecoteh.entity.model.ModelEntity;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.util.time.Time;

import javax.persistence.*;
import java.util.Date;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link MessageEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Entity
@Table(name = "messages")
public class MessageEntity extends ModelEntity {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The messageEntity userEntity.
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "user_id", nullable = false,
            referencedColumnName = "id"
    )
    @Fetch(FetchMode.JOIN)
    private UserEntity userEntity;

    /**
     * The subject of a messageEntity.
     */
    @Column(name = "subject", nullable = false)
    private String subject;

    /**
     * The text of a messageEntity.
     */
    @Column(name = "text", nullable = false)
    private String text;

    /**
     * The date of created messageEntity.
     */
    @Column(name = "date", nullable = false)
    private Date date;

    /**
     * Default constructor.
     */
    public MessageEntity() {
        this.userEntity = new UserEntity();
        this.subject = "";
        this.text = "";
        this.date = new Date();
    }

    /**
     * Constructor.
     *
     * @param userEntity the messageEntity userEntity.
     * @param subject    the subject of a new messageEntity.
     * @param text       the text of a new messageEntity.
     */
    public MessageEntity(
            final UserEntity userEntity,
            final String subject,
            final String text
    ) {
        this();
        setUserEntity(userEntity);
        setSubject(subject);
        setText(text);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "MessageEntity{" + super.toString() +
                ", userEntity=" + getUserEntity() +
                ", subject='" + getSubject() + '\'' +
                ", text='" + getText() + '\'' +
                ", date=" + getDate() +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the obj
     * argument, false otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final MessageEntity other = (MessageEntity) object;
            result = this.getUserEntity().equals(other.getUserEntity()) &&
                    this.getSubject().equalsIgnoreCase(other.getSubject()) &&
                    this.getText().equalsIgnoreCase(other.getText());
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
        return getUserEntity().hashCode() +
                getSubject().hashCode() +
                getText().hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public MessageEntity clone() {
        final MessageEntity messageEntity = (MessageEntity) super.clone();
        messageEntity.setUserEntity(getUserEntity().clone());
        return messageEntity;
    }

    /**
     * Returns a userEntity of the messageEntity.
     *
     * @return The userEntity of the messageEntity (newer null).
     */
    public UserEntity getUserEntity() {
        return this.userEntity;
    }

    /**
     * Sets a userEntity of the messageEntity.
     *
     * @param userEntity the userEntity of the messageEntity.
     */
    public void setUserEntity(final UserEntity userEntity) {
        if (isNull(this.userEntity)) {
            this.userEntity = new UserEntity();
        }
        this.userEntity.initialize(userEntity);
    }

    /**
     * Returns a subject of the messageEntity.
     *
     * @return The messageEntity subject or empty string (newer null).
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Sets a new subject to the messageEntity.
     * If parameter subject is blank, then sets empty string.
     * <pre>
     *     setSubject(null) - subject = ""
     *     setSubject("") - subject = ""
     *     setSubject(" ") - subject = ""
     *     setSubject("bob") - subject = "bob"
     *     setSubject(" bob ") - subject = "bob"
     * </pre>
     *
     * @param subject a new subject to the messageEntity.
     */
    public void setSubject(final String subject) {
        this.subject = isNotEmpty(subject) ? subject : "";
    }

    /**
     * Returns a text of the messageEntity.
     *
     * @return The messageEntity text or empty string (newer null).
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets a new text to the messageEntity.
     * If parameter text is blank, then sets empty string.
     * <pre>
     *     setText(null) - text = ""
     *     setText("") - text = ""
     *     setText(" ") - text = ""
     *     setText("bob") - text = "bob"
     *     setText(" bob ") - text = "bob"
     * </pre>
     *
     * @param text a new text to the messageEntity.
     */
    public void setText(final String text) {
        this.text = isNotEmpty(text) ? text : "";
    }

    /**
     * Returns a date of the messageEntity.
     *
     * @return The messageEntity date (newer null).
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets a new date to the messageEntity.
     * If parameter date is null, then sets new Date().
     * <pre>
     *     setDate(null) - date = new Date()
     *     setDate(someRealDate) - date = someRealDate
     * </pre>
     *
     * @param date the new date to the messageEntity.
     */
    public void setDate(final Date date) {
        this.date = isNotNull(date) ? date : new Date();
    }

    /**
     * Returns an article date in string format.
     *
     * @return The messageEntity string-date (newer null).
     */
    public String getDateToString() {
        return Time.getDate(getDate());
    }

    /**
     * Initializes the messageEntity.
     * Returns this messageEntity with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this messageEntity
     *     initialize(new MessageEntity()) - does nothing, returns this
     *     messageEntity with a new copied fields
     * </pre>
     *
     * @param messageEntity the messageEntity to copy.
     * @return This messageEntity with new fields (newer null).
     */
    public MessageEntity initialize(final MessageEntity messageEntity) {
        if (isNotNull(messageEntity)) {
            super.initialize(messageEntity);
            this.setUserEntity(messageEntity.getUserEntity());
            this.setSubject(messageEntity.getSubject());
            this.setText(messageEntity.getText());
            this.setDate(messageEntity.getDate());
        }
        return this;
    }

    /**
     *
     * @return
     */
    public Message convert() {
        return new MessageEntityConverter(this).convert();
    }
}
