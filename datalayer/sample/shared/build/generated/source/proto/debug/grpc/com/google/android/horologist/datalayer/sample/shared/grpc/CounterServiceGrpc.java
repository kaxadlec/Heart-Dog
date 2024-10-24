package com.google.android.horologist.datalayer.sample.shared.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.68.0)",
    comments = "Source: grpc_demo.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CounterServiceGrpc {

  private CounterServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.google.android.horologist.datalayer.sample.shared.grpc.CounterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> getGetCounterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getCounter",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> getGetCounterMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> getGetCounterMethod;
    if ((getGetCounterMethod = CounterServiceGrpc.getGetCounterMethod) == null) {
      synchronized (CounterServiceGrpc.class) {
        if ((getGetCounterMethod = CounterServiceGrpc.getGetCounterMethod) == null) {
          CounterServiceGrpc.getGetCounterMethod = getGetCounterMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getCounter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue.getDefaultInstance()))
              .build();
        }
      }
    }
    return getGetCounterMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta,
      com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> getIncrementMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "increment",
      requestType = com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta.class,
      responseType = com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta,
      com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> getIncrementMethod() {
    io.grpc.MethodDescriptor<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta, com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> getIncrementMethod;
    if ((getIncrementMethod = CounterServiceGrpc.getIncrementMethod) == null) {
      synchronized (CounterServiceGrpc.class) {
        if ((getIncrementMethod = CounterServiceGrpc.getIncrementMethod) == null) {
          CounterServiceGrpc.getIncrementMethod = getIncrementMethod =
              io.grpc.MethodDescriptor.<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta, com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "increment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue.getDefaultInstance()))
              .build();
        }
      }
    }
    return getIncrementMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CounterServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CounterServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CounterServiceStub>() {
        @java.lang.Override
        public CounterServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CounterServiceStub(channel, callOptions);
        }
      };
    return CounterServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CounterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CounterServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CounterServiceBlockingStub>() {
        @java.lang.Override
        public CounterServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CounterServiceBlockingStub(channel, callOptions);
        }
      };
    return CounterServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CounterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CounterServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CounterServiceFutureStub>() {
        @java.lang.Override
        public CounterServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CounterServiceFutureStub(channel, callOptions);
        }
      };
    return CounterServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getCounter(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCounterMethod(), responseObserver);
    }

    /**
     */
    default void increment(com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta request,
        io.grpc.stub.StreamObserver<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getIncrementMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CounterService.
   */
  public static abstract class CounterServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CounterServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CounterService.
   */
  public static final class CounterServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CounterServiceStub> {
    private CounterServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CounterServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CounterServiceStub(channel, callOptions);
    }

    /**
     */
    public void getCounter(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCounterMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void increment(com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta request,
        io.grpc.stub.StreamObserver<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getIncrementMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CounterService.
   */
  public static final class CounterServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CounterServiceBlockingStub> {
    private CounterServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CounterServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CounterServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue getCounter(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCounterMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue increment(com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getIncrementMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CounterService.
   */
  public static final class CounterServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CounterServiceFutureStub> {
    private CounterServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CounterServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CounterServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> getCounter(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCounterMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> increment(
        com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getIncrementMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_COUNTER = 0;
  private static final int METHODID_INCREMENT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_COUNTER:
          serviceImpl.getCounter((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue>) responseObserver);
          break;
        case METHODID_INCREMENT:
          serviceImpl.increment((com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta) request,
              (io.grpc.stub.StreamObserver<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetCounterMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue>(
                service, METHODID_GET_COUNTER)))
        .addMethod(
          getIncrementMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterDelta,
              com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue>(
                service, METHODID_INCREMENT)))
        .build();
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CounterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getGetCounterMethod())
              .addMethod(getIncrementMethod())
              .build();
        }
      }
    }
    return result;
  }
}
