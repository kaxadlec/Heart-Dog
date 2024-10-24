package com.google.android.horologist.datalayer.sample.screens.datalayer;

import com.google.android.horologist.datalayer.sample.shared.grpc.CounterServiceGrpcKt;
import com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.flow.Flow;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class DataLayerViewModel_Factory implements Factory<DataLayerViewModel> {
  private final Provider<CounterServiceGrpcKt.CounterServiceCoroutineStub> counterServiceProvider;

  private final Provider<Flow<GrpcDemoProto.CounterValue>> counterFlowProvider;

  public DataLayerViewModel_Factory(
      Provider<CounterServiceGrpcKt.CounterServiceCoroutineStub> counterServiceProvider,
      Provider<Flow<GrpcDemoProto.CounterValue>> counterFlowProvider) {
    this.counterServiceProvider = counterServiceProvider;
    this.counterFlowProvider = counterFlowProvider;
  }

  @Override
  public DataLayerViewModel get() {
    return newInstance(counterServiceProvider.get(), counterFlowProvider.get());
  }

  public static DataLayerViewModel_Factory create(
      Provider<CounterServiceGrpcKt.CounterServiceCoroutineStub> counterServiceProvider,
      Provider<Flow<GrpcDemoProto.CounterValue>> counterFlowProvider) {
    return new DataLayerViewModel_Factory(counterServiceProvider, counterFlowProvider);
  }

  public static DataLayerViewModel newInstance(
      CounterServiceGrpcKt.CounterServiceCoroutineStub counterService,
      Flow<GrpcDemoProto.CounterValue> counterFlow) {
    return new DataLayerViewModel(counterService, counterFlow);
  }
}
