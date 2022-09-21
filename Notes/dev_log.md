# Development Log

Wed Sep 7
- BUG: The map implicit intent sometimes didn't use the lat and lon passed from the ProfileFrag and used the default 0, 0 instead

Sun Sep 11
- UI: Changed the spinners for age, height, and weight to NumberPickers. This makes the programming side less complicated and provides a better UI interaction for choosing the values for these fields. The sex spinner was changed to two radio buttons for the same reasons. 

Mon Sep 12
- BUG: ProfileFrag is larger than the screen but could not scroll up and down
- FIX: Made ProfileFrag (and any other fragments) scrollable by wrapping the FragmentContainerView in MainActivity.xml in a ScrollView
- UI: Reorganized fragments into just two fragments (main and profile) since all the smaller fragments weren't actually being reused. Doing this also allowed us to reduce the number of activities to 1 which switches between the two fragments.

Tue Sep 13
- UI: Changed functionality of the "Weather" button. Instead of opening the browser with an implicit intent, it makes a TextView and Button visible. The TextView displays weather information from an API call to OpenWeatherMap. The "Details" button now opens the implicit intent that the "Weather" button used to.


Wed Sep 14
- BUG: NumberPicker values initialized the lowest number instead of the default values they should have had
- FIX: Changed how shared preferences stored NumberPicker values so they defaulted to the correct values
- UI: App defaults to ProfileFrag instead of MainFrag if a profile has not been created yet


Thu Sep 15
- BUG: Profile picture was not persistent if user switched repeated back for forth between MainFrag and ProfileFrag
- FIX: Stored path to profile pic as member variable and used by shared preferences in both fragments  


Fri Sep 16
- UI: Updated layout xml files to more closely match colours in wireframes. Until now functionality took precedence over colour palette.

Tue Sep 20
- UI: Changed font size of weather details and the text reads 'Loading...' during API calls

