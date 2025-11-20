package week2.day8;

/**
 * rest api
 *  1. endpoint :
 *      /student + get  get all student
 *      /student + post  create student
 *      /student/{id} + get  get student by id
 *  2. based http
 *  3. json , xml
 *  4. stateless
 *      /student?pageNum=1&pageSize=50

 * Old Spring impl: Stateful / Session
 *
 * browser  -> tomcat  server
 *             check if you have sessionId?
 *         <- generate session id
 * browser save session id in cookie
 *        -> bring cookie with request (in header)
 *           tomcat check stored data by sessionId
 *
 *
 *               LoadBalancer(ip)
 *              /    |          \
 *       Node1      Node2       Node3
 *           \         |        /
 *               Redis cache / DB
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Anagram -> input 2 string, check if they have same characters
 *  1. string1 null or string2 null ? throw exception? , null == null?  null == ""?
 *  2. A == a ?
 *  3. special characters? skip? count?
 *  4. convert to char array + sorting ?
 *     hashmap count frequency
 *     int[] 26
 *     int[] 128
 *     int[] 256
 *  5. create 2 hashmap ? create one hashmap
 *  6. use example "abc", "cba"
 *     i = 0
 *     map1 : [{a=1}]
 *     map2 : [{c=1}]
 *     i = 1
 *     ...

 *  public void solution() {
 *      if() //..
 *
 *      for(int i = 0; i < len; i++) {
 *      }
 *  }
 *
 *  Question: design student rest api
 *  1. CRUD ? get / post / delete
 *  2. Table?
 *      Student(id, name)?
 *  3. get all students ? get student by id
 *
 *  get all student
 *  endpoint: /student?age=?&pageNum..
 *  http method: get
 *  response body :
 *  [
 *      {"id": xx, "name":..}, {stu2}
 *  ]
 *  http status code: 200, 400, 500
 *
 *  create student
 *  endpoint: /student
 *  http method: post
 *  request body:
 *      {
 *          "name":
 *      }
 *  response body
 *      {
 *          "id": relocation id
 *      }
 *  http status code: 201(created), 400, 500,
 *
 *  get student by id
 *  endpoint:  /student/{id}
 *  http method: get
 *  response body {stu1}
 *  http status code: 200, 400, 404, 500
 *
 *  update student by id
 *  endpoint:  /student/{id}
 *  http method: put / patch
 *  request body:
 *      {
 *          "name": .
 *      }
 *  response body : ?
 *  http status code : 200/204, 400, 404, 500..
 *
 *  delete...
 *  endpoint:
 *  http method:
 *  response body
 *  http status code
 */

