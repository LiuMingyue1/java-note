package week2;

/**
 * IPV4  0.0.0.0 ~ 256.256.256.256
 * public ip :  public network
 * private ip :  inner network (like extension number)
 *
 * home(wifi)
 * DH..(ip exchange device)
 *   | get private ip
 * Laptop(network card: mac address) <-> NAT(network address transfer)  <->      website server
 *                                   private ip port + public ip port            destination ip + port
 *
 *
 * Connection
 * source ip, source port, destination ip, destination port
 *
 * Browser
 *     1. input endpoint -> click enter
 *          https://www.xx.com/student
 *     2. look up DNS server ->
 *          www.xx.com and ip mapping
 *     3. browser open random port -> send request to https://156.25.25.25/student
 *     4. request goes through NAT -> get public ip -> visit public internet / network
 *     5. build tcp connection
 *              [ip header][tcp header] ->  server [dest ip + port]
 *                              3 ways handshake to build connection
 *        send data to server
 *              [ip header][tcp header][http header][data1] -> server
 *                                                  <-  accepted
 *              [ip header][tcp header][data2] ->  server
 *                                                  <- accepted
 *
 *        server process your request
 *        server send response back to user / client
 *
 * Server(Tomcat)
 *          single thread Socket
 *          while(true/..) {
 *              build connection
 *              assign connection to a thread (from thread pool)
 *                          thread start working on this connection request
 *                          thread send response through this connection
 *                          connection.close()
 *                          return thread back to thread pool
 *          }
 *
 *  ASCII standard table 0 ~ 127 characters
 *  Encode -> increase the data size
 *  Decode -

 *
 * Network / server concept
 *      OSI model
 *      Physical layer 1   :   cable
 *      Data link layer 2  :   ethernet layer
 *      Network layer 3    :   ip header
 *      Transport layer 4  :   tcp header (port, connection status, ack, seq..)
 *              tcp :
 *              udp
 *      Session layer 5    :   socket
 *      Presentation(?) layer 6 :  SSL, TLS
 *      Application layer 7 :  HTTP, Websocket...
 *
 */

/**
 *  http
 *  https = http + ssl / tls
 */