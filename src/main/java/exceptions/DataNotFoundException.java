package exceptions;

public class DataNotFoundException extends Exception {
    public DataNotFoundException(String chemin, String cause) {
        super("La lecture du chemin " + chemin + " a renvoyé une erreur, veuillez vérifiez le chemin spécifié. Cause : " + cause);
    }
}
