// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: app_helper_pb.proto
// Protobuf Java Version: 4.28.2

package com.google.android.horologist.data;

public interface ComplicationInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.google.android.horologist.data.ComplicationInfo)
    com.google.protobuf.MessageLiteOrBuilder {

  /**
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int32 instanceId = 2;</code>
   * @return The instanceId.
   */
  int getInstanceId();

  /**
   * <code>string type = 3;</code>
   * @return The type.
   */
  java.lang.String getType();
  /**
   * <code>string type = 3;</code>
   * @return The bytes for type.
   */
  com.google.protobuf.ByteString
      getTypeBytes();

  /**
   * <code>.google.protobuf.Timestamp timestamp = 5;</code>
   * @return Whether the timestamp field is set.
   */
  boolean hasTimestamp();
  /**
   * <code>.google.protobuf.Timestamp timestamp = 5;</code>
   * @return The timestamp.
   */
  com.google.protobuf.Timestamp getTimestamp();
}
