package org.bashirov.sociallinks.entity;

import org.bashirov.sociallinks.enums.InteractionLevel;

public class InteractionNumAndAcceptorUserId {

    public static long INTERACTION_MIN_LEVEL_NUM;
    public static long INTERACTION_MAX_LEVEL_NUM;

    private long interactionNum;
    private long acceptorUserId;
    private InteractionLevel interactionLevel;

    public InteractionNumAndAcceptorUserId(long interactionNum, long acceptorUserId) {
        this.interactionNum = interactionNum;
        this.acceptorUserId = acceptorUserId;
    }

    public long getInteractionNum() {
        return interactionNum;
    }

    public void setInteractionNum(long interactionNum) {
        this.interactionNum = interactionNum;
    }

    public long getAcceptorUserId() {
        return acceptorUserId;
    }

    public void setAcceptorUserId(long acceptorUserId) {
        this.acceptorUserId = acceptorUserId;
    }

    public InteractionLevel getInteractionLevel() {
        return interactionLevel;
    }

    public void setInteractionLevel() {
        if (interactionNum < INTERACTION_MIN_LEVEL_NUM) {
            this.interactionLevel = InteractionLevel.MINIMUM;
        }
        else if (interactionNum < INTERACTION_MAX_LEVEL_NUM) {
            this.interactionLevel = InteractionLevel.AVERAGE;
        }
        else {
            this.interactionLevel = InteractionLevel.MAXIMUM;
        }
    }
}
