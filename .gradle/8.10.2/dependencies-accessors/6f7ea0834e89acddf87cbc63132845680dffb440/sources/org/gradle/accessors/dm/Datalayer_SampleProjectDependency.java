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
public class Datalayer_SampleProjectDependency extends DelegatingProjectDependency {

    @Inject
    public Datalayer_SampleProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":datalayer:sample:phone"
     */
    public Datalayer_Sample_PhoneProjectDependency getPhone() { return new Datalayer_Sample_PhoneProjectDependency(getFactory(), create(":datalayer:sample:phone")); }

    /**
     * Creates a project dependency on the project at path ":datalayer:sample:shared"
     */
    public Datalayer_Sample_SharedProjectDependency getShared() { return new Datalayer_Sample_SharedProjectDependency(getFactory(), create(":datalayer:sample:shared")); }

    /**
     * Creates a project dependency on the project at path ":datalayer:sample:wear"
     */
    public Datalayer_Sample_WearProjectDependency getWear() { return new Datalayer_Sample_WearProjectDependency(getFactory(), create(":datalayer:sample:wear")); }

}
