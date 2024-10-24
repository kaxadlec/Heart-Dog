package com.google.android.horologist.datalayer.sample.di;

import android.content.Context;
import com.google.android.horologist.data.WearDataLayerRegistry;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;

@ScopeMetadata("dagger.hilt.android.scopes.ServiceScoped")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ServiceModule_WearDataLayerRegistryFactory implements Factory<WearDataLayerRegistry> {
  private final Provider<Context> applicationContextProvider;

  private final Provider<CoroutineScope> coroutineScopeProvider;

  public ServiceModule_WearDataLayerRegistryFactory(Provider<Context> applicationContextProvider,
      Provider<CoroutineScope> coroutineScopeProvider) {
    this.applicationContextProvider = applicationContextProvider;
    this.coroutineScopeProvider = coroutineScopeProvider;
  }

  @Override
  public WearDataLayerRegistry get() {
    return wearDataLayerRegistry(applicationContextProvider.get(), coroutineScopeProvider.get());
  }

  public static ServiceModule_WearDataLayerRegistryFactory create(
      Provider<Context> applicationContextProvider,
      Provider<CoroutineScope> coroutineScopeProvider) {
    return new ServiceModule_WearDataLayerRegistryFactory(applicationContextProvider, coroutineScopeProvider);
  }

  public static WearDataLayerRegistry wearDataLayerRegistry(Context applicationContext,
      CoroutineScope coroutineScope) {
    return Preconditions.checkNotNullFromProvides(ServiceModule.INSTANCE.wearDataLayerRegistry(applicationContext, coroutineScope));
  }
}
