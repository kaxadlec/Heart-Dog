package com.google.android.horologist.datalayer.sample.screens.tracking;

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
public final class TrackingScreenViewModel_Factory implements Factory<TrackingScreenViewModel> {
  private final Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider;

  public TrackingScreenViewModel_Factory(
      Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider) {
    this.wearDataLayerAppHelperProvider = wearDataLayerAppHelperProvider;
  }

  @Override
  public TrackingScreenViewModel get() {
    return newInstance(wearDataLayerAppHelperProvider.get());
  }

  public static TrackingScreenViewModel_Factory create(
      Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider) {
    return new TrackingScreenViewModel_Factory(wearDataLayerAppHelperProvider);
  }

  public static TrackingScreenViewModel newInstance(WearDataLayerAppHelper wearDataLayerAppHelper) {
    return new TrackingScreenViewModel(wearDataLayerAppHelper);
  }
}
