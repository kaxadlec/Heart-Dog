package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final AccompanistLibraryAccessors laccForAccompanistLibraryAccessors = new AccompanistLibraryAccessors(owner);
    private final AndroidLibraryAccessors laccForAndroidLibraryAccessors = new AndroidLibraryAccessors(owner);
    private final AndroidxLibraryAccessors laccForAndroidxLibraryAccessors = new AndroidxLibraryAccessors(owner);
    private final CoilLibraryAccessors laccForCoilLibraryAccessors = new CoilLibraryAccessors(owner);
    private final ComLibraryAccessors laccForComLibraryAccessors = new ComLibraryAccessors(owner);
    private final ComposeLibraryAccessors laccForComposeLibraryAccessors = new ComposeLibraryAccessors(owner);
    private final DaggerLibraryAccessors laccForDaggerLibraryAccessors = new DaggerLibraryAccessors(owner);
    private final GoogleLibraryAccessors laccForGoogleLibraryAccessors = new GoogleLibraryAccessors(owner);
    private final HiltLibraryAccessors laccForHiltLibraryAccessors = new HiltLibraryAccessors(owner);
    private final IoLibraryAccessors laccForIoLibraryAccessors = new IoLibraryAccessors(owner);
    private final KotlinLibraryAccessors laccForKotlinLibraryAccessors = new KotlinLibraryAccessors(owner);
    private final KotlinxLibraryAccessors laccForKotlinxLibraryAccessors = new KotlinxLibraryAccessors(owner);
    private final LottieLibraryAccessors laccForLottieLibraryAccessors = new LottieLibraryAccessors(owner);
    private final MikepenzLibraryAccessors laccForMikepenzLibraryAccessors = new MikepenzLibraryAccessors(owner);
    private final MoshiLibraryAccessors laccForMoshiLibraryAccessors = new MoshiLibraryAccessors(owner);
    private final PlayservicesLibraryAccessors laccForPlayservicesLibraryAccessors = new PlayservicesLibraryAccessors(owner);
    private final ProtobufLibraryAccessors laccForProtobufLibraryAccessors = new ProtobufLibraryAccessors(owner);
    private final Retrofit2LibraryAccessors laccForRetrofit2LibraryAccessors = new Retrofit2LibraryAccessors(owner);
    private final RobolectricLibraryAccessors laccForRobolectricLibraryAccessors = new RobolectricLibraryAccessors(owner);
    private final RoborazziLibraryAccessors laccForRoborazziLibraryAccessors = new RoborazziLibraryAccessors(owner);
    private final RoomLibraryAccessors laccForRoomLibraryAccessors = new RoomLibraryAccessors(owner);
    private final WearcomposeLibraryAccessors laccForWearcomposeLibraryAccessors = new WearcomposeLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Dependency provider for <b>annotation</b> with <b>androidx.test:annotation</b> coordinates and
     * with version reference <b>annotation</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getAnnotation() {
        return create("annotation");
    }

    /**
     * Dependency provider for <b>dokka</b> with <b>org.jetbrains.dokka:dokka-gradle-plugin</b> coordinates and
     * with version reference <b>dokka</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getDokka() {
        return create("dokka");
    }

    /**
     * Dependency provider for <b>gradleMavenPublishPlugin</b> with <b>com.vanniktech:gradle-maven-publish-plugin</b> coordinates and
     * with version reference <b>gradlePublishPlugin</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getGradleMavenPublishPlugin() {
        return create("gradleMavenPublishPlugin");
    }

    /**
     * Dependency provider for <b>junit</b> with <b>junit:junit</b> coordinates and
     * with version reference <b>junit</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getJunit() {
        return create("junit");
    }

    /**
     * Dependency provider for <b>material</b> with <b>com.google.android.material:material</b> coordinates and
     * with version reference <b>material</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getMaterial() {
        return create("material");
    }

    /**
     * Dependency provider for <b>okio</b> with <b>com.squareup.okio:okio</b> coordinates and
     * with version reference <b>okio</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getOkio() {
        return create("okio");
    }

    /**
     * Dependency provider for <b>truth</b> with <b>com.google.truth:truth</b> coordinates and
     * with version reference <b>truth</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getTruth() {
        return create("truth");
    }

    /**
     * Dependency provider for <b>turbine</b> with <b>app.cash.turbine:turbine</b> coordinates and
     * with version reference <b>app.cash.turbine</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getTurbine() {
        return create("turbine");
    }

    /**
     * Group of libraries at <b>accompanist</b>
     */
    public AccompanistLibraryAccessors getAccompanist() {
        return laccForAccompanistLibraryAccessors;
    }

    /**
     * Group of libraries at <b>android</b>
     */
    public AndroidLibraryAccessors getAndroid() {
        return laccForAndroidLibraryAccessors;
    }

    /**
     * Group of libraries at <b>androidx</b>
     */
    public AndroidxLibraryAccessors getAndroidx() {
        return laccForAndroidxLibraryAccessors;
    }

    /**
     * Group of libraries at <b>coil</b>
     */
    public CoilLibraryAccessors getCoil() {
        return laccForCoilLibraryAccessors;
    }

    /**
     * Group of libraries at <b>com</b>
     */
    public ComLibraryAccessors getCom() {
        return laccForComLibraryAccessors;
    }

    /**
     * Group of libraries at <b>compose</b>
     */
    public ComposeLibraryAccessors getCompose() {
        return laccForComposeLibraryAccessors;
    }

    /**
     * Group of libraries at <b>dagger</b>
     */
    public DaggerLibraryAccessors getDagger() {
        return laccForDaggerLibraryAccessors;
    }

    /**
     * Group of libraries at <b>google</b>
     */
    public GoogleLibraryAccessors getGoogle() {
        return laccForGoogleLibraryAccessors;
    }

    /**
     * Group of libraries at <b>hilt</b>
     */
    public HiltLibraryAccessors getHilt() {
        return laccForHiltLibraryAccessors;
    }

    /**
     * Group of libraries at <b>io</b>
     */
    public IoLibraryAccessors getIo() {
        return laccForIoLibraryAccessors;
    }

    /**
     * Group of libraries at <b>kotlin</b>
     */
    public KotlinLibraryAccessors getKotlin() {
        return laccForKotlinLibraryAccessors;
    }

    /**
     * Group of libraries at <b>kotlinx</b>
     */
    public KotlinxLibraryAccessors getKotlinx() {
        return laccForKotlinxLibraryAccessors;
    }

    /**
     * Group of libraries at <b>lottie</b>
     */
    public LottieLibraryAccessors getLottie() {
        return laccForLottieLibraryAccessors;
    }

    /**
     * Group of libraries at <b>mikepenz</b>
     */
    public MikepenzLibraryAccessors getMikepenz() {
        return laccForMikepenzLibraryAccessors;
    }

    /**
     * Group of libraries at <b>moshi</b>
     */
    public MoshiLibraryAccessors getMoshi() {
        return laccForMoshiLibraryAccessors;
    }

    /**
     * Group of libraries at <b>playservices</b>
     */
    public PlayservicesLibraryAccessors getPlayservices() {
        return laccForPlayservicesLibraryAccessors;
    }

    /**
     * Group of libraries at <b>protobuf</b>
     */
    public ProtobufLibraryAccessors getProtobuf() {
        return laccForProtobufLibraryAccessors;
    }

    /**
     * Group of libraries at <b>retrofit2</b>
     */
    public Retrofit2LibraryAccessors getRetrofit2() {
        return laccForRetrofit2LibraryAccessors;
    }

    /**
     * Group of libraries at <b>robolectric</b>
     */
    public RobolectricLibraryAccessors getRobolectric() {
        return laccForRobolectricLibraryAccessors;
    }

    /**
     * Group of libraries at <b>roborazzi</b>
     */
    public RoborazziLibraryAccessors getRoborazzi() {
        return laccForRoborazziLibraryAccessors;
    }

    /**
     * Group of libraries at <b>room</b>
     */
    public RoomLibraryAccessors getRoom() {
        return laccForRoomLibraryAccessors;
    }

    /**
     * Group of libraries at <b>wearcompose</b>
     */
    public WearcomposeLibraryAccessors getWearcompose() {
        return laccForWearcomposeLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class AccompanistLibraryAccessors extends SubDependencyFactory {

        public AccompanistLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>testharness</b> with <b>com.google.accompanist:accompanist-testharness</b> coordinates and
         * with version reference <b>accompanist</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTestharness() {
            return create("accompanist.testharness");
        }

    }

    public static class AndroidLibraryAccessors extends SubDependencyFactory {
        private final AndroidToolsLibraryAccessors laccForAndroidToolsLibraryAccessors = new AndroidToolsLibraryAccessors(owner);

        public AndroidLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>android.tools</b>
         */
        public AndroidToolsLibraryAccessors getTools() {
            return laccForAndroidToolsLibraryAccessors;
        }

    }

    public static class AndroidToolsLibraryAccessors extends SubDependencyFactory {
        private final AndroidToolsBuildLibraryAccessors laccForAndroidToolsBuildLibraryAccessors = new AndroidToolsBuildLibraryAccessors(owner);

        public AndroidToolsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>android.tools.build</b>
         */
        public AndroidToolsBuildLibraryAccessors getBuild() {
            return laccForAndroidToolsBuildLibraryAccessors;
        }

    }

    public static class AndroidToolsBuildLibraryAccessors extends SubDependencyFactory {

        public AndroidToolsBuildLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>gradle</b> with <b>com.android.tools.build:gradle</b> coordinates and
         * with version reference <b>gradlePlugin</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGradle() {
            return create("android.tools.build.gradle");
        }

    }

    public static class AndroidxLibraryAccessors extends SubDependencyFactory {
        private final AndroidxActivityLibraryAccessors laccForAndroidxActivityLibraryAccessors = new AndroidxActivityLibraryAccessors(owner);
        private final AndroidxBenchmarkLibraryAccessors laccForAndroidxBenchmarkLibraryAccessors = new AndroidxBenchmarkLibraryAccessors(owner);
        private final AndroidxComplicationsLibraryAccessors laccForAndroidxComplicationsLibraryAccessors = new AndroidxComplicationsLibraryAccessors(owner);
        private final AndroidxConcurrentLibraryAccessors laccForAndroidxConcurrentLibraryAccessors = new AndroidxConcurrentLibraryAccessors(owner);
        private final AndroidxConstraintlayoutLibraryAccessors laccForAndroidxConstraintlayoutLibraryAccessors = new AndroidxConstraintlayoutLibraryAccessors(owner);
        private final AndroidxDatastoreLibraryAccessors laccForAndroidxDatastoreLibraryAccessors = new AndroidxDatastoreLibraryAccessors(owner);
        private final AndroidxHealthLibraryAccessors laccForAndroidxHealthLibraryAccessors = new AndroidxHealthLibraryAccessors(owner);
        private final AndroidxLifecycleLibraryAccessors laccForAndroidxLifecycleLibraryAccessors = new AndroidxLifecycleLibraryAccessors(owner);
        private final AndroidxMedia3LibraryAccessors laccForAndroidxMedia3LibraryAccessors = new AndroidxMedia3LibraryAccessors(owner);
        private final AndroidxMetricsLibraryAccessors laccForAndroidxMetricsLibraryAccessors = new AndroidxMetricsLibraryAccessors(owner);
        private final AndroidxNavigationLibraryAccessors laccForAndroidxNavigationLibraryAccessors = new AndroidxNavigationLibraryAccessors(owner);
        private final AndroidxPaletteLibraryAccessors laccForAndroidxPaletteLibraryAccessors = new AndroidxPaletteLibraryAccessors(owner);
        private final AndroidxRuntimeLibraryAccessors laccForAndroidxRuntimeLibraryAccessors = new AndroidxRuntimeLibraryAccessors(owner);
        private final AndroidxTestLibraryAccessors laccForAndroidxTestLibraryAccessors = new AndroidxTestLibraryAccessors(owner);
        private final AndroidxTracingLibraryAccessors laccForAndroidxTracingLibraryAccessors = new AndroidxTracingLibraryAccessors(owner);
        private final AndroidxWearLibraryAccessors laccForAndroidxWearLibraryAccessors = new AndroidxWearLibraryAccessors(owner);
        private final AndroidxWorkLibraryAccessors laccForAndroidxWorkLibraryAccessors = new AndroidxWorkLibraryAccessors(owner);

        public AndroidxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>annotation</b> with <b>androidx.annotation:annotation</b> coordinates and
         * with version <b>1.9.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAnnotation() {
            return create("androidx.annotation");
        }

        /**
         * Dependency provider for <b>appcompat</b> with <b>androidx.appcompat:appcompat</b> coordinates and
         * with version reference <b>appcompat</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAppcompat() {
            return create("androidx.appcompat");
        }

        /**
         * Dependency provider for <b>core</b> with <b>androidx.core:core</b> coordinates and
         * with version reference <b>androidxCore</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("androidx.core");
        }

        /**
         * Dependency provider for <b>corektx</b> with <b>androidx.core:core-ktx</b> coordinates and
         * with version reference <b>androidxCore</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCorektx() {
            return create("androidx.corektx");
        }

        /**
         * Dependency provider for <b>mediarouter</b> with <b>androidx.mediarouter:mediarouter</b> coordinates and
         * with version <b>1.7.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMediarouter() {
            return create("androidx.mediarouter");
        }

        /**
         * Dependency provider for <b>paging</b> with <b>androidx.paging:paging-compose</b> coordinates and
         * with version <b>3.3.2</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPaging() {
            return create("androidx.paging");
        }

        /**
         * Dependency provider for <b>startup</b> with <b>androidx.startup:startup-runtime</b> coordinates and
         * with version reference <b>androidxStartup</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getStartup() {
            return create("androidx.startup");
        }

        /**
         * Group of libraries at <b>androidx.activity</b>
         */
        public AndroidxActivityLibraryAccessors getActivity() {
            return laccForAndroidxActivityLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.benchmark</b>
         */
        public AndroidxBenchmarkLibraryAccessors getBenchmark() {
            return laccForAndroidxBenchmarkLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.complications</b>
         */
        public AndroidxComplicationsLibraryAccessors getComplications() {
            return laccForAndroidxComplicationsLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.concurrent</b>
         */
        public AndroidxConcurrentLibraryAccessors getConcurrent() {
            return laccForAndroidxConcurrentLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.constraintlayout</b>
         */
        public AndroidxConstraintlayoutLibraryAccessors getConstraintlayout() {
            return laccForAndroidxConstraintlayoutLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.datastore</b>
         */
        public AndroidxDatastoreLibraryAccessors getDatastore() {
            return laccForAndroidxDatastoreLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.health</b>
         */
        public AndroidxHealthLibraryAccessors getHealth() {
            return laccForAndroidxHealthLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.lifecycle</b>
         */
        public AndroidxLifecycleLibraryAccessors getLifecycle() {
            return laccForAndroidxLifecycleLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.media3</b>
         */
        public AndroidxMedia3LibraryAccessors getMedia3() {
            return laccForAndroidxMedia3LibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.metrics</b>
         */
        public AndroidxMetricsLibraryAccessors getMetrics() {
            return laccForAndroidxMetricsLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.navigation</b>
         */
        public AndroidxNavigationLibraryAccessors getNavigation() {
            return laccForAndroidxNavigationLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.palette</b>
         */
        public AndroidxPaletteLibraryAccessors getPalette() {
            return laccForAndroidxPaletteLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.runtime</b>
         */
        public AndroidxRuntimeLibraryAccessors getRuntime() {
            return laccForAndroidxRuntimeLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.test</b>
         */
        public AndroidxTestLibraryAccessors getTest() {
            return laccForAndroidxTestLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.tracing</b>
         */
        public AndroidxTracingLibraryAccessors getTracing() {
            return laccForAndroidxTracingLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.wear</b>
         */
        public AndroidxWearLibraryAccessors getWear() {
            return laccForAndroidxWearLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.work</b>
         */
        public AndroidxWorkLibraryAccessors getWork() {
            return laccForAndroidxWorkLibraryAccessors;
        }

    }

    public static class AndroidxActivityLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxActivityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>activity</b> with <b>androidx.activity:activity</b> coordinates and
         * with version reference <b>androidxActivity</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("androidx.activity");
        }

        /**
         * Dependency provider for <b>compose</b> with <b>androidx.activity:activity-compose</b> coordinates and
         * with version reference <b>androidxActivity</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCompose() {
            return create("androidx.activity.compose");
        }

    }

    public static class AndroidxBenchmarkLibraryAccessors extends SubDependencyFactory {
        private final AndroidxBenchmarkMacroLibraryAccessors laccForAndroidxBenchmarkMacroLibraryAccessors = new AndroidxBenchmarkMacroLibraryAccessors(owner);

        public AndroidxBenchmarkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>junit4</b> with <b>androidx.benchmark:benchmark-junit4</b> coordinates and
         * with version reference <b>androidx.benchmark</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit4() {
            return create("androidx.benchmark.junit4");
        }

        /**
         * Group of libraries at <b>androidx.benchmark.macro</b>
         */
        public AndroidxBenchmarkMacroLibraryAccessors getMacro() {
            return laccForAndroidxBenchmarkMacroLibraryAccessors;
        }

    }

    public static class AndroidxBenchmarkMacroLibraryAccessors extends SubDependencyFactory {

        public AndroidxBenchmarkMacroLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>junit4</b> with <b>androidx.benchmark:benchmark-macro-junit4</b> coordinates and
         * with version reference <b>androidx.benchmark</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit4() {
            return create("androidx.benchmark.macro.junit4");
        }

    }

    public static class AndroidxComplicationsLibraryAccessors extends SubDependencyFactory {
        private final AndroidxComplicationsDatasourceLibraryAccessors laccForAndroidxComplicationsDatasourceLibraryAccessors = new AndroidxComplicationsDatasourceLibraryAccessors(owner);

        public AndroidxComplicationsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>data</b> with <b>androidx.wear.watchface:watchface-complications-data</b> coordinates and
         * with version reference <b>androidx.complications.data</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getData() {
            return create("androidx.complications.data");
        }

        /**
         * Dependency provider for <b>rendering</b> with <b>androidx.wear.watchface:watchface-complications-rendering</b> coordinates and
         * with version reference <b>androidx.wear.watchface</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRendering() {
            return create("androidx.complications.rendering");
        }

        /**
         * Group of libraries at <b>androidx.complications.datasource</b>
         */
        public AndroidxComplicationsDatasourceLibraryAccessors getDatasource() {
            return laccForAndroidxComplicationsDatasourceLibraryAccessors;
        }

    }

    public static class AndroidxComplicationsDatasourceLibraryAccessors extends SubDependencyFactory {

        public AndroidxComplicationsDatasourceLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>ktx</b> with <b>androidx.wear.watchface:watchface-complications-data-source-ktx</b> coordinates and
         * with version reference <b>androidx.wear.watchface</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKtx() {
            return create("androidx.complications.datasource.ktx");
        }

    }

    public static class AndroidxConcurrentLibraryAccessors extends SubDependencyFactory {
        private final AndroidxConcurrentFutureLibraryAccessors laccForAndroidxConcurrentFutureLibraryAccessors = new AndroidxConcurrentFutureLibraryAccessors(owner);

        public AndroidxConcurrentLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>androidx.concurrent.future</b>
         */
        public AndroidxConcurrentFutureLibraryAccessors getFuture() {
            return laccForAndroidxConcurrentFutureLibraryAccessors;
        }

    }

    public static class AndroidxConcurrentFutureLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxConcurrentFutureLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>future</b> with <b>androidx.concurrent:concurrent-futures</b> coordinates and
         * with version reference <b>androidx.concurrent</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("androidx.concurrent.future");
        }

        /**
         * Dependency provider for <b>ktx</b> with <b>androidx.concurrent:concurrent-futures-ktx</b> coordinates and
         * with version reference <b>androidx.concurrent</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKtx() {
            return create("androidx.concurrent.future.ktx");
        }

    }

    public static class AndroidxConstraintlayoutLibraryAccessors extends SubDependencyFactory {

        public AndroidxConstraintlayoutLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>compose</b> with <b>androidx.constraintlayout:constraintlayout-compose</b> coordinates and
         * with version reference <b>androidx.constraintlayout.compose</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCompose() {
            return create("androidx.constraintlayout.compose");
        }

    }

    public static class AndroidxDatastoreLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxDatastoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>datastore</b> with <b>androidx.datastore:datastore</b> coordinates and
         * with version reference <b>androidx.datastore</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("androidx.datastore");
        }

        /**
         * Dependency provider for <b>core</b> with <b>androidx.datastore:datastore-core</b> coordinates and
         * with version reference <b>androidx.datastore</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("androidx.datastore.core");
        }

        /**
         * Dependency provider for <b>preferences</b> with <b>androidx.datastore:datastore-preferences</b> coordinates and
         * with version reference <b>androidx.datastore</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPreferences() {
            return create("androidx.datastore.preferences");
        }

    }

    public static class AndroidxHealthLibraryAccessors extends SubDependencyFactory {

        public AndroidxHealthLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>services</b> with <b>androidx.health:health-services-client</b> coordinates and
         * with version reference <b>androidx.health.services</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getServices() {
            return create("androidx.health.services");
        }

    }

    public static class AndroidxLifecycleLibraryAccessors extends SubDependencyFactory {
        private final AndroidxLifecycleRuntimeLibraryAccessors laccForAndroidxLifecycleRuntimeLibraryAccessors = new AndroidxLifecycleRuntimeLibraryAccessors(owner);
        private final AndroidxLifecycleViewmodelLibraryAccessors laccForAndroidxLifecycleViewmodelLibraryAccessors = new AndroidxLifecycleViewmodelLibraryAccessors(owner);

        public AndroidxLifecycleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>common</b> with <b>androidx.lifecycle:lifecycle-common</b> coordinates and
         * with version reference <b>androidxLifecycle</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCommon() {
            return create("androidx.lifecycle.common");
        }

        /**
         * Dependency provider for <b>process</b> with <b>androidx.lifecycle:lifecycle-process</b> coordinates and
         * with version reference <b>androidxLifecycle</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getProcess() {
            return create("androidx.lifecycle.process");
        }

        /**
         * Dependency provider for <b>service</b> with <b>androidx.lifecycle:lifecycle-service</b> coordinates and
         * with version reference <b>androidxLifecycle</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getService() {
            return create("androidx.lifecycle.service");
        }

        /**
         * Dependency provider for <b>testing</b> with <b>androidx.lifecycle:lifecycle-runtime-testing</b> coordinates and
         * with version reference <b>androidxLifecycle</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTesting() {
            return create("androidx.lifecycle.testing");
        }

        /**
         * Dependency provider for <b>viewmodelktx</b> with <b>androidx.lifecycle:lifecycle-viewmodel-ktx</b> coordinates and
         * with version reference <b>androidxLifecycle</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getViewmodelktx() {
            return create("androidx.lifecycle.viewmodelktx");
        }

        /**
         * Group of libraries at <b>androidx.lifecycle.runtime</b>
         */
        public AndroidxLifecycleRuntimeLibraryAccessors getRuntime() {
            return laccForAndroidxLifecycleRuntimeLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.lifecycle.viewmodel</b>
         */
        public AndroidxLifecycleViewmodelLibraryAccessors getViewmodel() {
            return laccForAndroidxLifecycleViewmodelLibraryAccessors;
        }

    }

    public static class AndroidxLifecycleRuntimeLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxLifecycleRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>runtime</b> with <b>androidx.lifecycle:lifecycle-runtime-ktx</b> coordinates and
         * with version reference <b>androidxLifecycle</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("androidx.lifecycle.runtime");
        }

        /**
         * Dependency provider for <b>compose</b> with <b>androidx.lifecycle:lifecycle-runtime-compose</b> coordinates and
         * with version reference <b>androidxLifecycle</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCompose() {
            return create("androidx.lifecycle.runtime.compose");
        }

    }

    public static class AndroidxLifecycleViewmodelLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxLifecycleViewmodelLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>viewmodel</b> with <b>androidx.lifecycle:lifecycle-viewmodel</b> coordinates and
         * with version reference <b>androidxLifecycle</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("androidx.lifecycle.viewmodel");
        }

        /**
         * Dependency provider for <b>compose</b> with <b>androidx.lifecycle:lifecycle-viewmodel-compose</b> coordinates and
         * with version reference <b>androidxLifecycle</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCompose() {
            return create("androidx.lifecycle.viewmodel.compose");
        }

    }

    public static class AndroidxMedia3LibraryAccessors extends SubDependencyFactory {
        private final AndroidxMedia3TestutilsLibraryAccessors laccForAndroidxMedia3TestutilsLibraryAccessors = new AndroidxMedia3TestutilsLibraryAccessors(owner);

        public AndroidxMedia3LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>common</b> with <b>androidx.media3:media3-common</b> coordinates and
         * with version reference <b>androidx.media3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCommon() {
            return create("androidx.media3.common");
        }

        /**
         * Dependency provider for <b>datasourceokhttp</b> with <b>androidx.media3:media3-datasource-okhttp</b> coordinates and
         * with version reference <b>androidx.media3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getDatasourceokhttp() {
            return create("androidx.media3.datasourceokhttp");
        }

        /**
         * Dependency provider for <b>exoplayer</b> with <b>androidx.media3:media3-exoplayer</b> coordinates and
         * with version reference <b>androidx.media3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getExoplayer() {
            return create("androidx.media3.exoplayer");
        }

        /**
         * Dependency provider for <b>exoplayerdash</b> with <b>androidx.media3:media3-exoplayer-dash</b> coordinates and
         * with version reference <b>androidx.media3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getExoplayerdash() {
            return create("androidx.media3.exoplayerdash");
        }

        /**
         * Dependency provider for <b>exoplayerhls</b> with <b>androidx.media3:media3-exoplayer-hls</b> coordinates and
         * with version reference <b>androidx.media3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getExoplayerhls() {
            return create("androidx.media3.exoplayerhls");
        }

        /**
         * Dependency provider for <b>exoplayerrtsp</b> with <b>androidx.media3:media3-exoplayer-rtsp</b> coordinates and
         * with version reference <b>androidx.media3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getExoplayerrtsp() {
            return create("androidx.media3.exoplayerrtsp");
        }

        /**
         * Dependency provider for <b>exoplayerworkmanager</b> with <b>androidx.media3:media3-exoplayer-workmanager</b> coordinates and
         * with version reference <b>androidx.media3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getExoplayerworkmanager() {
            return create("androidx.media3.exoplayerworkmanager");
        }

        /**
         * Dependency provider for <b>session</b> with <b>androidx.media3:media3-session</b> coordinates and
         * with version reference <b>androidx.media3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSession() {
            return create("androidx.media3.session");
        }

        /**
         * Dependency provider for <b>ui</b> with <b>androidx.media3:media3-ui</b> coordinates and
         * with version reference <b>androidx.media3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getUi() {
            return create("androidx.media3.ui");
        }

        /**
         * Group of libraries at <b>androidx.media3.testutils</b>
         */
        public AndroidxMedia3TestutilsLibraryAccessors getTestutils() {
            return laccForAndroidxMedia3TestutilsLibraryAccessors;
        }

    }

    public static class AndroidxMedia3TestutilsLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxMedia3TestutilsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>testutils</b> with <b>androidx.media3:media3-test-utils</b> coordinates and
         * with version reference <b>androidx.media3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("androidx.media3.testutils");
        }

        /**
         * Dependency provider for <b>robolectric</b> with <b>androidx.media3:media3-test-utils-robolectric</b> coordinates and
         * with version reference <b>androidx.media3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRobolectric() {
            return create("androidx.media3.testutils.robolectric");
        }

    }

    public static class AndroidxMetricsLibraryAccessors extends SubDependencyFactory {

        public AndroidxMetricsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>performance</b> with <b>androidx.metrics:metrics-performance</b> coordinates and
         * with version <b>1.0.0-beta01</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPerformance() {
            return create("androidx.metrics.performance");
        }

    }

    public static class AndroidxNavigationLibraryAccessors extends SubDependencyFactory {

        public AndroidxNavigationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>compose</b> with <b>androidx.navigation:navigation-compose</b> coordinates and
         * with version reference <b>androidxNavigation</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCompose() {
            return create("androidx.navigation.compose");
        }

        /**
         * Dependency provider for <b>runtime</b> with <b>androidx.navigation:navigation-runtime</b> coordinates and
         * with version reference <b>androidxNavigation</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRuntime() {
            return create("androidx.navigation.runtime");
        }

        /**
         * Dependency provider for <b>testing</b> with <b>androidx.navigation:navigation-testing</b> coordinates and
         * with version reference <b>androidxNavigation</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTesting() {
            return create("androidx.navigation.testing");
        }

    }

    public static class AndroidxPaletteLibraryAccessors extends SubDependencyFactory {

        public AndroidxPaletteLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>ktx</b> with <b>androidx.palette:palette-ktx</b> coordinates and
         * with version <b>1.0.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKtx() {
            return create("androidx.palette.ktx");
        }

    }

    public static class AndroidxRuntimeLibraryAccessors extends SubDependencyFactory {

        public AndroidxRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>tracing</b> with <b>androidx.compose.runtime:runtime-tracing</b> coordinates and
         * with version reference <b>runtimeTracing</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTracing() {
            return create("androidx.runtime.tracing");
        }

    }

    public static class AndroidxTestLibraryAccessors extends SubDependencyFactory {
        private final AndroidxTestExtLibraryAccessors laccForAndroidxTestExtLibraryAccessors = new AndroidxTestExtLibraryAccessors(owner);

        public AndroidxTestLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>espressocore</b> with <b>androidx.test.espresso:espresso-core</b> coordinates and
         * with version reference <b>androidx.test.espresso</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getEspressocore() {
            return create("androidx.test.espressocore");
        }

        /**
         * Dependency provider for <b>rules</b> with <b>androidx.test:rules</b> coordinates and
         * with version <b>1.6.1</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRules() {
            return create("androidx.test.rules");
        }

        /**
         * Dependency provider for <b>runner</b> with <b>androidx.test:runner</b> coordinates and
         * with version reference <b>androidx.test.runner</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRunner() {
            return create("androidx.test.runner");
        }

        /**
         * Dependency provider for <b>uiautomator</b> with <b>androidx.test.uiautomator:uiautomator</b> coordinates and
         * with version <b>2.3.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getUiautomator() {
            return create("androidx.test.uiautomator");
        }

        /**
         * Group of libraries at <b>androidx.test.ext</b>
         */
        public AndroidxTestExtLibraryAccessors getExt() {
            return laccForAndroidxTestExtLibraryAccessors;
        }

    }

    public static class AndroidxTestExtLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxTestExtLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>ext</b> with <b>androidx.test.ext:junit</b> coordinates and
         * with version reference <b>androidx.test.ext</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("androidx.test.ext");
        }

        /**
         * Dependency provider for <b>ktx</b> with <b>androidx.test.ext:junit-ktx</b> coordinates and
         * with version reference <b>androidx.test.ext</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKtx() {
            return create("androidx.test.ext.ktx");
        }

    }

    public static class AndroidxTracingLibraryAccessors extends SubDependencyFactory {

        public AndroidxTracingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>ktx</b> with <b>androidx.tracing:tracing-ktx</b> coordinates and
         * with version reference <b>androidxTracing</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKtx() {
            return create("androidx.tracing.ktx");
        }

    }

    public static class AndroidxWearLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxWearPhoneLibraryAccessors laccForAndroidxWearPhoneLibraryAccessors = new AndroidxWearPhoneLibraryAccessors(owner);
        private final AndroidxWearProtolayoutLibraryAccessors laccForAndroidxWearProtolayoutLibraryAccessors = new AndroidxWearProtolayoutLibraryAccessors(owner);
        private final AndroidxWearRemoteLibraryAccessors laccForAndroidxWearRemoteLibraryAccessors = new AndroidxWearRemoteLibraryAccessors(owner);
        private final AndroidxWearTilesLibraryAccessors laccForAndroidxWearTilesLibraryAccessors = new AndroidxWearTilesLibraryAccessors(owner);
        private final AndroidxWearToolingLibraryAccessors laccForAndroidxWearToolingLibraryAccessors = new AndroidxWearToolingLibraryAccessors(owner);

        public AndroidxWearLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>wear</b> with <b>androidx.wear:wear</b> coordinates and
         * with version reference <b>androidxWear</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("androidx.wear");
        }

        /**
         * Dependency provider for <b>input</b> with <b>androidx.wear:wear-input</b> coordinates and
         * with version reference <b>wearInput</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getInput() {
            return create("androidx.wear.input");
        }

        /**
         * Group of libraries at <b>androidx.wear.phone</b>
         */
        public AndroidxWearPhoneLibraryAccessors getPhone() {
            return laccForAndroidxWearPhoneLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.wear.protolayout</b>
         */
        public AndroidxWearProtolayoutLibraryAccessors getProtolayout() {
            return laccForAndroidxWearProtolayoutLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.wear.remote</b>
         */
        public AndroidxWearRemoteLibraryAccessors getRemote() {
            return laccForAndroidxWearRemoteLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.wear.tiles</b>
         */
        public AndroidxWearTilesLibraryAccessors getTiles() {
            return laccForAndroidxWearTilesLibraryAccessors;
        }

        /**
         * Group of libraries at <b>androidx.wear.tooling</b>
         */
        public AndroidxWearToolingLibraryAccessors getTooling() {
            return laccForAndroidxWearToolingLibraryAccessors;
        }

    }

    public static class AndroidxWearPhoneLibraryAccessors extends SubDependencyFactory {

        public AndroidxWearPhoneLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>interactions</b> with <b>androidx.wear:wear-phone-interactions</b> coordinates and
         * with version reference <b>androidxPhoneInteractions</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getInteractions() {
            return create("androidx.wear.phone.interactions");
        }

    }

    public static class AndroidxWearProtolayoutLibraryAccessors extends SubDependencyFactory {

        public AndroidxWearProtolayoutLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>material</b> with <b>androidx.wear.protolayout:protolayout-material</b> coordinates and
         * with version reference <b>androidxprotolayout</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMaterial() {
            return create("androidx.wear.protolayout.material");
        }

    }

    public static class AndroidxWearRemoteLibraryAccessors extends SubDependencyFactory {

        public AndroidxWearRemoteLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>interactions</b> with <b>androidx.wear:wear-remote-interactions</b> coordinates and
         * with version reference <b>androidxRemoteInteractions</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getInteractions() {
            return create("androidx.wear.remote.interactions");
        }

    }

    public static class AndroidxWearTilesLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxWearTilesToolingLibraryAccessors laccForAndroidxWearTilesToolingLibraryAccessors = new AndroidxWearTilesToolingLibraryAccessors(owner);

        public AndroidxWearTilesLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>tiles</b> with <b>androidx.wear.tiles:tiles</b> coordinates and
         * with version reference <b>androidxtiles</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("androidx.wear.tiles");
        }

        /**
         * Dependency provider for <b>renderer</b> with <b>androidx.wear.tiles:tiles-renderer</b> coordinates and
         * with version reference <b>androidxtiles</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRenderer() {
            return create("androidx.wear.tiles.renderer");
        }

        /**
         * Dependency provider for <b>testing</b> with <b>androidx.wear.tiles:tiles-testing</b> coordinates and
         * with version reference <b>androidxtiles</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTesting() {
            return create("androidx.wear.tiles.testing");
        }

        /**
         * Group of libraries at <b>androidx.wear.tiles.tooling</b>
         */
        public AndroidxWearTilesToolingLibraryAccessors getTooling() {
            return laccForAndroidxWearTilesToolingLibraryAccessors;
        }

    }

    public static class AndroidxWearTilesToolingLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxWearTilesToolingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>tooling</b> with <b>androidx.wear.tiles:tiles-tooling</b> coordinates and
         * with version reference <b>tiles.tooling.preview</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("androidx.wear.tiles.tooling");
        }

        /**
         * Dependency provider for <b>preview</b> with <b>androidx.wear.tiles:tiles-tooling-preview</b> coordinates and
         * with version reference <b>tiles.tooling.preview</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPreview() {
            return create("androidx.wear.tiles.tooling.preview");
        }

    }

    public static class AndroidxWearToolingLibraryAccessors extends SubDependencyFactory {

        public AndroidxWearToolingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>preview</b> with <b>androidx.wear:wear-tooling-preview</b> coordinates and
         * with version reference <b>wearToolingPreview</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPreview() {
            return create("androidx.wear.tooling.preview");
        }

    }

    public static class AndroidxWorkLibraryAccessors extends SubDependencyFactory {

        public AndroidxWorkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>ktx</b> with <b>androidx.work:work-runtime-ktx</b> coordinates and
         * with version reference <b>androidxWork</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKtx() {
            return create("androidx.work.ktx");
        }

        /**
         * Dependency provider for <b>testing</b> with <b>androidx.work:work-testing</b> coordinates and
         * with version reference <b>androidxWork</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTesting() {
            return create("androidx.work.testing");
        }

    }

    public static class CoilLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public CoilLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>coil</b> with <b>io.coil-kt:coil-compose</b> coordinates and
         * with version reference <b>io.coil.kt</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("coil");
        }

        /**
         * Dependency provider for <b>base</b> with <b>io.coil-kt:coil-compose-base</b> coordinates and
         * with version reference <b>io.coil.kt</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBase() {
            return create("coil.base");
        }

        /**
         * Dependency provider for <b>svg</b> with <b>io.coil-kt:coil-svg</b> coordinates and
         * with version reference <b>io.coil.kt</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSvg() {
            return create("coil.svg");
        }

        /**
         * Dependency provider for <b>test</b> with <b>io.coil-kt:coil-test</b> coordinates and
         * with version reference <b>io.coil.kt</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTest() {
            return create("coil.test");
        }

    }

    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComSquareupLibraryAccessors laccForComSquareupLibraryAccessors = new ComSquareupLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.squareup</b>
         */
        public ComSquareupLibraryAccessors getSquareup() {
            return laccForComSquareupLibraryAccessors;
        }

    }

    public static class ComSquareupLibraryAccessors extends SubDependencyFactory {
        private final ComSquareupOkhttp3LibraryAccessors laccForComSquareupOkhttp3LibraryAccessors = new ComSquareupOkhttp3LibraryAccessors(owner);

        public ComSquareupLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.squareup.okhttp3</b>
         */
        public ComSquareupOkhttp3LibraryAccessors getOkhttp3() {
            return laccForComSquareupOkhttp3LibraryAccessors;
        }

    }

    public static class ComSquareupOkhttp3LibraryAccessors extends SubDependencyFactory {
        private final ComSquareupOkhttp3LoggingLibraryAccessors laccForComSquareupOkhttp3LoggingLibraryAccessors = new ComSquareupOkhttp3LoggingLibraryAccessors(owner);

        public ComSquareupOkhttp3LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>mockwebserver</b> with <b>com.squareup.okhttp3:mockwebserver</b> coordinates and
         * with version reference <b>com.squareup.okhttp3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMockwebserver() {
            return create("com.squareup.okhttp3.mockwebserver");
        }

        /**
         * Dependency provider for <b>okhttp</b> with <b>com.squareup.okhttp3:okhttp</b> coordinates and
         * with version reference <b>com.squareup.okhttp3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getOkhttp() {
            return create("com.squareup.okhttp3.okhttp");
        }

        /**
         * Group of libraries at <b>com.squareup.okhttp3.logging</b>
         */
        public ComSquareupOkhttp3LoggingLibraryAccessors getLogging() {
            return laccForComSquareupOkhttp3LoggingLibraryAccessors;
        }

    }

    public static class ComSquareupOkhttp3LoggingLibraryAccessors extends SubDependencyFactory {

        public ComSquareupOkhttp3LoggingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>interceptor</b> with <b>com.squareup.okhttp3:logging-interceptor</b> coordinates and
         * with version reference <b>com.squareup.okhttp3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getInterceptor() {
            return create("com.squareup.okhttp3.logging.interceptor");
        }

    }

    public static class ComposeLibraryAccessors extends SubDependencyFactory {
        private final ComposeAnimationLibraryAccessors laccForComposeAnimationLibraryAccessors = new ComposeAnimationLibraryAccessors(owner);
        private final ComposeFoundationLibraryAccessors laccForComposeFoundationLibraryAccessors = new ComposeFoundationLibraryAccessors(owner);
        private final ComposeMaterialLibraryAccessors laccForComposeMaterialLibraryAccessors = new ComposeMaterialLibraryAccessors(owner);
        private final ComposeUiLibraryAccessors laccForComposeUiLibraryAccessors = new ComposeUiLibraryAccessors(owner);

        public ComposeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>bom</b> with <b>androidx.compose:compose-bom</b> coordinates and
         * with version reference <b>androidxComposeBom</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBom() {
            return create("compose.bom");
        }

        /**
         * Dependency provider for <b>material3</b> with <b>androidx.compose.material3:material3</b> coordinates and
         * with version reference <b>compose.material3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMaterial3() {
            return create("compose.material3");
        }

        /**
         * Dependency provider for <b>runtime</b> with <b>androidx.compose.runtime:runtime</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRuntime() {
            return create("compose.runtime");
        }

        /**
         * Group of libraries at <b>compose.animation</b>
         */
        public ComposeAnimationLibraryAccessors getAnimation() {
            return laccForComposeAnimationLibraryAccessors;
        }

        /**
         * Group of libraries at <b>compose.foundation</b>
         */
        public ComposeFoundationLibraryAccessors getFoundation() {
            return laccForComposeFoundationLibraryAccessors;
        }

        /**
         * Group of libraries at <b>compose.material</b>
         */
        public ComposeMaterialLibraryAccessors getMaterial() {
            return laccForComposeMaterialLibraryAccessors;
        }

        /**
         * Group of libraries at <b>compose.ui</b>
         */
        public ComposeUiLibraryAccessors getUi() {
            return laccForComposeUiLibraryAccessors;
        }

    }

    public static class ComposeAnimationLibraryAccessors extends SubDependencyFactory {

        public ComposeAnimationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>animationgraphics</b> with <b>androidx.compose.animation:animation-graphics</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAnimationgraphics() {
            return create("compose.animation.animationgraphics");
        }

    }

    public static class ComposeFoundationLibraryAccessors extends SubDependencyFactory {
        private final ComposeFoundationFoundationLibraryAccessors laccForComposeFoundationFoundationLibraryAccessors = new ComposeFoundationFoundationLibraryAccessors(owner);

        public ComposeFoundationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>compose.foundation.foundation</b>
         */
        public ComposeFoundationFoundationLibraryAccessors getFoundation() {
            return laccForComposeFoundationFoundationLibraryAccessors;
        }

    }

    public static class ComposeFoundationFoundationLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public ComposeFoundationFoundationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>foundation</b> with <b>androidx.compose.foundation:foundation</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("compose.foundation.foundation");
        }

        /**
         * Dependency provider for <b>layout</b> with <b>androidx.compose.foundation:foundation-layout</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLayout() {
            return create("compose.foundation.foundation.layout");
        }

    }

    public static class ComposeMaterialLibraryAccessors extends SubDependencyFactory {

        public ComposeMaterialLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>iconscore</b> with <b>androidx.compose.material:material-icons-core</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getIconscore() {
            return create("compose.material.iconscore");
        }

        /**
         * Dependency provider for <b>iconsext</b> with <b>androidx.compose.material:material-icons-extended</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getIconsext() {
            return create("compose.material.iconsext");
        }

        /**
         * Dependency provider for <b>ripple</b> with <b>androidx.compose.material:material-ripple</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRipple() {
            return create("compose.material.ripple");
        }

    }

    public static class ComposeUiLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final ComposeUiTestLibraryAccessors laccForComposeUiTestLibraryAccessors = new ComposeUiTestLibraryAccessors(owner);

        public ComposeUiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>ui</b> with <b>androidx.compose.ui:ui</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("compose.ui");
        }

        /**
         * Dependency provider for <b>graphics</b> with <b>androidx.compose.ui:ui-graphics</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGraphics() {
            return create("compose.ui.graphics");
        }

        /**
         * Dependency provider for <b>text</b> with <b>androidx.compose.ui:ui-text</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getText() {
            return create("compose.ui.text");
        }

        /**
         * Dependency provider for <b>tooling</b> with <b>androidx.compose.ui:ui-tooling</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTooling() {
            return create("compose.ui.tooling");
        }

        /**
         * Dependency provider for <b>toolingpreview</b> with <b>androidx.compose.ui:ui-tooling-preview</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getToolingpreview() {
            return create("compose.ui.toolingpreview");
        }

        /**
         * Dependency provider for <b>unit</b> with <b>androidx.compose.ui:ui-unit</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getUnit() {
            return create("compose.ui.unit");
        }

        /**
         * Dependency provider for <b>util</b> with <b>androidx.compose.ui:ui-util</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getUtil() {
            return create("compose.ui.util");
        }

        /**
         * Group of libraries at <b>compose.ui.test</b>
         */
        public ComposeUiTestLibraryAccessors getTest() {
            return laccForComposeUiTestLibraryAccessors;
        }

    }

    public static class ComposeUiTestLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public ComposeUiTestLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>test</b> with <b>androidx.compose.ui:ui-test</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("compose.ui.test");
        }

        /**
         * Dependency provider for <b>junit4</b> with <b>androidx.compose.ui:ui-test-junit4</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit4() {
            return create("compose.ui.test.junit4");
        }

        /**
         * Dependency provider for <b>manifest</b> with <b>androidx.compose.ui:ui-test-manifest</b> coordinates and
         * with <b>no version specified</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getManifest() {
            return create("compose.ui.test.manifest");
        }

    }

    public static class DaggerLibraryAccessors extends SubDependencyFactory {

        public DaggerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>hiltandroid</b> with <b>com.google.dagger:hilt-android</b> coordinates and
         * with version reference <b>googledagger</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHiltandroid() {
            return create("dagger.hiltandroid");
        }

        /**
         * Dependency provider for <b>hiltandroidcompiler</b> with <b>com.google.dagger:hilt-android-compiler</b> coordinates and
         * with version reference <b>googledagger</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHiltandroidcompiler() {
            return create("dagger.hiltandroidcompiler");
        }

        /**
         * Dependency provider for <b>hiltandroidplugin</b> with <b>com.google.dagger:hilt-android-gradle-plugin</b> coordinates and
         * with version reference <b>googledagger</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHiltandroidplugin() {
            return create("dagger.hiltandroidplugin");
        }

        /**
         * Dependency provider for <b>hiltandroidtesting</b> with <b>com.google.dagger:hilt-android-testing</b> coordinates and
         * with version reference <b>googledagger</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHiltandroidtesting() {
            return create("dagger.hiltandroidtesting");
        }

    }

    public static class GoogleLibraryAccessors extends SubDependencyFactory {

        public GoogleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>generativeai</b> with <b>com.google.ai.client.generativeai:generativeai</b> coordinates and
         * with version <b>0.9.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGenerativeai() {
            return create("google.generativeai");
        }

    }

    public static class HiltLibraryAccessors extends SubDependencyFactory {
        private final HiltExtLibraryAccessors laccForHiltExtLibraryAccessors = new HiltExtLibraryAccessors(owner);

        public HiltLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>navigationcompose</b> with <b>androidx.hilt:hilt-navigation-compose</b> coordinates and
         * with version reference <b>androidx.hilt</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getNavigationcompose() {
            return create("hilt.navigationcompose");
        }

        /**
         * Group of libraries at <b>hilt.ext</b>
         */
        public HiltExtLibraryAccessors getExt() {
            return laccForHiltExtLibraryAccessors;
        }

    }

    public static class HiltExtLibraryAccessors extends SubDependencyFactory {

        public HiltExtLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>compiler</b> with <b>androidx.hilt:hilt-compiler</b> coordinates and
         * with version reference <b>androidx.hilt</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCompiler() {
            return create("hilt.ext.compiler");
        }

        /**
         * Dependency provider for <b>work</b> with <b>androidx.hilt:hilt-work</b> coordinates and
         * with version reference <b>androidx.hilt</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getWork() {
            return create("hilt.ext.work");
        }

    }

    public static class IoLibraryAccessors extends SubDependencyFactory {
        private final IoGrpcLibraryAccessors laccForIoGrpcLibraryAccessors = new IoGrpcLibraryAccessors(owner);

        public IoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>io.grpc</b>
         */
        public IoGrpcLibraryAccessors getGrpc() {
            return laccForIoGrpcLibraryAccessors;
        }

    }

    public static class IoGrpcLibraryAccessors extends SubDependencyFactory {
        private final IoGrpcGrpcLibraryAccessors laccForIoGrpcGrpcLibraryAccessors = new IoGrpcGrpcLibraryAccessors(owner);
        private final IoGrpcProtobufLibraryAccessors laccForIoGrpcProtobufLibraryAccessors = new IoGrpcProtobufLibraryAccessors(owner);

        public IoGrpcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>io.grpc.grpc</b>
         */
        public IoGrpcGrpcLibraryAccessors getGrpc() {
            return laccForIoGrpcGrpcLibraryAccessors;
        }

        /**
         * Group of libraries at <b>io.grpc.protobuf</b>
         */
        public IoGrpcProtobufLibraryAccessors getProtobuf() {
            return laccForIoGrpcProtobufLibraryAccessors;
        }

    }

    public static class IoGrpcGrpcLibraryAccessors extends SubDependencyFactory {

        public IoGrpcGrpcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>android</b> with <b>io.grpc:grpc-android</b> coordinates and
         * with version <b>1.68.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAndroid() {
            return create("io.grpc.grpc.android");
        }

        /**
         * Dependency provider for <b>binder</b> with <b>io.grpc:grpc-binder</b> coordinates and
         * with version <b>1.68.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBinder() {
            return create("io.grpc.grpc.binder");
        }

        /**
         * Dependency provider for <b>kotlin</b> with <b>io.grpc:grpc-kotlin-stub</b> coordinates and
         * with version <b>1.4.1</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKotlin() {
            return create("io.grpc.grpc.kotlin");
        }

    }

    public static class IoGrpcProtobufLibraryAccessors extends SubDependencyFactory {

        public IoGrpcProtobufLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>lite</b> with <b>io.grpc:grpc-protobuf-lite</b> coordinates and
         * with version <b>1.68.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLite() {
            return create("io.grpc.protobuf.lite");
        }

    }

    public static class KotlinLibraryAccessors extends SubDependencyFactory {

        public KotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>gradlePlugin</b> with <b>org.jetbrains.kotlin:kotlin-gradle-plugin</b> coordinates and
         * with version reference <b>kotlin</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGradlePlugin() {
            return create("kotlin.gradlePlugin");
        }

        /**
         * Dependency provider for <b>reflect</b> with <b>org.jetbrains.kotlin:kotlin-reflect</b> coordinates and
         * with version reference <b>kotlin</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getReflect() {
            return create("kotlin.reflect");
        }

        /**
         * Dependency provider for <b>stdlib</b> with <b>org.jetbrains.kotlin:kotlin-stdlib</b> coordinates and
         * with version reference <b>kotlin</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getStdlib() {
            return create("kotlin.stdlib");
        }

    }

    public static class KotlinxLibraryAccessors extends SubDependencyFactory {
        private final KotlinxCoroutinesLibraryAccessors laccForKotlinxCoroutinesLibraryAccessors = new KotlinxCoroutinesLibraryAccessors(owner);
        private final KotlinxSerializationLibraryAccessors laccForKotlinxSerializationLibraryAccessors = new KotlinxSerializationLibraryAccessors(owner);

        public KotlinxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>kotlinx.coroutines</b>
         */
        public KotlinxCoroutinesLibraryAccessors getCoroutines() {
            return laccForKotlinxCoroutinesLibraryAccessors;
        }

        /**
         * Group of libraries at <b>kotlinx.serialization</b>
         */
        public KotlinxSerializationLibraryAccessors getSerialization() {
            return laccForKotlinxSerializationLibraryAccessors;
        }

    }

    public static class KotlinxCoroutinesLibraryAccessors extends SubDependencyFactory {

        public KotlinxCoroutinesLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>android</b> with <b>org.jetbrains.kotlinx:kotlinx-coroutines-android</b> coordinates and
         * with version reference <b>kotlinxCoroutine</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAndroid() {
            return create("kotlinx.coroutines.android");
        }

        /**
         * Dependency provider for <b>core</b> with <b>org.jetbrains.kotlinx:kotlinx-coroutines-core</b> coordinates and
         * with version reference <b>kotlinxCoroutine</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("kotlinx.coroutines.core");
        }

        /**
         * Dependency provider for <b>guava</b> with <b>org.jetbrains.kotlinx:kotlinx-coroutines-guava</b> coordinates and
         * with version reference <b>kotlinxCoroutine</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGuava() {
            return create("kotlinx.coroutines.guava");
        }

        /**
         * Dependency provider for <b>playservices</b> with <b>org.jetbrains.kotlinx:kotlinx-coroutines-play-services</b> coordinates and
         * with version reference <b>kotlinxCoroutine</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPlayservices() {
            return create("kotlinx.coroutines.playservices");
        }

        /**
         * Dependency provider for <b>test</b> with <b>org.jetbrains.kotlinx:kotlinx-coroutines-test</b> coordinates and
         * with version reference <b>kotlinxCoroutine</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTest() {
            return create("kotlinx.coroutines.test");
        }

    }

    public static class KotlinxSerializationLibraryAccessors extends SubDependencyFactory {

        public KotlinxSerializationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>org.jetbrains.kotlinx:kotlinx-serialization-core</b> coordinates and
         * with version reference <b>kotlinxSerialization</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("kotlinx.serialization.core");
        }

    }

    public static class LottieLibraryAccessors extends SubDependencyFactory {

        public LottieLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>compose</b> with <b>com.airbnb.android:lottie-compose</b> coordinates and
         * with version <b>6.5.2</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCompose() {
            return create("lottie.compose");
        }

    }

    public static class MikepenzLibraryAccessors extends SubDependencyFactory {

        public MikepenzLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>markdown</b> with <b>com.mikepenz:multiplatform-markdown-renderer</b> coordinates and
         * with version <b>0.20.0-coil2</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMarkdown() {
            return create("mikepenz.markdown");
        }

    }

    public static class MoshiLibraryAccessors extends SubDependencyFactory {
        private final MoshiKotlinLibraryAccessors laccForMoshiKotlinLibraryAccessors = new MoshiKotlinLibraryAccessors(owner);

        public MoshiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>adapters</b> with <b>com.squareup.moshi:moshi-adapters</b> coordinates and
         * with version reference <b>moshi</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAdapters() {
            return create("moshi.adapters");
        }

        /**
         * Group of libraries at <b>moshi.kotlin</b>
         */
        public MoshiKotlinLibraryAccessors getKotlin() {
            return laccForMoshiKotlinLibraryAccessors;
        }

    }

    public static class MoshiKotlinLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public MoshiKotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>kotlin</b> with <b>com.squareup.moshi:moshi-kotlin</b> coordinates and
         * with version reference <b>moshi</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("moshi.kotlin");
        }

        /**
         * Dependency provider for <b>codegen</b> with <b>com.squareup.moshi:moshi-kotlin-codegen</b> coordinates and
         * with version reference <b>moshi</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCodegen() {
            return create("moshi.kotlin.codegen");
        }

    }

    public static class PlayservicesLibraryAccessors extends SubDependencyFactory {

        public PlayservicesLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>auth</b> with <b>com.google.android.gms:play-services-auth</b> coordinates and
         * with version reference <b>playServicesAuth</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAuth() {
            return create("playservices.auth");
        }

        /**
         * Dependency provider for <b>base</b> with <b>com.google.android.gms:play-services-base</b> coordinates and
         * with version <b>18.5.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBase() {
            return create("playservices.base");
        }

        /**
         * Dependency provider for <b>wearable</b> with <b>com.google.android.gms:play-services-wearable</b> coordinates and
         * with version <b>18.2.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getWearable() {
            return create("playservices.wearable");
        }

    }

    public static class ProtobufLibraryAccessors extends SubDependencyFactory {
        private final ProtobufKotlinLibraryAccessors laccForProtobufKotlinLibraryAccessors = new ProtobufKotlinLibraryAccessors(owner);
        private final ProtobufProtocLibraryAccessors laccForProtobufProtocLibraryAccessors = new ProtobufProtocLibraryAccessors(owner);

        public ProtobufLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>protobuf.kotlin</b>
         */
        public ProtobufKotlinLibraryAccessors getKotlin() {
            return laccForProtobufKotlinLibraryAccessors;
        }

        /**
         * Group of libraries at <b>protobuf.protoc</b>
         */
        public ProtobufProtocLibraryAccessors getProtoc() {
            return laccForProtobufProtocLibraryAccessors;
        }

    }

    public static class ProtobufKotlinLibraryAccessors extends SubDependencyFactory {

        public ProtobufKotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>lite</b> with <b>com.google.protobuf:protobuf-kotlin-lite</b> coordinates and
         * with version reference <b>protobuf</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLite() {
            return create("protobuf.kotlin.lite");
        }

    }

    public static class ProtobufProtocLibraryAccessors extends SubDependencyFactory {
        private final ProtobufProtocGenLibraryAccessors laccForProtobufProtocGenLibraryAccessors = new ProtobufProtocGenLibraryAccessors(owner);

        public ProtobufProtocLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>stnd</b> with <b>com.google.protobuf:protoc</b> coordinates and
         * with version reference <b>protobuf</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getStnd() {
            return create("protobuf.protoc.stnd");
        }

        /**
         * Group of libraries at <b>protobuf.protoc.gen</b>
         */
        public ProtobufProtocGenLibraryAccessors getGen() {
            return laccForProtobufProtocGenLibraryAccessors;
        }

    }

    public static class ProtobufProtocGenLibraryAccessors extends SubDependencyFactory {
        private final ProtobufProtocGenGrpcLibraryAccessors laccForProtobufProtocGenGrpcLibraryAccessors = new ProtobufProtocGenGrpcLibraryAccessors(owner);

        public ProtobufProtocGenLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>javalite</b> with <b>com.google.protobuf:protoc-gen-javalite</b> coordinates and
         * with version reference <b>protobuf.gen.javalite</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJavalite() {
            return create("protobuf.protoc.gen.javalite");
        }

        /**
         * Group of libraries at <b>protobuf.protoc.gen.grpc</b>
         */
        public ProtobufProtocGenGrpcLibraryAccessors getGrpc() {
            return laccForProtobufProtocGenGrpcLibraryAccessors;
        }

    }

    public static class ProtobufProtocGenGrpcLibraryAccessors extends SubDependencyFactory {

        public ProtobufProtocGenGrpcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>java</b> with <b>io.grpc:protoc-gen-grpc-java</b> coordinates and
         * with version reference <b>protobuf.gen.grpc.java</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJava() {
            return create("protobuf.protoc.gen.grpc.java");
        }

        /**
         * Dependency provider for <b>kotlin</b> with <b>io.grpc:protoc-gen-grpc-kotlin</b> coordinates and
         * with version reference <b>protobuf.gen.grpc.kotlin</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKotlin() {
            return create("protobuf.protoc.gen.grpc.kotlin");
        }

    }

    public static class Retrofit2LibraryAccessors extends SubDependencyFactory {

        public Retrofit2LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>convertermoshi</b> with <b>com.squareup.retrofit2:converter-moshi</b> coordinates and
         * with version reference <b>com.squareup.retrofit2</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getConvertermoshi() {
            return create("retrofit2.convertermoshi");
        }

        /**
         * Dependency provider for <b>retrofit</b> with <b>com.squareup.retrofit2:retrofit</b> coordinates and
         * with version reference <b>com.squareup.retrofit2</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRetrofit() {
            return create("retrofit2.retrofit");
        }

    }

    public static class RobolectricLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public RobolectricLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>robolectric</b> with <b>org.robolectric:robolectric</b> coordinates and
         * with version reference <b>org.robolectric</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("robolectric");
        }

        /**
         * Dependency provider for <b>shadows</b> with <b>org.robolectric:shadows-framework</b> coordinates and
         * with version reference <b>org.robolectric</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getShadows() {
            return create("robolectric.shadows");
        }

    }

    public static class RoborazziLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public RoborazziLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>roborazzi</b> with <b>io.github.takahirom.roborazzi:roborazzi</b> coordinates and
         * with version reference <b>roborazzi</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("roborazzi");
        }

        /**
         * Dependency provider for <b>compose</b> with <b>io.github.takahirom.roborazzi:roborazzi-compose</b> coordinates and
         * with version reference <b>roborazzi</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCompose() {
            return create("roborazzi.compose");
        }

        /**
         * Dependency provider for <b>painter</b> with <b>io.github.takahirom.roborazzi:roborazzi-painter</b> coordinates and
         * with version reference <b>roborazzi</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPainter() {
            return create("roborazzi.painter");
        }

        /**
         * Dependency provider for <b>rule</b> with <b>io.github.takahirom.roborazzi:roborazzi-junit-rule</b> coordinates and
         * with version reference <b>roborazzi</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRule() {
            return create("roborazzi.rule");
        }

    }

    public static class RoomLibraryAccessors extends SubDependencyFactory {

        public RoomLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>common</b> with <b>androidx.room:room-common</b> coordinates and
         * with version reference <b>room</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCommon() {
            return create("room.common");
        }

        /**
         * Dependency provider for <b>compiler</b> with <b>androidx.room:room-compiler</b> coordinates and
         * with version reference <b>room</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCompiler() {
            return create("room.compiler");
        }

        /**
         * Dependency provider for <b>ktx</b> with <b>androidx.room:room-ktx</b> coordinates and
         * with version reference <b>room</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKtx() {
            return create("room.ktx");
        }

    }

    public static class WearcomposeLibraryAccessors extends SubDependencyFactory {

        public WearcomposeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>foundation</b> with <b>androidx.wear.compose:compose-foundation</b> coordinates and
         * with version reference <b>wearcompose</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getFoundation() {
            return create("wearcompose.foundation");
        }

        /**
         * Dependency provider for <b>material</b> with <b>androidx.wear.compose:compose-material</b> coordinates and
         * with version reference <b>wearcompose</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMaterial() {
            return create("wearcompose.material");
        }

        /**
         * Dependency provider for <b>navigation</b> with <b>androidx.wear.compose:compose-navigation</b> coordinates and
         * with version reference <b>wearcompose</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getNavigation() {
            return create("wearcompose.navigation");
        }

        /**
         * Dependency provider for <b>tooling</b> with <b>androidx.wear.compose:compose-ui-tooling</b> coordinates and
         * with version reference <b>wearcompose</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTooling() {
            return create("wearcompose.tooling");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final AndroidxVersionAccessors vaccForAndroidxVersionAccessors = new AndroidxVersionAccessors(providers, config);
        private final AppVersionAccessors vaccForAppVersionAccessors = new AppVersionAccessors(providers, config);
        private final ComVersionAccessors vaccForComVersionAccessors = new ComVersionAccessors(providers, config);
        private final ComposeVersionAccessors vaccForComposeVersionAccessors = new ComposeVersionAccessors(providers, config);
        private final IoVersionAccessors vaccForIoVersionAccessors = new IoVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        private final ProtobufVersionAccessors vaccForProtobufVersionAccessors = new ProtobufVersionAccessors(providers, config);
        private final TilesVersionAccessors vaccForTilesVersionAccessors = new TilesVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>accompanist</b> with value <b>0.36.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAccompanist() { return getVersion("accompanist"); }

        /**
         * Version alias <b>androidxActivity</b> with value <b>1.9.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxActivity() { return getVersion("androidxActivity"); }

        /**
         * Version alias <b>androidxComposeBom</b> with value <b>2024.09.00-alpha</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxComposeBom() { return getVersion("androidxComposeBom"); }

        /**
         * Version alias <b>androidxCore</b> with value <b>1.13.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxCore() { return getVersion("androidxCore"); }

        /**
         * Version alias <b>androidxLifecycle</b> with value <b>2.8.6</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxLifecycle() { return getVersion("androidxLifecycle"); }

        /**
         * Version alias <b>androidxNavigation</b> with value <b>2.8.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxNavigation() { return getVersion("androidxNavigation"); }

        /**
         * Version alias <b>androidxPhoneInteractions</b> with value <b>1.1.0-alpha04</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxPhoneInteractions() { return getVersion("androidxPhoneInteractions"); }

        /**
         * Version alias <b>androidxRemoteInteractions</b> with value <b>1.1.0-rc01</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxRemoteInteractions() { return getVersion("androidxRemoteInteractions"); }

        /**
         * Version alias <b>androidxStartup</b> with value <b>1.2.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxStartup() { return getVersion("androidxStartup"); }

        /**
         * Version alias <b>androidxTracing</b> with value <b>1.2.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxTracing() { return getVersion("androidxTracing"); }

        /**
         * Version alias <b>androidxWear</b> with value <b>1.3.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxWear() { return getVersion("androidxWear"); }

        /**
         * Version alias <b>androidxWork</b> with value <b>2.9.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxWork() { return getVersion("androidxWork"); }

        /**
         * Version alias <b>androidxprotolayout</b> with value <b>1.2.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxprotolayout() { return getVersion("androidxprotolayout"); }

        /**
         * Version alias <b>androidxtiles</b> with value <b>1.4.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAndroidxtiles() { return getVersion("androidxtiles"); }

        /**
         * Version alias <b>annotation</b> with value <b>1.0.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAnnotation() { return getVersion("annotation"); }

        /**
         * Version alias <b>appcompat</b> with value <b>1.7.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAppcompat() { return getVersion("appcompat"); }

        /**
         * Version alias <b>composesnapshot</b> with value <b>-</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getComposesnapshot() { return getVersion("composesnapshot"); }

        /**
         * Version alias <b>dependencyAnalysis</b> with value <b>2.2.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getDependencyAnalysis() { return getVersion("dependencyAnalysis"); }

        /**
         * Version alias <b>dokka</b> with value <b>1.9.20</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getDokka() { return getVersion("dokka"); }

        /**
         * Version alias <b>googledagger</b> with value <b>2.52</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getGoogledagger() { return getVersion("googledagger"); }

        /**
         * Version alias <b>gradlePlugin</b> with value <b>8.7.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getGradlePlugin() { return getVersion("gradlePlugin"); }

        /**
         * Version alias <b>gradlePublishPlugin</b> with value <b>0.30.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getGradlePublishPlugin() { return getVersion("gradlePublishPlugin"); }

        /**
         * Version alias <b>junit</b> with value <b>4.13.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getJunit() { return getVersion("junit"); }

        /**
         * Version alias <b>kotlin</b> with value <b>2.0.21</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getKotlin() { return getVersion("kotlin"); }

        /**
         * Version alias <b>kotlinxCoroutine</b> with value <b>1.9.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getKotlinxCoroutine() { return getVersion("kotlinxCoroutine"); }

        /**
         * Version alias <b>kotlinxSerialization</b> with value <b>1.7.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getKotlinxSerialization() { return getVersion("kotlinxSerialization"); }

        /**
         * Version alias <b>ksp</b> with value <b>2.0.21-1.0.25</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getKsp() { return getVersion("ksp"); }

        /**
         * Version alias <b>ktlint</b> with value <b>0.50.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getKtlint() { return getVersion("ktlint"); }

        /**
         * Version alias <b>material</b> with value <b>1.12.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getMaterial() { return getVersion("material"); }

        /**
         * Version alias <b>metalava</b> with value <b>0.3.5</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getMetalava() { return getVersion("metalava"); }

        /**
         * Version alias <b>moshi</b> with value <b>1.15.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getMoshi() { return getVersion("moshi"); }

        /**
         * Version alias <b>okio</b> with value <b>3.9.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getOkio() { return getVersion("okio"); }

        /**
         * Version alias <b>playServicesAuth</b> with value <b>21.2.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getPlayServicesAuth() { return getVersion("playServicesAuth"); }

        /**
         * Version alias <b>roborazzi</b> with value <b>1.29.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getRoborazzi() { return getVersion("roborazzi"); }

        /**
         * Version alias <b>room</b> with value <b>2.6.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getRoom() { return getVersion("room"); }

        /**
         * Version alias <b>runtimeTracing</b> with value <b>1.7.4</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getRuntimeTracing() { return getVersion("runtimeTracing"); }

        /**
         * Version alias <b>spotless</b> with value <b>6.25.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getSpotless() { return getVersion("spotless"); }

        /**
         * Version alias <b>truth</b> with value <b>1.4.4</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getTruth() { return getVersion("truth"); }

        /**
         * Version alias <b>wearInput</b> with value <b>1.2.0-alpha02</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getWearInput() { return getVersion("wearInput"); }

        /**
         * Version alias <b>wearToolingPreview</b> with value <b>1.0.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getWearToolingPreview() { return getVersion("wearToolingPreview"); }

        /**
         * Version alias <b>wearcompose</b> with value <b>1.5.0-alpha04</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getWearcompose() { return getVersion("wearcompose"); }

        /**
         * Group of versions at <b>versions.androidx</b>
         */
        public AndroidxVersionAccessors getAndroidx() {
            return vaccForAndroidxVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.app</b>
         */
        public AppVersionAccessors getApp() {
            return vaccForAppVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com</b>
         */
        public ComVersionAccessors getCom() {
            return vaccForComVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.compose</b>
         */
        public ComposeVersionAccessors getCompose() {
            return vaccForComposeVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.io</b>
         */
        public IoVersionAccessors getIo() {
            return vaccForIoVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org</b>
         */
        public OrgVersionAccessors getOrg() {
            return vaccForOrgVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.protobuf</b>
         */
        public ProtobufVersionAccessors getProtobuf() {
            return vaccForProtobufVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.tiles</b>
         */
        public TilesVersionAccessors getTiles() {
            return vaccForTilesVersionAccessors;
        }

    }

    public static class AndroidxVersionAccessors extends VersionFactory  {

        private final AndroidxComplicationsVersionAccessors vaccForAndroidxComplicationsVersionAccessors = new AndroidxComplicationsVersionAccessors(providers, config);
        private final AndroidxConstraintlayoutVersionAccessors vaccForAndroidxConstraintlayoutVersionAccessors = new AndroidxConstraintlayoutVersionAccessors(providers, config);
        private final AndroidxHealthVersionAccessors vaccForAndroidxHealthVersionAccessors = new AndroidxHealthVersionAccessors(providers, config);
        private final AndroidxTestVersionAccessors vaccForAndroidxTestVersionAccessors = new AndroidxTestVersionAccessors(providers, config);
        private final AndroidxWearVersionAccessors vaccForAndroidxWearVersionAccessors = new AndroidxWearVersionAccessors(providers, config);
        public AndroidxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>androidx.benchmark</b> with value <b>1.3.3</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getBenchmark() { return getVersion("androidx.benchmark"); }

        /**
         * Version alias <b>androidx.concurrent</b> with value <b>1.2.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getConcurrent() { return getVersion("androidx.concurrent"); }

        /**
         * Version alias <b>androidx.datastore</b> with value <b>1.1.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getDatastore() { return getVersion("androidx.datastore"); }

        /**
         * Version alias <b>androidx.hilt</b> with value <b>1.2.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getHilt() { return getVersion("androidx.hilt"); }

        /**
         * Version alias <b>androidx.media3</b> with value <b>1.4.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getMedia3() { return getVersion("androidx.media3"); }

        /**
         * Group of versions at <b>versions.androidx.complications</b>
         */
        public AndroidxComplicationsVersionAccessors getComplications() {
            return vaccForAndroidxComplicationsVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.androidx.constraintlayout</b>
         */
        public AndroidxConstraintlayoutVersionAccessors getConstraintlayout() {
            return vaccForAndroidxConstraintlayoutVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.androidx.health</b>
         */
        public AndroidxHealthVersionAccessors getHealth() {
            return vaccForAndroidxHealthVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.androidx.test</b>
         */
        public AndroidxTestVersionAccessors getTest() {
            return vaccForAndroidxTestVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.androidx.wear</b>
         */
        public AndroidxWearVersionAccessors getWear() {
            return vaccForAndroidxWearVersionAccessors;
        }

    }

    public static class AndroidxComplicationsVersionAccessors extends VersionFactory  {

        public AndroidxComplicationsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>androidx.complications.data</b> with value <b>1.2.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getData() { return getVersion("androidx.complications.data"); }

    }

    public static class AndroidxConstraintlayoutVersionAccessors extends VersionFactory  {

        public AndroidxConstraintlayoutVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>androidx.constraintlayout.compose</b> with value <b>1.0.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getCompose() { return getVersion("androidx.constraintlayout.compose"); }

    }

    public static class AndroidxHealthVersionAccessors extends VersionFactory  {

        public AndroidxHealthVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>androidx.health.services</b> with value <b>1.0.0-rc02</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getServices() { return getVersion("androidx.health.services"); }

    }

    public static class AndroidxTestVersionAccessors extends VersionFactory  {

        public AndroidxTestVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>androidx.test.espresso</b> with value <b>3.6.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getEspresso() { return getVersion("androidx.test.espresso"); }

        /**
         * Version alias <b>androidx.test.ext</b> with value <b>1.2.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getExt() { return getVersion("androidx.test.ext"); }

        /**
         * Version alias <b>androidx.test.runner</b> with value <b>1.6.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getRunner() { return getVersion("androidx.test.runner"); }

    }

    public static class AndroidxWearVersionAccessors extends VersionFactory  {

        public AndroidxWearVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>androidx.wear.watchface</b> with value <b>1.2.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getWatchface() { return getVersion("androidx.wear.watchface"); }

    }

    public static class AppVersionAccessors extends VersionFactory  {

        private final AppCashVersionAccessors vaccForAppCashVersionAccessors = new AppCashVersionAccessors(providers, config);
        public AppVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.app.cash</b>
         */
        public AppCashVersionAccessors getCash() {
            return vaccForAppCashVersionAccessors;
        }

    }

    public static class AppCashVersionAccessors extends VersionFactory  {

        public AppCashVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>app.cash.turbine</b> with value <b>1.2.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getTurbine() { return getVersion("app.cash.turbine"); }

    }

    public static class ComVersionAccessors extends VersionFactory  {

        private final ComSquareupVersionAccessors vaccForComSquareupVersionAccessors = new ComSquareupVersionAccessors(providers, config);
        public ComVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.squareup</b>
         */
        public ComSquareupVersionAccessors getSquareup() {
            return vaccForComSquareupVersionAccessors;
        }

    }

    public static class ComSquareupVersionAccessors extends VersionFactory  {

        public ComSquareupVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.squareup.okhttp3</b> with value <b>5.0.0-alpha.14</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getOkhttp3() { return getVersion("com.squareup.okhttp3"); }

        /**
         * Version alias <b>com.squareup.retrofit2</b> with value <b>2.11.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getRetrofit2() { return getVersion("com.squareup.retrofit2"); }

    }

    public static class ComposeVersionAccessors extends VersionFactory  {

        public ComposeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>compose.material3</b> with value <b>1.3.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getMaterial3() { return getVersion("compose.material3"); }

    }

    public static class IoVersionAccessors extends VersionFactory  {

        private final IoCoilVersionAccessors vaccForIoCoilVersionAccessors = new IoCoilVersionAccessors(providers, config);
        public IoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.io.coil</b>
         */
        public IoCoilVersionAccessors getCoil() {
            return vaccForIoCoilVersionAccessors;
        }

    }

    public static class IoCoilVersionAccessors extends VersionFactory  {

        public IoCoilVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>io.coil.kt</b> with value <b>2.7.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getKt() { return getVersion("io.coil.kt"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.robolectric</b> with value <b>4.13</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getRobolectric() { return getVersion("org.robolectric"); }

    }

    public static class ProtobufVersionAccessors extends VersionFactory  implements VersionNotationSupplier {

        private final ProtobufGenVersionAccessors vaccForProtobufGenVersionAccessors = new ProtobufGenVersionAccessors(providers, config);
        public ProtobufVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>protobuf</b> with value <b>4.28.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> asProvider() { return getVersion("protobuf"); }

        /**
         * Group of versions at <b>versions.protobuf.gen</b>
         */
        public ProtobufGenVersionAccessors getGen() {
            return vaccForProtobufGenVersionAccessors;
        }

    }

    public static class ProtobufGenVersionAccessors extends VersionFactory  {

        private final ProtobufGenGrpcVersionAccessors vaccForProtobufGenGrpcVersionAccessors = new ProtobufGenGrpcVersionAccessors(providers, config);
        public ProtobufGenVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>protobuf.gen.javalite</b> with value <b>3.0.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getJavalite() { return getVersion("protobuf.gen.javalite"); }

        /**
         * Group of versions at <b>versions.protobuf.gen.grpc</b>
         */
        public ProtobufGenGrpcVersionAccessors getGrpc() {
            return vaccForProtobufGenGrpcVersionAccessors;
        }

    }

    public static class ProtobufGenGrpcVersionAccessors extends VersionFactory  {

        public ProtobufGenGrpcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>protobuf.gen.grpc.java</b> with value <b>1.68.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getJava() { return getVersion("protobuf.gen.grpc.java"); }

        /**
         * Version alias <b>protobuf.gen.grpc.kotlin</b> with value <b>1.3.0:jdk8@jar</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getKotlin() { return getVersion("protobuf.gen.grpc.kotlin"); }

    }

    public static class TilesVersionAccessors extends VersionFactory  {

        private final TilesToolingVersionAccessors vaccForTilesToolingVersionAccessors = new TilesToolingVersionAccessors(providers, config);
        public TilesVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.tiles.tooling</b>
         */
        public TilesToolingVersionAccessors getTooling() {
            return vaccForTilesToolingVersionAccessors;
        }

    }

    public static class TilesToolingVersionAccessors extends VersionFactory  {

        public TilesToolingVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>tiles.tooling.preview</b> with value <b>1.4.1</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getPreview() { return getVersion("tiles.tooling.preview"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final ComposePluginAccessors paccForComposePluginAccessors = new ComposePluginAccessors(providers, config);
        private final KotlinxPluginAccessors paccForKotlinxPluginAccessors = new KotlinxPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>dependencyAnalysis</b> with plugin id <b>com.autonomousapps.dependency-analysis</b> and
         * with version reference <b>dependencyAnalysis</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getDependencyAnalysis() { return createPlugin("dependencyAnalysis"); }

        /**
         * Plugin provider for <b>dokka</b> with plugin id <b>org.jetbrains.dokka</b> and
         * with version reference <b>dokka</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getDokka() { return createPlugin("dokka"); }

        /**
         * Plugin provider for <b>gradleMavenPublishPlugin</b> with plugin id <b>com.vanniktech.maven.publish</b> and
         * with version reference <b>gradlePublishPlugin</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getGradleMavenPublishPlugin() { return createPlugin("gradleMavenPublishPlugin"); }

        /**
         * Plugin provider for <b>kotlinGradle</b> with plugin id <b>org.jetbrains.kotlin.android</b> and
         * with version reference <b>kotlin</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getKotlinGradle() { return createPlugin("kotlinGradle"); }

        /**
         * Plugin provider for <b>ksp</b> with plugin id <b>com.google.devtools.ksp</b> and
         * with version reference <b>ksp</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getKsp() { return createPlugin("ksp"); }

        /**
         * Plugin provider for <b>metalavaGradle</b> with plugin id <b>me.tylerbwong.gradle.metalava</b> and
         * with version reference <b>metalava</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getMetalavaGradle() { return createPlugin("metalavaGradle"); }

        /**
         * Plugin provider for <b>protobuf</b> with plugin id <b>com.google.protobuf</b> and
         * with version <b>0.9.4</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getProtobuf() { return createPlugin("protobuf"); }

        /**
         * Plugin provider for <b>roborazzi</b> with plugin id <b>io.github.takahirom.roborazzi</b> and
         * with version reference <b>roborazzi</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getRoborazzi() { return createPlugin("roborazzi"); }

        /**
         * Plugin provider for <b>spotless</b> with plugin id <b>com.diffplug.spotless</b> and
         * with version reference <b>spotless</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getSpotless() { return createPlugin("spotless"); }

        /**
         * Group of plugins at <b>plugins.compose</b>
         */
        public ComposePluginAccessors getCompose() {
            return paccForComposePluginAccessors;
        }

        /**
         * Group of plugins at <b>plugins.kotlinx</b>
         */
        public KotlinxPluginAccessors getKotlinx() {
            return paccForKotlinxPluginAccessors;
        }

    }

    public static class ComposePluginAccessors extends PluginFactory {

        public ComposePluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>compose.compiler</b> with plugin id <b>org.jetbrains.kotlin.plugin.compose</b> and
         * with version reference <b>kotlin</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getCompiler() { return createPlugin("compose.compiler"); }

    }

    public static class KotlinxPluginAccessors extends PluginFactory {

        public KotlinxPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>kotlinx.serialization</b> with plugin id <b>org.jetbrains.kotlin.plugin.serialization</b> and
         * with version reference <b>kotlin</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getSerialization() { return createPlugin("kotlinx.serialization"); }

    }

}
