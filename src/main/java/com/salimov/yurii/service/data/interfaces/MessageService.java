package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.Message;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the class {@link Message}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Message
 * @see DataService
 */
public interface MessageService
        extends DataService<Message, Long> {
}
