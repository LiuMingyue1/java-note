package week4;

/**
 * 1. diff monolithic and microservice
 * 2. microservice design patterns
 * 3. why microservice
 * 4. why api gateway / circuit breaker / message queue / other components.....
 * * * * * * * * * * * * * * * * * * * *
 * Monolithic Application
 *      1. single repository
 *      2. deployment
 *      3. development
 *      4. fault tolerance
 *      5. scalability :    Vertical + Horizontal Scaling
 *      ...
 *
 *
 * Mico-service
 *                          api gateway  -  security service
 *                            |
 *                           service
 *                       /          \
 *      shipping service        order service       payment service
 *      node1, node2            node3, node4        ..
 *      node6
 *              |                       |
 *            DB1                     DB2
 *
 *
 *      1. Api Gateway
 *          a. logging
 *          b. co-relation id / request id
 *                   UUID
 *                   DB -> primary key
 *                   Snowflake : long id [42bit timestamp][3bit machine id][process id][thread id][serial id]..
 *          c. centralized entry point / config CORS
 *      2. security service / security provider
 *      3. circuit breaker : resilience 4j  / spring cloud hystrix
 *          a. open : request can visit 3rd party api
 *                    example: 3 requests fail out of 5 requests
 *          b. close : service A return default result
 *                     background thread -> keeps checking 3rd party api status
 *          c. half open : allow 30%, 40% request visit 3rd party api
 *          service A -> 3rd api
 *      4. discovery service / service registration / service discovery center (inner network DNS) : spring cloud eureka / aws cloudmap
 *          service A -> service B
 *                  \      /
 *                 discovery service {"service-A": [ip1, ip2]},  {"service-B": [ip3, ip4, ip5]}
 *
 *          restTemplateProxy.getForObject("https://service-B/uri", X.class);
 *          1. service A query discovery service (find ip by "service-B" service name)
 *          2. get ip3, ip4, ip5 -> pick one (spring cloud ribbon client side load balancing)
 *          3. restTemplate.getForObject("https://ip5/uri", X.class);
 *      5. public subnet, private subnet VPC
 *      6. cache
 *          a. CDN
 *          b. Global Cache
 *          c. Local Cache
 *      7. Message queue
 *      8. DB
 *          a. nosql
 *          b. rdbms
 *          c. elastic search
 *          e. object storage
 *          f. file system
 *      9. serverless in AWS
 *      10. deployment / CI,CD / docker
 *      ...
 *
 *
 *
 */