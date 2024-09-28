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

### SQL SAMPLE QUERIES

> [*Click here to see output of below queries as PDF*](https://github.com/004Ajay/Task1_MySQL/blob/main/Sample%20Queries%20Outputs.pdf)

```sql
-- Find the employees with salaries above the average.

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
-- Find the employees who earn more than the average salary in their department.

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
-- Assign rank to employees based on their salary.

SELECT employee_id, salary, 
       RANK() OVER (ORDER BY salary DESC) AS salary_rank
FROM salaries;
```

```sql
-- Calculate the total salary by department.

SELECT department_id, 
       SUM(salary) OVER (PARTITION BY department_id) AS total_salary
FROM employees e
JOIN salaries s ON e.employee_id = s.employee_id;
```

---

## TASK 1 

> [*Click here to see output of below queries as PDF*](https://github.com/004Ajay/MySQL_Codes/blob/main/Task1%20Queries%20Outputs.pdf)


```sql
-- Find all employees whose first names start with a vowel and whose last names end with a consonant.

SELECT first_name, last_name
FROM employees
WHERE first_name LIKE 'A%' OR first_name LIKE 'E%' OR first_name LIKE 'I%' 
   OR first_name LIKE 'O%' OR first_name LIKE 'U%'
  AND last_name NOT LIKE '%a' AND last_name NOT LIKE '%e' 
  AND last_name NOT LIKE '%i' AND last_name NOT LIKE '%o' 
  AND last_name NOT LIKE '%u';
```


```sql
-- For each department, display the total salary expenditure, the average salary, and the highest salary. Use window functions to calculate the total, average, and max salary, but show each result for all employees in that department.

SELECT department_id,
       SUM(salary) OVER (PARTITION BY department_id) AS total_salary,
       AVG(salary) OVER (PARTITION BY department_id) AS avg_salary,
       MAX(salary) OVER (PARTITION BY department_id) AS max_salary,
       first_name, last_name
FROM employees e
JOIN salaries s ON e.employee_id = s.employee_id;
```

```sql
-- Write a query that fetches the following:
-- All employees, their department name, their manager’s name (if they have one), and their salary.
-- You will need to:
-- Join employees with their department. Perform a self-join to fetch the manager’s name.

 ** no info about manager is given
```


```sql
-- Create a query using a recursive CTE to list all employees and their respective reporting chains (i.e., list the manager’s manager and so on).

 ** no info about manager is given
```


```sql
-- Write a query to fetch the details of employees earning above a certain salary threshold. Investigate the performance of this query and suggest improvements, including the use of indexes

-- Taking average of all employees salary as the salary threshold

SET profiling = 1; -- for logging the performance of queries

-- Main Query
SELECT e.employee_id, e.first_name, e.last_name, s.salary
FROM employees e
JOIN salaries s ON e.employee_id = s.employee_id
WHERE s.salary > (SELECT AVG(salary) FROM salaries);

-- Create index for quick lookup
CREATE INDEX idx_salary ON salaries(salary);
CREATE INDEX idx_employee_id ON salaries(employee_id);

-- Again run the same query above to check execution speed difference
SELECT e.employee_id, e.first_name, e.last_name, s.salary
FROM employees e
JOIN salaries s ON e.employee_id = s.employee_id
WHERE s.salary > (SELECT AVG(salary) FROM salaries);

SHOW profiles \G -- Now run this to see the timings, \G for viewing the output vertically

```


```sql
-- You need to create a detailed sales report. First, create a temporary table to store interim sales data for each product, including total sales, average sales per customer, and the top salesperson for each product.
-- Hint: Use temporary tables and insert data from subqueries.

-- Table Creation Queries
CREATE TABLE products (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(100)
);

CREATE TABLE salespersons (
    salesperson_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50)
);

CREATE TABLE sales (
    sales_id INT PRIMARY KEY,
    product_id INT,
    salesperson_id INT,
    sales_amount DECIMAL(10, 2),
    FOREIGN KEY (product_id) REFERENCES products(product_id),
    FOREIGN KEY (salesperson_id) REFERENCES salespersons(salesperson_id)
);

-- Inserting Sample Data

-- Sample Data for Products
INSERT INTO products (product_id, product_name) VALUES
(1, 'Product A'),
(2, 'Product B');

-- Sample Data for Salespersons
INSERT INTO salespersons (salesperson_id, first_name, last_name) VALUES
(1, 'John', 'Doe'),
(2, 'Jane', 'Smith');

-- Sample Data for Sales
INSERT INTO sales (sales_id, product_id, salesperson_id, sales_amount) VALUES
(1, 1, 1, 100.00),
(2, 1, 2, 150.00),
(3, 2, 1, 200.00),
(4, 2, 2, 300.00);

-- Temporary Table Creation
CREATE TEMPORARY TABLE temp_sales_report (
    product_id INT,
    total_sales DECIMAL(10, 2),
    avg_sales_per_customer DECIMAL(10, 2),
    top_salesperson VARCHAR(100)
);

-- Inserting require analysis data to temp table 
INSERT INTO temp_sales_report (product_id, total_sales, avg_sales_per_customer, top_salesperson)
SELECT 
    p.product_id,
    SUM(s.sales_amount) AS total_sales,
    AVG(s.sales_amount) AS avg_sales_per_customer,
    (SELECT CONCAT(sp.first_name, ' ', sp.last_name)
     FROM salespersons sp
     JOIN sales s2 ON sp.salesperson_id = s2.salesperson_id
     WHERE s2.product_id = p.product_id
     GROUP BY sp.salesperson_id
     ORDER BY SUM(s2.sales_amount) DESC
     LIMIT 1) AS top_salesperson
FROM products p
JOIN sales s ON p.product_id = s.product_id
GROUP BY p.product_id;

-- Displaying analysis result table (temp table)
 SELECT * FROM temp_sales_report;

-- Cleanup (Optional)
DROP TEMPORARY TABLE IF EXISTS temp_sales_report;

```