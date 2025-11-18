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
 *      1. endpoint, api, uri, url: /student
 *      2. status code: response status
 *              2xx : success response
 *              3xx : redirect response
 *              4xx : client error
 *              5xx : internal server error
 *      3. header
 *              Content-Type: ..
 *              Accept
 *              Authorization: security token
 *              Allow-Method/ Allow-Origin / Allow-Header: CORS..
 *      4. request body
 *              {
 *                  "name": "Tom"
 *              }
 *      5. response body
 *              data response
 *              error response
 *      6. method
 *              get: read data
 *              post: create data
 *              put: update data (whole resource)
 *              patch: update partial data
 *              delete: delete data
 *              head: success but not response body
 *              ..
 *      7. ...
 *
 *  https = http + ssl / tls
 *
 *  CA = certificate authority
 *  Asymmetric key = Public key + Private key
 *  Symmetric key = one key for both encryption and decryption
 *
 *  one way ssl
 *  client     ->  hello ->  server(hold private key)
 *     <- certificate(contains public key) <-
 *        use root CA to verify it
 *        public key[random string] ->
 *            <- hash[random string]
 *             generate symmetric key
 *            ->  symmetric key[data] ->
 *            <-  symmetric key[data] <-
 *
 *
 *  two way ssl / two way tls / mTLS
 *  1. use library / 3rd party company to get root CA
 *  2. generate certificate using root CA
 *  3. send / share certificate with other server / user
 *  4. user -> bring certificate with request -> your server will verify it ..
 *
 *
 *  Oauth2.0
 *  browser -> 3rd party login
 *     |
 *   app
 *
 *   1. 3rd party login  -> redirect to url?access_code=xxx
 *   2. browser send code to app
 *   3. app -> verify code with 3rd party login
 *
 */