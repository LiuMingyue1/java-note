package week4;

/**
 * Single Leader
 *      1 write node + N read nodes
 *      1 master node + N slave nodes
 *      1 leader node + N follower nodes
 *      1 primary node + N secondary nodes
 *
 *          leader  -- stand by
 *          |       \
 *     follower1    follower2
 *     ack number/ acknowledge number
 *
 *   rowinfo... table name ..
 *   old data: xxx-xxx-xxx
 *   new data: yyy-yyy-yyy
 *
 *
 * Multi Leader
 *      Leader1                  Leader2
 *     /    \                   /       \
 *  follower  follower      follower   follower
 *
 * All Leader = Leaderless  (consistent hashing)
 *
 *          Node1 0
 *
 *  Node4           Node2 10k
 *  40k
 *
 *         Node3 20k
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 * MongoDB Cluster (CP)
 *                         |
 *                      mongos  -   config (id-sharding mapping)
 *               /          |       \
 *          sharding1       2       3
 *          primary
 *          secondary
 *          secondary
 *
 *
 * Global Secondary Index
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 * CAP  (CP / AP)
 *      consistency
 *      availability
 *      partition tolerance
 * BASE
 *      basic availability
 *      soft stage
 *      eventually consistency
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Cassandra Node
 *
 *      -->  memtable (memory) --> SSTable (sorted string table / immutable)
 *          |
 *       commit log (disk)
 *
 *
 *       read -> blooming filter -> SSTable1, 2, 3, 4, 5 ....
 *
 *
 *  Cassandra Cluster
 *
 *          Node1 0
 *
 *  Node4           Node2 10k
 *  40k
 *
 *         Node3 20k
 *
 *    Replica Factor = 3
 *    Read Consistency = 2
 *    Write Consistency = 2
 *
 *    rc + wc > rf
 *
 *    example:
 *    read -> node1(redirect to other nodes) -> node2 + node3
 *                  read all data from node2
 *                  read hashing value from node3
 *                          if same hash value r
 *                              return to user
 *                          else
 *                              trigger read repair node2 + node3
 *
 *   write -> node1(redirect to other nodes) -> node2, 3, 4
 *                 send write request to all 3 nodes
 *                 if N of nodes reply success response
 *                      return success to user
 *                 else
 *                      return fail response
 */