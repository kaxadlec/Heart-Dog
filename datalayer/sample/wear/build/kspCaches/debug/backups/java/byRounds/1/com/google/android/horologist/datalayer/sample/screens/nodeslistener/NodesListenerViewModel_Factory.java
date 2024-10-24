package com.google.android.horologist.datalayer.sample.screens.nodeslistener;

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
public final class NodesListenerViewModel_Factory implements Factory<NodesListenerViewModel> {
  private final Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider;

  public NodesListenerViewModel_Factory(
      Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider) {
    this.wearDataLayerAppHelperProvider = wearDataLayerAppHelperProvider;
  }

  @Override
  public NodesListenerViewModel get() {
    return newInstance(wearDataLayerAppHelperProvider.get());
  }

  public static NodesListenerViewModel_Factory create(
      Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider) {
    return new NodesListenerViewModel_Factory(wearDataLayerAppHelperProvider);
  }

  public static NodesListenerViewModel newInstance(WearDataLayerAppHelper wearDataLayerAppHelper) {
    return new NodesListenerViewModel(wearDataLayerAppHelper);
  }
}
