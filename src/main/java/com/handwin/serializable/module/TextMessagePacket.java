package com.handwin.serializable.module;

public class TextMessagePacket extends SimpleMessagePacket {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6921857642538245806L;
	public final static int TEXT_MESSAGE_PACKET_TYPE = TEXT_MESSAGE_TYPE*256 + SIMPLE_MESSAGE_PACKET_TYPE;

    public TextMessagePacket(){
        this.setPacketType(TEXT_MESSAGE_PACKET_TYPE);
    }

}
