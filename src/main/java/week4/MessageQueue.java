/**
 *
 *  server(producer)  -   message queue(server)  -  server(consumer)
 *
 *  producer
 *  1. send request to producer
 *  2. producer -> send message to message queue
 *  3. producer commit tx
 *  4. producer send response
 *
 *  consumer
 *  for(;;) {
 *      pull messages  from queue
 *      execute message
 *  }
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Queue Model
 *  Publisher Subscriber Model
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  SQS: Queue Model
 *
 *     -> [][][][][][][][][][][m1][m2] -> consumer
 *
 *     1. visibility time out
 *     2. FIFO : first in first out / order
 *     3. standard : no order
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  SNS: Publisher Subscriber
 *  SNS - SQS1 - consumer1
 *        SQS2 - consumer2
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Kafka
 *              Topic1                  Consumer Group1
 *             partition1               Consumer1 [partition1][partition2]
 *             partition2               Consumer2 [partition3]
 *             partition3
 *                                      Consumer Group2
 *                                      Consumer3 [partition1][partition2][partition3]
 *
 *  Queue model: 1 consumer group
 *  Publisher Sub model: > 1 consumer group
 *
 *  1. Kafka doesn't maintain order cross diff partitions
 *  2. to keep order of messages, set "Key" in each message -> messages with same key are pushed to same partition
 *
 *  Kafka Cluster
 *                  Broker1(server)
 *                  T1p1(leader partition)
 *                  T1p2(follower partition)
 *
 *                  Broker2(server)
 *                  T1p1(follower partition)
 *                  T1p2(leader partition)
 *
 *
 * 1. why kafka vs sqs / or rabbit mq
 * 2. what is topic
 * 3. what is broker
 * 4. what is partition
 * 5. how does kafka re-balance work
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Unique message / avoid duplicate messages
 *  1. unique message id (same message id for duplicate message)
 *  2. idempotent(put, get, patch, delete) service
 *         when status = pending
 *         then change pending to complete
 *  3. SNS deduplicate
 *  4. cache: save processed message id
 */