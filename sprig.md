Let's get started what is spring framework? 
The spring framework is an open-source framework for building Enterprise Java applications.
Spring aims to simplify the complex and cumbersome Enterprise Java application development process by offering in a framework that includes technologies such as:
aspect oriented programming dependency injection, plain old Java objects and so and so forth even with all these technologies spring is a lightweight framework that can be used to create scalable secure and robust Enterprise applications at a micro level we can consider this spring framework a collection of sub Frameworks such as spring web flow, spring MVC, spring orm and so and so forth.

## Core features of spring framework

### Inversion Of Control Container IOC
Is one of the core features of spring that provides a streamlined way to configure and manage Java objects this container is responsible for managing the life cycle of a defined Java object significantly increasing the configurability of a spring based application.
Ioc use dependency injection or dependency look up patterns to provide the object reference during runtime.

### Aspect Oriented Programming AOP
Aims to provide more modularity to the cross cutting concerns which are functions that span across the application such as:
- logging
- caching
- transaction management
- authentication
- etc...

### Data Access Frameworl DAF
Spring simplifies the database communication process by providing direct support for popular data access framework in Java such as:
- JDBC
- Hibernate
- Java Persistence API (JPA)
Additionally it offers features such as Resource Management exception handling and resource wrapping for all the supported data access frameworks further simplifying the development process.

### Spring MVC Framework MVC
Spring MVC enables developers to create applications using the popular MVC pattern. It is a request based framework that allows developers to easily create customized MVC implementations that exactly suits their needs.
The core components of the spring MVC is the dispatcher servlet class which handles user requests and then forward them to the correct controller this allows the controller to process the request create the model and then provide the information for the end user via a specified View.

### Spring Bean
Spring Bean refers to an object that is managed by the spring framework in a Java application.

The term Bean is used in the context of the spring framework.
The spring framework creates these beans manage their life cycle and organizes their dependencies with other beans. It takes care of the instantiation, configuration and wiring up of objects saving developers from a lot of manual work.

The spring beans can be configured using XML Java annotations or Java code.

### Life cycle of a Spring Bean
First let's understand the life cycle of an object.
The life cycle of an object means:
> the bean life cycle refers to when and how the bean is instantiated what action it performs until it leaves and when and how it is destroyed.

The bean life cycle is managed by the spring container when we run the program, the first of all the spring container gets started after that the container creates the instance of a bean as for the request and then dependencies are injected and finally the bean is destroyed when the spring container is closed

### How to configure Bean
We can use:
- @Configuration (annotation) declares a class as a full configuration class and here you need to note that a  ***class must be non final and public*** .
- @Bean (annotation) declares a bean configuration inside of a configuration class and the  ***method must be non final and non private, it can be public, protected or package-private.*** 

Here is an example:
    ```
    @Configuration
    public class AppConfig {
      @Bean
      public PaymentService paymentService(
          AccountRepository accountRepository
      ) {
          return new PaymentServiceImpl(accountRepository)    
      }
    }
    ```

We have the AppConfig class annotated with the configuration annotation and inside it we have a payment service which has the bean annotation so this will return a new payment service implementation and we can pass any other dependency.
In this case we have the accountRepository as a dependency for the payment service implementation.

Also let's see a full example where we can declare multiple beans:
    ```
    
    @Configuration
    public class AppConfig {
      @Bean
      public PaymentService paymentService() {
        return new PaymentServiceImpl(accountRepository())    
      }
    
      @Bean
      public AccountRepository accountRepository() {
          return new JdbcAccountRepository(dataSource());    
      }
    
      @Bean
      public DataSource dataSource() {
          return (mysqlConnector())
      }
    }
    ```

We have the AppConfig class annotated with the configuration annotation and inside that we have:
- PaymentService Bean
- AccountRepository Bean
- DataSource Bean

We have in the middle the account repository which is also a bean that takes a parameter or as a parameter the data source Bean that we already created and from the top we have the bean payment service that takes the account repository as a dependency and spring will manage all that.

So spring will know that we need to create a data source first and then pass it to the account Repository and then we create the account repository bean and pass it as an injectable Bean to the payment Service

### Spring Component Sample

Spring Component contains class-level annotation that marks class as a Spring Component (@Component).
Constructor-dependency injection is automatically done using @Autowired by injecting the constructor parameter(s).

@Autowired on Constructor is optional if there is only one constructor.

    ```
    @Component
    public class PaymentServiceImpl {
      private final AccountRepository accountRepository;
      
      @Autowired
      public PaymentServiceImpl(
          AccountRepository accountRepository
      ) {
          this.accountRepository = accountRepository;
      }
    }
    ```


