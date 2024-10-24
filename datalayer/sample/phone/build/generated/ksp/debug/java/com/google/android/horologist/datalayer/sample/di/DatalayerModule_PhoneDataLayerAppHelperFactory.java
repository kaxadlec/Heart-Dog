package com.google.android.horologist.datalayer.sample.di;

import android.content.Context;
import com.google.android.horologist.data.WearDataLayerRegistry;
import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatalayerModule_PhoneDataLayerAppHelperFactory implements Factory<PhoneDataLayerAppHelper> {
  private final Provider<Context> applicationContextProvider;

  private final Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider;

  public DatalayerModule_PhoneDataLayerAppHelperFactory(
      Provider<Context> applicationContextProvider,
      Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider) {
    this.applicationContextProvider = applicationContextProvider;
    this.wearDataLayerRegistryProvider = wearDataLayerRegistryProvider;
  }

  @Override
  public PhoneDataLayerAppHelper get() {
    return phoneDataLayerAppHelper(applicationContextProvider.get(), wearDataLayerRegistryProvider.get());
  }

  public static DatalayerModule_PhoneDataLayerAppHelperFactory create(
      Provider<Context> applicationContextProvider,
      Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider) {
    return new DatalayerModule_PhoneDataLayerAppHelperFactory(applicationContextProvider, wearDataLayerRegistryProvider);
  }

  public static PhoneDataLayerAppHelper phoneDataLayerAppHelper(Context applicationContext,
      WearDataLayerRegistry wearDataLayerRegistry) {
    return Preconditions.checkNotNullFromProvides(DatalayerModule.INSTANCE.phoneDataLayerAppHelper(applicationContext, wearDataLayerRegistry));
  }
}
