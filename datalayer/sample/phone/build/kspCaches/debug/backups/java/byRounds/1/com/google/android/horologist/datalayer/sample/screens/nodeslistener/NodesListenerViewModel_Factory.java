package com.google.android.horologist.datalayer.sample.screens.nodeslistener;

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
public final class NodesListenerViewModel_Factory implements Factory<NodesListenerViewModel> {
  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  public NodesListenerViewModel_Factory(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider) {
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
  }

  @Override
  public NodesListenerViewModel get() {
    return newInstance(phoneDataLayerAppHelperProvider.get());
  }

  public static NodesListenerViewModel_Factory create(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider) {
    return new NodesListenerViewModel_Factory(phoneDataLayerAppHelperProvider);
  }

  public static NodesListenerViewModel newInstance(
      PhoneDataLayerAppHelper phoneDataLayerAppHelper) {
    return new NodesListenerViewModel(phoneDataLayerAppHelper);
  }
}
