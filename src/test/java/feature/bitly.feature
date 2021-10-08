Feature: Test bitly APIs

Scenario Outline: Verify Shortening given link
Given User has shortenBitlink payload with "<longURL>" and "<domain>"
When User calls "ShortenLink" with "post" http request
Then Http response code should be 200
And "long_url" in response body is "<longURL>"
Given User has getBitLink payload
When User calls "GetLink" with "get" http request
Then Http response code should be 200
And "long_url" in response body is "<longURL>"


Examples:
|longURL                                          | domain |
|https://members.parliament.uk/member/2098/career | bit.ly |
|https://www.scotsman.com/news/politics/who-are-donors-behind-yes-and-no-campaigns-1531716| bit.ly |

Scenario Outline: Verify invalid domain
Given User has shortenBitlink payload with "<longURL>" and "<domain>"
When User calls "ShortenLink" with "post" http request
Then Http response code should be 400
And "message" in response body is "INVALID_ARG_DOMAIN"

Examples:
|longURL                                          | domain |
|https://members.parliament.uk/member/2098/career | bit.l |

Scenario Outline: Verify invalid long_url
Given User has shortenBitlink payload with "<longURL>" and "<domain>"
When User calls "ShortenLink" with "post" http request
Then Http response code should be 400
And "message" in response body is "INVALID_ARG_LONG_URL"

Examples:
|longURL                                          | domain |
|members.parliament.uk/member/2098/career | bit.ly |