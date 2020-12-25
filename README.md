# conference-system
This a school project made in the Software Design course (CSC207) at the University of Toronto

The goal of this project was to put our clean architecture skills at work! We implemented a design of this program that follows clean architecture and SOLID principles as well as some design patterns.

There are 2 phases to this project. Phase 2 is an extension to Phase 1. 
To find out how our system works, first check out phase 1 README, and then phase 2 README.

Read the README of phase 1 to find out info on the project, and read the README of phase 2 to see the new features and extensions we added.

Basic overview of what our program does:
1. There are 4 types of accounts: Admin, Attendee, Organizer and Speaker.
2. They can message each other. Attendees and Organizers can message anyone. Organizers can send an automated message to everyone in the system. Speakers can only send messages to attendees attending their talk. Admins cant message anyone.
3. Attendees attend events. Speakers talk at the events. Organizers create ("host") events and can also attend otehr events. Admin monitor messages sent and can delete messages. Admins can't attend events. 
4. to message a user, you first have to add the user as a contact.

This was a very quick breakdown of our program. For a more detailed description, please check out the phase 1 README, and then phase 2 README.
