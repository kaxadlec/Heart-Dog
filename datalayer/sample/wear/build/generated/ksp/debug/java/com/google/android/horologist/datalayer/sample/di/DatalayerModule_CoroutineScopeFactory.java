package com.google.android.horologist.datalayer.sample.di;

import dagger.hilt.android.ActivityRetainedLifecycle;
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
public final class DatalayerModule_CoroutineScopeFactory implements Factory<CoroutineScope> {
  private final Provider<ActivityRetainedLifecycle> activityRetainedLifecycleProvider;

  public DatalayerModule_CoroutineScopeFactory(
      Provider<ActivityRetainedLifecycle> activityRetainedLifecycleProvider) {
    this.activityRetainedLifecycleProvider = activityRetainedLifecycleProvider;
  }

  @Override
  public CoroutineScope get() {
    return coroutineScope(activityRetainedLifecycleProvider.get());
  }

  public static DatalayerModule_CoroutineScopeFactory create(
      Provider<ActivityRetainedLifecycle> activityRetainedLifecycleProvider) {
    return new DatalayerModule_CoroutineScopeFactory(activityRetainedLifecycleProvider);
  }

  public static CoroutineScope coroutineScope(ActivityRetainedLifecycle activityRetainedLifecycle) {
    return Preconditions.checkNotNullFromProvides(DatalayerModule.INSTANCE.coroutineScope(activityRetainedLifecycle));
  }
}
