package com.google.android.horologist.datalayer.sample.di;

import com.google.android.horologist.data.WearDataLayerRegistry;
import com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.flow.Flow;

@ScopeMetadata("dagger.hilt.android.scopes.ActivityRetainedScoped")
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
public final class DatalayerModule_CounterFlowFactory implements Factory<Flow<GrpcDemoProto.CounterValue>> {
  private final Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider;

  public DatalayerModule_CounterFlowFactory(
      Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider) {
    this.wearDataLayerRegistryProvider = wearDataLayerRegistryProvider;
  }

  @Override
  public Flow<GrpcDemoProto.CounterValue> get() {
    return counterFlow(wearDataLayerRegistryProvider.get());
  }

  public static DatalayerModule_CounterFlowFactory create(
      Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider) {
    return new DatalayerModule_CounterFlowFactory(wearDataLayerRegistryProvider);
  }

  public static Flow<GrpcDemoProto.CounterValue> counterFlow(
      WearDataLayerRegistry wearDataLayerRegistry) {
    return Preconditions.checkNotNullFromProvides(DatalayerModule.INSTANCE.counterFlow(wearDataLayerRegistry));
  }
}
