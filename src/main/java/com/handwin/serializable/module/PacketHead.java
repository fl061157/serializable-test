package com.handwin.serializable.module;

import java.io.Serializable;

public class PacketHead implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6472617997444617670L;
	private byte head;
    private byte version;
    private int packetType;
    private boolean zip;
    private byte secret;
    private long timestamp;
    private int tempId;
    private int packetSize;
    private short appId;

    public byte getHead() {
        return head;
    }

    public void setHead(byte head) {
        this.head = head;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public int getPacketType() {
        return packetType;
    }

    public void setPacketType(int packetType) {
        this.packetType = packetType;
    }

    public byte getSecret() {
        return secret;
    }

    public void setSecret(byte secret) {
        this.secret = secret;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getTempId() {
        return tempId;
    }

    public void setTempId(int tempId) {
        this.tempId = tempId;
    }

    public int getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    public short getAppId() {
        return appId;
    }

    public void setAppId(short appId) {
        this.appId = appId;
    }

    public boolean isZip() {
        return zip;
    }

    public void setZip(boolean zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PacketHead{");
        sb.append("head=").append(head);
        sb.append(", version=").append(version);
        sb.append(", packetType=").append(packetType);
        sb.append(", secret=").append(secret);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", tempId=").append(tempId);
        sb.append(", packetSize=").append(packetSize);
        sb.append(", appId=").append(appId);
        sb.append('}');
        return sb.toString();
    }


    public static PacketHead clone(PacketHead src){
        PacketHead target = null;
        if(null != src){
            target  = new PacketHead();
            target.setHead(src.getHead());
            target.setVersion(src.getVersion());
            target.setPacketType(src.getPacketType());
            target.setSecret(src.getSecret());
            target.setTimestamp(src.getTimestamp());
            target.setTempId(src.getTempId());
            target.setPacketSize(src.getPacketSize());
            target.setAppId(src.getAppId());
        }

        return target;
    }
	

}
