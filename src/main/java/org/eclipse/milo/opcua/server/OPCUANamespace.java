package org.eclipse.milo.opcua.server;

import java.util.List;
import java.util.Random;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.ManagedNamespaceWithLifecycle;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.dtd.DataTypeDictionaryManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilters;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import java.util.function.Predicate;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilter;
import org.eclipse.milo.opcua.stack.core.AttributeId;



public class OPCUANamespace extends ManagedNamespaceWithLifecycle {

    private static final String NAMESPACE_URI = "urn:eclipse:milo:meteo2";
    private final Random random = new Random();
    private final SubscriptionModel subscriptionModel;

    private static class AttributeLoggingFilter implements AttributeFilter {
        public AttributeLoggingFilter() {this(attributeId -> true);}
        public AttributeLoggingFilter(Predicate<AttributeId> attributePredicate) {}
    }

    OPCUANamespace(OpcUaServer server) {
        super(server, NAMESPACE_URI);
        subscriptionModel = new SubscriptionModel(server, this);
        DataTypeDictionaryManager dictionaryManager = new DataTypeDictionaryManager(getNodeContext(), NAMESPACE_URI);
        getLifecycleManager().addLifecycle(dictionaryManager);
        getLifecycleManager().addLifecycle(subscriptionModel);
        getLifecycleManager().addStartupTask(this::createAndAddNodes);
    }

    private void createAndAddNodes() {
        NodeId folderNodeId = newNodeId("VantagePro2");
        UaFolderNode folderNode = new UaFolderNode(
            getNodeContext(),
            folderNodeId,
            newQualifiedName("VantagePro2"),
            LocalizedText.english("VantagePro2")
        );
        getNodeManager().addNode(folderNode);
        // add root folder
        folderNode.addReference(new Reference(
            folderNode.getNodeId(),
            Identifiers.Organizes,
            Identifiers.ObjectsFolder.expanded(),
            false
        ));

        // ADD VALUE
        // serverDayMonthYear
        {   String name = "serverDayMonthYear";
            NodeId typeId = Identifiers.String;
            Variant variant = new Variant("00-00-000");
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // serverHourMinute
        {   String name = "serverHourMinute";
            NodeId typeId = Identifiers.String;
            Variant variant = new Variant("00-00");
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // meteoDayMonthYear
        {   String name = "meteoDayMonthYear";
            NodeId typeId = Identifiers.String;
            Variant variant = new Variant("00-00-0000");
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // meteoHourMinute
        {   String name = "meteoHourMinute";
            NodeId typeId = Identifiers.String;
            Variant variant = new Variant("00-00");
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // payloadDayMontYear
        {   String name = "payloadDayMontYear";
            NodeId typeId = Identifiers.String;
            Variant variant = new Variant("00-00-0000");
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // payloadHourMinute
        {   String name = "payloadHourMinute";
            NodeId typeId = Identifiers.String;
            Variant variant = new Variant("00-00");
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // payloadTempAir
        {   String name = "payloadTempAir";
            NodeId typeId = Identifiers.Double;
            Variant variant = new Variant(0);
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextDouble())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // payloadRain
        {   String name = "payloadRain";
            NodeId typeId = Identifiers.Double;
            Variant variant = new Variant(0);
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextDouble())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // payloadPressure
        {   String name = "payloadPressure";
            NodeId typeId = Identifiers.Double;
            Variant variant = new Variant(0);
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextDouble())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // payloadInsideHumidity
        {   String name = "payloadInsideHumidity";
            NodeId typeId = Identifiers.Double;
            Variant variant = new Variant(0);
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextDouble())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // payloadAverageWindSpeed
        {   String name = "payloadAverageWindSpeed";
            NodeId typeId = Identifiers.Double;
            Variant variant = new Variant(0);
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextDouble())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}


        // payloadWindDirect
        {   String name = "payloadWindDirect";
            NodeId typeId = Identifiers.Double;
            Variant variant = new Variant(0);
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextDouble())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // serverTries
        {   String name = "serverTries";
            NodeId typeId = Identifiers.Double;
            Variant variant = new Variant(0);
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextDouble())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // serverStep
        {   String name = "serverStep";
            NodeId typeId = Identifiers.Double;
            Variant variant = new Variant(0);
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextDouble())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // serverCountSync
        {   String name = "serverCountSync";
            NodeId typeId = Identifiers.Double;
            Variant variant = new Variant(0);
            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(name))
                    .setAccessLevel(AccessLevel.READ_ONLY)
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();
            node.setValue(new DataValue(variant));
            node.getFilterChain().addLast(
                    new AttributeLoggingFilter(),
                    AttributeFilters.getValue(ctx -> new DataValue(new Variant(random.nextDouble())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}
    }



    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsCreated(dataItems);
    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsModified(dataItems);
    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsDeleted(dataItems);
    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {
        subscriptionModel.onMonitoringModeChanged(monitoredItems);
    }
}
