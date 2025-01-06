/*
query 1: 
Enter a new team into the database
*/
DROP PROCEDURE IF EXISTS query1;
GO 
CREATE PROCEDURE query1
    @team_name VARCHAR(100),
    @team_type VARCHAR(100),
    @date_formed DATE
AS
BEGIN
INSERT INTO Team VALUES
    (@team_name, @team_type, @date_formed);
END
GO

/*
query 2: 
Enter a new client into the database and associate him or her with one or more teams
*/
DROP PROCEDURE IF EXISTS query2_1;
GO 
CREATE PROCEDURE query2_1
    @ssn INT,
    @person_name VARCHAR(100),
    @gender VARCHAR(6),
    @profession VARCHAR(100),
    @on_mail_list VARCHAR(1),
    @email VARCHAR(100),
    @mailing_address VARCHAR(255),
    @phone_number VARCHAR(15),
    @emergency_name VARCHAR(100),
    @emergency_phone_number VARCHAR(15),
    @relationship VARCHAR(15),
    @dr_name VARCHAR(100),
    @dr_phone VARCHAR(15),
    @date_assigned DATE
AS
BEGIN
INSERT INTO Person(ssn, person_name, gender, profession, on_mail_list) VALUES
    (@ssn, @person_name, @gender, @profession, @on_mail_list);

INSERT INTO Contact_information(ssn, email, mailing_address, phone_number) VALUES
    (@ssn, @email, @mailing_address, @phone_number);

INSERT INTO Emergency_contact (ssn, emergency_name, emergency_phone_number, relationship) VALUES
    (@ssn, @emergency_name, @emergency_phone_number, @relationship);

INSERT INTO Clients(ssn, dr_name, dr_phone, date_assigned) VALUES
    (@ssn, @dr_name, @dr_phone, @date_assigned);
END
GO
-- procedure to add more team care for client
DROP PROCEDURE IF EXISTS query2_2;
GO 
CREATE PROCEDURE query2_2
    @ssn INT,
    @team_name VARCHAR(100),
    @clients_is_active VARCHAR(1)
AS
BEGIN
INSERT INTO Care(ssn, team_name, clients_is_active) VALUES
    (@ssn, @team_name, @clientS_is_active);
END
GO
-- procedure to add list of needs
DROP PROCEDURE IF EXISTS query2_3;
GO 
CREATE PROCEDURE query2_3
    @ssn INT,
    @needs VARCHAR(100),
    @value_of_importance INT
AS
BEGIN
INSERT INTO Needs_list(ssn, needs, value_of_importance) VALUES
    (@ssn, @needs, @value_of_importance);
END
GO
-- procedure to add insurance policy
DROP PROCEDURE IF EXISTS query2_4;
GO 
CREATE PROCEDURE query2_4
    @ssn INT,
    @insurance_id INT,
    @provider_name VARCHAR(100),
    @provider_address VARCHAR(100),
    @insurance_type VARCHAR(100)
AS
BEGIN
INSERT INTO Insurance_policy(insurance_id, ssn, provider_name, provider_address, insurance_type) VALUES
    (@insurance_id, @ssn, @provider_name, @provider_address, @insurance_type);
END
GO

/*
query 3:
Enter a new volunteer into the database and associate him or her with one or more teams
*/
DROP PROCEDURE IF EXISTS query3_1;
GO 
CREATE PROCEDURE query3_1
    @ssn INT,
    @person_name VARCHAR(100),
    @gender VARCHAR(6),
    @profession VARCHAR(100),
    @on_mail_list VARCHAR(1),
    @email VARCHAR(100),
    @mailing_address VARCHAR(255),
    @phone_number VARCHAR(15),
    @emergency_name VARCHAR(100),
    @emergency_phone_number VARCHAR(15),
    @relationship VARCHAR(15),
    @date_joined DATE,
    @training_location VARCHAR(4),
    @training_date DATE
    
AS
BEGIN
INSERT INTO Person(ssn, person_name, gender, profession, on_mail_list) VALUES
    (@ssn, @person_name, @gender, @profession, @on_mail_list);

INSERT INTO Contact_information(ssn, email, mailing_address, phone_number) VALUES
    (@ssn, @email, @mailing_address, @phone_number);

INSERT INTO Emergency_contact (ssn, emergency_name, emergency_phone_number, relationship) VALUES
    (@ssn, @emergency_name, @emergency_phone_number, @relationship);

INSERT INTO Volunteers(ssn, date_joined, training_location, training_date) VALUES
    (@ssn, @date_joined, @training_location, @training_date);
END
GO
-- associate volunteer with 1 or more team
DROP PROCEDURE IF EXISTS query3_2;
GO 
CREATE PROCEDURE query3_2
    @ssn INT,
    @team_name VARCHAR(100),
    @volun_is_active VARCHAR(1),
    @volunteer_role VARCHAR(6),
    @worked_month VARCHAR(3),
    @worked_year INT,
    @worked_hours INT
AS
BEGIN
INSERT INTO Volunteer_Role(ssn, team_name, v_role) VALUES
    (@ssn, @team_name, @volunteer_role);

