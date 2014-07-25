package com.handwin.serializable;

import java.nio.ByteBuffer;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.protobuf.ByteString;
import com.handwin.serializable.module.PacketHead;
import com.handwin.serializable.module.TextMessagePacket;
import com.handwin.serializable.module.TextMessagePacketAvro;
import com.handwin.serializable.module.TextMessagePacketPro;


/**
 * 
 * @author fangliang
 *
 */
public class SerializableTest {
	
	
	protected final static String CMSG_ID = "ABC_10001" ;
	protected final static TextMessagePacket TEXT  = new TextMessagePacket() ;
	protected final static byte[] CONTENT = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes();
	protected final static String FROM = "123456789";
	protected final static long MESSAGE_ID = 10000l ;
	protected final static byte MESSAGE_SERVICE_TYPE = 0x01 ;
	protected final static byte MESSAGE_TYPE = 0x10 ;
	protected final static byte MESSAGE_FLAG = 0x02 ;
	
	
	protected final static short APP_ID = 100 ;
	protected final static byte V_HEAD = 0x00 ;
	protected final static int PACKET_SIZE = 100000 ;
	protected final static int PACKET_TYPE = 2;
	protected final static byte SECRECT = 0x01; 
	protected final static int TMP_ID = 101 ;
	protected final static int TIME_STAMP = (int)System.currentTimeMillis() ;
	protected final static byte VESION = 0x01 ;
	protected final static boolean ZIP = true  ;
	
	protected final static String TO_USER = "987654321";
	protected final static int TRACE_ID = 99999;
	
	
	protected final static PacketHead HEAD = new PacketHead() ;
	static {
		HEAD.setAppId(APP_ID);
		HEAD.setHead(V_HEAD);
		HEAD.setPacketSize(PACKET_SIZE);
		HEAD.setPacketType(PACKET_TYPE);
		HEAD.setSecret(SECRECT);
		//HEAD.setTempId(TMP_ID);
		HEAD.setTimestamp(TIME_STAMP);
		HEAD.setVersion(VESION);
		HEAD.setZip(ZIP);
	}
	
	
	
	static {
		TEXT.setCmsgid(CMSG_ID);
		TEXT.setContent(CONTENT); 
		TEXT.setFrom(FROM);
		TEXT.setFromGroup(null); 
		TEXT.setMessageId( MESSAGE_ID );
		TEXT.setMessageServiceType( MESSAGE_SERVICE_TYPE );
		TEXT.setMessageType(MESSAGE_TYPE);
		TEXT.setMsgFlag(MESSAGE_FLAG);
		TEXT.setPacketHead(HEAD);
		TEXT.setPacketType(PACKET_TYPE);
		TEXT.setTempId(TMP_ID); 
		TEXT.setToGroup(null); 
		TEXT.setToUser(TO_USER);
		TEXT.setTraceId(TRACE_ID);
	}
	
	
	
	protected final static TextMessagePacketAvro textMessagePacketAvro = new TextMessagePacketAvro() ;
	static {
		textMessagePacketAvro.setAppID((int)TEXT.getPacketHead().getAppId());  
		textMessagePacketAvro.setCmsgID(TEXT.getCmsgid()); 
		textMessagePacketAvro.setContent(ByteBuffer.wrap(TEXT.getContent()));  
		textMessagePacketAvro.setFrom(ByteBuffer.wrap(TEXT.getFrom().getBytes())); 
		textMessagePacketAvro.setMessageFlag((int)TEXT.getMsgFlag()); 
		textMessagePacketAvro.setMessageId( TEXT.getMessageId() ); 
		textMessagePacketAvro.setMessageServiceType( (int) TEXT.getMessageServiceType() );
		textMessagePacketAvro.setMessageType( (int) TEXT.getMessageType() ); 
		textMessagePacketAvro.setPacketSize((int) TEXT.getPacketHead().getPacketSize() ); 
		textMessagePacketAvro.setSecrect( (int)TEXT.getPacketHead().getSecret() );
		textMessagePacketAvro.setTempID( (int) TEXT.getTempId()  ); 
		textMessagePacketAvro.setTimeStamp(TEXT.getPacketHead().getTimestamp());
		textMessagePacketAvro.setToUser(TEXT.getToUser()); 
		textMessagePacketAvro.setTraceID((int)TEXT.getTraceId()); 
		textMessagePacketAvro.setVersion((long)TEXT.getPacketHead().getVersion()); 
		textMessagePacketAvro.setVHead( (int)TEXT.getPacketHead().getHead() );
		textMessagePacketAvro.setZip(TEXT.getPacketHead().isZip() );
	}
	
	
	protected static TextMessagePacketPro.TextMessagePacket textMessagePacketPro = null ;
	static {
		TextMessagePacketPro.TextMessagePacket.Builder builder = TextMessagePacketPro.TextMessagePacket.newBuilder() ;
		builder.setAppID((int)TEXT.getPacketHead().getAppId());  
		builder.setCmsgID(TEXT.getCmsgid()); 
		builder.setContent(ByteString.copyFrom(TEXT.getContent())) ;
		builder.setFrom(TEXT.getFrom()); 
		builder.setMessageFlag((int)TEXT.getMsgFlag()); 
		builder.setMessageId( (int)((long)TEXT.getMessageId()) ); 
		builder.setMessageServiceType( (int) TEXT.getMessageServiceType() );
		builder.setMessageType( (int) TEXT.getMessageType() ); 
		builder.setPacketSize((int) TEXT.getPacketHead().getPacketSize() ); 
		builder.setSecrect( (int)TEXT.getPacketHead().getSecret() );
		builder.setTempID( (int) TEXT.getTempId()  ); 
		builder.setTimeStamp( (int)TEXT.getPacketHead().getTimestamp());
		builder.setToUser(TEXT.getToUser()); 
		builder.setTraceID(String.valueOf( TEXT.getTraceId() ));  
		builder.setVersion((int)TEXT.getPacketHead().getVersion()); 
		builder.setVHead( (int)TEXT.getPacketHead().getHead() );
		builder.setZip(TEXT.getPacketHead().isZip() );
		textMessagePacketPro = builder.build() ;
	}
	
	
	
