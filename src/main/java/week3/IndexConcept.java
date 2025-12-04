/**
 * non clustered index
 * B+ tree
 *               [30 ,   50]
 *            /     \         \
 *      [10, 20] <-> [30, 40] <-> [50, 60]
 *     rowid
 *       |
 *       |
 *       ------------------------ rowid  id name
 *                                   xx, 1, 'Tom'
 *
 * Bitmap index
 *
 *     id,  state  rowid,  rowid, NJ, NY
 *     1 ,  NJ      xx      xx    1,  0
 *     2 ,  NY      xy      xy    0,  1
 *     3 ,  NJ      ZZ      ZZ    1,  0
 *
 *    NJ 101
 *    NY 010
 *
 *    select in NJ or NY
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   clustered index
 * B+ tree + table
 *               [30 ,   50]
 *            /     \         \
 *      [10, 20] <-> [30, 40] <-> [50, 60]
 *      xx
 *      1
 *    'Tom'
 *
 *
 *   *********************
 *  Full table scan
 *  Index access scan
 *      1. index unique scan
 *      2. index range scan
 *      3. index full scan
 *      4. index fast full scan
 *  table join
 *      1. nested loop join
 *          for(int i = 0; i < n; i++) {
 *              for(int j = 0; j < m; j++) {
 *                  if(arr1[i] == arr2[j]) {
 *                      select this row
 *                  }
 *              }
 *          }
 *      2. hash join
 *          [hash(id1) == hash(id5)][][][][][][][][hash(id2)][][][]
 *          1. find bucket
 *          2. join in each bucket
 *      3. merge sort join
 *          1. sort arr1, sort arr2
 *          2. two pointer to merge / join
 *  Hint
 *      1. full
 *      2. index
 *      3. parallel
 *      4. use_nl, use_hash, use_merge
 *      5. leading
 *
 * How to tune db performance/query performance
 * 1. query tuning + index
 *       execution plan + statistics
 *       1. check index usage / type
 *       2. add query hint
 *              index access path
 *              join strategies
 *              full table scan
 *              ..
 *    material view
 *    de-normalization
 * 2. vertical scaling
 * 3. horizontal scaling : add more read db
 *      write db + multiple read db
 * 3. add cache
 *      global cache (redis)
 *      local cache(spring boot result cache)
 *      load balancer cache
 *      ...
 * 4. sharding / partition
 */

//select /*+ full(e) parallel(10) */ * from hr.employees e where employee_id > 200