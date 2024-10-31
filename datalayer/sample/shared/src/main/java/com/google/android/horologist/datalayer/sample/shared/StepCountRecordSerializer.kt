package com.google.android.horologist.datalayer.sample.shared

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.android.horologist.datalayer.sample.shared.grpc.StepCountProto.StepCountValue
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object StepCountRecordSerializer : Serializer<StepCountValue> {
    override val defaultValue: StepCountValue
        get() = StepCountValue.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): StepCountValue =
        try {
            StepCountValue.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: StepCountValue, output: OutputStream) {
        t.writeTo(output)
    }
}
