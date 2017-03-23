package com.salimov.ecoteh.dao.interfaces;

import com.salimov.ecoteh.entity.Message;
import com.salimov.ecoteh.util.cache.Cache;

/**
 * The class implements a set of standard methods for working models objects
 * of the {@link Message} class with the {@link Cache}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface MessageDao extends DataDao<Message> {
}
