// Generated by the protocol buffer compiler. DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: app_helper_pb.proto

// Generated files should ignore deprecation warnings
@file:Suppress("DEPRECATION")
package com.google.android.horologist.data;

@kotlin.jvm.JvmName("-initializetileInfo")
public inline fun tileInfo(block: com.google.android.horologist.data.TileInfoKt.Dsl.() -> kotlin.Unit): com.google.android.horologist.data.TileInfo =
  com.google.android.horologist.data.TileInfoKt.Dsl._create(com.google.android.horologist.data.TileInfo.newBuilder()).apply { block() }._build()
/**
 * Protobuf type `com.google.android.horologist.data.TileInfo`
 */
public object TileInfoKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: com.google.android.horologist.data.TileInfo.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: com.google.android.horologist.data.TileInfo.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): com.google.android.horologist.data.TileInfo = _builder.build()

    /**
     * `string name = 2;`
     */
    public var name: kotlin.String
      @JvmName("getName")
      get() = _builder.name
      @JvmName("setName")
      set(value) {
        _builder.name = value
      }
    /**
     * `string name = 2;`
     */
    public fun clearName() {
      _builder.clearName()
    }

    /**
     * `.google.protobuf.Timestamp timestamp = 3;`
     */
    public var timestamp: com.google.protobuf.Timestamp
      @JvmName("getTimestamp")
      get() = _builder.timestamp
      @JvmName("setTimestamp")
      set(value) {
        _builder.timestamp = value
      }
    /**
     * `.google.protobuf.Timestamp timestamp = 3;`
     */
    public fun clearTimestamp() {
      _builder.clearTimestamp()
    }
    /**
     * `.google.protobuf.Timestamp timestamp = 3;`
     * @return Whether the timestamp field is set.
     */
    public fun hasTimestamp(): kotlin.Boolean {
      return _builder.hasTimestamp()
    }
    public val TileInfoKt.Dsl.timestampOrNull: com.google.protobuf.Timestamp?
      get() = _builder.timestampOrNull
  }
}
public inline fun com.google.android.horologist.data.TileInfo.copy(block: `com.google.android.horologist.data`.TileInfoKt.Dsl.() -> kotlin.Unit): com.google.android.horologist.data.TileInfo =
  `com.google.android.horologist.data`.TileInfoKt.Dsl._create(this.toBuilder()).apply { block() }._build()

public val com.google.android.horologist.data.TileInfoOrBuilder.timestampOrNull: com.google.protobuf.Timestamp?
  get() = if (hasTimestamp()) getTimestamp() else null

