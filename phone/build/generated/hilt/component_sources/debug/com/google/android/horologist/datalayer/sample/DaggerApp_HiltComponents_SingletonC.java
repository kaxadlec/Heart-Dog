package com.google.android.horologist.datalayer.sample;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.android.horologist.data.WearDataLayerRegistry;
import com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper;
import com.google.android.horologist.datalayer.phone.ui.prompt.installapp.InstallAppPrompt;
import com.google.android.horologist.datalayer.phone.ui.prompt.installtile.InstallTilePrompt;
import com.google.android.horologist.datalayer.phone.ui.prompt.reengage.ReEngagePrompt;
import com.google.android.horologist.datalayer.phone.ui.prompt.signin.SignInPrompt;
import com.google.android.horologist.datalayer.sample.di.DatalayerModule_PhoneDataLayerAppHelperFactory;
import com.google.android.horologist.datalayer.sample.di.DatalayerModule_ProvidesCoroutineScopeFactory;
import com.google.android.horologist.datalayer.sample.di.DatalayerModule_WearDataLayerRegistryFactory;
import com.google.android.horologist.datalayer.sample.di.PromptModule_InstallAppPromptFactory;
import com.google.android.horologist.datalayer.sample.di.PromptModule_InstallTilePromptFactory;
import com.google.android.horologist.datalayer.sample.di.PromptModule_ReEngagePromptFactory;
import com.google.android.horologist.datalayer.sample.di.PromptModule_SignInPromptFactory;
import com.google.android.horologist.datalayer.sample.screens.counter.CounterScreenViewModel;
import com.google.android.horologist.datalayer.sample.screens.counter.CounterScreenViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installapp.InstallAppPromptDemoViewModel;
import com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installapp.InstallAppPromptDemoViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installtile.InstallTilePromptDemoViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.reengage.ReEngageCustomPromptDemoViewModel;
import com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.reengage.ReEngageCustomPromptDemoViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.signin.SignInCustomPromptDemoViewModel;
import com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.signin.SignInCustomPromptDemoViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.inappprompts.installtile.InstallTilePromptDemoViewModel;
import com.google.android.horologist.datalayer.sample.screens.inappprompts.reengage.ReEngagePromptDemoViewModel;
import com.google.android.horologist.datalayer.sample.screens.inappprompts.reengage.ReEngagePromptDemoViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.inappprompts.signin.SignInPromptDemoViewModel;
import com.google.android.horologist.datalayer.sample.screens.inappprompts.signin.SignInPromptDemoViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.nodes.NodesViewModel;
import com.google.android.horologist.datalayer.sample.screens.nodes.NodesViewModel_HiltModules;
import com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerViewModel;
import com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerViewModel_HiltModules;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
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
public final class DaggerApp_HiltComponents_SingletonC {
  private DaggerApp_HiltComponents_SingletonC() {
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

    public App_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements App_HiltComponents.ActivityRetainedC.Builder {
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
    public App_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements App_HiltComponents.ActivityC.Builder {
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
    public App_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements App_HiltComponents.FragmentC.Builder {
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
    public App_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements App_HiltComponents.ViewWithFragmentC.Builder {
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
    public App_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements App_HiltComponents.ViewC.Builder {
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
    public App_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements App_HiltComponents.ViewModelC.Builder {
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
    public App_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements App_HiltComponents.ServiceC.Builder {
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
    public App_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends App_HiltComponents.ViewWithFragmentC {
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

  private static final class FragmentCImpl extends App_HiltComponents.FragmentC {
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

  private static final class ViewCImpl extends App_HiltComponents.ViewC {
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

  private static final class ActivityCImpl extends App_HiltComponents.ActivityC {
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
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>builderWithExpectedSize(11).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_counter_CounterScreenViewModel, CounterScreenViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_installapp_InstallAppPromptDemoViewModel, InstallAppPromptDemoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_installapp_InstallAppPromptDemoViewModel, com.google.android.horologist.datalayer.sample.screens.inappprompts.installapp.InstallAppPromptDemoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_installtile_InstallTilePromptDemoViewModel, InstallTilePromptDemoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_installtile_InstallTilePromptDemoViewModel, com.google.android.horologist.datalayer.sample.screens.inappprompts.installtile.InstallTilePromptDemoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_nodeslistener_NodesListenerViewModel, NodesListenerViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_nodes_NodesViewModel, NodesViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_reengage_ReEngageCustomPromptDemoViewModel, ReEngageCustomPromptDemoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_reengage_ReEngagePromptDemoViewModel, ReEngagePromptDemoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_signin_SignInCustomPromptDemoViewModel, SignInCustomPromptDemoViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_signin_SignInPromptDemoViewModel, SignInPromptDemoViewModel_HiltModules.KeyModule.provide()).build());
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

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_installapp_InstallAppPromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installapp.InstallAppPromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_installapp_InstallAppPromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.installapp.InstallAppPromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_nodes_NodesViewModel = "com.google.android.horologist.datalayer.sample.screens.nodes.NodesViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_installtile_InstallTilePromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.installtile.InstallTilePromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_nodeslistener_NodesListenerViewModel = "com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_counter_CounterScreenViewModel = "com.google.android.horologist.datalayer.sample.screens.counter.CounterScreenViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_reengage_ReEngagePromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.reengage.ReEngagePromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_signin_SignInPromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.signin.SignInPromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_reengage_ReEngageCustomPromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.reengage.ReEngageCustomPromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_installtile_InstallTilePromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installtile.InstallTilePromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_signin_SignInCustomPromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.signin.SignInCustomPromptDemoViewModel";

      @KeepFieldType
      InstallAppPromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_installapp_InstallAppPromptDemoViewModel2;

      @KeepFieldType
      com.google.android.horologist.datalayer.sample.screens.inappprompts.installapp.InstallAppPromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_installapp_InstallAppPromptDemoViewModel2;

      @KeepFieldType
      NodesViewModel com_google_android_horologist_datalayer_sample_screens_nodes_NodesViewModel2;

      @KeepFieldType
      InstallTilePromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_installtile_InstallTilePromptDemoViewModel2;

      @KeepFieldType
      NodesListenerViewModel com_google_android_horologist_datalayer_sample_screens_nodeslistener_NodesListenerViewModel2;

      @KeepFieldType
      CounterScreenViewModel com_google_android_horologist_datalayer_sample_screens_counter_CounterScreenViewModel2;

      @KeepFieldType
      ReEngagePromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_reengage_ReEngagePromptDemoViewModel2;

      @KeepFieldType
      SignInPromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_signin_SignInPromptDemoViewModel2;

      @KeepFieldType
      ReEngageCustomPromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_reengage_ReEngageCustomPromptDemoViewModel2;

      @KeepFieldType
      com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installtile.InstallTilePromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_installtile_InstallTilePromptDemoViewModel2;

      @KeepFieldType
      SignInCustomPromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_signin_SignInCustomPromptDemoViewModel2;
    }
  }

  private static final class ViewModelCImpl extends App_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<CounterScreenViewModel> counterScreenViewModelProvider;

    private Provider<InstallAppPromptDemoViewModel> installAppPromptDemoViewModelProvider;

    private Provider<com.google.android.horologist.datalayer.sample.screens.inappprompts.installapp.InstallAppPromptDemoViewModel> installAppPromptDemoViewModelProvider2;

    private Provider<com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installtile.InstallTilePromptDemoViewModel> installTilePromptDemoViewModelProvider;

    private Provider<InstallTilePromptDemoViewModel> installTilePromptDemoViewModelProvider2;

    private Provider<NodesListenerViewModel> nodesListenerViewModelProvider;

    private Provider<NodesViewModel> nodesViewModelProvider;

    private Provider<ReEngageCustomPromptDemoViewModel> reEngageCustomPromptDemoViewModelProvider;

    private Provider<ReEngagePromptDemoViewModel> reEngagePromptDemoViewModelProvider;

    private Provider<SignInCustomPromptDemoViewModel> signInCustomPromptDemoViewModelProvider;

    private Provider<SignInPromptDemoViewModel> signInPromptDemoViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.counterScreenViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.installAppPromptDemoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.installAppPromptDemoViewModelProvider2 = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.installTilePromptDemoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.installTilePromptDemoViewModelProvider2 = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.nodesListenerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.nodesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.reEngageCustomPromptDemoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.reEngagePromptDemoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.signInCustomPromptDemoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.signInPromptDemoViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(11).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_counter_CounterScreenViewModel, ((Provider) counterScreenViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_installapp_InstallAppPromptDemoViewModel, ((Provider) installAppPromptDemoViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_installapp_InstallAppPromptDemoViewModel, ((Provider) installAppPromptDemoViewModelProvider2)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_installtile_InstallTilePromptDemoViewModel, ((Provider) installTilePromptDemoViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_installtile_InstallTilePromptDemoViewModel, ((Provider) installTilePromptDemoViewModelProvider2)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_nodeslistener_NodesListenerViewModel, ((Provider) nodesListenerViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_nodes_NodesViewModel, ((Provider) nodesViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_reengage_ReEngageCustomPromptDemoViewModel, ((Provider) reEngageCustomPromptDemoViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_reengage_ReEngagePromptDemoViewModel, ((Provider) reEngagePromptDemoViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_signin_SignInCustomPromptDemoViewModel, ((Provider) signInCustomPromptDemoViewModelProvider)).put(LazyClassKeyProvider.com_google_android_horologist_datalayer_sample_screens_inappprompts_signin_SignInPromptDemoViewModel, ((Provider) signInPromptDemoViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_installapp_InstallAppPromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.installapp.InstallAppPromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_nodes_NodesViewModel = "com.google.android.horologist.datalayer.sample.screens.nodes.NodesViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_reengage_ReEngagePromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.reengage.ReEngagePromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_installapp_InstallAppPromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installapp.InstallAppPromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_nodeslistener_NodesListenerViewModel = "com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_counter_CounterScreenViewModel = "com.google.android.horologist.datalayer.sample.screens.counter.CounterScreenViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_reengage_ReEngageCustomPromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.reengage.ReEngageCustomPromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_installtile_InstallTilePromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installtile.InstallTilePromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_signin_SignInPromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.signin.SignInPromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_signin_SignInCustomPromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.signin.SignInCustomPromptDemoViewModel";

      static String com_google_android_horologist_datalayer_sample_screens_inappprompts_installtile_InstallTilePromptDemoViewModel = "com.google.android.horologist.datalayer.sample.screens.inappprompts.installtile.InstallTilePromptDemoViewModel";

      @KeepFieldType
      com.google.android.horologist.datalayer.sample.screens.inappprompts.installapp.InstallAppPromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_installapp_InstallAppPromptDemoViewModel2;

      @KeepFieldType
      NodesViewModel com_google_android_horologist_datalayer_sample_screens_nodes_NodesViewModel2;

      @KeepFieldType
      ReEngagePromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_reengage_ReEngagePromptDemoViewModel2;

      @KeepFieldType
      InstallAppPromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_installapp_InstallAppPromptDemoViewModel2;

      @KeepFieldType
      NodesListenerViewModel com_google_android_horologist_datalayer_sample_screens_nodeslistener_NodesListenerViewModel2;

      @KeepFieldType
      CounterScreenViewModel com_google_android_horologist_datalayer_sample_screens_counter_CounterScreenViewModel2;

      @KeepFieldType
      ReEngageCustomPromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_reengage_ReEngageCustomPromptDemoViewModel2;

      @KeepFieldType
      com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installtile.InstallTilePromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_installtile_InstallTilePromptDemoViewModel2;

      @KeepFieldType
      SignInPromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_signin_SignInPromptDemoViewModel2;

      @KeepFieldType
      SignInCustomPromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_custom_signin_SignInCustomPromptDemoViewModel2;

      @KeepFieldType
      InstallTilePromptDemoViewModel com_google_android_horologist_datalayer_sample_screens_inappprompts_installtile_InstallTilePromptDemoViewModel2;
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
          case 0: // com.google.android.horologist.datalayer.sample.screens.counter.CounterScreenViewModel 
          return (T) new CounterScreenViewModel(singletonCImpl.phoneDataLayerAppHelperProvider.get(), singletonCImpl.wearDataLayerRegistryProvider.get());

          case 1: // com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installapp.InstallAppPromptDemoViewModel 
          return (T) new InstallAppPromptDemoViewModel(singletonCImpl.phoneDataLayerAppHelperProvider.get(), singletonCImpl.installAppPromptProvider.get());

          case 2: // com.google.android.horologist.datalayer.sample.screens.inappprompts.installapp.InstallAppPromptDemoViewModel 
          return (T) new com.google.android.horologist.datalayer.sample.screens.inappprompts.installapp.InstallAppPromptDemoViewModel(singletonCImpl.phoneDataLayerAppHelperProvider.get(), singletonCImpl.installAppPromptProvider.get());

          case 3: // com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installtile.InstallTilePromptDemoViewModel 
          return (T) new com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.installtile.InstallTilePromptDemoViewModel(singletonCImpl.phoneDataLayerAppHelperProvider.get(), singletonCImpl.installTilePromptProvider.get());

          case 4: // com.google.android.horologist.datalayer.sample.screens.inappprompts.installtile.InstallTilePromptDemoViewModel 
          return (T) new InstallTilePromptDemoViewModel(singletonCImpl.phoneDataLayerAppHelperProvider.get(), singletonCImpl.installTilePromptProvider.get());

          case 5: // com.google.android.horologist.datalayer.sample.screens.nodeslistener.NodesListenerViewModel 
          return (T) new NodesListenerViewModel(singletonCImpl.phoneDataLayerAppHelperProvider.get());

          case 6: // com.google.android.horologist.datalayer.sample.screens.nodes.NodesViewModel 
          return (T) new NodesViewModel(singletonCImpl.phoneDataLayerAppHelperProvider.get());

          case 7: // com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.reengage.ReEngageCustomPromptDemoViewModel 
          return (T) new ReEngageCustomPromptDemoViewModel(singletonCImpl.phoneDataLayerAppHelperProvider.get(), singletonCImpl.reEngagePromptProvider.get());

          case 8: // com.google.android.horologist.datalayer.sample.screens.inappprompts.reengage.ReEngagePromptDemoViewModel 
          return (T) new ReEngagePromptDemoViewModel(singletonCImpl.phoneDataLayerAppHelperProvider.get(), singletonCImpl.reEngagePromptProvider.get());

          case 9: // com.google.android.horologist.datalayer.sample.screens.inappprompts.custom.signin.SignInCustomPromptDemoViewModel 
          return (T) new SignInCustomPromptDemoViewModel(singletonCImpl.phoneDataLayerAppHelperProvider.get(), singletonCImpl.signInPromptProvider.get());

          case 10: // com.google.android.horologist.datalayer.sample.screens.inappprompts.signin.SignInPromptDemoViewModel 
          return (T) new SignInPromptDemoViewModel(singletonCImpl.phoneDataLayerAppHelperProvider.get(), singletonCImpl.signInPromptProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends App_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
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

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends App_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends App_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<CoroutineScope> providesCoroutineScopeProvider;

    private Provider<WearDataLayerRegistry> wearDataLayerRegistryProvider;

    private Provider<PhoneDataLayerAppHelper> phoneDataLayerAppHelperProvider;

    private Provider<InstallAppPrompt> installAppPromptProvider;

    private Provider<InstallTilePrompt> installTilePromptProvider;

    private Provider<ReEngagePrompt> reEngagePromptProvider;

    private Provider<SignInPrompt> signInPromptProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.providesCoroutineScopeProvider = DoubleCheck.provider(new SwitchingProvider<CoroutineScope>(singletonCImpl, 2));
      this.wearDataLayerRegistryProvider = DoubleCheck.provider(new SwitchingProvider<WearDataLayerRegistry>(singletonCImpl, 1));
      this.phoneDataLayerAppHelperProvider = DoubleCheck.provider(new SwitchingProvider<PhoneDataLayerAppHelper>(singletonCImpl, 0));
      this.installAppPromptProvider = DoubleCheck.provider(new SwitchingProvider<InstallAppPrompt>(singletonCImpl, 3));
      this.installTilePromptProvider = DoubleCheck.provider(new SwitchingProvider<InstallTilePrompt>(singletonCImpl, 4));
      this.reEngagePromptProvider = DoubleCheck.provider(new SwitchingProvider<ReEngagePrompt>(singletonCImpl, 5));
      this.signInPromptProvider = DoubleCheck.provider(new SwitchingProvider<SignInPrompt>(singletonCImpl, 6));
    }

    @Override
    public void injectApp(App arg0) {
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

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.google.android.horologist.datalayer.phone.PhoneDataLayerAppHelper 
          return (T) DatalayerModule_PhoneDataLayerAppHelperFactory.phoneDataLayerAppHelper(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.wearDataLayerRegistryProvider.get());

          case 1: // com.google.android.horologist.data.WearDataLayerRegistry 
          return (T) DatalayerModule_WearDataLayerRegistryFactory.wearDataLayerRegistry(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.providesCoroutineScopeProvider.get());

          case 2: // kotlinx.coroutines.CoroutineScope 
          return (T) DatalayerModule_ProvidesCoroutineScopeFactory.providesCoroutineScope();

          case 3: // com.google.android.horologist.datalayer.phone.ui.prompt.installapp.InstallAppPrompt 
          return (T) PromptModule_InstallAppPromptFactory.installAppPrompt(singletonCImpl.phoneDataLayerAppHelperProvider.get());

          case 4: // com.google.android.horologist.datalayer.phone.ui.prompt.installtile.InstallTilePrompt 
          return (T) PromptModule_InstallTilePromptFactory.installTilePrompt(singletonCImpl.phoneDataLayerAppHelperProvider.get());

          case 5: // com.google.android.horologist.datalayer.phone.ui.prompt.reengage.ReEngagePrompt 
          return (T) PromptModule_ReEngagePromptFactory.reEngagePrompt(singletonCImpl.providesCoroutineScopeProvider.get(), singletonCImpl.phoneDataLayerAppHelperProvider.get());

          case 6: // com.google.android.horologist.datalayer.phone.ui.prompt.signin.SignInPrompt 
          return (T) PromptModule_SignInPromptFactory.signInPrompt(singletonCImpl.providesCoroutineScopeProvider.get(), singletonCImpl.phoneDataLayerAppHelperProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
