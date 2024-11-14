package com.google.android.horologist.datalayer.sample.shared

import androidx.datastore.core.Serializer
import com.google.android.horologist.datalayer.sample.shared.grpc.EmojiProto
import java.io.InputStream
import java.io.OutputStream

object EmojiValueSerializer : Serializer<EmojiProto.EmojiValue> {
    override val defaultValue: EmojiProto.EmojiValue = EmojiProto.EmojiValue.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): EmojiProto.EmojiValue {
        return EmojiProto.EmojiValue.parseFrom(input)
    }

    override suspend fun writeTo(t: EmojiProto.EmojiValue, output: OutputStream) {
        t.writeTo(output)
    }
}
