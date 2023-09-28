# Java - Annotation Process

Java Annotation Associated with java source code elements such as classes, methods, and varriables.
Annotation provides information to a program at compile time or running time base on which the programe can take further action.

An Annotation processor processes these annotations at compile time or running time to provide functionality such as code generation, errror checking, etc.

The java,lang package provides some core annotations and we also can create our custom annotations that can be processed with annotation processors.

## Annotaion Basic
An Annotaion could be defined by @ symbol

![](../../images/file_io.jpg)

### Standard Annotation

@SuppressWarnings - indicate that warnings on the code compilation should be ignored
@Deprecated
@Override
@FunctionalInterface - indicate that an interface could not have more than one abstract method

### Meta Annotation
Meta annotations are annotations applied to other annotations that provide information about the annotation to the compiler or the runntime environment.
Meta annotation can provide below information about an annotation:
* can the annotation be inherited by child classes? - @Inherited
* does the annotation need to be show up in the documentation? - @Documented
* can the annotation be applied multiple times? - @Repeatable
* what type does the annotation apply to, type such as class, method, etc. - @Target
* will the annotation be processed at compile time or running time - @Retention

## Custom Annotation
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Entity {
//Entity的名称
public String value() default "";
//是否懒加载
public boolean isLazyLoad() default true;
}


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityProperty {
public String value() default "";
}
```


