package com.google.android.horologist.datalayer.sample.screens.info;

import androidx.lifecycle.SavedStateHandle;
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
public final class InfoScreenViewModel_Factory implements Factory<InfoScreenViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public InfoScreenViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public InfoScreenViewModel get() {
    return newInstance(savedStateHandleProvider.get());
  }

  public static InfoScreenViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new InfoScreenViewModel_Factory(savedStateHandleProvider);
  }

  public static InfoScreenViewModel newInstance(SavedStateHandle savedStateHandle) {
    return new InfoScreenViewModel(savedStateHandle);
  }
}
