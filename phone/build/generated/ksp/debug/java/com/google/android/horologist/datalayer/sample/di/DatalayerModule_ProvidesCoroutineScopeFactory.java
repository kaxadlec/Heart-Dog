package com.google.android.horologist.datalayer.sample.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineScope;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatalayerModule_ProvidesCoroutineScopeFactory implements Factory<CoroutineScope> {
  @Override
  public CoroutineScope get() {
    return providesCoroutineScope();
  }

  public static DatalayerModule_ProvidesCoroutineScopeFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CoroutineScope providesCoroutineScope() {
    return Preconditions.checkNotNullFromProvides(DatalayerModule.INSTANCE.providesCoroutineScope());
  }

  private static final class InstanceHolder {
    private static final DatalayerModule_ProvidesCoroutineScopeFactory INSTANCE = new DatalayerModule_ProvidesCoroutineScopeFactory();
  }
}
