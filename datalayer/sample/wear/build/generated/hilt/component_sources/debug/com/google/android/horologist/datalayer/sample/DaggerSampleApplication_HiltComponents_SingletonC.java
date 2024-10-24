package com.google.android.horologist.datalayer.sample;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.android.horologist.data.WearDataLayerRegistry;
import com.google.android.horologist.datalayer.sample.di.DatalayerModule_CoroutineScopeFactory;
import com.google.android.horologist.datalayer.sample.di.DatalayerModule_CounterFlowFactory;
import com.google.android.horologist.datalayer.sample.di.DatalayerModule_CounterServiceFactory;
import com.google.android.horologist.datalayer.sample.di.DatalayerModule_TileSyncFactory;
import com.google.android.horologist.datalayer.sample.di.DatalayerModule_WearDataLayerAppHelperFactory;
import com.google.android.horologist.datalayer.sample.di.DatalayerModule_WearDataLayerRegistryFactory;
import com.google.android.horologist.datalayer.sample.di.ServiceModule_CoroutineScopeFactory;
import com.google.android.horologist.datalayer.sample.di.ServiceModule_TileSyncFactory;
import com.google.android.horologist.datalayer.sample.di.ServiceModule_WearDataLayerAppHelperFactory;
import com.google.android.horologist.datalayer.sample.di.ServiceModule_WearDataLayerRegistryFactory;
import com.google.android.horologist.datalayer.sample.screens.datalayer.DataLayerViewModel;
import com.google.android.horologist.datalayer.sample.screens.datalayer.DataLayerViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.info.InfoScreenViewModel;
import com.google.android.horologist.datalayer.sample.screens.info.InfoScreenViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.nodes.DataLayerNodesViewModel;
import com.google.android.horologist.datalayer.sample.screens.nodes.DataLayerNodesViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.nodes.SampleDataService;
import com.google.android.horologist.datalayer.sample.screens.nodes.SampleDataService_MembersInjector;
import com.google.android.horologist.datalayer.sample.screens.nodesactions.NodeDetailsViewModel;
import com.google.android.horologist.datalayer.sample.screens.nodesactions.NodeDetailsViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.nodesactions.NodesActionViewModel;
import com.google.android.horologist.datalayer.sample.screens.nodesactions.NodesActionViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerViewModel;
import com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.tracking.TrackingScreenViewModel;
import com.google.android.horologist.datalayer.sample.screens.tracking.TrackingScreenViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.shared.grpc.CounterServiceGrpcKt;
import com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto;
import com.google.android.horologist.datalayer.watch.WearDataLayerAppHelper;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

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
public final class DaggerSampleApplication_HiltComponents_SingletonC {
  private DaggerSampleApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public SampleApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements SampleApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public SampleApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements SampleApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public SampleApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements SampleApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public SampleApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements SampleApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public SampleApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements SampleApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public SampleApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements SampleApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public SampleApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements SampleApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public SampleApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends SampleApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends SampleApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends SampleApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends SampleApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
      injectMainActivity2(arg0);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>builderWithExpectedSize(7).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_nodes_DataLayerNodesViewModel, DataLayerNodesViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_datalayer_DataLayerViewModel, DataLayerViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_info_InfoScreenViewModel, InfoScreenViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_nodesactions_NodeDetailsViewModel, NodeDetailsViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_nodesactions_NodesActionViewModel, NodesActionViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_nodeslistener_NodesListenerViewModel, NodesListenerViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_tracking_TrackingScreenViewModel, TrackingScreenViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @CanIgnoreReturnValue
    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectTileSync(instance, activityRetainedCImpl.tileSyncProvider.get());
      return instance;
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_google_android_horologist_datalayer_sample_screens_datalayer_DataLayerViewModel = "com.google.android.horologist.datalayer.sample.screens.datalayer.DataLayerViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_nodesactions_NodesActionViewModel = "com.google.android.horologist.datalayer.sample.screens.nodesactions.NodesActionViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_nodeslistener_NodesListenerViewModel = "com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_nodes_DataLayerNodesViewModel = "com.google.android.horologist.datalayer.sample.screens.nodes.DataLayerNodesViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_nodesactions_NodeDetailsViewModel = "com.google.android.horologist.datalayer.sample.screens.nodesactions.NodeDetailsViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_tracking_TrackingScreenViewModel = "com.google.android.horologist.datalayer.sample.screens.tracking.TrackingScreenViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_info_InfoScreenViewModel = "com.google.android.horologist.datalayer.sample.screens.info.InfoScreenViewModel";

