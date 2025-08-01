package com.aey.mox.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.aey.mox.listeners.EventListener;
import com.aey.mox.publisher.EventManager;

/**
 * {@code Context<T, E>} is a mutable, stateless data manager designed to act as a single
 * source of information. It functions as a central container that transports data,
 * states, and errors. Its goal is to facilitate communication between different
 * layers or components. It implements the observer pattern to notify all its
 * subscribers when data changes.
 * 
 * <p>
 * 
 * You can subscribe multiple {@link EventListener} to the same {@code Context} with
 * different actions, and you can also subscribe multiple {@link EventListener} to
 * the same {@code Context} with the same action. This means that
 * {@link EventListener} can be grouped by the same action, but listen to different
 * events and respond to different events.
 * 
 * <p>
 * 
 * This happens because the {@code Context} has an internal {@code HashMap} that is
 * responsible for grouping lists of {@link EventListener} by action. And each list
 * can contain different {@link EventListener}. Therefore, if your
 * {@link EventListener} are registered in a different list with a different action,
 * they will not be automatically updated every time you call the {@code emit()}
 * function. But you can update all the {@link EventListener} in a list by simply
 * calling the {@code emit()} function. If you want to update a specific event
 * {@link EventListener}, you will have to call the {@code notify()} function, passing
 * the {@code eventType} and the {@link EventListener} as parameters (this way, the
 * {@code Context} will know that you only want to update a specific
 * {@link EventListener}).
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * Context<User, Exception> context = new Context<>(Actions.PUBLISH.getAction());
 * UserContext userContext = new UserContext();
 * context.subscribe(Actions.PUBLISH.getAction(), userContext);
 * context.set(Prop.bind("userId", id));
 * context.<Integer>get("userId").ifPresent(uid -> userOne.setUserId(uid));
 * context.notify(Actions.PUBLISH.getAction(), userContext, userOne);
 * }</pre>
 * 
 * @param <T> The type of value stored in the context
 * @param <E> The type of error handled by the context
 * 
 * @author ymoyamac
 * @since 1.0
 */
public class Context<T, E> extends EventManager {

    private final Map<String, Prop<?>> props = new HashMap<>();
    private E err;

    /**
     * Default empty constructor
     * 
     * @since 1.0
     */
    public Context() {}

    /**
     * Constructs a context with operations registered in the underlying event manager.
     *
     * @param operations The names of operations to register
     * 
     * @since 1.0
     */
    public Context(String ...operations) {
        super(operations);
    }

    /**
     * Returns the complete {@code Map} of all props stored in the context. This
     * data structure contains all the properties registered by all listeners.There are
     * no access restrictions and they are visible to everyone.
     * 
     * @return {@code Map<String, Prop<?>>} The properties of the context
     * 
     * @since 1.0
     */
    public final Map<String, Prop<?>> getProps() {
        return this.props;
    }

    /**
     * /**
     * Stores a typed value in the context under the given key. This method internally
     * creates a {@link Prop} instance with the {@code bind()} static method.
     *
     * @param   key     The key under which the value is stored 
     * @param   value   The value to store
     * 
     * @since 1.0
     */
    public final void set(String key, T value) {
        Prop<T> prop = Prop.bind(key, value);
        this.props.put(key, prop);
    }

    /**
     * Stores one or more {@link Prop} instances in the context. This method uses the
     * static {@code bind()} method of the {@link Prop} class to create a new instance.
     *
     * @param props  One or more properties to store
     * 
     * @since 1.0
     */
    public final void set(Prop<?> ...props) {
        for (Prop<?> prop : props) {
            this.props.put(prop.getId(), prop);
        }
    }

    /**
     * Retrieves a property value by its key, cast to the expected type.
     * Throws {@link ClassCastException} if the actual type of the stored value
     * does not match the type stored in the corresponding {@link Prop}.
     *
     * @param   name    The key of the property to retrieve 
     * @param   <U>     The expected type of the value 
     * @return  {@link Optional} 
     *          Containing the value if present and type matches, or empty otherwise
     * 
     * @throws  ClassCastException
     *          If the type of the stored value does not match
     * 
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public final <U> Optional<U> get(String name) {
        Prop<?> prop = props.get(name);

        if (prop == null) {
            return Optional.empty();
        }

        if (!prop.getClazz().isInstance(prop.getValue())) {
            throw new ClassCastException("Expected type " + prop.getClazz() + ", but got " + prop.getValue().getClass());
        }
        return Optional.ofNullable((U) prop.getValue());
    }

    public final E err(E err) {
        this.err = err;
        return err;
    }

    @Override
    public String toString() {
        return "Context {props=" + props + ", tableListeners=" + super.tableListeners + ", err=" + err + "}";
    };

}
