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


public class ExampleNamespace extends ManagedNamespaceWithLifecycle {

    public static final String NAMESPACE_URI = "urn:eclipse:milo:meteo2";
    private final Random random = new Random();
    private final SubscriptionModel subscriptionModel;

    ExampleNamespace(OpcUaServer server) {
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
        // sOpcDayMonthYear
        {   String name = "sOpcDayMonthYear";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // sOpcHourMinute
        {   String name = "sOpcHourMinute";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // vOpcDayMonthYear
        {   String name = "vOpcDayMonthYear";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // vOpcHourMinute
        {   String name = "vOpcHourMinute";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // pOpcDayMontYear
        {   String name = "pOpcDayMontYear";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // pOpcHourMinute
        {   String name = "pOpcHourMinute";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // pOpcTempAir
        {   String name = "pOpcTempAir";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // pOpcRain
        {   String name = "pOpcRain";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // pOpcPressure
        {   String name = "pOpcPressure";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // pOpcInsideHumidity
        {   String name = "pOpcInsideHumidity";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // pOpcAverageWindSpeed
        {   String name = "pOpcAverageWindSpeed";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}


        // pOpcWindDirect
        {   String name = "pOpcWindDirect";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // sOpcSysTries
        {   String name = "sOpcSysTries";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // sOpcSysStep
        {   String name = "sOpcSysStep";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
            );
            getNodeManager().addNode(node);
            folderNode.addOrganizes(node);}

        // sOpcSysCountSync
        {   String name = "sOpcSysCountSync";
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
                    AttributeFilters.getValue(
                            ctx -> new DataValue(new Variant(random.nextInt())))
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
