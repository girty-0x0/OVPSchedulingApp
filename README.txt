Title: OVP Scheduling Application

Author: Orlando Vera Perez
Version: 1.1.2
Date: 01/07/2023
Email: overape@wgu.edu

IDE: IntelliJ IDEA 2021.1.3 (Community Edition)
JDK Version: Java SE Development Kit 17.0.1 (JDK 17.0.1)
JavaFX Version: JavaFX-SDK-17.0.1
MySQL Connector Driver Version: mysql-connector-java-8.0.25

Purpose:
This is a desktop scheduling application with the ability to add, remove, and modify appointments as well as customers. The intuitive GUI will
allow all users to efficiently make appointments with international features like timezone conversions, a translated log-in screen, selection
of administrative divisions, and more.
Specifically made to interface with a MySQL database, this will allow for users all over the world to
track any upcoming appointments in their respective timezones while updating the central database in UTC time. Also, any information changed in
the database will update on the user's screen every time the application is opened. That means, all information is updated in a standardized manner
for all application users.

How to run the program (IDE):
1. Configure IDE to launch application from Main/Main.java
2. Run Application
3. Log-in with credentials stored in the application's database (e.g.: Username: "test" ; Password: "test")
4. Enjoy your new scheduling application!

How to run the program (No IDE):
1. Launch the Application
2. Log-in with credentials stored in the application's database (e.g.: Username: "test" ; Password: "test")
3. Enjoy your new scheduling application!

Reports displayed:
1. Appointments by Type & Month: After choosing a month and type from the drop-down menus, click "Get Total".
    A table will show all the appointments and count the total number of appointments of the specified type and chosen month.
2. Schedule for Contact: After selecting a contact from the drop-down menu, the table will populate all the appointments for the specified contact.
3. Upcoming Appointments for Customer: After selecting a customer from the drop-down menu, the table will populate all appointments a customer has
    from the moment the customer was specified.

