Feature: CRUD and list scooped articles

Scenario: Create new article metadata
	Given I have scooped an article on the Internet with the following metadata:
		| Author         | Title                                            | location                                                                                                     |
		| Stephan Schwab | From competition it is a big leap to cooperation | http://www.stephan-schwab.com/china/culture/management/thoughts/2014/08/30/collaboration-or-cooperation.html |
	When I call the article service with URI "/article" and verb "POST"
	Then the article metadata has been stored in the archive

Scenario: Read article metadata
	Given some article metadata with key "550e8400-e29b-11d4-a716-446655440000" exists in the archive
	When I call the article service with URI "/article/550e8400-e29b-11d4-a716-446655440000" and verb "GET"
	Then I receive the article metadata

Scenario: Update article metadata

Scenario: Delete article metadata

Scenario: List article metadata
