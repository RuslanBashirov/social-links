package org.bashirov.sociallinks.controller;

import org.bashirov.sociallinks.entity.InteractionNumAndAcceptorUserId;
import org.bashirov.sociallinks.service.UserInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userInteraction")
public class UserInteractionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInteractionController.class);

    @Autowired
    private UserInteractionService dao;

    @GetMapping("/getUserInteractionGraph")
    public Map<Long, List<InteractionNumAndAcceptorUserId>> getUserInteractionGraph() {
        Map<Long, List<InteractionNumAndAcceptorUserId>> graph = null;
        try {
            graph = dao.getUserInteractionGraph();
        } catch (SQLException sql) {
            LOGGER.error("SQLException while closing connection \n{}", sql.toString());
        }
        return graph;
    }

    @PostMapping("/createUserInteraction")
    public void createUserInteraction(@RequestParam long initiatorUserId, @RequestParam long acceptorUserId) {
        try {
            dao.createUserInteraction(initiatorUserId, acceptorUserId);
        } catch (SQLException sql) {
            LOGGER.error("SQLException while closing connection \n{}", sql.toString());
        }
    }
}
