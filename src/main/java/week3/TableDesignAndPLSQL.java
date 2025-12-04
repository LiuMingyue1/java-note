/**
 * 1 - 1
 * 1 - m : teacher 1 - m student
 *      teacher(t_id(pk), teacher_name)
 *      student(s_id, stu_name, t_id(fk))
 * m - m : teacher m - m student
 *      teacher(t_id(pk), teacher_name)
 *      teacher_student(junction table/associate table)..
 *          option1: id(pk), s_id(fk), t_id(fk)
 *          option2: (s_id, t_id)(pk)
 *      student(s_id, stu_name)
 *
 *      teacher 1 - m teacher_student m - 1 student
 *
 * normalization vs de-normalization
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  survey
 *  1. id, name, json
 *  2. id, name, col1, col2, col3, col4, col5
 *  3. parent table(id, name)
 *     survey1 table
 *     survey2 table
 *  4. entity attribute mapping
 *      id, survey_id, column_name, column_type, column_value
 *       1,     a101 ,   "name"   ,  "string",    "Tom"
 *       2,     a101 ,   "gender" ,  "string",    "male"
 *       ...
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  PL/SQL
 *  Trigger
     CREATE OR REPLACE TRIGGER Print_salary_changes
     BEFORE DELETE OR INSERT OR UPDATE ON Emp_tab
     FOR EACH ROW WHEN (new.Empno > 0)
     DECLARE
        sal_diff number;
     BEGIN
         sal_diff  := :new.sal  - :old.sal;
         dbms_output.put('Old salary: ' || :old.sal);
         dbms_output.put('  New salary: ' || :new.sal);
         dbms_output.put_line('  Difference ' || sal_diff);
     END;

 *  Stored Procedure
 CREATE OR REPLACE PROCEDURE process_employees_by_dept (
     p_department_id IN NUMBER
 )
 IS
 -- Declare an explicit cursor
     CURSOR emp_cursor IS
     SELECT employee_id, first_name, last_name, salary
     FROM employees
     WHERE department_id = p_department_id;

     -- Declare a record variable to hold fetched data
     emp_rec emp_cursor%ROWTYPE;
 BEGIN
     -- Open the cursor
     OPEN emp_cursor;

     LOOP
         -- Fetch a row into the record variable
         FETCH emp_cursor INTO emp_rec;

         -- Exit the loop when no more rows are found
         EXIT WHEN emp_cursor%NOTFOUND;

         -- Process the fetched data (e.g., display it)
         DBMS_OUTPUT.PUT_LINE(
         'Employee ID: ' || emp_rec.employee_id ||
         ', Name: ' || emp_rec.first_name || ' ' || emp_rec.last_name ||
         ', Salary: ' || emp_rec.salary
         );

         -- You can perform other operations here, like updating a table
         -- UPDATE employees SET bonus = emp_rec.salary * 0.1 WHERE employee_id = emp_rec.employee_id;

     END LOOP;

    -- Close the cursor
    CLOSE emp_cursor;

 EXCEPTION
     WHEN OTHERS THEN
         -- Handle any exceptions
         DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
     IF emp_cursor%ISOPEN THEN
        CLOSE emp_cursor;
     END IF;
 END;

 *  Function

 CREATE OR REPLACE FUNCTION myFunc (
     p_monthly_salary IN NUMBER,
     p_commission     IN NUMBER
 ) RETURN NUMBER IS
     v_annual_salary NUMBER;
 BEGIN
     v_annual_salary := (p_monthly_salary * 12) + NVL(p_commission, 0);
     RETURN v_annual_salary;
 END;

 select
 from xx
 where myFunc(..) >
 *
 *  Package
 package_name1.storedProcedure1
 package_name1.storedProcedure2
 package_name2.function1
 *
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  spring boot
 *  
 *
 */