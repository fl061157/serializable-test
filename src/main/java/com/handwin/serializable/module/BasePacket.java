package com.handwin.serializable.module;

import java.io.Serializable;

public interface BasePacket extends Serializable {
	
	byte DIRECT_TRANS_PACKET_TYPE = -99;

    int getPacketType();
    void setPacketType(int type);
    PacketHead getPacketHead();
    void setPacketHead(PacketHead head);
    /**
     * 设置跟踪id，用于跟踪包的处理流程，方便程序的日志分析
     * @param traceId
     */
    void setTraceId(long traceId);

}
