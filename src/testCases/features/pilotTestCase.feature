Feature: Test pilot will mold the appropriate abstracted class for futher features

  Scenario: Get to Multichannel Video Programming Distributor List
    Given I have launched the app
    When I click the settings icon
    Then I click on the sign in option
    And I select more providers
    And I click on a tv provider
    And Enter Username and Password
    And I Login
    And I check to see how long it took to sign in 
    And I play a video 
    And I take a screen shot of the Pre Role (ads)
    And I pause the video
    And I sign out
    Then close driver 
    
