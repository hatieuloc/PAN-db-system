# PAN-db-system
##I. DESCRIPTION
The Patient Assistance Network (PAN) is a non-profit organization that provides support and care for patients. PAN needs to implement a database system to keep track of the personnel necessary to support the organization. In this project, your task will be to design and implement this database system. The information that needs to be stored in the database is described in this section.

There are many categories of people that need to be tracked in the PAN database. Each person can fall into one or more than one of the following categories: clients, volunteers, employees, and donors. PAN tracks the name, social security number, gender, and profession of each person. In addition, PAN stores the contact information for each person consisting of a mailing address, email address, and phone number. PAN also sends a monthly newsletter to people on its mailing list, so the database should indicate whether or not each person in the database is on that list. Finally, the system should have the ability to store a list of emergency contacts for the people in the database. This information should record the name and phone number for each of the emergency contacts along with their respective relationship to the person in the database.

PAN tracks its list of clients in the database. For each client, PAN tracks the name and phone number of his or her doctor. PAN also tracks the date the client was first assigned to the organization. Each client has a list of needs. Examples of these needs include visiting, shopping, housekeeping, transportation, yard work, and food. Each of these needs is also associated with a value indicating its importance to the client (1-10). PAN also tracks the list of insurance policies
that each client has. Each insurance policy has a unique policy id, a provider name, provider address, and a type such as life, health, home, or auto.

PAN provides care for each client using teams that contain many volunteers. Each team cares for several clients, and more than one team may care for a client. Each team is identified by its name, and each team also has a type and a date it was formed. A volunteer may serve on multiple teams. For each volunteer, the database should store the date he or she first joined PAN and the date and location of his or her most recent training course. In addition, PAN should record the number of hours a volunteer worked each month for a particular team. Note that the volunteers do not work the same number of hours each month. One of the volunteers on a team serves as the team leader. This information should be tracked in the database as well. In addition, volunteers and clients may switch teams; so, the database system should provide the ability to mark whether or not each volunteer and client is active or inactive on a specific team.

Every team reports periodically to one PAN employee to discuss its current status, and more than one team may report to the same employee. The database should record the date of each report as well as a description of its content. For each employee, the database should store the employee’s salary, marital status, and hire date. An employee may charge several expenses each month. The database should track the date of the expense, along with the amount and its description.

PAN depends on support from its donors. The database should track these people as well as record each of their donations. This information should include the date, amount, and type of donation along with the name of the fund raising campaign that generated the donation if it is applicable. If the donation was made by check, the database should record the check number. If the donation was made by credit card, the database should record the card number, card type, and expiration date. In addition, each donor may wish to remain anonymous, so the database should record that information as well.

Queries and Their Frequencies for the PAN Database System
1. Enter a new team into the database (1/month).
2. Enter a new client into the database and associate him or her with one or more teams (1/week).
3. Enter a new volunteer into the database and associate him or her with one or more teams (2/month).
4. Enter the number of hours a volunteer worked this month for a particular team (30/month).
5. Enter a new employee into the database and associate him or her with one or more teams (1/year).
6. Enter an expense charged by an employee (1/day).
7. Enter a new donor and associate him or her with several donations (1/day).
8. Retrieve the name and phone number of the doctor of a particular client (1/week).
9. Retrieve the total amount of expenses charged by each employee for a particular period of time. The list should be sorted by the total amount of expenses (1/month).
10. Retrieve the list of volunteers that are members of teams that support a particular client (4/year).
11. Retrieve the names of all teams that were founded after a particular date (1/month).
12. Retrieve the names, social security numbers, contact information, and emergency contact information of all people in the database (1/week).
13. Retrieve the name and total amount donated by donors that are also employees. The list should be sorted by the total amount of the donations, and indicate if each donor wishes to remain anonymous (1/week)
14. Increase the salary by 10% of all employees to whom more than one team must report. (1/year)
15. Delete all clients who do not have health insurance and whose value of importance for transportation is less than 5 (4/year).

##II. TASKS TO BE PERFORMED
Task 1: Design an ER diagram to represent the PAN database defined in Section I.
Task 2: Convert the ER diagram in Task 1 to a Relational Database (i.e. a set of relational schemas).
Task 3: Write SQL statements for all queries (1-15) defined in part I. Write a Java application program that uses JDBC and Azure SQL Database to implement all SQL queries (options 1-15), two additional queries for import and export (options 16- 17), and the “Quit” option (option 18) as specified in the menu given below. You are free to pick any file format you wish to use for file import and export options. The program will stop execution only when the user chooses the “Quit” option; otherwise, all options must be available for the user to choose at all times.

WELCOME TO THE PATIENT ASSISTANT NETWORK DATABASE SYSTEM
(1) Description of query 1
(2) Description of query 2
.
.
.
(15) Description of query 15
(16) Import: enter new teams from a data file until the file is empty (the user must be asked
to enter the input file name).
(17) Export: Retrieve names and mailing addresses of all people on the mailing list and
output them to a data file instead of screen (the user must be asked to enter the output file
name).
(18) Quit
