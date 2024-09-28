-- You need to create a detailed sales report. First, create a temporary table to store interim sales data for each product, including total sales, average sales per customer, and the top salesperson for each product.
-- Hint: Use temporary tables and insert data from subqueries.


CREATE TEMPORARY TABLE temp_sales_report (
    product_id INT,
    total_sales DECIMAL(10, 2),
    avg_sales_per_customer DECIMAL(10, 2),
    top_salesperson VARCHAR(100)
);
