package com.google.android.horologist.datalayer.sample.shared

import android.util.Log
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.android.horologist.datalayer.sample.shared.grpc.HeartRateProto.HeartRateRecord
import com.google.android.horologist.datalayer.sample.shared.grpc.LocationTrackingProto.LocationTrackingRecord
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object LocationTrackingRecordSerializer : Serializer<LocationTrackingRecord> {
    override val defaultValue: LocationTrackingRecord
        get() = LocationTrackingRecord.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): LocationTrackingRecord =
        try {
            LocationTrackingRecord.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: LocationTrackingRecord, output: OutputStream) {
        t.writeTo(output)
    }
}