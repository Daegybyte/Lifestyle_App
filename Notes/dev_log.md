# Development Log

- Moved views into various fragments for reusability.
- Changed the spinners for age, height, and weight to numberpickers. This makes the programming side less complicated and provides a better UI interaction for choosing the values for these fields. The sex spinner was changed to two radio buttons for the same reasons.
- Reorganized fragments into just two fragments (main and profile) since all the smaller fragments weren't actually being reused. Doing this also allowed us to reduce the number of activities to 1 which switches between the two fragments.


commit 3f4897c3d6eb7a770a09ccac28b25bfdc08fef93
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Mon Aug 29 10:54:56 2022 -0600

    first commit

commit 11ead8849cd275fa6d283585a6b4c9976c2f42c4
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Mon Aug 29 10:56:25 2022 -0600

    Hiiiii

commit 5e714abb4a01626bb757af6ee03272474d7b091d
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Mon Aug 29 13:07:26 2022 -0600

    added plain view

commit fb2fa42e05a83bdedf96abef521e6a153dead61b
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Mon Aug 29 13:22:57 2022 -0600

    nuked it

commit dd3f784136067045d73e3b98f2bab2c83153e4ab
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Mon Aug 29 13:24:19 2022 -0600

    removed ds

commit 1ce2d944021f527f748ab9b0a28582d17b3b908f
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Mon Aug 29 13:25:59 2022 -0600

    add a gitignore

commit 4aebf8a1419d1c162048db483d9716b5c897e89a
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Mon Aug 29 13:45:02 2022 -0600

    added an activity for the profile input/editting

commit ad6f643005647e7ebb884c42bcef0e19cb2b9ed1
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Mon Aug 29 14:10:53 2022 -0600

    updated phase 1 notes

commit 86a7269e8659a09f7eb5007d5a88da409432ed7d
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Mon Aug 29 15:06:51 2022 -0600

    added figma file and exported pngs of activities

commit 353c217f1b5ff289103d60f704be46dcfbfa2de9
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Mon Aug 29 15:27:18 2022 -0600

    roughed in the Views of the ProfileActivity

commit 81657caa1d3516349eab857eaa9e8dd7498ebc6e
Merge: 353c217 86a7269
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Mon Aug 29 15:27:30 2022 -0600

    Merge branch 'main' of https://github.com/Daegybyte/6018_project

commit aa66ee3aef97d8b029eddec0a7b2e88726becb86
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Wed Aug 31 12:13:12 2022 -0600

    added activity design images

commit 611f6ce3222a1366310390eaa651c539bb6a507c
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Wed Aug 31 12:17:47 2022 -0600

    renamed README for github readability purposes

commit bf85cdb4eb38ca17906ce60999cd518f0d61b26c
Author: Daegybyte <55812910+Daegybyte@users.noreply.github.com>
Date:   Wed Aug 31 12:19:02 2022 -0600

    Delete designNotes.md
    
    no longer a file

commit 2a03d1dc8f4ad5410e337dd306f79c40a9a9dcdf
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Wed Aug 31 12:40:22 2022 -0600

    attempting to populate spinners

commit ab8d24f486bed022665cd364002774d7136afff6
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Thu Sep 1 15:49:35 2022 -0600

    main activity layout including a spinner

commit 0738221fd53a62f4eadbfe27f2f67183fd897738
Author: Diego <lucy@lucy.local>
Date:   Thu Sep 1 16:13:29 2022 -0600

    merged

commit 0093f1444056c0d97dd79a410963f9e10c25d314
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Wed Sep 7 10:28:29 2022 -0600

    merging the jon branch to the main branch...fingers crossed

commit c77ccb04b5020f23e8d508b110704865d88f4a31
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Wed Sep 7 12:24:39 2022 -0600

    permissions and button WIP

commit 1d30b7bf2c7d47d378c825a22b277746ae379c41
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Wed Sep 7 12:29:36 2022 -0600

    implicit intent is working, but still might defatult to lat 0 long 0

commit 28882e11db105df5d57e1c5b1b4714d7e8018170
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Wed Sep 7 12:59:42 2022 -0600

    logged

