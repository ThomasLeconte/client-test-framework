Feature: Testeuuh

  @BeforeInitDriver
  @AfterFermerFenetre
  Scenario: Naviguer entre plusieurs pages
    Given Je suis sur le site "https://computer-database.gatling.io/computers"
    And Je charge les references de la page "accueil"
    When Je clique sur le bouton de reference "addComputerBtn"
    Then Je suis sur la page "https://computer-database.gatling.io/computers/new"

  @BeforeInitDriver
  @AfterFermerFenetre
  Scenario: Créer un ordinateur
    Given Je suis sur le site "https://computer-database.gatling.io/computers/new"
    And Je charge les references de la page "ajouterOrdinateur"
    When Je rempli le champs de reference "champsNom" avec le nom "Mac M1"
    And Je rempli le champs de reference "champsDateSortie" avec le nom "2021-03-12"
    And Je rempli le champs de reference "champsDateFin" avec le nom "2022-03-08"
    And Je selectionne la valeur "Apple Inc." dans la liste déroulante de référence "listeCompagnies"
    And Je clique sur le bouton de reference "createBouton"
    And J'attends "1" seconde
    Then Je suis sur la page "https://computer-database.gatling.io/computers"
    And Je charge les references de la page "accueil"
    And L'element de reference "alerteSuccesCreation" est present sur la page
