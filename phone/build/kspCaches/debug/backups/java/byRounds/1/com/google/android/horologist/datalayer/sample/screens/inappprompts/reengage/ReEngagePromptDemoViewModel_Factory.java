package com.google.android.horologist.datalayer.sample.screens.inappprompts.reengage;

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
public final class ReEngagePromptDemoViewModel_Factory implements Factory<ReEngagePromptDemoViewModel> {
  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  private final Provider<ReEngagePrompt> reEngagePromptProvider;

  public ReEngagePromptDemoViewModel_Factory(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<ReEngagePrompt> reEngagePromptProvider) {
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
    this.reEngagePromptProvider = reEngagePromptProvider;
  }

  @Override
  public ReEngagePromptDemoViewModel get() {
    return newInstance(phoneDataLayerAppHelperProvider.get(), reEngagePromptProvider.get());
  }

  public static ReEngagePromptDemoViewModel_Factory create(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<ReEngagePrompt> reEngagePromptProvider) {
    return new ReEngagePromptDemoViewModel_Factory(phoneDataLayerAppHelperProvider, reEngagePromptProvider);
  }

  public static ReEngagePromptDemoViewModel newInstance(
      PhoneDataLayerAppHelper phoneDataLayerAppHelper, ReEngagePrompt reEngagePrompt) {
    return new ReEngagePromptDemoViewModel(phoneDataLayerAppHelper, reEngagePrompt);
  }
}
