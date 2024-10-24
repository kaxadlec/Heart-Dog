package com.google.android.horologist.datalayer.sample.screens.inappprompts.installtile;

import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper;
import com.google.android.horologist.datalayer.phone.ui.prompt.installtile.InstallTilePrompt;
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
public final class InstallTilePromptDemoViewModel_Factory implements Factory<InstallTilePromptDemoViewModel> {
  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  private final Provider<InstallTilePrompt> installTilePromptProvider;

  public InstallTilePromptDemoViewModel_Factory(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<InstallTilePrompt> installTilePromptProvider) {
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
    this.installTilePromptProvider = installTilePromptProvider;
  }

  @Override
  public InstallTilePromptDemoViewModel get() {
    return newInstance(phoneDataLayerAppHelperProvider.get(), installTilePromptProvider.get());
  }

  public static InstallTilePromptDemoViewModel_Factory create(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<InstallTilePrompt> installTilePromptProvider) {
    return new InstallTilePromptDemoViewModel_Factory(phoneDataLayerAppHelperProvider, installTilePromptProvider);
  }

  public static InstallTilePromptDemoViewModel newInstance(
      PhoneDataLayerAppHelper phoneDataLayerAppHelper, InstallTilePrompt installTilePrompt) {
    return new InstallTilePromptDemoViewModel(phoneDataLayerAppHelper, installTilePrompt);
  }
}
