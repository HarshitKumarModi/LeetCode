# Write your MySQL query statement below
SELECT c.name AS Customers
FROM Customers c LEFT JOIN Orders o
ON C.id = o.customerId
WHERE o.customerId IS NULL;