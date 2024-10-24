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
public final class SampleTileService_MembersInjector implements MembersInjector<SampleTileService> {
  private final Provider<TileSync> tileSyncProvider;

  public SampleTileService_MembersInjector(Provider<TileSync> tileSyncProvider) {
    this.tileSyncProvider = tileSyncProvider;
  }

  public static MembersInjector<SampleTileService> create(Provider<TileSync> tileSyncProvider) {
    return new SampleTileService_MembersInjector(tileSyncProvider);
  }

  @Override
  public void injectMembers(SampleTileService instance) {
    injectTileSync(instance, tileSyncProvider.get());
  }

  @InjectedFieldSignature("com.google.android.horologist.datalayer.sample.SampleTileService.tileSync")
  public static void injectTileSync(SampleTileService instance, TileSync tileSync) {
    instance.tileSync = tileSync;
  }
}
