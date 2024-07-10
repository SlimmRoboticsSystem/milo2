package org.eclipse.milo.opcua.server;

import java.util.function.Predicate;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilter;
import org.eclipse.milo.opcua.stack.core.AttributeId;

public class AttributeLoggingFilter implements AttributeFilter {
    public AttributeLoggingFilter() {this(attributeId -> true);}
    public AttributeLoggingFilter(Predicate<AttributeId> attributePredicate) {}
}
