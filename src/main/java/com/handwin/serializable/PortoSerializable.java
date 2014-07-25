package com.handwin.serializable;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

/**
 * 
 * @author fangliang
 *
 * @param <T>
 */
public class PortoSerializable<T extends MessageLite> implements Serializable<T> {
	
	
	private MessageLite messageLite ;
	
	public PortoSerializable( MessageLite messageLite ) {
		this.messageLite = messageLite ;
	}
	

	@Override
	public byte[] ser(T t) {
		return t.toByteArray() ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T der(byte[] bytes, Class<T> tClass) {
		
		T result = null ;
		try {
			result = (T)messageLite.getParserForType().parseFrom( bytes ) ;
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		return result ;
	}

}
