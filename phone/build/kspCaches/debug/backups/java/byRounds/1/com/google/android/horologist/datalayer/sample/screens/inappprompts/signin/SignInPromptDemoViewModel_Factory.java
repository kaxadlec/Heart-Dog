package com.google.android.horologist.datalayer.sample.screens.inappprompts.signin;

import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper;
import com.google.android.horologist.datalayer.phone.ui.prompt.signin.SignInPrompt;
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
public final class SignInPromptDemoViewModel_Factory implements Factory<SignInPromptDemoViewModel> {
  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  private final Provider<SignInPrompt> signInPromptProvider;

  public SignInPromptDemoViewModel_Factory(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<SignInPrompt> signInPromptProvider) {
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
    this.signInPromptProvider = signInPromptProvider;
  }

  @Override
  public SignInPromptDemoViewModel get() {
    return newInstance(phoneDataLayerAppHelperProvider.get(), signInPromptProvider.get());
  }

  public static SignInPromptDemoViewModel_Factory create(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<SignInPrompt> signInPromptProvider) {
    return new SignInPromptDemoViewModel_Factory(phoneDataLayerAppHelperProvider, signInPromptProvider);
  }

  public static SignInPromptDemoViewModel newInstance(
      PhoneDataLayerAppHelper phoneDataLayerAppHelper, SignInPrompt signInPrompt) {
    return new SignInPromptDemoViewModel(phoneDataLayerAppHelper, signInPrompt);
  }
}
