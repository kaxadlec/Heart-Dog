package com.google.android.horologist.datalayer.sample.screens.nodes;

import com.google.android.horologist.data.WearDataLayerRegistry;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class SampleDataService_MembersInjector implements MembersInjector<SampleDataService> {
  private final Provider<WearDataLayerRegistry> registryProvider;

  public SampleDataService_MembersInjector(Provider<WearDataLayerRegistry> registryProvider) {
    this.registryProvider = registryProvider;
  }

  public static MembersInjector<SampleDataService> create(
      Provider<WearDataLayerRegistry> registryProvider) {
    return new SampleDataService_MembersInjector(registryProvider);
  }

  @Override
  public void injectMembers(SampleDataService instance) {
    injectRegistry(instance, registryProvider.get());
  }

  @InjectedFieldSignature("com.google.android.horologist.datalayer.sample.screens.nodes.SampleDataService.registry")
  public static void injectRegistry(SampleDataService instance, WearDataLayerRegistry registry) {
    instance.registry = registry;
  }
}
