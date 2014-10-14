Feature: Interact with article metadata archive

  Background:
    Given there are no articles in the archive

  Scenario: Create new article metadata
    When I submit the following article metadata
      | Author         | Title                                            | location                                                                                                     |
      | Stephan Schwab | From competition it is a big leap to cooperation | http://www.stephan-schwab.com/china/culture/management/thoughts/2014/08/30/collaboration-or-cooperation.html |
    Then the article metadata has been stored in the archive

  Scenario: List articles
    When I submit the following article metadata
      | Author         | Title                                            | location                                                                                                     |
      | Stephan Schwab | From competition it is a big leap to cooperation | http://www.stephan-schwab.com/china/culture/management/thoughts/2014/08/30/collaboration-or-cooperation.html |
    And I open ALE News
    Then I see a list of articles
