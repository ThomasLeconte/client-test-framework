Feature: Demo

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
    And J'attends 1 seconde
    Then Je suis sur la page "https://computer-database.gatling.io/computers"
    And Je charge les references de la page "accueil"
    And L'element de reference "alerteSuccesCreation" est present sur la page

  @BeforeInitDriver
  @AfterFermerFenetre
  Scenario: Naviguer entre plusieurs onglets
    Given Je suis sur le site "https://computer-database.gatling.io/computers/new"
    And J'ouvre un nouvel onglet avec l'url "https://www.youtube.com/"
    Then Je suis sur la page "https://www.youtube.com/"
    Then Je vais sur l'onglet numéro 1
    And Je suis sur la page "https://computer-database.gatling.io/computers/new"

  @BeforeInitDriver
  @AfterFermerFenetre
  Scenario: Supprimer les cookies
    Given Je suis sur le site "https://www.youtube.com/"
    Then J'attends 5 seconde
    And L'élément avec le xpath "//tp-yt-paper-button[@id='button']/yt-formatted-string[contains(., 'Tout accepter')]" existe
    And Je definis l'element "boutonAccepter" avec la référence "//tp-yt-paper-button[@id='button']/yt-formatted-string[contains(., 'Tout accepter')]"
    Then Je clique sur le bouton de reference "boutonAccepter"
    Then Je supprime les cookies du navigateur
    Then Je raffraichis la page
    Then J'attends 5 seconde
    Then L'élément avec le xpath "//tp-yt-paper-button[@id='button']/yt-formatted-string[contains(., 'Tout accepter')]" existe
    
