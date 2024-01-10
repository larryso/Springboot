# IoC Container

IoC - Inversion of Control, is one of those important concepts in Spring Framework.
In simple terms, it is a design pattern that allows the framework to take control of creating and configuring objects, and inject those objects in runtime.

Sprring IoC was implemented by using two important interfaces: BeanFactory and ApplicationContext.

BeanFactory is the core interface for implementing IoC, thinke of it as a container that holds all beans that defined in your configuration file.
At startup, BeanFactory creates and configures all these beans and stored them in the container, and you can request a bean from the BeanFactory at runtime.

ApplicationContext is a subinterface of BeanFactory that adds more functionality。

BeanFactory loads beans on-demand while ApplicationContext loads all beans at startup (lazy load and eager load)

```java
 BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
        Book book = (Book) beanFactory.getBean("book");
        System.out.println(book.getBookPublsher());

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Book book2 = (Book) context.getBean("book");
        System.out.println(book2);

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(BeanConfiguration.class);
        annotationConfigApplicationContext.refresh();

        Book book3 = (Book) annotationConfigApplicationContext.getBean("book");
        System.out.println(book3);
```

For More Details of Difference of BeanFactory VS ApplicationContext: [Difference](https://springframework.guru/spring-beanfactory-vs-applicationcontext/)