package com.google.android.horologist.datalayer.sample.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineScope;

@ScopeMetadata("dagger.hilt.android.scopes.ServiceScoped")
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
public final class ServiceModule_CoroutineScopeFactory implements Factory<CoroutineScope> {
  @Override
  public CoroutineScope get() {
    return coroutineScope();
  }

  public static ServiceModule_CoroutineScopeFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CoroutineScope coroutineScope() {
    return Preconditions.checkNotNullFromProvides(ServiceModule.INSTANCE.coroutineScope());
  }

  private static final class InstanceHolder {
    private static final ServiceModule_CoroutineScopeFactory INSTANCE = new ServiceModule_CoroutineScopeFactory();
  }
}
