// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: app_helper_pb.proto
// Protobuf Java Version: 4.28.2

package com.google.android.horologist.data;

/**
 * Protobuf type {@code com.google.android.horologist.data.SurfacesInfo}
 */
public  final class SurfacesInfo extends
    com.google.protobuf.GeneratedMessageLite<
        SurfacesInfo, SurfacesInfo.Builder> implements
    // @@protoc_insertion_point(message_implements:com.google.android.horologist.data.SurfacesInfo)
    SurfacesInfoOrBuilder {
  private SurfacesInfo() {
    complications_ = emptyProtobufList();
    tiles_ = emptyProtobufList();
  }
  private int bitField0_;
  public static final int COMPLICATIONS_FIELD_NUMBER = 2;
  private com.google.protobuf.Internal.ProtobufList<com.google.android.horologist.data.ComplicationInfo> complications_;
  /**
   * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.google.android.horologist.data.ComplicationInfo> getComplicationsList() {
    return complications_;
  }
  /**
   * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
   */
  public java.util.List<? extends com.google.android.horologist.data.ComplicationInfoOrBuilder> 
      getComplicationsOrBuilderList() {
    return complications_;
  }
  /**
   * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
   */
  @java.lang.Override
  public int getComplicationsCount() {
    return complications_.size();
  }
  /**
   * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
   */
  @java.lang.Override
  public com.google.android.horologist.data.ComplicationInfo getComplications(int index) {
    return complications_.get(index);
  }
  /**
   * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
   */
  public com.google.android.horologist.data.ComplicationInfoOrBuilder getComplicationsOrBuilder(
      int index) {
    return complications_.get(index);
  }
  private void ensureComplicationsIsMutable() {
    com.google.protobuf.Internal.ProtobufList<com.google.android.horologist.data.ComplicationInfo> tmp = complications_;
    if (!tmp.isModifiable()) {
      complications_ =
          com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
     }
  }

  /**
   * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
   */
  private void setComplications(
      int index, com.google.android.horologist.data.ComplicationInfo value) {
    value.getClass();
  ensureComplicationsIsMutable();
    complications_.set(index, value);
  }
  /**
   * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
   */
  private void addComplications(com.google.android.horologist.data.ComplicationInfo value) {
    value.getClass();
  ensureComplicationsIsMutable();
    complications_.add(value);
  }
  /**
   * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
   */
  private void addComplications(
      int index, com.google.android.horologist.data.ComplicationInfo value) {
    value.getClass();
  ensureComplicationsIsMutable();
    complications_.add(index, value);
  }
  /**
   * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
   */
  private void addAllComplications(
      java.lang.Iterable<? extends com.google.android.horologist.data.ComplicationInfo> values) {
    ensureComplicationsIsMutable();
    com.google.protobuf.AbstractMessageLite.addAll(
        values, complications_);
  }
  /**
   * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
   */
  private void clearComplications() {
    complications_ = emptyProtobufList();
  }
  /**
   * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
   */
  private void removeComplications(int index) {
    ensureComplicationsIsMutable();
    complications_.remove(index);
  }

  public static final int TILES_FIELD_NUMBER = 3;
  private com.google.protobuf.Internal.ProtobufList<com.google.android.horologist.data.TileInfo> tiles_;
  /**
   * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
   */
  @java.lang.Override
  public java.util.List<com.google.android.horologist.data.TileInfo> getTilesList() {
    return tiles_;
  }
  /**
   * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
   */
  public java.util.List<? extends com.google.android.horologist.data.TileInfoOrBuilder> 
      getTilesOrBuilderList() {
    return tiles_;
  }
  /**
   * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
   */
  @java.lang.Override
  public int getTilesCount() {
    return tiles_.size();
  }
  /**
   * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
   */
  @java.lang.Override
  public com.google.android.horologist.data.TileInfo getTiles(int index) {
    return tiles_.get(index);
  }
  /**
   * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
   */
  public com.google.android.horologist.data.TileInfoOrBuilder getTilesOrBuilder(
      int index) {
    return tiles_.get(index);
  }
  private void ensureTilesIsMutable() {
    com.google.protobuf.Internal.ProtobufList<com.google.android.horologist.data.TileInfo> tmp = tiles_;
    if (!tmp.isModifiable()) {
      tiles_ =
          com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
     }
  }

  /**
   * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
   */
  private void setTiles(
      int index, com.google.android.horologist.data.TileInfo value) {
    value.getClass();
  ensureTilesIsMutable();
    tiles_.set(index, value);
  }
  /**
   * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
   */
  private void addTiles(com.google.android.horologist.data.TileInfo value) {
    value.getClass();
  ensureTilesIsMutable();
    tiles_.add(value);
  }
  /**
   * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
   */
  private void addTiles(
      int index, com.google.android.horologist.data.TileInfo value) {
    value.getClass();
  ensureTilesIsMutable();
    tiles_.add(index, value);
  }
  /**
   * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
   */
  private void addAllTiles(
      java.lang.Iterable<? extends com.google.android.horologist.data.TileInfo> values) {
    ensureTilesIsMutable();
    com.google.protobuf.AbstractMessageLite.addAll(
        values, tiles_);
  }
  /**
   * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
   */
  private void clearTiles() {
    tiles_ = emptyProtobufList();
  }
  /**
   * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
   */
  private void removeTiles(int index) {
    ensureTilesIsMutable();
    tiles_.remove(index);
  }

  public static final int ACTIVITYLAUNCHED_FIELD_NUMBER = 4;
  private com.google.android.horologist.data.ActivityLaunched activityLaunched_;
  /**
   * <pre>
   * Temporarily keep [ActivityLaunched] - remove at a later date
   * </pre>
   *
   * <code>.com.google.android.horologist.data.ActivityLaunched activityLaunched = 4;</code>
   */
  @java.lang.Override
  public boolean hasActivityLaunched() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <pre>
   * Temporarily keep [ActivityLaunched] - remove at a later date
   * </pre>
   *
   * <code>.com.google.android.horologist.data.ActivityLaunched activityLaunched = 4;</code>
   */
  @java.lang.Override
  public com.google.android.horologist.data.ActivityLaunched getActivityLaunched() {
    return activityLaunched_ == null ? com.google.android.horologist.data.ActivityLaunched.getDefaultInstance() : activityLaunched_;
  }
  /**
   * <pre>
   * Temporarily keep [ActivityLaunched] - remove at a later date
   * </pre>
   *
   * <code>.com.google.android.horologist.data.ActivityLaunched activityLaunched = 4;</code>
   */
  private void setActivityLaunched(com.google.android.horologist.data.ActivityLaunched value) {
    value.getClass();
  activityLaunched_ = value;
    bitField0_ |= 0x00000001;
    }
  /**
   * <pre>
   * Temporarily keep [ActivityLaunched] - remove at a later date
   * </pre>
   *
   * <code>.com.google.android.horologist.data.ActivityLaunched activityLaunched = 4;</code>
   */
  @java.lang.SuppressWarnings({"ReferenceEquality"})
  private void mergeActivityLaunched(com.google.android.horologist.data.ActivityLaunched value) {
    value.getClass();
  if (activityLaunched_ != null &&
        activityLaunched_ != com.google.android.horologist.data.ActivityLaunched.getDefaultInstance()) {
      activityLaunched_ =
        com.google.android.horologist.data.ActivityLaunched.newBuilder(activityLaunched_).mergeFrom(value).buildPartial();
    } else {
      activityLaunched_ = value;
    }
    bitField0_ |= 0x00000001;
  }
  /**
   * <pre>
   * Temporarily keep [ActivityLaunched] - remove at a later date
   * </pre>
   *
   * <code>.com.google.android.horologist.data.ActivityLaunched activityLaunched = 4;</code>
   */
  private void clearActivityLaunched() {  activityLaunched_ = null;
    bitField0_ = (bitField0_ & ~0x00000001);
  }

  public static final int USAGEINFO_FIELD_NUMBER = 5;
  private com.google.android.horologist.data.UsageInfo usageInfo_;
  /**
   * <code>.com.google.android.horologist.data.UsageInfo usageInfo = 5;</code>
   */
  @java.lang.Override
  public boolean hasUsageInfo() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>.com.google.android.horologist.data.UsageInfo usageInfo = 5;</code>
   */
  @java.lang.Override
  public com.google.android.horologist.data.UsageInfo getUsageInfo() {
    return usageInfo_ == null ? com.google.android.horologist.data.UsageInfo.getDefaultInstance() : usageInfo_;
  }
  /**
   * <code>.com.google.android.horologist.data.UsageInfo usageInfo = 5;</code>
   */
  private void setUsageInfo(com.google.android.horologist.data.UsageInfo value) {
    value.getClass();
  usageInfo_ = value;
    bitField0_ |= 0x00000002;
    }
  /**
   * <code>.com.google.android.horologist.data.UsageInfo usageInfo = 5;</code>
   */
  @java.lang.SuppressWarnings({"ReferenceEquality"})
  private void mergeUsageInfo(com.google.android.horologist.data.UsageInfo value) {
    value.getClass();
  if (usageInfo_ != null &&
        usageInfo_ != com.google.android.horologist.data.UsageInfo.getDefaultInstance()) {
      usageInfo_ =
        com.google.android.horologist.data.UsageInfo.newBuilder(usageInfo_).mergeFrom(value).buildPartial();
    } else {
      usageInfo_ = value;
    }
    bitField0_ |= 0x00000002;
  }
  /**
   * <code>.com.google.android.horologist.data.UsageInfo usageInfo = 5;</code>
   */
  private void clearUsageInfo() {  usageInfo_ = null;
    bitField0_ = (bitField0_ & ~0x00000002);
  }

  public static com.google.android.horologist.data.SurfacesInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.google.android.horologist.data.SurfacesInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.google.android.horologist.data.SurfacesInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.google.android.horologist.data.SurfacesInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.google.android.horologist.data.SurfacesInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.google.android.horologist.data.SurfacesInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.google.android.horologist.data.SurfacesInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.google.android.horologist.data.SurfacesInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static com.google.android.horologist.data.SurfacesInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }

  public static com.google.android.horologist.data.SurfacesInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static com.google.android.horologist.data.SurfacesInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.google.android.horologist.data.SurfacesInfo parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return (Builder) DEFAULT_INSTANCE.createBuilder();
  }
  public static Builder newBuilder(com.google.android.horologist.data.SurfacesInfo prototype) {
    return DEFAULT_INSTANCE.createBuilder(prototype);
  }

  /**
   * Protobuf type {@code com.google.android.horologist.data.SurfacesInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        com.google.android.horologist.data.SurfacesInfo, Builder> implements
      // @@protoc_insertion_point(builder_implements:com.google.android.horologist.data.SurfacesInfo)
      com.google.android.horologist.data.SurfacesInfoOrBuilder {
    // Construct using com.google.android.horologist.data.SurfacesInfo.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
     */
    @java.lang.Override
    public java.util.List<com.google.android.horologist.data.ComplicationInfo> getComplicationsList() {
      return java.util.Collections.unmodifiableList(
          instance.getComplicationsList());
    }
    /**
     * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
     */
    @java.lang.Override
    public int getComplicationsCount() {
      return instance.getComplicationsCount();
    }/**
     * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
     */
    @java.lang.Override
    public com.google.android.horologist.data.ComplicationInfo getComplications(int index) {
      return instance.getComplications(index);
    }
    /**
     * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
     */
    public Builder setComplications(
        int index, com.google.android.horologist.data.ComplicationInfo value) {
      copyOnWrite();
      instance.setComplications(index, value);
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
     */
    public Builder setComplications(
        int index, com.google.android.horologist.data.ComplicationInfo.Builder builderForValue) {
      copyOnWrite();
      instance.setComplications(index,
          builderForValue.build());
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
     */
    public Builder addComplications(com.google.android.horologist.data.ComplicationInfo value) {
      copyOnWrite();
      instance.addComplications(value);
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
     */
    public Builder addComplications(
        int index, com.google.android.horologist.data.ComplicationInfo value) {
      copyOnWrite();
      instance.addComplications(index, value);
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
     */
    public Builder addComplications(
        com.google.android.horologist.data.ComplicationInfo.Builder builderForValue) {
      copyOnWrite();
      instance.addComplications(builderForValue.build());
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
     */
    public Builder addComplications(
        int index, com.google.android.horologist.data.ComplicationInfo.Builder builderForValue) {
      copyOnWrite();
      instance.addComplications(index,
          builderForValue.build());
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
     */
    public Builder addAllComplications(
        java.lang.Iterable<? extends com.google.android.horologist.data.ComplicationInfo> values) {
      copyOnWrite();
      instance.addAllComplications(values);
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
     */
    public Builder clearComplications() {
      copyOnWrite();
      instance.clearComplications();
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.ComplicationInfo complications = 2;</code>
     */
    public Builder removeComplications(int index) {
      copyOnWrite();
      instance.removeComplications(index);
      return this;
    }

    /**
     * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
     */
    @java.lang.Override
    public java.util.List<com.google.android.horologist.data.TileInfo> getTilesList() {
      return java.util.Collections.unmodifiableList(
          instance.getTilesList());
    }
    /**
     * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
     */
    @java.lang.Override
    public int getTilesCount() {
      return instance.getTilesCount();
    }/**
     * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
     */
    @java.lang.Override
    public com.google.android.horologist.data.TileInfo getTiles(int index) {
      return instance.getTiles(index);
    }
    /**
     * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
     */
    public Builder setTiles(
        int index, com.google.android.horologist.data.TileInfo value) {
      copyOnWrite();
      instance.setTiles(index, value);
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
     */
    public Builder setTiles(
        int index, com.google.android.horologist.data.TileInfo.Builder builderForValue) {
      copyOnWrite();
      instance.setTiles(index,
          builderForValue.build());
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
     */
    public Builder addTiles(com.google.android.horologist.data.TileInfo value) {
      copyOnWrite();
      instance.addTiles(value);
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
     */
    public Builder addTiles(
        int index, com.google.android.horologist.data.TileInfo value) {
      copyOnWrite();
      instance.addTiles(index, value);
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
     */
    public Builder addTiles(
        com.google.android.horologist.data.TileInfo.Builder builderForValue) {
      copyOnWrite();
      instance.addTiles(builderForValue.build());
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
     */
    public Builder addTiles(
        int index, com.google.android.horologist.data.TileInfo.Builder builderForValue) {
      copyOnWrite();
      instance.addTiles(index,
          builderForValue.build());
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
     */
    public Builder addAllTiles(
        java.lang.Iterable<? extends com.google.android.horologist.data.TileInfo> values) {
      copyOnWrite();
      instance.addAllTiles(values);
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
     */
    public Builder clearTiles() {
      copyOnWrite();
      instance.clearTiles();
      return this;
    }
    /**
     * <code>repeated .com.google.android.horologist.data.TileInfo tiles = 3;</code>
     */
    public Builder removeTiles(int index) {
      copyOnWrite();
      instance.removeTiles(index);
      return this;
    }

    /**
     * <pre>
     * Temporarily keep [ActivityLaunched] - remove at a later date
     * </pre>
     *
     * <code>.com.google.android.horologist.data.ActivityLaunched activityLaunched = 4;</code>
     */
    @java.lang.Override
    public boolean hasActivityLaunched() {
      return instance.hasActivityLaunched();
    }
    /**
     * <pre>
     * Temporarily keep [ActivityLaunched] - remove at a later date
     * </pre>
     *
     * <code>.com.google.android.horologist.data.ActivityLaunched activityLaunched = 4;</code>
     */
    @java.lang.Override
    public com.google.android.horologist.data.ActivityLaunched getActivityLaunched() {
      return instance.getActivityLaunched();
    }
    /**
     * <pre>
     * Temporarily keep [ActivityLaunched] - remove at a later date
     * </pre>
     *
     * <code>.com.google.android.horologist.data.ActivityLaunched activityLaunched = 4;</code>
     */
    public Builder setActivityLaunched(com.google.android.horologist.data.ActivityLaunched value) {
      copyOnWrite();
      instance.setActivityLaunched(value);
      return this;
      }
    /**
     * <pre>
     * Temporarily keep [ActivityLaunched] - remove at a later date
     * </pre>
     *
     * <code>.com.google.android.horologist.data.ActivityLaunched activityLaunched = 4;</code>
     */
    public Builder setActivityLaunched(
        com.google.android.horologist.data.ActivityLaunched.Builder builderForValue) {
      copyOnWrite();
      instance.setActivityLaunched(builderForValue.build());
      return this;
    }
    /**
     * <pre>
     * Temporarily keep [ActivityLaunched] - remove at a later date
     * </pre>
     *
     * <code>.com.google.android.horologist.data.ActivityLaunched activityLaunched = 4;</code>
     */
    public Builder mergeActivityLaunched(com.google.android.horologist.data.ActivityLaunched value) {
      copyOnWrite();
      instance.mergeActivityLaunched(value);
      return this;
    }
    /**
     * <pre>
     * Temporarily keep [ActivityLaunched] - remove at a later date
     * </pre>
     *
     * <code>.com.google.android.horologist.data.ActivityLaunched activityLaunched = 4;</code>
     */
    public Builder clearActivityLaunched() {  copyOnWrite();
      instance.clearActivityLaunched();
      return this;
    }

    /**
     * <code>.com.google.android.horologist.data.UsageInfo usageInfo = 5;</code>
     */
    @java.lang.Override
    public boolean hasUsageInfo() {
      return instance.hasUsageInfo();
    }
    /**
     * <code>.com.google.android.horologist.data.UsageInfo usageInfo = 5;</code>
     */
    @java.lang.Override
    public com.google.android.horologist.data.UsageInfo getUsageInfo() {
      return instance.getUsageInfo();
    }
    /**
     * <code>.com.google.android.horologist.data.UsageInfo usageInfo = 5;</code>
     */
    public Builder setUsageInfo(com.google.android.horologist.data.UsageInfo value) {
      copyOnWrite();
      instance.setUsageInfo(value);
      return this;
      }
    /**
     * <code>.com.google.android.horologist.data.UsageInfo usageInfo = 5;</code>
     */
    public Builder setUsageInfo(
        com.google.android.horologist.data.UsageInfo.Builder builderForValue) {
      copyOnWrite();
      instance.setUsageInfo(builderForValue.build());
      return this;
    }
    /**
     * <code>.com.google.android.horologist.data.UsageInfo usageInfo = 5;</code>
     */
    public Builder mergeUsageInfo(com.google.android.horologist.data.UsageInfo value) {
      copyOnWrite();
      instance.mergeUsageInfo(value);
      return this;
    }
    /**
     * <code>.com.google.android.horologist.data.UsageInfo usageInfo = 5;</code>
     */
    public Builder clearUsageInfo() {  copyOnWrite();
      instance.clearUsageInfo();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:com.google.android.horologist.data.SurfacesInfo)
  }
  @java.lang.Override
  @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
  protected final java.lang.Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      java.lang.Object arg0, java.lang.Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new com.google.android.horologist.data.SurfacesInfo();
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case BUILD_MESSAGE_INFO: {
          java.lang.Object[] objects = new java.lang.Object[] {
            "bitField0_",
            "complications_",
            com.google.android.horologist.data.ComplicationInfo.class,
            "tiles_",
            com.google.android.horologist.data.TileInfo.class,
            "activityLaunched_",
            "usageInfo_",
          };
          java.lang.String info =
              "\u0000\u0004\u0000\u0001\u0002\u0005\u0004\u0000\u0002\u0000\u0002\u001b\u0003\u001b" +
              "\u0004\u1009\u0000\u0005\u1009\u0001";
          return newMessageInfo(DEFAULT_INSTANCE, info, objects);
      }
      // fall through
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        com.google.protobuf.Parser<com.google.android.horologist.data.SurfacesInfo> parser = PARSER;
        if (parser == null) {
          synchronized (com.google.android.horologist.data.SurfacesInfo.class) {
            parser = PARSER;
            if (parser == null) {
              parser =
                  new DefaultInstanceBasedParser<com.google.android.horologist.data.SurfacesInfo>(
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


  // @@protoc_insertion_point(class_scope:com.google.android.horologist.data.SurfacesInfo)
  private static final com.google.android.horologist.data.SurfacesInfo DEFAULT_INSTANCE;
  static {
    SurfacesInfo defaultInstance = new SurfacesInfo();
    // New instances are implicitly immutable so no need to make
    // immutable.
    DEFAULT_INSTANCE = defaultInstance;
    com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
      SurfacesInfo.class, defaultInstance);
  }

  public static com.google.android.horologist.data.SurfacesInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<SurfacesInfo> PARSER;

  public static com.google.protobuf.Parser<SurfacesInfo> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

