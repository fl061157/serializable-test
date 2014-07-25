package com.handwin.serializable.module;

public class SimpleMessagePacket extends AbstractBasePacket {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 4884968692610904375L;

	public final static byte SIMPLE_MESSAGE_PACKET_TYPE = 0x05;

    public final static byte TEXT_MESSAGE_TYPE = 0x01;
    public final static byte FORWARD_MESSAGE_TYPE = 0x02;
    public final static byte IMAGE_URL_MESSAGE_TYPE = 0x03;
    public final static byte VOICE_MESSAGE_TYPE = 0x05;
    public final static byte VIDEO_MESSAGE_TYPE = 0X06;

    public final static byte STATUS_MESSAGE_TYPE = 0x10;
    public final static byte STATUS_RESPONSE_MESSAGE_TYPE = 0x11;

    public final static byte TO_USER = 0x01;
    public final static byte TO_GROUP = 0x02;

    private byte messageType;
    private byte messageServiceType;
    private String toUser;
    private String toGroup;
    private String from;
    private String fromGroup;
//    private String content;
    private byte[] content;

    private Long messageId;
    private int tempId;

    /**
     * 客户端是否重发的消息 0x01表示未重发的消息
     */
    private byte msgFlag = 0x00;

    /**
     * 客户端发送消息请求的消息id（客户端生成的消息id）
     */
    private String cmsgid;

    public byte getMessageType() {
        return messageType;
    }

    public void setMessageType(byte messageType) {
        this.messageType = messageType;
    }

    public byte getMessageServiceType() {
        return messageServiceType;
    }

    public void setMessageServiceType(byte messageServiceType) {
        this.messageServiceType = messageServiceType;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromGroup() {
        return fromGroup;
    }

    public void setFromGroup(String fromGroup) {
        this.fromGroup = fromGroup;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public int getTempId() {
        return tempId;
    }

    public void setTempId(int tempId) {
        this.tempId = tempId;
    }

    public String getToGroup() {
        return toGroup;
    }

    public void setToGroup(String toGroup) {
        this.toGroup = toGroup;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getCmsgid() {
        return cmsgid;
    }

    public void setCmsgid(String cmsgid) {
        this.cmsgid = cmsgid;
    }

    public byte getMsgFlag() {
        return msgFlag;
    }

    public void setMsgFlag(byte msgFlag) {
        this.msgFlag = msgFlag;
    }

}
