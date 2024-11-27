-- HackerRank
/*
 https://www.hackerrank.com/challenges/sql-projects/problem?isFullScreen=true
 */

CREATE TABLE Projects
(
    TASK_ID           INTEGER PRIMARY KEY AUTOINCREMENT,
    Start_Date        DATE,
    End_Date          DATE,
);

select  s.Start_Date, e.End_Date
FROM Projects s
CROSS JOIN Projects e
WHERE
    s.Start_Date NOT IN (
        SELECT End_Date FROM Projects
    )
    AND
    e.End_Date NOT IN(
        SELECT Start_Date
        FROM Projects
    )
    AND
    e.End_DATE = (
        SELECT End_Date
        FROM Projects
        WHERE End_Date >= s.End_Date
        AND End_Date NOT IN (SELECT Start_Date FROM Projects)
        ORDER BY End_Date
        LIMIT 1
    )
    ORDER BY DATEDIFF(e.End_Date, s.Start_Date)
;
