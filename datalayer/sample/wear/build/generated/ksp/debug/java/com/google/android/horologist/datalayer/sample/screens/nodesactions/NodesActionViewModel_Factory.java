package com.google.android.horologist.datalayer.sample.screens.nodesactions;

import com.google.android.horologist.datalayer.watch.WearDataLayerAppHelper;
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
public final class NodesActionViewModel_Factory implements Factory<NodesActionViewModel> {
  private final Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider;

  public NodesActionViewModel_Factory(
      Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider) {
    this.wearDataLayerAppHelperProvider = wearDataLayerAppHelperProvider;
  }

  @Override
  public NodesActionViewModel get() {
    return newInstance(wearDataLayerAppHelperProvider.get());
  }

  public static NodesActionViewModel_Factory create(
      Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider) {
    return new NodesActionViewModel_Factory(wearDataLayerAppHelperProvider);
  }

  public static NodesActionViewModel newInstance(WearDataLayerAppHelper wearDataLayerAppHelper) {
    return new NodesActionViewModel(wearDataLayerAppHelper);
  }
}
