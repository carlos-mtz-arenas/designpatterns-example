package mx.javasample.designpatterns.core.mappers;


public interface Mapper<SOURCE, TARGET> {

    /**
     * Maps one instance into another.
     *
     * @param source The initial object that contains all the initial information.
     * @return Another object from a different class with its properties based on the {@link SOURCE} element.
     */
    TARGET map(SOURCE source);
}
