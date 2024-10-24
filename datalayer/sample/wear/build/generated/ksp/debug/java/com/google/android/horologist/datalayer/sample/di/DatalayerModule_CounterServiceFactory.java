package com.google.android.horologist.datalayer.sample.di;

import com.google.android.horologist.data.WearDataLayerRegistry;
import com.google.android.horologist.datalayer.sample.shared.grpc.CounterServiceGrpcKt;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;

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
public final class DatalayerModule_CounterServiceFactory implements Factory<CounterServiceGrpcKt.CounterServiceCoroutineStub> {
  private final Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider;

  private final Provider<CoroutineScope> coroutineScopeProvider;

  public DatalayerModule_CounterServiceFactory(
      Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider,
      Provider<CoroutineScope> coroutineScopeProvider) {
    this.wearDataLayerRegistryProvider = wearDataLayerRegistryProvider;
    this.coroutineScopeProvider = coroutineScopeProvider;
  }

  @Override
  public CounterServiceGrpcKt.CounterServiceCoroutineStub get() {
    return counterService(wearDataLayerRegistryProvider.get(), coroutineScopeProvider.get());
  }

  public static DatalayerModule_CounterServiceFactory create(
      Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider,
      Provider<CoroutineScope> coroutineScopeProvider) {
    return new DatalayerModule_CounterServiceFactory(wearDataLayerRegistryProvider, coroutineScopeProvider);
  }

  public static CounterServiceGrpcKt.CounterServiceCoroutineStub counterService(
      WearDataLayerRegistry wearDataLayerRegistry, CoroutineScope coroutineScope) {
    return Preconditions.checkNotNullFromProvides(DatalayerModule.INSTANCE.counterService(wearDataLayerRegistry, coroutineScope));
  }
}
