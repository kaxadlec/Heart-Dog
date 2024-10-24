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
public final class DatalayerModule_TileSyncFactory implements Factory<TileSync> {
  private final Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider;

  private final Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider;

  public DatalayerModule_TileSyncFactory(
      Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider,
      Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider) {
    this.wearDataLayerRegistryProvider = wearDataLayerRegistryProvider;
    this.wearDataLayerAppHelperProvider = wearDataLayerAppHelperProvider;
  }

  @Override
  public TileSync get() {
    return tileSync(wearDataLayerRegistryProvider.get(), wearDataLayerAppHelperProvider.get());
  }

  public static DatalayerModule_TileSyncFactory create(
      Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider,
      Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider) {
    return new DatalayerModule_TileSyncFactory(wearDataLayerRegistryProvider, wearDataLayerAppHelperProvider);
  }

  public static TileSync tileSync(WearDataLayerRegistry wearDataLayerRegistry,
      WearDataLayerAppHelper wearDataLayerAppHelper) {
    return Preconditions.checkNotNullFromProvides(DatalayerModule.INSTANCE.tileSync(wearDataLayerRegistry, wearDataLayerAppHelper));
  }
}
