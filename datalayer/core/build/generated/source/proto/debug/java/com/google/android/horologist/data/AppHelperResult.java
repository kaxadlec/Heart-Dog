// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: app_helper_pb.proto
// Protobuf Java Version: 4.28.2

package com.google.android.horologist.data;

/**
 * Protobuf type {@code com.google.android.horologist.data.AppHelperResult}
 */
public  final class AppHelperResult extends
    com.google.protobuf.GeneratedMessageLite<
        AppHelperResult, AppHelperResult.Builder> implements
    // @@protoc_insertion_point(message_implements:com.google.android.horologist.data.AppHelperResult)
    AppHelperResultOrBuilder {
  private AppHelperResult() {
  }
  public static final int CODE_FIELD_NUMBER = 1;
  private int code_;
  /**
   * <code>.com.google.android.horologist.data.AppHelperResultCode code = 1;</code>
   * @return The enum numeric value on the wire for code.
   */
  @java.lang.Override
  public int getCodeValue() {
    return code_;
  }
  /**
   * <code>.com.google.android.horologist.data.AppHelperResultCode code = 1;</code>
   * @return The code.
   */
  @java.lang.Override
  public com.google.android.horologist.data.AppHelperResultCode getCode() {
    com.google.android.horologist.data.AppHelperResultCode result = com.google.android.horologist.data.AppHelperResultCode.forNumber(code_);
    return result == null ? com.google.android.horologist.data.AppHelperResultCode.UNRECOGNIZED : result;
  }
  /**
   * <code>.com.google.android.horologist.data.AppHelperResultCode code = 1;</code>
   * @param value The enum numeric value on the wire for code to set.
   */
  private void setCodeValue(int value) {
      code_ = value;
  }
  /**
   * <code>.com.google.android.horologist.data.AppHelperResultCode code = 1;</code>
   * @param value The code to set.
   */
  private void setCode(com.google.android.horologist.data.AppHelperResultCode value) {
    code_ = value.getNumber();

  }
  /**
   * <code>.com.google.android.horologist.data.AppHelperResultCode code = 1;</code>
   */
  private void clearCode() {

    code_ = 0;
  }

  public static com.google.android.horologist.data.AppHelperResult parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.google.android.horologist.data.AppHelperResult parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.google.android.horologist.data.AppHelperResult parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.google.android.horologist.data.AppHelperResult parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.google.android.horologist.data.AppHelperResult parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.google.android.horologist.data.AppHelperResult parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.google.android.horologist.data.AppHelperResult parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.google.android.horologist.data.AppHelperResult parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static com.google.android.horologist.data.AppHelperResult parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }

  public static com.google.android.horologist.data.AppHelperResult parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static com.google.android.horologist.data.AppHelperResult parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.google.android.horologist.data.AppHelperResult parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return (Builder) DEFAULT_INSTANCE.createBuilder();
  }
  public static Builder newBuilder(com.google.android.horologist.data.AppHelperResult prototype) {
    return DEFAULT_INSTANCE.createBuilder(prototype);
  }

  /**
   * Protobuf type {@code com.google.android.horologist.data.AppHelperResult}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        com.google.android.horologist.data.AppHelperResult, Builder> implements
      // @@protoc_insertion_point(builder_implements:com.google.android.horologist.data.AppHelperResult)
      com.google.android.horologist.data.AppHelperResultOrBuilder {
    // Construct using com.google.android.horologist.data.AppHelperResult.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>.com.google.android.horologist.data.AppHelperResultCode code = 1;</code>
     * @return The enum numeric value on the wire for code.
     */
    @java.lang.Override
    public int getCodeValue() {
      return instance.getCodeValue();
    }
    /**
     * <code>.com.google.android.horologist.data.AppHelperResultCode code = 1;</code>
     * @param value The code to set.
     * @return This builder for chaining.
     */
    public Builder setCodeValue(int value) {
      copyOnWrite();
      instance.setCodeValue(value);
      return this;
    }
    /**
     * <code>.com.google.android.horologist.data.AppHelperResultCode code = 1;</code>
     * @return The code.
     */
    @java.lang.Override
    public com.google.android.horologist.data.AppHelperResultCode getCode() {
      return instance.getCode();
    }
    /**
     * <code>.com.google.android.horologist.data.AppHelperResultCode code = 1;</code>
     * @param value The enum numeric value on the wire for code to set.
     * @return This builder for chaining.
     */
    public Builder setCode(com.google.android.horologist.data.AppHelperResultCode value) {
      copyOnWrite();
      instance.setCode(value);
      return this;
    }
    /**
     * <code>.com.google.android.horologist.data.AppHelperResultCode code = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearCode() {
      copyOnWrite();
      instance.clearCode();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:com.google.android.horologist.data.AppHelperResult)
  }
  @java.lang.Override
  @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
  protected final java.lang.Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      java.lang.Object arg0, java.lang.Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new com.google.android.horologist.data.AppHelperResult();
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case BUILD_MESSAGE_INFO: {
          java.lang.Object[] objects = new java.lang.Object[] {
            "code_",
          };
          java.lang.String info =
              "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f";
          return newMessageInfo(DEFAULT_INSTANCE, info, objects);
      }
      // fall through
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        com.google.protobuf.Parser<com.google.android.horologist.data.AppHelperResult> parser = PARSER;
        if (parser == null) {
          synchronized (com.google.android.horologist.data.AppHelperResult.class) {
            parser = PARSER;
            if (parser == null) {
              parser =
                  new DefaultInstanceBasedParser<com.google.android.horologist.data.AppHelperResult>(
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


  // @@protoc_insertion_point(class_scope:com.google.android.horologist.data.AppHelperResult)
  private static final com.google.android.horologist.data.AppHelperResult DEFAULT_INSTANCE;
  static {
    AppHelperResult defaultInstance = new AppHelperResult();
    // New instances are implicitly immutable so no need to make
    // immutable.
    DEFAULT_INSTANCE = defaultInstance;
    com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
      AppHelperResult.class, defaultInstance);
  }

  public static com.google.android.horologist.data.AppHelperResult getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<AppHelperResult> PARSER;

  public static com.google.protobuf.Parser<AppHelperResult> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

