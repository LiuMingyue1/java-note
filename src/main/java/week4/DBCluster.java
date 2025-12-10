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
 *  Cassandra
 *
 *  Message queue
 */