commit df9d9c0afec8adb2878ad9d33d42cef49f6f329e
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Wed Sep 7 13:27:19 2022 -0600

    Hikes and Weather buttons work -- take a long time to get geolocation data -- solution...loading screen?

commit 2ad0d06a398ca89277997db89d28f9ad2849228a
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Wed Sep 7 14:18:54 2022 -0600

    did some arrow handling, IDK

commit 495a3b4e0276f2238a60c01d5940c06c9c6fbd09
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Wed Sep 7 14:39:30 2022 -0600

    added onClick to Edit Profile Button to go from Main to Profile

commit 1b8f8519aa5de92faee1aa38fe1ef0bba5f9d4aa
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Thu Sep 8 15:59:55 2022 -0600

    created a BMR class and started making JUnit tests in the BMRTest file located in com.example.project(test)

commit 0861fe4e30ad77fe9764cbb6851799652760f01b
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Thu Sep 8 16:14:41 2022 -0600

    refactoring in BMR class

commit 0a6ad6a174c783fab307ca35316333a938a86581
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Thu Sep 8 16:29:01 2022 -0600

    more unit tests

commit 17dd0d49d8ebd6abae11bfe236d6166fec6f40c9
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Thu Sep 8 16:48:09 2022 -0600

    unit test refactoring

commit 7c00264308483798733b006fb9e3f5688f2b1532
Author: Diego <lucy@lucy.local>
Date:   Thu Sep 8 18:43:55 2022 -0600

    unit test refactoring, fixed calls to heavy and extreme

commit d81643aef911bce2c22abc420b8b2cbcfa1eb5d0
Author: Diego <lucy@lucy.local>
Date:   Fri Sep 9 08:45:18 2022 -0600

    added junit testing to gradle app

commit a2f8b79f7cb23ba415e375a47a74aaf45c758bc3
Author: Diego <lucy@lucy.local>
Date:   Fri Sep 9 09:01:52 2022 -0600

    junit test refactoring

commit 6e91c43e8d432f88caf6e84c3eff66789b470e0f
Author: Diego <lucy@lucy.local>
Date:   Fri Sep 9 09:44:49 2022 -0600

    junit test refactoring

commit 633330339240d7b732b3182e0e34c9f7ce109ad8
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Fri Sep 9 11:48:53 2022 -0600

    merging misc.xml....not sure what this file does

commit 577f499c38a9f49d4396872e587962b81c10ae8d
Merge: 6333303 6e91c43
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Fri Sep 9 11:50:45 2022 -0600

    Merge branch 'main' of https://github.com/Daegybyte/6018_project

commit de84b77528817d11b40e569b808f94bc4132c0de
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Fri Sep 9 11:52:02 2022 -0600

    added old profile.xml as backup

commit 2820f03dc7009d620257b33eda8984387e33bb76
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Fri Sep 9 12:17:24 2022 -0600

    updated BMR to use 1990 revision to equations

commit 0a1ac3fcb5a0818807965d4a618c138797887993
Author: Diego <lucy@lucy.local>
Date:   Fri Sep 9 13:20:34 2022 -0600

    working for presentation

commit d1efa7d280e0cb97347f05024053491b82e020fa
Author: Diego <lucy@lucy.local>
Date:   Fri Sep 9 14:55:31 2022 -0600

    updated the text boxes to contain hints instead of strings

commit 19fcf23b62b169f60ff3741070090f87d3da2cdf
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Sun Sep 11 23:49:35 2022 -0600

    updated profile activity to use fragments ... switched a lot of the spinners to number pickers

commit 9bbeb26f95a8fa26784f3894b15300acdc8f975c
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Mon Sep 12 11:01:38 2022 -0600

    Profile is now scrollable ... setting things up to move main activity and profile activity into two big fragments

commit 9c951afa82124a9558070a92e12d3c2a6d54a2b8
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Mon Sep 12 12:20:50 2022 -0600

    changed main activity so it is in its own fragment

commit 449aa51ec802f0dc6d0a5fdd6218195a95a5fa87
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Mon Sep 12 12:49:30 2022 -0600

    moved Kotlin implementation from the previously seperate Fragment files into MainFrag

commit cf4c1bb2a6f0a6f85c259c710db31a312a5922ec
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Mon Sep 12 12:52:21 2022 -0600

    added espresso and UI automator