We see an example, we have our PaymentServiceImp class which is marked as a component.
1) This means that we want to mark this class as a spring bean.
2) Then we have a private final AccountRepository which is refers to another spring bean.
3) We have a constructor of PaymentServiceImpl with the annotation @Autowired.
4) We have or we pass a parameter of type AccountRepository and then we assign the local or the class instance AccountRepository to the account repository that we get as a parameter from the constructor.
5) We are injecting via the constructor the AccountRepository bean into the variable or the local variable accountRepository.

Spring provides component stereotype to classify classes as spring components.
Sub-Types are available as a refinement for the standard components.
@Component as a general component annotation indicating that the class should be initialized configured and managed by the core container.
@Repository, @Service, @Controller as meta-annotation for @Component that allows further re-fine components.
Own stereotype annotations can (and should) be defined to support general architecture principles.

### Bean Naming

We have this configuration class that contains the three beans that we explained already.
Before PaymentService and AccountRepository and DataSource we provided a name to DataSource like "ds":

    ```
    @Configuration
    public class AppConfig {
        @Bean
        public PaymentService paymentService() {
            return new PaymentServiceImpl(accountRepository())    
        }
    
        @Bean
        public AccountRepository accountRepository() {
            return new JdbcAccountRepository(dataSource());    
        }
        
        @Bean("ds")
        public DataSource dataSource() {
            return (mysqlConnector())
        }
    }
    ```

So now let's see how spring is going to name these bean.

First when we talk about the first Bean PaymentService which returns a payment service object, this will give it a name or a bean name PaymentService as for the method name.

The same for the AccountRepository if we don't provide a name for the bean,  ***spring automatically will use the method name as a bean name***  so the bean of type type AccountRepository will be named AccountRepository.

Finally for the data source when we provide the nam, spring will take that one as a name for the bean so the DataSource bean name will be DS.

Bean naming is really important in case we want to fetch or to get programmatically any bean from our application context so now we know how spring names the  ***beans injection.*** 

### Dependency Injection
When we talk about  ***beans injection***  we also mean  ***dependency injection***  so the spring framework provides  ***four ways to inject beans***  so the spring can configure dependencies on different injection elements.

The four elements for dependency injection:
 - ***Constructor Injection***  constructor parameter receive dependencies during bean construction.
 - ***Field Injection***  field definition to receive dependency, injected with the reflection axis.
 - ***Configuration Methods***  one or many parameters receiving dependencies through method parameters.
 - ***Setter Injection***  Java Setter method are specialized configuration method with only one parameter and a defined name scheme called also setter injection.

The injection target can be referred using two different modes,  ***type injection***  injects an object of matching type or  ***name injection***  injects any object by name constructor injection.

### Constructor Injection
Let's first see the example of the case of a service, we have here a class called DefaultPaymentService that contains a final account AccountRepository and then we have the constructor DefaultPaymentService.
In the DefaultPaymentService constructor we are declaring also an object or we passing a parameter of type AccountRepository and like that  ***spring will be automatically injecting***  this AccountRepository bean.

    ```
    @Service
    public class DefaultPaymentService {
      private final AccountRepository accountRepository;
      
      public DefaultPaymentService(AccountRepository accountRepository) {
          this.accountRepository = accountRepository;    
      }
    }
    ```


In case we have a repository we see that we have a JdbcAccountRepository that implements AccountRepository so we have a final or private final DataSource and then we have the constructor JdbcAccountRepository and we pass also a parameter which is the data source and then spring will automatically inject that using the constructor.

    ```
    @Repository
    public class JdbcAccountRepository implements AccountRepository {
      private final DataSource dataSource;
      
      public JdbcAccountRepository(DataSource dataSource) {
          this.dataSource = dataSource;    
      }
    }
    ```

### Annotation Qualifer
For injecting beans we can specify or we can tell spring which bean to inject and for this one we can use the the annotation called qualifier.

let's see an example so here we have a class called application config annotated with a configuration and inside that we have two beans of the same type. The two beans are of type account repository and we have a primary and a secondary so this means that we will create two beans the first one will be called primary and the second one will be called secondary but these two beans are referred by type which is the account repository so here we can give a qualifier for each bean.

    ```
    @Configuration
    public class ApplicationConfig {
      @Bean
      @Qualifier("primary")
      public AccountRepository primary(){
          return new JdbcAccountRepository(...)    
      }
      
      @Bean
      @Qualifier("secondary")
      public AccountRepository secondary(){
          return new JdbcAccountRepository(...)    
      }
    }
    ```

The first one we give it a qualifier primary and the second one we will give it a qualifier secondary.

