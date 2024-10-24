package com.google.android.horologist.datalayer.sample.screens.nodes;

import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper;
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
public final class NodesViewModel_Factory implements Factory<NodesViewModel> {
  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  public NodesViewModel_Factory(Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider) {
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
  }

  @Override
  public NodesViewModel get() {
    return newInstance(phoneDataLayerAppHelperProvider.get());
  }

  public static NodesViewModel_Factory create(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider) {
    return new NodesViewModel_Factory(phoneDataLayerAppHelperProvider);
  }

  public static NodesViewModel newInstance(PhoneDataLayerAppHelper phoneDataLayerAppHelper) {
    return new NodesViewModel(phoneDataLayerAppHelper);
  }
}