INSERT INTO Work_in(ssn, team_name, volun_is_active, worked_month, worked_year, worked_hours) VALUES 
    (@ssn, @team_name, @volun_is_active, @worked_month, @worked_year, @worked_hours);
END
GO

/*
query 4: 
Enter the number of hours a volunteer worked this month for a particular team
*/
DROP PROCEDURE IF EXISTS query4;
GO
CREATE PROCEDURE query4
    @ssn INT,
    @team_name VARCHAR(100),
    @worked_month VARCHAR(3),
    @worked_year INT,
    @worked_hours INT

AS
BEGIN
UPDATE Work_in
SET worked_hours =  @worked_hours
WHERE ssn = @ssn AND team_name = @team_name AND worked_year = @worked_year
END
GO

/*
query 5:
Enter a new employee into the database and associate him or her with one or more teams
*/
DROP PROCEDURE IF EXISTS query5_1;
GO 
CREATE PROCEDURE query5_1
    @ssn INT,
    @person_name VARCHAR(100),
    @gender VARCHAR(6),
    @profession VARCHAR(100),
    @on_mail_list VARCHAR(1),
    @email VARCHAR(100),
    @mailing_address VARCHAR(255),
    @phone_number VARCHAR(15),
    @emergency_name VARCHAR(100),
    @emergency_phone_number VARCHAR(15),
    @relationship VARCHAR(15),
    @salary REAL,
    @marital_status VARCHAR(15),
    @hire_date DATE   
AS
BEGIN
INSERT INTO Person(ssn, person_name, gender, profession, on_mail_list) VALUES
    (@ssn, @person_name, @gender, @profession, @on_mail_list);

INSERT INTO Contact_information(ssn, email, mailing_address, phone_number) VALUES
    (@ssn, @email, @mailing_address, @phone_number);

INSERT INTO Emergency_contact (ssn, emergency_name, emergency_phone_number, relationship) VALUES
    (@ssn, @emergency_name, @emergency_phone_number, @relationship);

INSERT INTO Employees(ssn, salary, marital_status, hire_date) VALUES
    (@ssn, @salary, @marital_status, @hire_date);
END
GO

-- associate with another team
DROP PROCEDURE IF EXISTS query5_2;
GO 
CREATE PROCEDURE query5_2
    @team_name VARCHAR(100),
    @ssn INT,
    @rep_description VARCHAR(255),
    @date_report DATE

AS
BEGIN
INSERT INTO Report(team_name, ssn, rep_description, date_report) VALUES
    (@team_name, @ssn, @rep_description, @date_report);
END
GO

/*
query 6:
Enter an expense charged by an employee 
*/
DROP PROCEDURE IF EXISTS query6;
GO 
CREATE PROCEDURE query6
    @ssn INT,
    @date_charge DATE,
    @e_amount REAL,
    @e_description VARCHAR(100)
    
AS
BEGIN
INSERT INTO Expense(ssn, date_charge, e_amount, e_description) VALUES
    (@ssn, @date_charge, @e_amount, @e_description);
END
GO

/*
query 7:
Enter a new donor and associate him or her with several donations
*/
DROP PROCEDURE IF EXISTS query7_1;
GO 
CREATE PROCEDURE query7_1
    @ssn INT,
    @person_name VARCHAR(100),
    @gender VARCHAR(6),
    @profession VARCHAR(100),
    @on_mail_list VARCHAR(1),
    @email VARCHAR(100),
    @mailing_address VARCHAR(255),
    @phone_number VARCHAR(15),
    @emergency_name VARCHAR(100),
    @emergency_phone_number VARCHAR(15),
    @relationship VARCHAR(15),
    @is_anonymous VARCHAR(1)    
AS
BEGIN
INSERT INTO Person(ssn, person_name, gender, profession, on_mail_list) VALUES
    (@ssn, @person_name, @gender, @profession, @on_mail_list);

INSERT INTO Contact_information(ssn, email, mailing_address, phone_number) VALUES
    (@ssn, @email, @mailing_address, @phone_number);

INSERT INTO Emergency_contact (ssn, emergency_name, emergency_phone_number, relationship) VALUES
    (@ssn, @emergency_name, @emergency_phone_number, @relationship);

INSERT INTO Donors(ssn, is_anonymous) VALUES
    (@ssn, @is_anonymous);
END
GO

-- associate donor vs donation
DROP PROCEDURE IF EXISTS query7_2;
GO 
CREATE PROCEDURE query7_2
    @ssn INT,
    @d_date DATE,
    @d_amount REAL,
    @campaign_name VARCHAR(100),
    @d_type VARCHAR(5),
    @check_number VARCHAR(15),
    @card_type VARCHAR(15),
    @card_number VARCHAR(15),
    @expire_date VARCHAR(15)
AS
BEGIN
INSERT INTO Donation(ssn, d_date, d_amount, campaign_name, d_type, check_number, card_type, card_number, expire_date) VALUES
    (@ssn, @d_date, @d_amount, @campaign_name, @d_type, @check_number, @card_type, @card_number, @expire_date);
