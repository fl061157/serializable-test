package com.handwin.serializable.module;


public class AbstractBasePacket implements BasePacket {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = -2166992299605573154L;
	protected int packetType;
    protected PacketHead packetHead;
    protected long traceId;

    public AbstractBasePacket(){
        this.traceId = System.nanoTime();
    }

    @Override
    public int getPacketType() {
        return packetType;
    }

    @Override
    public void setPacketType(int packetType) {
        this.packetType = packetType;
    }

    @Override
    public PacketHead getPacketHead() {
        return packetHead;
    }

    @Override
    public void setPacketHead(PacketHead packetHead) {
        this.packetHead = packetHead;
    }

    @Override
    public void setTraceId(long traceId){
       this.traceId = traceId;
    }

    public long getTraceId() {
        return traceId;
    }

}
