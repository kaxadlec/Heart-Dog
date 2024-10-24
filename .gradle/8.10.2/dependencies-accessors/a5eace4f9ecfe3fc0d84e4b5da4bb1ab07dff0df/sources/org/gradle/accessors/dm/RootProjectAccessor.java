package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class RootProjectAccessor extends TypeSafeProjectDependencyFactory {


    @Inject
    public RootProjectAccessor(DefaultProjectDependencyFactory factory, ProjectFinder finder) {
        super(factory, finder);
    }

    /**
     * Creates a project dependency on the project at path ":"
     */
    public HotdogReleaseProjectDependency getHotdogRelease() { return new HotdogReleaseProjectDependency(getFactory(), create(":")); }

    /**
     * Creates a project dependency on the project at path ":annotations"
     */
    public AnnotationsProjectDependency getAnnotations() { return new AnnotationsProjectDependency(getFactory(), create(":annotations")); }

    /**
     * Creates a project dependency on the project at path ":compose-layout"
     */
    public ComposeLayoutProjectDependency getComposeLayout() { return new ComposeLayoutProjectDependency(getFactory(), create(":compose-layout")); }

    /**
     * Creates a project dependency on the project at path ":compose-tools"
     */
    public ComposeToolsProjectDependency getComposeTools() { return new ComposeToolsProjectDependency(getFactory(), create(":compose-tools")); }

    /**
     * Creates a project dependency on the project at path ":datalayer"
     */
    public DatalayerProjectDependency getDatalayer() { return new DatalayerProjectDependency(getFactory(), create(":datalayer")); }

    /**
     * Creates a project dependency on the project at path ":tiles"
     */
    public TilesProjectDependency getTiles() { return new TilesProjectDependency(getFactory(), create(":tiles")); }

}
