package com.google.android.horologist.datalayer.sample.di;

import com.google.android.horologist.data.WearDataLayerRegistry;
import com.google.android.horologist.datalayer.sample.TileSync;
import com.google.android.horologist.datalayer.watch.WearDataLayerAppHelper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ServiceModule_TileSyncFactory implements Factory<TileSync> {
  private final Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider;

  private final Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider;

  public ServiceModule_TileSyncFactory(
      Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider,
      Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider) {
    this.wearDataLayerRegistryProvider = wearDataLayerRegistryProvider;
    this.wearDataLayerAppHelperProvider = wearDataLayerAppHelperProvider;
  }

  @Override
  public TileSync get() {
    return tileSync(wearDataLayerRegistryProvider.get(), wearDataLayerAppHelperProvider.get());
  }

  public static ServiceModule_TileSyncFactory create(
      Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider,
      Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider) {
    return new ServiceModule_TileSyncFactory(wearDataLayerRegistryProvider, wearDataLayerAppHelperProvider);
  }

  public static TileSync tileSync(WearDataLayerRegistry wearDataLayerRegistry,
      WearDataLayerAppHelper wearDataLayerAppHelper) {
    return Preconditions.checkNotNullFromProvides(ServiceModule.INSTANCE.tileSync(wearDataLayerRegistry, wearDataLayerAppHelper));
  }
}
