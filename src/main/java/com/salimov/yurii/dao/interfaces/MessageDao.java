package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.Message;
import com.salimov.yurii.util.cache.Cache;

/**
 * The class implements a set of standard methods for working models objects
 * of the {@link Message} class with the {@link Cache}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface MessageDao extends DataDao<Message> {
}
