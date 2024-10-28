package com.google.android.horologist.datalayer.sample.shared

import android.util.Log
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.android.horologist.datalayer.sample.shared.grpc.HeartRateProto.HeartRateRecord
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object HeartRateRecordSerializer : Serializer<HeartRateRecord> {
    override val defaultValue: HeartRateRecord
        get() = HeartRateRecord.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): HeartRateRecord =
        try {
            HeartRateRecord.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: HeartRateRecord, output: OutputStream) {
        t.writeTo(output)
    }
}