END
GO

/*
query 8:
Retrieve the name and phone number of the doctor of a particular client
*/
DROP PROCEDURE IF EXISTS query8;
GO 
CREATE PROCEDURE query8
    @ssn INT
AS
BEGIN
SELECT dr_name, dr_phone 
FROM Clients 
WHERE ssn = @ssn;
END
GO

/*
query 9:
Retrieve the total amount of expenses charged by each employee for a particular period of time. 
The list should be sorted by the total amount of expenses
*/
DROP PROCEDURE IF EXISTS query9;
GO 
CREATE PROCEDURE query9
    @from_time DATE,
    @to_time DATE
AS
BEGIN
SELECT ssn, SUM(e_amount) AS total_amount
FROM Expense 
WHERE date_charge BETWEEN @from_time AND @to_time
GROUP BY ssn
ORDER BY SUM(e_amount);
END
GO

/*
query 10:
Retrieve the list of volunteers that are members of teams that support a particular client
*/
DROP PROCEDURE IF EXISTS query10;
GO 
CREATE PROCEDURE query10
    @ssn INT
AS
BEGIN
SELECT ssn 
FROM Person
WHERE ssn IN (
    SELECT wi.ssn 
    FROM Work_in wi
    JOIN Care c ON wi.team_name = c.team_name 
    WHERE c.ssn = @ssn AND wi.volunteer_role = 'Member');
END
GO

/*
query 11:
Retrieve the names of all teams that were founded after a particular date
*/
DROP PROCEDURE IF EXISTS query11;
GO 
CREATE PROCEDURE query11
    @date_after DATE
AS
BEGIN
SELECT team_name, date_formed
FROM Team 
WHERE date_formed > @date_after
ORDER BY date_formed;
END
GO

/*
query 12:
Retrieve the names, social security numbers, contact information, and emergency contact
information of all people in the database
*/
DROP PROCEDURE IF EXISTS query12;
GO 
CREATE PROCEDURE query12
AS
BEGIN
SELECT p.person_name, p.ssn, ci.email, ci.mailing_address, ci.phone_number, em.emergency_name, em.emergency_phone_number, em.relationship
FROM Person p 
FULL OUTER JOIN Contact_information ci ON p.ssn = ci.ssn
FULL OUTER JOIN Emergency_contact em ON p.ssn = em.ssn
END
GO

/*
query 13:
Retrieve the name and total amount donated by donors that are also employees. The list
should be sorted by the total amount of the donations, and indicate if each donor wishes to
remain anonymous
*/
DROP PROCEDURE IF EXISTS query13;
GO 
CREATE PROCEDURE query13
AS
BEGIN
SELECT p.person_name, SUM(dn.d_amount) AS total_donation_amount, d.is_anonymous
FROM Person p
INNER JOIN Donors d ON p.ssn = d.ssn
INNER JOIN Employees e ON p.ssn = e.ssn
INNER JOIN Donation dn ON d.ssn = dn.ssn
WHERE p.ssn IN (
    SELECT d.SSN
    FROM Donors d, Employees e 
    WHERE d.ssn = e.ssn AND p.ssn IN (
        SELECT d.ssn
        FROM Donation, Donors
        WHERE d.ssn = dn.ssn))
GROUP BY person_name, is_anonymous
ORDER BY total_donation_amount;
END
GO

/*
query 14:
Increase the salary by 10% of all employees to whom more than one team must report
*/
DROP PROCEDURE IF EXISTS query14;
GO 
CREATE PROCEDURE query14
AS
BEGIN
UPDATE Employees
SET salary *= 1.1
WHERE ssn IN (
    SELECT r.ssn
    FROM Report r
    JOIN Employees e ON r.ssn = e.ssn
    GROUP BY r.ssn
    HAVING COUNT(r.ssn) >= 2);
END
GO

/*
query 15:
Delete all clients who do not have health insurance and whose value of importance for
transportation is less than 5
*/
DROP PROCEDURE IF EXISTS query15;
GO 
CREATE PROCEDURE query15
AS
BEGIN
DELETE FROM Person
WHERE ssn IN (
    SELECT ip.ssn  
    FROM Insurance_policy ip
    WHERE ssn IN (
        SELECT nl.ssn 
        FROM Needs_list nl
        WHERE needs = 'transportation' AND value_of_importance < 5) AND NOT EXISTS (
            SELECT 1 
            FROM Insurance_policy ip 
            WHERE ip.ssn = ssn AND insurance_type = 'health'));
END
GO

/*
query 17:
Export: Retrieve names and mailing addresses of all people on the mailing list and
output them to a data file instead of screen (the user must be asked to enter the output file name)
*/
DROP PROCEDURE IF EXISTS query17;
GO 
CREATE PROCEDURE query17
AS
BEGIN
SELECT person_name, mailing_address
FROM Person, Contact_information 
WHERE on_mail_list = 'Y' AND Person.ssn = Contact_information.ssn 
END
GO
