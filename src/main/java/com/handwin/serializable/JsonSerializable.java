package com.handwin.serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

/**
 * 
 * @author fangliang
 *
 * @param <T>
 */
public class JsonSerializable<T> implements Serializable<T> {

	@Override
	public byte[] ser(T t) {
		return JSON.toJSONBytes(t) ;
	}

	@Override
	public T der(byte[] bytes, Class<T> tClass) {
		return JSON.parseObject(bytes, tClass , Feature.AutoCloseSource ) ;
	}

}
