package com.google.android.horologist.datalayer.sample;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<TileSync> tileSyncProvider;

  public MainActivity_MembersInjector(Provider<TileSync> tileSyncProvider) {
    this.tileSyncProvider = tileSyncProvider;
  }

  public static MembersInjector<MainActivity> create(Provider<TileSync> tileSyncProvider) {
    return new MainActivity_MembersInjector(tileSyncProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectTileSync(instance, tileSyncProvider.get());
  }

  @InjectedFieldSignature("com.google.android.horologist.datalayer.sample.MainActivity.tileSync")
  public static void injectTileSync(MainActivity instance, TileSync tileSync) {
    instance.tileSync = tileSync;
  }
}
