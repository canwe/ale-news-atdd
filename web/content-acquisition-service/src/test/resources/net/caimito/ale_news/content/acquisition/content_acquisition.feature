Feature: Content Acquisition

  Scenario: Poll HTML source
    Given the HTML source "file://htmlsource"
    When a poll is triggered
    # And an excerpt for each item in the source is stored
    Then content analysis is triggered
