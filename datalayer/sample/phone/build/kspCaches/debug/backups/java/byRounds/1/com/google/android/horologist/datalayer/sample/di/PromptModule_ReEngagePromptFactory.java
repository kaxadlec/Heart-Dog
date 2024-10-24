package com.google.android.horologist.datalayer.sample.di;

import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper;
import com.google.android.horologist.datalayer.phone.ui.prompt.reengage.ReEngagePrompt;
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
public final class PromptModule_ReEngagePromptFactory implements Factory<ReEngagePrompt> {
  private final Provider<CoroutineScope> coroutineScopeProvider;

  private final Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

  public PromptModule_ReEngagePromptFactory(Provider<CoroutineScope> coroutineScopeProvider,
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider) {
    this.coroutineScopeProvider = coroutineScopeProvider;
    this.phoneDataLayerAppHelperProvider = phoneDataLayerAppHelperProvider;
  }

  @Override
  public ReEngagePrompt get() {
    return reEngagePrompt(coroutineScopeProvider.get(), phoneDataLayerAppHelperProvider.get());
  }

  public static PromptModule_ReEngagePromptFactory create(
      Provider<CoroutineScope> coroutineScopeProvider,
      Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider) {
    return new PromptModule_ReEngagePromptFactory(coroutineScopeProvider, phoneDataLayerAppHelperProvider);
  }

  public static ReEngagePrompt reEngagePrompt(CoroutineScope coroutineScope,
      PhoneDataLayerAppHelper phoneDataLayerAppHelper) {
    return Preconditions.checkNotNullFromProvides(PromptModule.INSTANCE.reEngagePrompt(coroutineScope, phoneDataLayerAppHelper));
  }
}
