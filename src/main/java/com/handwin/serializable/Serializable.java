package com.handwin.serializable;

/**
 * 
 * @author fangliang
 *
 * @param <T>
 */
public interface Serializable<T> {
	
	public byte[] ser( T t ); 
	
	public T der( byte[] bytes, Class<T> tClass );
	
}
