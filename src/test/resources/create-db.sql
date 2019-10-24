DROP TABLE IF EXISTS employee;
CREATE TABLE employee
(
    employee_id SERIAL,
    first_name VARCHAR (20),
    last_name  VARCHAR (20),
    department_id INT,
    job_title  VARCHAR (20),
    gender gender_t,
    date_of_birth date,
    CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
);