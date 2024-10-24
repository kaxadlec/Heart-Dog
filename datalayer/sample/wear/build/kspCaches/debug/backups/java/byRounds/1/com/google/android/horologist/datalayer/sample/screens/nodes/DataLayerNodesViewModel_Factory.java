package com.google.android.horologist.datalayer.sample.screens.nodes;

import com.google.android.horologist.data.WearDataLayerRegistry;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DataLayerNodesViewModel_Factory implements Factory<DataLayerNodesViewModel> {
  private final Provider<WearDataLayerRegistry> registryProvider;

  public DataLayerNodesViewModel_Factory(Provider<WearDataLayerRegistry> registryProvider) {
    this.registryProvider = registryProvider;
  }

  @Override
  public DataLayerNodesViewModel get() {
    return newInstance(registryProvider.get());
  }

  public static DataLayerNodesViewModel_Factory create(
      Provider<WearDataLayerRegistry> registryProvider) {
    return new DataLayerNodesViewModel_Factory(registryProvider);
  }

  public static DataLayerNodesViewModel newInstance(WearDataLayerRegistry registry) {
    return new DataLayerNodesViewModel(registry);
  }
}