commit 3755f4878b595e0a8432cc2a8d12a117e177ff66
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Mon Sep 12 13:26:24 2022 -0600

    changed profile activity so it is all in its own fragment

commit bcd293febcd7bb85922c55df0f0b8510d9ef6168
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Mon Sep 12 13:33:25 2022 -0600

    implemented edit profile button

commit 05ba0deb9449d5a2e31b49b2b4d6cd578686ad62
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Mon Sep 12 14:01:20 2022 -0600

    file cleanup -- deleted extraneous files

commit 639384992f2bd12a11879be9523fa8219d387b38
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Mon Sep 12 14:05:26 2022 -0600

    small fix of numberpickers in profile.kt

commit e8b51b26da80ea3614fcddc045c9fa4ec5402d08
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Mon Sep 12 14:10:19 2022 -0600

    added espresso and UI automator

commit bbe9717a158543c4323c7582b459c5e06f941a62
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Mon Sep 12 14:44:08 2022 -0600

    began implementing SharedPreferences: currently stores and loads user's name only

commit f9bca41eb014fc25bfae79b35dfc27936077aff9
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Mon Sep 12 15:40:36 2022 -0600

    SharedPreferences stores and loads name, age, height, weight, activity level, and sex

commit 32526fcc245cb71e8931fb96a04b167ea5b7c604
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Mon Sep 12 15:58:34 2022 -0600

    SharedPreferences loads the activity level on the homepage now

commit b9a9875b4ff597e98c166f05ad099218f8a8f807
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Tue Sep 13 17:44:23 2022 -0600

    Added a temporary splash-loading fragment -- app still opens main fragment

commit 4848e52e05a2f2fc18a6ea71de99ce5851402eec
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Tue Sep 13 18:55:44 2022 -0600

    changing activity level from dropdown in main fragment updates the appropriate textviews

commit ae436615931c6495534653792a40eb2cfc14da5b
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Tue Sep 13 19:21:09 2022 -0600

    when weather button is pressed, it makes a hidden part of the layout visible

commit 0230a73429d6e9586593fd25775a3f15f99b9a0d
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Wed Sep 14 12:41:54 2022 -0600

    made some parameters nullable in the onItemSelected() signature

commit db36328720f0380fc8d1611bba097f8ea9de5185
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Wed Sep 14 13:16:28 2022 -0600

    fixed default values showing on number pickers

commit 4202c43e2d937f8d6319a19e6a957ce50e8a2920
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Wed Sep 14 13:38:25 2022 -0600

    added immediate redirect to profile fragment if profile has not yet been created

commit 9a5da8c1104599c2f00e4510500c7224d18acba2
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Wed Sep 14 15:14:48 2022 -0600

    uiAutomator class is working

commit 20c99f5fd2e568e6e7f084ed9d04a3a67103b9df
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Wed Sep 14 15:43:15 2022 -0600

    added profile pic functionality

commit 85c80df7ae895db3870cbd2887db39128b08f4f9
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Wed Sep 14 17:01:51 2022 -0600

    Spinner now displays updated values for various activity levels

commit a46e131a8f12e0e935b52f462d756a4b7ae52d6b
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Thu Sep 15 12:56:57 2022 -0600

    fixed tests

commit f718196b042aff2dbc127a7a25187135782984f2
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Thu Sep 15 12:59:45 2022 -0600

    profile pic is now persistent back and forth between main and profile fragments

commit 61d272ae614c39dd0be8c310d071609fcaf8e3b4
Author: Kelan Albertson <kelan.albertson@utah.edu>
Date:   Thu Sep 15 13:29:25 2022 -0600

    change activity level spinner in main frag will now update activity level in profile frag

commit 2fa36d958768564b7f39cadbdf3d0d3bde5d0edc
Author: Diego <lucy@lucy.uconnect.utah.edu>
Date:   Thu Sep 15 16:23:34 2022 -0600

    tests

commit 59558d77204222c9a8d716127365c59e6dcf10a4
Author: Jon <jon.hughes.msd@gmail.com>
Date:   Thu Sep 15 20:12:08 2022 -0600

    successful weather api call