Let's say we have the default payment service and we want to autowire or to inject a bean of type AccountRepository.

    ```
    @Service
    public class DefaultPaymentService {
        @Autowired
        public DefaultPaymentService(
            @Qualifier("primary") AccountRepository accountRepository
        ) {
            this.accountRepository = accountRepository;    
        }
    }
    ```
Here we want to choose or we can choose which bean to inject.
We can use again the qualifier annotation on the service or the object level and as we can see in the DefaultPaymentService constructor.
We are telling spring that we want to inject the bean that has the name or the qualifier called primary. This is how we can use the qualifier annotation to inject the bean.

### Annotation Primary
If we want to to define a bean as primary, we can use the primary annotation to define which bean should be primary or should be prior or prioritized for spring  to be injected.

    ```
    @Configuration
    public class ApplicationConfig {
        @Bean
        @Primary
        public AccountRepository primary(){
            return new JdbcAccountRepository(...)    
        }
        
        @Bean
        public AccountRepository secondary(){
            return new JdbcAccountRepository(...)    
        }
    }
    ```

In this case we don't  need the qualifier annotation, assuming again that we have the ApplicationConfig class and then we have the same two beans but now instead of giving a qualifier annotation we can choose which is the primary, for example we have the AccountRepository primary and then we give it the primary annotation.
Then when we want to inject a bean of type AccountRepository all we need to do is just to inject it using the constructor or the field and so on.

    ```
    @Service
    public class DefaultPaymentService {
        @Autowired
        public DefaultPaymentService(AccountRepository accountRepository) {
            this.accountRepository = accountRepository;    
        }
    }
    ```

Spring will know automatically that the AccountRepository bean which is called primary is prioritized bean.
It will inject that one  ***unless we want to change***  we can give a qualifier and then when we use the qualifier spring will use that one over the primary field injection.


### Field Injection
Field injection allows direct injection into field declaration without constructor or method delegation.
But here you need to note that this type of injection discouraged because it makes testing of component in isolation more complex therefore should only be used in test classes.

Here is an example:

    ```
    @Service
    public class DefaultPaymentService {
        @Autowired
        private AccountRepository accountRepository;
    }
    ```
We have the DefaultPaymentService which is a class annotated with service annotation.
We have a variable or a field of type AccountRepository.
When using the autowired annotation this means that we want to inject this AccountRepository using  ***field injection method.*** 

### Method Injection
Method injection allows setting one or many dependencies by one method and it also allows for initializing work if needed while receiving dependencies.
An example:

    ```
    @Service
    public class DefaultPaymentService {
        @Autowired
        public void configureClass(
            AccountRepository accountRepository,
            FeeCalculator feeCalculator
        ) {
            // ....    
        }
    }
    ```
We have our DefaultPaymentService annotated with a service annotation. Then we have a method called configureClass which is of type void and then we have two variables or two feeds which is AccountRepository and feeCalculator.
For example and assuming these two classes or these two objects are beans all we need to do is to set the autowire annotation on the method level and then spring will understand automatically.

### Setter Injection
Setter injection follows Java bean naming convention to inject dependencies.
Here an example:

    ```
    @Service
    public class DefaultPaymentService {
        @Autowired
        public void setAccountRepository(
            AccountRepository accountRepository
        ) {
            // ...    
        }
    }
    ```
Assuming that we have a class called DefaultPaymentService annotated with the service. Then all we need to do is to declare our field and then use the setter method to inject this bean.

Here we see that we have a public void setAccountRepository and it takes as a parameter AccountRepository. It's just a classic set method annotated with at autowired annotation, spring will understand that this is a setter injection and it will inject the field using the setter method.

Since you can mix  ***constructor-based***  and  ***setter-based***  dependency injection it's good rule to use constructor for mandatory dependencies and setter for method or configuration method for optional dependencies

Spring team generally advocates constructor injection so it's always recommended and it's always better to use constructor injection to inject your beans into a class bean.

### Bean Scoping
scoping so first let's understand what is a beans scope so beans scope in Spring framework.

Refers to the life cycle of a spring bean and its availability in the context of the application. When a bean is instantiated or looked up its scopes determines its life cycle and which other beans can interact with it.
Spring provides multiple scopes to register and configure beans and scoping has an impact on state management of the components.

- Singleton one instance per application context, must be thread safe, is the default bean scope in Spring, only one instance of the bean Is created and all requests of that bean will receive the same instance, this is useful for bean that do not hold state or where the same state is to be shared by all users or threads.

- Prototype a new instance is created each time a bean is requested, this is useful for beans to carry state that is specific to other user or thread and thus can't be shared.