	public static final int THREAD_SIZE = 5; 
	public static final CyclicBarrier BARRIER = new CyclicBarrier(THREAD_SIZE ,  new Runnable() {
		@Override
		public void run() {
			System.out.println( "Begin ====> " ); 
		}
	} ) ;

	public static final CountDownLatch LATCH = new CountDownLatch( THREAD_SIZE ) ;
	
	
	protected enum What {
		
		Hessian {
			final Serializable<TextMessagePacket> ss = new HessianSerializable<TextMessagePacket>() ;
			
			@Override
			protected void work() {
				byte[] bytes  = ss.ser( TEXT ) ;
				TextMessagePacket packet = ss.der(bytes, TextMessagePacket.class) ;
			}
		}, 
		Json {
			final Serializable<TextMessagePacket> ss = new JsonSerializable<TextMessagePacket>() ;
			
			@Override
			protected void work() {
				byte[] bytes  = ss.ser( TEXT ) ;
				TextMessagePacket packet = ss.der(bytes, TextMessagePacket.class) ;
			}
		},
		Kyro {
			final  Serializable<TextMessagePacket> ss = new KryoSerializable<TextMessagePacket>() ;
			@Override
			protected void work() {
				byte[] bytes  = ss.ser( TEXT ) ;
				TextMessagePacket packet = ss.der(bytes, TextMessagePacket.class) ;
				//System.out.println( packet.getFrom() + " " + packet.getToUser() + "  " + packet.getPacketHead().getAppId() ) ;
			}
		},
		Avro {
			final Serializable<TextMessagePacketAvro> ss = new AvroSerializable<TextMessagePacketAvro>("/Users/fangliang/Documents/workspace/serializable-test/textmessage.avsc", TextMessagePacketAvro.class ) ;
			@Override
			protected void work() {
				byte[] bytes  = ss.ser( textMessagePacketAvro ) ;
				TextMessagePacketAvro packet = ss.der(bytes, TextMessagePacketAvro.class) ;
			}
		},
		Protobuf{
			
			final Serializable<TextMessagePacketPro.TextMessagePacket> ss = new PortoSerializable<TextMessagePacketPro.TextMessagePacket>(TextMessagePacketPro.TextMessagePacket.getDefaultInstance() ) ; 
			
			@Override
			protected void work() {
				byte[] bytes  = ss.ser( textMessagePacketPro ) ;
				TextMessagePacketPro.TextMessagePacket packet = ss.der(bytes, TextMessagePacketPro.TextMessagePacket.class) ;
			}
		} ;
		
		protected abstract void work() ;
		
	}
	
	public static class Job implements Runnable {
		private final What what ;
		private final int count ;
		
		public Job ( What what , int count) {
			this.what = what ;
			this.count = count ;
		}
		
		@Override
		public void run() {
			
			try {
				BARRIER.await() ;
			} catch (InterruptedException e) {
			} catch (BrokenBarrierException e) {
			}
			
			long s = System.currentTimeMillis() ;
			for( int i = 0 ; i < count ; i++ ) {
				what.work() ; 
			}
			System.out.println( what.name() + " Ser Dcer Time : " + ( System.currentTimeMillis() - s ) + "毫秒" ); 
			
			LATCH.countDown() ; 
		}
	}
	
	
	
	
	
	
	private static ExecutorService exec = Executors.newFixedThreadPool(THREAD_SIZE) ;
 	
	public static void main( String[] args ) {
		int count = 100000 ;
		System.out.println("各自序列化 反序列化 " + count + "次 统计各自总时间 ");
		exec.execute(new Job(What.Avro, count));
		exec.execute(new Job(What.Hessian, count));
		exec.execute(new Job(What.Json, count));
		exec.execute(new Job(What.Kyro, count));
		exec.execute(new Job(What.Protobuf, count));
		try {
			LATCH.await();
		} catch (InterruptedException e) {
		} 
		exec.shutdown() ;
		System.out.println("统计结束 =====> "); 
	}
	
	
	

}
