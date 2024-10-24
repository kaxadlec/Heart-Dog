package com.google.android.horologist.datalayer.sample.shared.grpc

import com.google.android.horologist.datalayer.sample.shared.grpc.CounterServiceGrpc.getServiceDescriptor
import com.google.protobuf.Empty
import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

/**
 * Holder for Kotlin coroutine-based client and server APIs for
 * com.google.android.horologist.datalayer.sample.shared.grpc.CounterService.
 */
public object CounterServiceGrpcKt {
  public const val SERVICE_NAME: String = CounterServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = CounterServiceGrpc.getServiceDescriptor()

  public val getCounterMethod: MethodDescriptor<Empty, GrpcDemoProto.CounterValue>
    @JvmStatic
    get() = CounterServiceGrpc.getGetCounterMethod()

  public val incrementMethod:
      MethodDescriptor<GrpcDemoProto.CounterDelta, GrpcDemoProto.CounterValue>
    @JvmStatic
    get() = CounterServiceGrpc.getIncrementMethod()

  /**
   * A stub for issuing RPCs to a(n)
   * com.google.android.horologist.datalayer.sample.shared.grpc.CounterService service as suspending
   * coroutines.
   */
  @StubFor(CounterServiceGrpc::class)
  public class CounterServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<CounterServiceCoroutineStub>(channel, callOptions) {
    public override fun build(channel: Channel, callOptions: CallOptions):
        CounterServiceCoroutineStub = CounterServiceCoroutineStub(channel, callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun getCounter(request: Empty, headers: Metadata = Metadata()):
        GrpcDemoProto.CounterValue = unaryRpc(
      channel,
      CounterServiceGrpc.getGetCounterMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][Status].  If the RPC completes with another status, a corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun increment(request: GrpcDemoProto.CounterDelta, headers: Metadata =
        Metadata()): GrpcDemoProto.CounterValue = unaryRpc(
      channel,
      CounterServiceGrpc.getIncrementMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the
   * com.google.android.horologist.datalayer.sample.shared.grpc.CounterService service based on Kotlin
   * coroutines.
   */
  public abstract class CounterServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for
     * com.google.android.horologist.datalayer.sample.shared.grpc.CounterService.getCounter.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getCounter(request: Empty): GrpcDemoProto.CounterValue = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.google.android.horologist.datalayer.sample.shared.grpc.CounterService.getCounter is unimplemented"))

    /**
     * Returns the response to an RPC for
     * com.google.android.horologist.datalayer.sample.shared.grpc.CounterService.increment.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [Status].  If this method fails with a [java.util.concurrent.CancellationException], the RPC
     * will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun increment(request: GrpcDemoProto.CounterDelta):
        GrpcDemoProto.CounterValue = throw
        StatusException(UNIMPLEMENTED.withDescription("Method com.google.android.horologist.datalayer.sample.shared.grpc.CounterService.increment is unimplemented"))

    public final override fun bindService(): ServerServiceDefinition =
        builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = CounterServiceGrpc.getGetCounterMethod(),
      implementation = ::getCounter
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = CounterServiceGrpc.getIncrementMethod(),
      implementation = ::increment
    )).build()
  }
}
