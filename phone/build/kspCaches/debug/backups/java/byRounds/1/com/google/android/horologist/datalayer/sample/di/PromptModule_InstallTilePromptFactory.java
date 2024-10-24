package com.google.android.horologist.datalayer.sample.di;

import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper;
import com.google.android.horologist.datalayer.phone.ui.prompt.installtile.InstallTilePrompt;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class PromptModule_InstallTilePromptFactory implements Factory<InstallTilePrompt> {
  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  public PromptModule_InstallTilePromptFactory(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider) {
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
  }

  @Override
  public InstallTilePrompt get() {
    return installTilePrompt(phoneDataLayerAppHelperProvider.get());
  }

  public static PromptModule_InstallTilePromptFactory create(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider) {
    return new PromptModule_InstallTilePromptFactory(phoneDataLayerAppHelperProvider);
  }

  public static InstallTilePrompt installTilePrompt(
      PhoneDataLayerAppHelper phoneDataLayerAppHelper) {
    return Preconditions.checkNotNullFromProvides(PromptModule.INSTANCE.installTilePrompt(phoneDataLayerAppHelper));
  }
}
