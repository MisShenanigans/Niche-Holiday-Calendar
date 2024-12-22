# Niche Holiday Calender

## This is a student project for a undergraduate programming class

This is a student project for course CPSC210. It primarily 
does three things.

- Provides an overview of all days in a month and put them into
a calendar view.

- Inform the user a niche holiday is happening when it is happening. and
also provides some basic information about that niche holiday.

- Provide information of Niche Holiday about any date the user
is curious of.

This program is designed for 
- Anyone looking for some extra nicheness in their life.
- Anyone wishing to be able to provide fun facts in a party or gathering
- Anyone dedicating to celebrating a certain niche holiday but have
memory so terrible so they can't memorize those themselves.

I decide to take on this project because it is something I will need
in my life and I think it will do other people some help.

## As a user, I would like to check if today is a niche holiday.
## As a user, I would like to go to a date and see it's month.
## As a user, I would like to go to check if a date I typed in is a niche holiday.
## As a user, I would like to move to tomorrow and yesterday to see if they are niche.
## As a user, I want to be able save my NicheGlossary to file, (if so I choose)
## As a user, I want to be able to load my NicheGlossary from file (if I so choose)
## As a user, I want to be able to remove a Niche Holiday from the Glossary




## Citation:
Used JSON libraries from: https://github.com/stleary/JSON-java

Persistence package and its corresponding tests are largely taken from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
due to similarities in data types. 


## Phase 4: Task 2
Here is an instance of P2T2 opening the program(with pre-loaded glossary loading),
adding and removing the 3-14 Pie Day:

"
Wed Apr 12 18:36:44 PDT 2023
Loaded the default Glossary

Wed Apr 12 18:37:28 PDT 2023
Added: PIE Day 3-14

Wed Apr 12 18:37:37 PDT 2023
Removed: PIE Day 3-14
"

## Phase 4: Task 3
Given more time, I will refactor the method printCalendar() in class NicheCalendarGUI into
the class NicheDate because in GUI all it does is to produce a string and that should be 
a task for model instead of UI. I will likely also make a few more exceptions regarding to
repeated attempts of adding NicheHoliday onto the same day in a year.

Also I will likely create a selection box in UI so the user will be able to pick the save file
they want to load and save their holiday glossary and I can rewrite the default glossary as
a json file instead of a NicheHoliday array that looks weird.








