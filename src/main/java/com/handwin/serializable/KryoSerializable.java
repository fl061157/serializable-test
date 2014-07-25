package com.handwin.serializable;

import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * 
 * @author fangliang
 *
 * @param <T>
 */
public class KryoSerializable<T> implements Serializable<T> {
	
	public T der(byte[] bytes, Class<T> tClass) {
		Kryo kryo = new Kryo();
		Input inPut = new Input( bytes ) ;
		try {
			return kryo.readObject(inPut, tClass) ;
		} finally {
			inPut.close() ;
		}
	}
	
	public byte[] ser(T t) { 
		Kryo kryo = new Kryo();
		ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
		Output outPut = new Output( stream );
		try {
			kryo.writeObject(outPut, t) ;
		} finally {
			outPut.close() ;
		}
		return stream.toByteArray() ; 
	}
	

}
