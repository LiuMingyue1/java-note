/**
 * -- employee
 *
 * -- user 1 ->           insert / update / delete
 *
 * -- timeline ---------------------------------------------------
 * --             |                                          |
 * -- user 2 -> select                                     select
 *
 * Transaction
 *      begin tx
 *          select
 *          insert 100 rows
 *          update
 *      commit tx
 *
 *      Atomicity
 *      Consistency
 *      Isolation level
 *          Read Uncommitted
 *          Read Committed
 *          Repeatable Read
 *          Serializable
 *      Durability
 *
 * Read Uncommitted (dirty read)
 * user1     begin tx1      select1       !=      select2
 *              |           |                       |
 * timeline ------------------------------------------------>
 *              |                       |
 * user2      begin tx2       insert / update /delete
 *
 *
 * Read Committed (non-repeatable, phantom read)
 * user1     begin tx1      select1       ==      select2       !=        select3
 *              |           |                       |                       |
 * timeline -------------------------------------------------------------------------------->
 *              |                       |                           |
 * user2      begin tx2       insert / update /delete           commit
 *
 *
 * Repeatable Read (phantom read)
 * user1     begin tx1      select1       ==      select2       ==        select3
 *              |            |                       |                       |
 * timeline -------------------------------------------------------------------------------->
 *              |                       |                           |
 * user2      begin tx2       insert / update /delete              commit
 *
 *
 * Serializable
 * user1     begin tx1      select1       ==      select2       ==        select3
 *              |            |                       |                       |
 * timeline -------------------------------------------------------------------------------->
 *              |                       |                           |
 * user2      begin tx2       insert / update /delete              commit
 *
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   MVCC = multi version concurrency control
 *
 *   emp table
 *   emp_id, emp_name, row_id, tx_id, rollback_pointer
 *      1 ,  'Tommy'    xx      2
 *                                          |
 *                                          1  , 'Tom'     yy        1
 *
 *
 *   select = generate read_view(committed tx id)
 *  *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  read lock(share lock) -> block write lock
 *  write lock(exclusive lock) -> block read + write lock
 *
 *  add read lock to query:
 *      1. select ... for share
 *      2. Serializable
 *  add write lock :
 *      1. insert, update, delete (default write lock)
 *      2. select.... for update
 *
 *  add gap lock:
 *      1. repeatable read / serializable
 *
 *   emp table
 *   id
 *   1
 *   [2, 3) gap lock
 *   3 record lock
 *   (3, 5) gap lock
 *   5 record lock
 *   (5, 10) gap lock
 *   10 record lock
 *   (10, infinite) gap lock
 *
 *   select .. from emp where id >= 2 for update
 *
 *   add optimistic lock
 *      user1           user2
 *      read a = 1     read a = 1
 *           v = 1          v = 1
 *      a++             a++
 *      update a = 2
 *      update v = 2
 *      where v = 1
 *                       update a = 2
 *                       update v = 2
 *                       where v = 1
 *                       get error because v = 2
 *
 *                       read a = 2 , v = 2
 *                       a++
 *                       update a = 3, v = 3 where v = 2
 *
 *     1. add version column
 *
 */