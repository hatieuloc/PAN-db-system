DROP TABLE IF EXISTS Contact_information;
DROP TABLE IF EXISTS Emergency_contact;
DROP TABLE IF EXISTS Needs_list;
DROP TABLE IF EXISTS Insurance_policy;
DROP TABLE IF EXISTS Work_in;
DROP TABLE IF EXISTS Care;
DROP TABLE IF EXISTS Report;
DROP TABLE IF EXISTS Expense;
DROP TABLE IF EXISTS Donation;
DROP TABLE IF EXISTS Donors;
DROP TABLE IF EXISTS Employees;
DROP TABLE IF EXISTS Volunteers;
DROP TABLE IF EXISTS Clients;
DROP TABLE IF EXISTS Team;
DROP TABLE IF EXISTS Person;

CREATE TABLE Person (
    ssn INT PRIMARY KEY,
    person_name VARCHAR(100) NOT NULL,
    gender VARCHAR(6) NOT NULL,
    profession VARCHAR(100),
    on_mail_list VARCHAR(1) NOT NULL,

    CONSTRAINT CHK_gender CHECK (gender IN ('Male', 'Female', 'Other')),
    CONSTRAINT CHK_mail_list CHECK (on_mail_list IN ('Y', 'N'))
);

CREATE TABLE Contact_information (
    ssn INT,
    email VARCHAR(100) NOT NULL,
    mailing_address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,

    CONSTRAINT CHK_phone CHECK (phone_number LIKE '[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'),

    CONSTRAINT PK_cpe PRIMARY KEY (ssn),
    CONSTRAINT FK_ci FOREIGN KEY (ssn) REFERENCES Person (ssn) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Emergency_contact (
    ssn INT,
    emergency_name VARCHAR(100) DEFAULT 'N/A',
    emergency_phone_number VARCHAR(15) DEFAULT 'N/A',
    relationship VARCHAR(15) DEFAULT 'N/A',

    CONSTRAINT PK_ec PRIMARY KEY (ssn, emergency_name, emergency_phone_number),
    CONSTRAINT FK_ec FOREIGN KEY (ssn) REFERENCES Person ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Clients (
    ssn INT,
    dr_name VARCHAR(100) NOT NULL,
    dr_phone VARCHAR(15) NOT NULL,
    date_assigned DATE NOT NULL,

    CONSTRAINT CHK_drphone CHECK (dr_phone LIKE '[0-9][0-9][0-9]-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'),
    CONSTRAINT PK_c PRIMARY KEY (ssn),
    CONSTRAINT FK_c FOREIGN KEY (ssn) REFERENCES Person ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Needs_list (
    ssn INT,
    needs VARCHAR(100) NOT NULL,
    value_of_importance INT NOT NULL,

    CONSTRAINT CHK_drnlphone CHECK (value_of_importance BETWEEN 1 AND 10),
    CONSTRAINT CHK_needs CHECK (needs IN ('visiting', 'shopping', 'housekeeping', 'transportation', 'yard_work', 'food')),

    CONSTRAINT PK_cnl PRIMARY KEY (ssn, needs),
    CONSTRAINT FK_cnl FOREIGN KEY (ssn) REFERENCES Clients (ssn) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Insurance_policy (
    insurance_id INT,
    ssn INT,
    provider_name VARCHAR(100) NOT NULL,
    provider_address VARCHAR(100) NOT NULL,
    insurance_type VARCHAR(100) NOT NULL,

    CONSTRAINT CHK_instype CHECK (insurance_type IN ('life', 'health', 'home', 'auto')),

    CONSTRAINT PK_clip PRIMARY KEY (insurance_id, ssn), 
    CONSTRAINT FK_clip FOREIGN KEY (ssn) REFERENCES Clients (ssn) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Volunteers (
    ssn INT PRIMARY KEY,
    date_joined DATE NOT NULL,
    training_location VARCHAR(4) NOT NULL,
    training_date DATE NOT NULL,

    CONSTRAINT CHK_loc CHECK (training_location LIKE '[A-Z][0-9][0-9][0-9]'),

    CONSTRAINT FK_vo FOREIGN KEY (ssn) REFERENCES Person (ssn) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Team (
    team_name VARCHAR(100) PRIMARY KEY,
    team_type VARCHAR(100) NOT NULL,
    date_formed DATE NOT NULL
);

CREATE TABLE Volunteer_Role (
    team_name VARCHAR(100) PRIMARY KEY,
    ssn INT PRIMARY KEY,
    volunteer_role VARCHAR(6) NOT NULL,

    CONSTRAINT CHK_vrole CHECK (v_role IN ('Leader', 'Member')),

    CONSTRAINT PK_vlr PRIMARY KEY (ssn, worked_hours),
    CONSTRAINT FK_vlrvl FOREIGN KEY (ssn) REFERENCES Volunteers ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_vltn FOREIGN KEY (team_name) REFERENCES Team ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE Work_in (
    ssn INT,
    team_name VARCHAR(100),
    volun_is_active VARCHAR(1) NOT NULL,
    worked_month VARCHAR(3) NOT NULL,
    worked_year INT NOT NULL,
    worked_hours INT NOT NULL,

    CONSTRAINT CHK_vol_act CHECK (volun_is_active IN ('Y', 'N')),   
    CONSTRAINT CHK_my CHECK (worked_month IN ('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec')),

    CONSTRAINT PK_wi PRIMARY KEY (ssn, worked_hours),
    CONSTRAINT FK_sw FOREIGN KEY (ssn) REFERENCES Volunteers ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_tw FOREIGN KEY (team_name) REFERENCES Team ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Care (
    ssn INT ,
    team_name VARCHAR(100),
    clients_is_active VARCHAR(1) NOT NULL,

    CONSTRAINT CHK_act CHECK(clients_is_active IN ('Y', 'N')),

    CONSTRAINT PK_stns PRIMARY KEY (ssn, team_name),
    CONSTRAINT FK_ss FOREIGN KEY (ssn) REFERENCES Clients(ssn) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_t FOREIGN KEY (team_name) REFERENCES Team(team_name) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Employees (
    ssn INT,
    salary REAL NOT NULL,
    marital_status VARCHAR(15) NOT NULL,
    hire_date DATE NOT NULL,

    CONSTRAINT CHK_mari CHECK(marital_status IN ('married', 'single', 'divorced', 'separated', 'widowed')),

    CONSTRAINT PK_e PRIMARY KEY (ssn),
    CONSTRAINT FK_e FOREIGN KEY (ssn) REFERENCES Person ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Report (
    team_name VARCHAR(100),
    ssn INT NOT NULL,
    rep_description VARCHAR(255) NOT NULL,
    date_report DATE NOT NULL,

    CONSTRAINT PK_r PRIMARY KEY (team_name, ssn),
    CONSTRAINT FK_tr FOREIGN KEY (team_name) REFERENCES Team ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FK_er FOREIGN KEY (ssn) REFERENCES Employees ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Expense (
    ssn INT,
    date_charge DATE NOT NULL,
    e_amount REAL NOT NULL,
    e_description VARCHAR(100) NOT NULL,

    CONSTRAINT PK_expe PRIMARY KEY (ssn, date_charge, e_amount),
    CONSTRAINT FK_expe FOREIGN KEY (ssn) REFERENCES Employees ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Donors (
    ssn INT,
    is_anonymous VARCHAR(1) NOT NULL,
    
    CONSTRAINT CHK_iano CHECK(is_anonymous IN ('Y', 'N')),

    CONSTRAINT PK_d PRIMARY KEY (ssn),
    CONSTRAINT FK_d FOREIGN KEY (ssn) REFERENCES Person ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Donation (
    ssn INT,
    d_date DATE NOT NULL,
    d_amount REAL NOT NULL, 
    campaign_name VARCHAR(100) DEFAULT 'N/A',
    d_type VARCHAR(5) NOT NULL,
    check_number VARCHAR(15) DEFAULT 'N/A',
    card_type VARCHAR(15) DEFAULT 'N/A',
    card_number VARCHAR(15) DEFAULT 'N/A',
    expire_date VARCHAR(15) DEFAULT 'N/A',

    CONSTRAINT CHK_dtype CHECK(d_type IN ('Check', 'Card')),
    CONSTRAINT CHK_ctype CHECK(card_type IN ('Visa', 'Master', 'Discover', 'American Express', 'N/A')),

    CONSTRAINT PK_dona PRIMARY KEY (ssn, d_date, d_amount),
    CONSTRAINT FK_dd FOREIGN KEY (ssn) REFERENCES Donors ON UPDATE CASCADE ON DELETE CASCADE
);




