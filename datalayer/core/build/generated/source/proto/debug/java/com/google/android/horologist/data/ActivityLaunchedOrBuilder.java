// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: app_helper_pb.proto
// Protobuf Java Version: 4.28.2

package com.google.android.horologist.data;

public interface ActivityLaunchedOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.google.android.horologist.data.ActivityLaunched)
    com.google.protobuf.MessageLiteOrBuilder {

  /**
   * <code>bool activityLaunchedOnce = 1;</code>
   * @return The activityLaunchedOnce.
   */
  boolean getActivityLaunchedOnce();

  /**
   * <code>.google.protobuf.Timestamp timestamp = 2;</code>
   * @return Whether the timestamp field is set.
   */
  boolean hasTimestamp();
  /**
   * <code>.google.protobuf.Timestamp timestamp = 2;</code>
   * @return The timestamp.
   */
  com.google.protobuf.Timestamp getTimestamp();
}
