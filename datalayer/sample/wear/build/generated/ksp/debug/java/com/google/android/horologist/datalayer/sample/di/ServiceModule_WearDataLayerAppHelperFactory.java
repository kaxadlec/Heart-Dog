package com.google.android.horologist.datalayer.sample.di;

import android.content.Context;
import com.google.android.horologist.data.WearDataLayerRegistry;
import com.google.android.horologist.datalayer.watch.WearDataLayerAppHelper;
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
public final class ServiceModule_WearDataLayerAppHelperFactory implements Factory<WearDataLayerAppHelper> {
  private final Provider<Context> applicationContextProvider;

  private final Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider;

  private final Provider<CoroutineScope> coroutineScopeProvider;

  public ServiceModule_WearDataLayerAppHelperFactory(Provider<Context> applicationContextProvider,
      Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider,
      Provider<CoroutineScope> coroutineScopeProvider) {
    this.applicationContextProvider = applicationContextProvider;
    this.wearDataLayerRegistryProvider = wearDataLayerRegistryProvider;
    this.coroutineScopeProvider = coroutineScopeProvider;
  }

  @Override
  public WearDataLayerAppHelper get() {
    return wearDataLayerAppHelper(applicationContextProvider.get(), wearDataLayerRegistryProvider.get(), coroutineScopeProvider.get());
  }

  public static ServiceModule_WearDataLayerAppHelperFactory create(
      Provider<Context> applicationContextProvider,
      Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider,
      Provider<CoroutineScope> coroutineScopeProvider) {
    return new ServiceModule_WearDataLayerAppHelperFactory(applicationContextProvider, wearDataLayerRegistryProvider, coroutineScopeProvider);
  }

  public static WearDataLayerAppHelper wearDataLayerAppHelper(Context applicationContext,
      WearDataLayerRegistry wearDataLayerRegistry, CoroutineScope coroutineScope) {
    return Preconditions.checkNotNullFromProvides(ServiceModule.INSTANCE.wearDataLayerAppHelper(applicationContext, wearDataLayerRegistry, coroutineScope));
  }
}
