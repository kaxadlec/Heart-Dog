package com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.reengage;

import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper;
import com.google.android.horologist.datalayer.phone.ui.prompt.reengage.ReEngagePrompt;
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
public final class ReEngageCustomPromptDemoViewModel_Factory implements Factory<ReEngageCustomPromptDemoViewModel> {
  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  private final Provider<ReEngagePrompt> reEngagePromptProvider;

  public ReEngageCustomPromptDemoViewModel_Factory(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<ReEngagePrompt> reEngagePromptProvider) {
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
    this.reEngagePromptProvider = reEngagePromptProvider;
  }

  @Override
  public ReEngageCustomPromptDemoViewModel get() {
    return newInstance(phoneDataLayerAppHelperProvider.get(), reEngagePromptProvider.get());
  }

  public static ReEngageCustomPromptDemoViewModel_Factory create(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<ReEngagePrompt> reEngagePromptProvider) {
    return new ReEngageCustomPromptDemoViewModel_Factory(phoneDataLayerAppHelperProvider, reEngagePromptProvider);
  }

  public static ReEngageCustomPromptDemoViewModel newInstance(
      PhoneDataLayerAppHelper phoneDataLayerAppHelper, ReEngagePrompt reEngagePrompt) {
    return new ReEngageCustomPromptDemoViewModel(phoneDataLayerAppHelper, reEngagePrompt);
  }
}
