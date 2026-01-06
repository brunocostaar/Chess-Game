package gui;

/**
 * GuiLauncher.java
 * ----------------
 * This class serves as the entry point for running the JavaFX application from within an IDE
 * (like IntelliJ IDEA or Android Studio) without needing complex VM arguments.
 *
 * Why is this needed?
 * Modern JavaFX applications (Java 11+) often fail when the main class extends `Application`
 * directly and is run from the classpath, throwing errors about missing JavaFX runtime components.
 *
 * By having a separate main class that does NOT extend `Application` but simply calls
 * `ChessGUI.main(args)`, we trick the JVM into loading the JavaFX classes correctly on the classpath.
 */
public class GuiLauncher {
    public static void main(String[] args) {
        // Delegates execution to the actual JavaFX Application class.
        ChessGUI.main(args);
    }
}
