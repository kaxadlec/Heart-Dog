package com.google.android.horologist.datalayer.sample.screens.counter;

import com.google.android.horologist.data.WearDataLayerRegistry;
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
public final class CounterScreenViewModel_Factory implements Factory<CounterScreenViewModel> {
  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  private final Provider<WearDataLayerRegistry> registryProvider;

  public CounterScreenViewModel_Factory(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<WearDataLayerRegistry> registryProvider) {
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
    this.registryProvider = registryProvider;
  }

  @Override
  public CounterScreenViewModel get() {
    return newInstance(phoneDataLayerAppHelperProvider.get(), registryProvider.get());
  }

  public static CounterScreenViewModel_Factory create(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<WearDataLayerRegistry> registryProvider) {
    return new CounterScreenViewModel_Factory(phoneDataLayerAppHelperProvider, registryProvider);
  }

  public static CounterScreenViewModel newInstance(PhoneDataLayerAppHelper phoneDataLayerAppHelper,
      WearDataLayerRegistry registry) {
    return new CounterScreenViewModel(phoneDataLayerAppHelper, registry);
  }
}
