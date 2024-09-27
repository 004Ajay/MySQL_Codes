## DATABASE INITIATOR CODES

```sql
CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    department_id INT,
    hire_date DATE
);
```

```sql
CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50)
);
```

```sql
CREATE TABLE salaries (
    employee_id INT PRIMARY KEY,
    salary INT,
    bonus INT,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);
```

```sql
INSERT INTO employees (employee_id, first_name, last_name, department_id, hire_date)
VALUES 
    (101, 'John', 'Doe', 1, '2020-01-01'),
    (102, 'Jane', 'Smith', 2, '2019-03-15'),
    (103, 'Robert', 'Brown', 3, '2018-06-20'),
    (104, 'Emily', 'Davis', 1, '2021-11-05'),
    (105, 'Michael', 'Johnson', 4, '2017-09-10');
```

```sql
INSERT INTO departments (department_id, department_name)
VALUES 
    (1, 'Sales'), 
    (2, 'HR'), 
    (3, 'IT'), 
    (4, 'Finance');
```

```sql
INSERT INTO salaries (employee_id, salary, bonus)
VALUES 
    (101, 60000, 5000),
    (102, 55000, 4000),
    (103, 70000, 6000),
    (104, 62000, 4500),
    (105, 80000, 7000);
```

---

### SQL QUERIES

```sql
-- Find the employees with salaries above the average
SELECT first_name, last_name
FROM employees
WHERE employee_id IN (
    SELECT employee_id 
    FROM salaries 
    WHERE salary > (
        SELECT AVG(salary) 
        FROM salaries
    )
);
```

```sql
-- Find the employees who earn more than the average salary in their department
SELECT e.first_name, e.last_name
FROM employees e
WHERE (SELECT salary 
       FROM salaries s 
       WHERE s.employee_id = e.employee_id) > (
       SELECT AVG(salary) 
       FROM salaries s2 
       JOIN employees e2 ON e2.employee_id = s2.employee_id 
       WHERE e2.department_id = e.department_id
);

```

```sql
-- Assign rank to employees based on their salary
SELECT employee_id, salary, 
       RANK() OVER (ORDER BY salary DESC) AS salary_rank
FROM salaries;
```

```sql
-- Calculate the total salary by department
SELECT department_id, 
       SUM(salary) OVER (PARTITION BY department_id) AS total_salary
FROM employees e
JOIN salaries s ON e.employee_id = s.employee_id;
```

---

## TASKS

