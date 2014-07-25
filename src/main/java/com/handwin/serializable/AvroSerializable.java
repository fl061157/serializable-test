package com.handwin.serializable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;

/**
 * JUST TEST
 * @author fangliang
 *
 * @param <T>
 */
public class AvroSerializable<T extends GenericRecord > implements Serializable<T> {
	
	private Schema schema ;
	private SpecificDatumReader<T> reader ; 
	private GenericDatumWriter<GenericRecord> datumWriter ;
	
	
	public AvroSerializable( String schemaFullPath , Class<T> tClass ) {
		try {
			schema = new Parser().parse(new File(schemaFullPath)) ;
			reader =  new SpecificDatumReader<T>( tClass ) ;
			datumWriter = new GenericDatumWriter<GenericRecord>(schema);
		} catch (IOException e) {
			throw new RuntimeException( e ) ;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public T der(byte[] bytes, Class<T> tClass) {
		BinaryDecoder decoder = null;
		decoder =  DecoderFactory.get().binaryDecoder(bytes, decoder ) ;
		T old = null ;
		try {
			old = tClass.newInstance() ;
		} catch (InstantiationException e) {
			e.printStackTrace() ;
			return null ;
		} catch (IllegalAccessException e) {
			return null ;
		}
		try {
			Object o = reader.read( old, decoder ) ;
			return (T) o;
		} catch (IOException e) {
			e.printStackTrace() ;
			return null ;
		}
	}
	
	public byte[] ser(T t) {
		BinaryEncoder encoder = null ;
		ByteArrayOutputStream out = new ByteArrayOutputStream() ;
		encoder = EncoderFactory.get().binaryEncoder(out, encoder ) ; 
		try {
			datumWriter.write(t, encoder ) ;
			encoder.flush() ;
			return out.toByteArray()  ;
		} catch (IOException e) {
			e.printStackTrace();
			return null; 
		} finally {
			try {
				out.close() ;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
