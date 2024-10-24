package com.google.android.horologist.datalayer.sample.screens.nodesactions;

import androidx.lifecycle.SavedStateHandle;
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
public final class NodeDetailsViewModel_Factory implements Factory<NodeDetailsViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider;

  public NodeDetailsViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.wearDataLayerAppHelperProvider = wearDataLayerAppHelperProvider;
  }

  @Override
  public NodeDetailsViewModel get() {
    return newInstance(savedStateHandleProvider.get(), wearDataLayerAppHelperProvider.get());
  }

  public static NodeDetailsViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider) {
    return new NodeDetailsViewModel_Factory(savedStateHandleProvider, wearDataLayerAppHelperProvider);
  }

  public static NodeDetailsViewModel newInstance(SavedStateHandle savedStateHandle,
      WearDataLayerAppHelper wearDataLayerAppHelper) {
    return new NodeDetailsViewModel(savedStateHandle, wearDataLayerAppHelper);
  }
}
