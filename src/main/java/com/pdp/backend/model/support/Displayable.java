package com.pdp.backend.model.support;

/**
 * The Displayable interface represents an entity that can be displayed in the user interface.
 * It defines a method to retrieve the name of the entity.
 *
 * Classes that implement this interface provide a way to display their information in the user interface.
 * The getName() method should return the name of the entity as a String.
 *
 * This interface is typically implemented by model classes whose instances are displayed in the UI.
 *
 * @author Aliabbos Ashurov
 * @version 1.0
 * @since 16/April/2024
 */
public interface Displayable {
    /**
     * Retrieves the name of the entity.
     *
     * @return the name of the entity as a String
     */
    String getName();
}
