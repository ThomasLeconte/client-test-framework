package exceptions;

public class ReferenceNotFoundException extends Exception {
    public ReferenceNotFoundException(String referenceName, String pageName) {
        super("La référence " + referenceName + " n'a pas été trouvée dans les références de la page " + pageName + ".");
    }
}
