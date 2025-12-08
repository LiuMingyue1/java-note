package week4;

/**
 * hibernate + Spring data jpa
 *
 * jpa
 *      1. entity manager factory
 *      2. entity manager
 *      3. persist
 *              insert new entry
 *      4. merge
 *              update if exist in db
 *              or
 *              insert new entry
 *      5. JPQL
 *              SELECT c FROM Customer c
 * hibernate
 *      1. session factory
 *      2. session
 *      3. saveOrUpdate..
 *      4. HQL
 *  *  *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  *
 *  With JPA only
 *  class CustomerRepoImpl implements CustomerRepo{
 *      @PersistentContext
 *      private EntityManager em;
 *
 *      @Override
 *      public void getAllCustomer() {
 *          em.createQuery("SELECT c FROM Customer c", Customer.class);
 *          em.createNativeQuery("select * from customer_table");
 *          ...
 *      }
 *  }
 *
 *  With Spring data jpa
 *  @Respository
 *  interface CustomerRepo extends JpaRepository<Customer, String> {
 *
 *      @Query("")
 *      List<XX> getXX();
 *
 *      List<XX> findXXByName(String name);
 *  }
 *   *  *  *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  *
 *   Entity annotations
 *   1. @Entity
 *   2. @Table("..")
 *   3. @Column
 *   4. @OneToMany(fetchType=Eager/Lazy) + @ManyToOne
 *      @JoinColumn
 *
 *  A 1 - m B
 *  class A {
 *      @OneToMany(mappedBy="a_ref")
 *      private List<B> bList;
 *  }
 *
 *  class B {
 *      @ManyToOne
 *      @JoinColumn("..")
 *      private A a_ref;
 *  }
 *
 *      Lazy vs Eager
 *      1. Eager Loading
 *          em.createQuery("select a from A a join a.bList")
 *      2. Lazy Loading
 *          List<A> aList = (List)em.createQuery("select a from A a fetch join a.bList");
 *          for(A a: aList) {
 *              List<B> bList = a.bList();
 *              bList.size() / get(index)... //trigger sql query
 *          }
 *
 *          N + 1 query
 *          LazyInitializationException
 *  *   *  *  *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  *
 *  Criteria Query = Builder Pattern = Dynamic Query Builder
 *
 * EntityManager em = // ... obtain EntityManager
 * CriteriaBuilder cb = em.getCriteriaBuilder();
 * CriteriaQuery<Person> cq = cb.createQuery(Person.class);
 * Root<Person> personRoot = cq.from(Person.class);
 *
 * // Condition: name equals "John"
 * Predicate nameEqualsJohn = cb.equal(personRoot.get("name"), "John");
 *
 * // Condition: age is greater than 30
 * Predicate ageGreaterThan30 = cb.greaterThan(personRoot.get("age"), 30);
 *
 * // Combine conditions with AND: name equals "John" AND age is greater than 30
 * Predicate finalPredicate = cb.and(nameEqualsJohn, ageGreaterThan30);
 *
 * // Apply the WHERE clause
 * cq.where(finalPredicate);
 *
 * // Select the root entity
 * cq.select(personRoot);
 *
 * // Execute the query
 * List<Person> people = em.createQuery(cq).getResultList();
 */