      @KeepFieldType
      DataLayerViewModel com_google_android_horologist_datalayer_sample_screens_datalayer_DataLayerViewModel2;

      @KeepFieldType
      NodesActionViewModel com_google_android_horologist_datalayer_sample_screens_nodesactions_NodesActionViewModel2;

      @KeepFieldType
      NodesListenerViewModel com_google_android_horologist_datalayer_sample_screens_nodeslistener_NodesListenerViewModel2;

      @KeepFieldType
      DataLayerNodesViewModel com_google_android_horologist_datalayer_sample_screens_nodes_DataLayerNodesViewModel2;

      @KeepFieldType
      NodeDetailsViewModel com_google_android_horologist_datalayer_sample_screens_nodesactions_NodeDetailsViewModel2;

      @KeepFieldType
      TrackingScreenViewModel com_google_android_horologist_datalayer_sample_screens_tracking_TrackingScreenViewModel2;

      @KeepFieldType
      InfoScreenViewModel com_google_android_horologist_datalayer_sample_screens_info_InfoScreenViewModel2;
    }
  }

  private static final class ViewModelCImpl extends SampleApplication_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<DataLayerNodesViewModel> dataLayerNodesViewModelProvider;

    private Provider<DataLayerViewModel> dataLayerViewModelProvider;

    private Provider<InfoScreenViewModel> infoScreenViewModelProvider;

    private Provider<NodeDetailsViewModel> nodeDetailsViewModelProvider;

    private Provider<NodesActionViewModel> nodesActionViewModelProvider;

    private Provider<NodesListenerViewModel> nodesListenerViewModelProvider;

    private Provider<TrackingScreenViewModel> trackingScreenViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.dataLayerNodesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.dataLayerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.infoScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.nodeDetailsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.nodesActionViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.nodesListenerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.trackingScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(7).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_nodes_DataLayerNodesViewModel, ((Provider) dataLayerNodesViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_datalayer_DataLayerViewModel, ((Provider) dataLayerViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_info_InfoScreenViewModel, ((Provider) infoScreenViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_nodesactions_NodeDetailsViewModel, ((Provider) nodeDetailsViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_nodesactions_NodesActionViewModel, ((Provider) nodesActionViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_nodeslistener_NodesListenerViewModel, ((Provider) nodesListenerViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_tracking_TrackingScreenViewModel, ((Provider) trackingScreenViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_google_android_horologist_datalayer_sample_screens_nodesactions_NodeDetailsViewModel = "com.google.android.horologist.datalayer.sample.screens.nodesactions.NodeDetailsViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_nodes_DataLayerNodesViewModel = "com.google.android.horologist.datalayer.sample.screens.nodes.DataLayerNodesViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_nodesactions_NodesActionViewModel = "com.google.android.horologist.datalayer.sample.screens.nodesactions.NodesActionViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_nodeslistener_NodesListenerViewModel = "com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_tracking_TrackingScreenViewModel = "com.google.android.horologist.datalayer.sample.screens.tracking.TrackingScreenViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_datalayer_DataLayerViewModel = "com.google.android.horologist.datalayer.sample.screens.datalayer.DataLayerViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_info_InfoScreenViewModel = "com.google.android.horologist.datalayer.sample.screens.info.InfoScreenViewModel";

      @KeepFieldType
      NodeDetailsViewModel com_google_android_horologist_datalayer_sample_screens_nodesactions_NodeDetailsViewModel2;

      @KeepFieldType
      DataLayerNodesViewModel com_google_android_horologist_datalayer_sample_screens_nodes_DataLayerNodesViewModel2;

      @KeepFieldType
      NodesActionViewModel com_google_android_horologist_datalayer_sample_screens_nodesactions_NodesActionViewModel2;

      @KeepFieldType
      NodesListenerViewModel com_google_android_horologist_datalayer_sample_screens_nodeslistener_NodesListenerViewModel2;

      @KeepFieldType
      TrackingScreenViewModel com_google_android_horologist_datalayer_sample_screens_tracking_TrackingScreenViewModel2;

      @KeepFieldType
      DataLayerViewModel com_google_android_horologist_datalayer_sample_screens_datalayer_DataLayerViewModel2;

      @KeepFieldType
      InfoScreenViewModel com_google_android_horologist_datalayer_sample_screens_info_InfoScreenViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.google.android.horologist.datalayer.sample.screens.nodes.DataLayerNodesViewModel 
          return (T) new DataLayerNodesViewModel(activityRetainedCImpl.wearDataLayerRegistryProvider.get());

          case 1: // com.google.android.horologist.datalayer.sample.screens.datalayer.DataLayerViewModel 
          return (T) new DataLayerViewModel(activityRetainedCImpl.counterServiceProvider.get(), activityRetainedCImpl.counterFlowProvider.get());

          case 2: // com.google.android.horologist.datalayer.sample.screens.info.InfoScreenViewModel 
          return (T) new InfoScreenViewModel(viewModelCImpl.savedStateHandle);

          case 3: // com.google.android.horologist.datalayer.sample.screens.nodesactions.NodeDetailsViewModel 
          return (T) new NodeDetailsViewModel(viewModelCImpl.savedStateHandle, activityRetainedCImpl.wearDataLayerAppHelperProvider.get());

          case 4: // com.google.android.horologist.datalayer.sample.screens.nodesactions.NodesActionViewModel 
          return (T) new NodesActionViewModel(activityRetainedCImpl.wearDataLayerAppHelperProvider.get());

          case 5: // com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerViewModel 
          return (T) new NodesListenerViewModel(activityRetainedCImpl.wearDataLayerAppHelperProvider.get());

          case 6: // com.google.android.horologist.datalayer.sample.screens.tracking.TrackingScreenViewModel 
          return (T) new TrackingScreenViewModel(activityRetainedCImpl.wearDataLayerAppHelperProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends SampleApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private Provider<CoroutineScope> coroutineScopeProvider;

    private Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider;

    private Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider;

    private Provider<TileSync> tileSyncProvider;

    private Provider<CounterServiceGrpcKt.CounterServiceCoroutineStub> counterServiceProvider;

    private Provider<Flow<GrpcDemoProto.CounterValue>> counterFlowProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
      this.coroutineScopeProvider = DoubleCheck.provider(new SwitchingProvider<CoroutineScope>(singletonCImpl, activityRetainedCImpl, 3));
      this.wearDataLayerRegistryProvider = DoubleCheck.provider(new SwitchingProvider<WearDataLayerRegistry>(singletonCImpl, activityRetainedCImpl, 2));
      this.wearDataLayerAppHelperProvider = DoubleCheck.provider(new SwitchingProvider<WearDataLayerAppHelper>(singletonCImpl, activityRetainedCImpl, 4));
      this.tileSyncProvider = DoubleCheck.provider(new SwitchingProvider<TileSync>(singletonCImpl, activityRetainedCImpl, 1));
      this.counterServiceProvider = DoubleCheck.provider(new SwitchingProvider<CounterServiceGrpcKt.CounterServiceCoroutineStub>(singletonCImpl, activityRetainedCImpl, 5));
      this.counterFlowProvider = DoubleCheck.provider(new SwitchingProvider<Flow<GrpcDemoProto.CounterValue>>(singletonCImpl, activityRetainedCImpl, 6));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          case 1: // com.google.android.horologist.datalayer.sample.TileSync 
          return (T) DatalayerModule_TileSyncFactory.tileSync(activityRetainedCImpl.wearDataLayerRegistryProvider.get(), activityRetainedCImpl.wearDataLayerAppHelperProvider.get());

          case 2: // com.google.android.horologist.data.WearDataLayerRegistry 
          return (T) DatalayerModule_WearDataLayerRegistryFactory.wearDataLayerRegistry(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), activityRetainedCImpl.coroutineScopeProvider.get());

          case 3: // kotlinx.coroutines.CoroutineScope 
          return (T) DatalayerModule_CoroutineScopeFactory.coroutineScope(activityRetainedCImpl.provideActivityRetainedLifecycleProvider.get());

          case 4: // com.google.android.horologist.datalayer.watch.WearDataLayerAppHelper 
          return (T) DatalayerModule_WearDataLayerAppHelperFactory.wearDataLayerAppHelper(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), activityRetainedCImpl.wearDataLayerRegistryProvider.get(), activityRetainedCImpl.coroutineScopeProvider.get());

          case 5: // com.google.android.horologist.datalayer.sample.shared.grpc.CounterServiceGrpcKt.CounterServiceCoroutineStub 
          return (T) DatalayerModule_CounterServiceFactory.counterService(activityRetainedCImpl.wearDataLayerRegistryProvider.get(), activityRetainedCImpl.coroutineScopeProvider.get());

          case 6: // kotlinx.coroutines.flow.Flow<com.google.android.horologist.datalayer.sample.shared.grpc.GrpcDemoProto.CounterValue> 
          return (T) DatalayerModule_CounterFlowFactory.counterFlow(activityRetainedCImpl.wearDataLayerRegistryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends SampleApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private Provider<CoroutineScope> coroutineScopeProvider;

    private Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider;

    private Provider<WearDataLayerAppHelper> wearDataLayerAppHelperProvider;

    private Provider<TileSync> tileSyncProvider;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(serviceParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final Service serviceParam) {
      this.coroutineScopeProvider = DoubleCheck.provider(new SwitchingProvider<CoroutineScope>(singletonCImpl, serviceCImpl, 2));
      this.wearDataLayerRegistryProvider = DoubleCheck.provider(new SwitchingProvider<WearDataLayerRegistry>(singletonCImpl, serviceCImpl, 1));
      this.wearDataLayerAppHelperProvider = DoubleCheck.provider(new SwitchingProvider<WearDataLayerAppHelper>(singletonCImpl, serviceCImpl, 3));
      this.tileSyncProvider = DoubleCheck.provider(new SwitchingProvider<TileSync>(singletonCImpl, serviceCImpl, 0));
    }

    @Override
    public void injectSampleTileService(SampleTileService arg0) {
      injectSampleTileService2(arg0);
    }

    @Override
    public void injectSampleDataService(SampleDataService arg0) {
      injectSampleDataService2(arg0);
    }

    @CanIgnoreReturnValue
    private SampleTileService injectSampleTileService2(SampleTileService instance) {
      SampleTileService_MembersInjector.injectTileSync(instance, tileSyncProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private SampleDataService injectSampleDataService2(SampleDataService instance2) {
      SampleDataService_MembersInjector.injectRegistry(instance2, wearDataLayerRegistryProvider.get());
      return instance2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ServiceCImpl serviceCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ServiceCImpl serviceCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.serviceCImpl = serviceCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.google.android.horologist.datalayer.sample.TileSync 
          return (T) ServiceModule_TileSyncFactory.tileSync(serviceCImpl.wearDataLayerRegistryProvider.get(), serviceCImpl.wearDataLayerAppHelperProvider.get());

          case 1: // com.google.android.horologist.data.WearDataLayerRegistry 
          return (T) ServiceModule_WearDataLayerRegistryFactory.wearDataLayerRegistry(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), serviceCImpl.coroutineScopeProvider.get());

          case 2: // kotlinx.coroutines.CoroutineScope 
          return (T) ServiceModule_CoroutineScopeFactory.coroutineScope();

          case 3: // com.google.android.horologist.datalayer.watch.WearDataLayerAppHelper 
          return (T) ServiceModule_WearDataLayerAppHelperFactory.wearDataLayerAppHelper(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), serviceCImpl.wearDataLayerRegistryProvider.get(), serviceCImpl.coroutineScopeProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class SingletonCImpl extends SampleApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;

    }

    @Override
    public void injectSampleApplication(SampleApplication arg0) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }
  }
}
