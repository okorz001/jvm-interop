package interop.java.lib;

@FunctionalInterface
public interface JavaInterfaceWithMethod {
    String getLanguage();

    default void interfaceMethod() {
        System.out.println("Implemented Java interface with method in " + getLanguage());
    }
}
