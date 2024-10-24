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
public class DatalayerProjectDependency extends DelegatingProjectDependency {

    @Inject
    public DatalayerProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":datalayer:core"
     */
    public Datalayer_CoreProjectDependency getCore() { return new Datalayer_CoreProjectDependency(getFactory(), create(":datalayer:core")); }

    /**
     * Creates a project dependency on the project at path ":datalayer:grpc"
     */
    public Datalayer_GrpcProjectDependency getGrpc() { return new Datalayer_GrpcProjectDependency(getFactory(), create(":datalayer:grpc")); }

    /**
     * Creates a project dependency on the project at path ":datalayer:phone"
     */
    public Datalayer_PhoneProjectDependency getPhone() { return new Datalayer_PhoneProjectDependency(getFactory(), create(":datalayer:phone")); }

    /**
     * Creates a project dependency on the project at path ":datalayer:phone-ui"
     */
    public Datalayer_PhoneUiProjectDependency getPhoneUi() { return new Datalayer_PhoneUiProjectDependency(getFactory(), create(":datalayer:phone-ui")); }

    /**
     * Creates a project dependency on the project at path ":datalayer:sample"
     */
    public Datalayer_SampleProjectDependency getSample() { return new Datalayer_SampleProjectDependency(getFactory(), create(":datalayer:sample")); }

    /**
     * Creates a project dependency on the project at path ":datalayer:watch"
     */
    public Datalayer_WatchProjectDependency getWatch() { return new Datalayer_WatchProjectDependency(getFactory(), create(":datalayer:watch")); }

}
