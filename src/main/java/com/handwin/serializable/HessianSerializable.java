package com.handwin.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

/**
 * 
 * @author fangliang
 *
 * @param <T>
 */
public class HessianSerializable<T> implements Serializable<T> {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public T der(byte[] bytes, Class<T> tClass) {
		T result = null ;
		ByteArrayInputStream  byteArrayInputStream = new ByteArrayInputStream(bytes);
		try {
			Hessian2Input in = new Hessian2Input(byteArrayInputStream);
			result = (T)in.readObject(tClass) ;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if( byteArrayInputStream != null ) {
				try {
					byteArrayInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
        return result ;
	}
	
	@Override
	public byte[] ser(T t) {
		byte[] objArray  = null ;
		ByteArrayOutputStream  byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			Hessian2Output out = new Hessian2Output(byteArrayOutputStream);
			out.writeObject( t );
			out.flush();
			out.close();
			objArray = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace(); 
		} finally {
			if( byteArrayOutputStream !=  null ) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return objArray ;
	}
	
}
