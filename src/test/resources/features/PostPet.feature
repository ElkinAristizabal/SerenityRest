Feature: Post Pet

  Scenario Outline:
      Given user consumes the APIs endpoint
      When user use the path pet to send the pet information with each <test>
      Then user can validate the response service 200

      Examples:
        |test|
        |  1 |
        |  2 |