- Request scope is only valid in the context of web over spring application context for a single HTTP request, a new bean is created for each HTTP request.

- Session is for HTTP session so this means a new bean is created for each HTTP session by the container.

- Application this scope is valid only in the context of webware spring application context for the life cycle of a servlet context so this bean is scoped at the application level.

- Websocket this scope is valid only in the context of webware spring application context for the life cycle of a websocket, the bean Is scoped at the websocket level so bean scoping is really important if you want to correctly manage your bean.

### Define A Scope
We can specify it by name so for example:

    ```
    @Configuration
    public class MyConfiguration {
        @Bean
        @Scope("prototype")
        public PropBean propBean() { //... }
        // or by
        @Bean
        @SessionScope
        public SessionBean sessionBean() { //... }
    }
    ```


### Spring Framework Special Spring Beans
Let's talk a little bit about some  ***special spring beans, *** first of all we have the bean environment.
Spring provides an environment abstraction to the couple application code from the environment with a support for bean definition profiles that allow different sets of beans depending on the environment

For example we have:
- local environment
- dev environment
- cloud
- prod

Helps resolving properties for external sources for database settings from the configuration file or reading credentials from CLI arguments and so forth. Environment can also be injected into the code if needed. Let's see an example:

    ```
    @Configuration
    public class ApplicationConfig{
        @Autowired final Environment environment;
        
        @Bean
        public PaymentService paymentService() {
            var profile = Profiles.of("cloud");
            var isOkay = this.environment.acceptsProfiles(profile);
            this.environment.getProperty("data.driver");    
        }
    }
    ```


Then we have the  ***bean profiles***  so a profile in Spring is a named logical grouping that may be activated programmatically or set as active through configuration so this feature is particularly useful when you have beans that should be active or registered and used in certain environments or conditions.
For instance you may have different configuration for development testing and production environment and you want to make sure that certain beans are only used in one of these environments this is how we can use this profile Bean or the profile annotation:


    ```
    //Spring component
    @Service
    @Profile("cloud")
    public class DefaultPaymentService implements PaymentService {}
    ```

    ```
    //configuration
    @Configuration
    @Profile("cloud")
    public class ApplicationConfig {}
    ```

    ```
    // another configuration
    @Configuration
    public class ApplicationConfig {
        @Bean
        @Profile("cloud")
        public PaymentService paymentService() { //... }
    }
    ```
We can use it in three different ways first of all on a spring component so here we have the first one is the DefaultPaymentService class and i want this service to be only available for the profile Cloud.

We can use it on the configuration level so I want this configuration to be scanned and applied by Spring only for the cloud profile.

We can declare the profile on the bean configuration so this means that a specific bean is only available for the cloud profile.

Bean can be  ***active or activated programmatically *** for example let's see this code:

    ```
    public static void main(String[] args) {
      AnnotationConfigApplicationContext applicationContext;
      applicationContext = new AnnotationConfigApplicationContext();
      applicationContext.getEnvironment().setActiveProfiles("cloud");
      applicationContext.scan("com.site.sample");
      applicationContext.refresh();
      
      PaymentService paymentService =        
          applicationContext.getBean(PaymentService.class);
    }
    ```
To configure a Spring application with active profiles, you first create an instance of `AnnotationConfigApplicationContext`, which serves as your application context.
Through this context, you can access the environment using `getEnvironment()` and set the active profiles by providing the profile name with `setActiveProfiles("profileName")`.
If your configuration classes or components are located in a specific package, you can specify it using `applicationContext.scan("base.package")`.
After that, it's important to call `refresh()` so that Spring initializes and refreshes the context properly. Once the context is set up, you can retrieve beans associated with the active profile, such as getting a `PaymentService` bean using `applicationContext.getBean(PaymentService.class)`.

Another different way to define the active profile so using  ***the properties file*** .

in Spring framework the value annotation is used at the field level or method or constructor parameter level for expression driven dependency injection this annotation is commonly used for injecting values into variable in a class whether they are primitives strings or complex types these values can come from properties files system properties or they can be hardcoded. Here is an example:

    ```
    @Configuration
    @PropertySource("classpath:database.properties")
    public class ApplicationConfig {
        @Value("${jdbc.url}")
        private String url;
    }
    ```

Th value annotation can also resolve  ***dynamic expression***  to access other beans or well-known beans like for example system properties:

    ```
    @Component
    public class FeeCalculator {
        private String defaultLocale;
        @Value("#{systemProperties['user.region']}"
        public void setDefaultLocale(String defaultLocale) {
            this.defaultLocale = defaultLocale;    
        }
    }
    ```
