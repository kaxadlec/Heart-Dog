// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: grpc_demo.proto
// Protobuf Java Version: 4.28.2

package com.google.android.horologist.datalayer.sample.shared.grpc;

public final class GrpcDemoProto {
  private GrpcDemoProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  public interface CounterValueOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.google.android.horologist.datalayer.sample.shared.grpc.CounterValue)
      com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>int64 value = 1;</code>
     * @return The value.
     */
    long getValue();

    /**
     * <code>.google.protobuf.Timestamp updated = 2;</code>
     * @return Whether the updated field is set.
     */
    boolean hasUpdated();
    /**
     * <code>.google.protobuf.Timestamp updated = 2;</code>
     * @return The updated.
     */
    com.google.protobuf.Timestamp getUpdated();
  }
  /**
   * Protobuf type {@code com.google.android.horologist.datalayer.sample.shared.grpc.CounterValue}
   */
  public  static final class CounterValue extends
      com.google.protobuf.GeneratedMessageLite<
          CounterValue, CounterValue.Builder> implements
      // @@protoc_insertion_point(message_implements:com.google.android.horologist.datalayer.sample.shared.grpc.CounterValue)
      CounterValueOrBuilder {
    private CounterValue() {
    }
    private int bitField0_;
    public static final int VALUE_FIELD_NUMBER = 1;
    private long value_;
    /**
     * <code>int64 value = 1;</code>
     * @return The value.
     */
    @java.lang.Override
    public long getValue() {
      return value_;
    }
    /**
     * <code>int64 value = 1;</code>
     * @param value The value to set.
     */
    private void setValue(long value) {
      
      value_ = value;
    }
    /**
     * <code>int64 value = 1;</code>
     */
    private void clearValue() {

      value_ = 0L;
    }

    public static final int UPDATED_FIELD_NUMBER = 2;
    private com.google.protobuf.Timestamp updated_;
    /**
     * <code>.google.protobuf.Timestamp updated = 2;</code>
     */
    @java.lang.Override
    public boolean hasUpdated() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.google.protobuf.Timestamp updated = 2;</code>
     */
    @java.lang.Override
    public com.google.protobuf.Timestamp getUpdated() {
      return updated_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : updated_;
    }
    /**
     * <code>.google.protobuf.Timestamp updated = 2;</code>
     */
    private void setUpdated(com.google.protobuf.Timestamp value) {
      value.getClass();
  updated_ = value;
      bitField0_ |= 0x00000001;
      }
    /**
     * <code>.google.protobuf.Timestamp updated = 2;</code>
     */
    @java.lang.SuppressWarnings({"ReferenceEquality"})
    private void mergeUpdated(com.google.protobuf.Timestamp value) {
      value.getClass();
  if (updated_ != null &&
          updated_ != com.google.protobuf.Timestamp.getDefaultInstance()) {
        updated_ =
          com.google.protobuf.Timestamp.newBuilder(updated_).mergeFrom(value).buildPartial();
      } else {
        updated_ = value;
      }
      bitField0_ |= 0x00000001;
    }
    /**
     * <code>.google.protobuf.Timestamp updated = 2;</code>
     */
    private void clearUpdated() {  updated_ = null;
      bitField0_ = (bitField0_ & ~0x00000001);
    }

    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
      return (Builder) DEFAULT_INSTANCE.createBuilder();
    }
    public static Builder newBuilder(com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue prototype) {
      return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code com.google.android.horologist.datalayer.sample.shared.grpc.CounterValue}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue, Builder> implements
        // @@protoc_insertion_point(builder_implements:com.google.android.horologist.datalayer.sample.shared.grpc.CounterValue)
        com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValueOrBuilder {
      // Construct using com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue.newBuilder()
      private Builder() {
        super(DEFAULT_INSTANCE);
      }


      /**
       * <code>int64 value = 1;</code>
       * @return The value.
       */
      @java.lang.Override
      public long getValue() {
        return instance.getValue();
      }
      /**
       * <code>int64 value = 1;</code>
       * @param value The value to set.
       * @return This builder for chaining.
       */
      public Builder setValue(long value) {
        copyOnWrite();
        instance.setValue(value);
        return this;
      }
      /**
       * <code>int64 value = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearValue() {
        copyOnWrite();
        instance.clearValue();
        return this;
      }

      /**
       * <code>.google.protobuf.Timestamp updated = 2;</code>
       */
      @java.lang.Override
      public boolean hasUpdated() {
        return instance.hasUpdated();
      }
      /**
       * <code>.google.protobuf.Timestamp updated = 2;</code>
       */
      @java.lang.Override
      public com.google.protobuf.Timestamp getUpdated() {
        return instance.getUpdated();
      }
      /**
       * <code>.google.protobuf.Timestamp updated = 2;</code>
       */
      public Builder setUpdated(com.google.protobuf.Timestamp value) {
        copyOnWrite();
        instance.setUpdated(value);
        return this;
        }
      /**
       * <code>.google.protobuf.Timestamp updated = 2;</code>
       */
      public Builder setUpdated(
          com.google.protobuf.Timestamp.Builder builderForValue) {
        copyOnWrite();
        instance.setUpdated(builderForValue.build());
        return this;
      }
      /**
       * <code>.google.protobuf.Timestamp updated = 2;</code>
       */
      public Builder mergeUpdated(com.google.protobuf.Timestamp value) {
        copyOnWrite();
        instance.mergeUpdated(value);
        return this;
      }
      /**
       * <code>.google.protobuf.Timestamp updated = 2;</code>
       */
      public Builder clearUpdated() {  copyOnWrite();
        instance.clearUpdated();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.google.android.horologist.datalayer.sample.shared.grpc.CounterValue)
    }
    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
        com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
        java.lang.Object arg0, java.lang.Object arg1) {
      switch (method) {
        case NEW_MUTABLE_INSTANCE: {
          return new com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue();
        }
        case NEW_BUILDER: {
          return new Builder();
        }
        case BUILD_MESSAGE_INFO: {
            java.lang.Object[] objects = new java.lang.Object[] {
              "bitField0_",
              "value_",
              "updated_",
            };
            java.lang.String info =
                "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u1009" +
                "\u0000";
            return newMessageInfo(DEFAULT_INSTANCE, info, objects);
        }
        // fall through
        case GET_DEFAULT_INSTANCE: {
          return DEFAULT_INSTANCE;
        }
        case GET_PARSER: {
          com.google.protobuf.Parser<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> parser = PARSER;
          if (parser == null) {
            synchronized (com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue.class) {
              parser = PARSER;
              if (parser == null) {
                parser =
                    new DefaultInstanceBasedParser<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue>(
                        DEFAULT_INSTANCE);
                PARSER = parser;
              }
            }
          }
          return parser;
      }
      case GET_MEMOIZED_IS_INITIALIZED: {
        return (byte) 1;
      }
      case SET_MEMOIZED_IS_INITIALIZED: {
        return null;
      }
      }
      throw new UnsupportedOperationException();
    }


    // @@protoc_insertion_point(class_scope:com.google.android.horologist.datalayer.sample.shared.grpc.CounterValue)
    private static final com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue DEFAULT_INSTANCE;
    static {
      CounterValue defaultInstance = new CounterValue();
      // New instances are implicitly immutable so no need to make
      // immutable.
      DEFAULT_INSTANCE = defaultInstance;
      com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
        CounterValue.class, defaultInstance);
    }

    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<CounterValue> PARSER;

    public static com.google.protobuf.Parser<CounterValue> parser() {
      return DEFAULT_INSTANCE.getParserForType();
    }
  }

  public interface CounterDeltaOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.google.android.horologist.datalayer.sample.shared.grpc.CounterDelta)
      com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>int64 delta = 1;</code>
     * @return The delta.
     */
    long getDelta();
  }
  /**
   * Protobuf type {@code com.google.android.horologist.datalayer.sample.shared.grpc.CounterDelta}
   */
  public  static final class CounterDelta extends
      com.google.protobuf.GeneratedMessageLite<
          CounterDelta, CounterDelta.Builder> implements
      // @@protoc_insertion_point(message_implements:com.google.android.horologist.datalayer.sample.shared.grpc.CounterDelta)
      CounterDeltaOrBuilder {
    private CounterDelta() {
    }
    public static final int DELTA_FIELD_NUMBER = 1;
    private long delta_;
    /**
     * <code>int64 delta = 1;</code>
     * @return The delta.
     */
    @java.lang.Override
    public long getDelta() {
      return delta_;
    }
    /**
     * <code>int64 delta = 1;</code>
     * @param value The delta to set.
     */
    private void setDelta(long value) {
      
      delta_ = value;
    }
    /**
     * <code>int64 delta = 1;</code>
     */
    private void clearDelta() {

      delta_ = 0L;
    }

    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
      return (Builder) DEFAULT_INSTANCE.createBuilder();
    }
    public static Builder newBuilder(com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta prototype) {
      return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code com.google.android.horologist.datalayer.sample.shared.grpc.CounterDelta}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta, Builder> implements
        // @@protoc_insertion_point(builder_implements:com.google.android.horologist.datalayer.sample.shared.grpc.CounterDelta)
        com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDeltaOrBuilder {
      // Construct using com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta.newBuilder()
      private Builder() {
        super(DEFAULT_INSTANCE);
      }


      /**
       * <code>int64 delta = 1;</code>
       * @return The delta.
       */
      @java.lang.Override
      public long getDelta() {
        return instance.getDelta();
      }
      /**
       * <code>int64 delta = 1;</code>
       * @param value The delta to set.
       * @return This builder for chaining.
       */
      public Builder setDelta(long value) {
        copyOnWrite();
        instance.setDelta(value);
        return this;
      }
      /**
       * <code>int64 delta = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearDelta() {
        copyOnWrite();
        instance.clearDelta();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.google.android.horologist.datalayer.sample.shared.grpc.CounterDelta)
    }
    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
        com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
        java.lang.Object arg0, java.lang.Object arg1) {
      switch (method) {
        case NEW_MUTABLE_INSTANCE: {
          return new com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta();
        }
        case NEW_BUILDER: {
          return new Builder();
        }
        case BUILD_MESSAGE_INFO: {
            java.lang.Object[] objects = new java.lang.Object[] {
              "delta_",
            };
            java.lang.String info =
                "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0002";
            return newMessageInfo(DEFAULT_INSTANCE, info, objects);
        }
        // fall through
        case GET_DEFAULT_INSTANCE: {
          return DEFAULT_INSTANCE;
        }
        case GET_PARSER: {
          com.google.protobuf.Parser<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta> parser = PARSER;
          if (parser == null) {
            synchronized (com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta.class) {
              parser = PARSER;
              if (parser == null) {
                parser =
                    new DefaultInstanceBasedParser<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta>(
                        DEFAULT_INSTANCE);
                PARSER = parser;
              }
            }
          }
          return parser;
      }
      case GET_MEMOIZED_IS_INITIALIZED: {
        return (byte) 1;
      }
      case SET_MEMOIZED_IS_INITIALIZED: {
        return null;
      }
      }
      throw new UnsupportedOperationException();
    }


    // @@protoc_insertion_point(class_scope:com.google.android.horologist.datalayer.sample.shared.grpc.CounterDelta)
    private static final com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta DEFAULT_INSTANCE;
    static {
      CounterDelta defaultInstance = new CounterDelta();
      // New instances are implicitly immutable so no need to make
      // immutable.
      DEFAULT_INSTANCE = defaultInstance;
      com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
        CounterDelta.class, defaultInstance);
    }

    public static com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<CounterDelta> PARSER;

    public static com.google.protobuf.Parser<CounterDelta> parser() {
      return DEFAULT_INSTANCE.getParserForType();
    }
  }


  static {
  }

  // @@protoc_insertion_point(outer_class_scope)
}
