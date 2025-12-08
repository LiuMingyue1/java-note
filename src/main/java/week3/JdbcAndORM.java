package week3;

/**
 * public class JdbcExample {
 *     // Database credentials
 *     static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
 *     static final String USER = "root";
 *     static final String PASS = "your_password"; // Replace with your MySQL password
 *
 *     public static void main(String[] args) {
 *         Connection conn = null;
 *         Statement stmt = null;
 *         ResultSet rs = null;
 *
 *         try {
 *             // STEP 1: Register JDBC driver (not strictly necessary for modern JDBC drivers, but good practice)
 *             Class.forName("com.mysql.cj.jdbc.Driver"); // Use "com.mysql.jdbc.Driver" for older versions
 *
 *             // STEP 2: Open a connection
 *             System.out.println("Connecting to database...");
 *             conn = DriverManager.getConnection(DB_URL, USER, PASS);
 *
 *             // STEP 3: Execute a query
 *             System.out.println("Creating statement...");
 *             stmt = conn.createStatement();
 *             String sql = "SELECT id, name, age FROM employees";
 *             rs = stmt.executeQuery(sql);
 *
 *             // STEP 4: Process the results
 *             System.out.println("Fetching results:");
 *             while (rs.next()) {
 *                 // Retrieve by column name
 *                 int id = rs.getInt("id");
 *                 String name = rs.getString("name");
 *                 int age = rs.getInt("age");
 *
 *                 // Display values
 *                 System.out.print("ID: " + id);
 *                 System.out.print(", Name: " + name);
 *                 System.out.println(", Age: " + age);
 *             }
 *         } catch (SQLException se) {
 *             // Handle errors for JDBC
 *             se.printStackTrace();
 *         } catch (Exception e) {
 *             // Handle errors for Class.forName
 *             e.printStackTrace();
 *         } finally {
 *             // STEP 5: Close resources
 *             try {
 *                 if (rs != null) rs.close();
 *             } catch (SQLException se2) {
 *                 // Nothing to do
 *             }
 *             try {
 *                 if (stmt != null) stmt.close();
 *             } catch (SQLException se2) {
 *                 // Nothing to do
 *             }
 *             try {
 *                 if (conn != null) conn.close();
 *             } catch (SQLException se) {
 *                 se.printStackTrace();
 *             }
 *         }
 *         System.out.println("Goodbye!");
 *     }
 * }
 *
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  why orm over jdbc
 *  1. build connection (data source -> driver -> db)
 *      database 1 - 1 data source 1 - 1 entity manager factory 1 - m entity manager
 *  2. centralized query (hql/jpql) -> native query -> db
 *  3. cache (persistent context + first level + second level cache)
 *  4. focus on object / object mapping
 *  5. connection pool
 *  6. criteria query / dynamic query
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  how to create orm / use orm
 *  1. add dependencies : spring boot starter jpa
 *  2. create repository
 *      a. interface (no impl) : dynamic proxy
 *      b. interface + impl
 *          autowire entity manager
 *          entity manager -> send query to db
 *  3. use @Transactional in service
 *  4. entity classes
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Homework
 *      1. in-memory db (derby / h2)
 *      2. student m - m teacher (don't use @ManyToMany)
 *          student entity
 *          student_teacher entity
 *          teacher entity
 *      3. endpoints:
 *          student CRUD endpoints
 *          get all related teachers by student id endpoint
 *  Deadline 
 *
 *
 *
 */