package com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.signin;

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
public final class SignInCustomPromptDemoViewModel_Factory implements Factory<SignInCustomPromptDemoViewModel> {
  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  private final Provider<SignInPrompt> signInCustomPromptProvider;

  public SignInCustomPromptDemoViewModel_Factory(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<SignInPrompt> signInCustomPromptProvider) {
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
    this.signInCustomPromptProvider = signInCustomPromptProvider;
  }

  @Override
  public SignInCustomPromptDemoViewModel get() {
    return newInstance(phoneDataLayerAppHelperProvider.get(), signInCustomPromptProvider.get());
  }

  public static SignInCustomPromptDemoViewModel_Factory create(
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider,
      Provider<SignInPrompt> signInCustomPromptProvider) {
    return new SignInCustomPromptDemoViewModel_Factory(phoneDataLayerAppHelperProvider, signInCustomPromptProvider);
  }

  public static SignInCustomPromptDemoViewModel newInstance(
      PhoneDataLayerAppHelper phoneDataLayerAppHelper, SignInPrompt signInCustomPrompt) {
    return new SignInCustomPromptDemoViewModel(phoneDataLayerAppHelper, signInCustomPrompt);
  }
}
