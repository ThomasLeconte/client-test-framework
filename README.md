# Client Test Framework
Ce framework est basé sur la technologie WebDriver, qui permet de contrôler automatiquement un navigateur internet grâce à une API.
Ici, la bibliothèque Selenium WebDriver est utilisée pour piloter notre navigateur.

## Fonctionnalités principales

### Méthodes utiles
**<...> = arguments obligatoires**  
**[...] = arguments optionnels**

> nouvelOnglet([String url]) - Ouvre un nouvel onglet sur la même fenêtre du navigateur actuel.

> allerSurOnglet(int indexOnglet) - Sélectionne un onglet parmis ceux qui sont disponibles grâce à son index dans la liste des onglets.

> supprimerCookies() - Supprimme l'ensemble des cookies du navigateur.

> modifierTailleEcran(int largeur, int longueur) - Redimensionne la fenêtre actuelle avec des dimensions personnalisées.

> passerEnModeMobile() - Redmimensionne la fenêtre pour lui donner l'apparence d'un écran de smartphone.

> viderChamps(String referenceChamps) - vide le champs d'un formulaire.

> remplirChamps(String referenceChamps, String valeur) - Rempli un champs avec la valeur spécifiée en le ciblant grâce à sa référence.

> attendre(int nbSecondes) - Permet d'attendre pendant X secondes durant l'exécution.

> selectionnerValeurDansListe(String referenceListe, String valeur) - Sélectionne une valeur parmis celles disponibles dans une liste déroulante.

> clicSurBouton(referenceBouton) - Clique sur un bouton grâce à sa référence.

> verifierPresenceValeurDansTableau(String valeur) - Renvoie un booléen confirmant la présence d'une valeur parmis les tableaux disponibles sur la page.

> verifierPresenceElement(String referenceElement) - Contrôle la présence d'un élément sur une page grâce à sa référence.

> getDonnee(String cheminJSON) - Récupère la valeur d'une donnée JSON grâce à son chemin hiérarchique dans le fichier de données.
### Référencement d'élément
Cette fonctionnalité permet de mettre un nom à des éléments d'une application WEB grâce à une jonction entre le nom et le lien XPath de l'élément.
Cette jonction permettra de récupérer l'élément en question pour pouvoir le manipuler ou contrôler ses attributs.

**Implémentation :**

- Créer un fichier JSON permettant de référencer vos éléments à l'endroit que vous voulez. Ensuite, vous devrez respecter une structure précise :
```json
{
  "pages": [
    {
      "nom": "nomDeVotrePage",
      "references": {
        "nomDeVotreReference": "CheminXPathVersVotreElement",
        "addComputerBtn": "//*[@id=\"add\"]",
        "rechercheOrdinateur": "//form/input[@id='searchbox']",
        "rechercheOrdinateurBtn": "//form/input[@id='searchsubmit']",
        "alerteSuccesCreation": "//section//div[@class='alert-message warning' and contains(., 'has been created')]"
      }
    }
    ...
}
```

## Pour le développeur
## Pour le testeur