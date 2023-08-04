package org.spysat.simplehammers.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;

/**
 * The ToolRadiusCallback interface defines a mechanism for modifying the radius of a tool's Area of Effect (AOE) mining behavior dynamically through registered callbacks.
 * This interface is part of an event-driven system that allows external code, such as mods, to influence the tool's radius without direct code modifications.
 */
public interface ToolRadiusCallback {

    /**
     * The EVENT constant represents the event itself and facilitates the callback mechanism for modifying tool radius.
     * It is created using the EventFactory.createArrayBacked method to set up an event with an array-backed callback mechanism.
     * Registered listeners (callbacks) implementing this interface can modify the radius of a tool's AOE mining behavior when this event is triggered.
     */
    Event<ToolRadiusCallback> EVENT = EventFactory.createArrayBacked(ToolRadiusCallback.class,
            listeners -> (tool, currentRadius) -> {
                // Iterate through all registered listeners to allow each callback to potentially modify the currentRadius.
                for (ToolRadiusCallback callback : listeners) {
                    currentRadius = callback.getRadius(tool, currentRadius);
                }

                // Return the potentially modified currentRadius as the final result of the event.
                return currentRadius;
            });

    /**
     * The getRadius method is implemented by listeners (callbacks) to provide the ability to modify the radius of a tool's AOE mining behavior.
     * When the EVENT is triggered, each registered listener's getRadius method is invoked, allowing them to adjust the currentRadius based on their own logic.
     * The final modified currentRadius value is returned as the result of the event.
     *
     * @param tool The tool ItemStack for which the radius is being modified.
     * @param currentRadius The current radius of the tool's AOE mining behavior.
     * @return The potentially modified radius of the tool's AOE mining behavior.
     */
    int getRadius(ItemStack tool, int currentRadius);
}

