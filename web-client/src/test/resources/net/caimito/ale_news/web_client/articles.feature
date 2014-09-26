Feature: Article metadata archive

  Scenario: Create new article metadata
    When I submit the following article metadata
      | Author         | Title                                            | location                                                                                                     |
      | Stephan Schwab | From competition it is a big leap to cooperation | http://www.stephan-schwab.com/china/culture/management/thoughts/2014/08/30/collaboration-or-cooperation.html |
    Then I see a list of articles

  Scenario: List articles
    When I open ALE News
    Then I see a list of articles
