package com.function;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

/**
 * Azure Functions with Service Bus Trigger.
 */
public class ServiceBusQueueTriggerJava1 
    /**
     * This function will be invoked when a new message is received at the Service Bus Queue.
     */
    @FunctionName("ServiceBusQueueTriggerJava1")
    public void run(
            @ServiceBusQueueTrigger(
                name = "message", 
                queueName = "processedsymbols", 
                connection = "demolcsappdevsymboldemosb1_SERVICEBUS"
                ) String message,
            @CosmosDBOutput(
                name = "databaseOutput",
                databaseName = "SymbolProcessor",
                collectionName = "ProcessedEvents",
                connectionStringSetting = "CosmosDBConnectionString"
                ) OutputBinding<String> document,
            final ExecutionContext context
    ) 
    {
        context.getLogger().info("Java Service Bus Queue trigger function executed.");
        context.getLogger().info("Message received: " + message);

        document.setValue(message);        
        context.getLogger().info("Document persisted to cosmos: " + message);
    }
}
