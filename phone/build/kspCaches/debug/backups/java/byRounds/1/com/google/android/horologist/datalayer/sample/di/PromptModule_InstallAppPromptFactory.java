package com.google.android.horologist.datalayer.sample.di;

import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper;
import com.google.android.horologist.datalayer.phone.ui.prompt.installapp.InstallAppPrompt;
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
public final class PromptModule_InstallAppPromptFactory implements Factory<InstallAppPrompt> {
  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  public PromptModule_InstallAppPromptFactory(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider) {
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
  }

  @Override
  public InstallAppPrompt get() {
    return installAppPrompt(phoneDataLayerAppHelperProvider.get());
  }

  public static PromptModule_InstallAppPromptFactory create(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider) {
    return new PromptModule_InstallAppPromptFactory(phoneDataLayerAppHelperProvider);
  }

  public static InstallAppPrompt installAppPrompt(PhoneDataLayerAppHelper phoneDataLayerAppHelper) {
    return Preconditions.checkNotNullFromProvides(PromptModule.INSTANCE.installAppPrompt(phoneDataLayerAppHelper));
  }
}
