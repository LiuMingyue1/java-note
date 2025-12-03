/**
 * --RDBMS : Oracle, MySQL, Postgre, H2, Derby, SQL server
 *
 * -- select * from hr.employees
 *
 * -- select first_name as FN, last_name as LN,
 * --     (
 * --         case salary
 * --             when 4800 then 1
 * --             else 0
 * --         end
 * --     ) as salary
 * -- from hr.employees
 * -- where salary > 1000 and salary < 5000
 * -- order by salary desc, first_name asc
 *
 * -- select * from hr.employees
 *
 * --aggregation function
 * -- select max(salary)
 * -- from hr.employees
 *
 * -- select count(*)
 * -- from hr.employees
 *
 * -- select avg(salary) / sum() / min()
 *
 * --subquery
 * select 2nd highest salary in employees table
 * sol1: sort -> 2nd highest salary
 * sol2: for loop 1 get max,  for loop 2 get 2nd max
 *
 * select max(salary)
 * from hr.employees t
 * where t.salary < (select max(x.salary) from hr.employees x)
 *
 * nth highest salary
 * select t.*
 * from hr.employees t
 * where 2 = (select count(x.salary) from hr.employees x where x.salary > t.salary)
 *
 * 24000  1
 * 24000  1
 * 17000  2
 *
 * emp1  sal1 -> count(salary > sal1) = n - 1 :  (select count(x.salary) from hr.employees x where x.salary > sal1)
 * emp2  sal2 -> count(salary > sal2) = n - 1 :  (select count(x.salary) from hr.employees x where x.salary > sal2)
 * emp3  sal3 -> count(salary > sal3) != n - 1
 *
 *
 * select *
 * from (
 *         select t.*, dense_rank() over (order by salary desc) as rank
 *         from hr.employees t
 *     )
 * where rank = 2
 *
 *
 * select first_name, department_id, max(salary)
 * from hr.employees
 * group by department_id
 *
 * select department_id, max(salary)
 * from hr.employees
 * where
 * group by department_id
 * having max(salary) > 5000
 * ------------------------------------------------------
 * select *
 * from (
 *         select t.*, dense_rank() over (order by salary desc) as rank
 *         from hr.employees t
 *     )
 * where rank = 2
 * ------------------------------------------------------
 * 2nd highest salary in each department
 * select *
 * from (select department_id, salary, rank() over (partition by department_id order by salary) as rank
 *     from hr.employees)
 * where rank = 2
 *
 * emp1, dept1, sal1, rank1
 * emp2, dept1, sal2, rank2
 * emp3, dept2, sal3, rank1
 * ------------------------------------------------------
 * count employee number in each department, return column as dept, emp_cnt
 * select department_id as dept, count(employee_id) as emp_cnt
 * from hr.employees
 * group by department_id
 *
 *
 * --union / union all , intersect , except / minus
 * A + B
 * select count(*)
 * from (
 *     select first_name from hr.employees
 *     union all
 *     select first_name, from hr.employees
 * )
 *
 * select count(*)
 * from (
 *     select * from hr.employees
 *     union
 *     select * from hr.employees
 * )
 *
 *
 * shared (A , B)
 * select count(*)
 * from (
 *     select * from hr.employees where salary > 5000
 *     intersect
 *     select * from hr.employees where salary < 5000
 * ) //return 0
 *
 *
 * A - shared (A, B)
 * select count(*)
 * from (
 *     select * from hr.employees where salary > 5000
 *     minus / except
 *     select * from hr.employees where salary < 5000
 * )
 * ------------------------------------------------
 *
 * emp
 * emp1 ,  dept1
 * emp2 ,  dept2
 *
 * dept
 * dept1, dept info1
 * dept2, ...
 *
 * join : inner join, outer join, cross join
 *
 * cross join: from A, B
 * select * from emp, dept
 * emp1, dept1, dept1, dept info1
 * emp1, dept1, dept2, dept info2
 * emp2, dept2, dept1, dept info1
 * emp2, dept2, dept2, dept info2
 * select *
 * from emp e, dept d
 * where e.dept_id = d.dept_id
 * ------
 * inner join: from A join B on xx
 * select *
 * from emp e join dept d on e.dept_id = d.dept_id
 * ------
 * outer join: from A left join B on xx / A left join B == B right join A
 * emp
 * emp1 ,  dept1
 * emp2 ,  dept2
 * emp3 ,  null
 *
 * dept
 * dept1, dept info1
 * dept2, ...
 * dept3,
 *
 * select *
 * from emp e right join dept d on e.dept_id = d.dept_id
 * emp1, dept1, dept1, dept info1
 * emp2, dept2, dept2, dept info2
 * null, null , dept3, dept info3
 *
 *
 * select *
 * from emp e full join dept d on e.dept_id = d.dept_id
 * emp1, dept1, dept1, dept info1
 * emp2, dept2, dept2, dept info2
 * null, null , dept3, dept info3
 * emp3 ,  null, null , null
 *
 *
 *
 * given emp + dept table
 * emp table (id, name, salary, dept_id)
 * dept table (id, name)
 * 1. count employee number in each dept -> return dept id, name, count
 * 2. get 2nd highest emp salary in each dept -> return dept id, name, salary
 */