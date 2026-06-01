Feature: Login

  @e2e @sanity
  Scenario Outline: Valid user can log in
    Given the user is on the login page
    When the user logs in with email "<emailAddress>" and password "<password>"
    Then the account name should be "Ffaga gdgdg"

    Examples:
      | emailAddress          | password |
      | dummy6thmay@test.com  | 12345    |
      | dummy8thmay@test.com  | 12345    |

  @e2e @sanity
  Scenario Outline: Invalid user sees authentication error
    Given the user is on the login page
    When the user tries to log in with email "<emailAddress>" and password "<password>"
    Then the login error message should be "Authentication failed."

    Examples:
      | emailAddress           | password |
      | samp16thMay@test.com   | 12345    |
      | samp016thMay@test.com  | 12345    |
