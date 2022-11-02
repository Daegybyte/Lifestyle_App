# 6018 MSD project
## Created By: Diego, Jon, Kelan 

### Phase 3 roles:
* Jon - Test lead
* Diego - Team Lead
* Kelan - Design Lead

### Phase 3 meeting notes:

Set up cognito login at the start to allow user chance to not use app if they don't want an app where they need to sign up for a service.

update the database onPause()

No major changes to UI

# Design:

## Class Diagram
### Class diagram overview
<img src="./designFiles/class_diagrams/p3_diagram_slide_TESTS.png" width="75%" height="75%">

<img src="./designFiles/class_diagrams/full_class_diagram.png" width="100%" height="75%">

## Register
<img src="./screenshots/cognito_signup.png" width="25%" height="25%">
<img src="./screenshots/profile_creation.png" width="25%" height="25%">

<img src="./screenshots/profile_creation_tablet.png" width="50%" height="25%">


## Profile

<img src="./screenshots/step_on.png" width="25%" height="25%">
<img src="./screenshots/step_off.png" width="25%" height="25%">


<img src="./screenshots/profile_page_tablet.png" width="50%" height="50%">


## Update Activity

<img src="./screenshots/profile_update_activity_level.png" width="25%" height="25%">

<img src="./screenshots/update_activity_level_tablet.png" width="50%" height="50%">



## Weather Details

<img src="./screenshots/profile_page_weather.png" width="25%" height="25%">

<img src="./screenshots/profile_page_weather_tablet.png" width="50%" height="50%">



## Weather

<img src="./screenshots/weather.png" width="25%" height="25%">


## Hikes

<img src="./screenshots/hikes.png" width="25%" height="25%">


# Testing

We used five locations in four different quadrants of the planet to test our location detection. MEB and NYC were in the same quadrant. MEB was a good test location to veryify with because of our familiarity with the area, and NYC was chosen because we would know we weren't getting faulty location data from the locality of our machines.

<img src="./screenshots/testingLocation.png" width="50%" height="50%">

### UI Automator

UI automator was used to run tests on the UI, testing that values exists where expected.

### Step testing

Due to limitations in the emulator, we had to use a physical android device to test the step counter.

