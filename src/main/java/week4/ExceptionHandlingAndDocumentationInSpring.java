package week4;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *  1. try catch
 *  2. throws
 *  3. @ExceptionHandler
 *  4. @ControllerAdvice + @ExceptionHandler
 *      @RestControllerAdvice
 *      class xx implements RestResponse...  {
 *          @ExceptionHandler(IllegalArgumentException.class)
 *          public ResponseEntity<?> hanldeClientException() {
 *              //throw internal error / 400
 *          }
 *          @ExceptionHandler(Exception.class)
 *          public ResponseEntity<?> hanldeInteralException() {
 *              //throw internal error / 500
 *          }
 *      }
 *  5. logging
 *  tomcat server1(universal forwarder)  ->
 *  tomcat server2(universal forwarder)  ->      Splunk(centralized log server)
 *  tomcat server3(universal forwarder)  ->
 */

class XXController {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> hanldeException() {
        return null;
    }
}

/**
 * design rest api
 * 1. open api
 *      openapi: 3.0.0
 * info:
 *   title: User Management API
 *   version: 1.0.0
 *   description: A simple API for managing users
 * servers:
 *   - url: https://api.example.com/v1
 *     description: Production server
 *   - url: http://localhost:8080/v1
 *     description: Local development server
 * paths:
 *   /users:
 *     get:
 *       summary: Get a list of users
 *       operationId: listUsers
 *       responses:
 *         '200':
 *           description: A list of users
 *           content:
 *             application/json:
 *               schema:
 *                 type: array
 *                 items:
 *                   $ref: '#/components/schemas/User'
 *         '400':
 *           description: Invalid request
 *     post:
 *       summary: Create a new user
 *       operationId: createUser
 *       requestBody:
 *         required: true
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/NewUser'
 *       responses:
 *         '201':
 *           description: User created successfully
 *           content:
 *             application/json:
 *               schema:
 *                 $ref: '#/components/schemas/User'
 *         '400':
 *           description: Invalid user data
 *   /users/{userId}:
 *     get:
 *       summary: Get user by ID
 *       operationId: getUserById
 *       parameters:
 *         - name: userId
 *           in: path
 *           required: true
 *           description: ID of the user to retrieve
 *           schema:
 *             type: string
 *             format: uuid
 *       responses:
 *         '200':
 *           description: User details
 *           content:
 *             application/json:
 *               schema:
 *                 $ref: '#/components/schemas/User'
 *         '404':
 *           description: User not found
 * components:
 *   schemas:
 *     User:
 *       type: object
 *       properties:
 *         id:
 *           type: string
 *           format: uuid
 *           description: Unique identifier for the user
 *         name:
 *           type: string
 *           description: Name of the user
 *         email:
 *           type: string
 *           format: email
 *           description: Email address of the user
 *       required:
 *         - id
 *         - name
 *         - email
 *     NewUser:
 *       type: object
 *       properties:
 *         name:
 *           type: string
 *           description: Name of the new user
 *         email:
 *           type: string
 *           format: email
 *           description: Email address of the new user
 *       required:
 *         - name
 *         - email
 */

