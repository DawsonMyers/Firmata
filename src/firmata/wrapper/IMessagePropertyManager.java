package firmata.wrapper;

/**
 * Property for the message
 */
public interface IMessagePropertyManager<ConcretePropertyClass> {
    ConcretePropertyClass get(MessageWithProperties data);
    void set(MessageWithProperties data);
}
