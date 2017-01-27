package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.MessageDao;
import com.salimov.yurii.entity.Message;
import com.salimov.yurii.entity.Model;
import com.salimov.yurii.util.cache.Cache;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * The interface provides a set of standard methods
 * for working {@link Message} objects with the {@link Cache}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see MessageDao
 * @see Message
 */
@Repository
public final class MessageDaoImpl implements MessageDao {
    /**
     * The key of objects in the cache.
     */
    private final static String KEY = "Message";

    /**
     * The default lifetime of objects (hours).
     */
    private final static int HOURS = 24;

    /**
     * The default lifetime of objects (minutes).
     */
    private final static int MINUTES = 0;

    /**
     * The default lifetime of objects (seconds).
     */
    private final static int SECONDS = 0;

    /**
     * The default lifetime of objects (milliseconds).
     */
    private final static int MILLISECONDS = 0;

    /**
     * Saves in the {@link Cache} and returns message.
     *
     * @param message a message to add.
     * @return The saving message.
     * @see Message
     */
    @Override
    public Message add(final Message message) {
        return (Message) Cache.put(
                getKey(
                        message.getId()
                ),
                message,
                HOURS,
                MINUTES,
                SECONDS,
                MILLISECONDS
        );
    }

    /**
     * Saves in the {@link Cache} and returns messages.
     *
     * @param messages a messages to add.
     * @return The saving messages.
     * @see Message
     */
    @Override
    public Collection<Message> addAll(final Collection<Message> messages) {
        return messages.stream()
                .map(this::add)
                .collect(
                        Collectors.toCollection(ArrayList::new)
                );
    }

    /**
     * Updates in the {@link Cache} and returns message.
     *
     * @param message a message to update.
     * @return The updating message.
     * @see Message
     */
    @Override
    public Message update(final Message message) {
        return add(message);
    }

    /**
     * Returns model object of the message with parameter id
     * from the {@link Cache}.
     *
     * @param id a id of the message to return.
     * @return The message with parameter id.
     * @see Message
     */
    @Override
    public Message get(final Long id) {
        return (Message) Cache.get(
                getKey(id)
        );
    }

    /**
     * Returns all messages from the {@link Cache}.
     *
     * @return The all messages.
     * @see Message
     */
    @Override
    public Collection<Message> getAll() {
        return Cache.getAll(KEY)
                .stream()
                .filter(
                        object -> object instanceof Message
                )
                .map(
                        object -> (Message) object
                )
                .collect(
                        Collectors.toList()
                );
    }

    /**
     * Removes message with parameter id from the {@link Cache}.
     *
     * @param id a id model object to remove.
     * @see Message
     */
    @Override
    public void remove(final Long id) {
        Cache.remove(
                getKey(id)
        );
    }

    /**
     * Removes message from the {@link Cache}.
     *
     * @param message The message to remove.
     * @see Message
     */
    @Override
    public void remove(final Message message) {
        remove(
                message.getId()
        );
    }

    /**
     * Removes messages from the {@link Cache}.
     *
     * @param messages The model objects to remove.
     * @see Message
     */
    @Override
    public void remove(final Collection<Message> messages) {
        if (messages != null && !messages.isEmpty()) {
            messages.forEach(this::remove);
        }
    }

    /**
     * Removes all messages from the {@link Cache}.
     *
     * @see Message
     */
    @Override
    public void removeAll() {
        Cache.removeAll(KEY);
    }

    /**
     * Checks whether message with parameter id is exists in the database.
     *
     * @param id a id of message to exists.
     * @return {@code true} if message is exists, {@code false} otherwise.
     * @see Model
     */
    @Override
    public boolean exists(final Long id) {
        return Cache.exist(
                getKey(id)
        );
    }

    /**
     * Returns message key.
     *
     * @param id id a id of message to exists.
     * @return The message key.
     */
    private static String getKey(final Long id) {
        return KEY + " " + id;
    }
}
