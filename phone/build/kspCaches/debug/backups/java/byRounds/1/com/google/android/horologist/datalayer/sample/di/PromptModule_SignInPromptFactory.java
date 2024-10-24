package com.google.android.horologist.datalayer.sample.di;

import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper;
import com.google.android.horologist.datalayer.phone.ui.prompt.signin.SignInPrompt;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;

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
public final class PromptModule_SignInPromptFactory implements Factory<SignInPrompt> {
  private final Provider<CoroutineScope> coroutineScopeProvider;

  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  public PromptModule_SignInPromptFactory(Provider<CoroutineScope> coroutineScopeProvider,
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider) {
    this.coroutineScopeProvider = coroutineScopeProvider;
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
  }

  @Override
  public SignInPrompt get() {
    return signInPrompt(coroutineScopeProvider.get(), phoneDataLayerAppHelperProvider.get());
  }

  public static PromptModule_SignInPromptFactory create(
      Provider<CoroutineScope> coroutineScopeProvider,
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider) {
    return new PromptModule_SignInPromptFactory(coroutineScopeProvider, phoneDataLayerAppHelperProvider);
  }

  public static SignInPrompt signInPrompt(CoroutineScope coroutineScope,
      PhoneDataLayerAppHelper phoneDataLayerAppHelper) {
    return Preconditions.checkNotNullFromProvides(PromptModule.INSTANCE.signInPrompt(coroutineScope, phoneDataLayerAppHelper));
  }
}
