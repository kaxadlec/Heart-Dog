package com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installapp;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.internal.lifecycle.HiltViewModelMap.KeySet")
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
public final class InstallAppPromptDemoViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {
  @Override
  public Boolean get() {
    return provide();
  }

  public static InstallAppPromptDemoViewModel_HiltModules_KeyModule_ProvideFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static boolean provide() {
    return InstallAppPromptDemoViewModel_HiltModules.KeyModule.provide();
  }

  private static final class InstanceHolder {
    private static final InstallAppPromptDemoViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new InstallAppPromptDemoViewModel_HiltModules_KeyModule_ProvideFactory();
  }
}
