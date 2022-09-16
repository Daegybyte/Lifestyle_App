# Development Log

- Moved views into various fragments for reusability.
- Changed the spinners for age, height, and weight to numberpickers. This makes the programming side less complicated and provides a better UI interaction for choosing the values for these fields. The sex spinner was changed to two radio buttons for the same reasons.
- Reorganized fragments into just two fragments (main and profile) since all the smaller fragments weren't actually being reused. Doing this also allowed us to reduce the number of activities to 1 which switches between the two fragments.
