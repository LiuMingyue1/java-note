package week2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  1. Spring IOC , AOP
 *  2. Spring Boot
 *      Spring MVC + Tomcat
 *      thread safety
 *  3. Rest api Design
 *
 *
 *  maven project
 *         Spring
 *      1. IOC: IOC container(Application Context)
 *          Dependency Injection
 *          1. @Component, @Service, @Repository, @Bean(Configuration), @Controller(Spring MVC)
 *          2. @Autowired
 *                  constructor injection
 *                  field injection
 *                  setter injection
 *          3. By Type
 *             By Name, @Qualifier
 *          4. Bean Scope: singleton, prototype, request, session, global session
 *      2. AOP
 *       @Aspect
 *       class MyAOP {
 *           @Before
 *           @PointCut(xxClass.*)
 *           public void printBefore1(JoinPoint) {
 *               ...
 *           }
 *
 *          @After
 *          public void printAfter1() {}
 *
 *          @Before
 *          public void printBefore2() {}
 *
 *          @After
 *  *       public void printAfter2() {}
 *       }
 *
 *       1. @Before ->
 *              BeforeAdvice {
 *                  invoke(mi) {
 *                      execute before logic
 *                      mi.proceed();
 *                  }
 *              }
 *          @After  ->
 *              AfterAdvice {
 *                  invoke(mi) {
 *                      Object retVal = mi.proceed();
 *                      execute after logic
 *                      return retVal
 *                  }
 *              }
 *      2. proxyInstance.method() -> trigger invoke() ->
 *              new ReflectiveMethodInvocation() {
 *                  List of Advices [Before1, After1, After2, Before2]
 *                                                                      i
 *                  proceed() {
 *                      if(i == len) {
 *  *                          execute the real logic
 *                              return
 *  *                   } else {
 *                      Before1.invoke(this) {
 *                              execute before logic // print before 1
 *                              this.proceed() {
 *                                  After1.invoke(this) {
 *                                      this.proceed() {
 *                                          After2.invoke(this) {
 *                                              this.proceed() {
 *                                                  Before2.invoke(this) {
 *                                                      execute before2 logic
 *                                                      this.proceed();
 *                                                  }
 *                                              }
 *
 *                                              execute after2 logic
 *                                          }
 *                                      }
 *
 *                                      execute after1 logic
 *                                  }
 *                              }
 *                          }
 *                      }
 *                  }
 *              }
 *              print before1 -> print before2 -> execute real logic -> print after2 -> print after1
 *
 *
 *      3. Spring MVC
 *      Server(Tomcat)
 *          single thread Socket
 *          while(true/..) {
 *              build connection
 *              assign connection to a thread (from thread pool)
 *                          thread start working on this connection request
 *                                  DispatcherServlet(front controller,  /*) -> handler Mapping -> Controller -> Service -> Repo -> DB
 *                                              |
 *                               @ResponseBody HttpMessageConverter(Jackson, jax-b) / view resolver + model and view(old version)
 *                                             |
 *                                           json / xml
 *                          thread send response through this connection
 *                          connection.close()
 *                          return thread back to thread pool
 *          }
 *          RestController = ResponseBody + Controller
 *
 *
 *      4. package[Spring + SpringMVC] -> war file -> upload it to tomcat
 *         package[SpringBoot + libraries + Tomcat] -> jar file
 *
 *      5. Spring Boot
 *          1. auto configuration
 *          2. application.properties
 *          3. main method -> starter
 *          4. embedded tomcat
 *          5. spring boot actuator
 *          ...
 *
 *
 *
 *
 *
 *
 *
 * new Service(MockRepositoryRef);
 */
@SpringBootApplication
class Day7SpringIOC {
    private static Day7StudentService s1;
    private static Day7StudentService s2;
    private static ExecutorService pool;

    @Autowired
    public Day7SpringIOC(
            @Qualifier("myFirstImpl") Day7StudentService s1,
            @Qualifier("myFirstImpl") Day7StudentService s2,
            ExecutorService myThreadPool ) {
        Day7SpringIOC.s1 = s1;
        Day7SpringIOC.s2 = s2;
        pool = myThreadPool;
    }

    public static void main(String[] args) {
        SpringApplication.run(Day7SpringIOC.class, args);
        System.out.println(s1 == s2);
    }
}

@Service
interface Day7StudentService {}
@Service("myFirstImpl")
@Scope("prototype")
class Day7StudentServiceImpl1 implements Day7StudentService{
    @Override
    public String toString() {
        return "Day7StudentServiceImpl1{}";
    }
}
@Service
class Day7StudentServiceImpl2 implements Day7StudentService{
    @Override
    public String toString() {
        return "Day7StudentServiceImpl2{}";
    }
}
@Configuration
class Day7Configuration {
    @Bean
    public ExecutorService myThreadPool() {
        return Executors.newCachedThreadPool();
    }
}