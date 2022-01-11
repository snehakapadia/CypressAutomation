@tag
Feature: Testing a basic scenario in Flink
		
	Scenario: Addition and Movement of card Between Lists
		Given User navigates to "Trello" webpage
		When User clicks on "Log_in" button
    And User enters "Email" as "snehakapadia948@gmail.com"
    And User clicks on "Login_Altassian" button
    And User enters "Password" as "Testing@123"
    And User clicks on "Login_Button" button
    When User clicks on "Add_a_Card" in "To Do"
    And User enters data "Automation Task_TimeStamp" in "Add_Title" in "To Do"
    Then User verifies "Verify_Card" in "To Do"
    When User clicks on "Verify_Card" in "To Do"
    When User clicks on "Move" button
    And User selects "Doing" from "SelectList"
    And User clicks on "MoveList" button
		And User clicks on "CloseWindow" button
		Then User verifies "Verify_Card" in "Doing"
		When User clicks on "Verify_Card" in "Doing"
    When User clicks on "Move" button
    And User selects "Done" from "SelectList"
    And User clicks on "MoveList" button
		And User clicks on "CloseWindow" button
		Then User verifies "Verify_Card" in "Done"

	Scenario: Addition and Editing of card
		Given User navigates to "Trello" webpage
		When User clicks on "Log_in" button
    And User enters "Email" as "snehakapadia948@gmail.com"
    And User clicks on "Login_Altassian" button
    And User enters "Password" as "Testing@123"
    And User clicks on "Login_Button" button
    When User clicks on "Add_a_Card" in "To Do"
    And User enters data "Automation Task_TimeStamp" in "Add_Title" in "To Do"
    Then User verifies "Verify_Card" in "To Do"
    When User clicks on "Verify_Card" in "To Do"
    And User clicks on "Labels" button
    And User clicks on "color" as "yellow"
    And User clicks on "closePopup" button
    And User clicks on "CloseWindow" button
    Then User verifies "verifyLabel" has "yellow" color