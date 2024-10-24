package com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installapp;

import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper;
import com.google.android.horologist.datalayer.phone.ui.prompt.installapp.InstallAppPrompt;
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
public final class InstallAppPromptDemoViewModel_Factory implements Factory<InstallAppPromptDemoViewModel> {
  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  private final Provider<InstallAppPrompt> installAppPromptProvider;

  public InstallAppPromptDemoViewModel_Factory(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<InstallAppPrompt> installAppPromptProvider) {
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
    this.installAppPromptProvider = installAppPromptProvider;
  }

  @Override
  public InstallAppPromptDemoViewModel get() {
    return newInstance(phoneDataLayerAppHelperProvider.get(), installAppPromptProvider.get());
  }

  public static InstallAppPromptDemoViewModel_Factory create(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<InstallAppPrompt> installAppPromptProvider) {
    return new InstallAppPromptDemoViewModel_Factory(phoneDataLayerAppHelperProvider, installAppPromptProvider);
  }

  public static InstallAppPromptDemoViewModel newInstance(
      PhoneDataLayerAppHelper phoneDataLayerAppHelper, InstallAppPrompt installAppPrompt) {
    return new InstallAppPromptDemoViewModel(phoneDataLayerAppHelper, installAppPrompt);
  }
}
