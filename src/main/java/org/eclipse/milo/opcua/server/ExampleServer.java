package org.eclipse.milo.opcua.server;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.sdk.server.util.HostnameUtil;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.eclipse.milo.opcua.stack.server.EndpointConfiguration;
import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig.USER_TOKEN_POLICY_ANONYMOUS;
import org.eclipse.milo.opcua.sdk.server.identity.AnonymousIdentityValidator;


public class ExampleServer {

    private static final int TCP_BIND_PORT = 12686;

    public static void main(String[] args) throws Exception {
        ExampleServer server = new ExampleServer();
        server.startup().get();
        final CompletableFuture<Void> future = new CompletableFuture<>();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> future.complete(null)));
        future.get();
    }

    private final OpcUaServer server;
    public static final String SERVER_URI = "urn:eclipse:milo:meteo2";

    public ExampleServer() {
        Set<EndpointConfiguration> endpointConfigurations = createEndpointConfigurations();
        OpcUaServerConfig serverConfig = OpcUaServerConfig.builder()
                .setApplicationUri(SERVER_URI)
                .setApplicationName(LocalizedText.english("meteo2 OPC-UA server"))
                .setEndpoints(endpointConfigurations)
                .setBuildInfo(
                        new BuildInfo(
                                "urn:eclipse:milo:meteo2-server",
                                "eclipse",
                                "meteo2 OPC-UA server",
                                OpcUaServer.SDK_VERSION,
                                "", DateTime.now()))
                .setIdentityValidator(new AnonymousIdentityValidator())
                .setProductUri("urn:eclipse:milo:meteo2-server")
                .build();
        server = new OpcUaServer(serverConfig);
        ExampleNamespace exampleNamespace = new ExampleNamespace(server);
        exampleNamespace.startup();
    }


    private Set<EndpointConfiguration> createEndpointConfigurations() {
        Set<EndpointConfiguration> endpointConfigurations = new LinkedHashSet<>();
        List<String> bindAddresses = newArrayList();
        bindAddresses.add("0.0.0.0");
        Set<String> hostnames = new LinkedHashSet<>();
        hostnames.add(HostnameUtil.getHostname());
        hostnames.addAll(HostnameUtil.getHostnames("0.0.0.0"));
        for (String bindAddress : bindAddresses) {
            for (String hostname : hostnames) {
                EndpointConfiguration.Builder builder = EndpointConfiguration.newBuilder()
                        .setBindAddress(bindAddress)
                        .setHostname(hostname)
                        .setPath("/data")
                        .addTokenPolicies(USER_TOKEN_POLICY_ANONYMOUS);

                EndpointConfiguration.Builder noSecurityBuilder = builder.copy()
                        .setSecurityPolicy(SecurityPolicy.None)
                        .setSecurityMode(MessageSecurityMode.None);

                endpointConfigurations.add(buildTcpEndpoint(noSecurityBuilder));
            }
        }
        return endpointConfigurations;
    }

    private static EndpointConfiguration buildTcpEndpoint(EndpointConfiguration.Builder base) {
        return base.copy()
                .setTransportProfile(TransportProfile.TCP_UASC_UABINARY)
                .setBindPort(TCP_BIND_PORT)
                .build();
    }

    public CompletableFuture<OpcUaServer> startup() {return server.startup();}
}