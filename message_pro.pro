option java_package = "com.handwin.serializable";
option java_outer_classname = "TextMessagePacketPro";

message TextMessagePacket {
  
  optional string cmsgID =  1;
  optional bytes content = 2; 
  optional string from = 3;
  optional int32 messageId = 4;
  optional int32 messageServiceType = 5;
  optional int32 messageType = 6;
  optional int32 messageFlag = 7;
  optional int32 appID =8;
  optional int32 vHead = 9;
  optional int32 packetSize = 10; 
  optional int32 secrect = 11;
  optional int32  tempID = 12;
  optional int32  timeStamp = 13;  
  optional int32 version = 14;  
  optional bool zip = 15;  
  optional string toUser = 16;
  optional string traceID = 17;
}
