# You need to implement the two scenarios in KiSSFLOW.
#
# You are free to choose between Python, JavaScript and Java to automate these Scenarios in Selenium using Cucumber. 
#
# IF working with Java Ensure you give a complete  JAR file with all the dependencies used in order to run this test case.
#
# If using python or JavaScript provide appropriate dependency files 
#
# You will have 7 working days from the day the script was mailed to work on the same.
#
# Kindly reach out to us for any further clarifications.
#
#
Feature: Test If Formula Firing When a particular criteria is met then the value on another field is set or unset accordingly.
  	
  Background:
    Given I login to KiSSFLow with the username "samosa@basy.cf" and password "Kissflow123"
	And I create an App "TestApp" 
	#you are allowed to change the name of the App alone if you found that an App Alrady exists with that name
    And I create a Field of type "Text" with Name "State" on "TestApp"
    And I create a Field of type "Text" with Name "Needs Verification" on "TestApp"
    And I add a Formula 'IF(State="TamilNadu","Yes","No")' to "Needs Verification" on "TestApp"
    And I published "TestApp"
  @tamilnadu
  Scenario: If should fire when I enter TamilNadu
    When I Initiate "TestApp" as item1
    And I enter "TamilNadu" in "State" for item1
    Then field "Needs Verification" for item1 should equal "Yes"

  @kerala
  Scenario: Else should fire when I enter Kerala
    When I Initiate "TestApp" as item2
    And I enter "Kerala" in "State" for item2
    Then field "Needs Verification" for item2 should equal "No"

