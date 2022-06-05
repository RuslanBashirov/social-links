package org.bashirov.sociallinks.repository;

import org.bashirov.sociallinks.Util;
import org.bashirov.sociallinks.entity.InteractionNumAndAcceptorUserId;
import org.bashirov.sociallinks.service.UserInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class UserInteractionImpl implements UserInteractionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInteractionImpl.class);

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.password}")
    private String dbPassword;

    @Override
    public Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        con.setAutoCommit(false);
        return con;
    }

    @Override
    public Map<Long, List<InteractionNumAndAcceptorUserId>> getUserInteractionGraph() throws SQLException {
        Connection con = null;
        CallableStatement st = null;
        ResultSet rs = null;
        Map<Long, List<InteractionNumAndAcceptorUserId>> graph = new HashMap<>();
        try {
            con = getConnection();
            st = con.prepareCall("select initiator_user_id, acceptor_user_id, count(*) as interaction_cnt " +
                    "from (select initiator_user_id, acceptor_user_id from user_interaction " +
                    "union all select acceptor_user_id, initiator_user_id from user_interaction) " +
                    "as regular_union_inverted " +
                    "group by initiator_user_id, acceptor_user_id");
            rs = st.executeQuery();

            while (rs.next()) {
                long initiatorUserId = rs.getLong("initiator_user_id");
                long interactionNum = rs.getLong("interaction_cnt");
                long acceptorUserId = rs.getLong("acceptor_user_id");

                graph.putIfAbsent(initiatorUserId, new ArrayList<>());
                graph.get(initiatorUserId)
                        .add(new InteractionNumAndAcceptorUserId(interactionNum, acceptorUserId));
            }

            LOGGER.debug("Got graph of size = {}", graph.size());
        } catch (SQLException sql) {
            LOGGER.error("SQLException in getting user interaction graph \n{}", sql.toString());
        } finally {
            Util.closeRsAndSt(rs, st);
            Util.closeCon(con);
        }

        return graph;
    }

    @Override
    public void createUserInteraction(long initiatorUserId, long acceptorUserId) throws SQLException {
        Connection con = null;
        CallableStatement st = null;
        try {
            con = getConnection();
            st = con.prepareCall("insert into user_interaction" +
                    "(id, initiator_user_id, acceptor_user_id)\n" +
                    "values((select nextval('user_interaction_id_increment_seq')), ?, ?)");
            st.setLong(1, initiatorUserId);
            st.setLong(2, acceptorUserId);
            st.executeUpdate();
            con.commit();

            LOGGER.debug("Create user interaction for initiatorUserId = {} and acceptorUserId = {}", initiatorUserId, acceptorUserId);
        } catch (SQLException sql) {
            LOGGER.error("SQLException in creating user interaction for initiatorUserId = {} and acceptorUserId = {} \n{}",
                    initiatorUserId, acceptorUserId, sql.toString());
        } finally {
            Util.closeSt(st);
            Util.closeCon(con);
        }
    }
}
