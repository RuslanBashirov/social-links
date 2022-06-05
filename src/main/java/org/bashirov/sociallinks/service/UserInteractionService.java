package org.bashirov.sociallinks.service;

import org.bashirov.sociallinks.entity.InteractionNumAndAcceptorUserId;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserInteractionService {

    Connection getConnection() throws SQLException;
    Map<Long, List<InteractionNumAndAcceptorUserId>> getUserInteractionGraph() throws SQLException;
    void createUserInteraction(long initiatorUserId, long acceptorUserId) throws SQLException;
}
