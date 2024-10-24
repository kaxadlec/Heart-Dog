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
public class HotdogReleaseProjectDependency extends DelegatingProjectDependency {

    @Inject
    public HotdogReleaseProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":annotations"
     */
    public AnnotationsProjectDependency getAnnotations() { return new AnnotationsProjectDependency(getFactory(), create(":annotations")); }

    /**
     * Creates a project dependency on the project at path ":datalayer"
     */
    public DatalayerProjectDependency getDatalayer() { return new DatalayerProjectDependency(getFactory(), create(":datalayer")); }

}
