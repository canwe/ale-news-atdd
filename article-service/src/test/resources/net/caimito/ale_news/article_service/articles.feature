Feature: CRUD and list scooped articles

Scenario: Create new article metadata
	When I post the following article metadata to the article service with URI "/article"
		| Author         | Title                                            | location                                                                                                     |
		| Stephan Schwab | From competition it is a big leap to cooperation | http://www.stephan-schwab.com/china/culture/management/thoughts/2014/08/30/collaboration-or-cooperation.html |
	Then an article ID is returned
	And the article metadata has been stored in the archive

Scenario: Read article metadata
	Given some article metadata with key "550e8400-e29b-11d4-a716-446655440000" exists in the archive
	When I ask the article service with URI "/article/550e8400-e29b-11d4-a716-446655440000"
	Then I receive the article metadata

Scenario: Update article metadata
	Given the article with key "550e8400-e29b-11d4-a716-446655440000" exists in the archive
		| Author         | Title                                            | location                                                                                                     |
		| Stephan Schwab | From competition it is a big leap to cooperation | http://www.stephan-schwab.com/china/culture/management/thoughts/2014/08/30/collaboration-or-cooperation.html |
	When I update details of the article with URI "/article/550e8400-e29b-11d4-a716-446655440000"
		| Author     |
		| Hans Moser |	
	Then the metadata for article "550e8400-e29b-11d4-a716-446655440000" in the archive is
		| Author     | Title                                            | location                                                                                                     |
		| Hans Moser | From competition it is a big leap to cooperation | http://www.stephan-schwab.com/china/culture/management/thoughts/2014/08/30/collaboration-or-cooperation.html |

Scenario: Delete article metadata
	Given the article with key "550e8400-e29b-11d4-a716-446655440000" exists in the archive
		| Author         | Title                                            | location                                                                                                     |
		| Stephan Schwab | From competition it is a big leap to cooperation | http://www.stephan-schwab.com/china/culture/management/thoughts/2014/08/30/collaboration-or-cooperation.html |
	When I delete the article with key "550e8400-e29b-11d4-a716-446655440000"
	Then the article with key "550e8400-e29b-11d4-a716-446655440000" does not exist

Scenario: List article metadata
