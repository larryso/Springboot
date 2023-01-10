# spring @Configuration注解的proxyBeanMethods属性
@Configuration注解与@Component注解的区别，@Component注解用来定义一个Bean，而@Configuration与@Bean注解结合，可以在一个类中定义多个Bean

@Configuration注解有一个属性proxyBeanMethods, 
* 这个属性true 时： 外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象，也就是说，保证每个@Bean方法被调用多少次返回的组件都是单例的，所以如果proxyBeanMethods=true时，每次从外部调用@Bean方法时，都会进行判断操作，判断容器中是否有这个组件，会比较耗时，也即Full模式
* 这个属性false 时：每个获取Bean方法被调用多少次返回的组件都是新创建的bean;即当从外部调用 get Bean方法时，不会去判断容器中是否有这个组件&;而是直接新创建一个组件然后返回 比Full模式效率高

* proxyBeanMethods is true by default

## @Configuration注解的原理
首先来看一下@Configuration注解的源码：
``` java
package org.springframework.context.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration {
	@AliasFor(annotation = Component.class)
	String value() default "";
	boolean proxyBeanMethods() default true;

}
```
@Configuration注解上打了一个@component注解

spring会扫描所有@component注解的类及其子类（包括@Configuration注解声明的类），认为这些类是bean, 并且把这些bean对应的beanDefinition放到容器中进行管理。BeanDefinition是对bean的描述，里边存有bean的名称，Class等基本信息

在获取到所有的bean defenition之后，Spring会有一些post process执行，其中一个就是ConfigurationClassPostProcessor， 在这里，Spring会遍历所有的bean definition， 如果发现其中有标记了@Configuration注解的，会对这个类进行CGLIB代码，生成一个代理的类，并且把这个类设置到BeanDefenition的Class属性中。当需要拿到这个bean的实例的时候，会从这个class属性中拿到的Class对象进行反射，那么最终反射出来的是代理增强后的类

代理中对方法进行了增强？在哪方面进行了增强？对于@Bean标记的方法，返回的都是一个bean，在增强的方法中，Spring会先去容器中查看一下是否有这个bean的实例了，如果有了的话，就返回已有对象，没有的话就创建一个，然后放到容器中
