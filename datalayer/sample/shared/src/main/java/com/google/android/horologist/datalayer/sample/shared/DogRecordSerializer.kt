package com.google.android.horologist.datalayer.sample.shared

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.android.horologist.datalayer.sample.shared.grpc.DogProto.DogRecord
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object DogRecordSerializer : Serializer<DogRecord> {
    override val defaultValue: DogRecord
        get() = DogRecord.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): DogRecord =
        try {
            DogRecord.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: DogRecord, output: OutputStream) {
        t.writeTo(output)
    }
}