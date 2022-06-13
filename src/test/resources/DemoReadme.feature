Feature: DemoReadme


  @BeforeInitDriver
  @AfterFermerFenetre
  Scenario: Ajouter un iphone 13 pro au panier
    Given Je suis sur le site "https://www.actimag.biz/"
    Then J'attends 2 seconde
    And Je definis l'element "menuIphone" avec la référence "/html/body/main/header/div[3]/div/div/ul/li[5]"
    Then Je clique sur le bouton de reference "menuIphone"
    Then J'attends 2 seconde
    And  Je definis l'element "Iphone13" avec la référence "/html/body/main/header/div[3]/div/div/ul/li[5]/div/table/tbody/tr/td[2]/div/div[2]/span/a"
    Then J'attends 2 seconde
    Then Je clique sur le bouton de reference "Iphone13"
    And Je definis l'element "boutonAcheterIphone13Pro" avec la référence "/html/body/main/section/div/div[2]/div/div[2]/div/div[8]/div/div[2]/div/div/div/div/div/div[4]/div/div[4]/div/a"
    Then J'attends 2 seconde
    Then Je clique sur le bouton de reference "boutonAcheterIphone13Pro"
    And Je definis l'element "couleurOr" avec la référence "/html/body/main/section/div/div[2]/section/div[1]/div/div[2]/div[3]/div[1]/form/div[1]/div[1]/ul/li[2]/label/input"
    Then J'attends 2 seconde
    Then Je clique sur le bouton de reference "couleurOr"
    And Je definis l'element "boutonAjouterPanier" avec la référence "/html/body/main/section/div/div[2]/section/div[1]/div/div[2]/div[3]/div[1]/form/div[2]/div/div/button"
    Then Je clique sur le bouton de reference "boutonAjouterPanier"
    Then J'attends 2 seconde
    And L'élément avec le xpath "/html/body/div[2]/div/div/div[1]/h4/text()" existe