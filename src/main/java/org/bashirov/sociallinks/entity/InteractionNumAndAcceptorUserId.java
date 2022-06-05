package org.bashirov.sociallinks.entity;

public class InteractionNumAndAcceptorUserId {
    private long interactionNum;
    private long acceptorUserId;

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
}
