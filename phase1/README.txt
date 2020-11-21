Phase1 | Group number: group_0112

How to begin the system:
You can start the system up by opening C




Run file ConferenceDemo.java
Every page/ would show lists of actions where the user would select by inputting the number of the choice in the terminal.
Login/Register menu is shown upon starting the program.
After login/ register, there would be a list of actions depending on the user type.
To end the current session select SHUTDOWN, to switch users select LOGOUT then login with its credentials.

usernames are limited to numbers and lowercase alphabet.



//Notes for specific classes
Conference System:
The ConferenceSystem class holds all of the Controllers and Use Cases,
Everytime the Conference system runs, it would import previous states of each Use Case
and initialize each Controller by passing in relevant use cases.
When the conference system shuts down, it would save the states of each use case.

Controllers
Attendee System
Event System
LoginAndRegistration System
Message System
Organizer System
Speaker System

Use Cases
AttendeeManager
ChatManager
EventManager
OrganizerManager
RoomManager
SpeakerManager

Entities
User(abstract)
Attendee extends User
Organizer extends User
Speaker extends User
Chat
Message
Event
Room

