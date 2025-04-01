
## JPA Annotations

### @EnableJpaRepositories @EntityScan
@EnableJpaRepositories will enable JPA repositories that contains in the given packages.

By default, Springboot auto configuration will scan only the main application package and its sub packages for detecting the JPA reposotories. Therefore, if the JPA reposotories are placed under the main application package or its sub packages, then it will be detected by @EnableAutoConfiguration as a part of auto configurating.
if the repository classes are not placed under main package and its sub packages, then @EnableJpaRepositories annotation can help enable JPA repositories that contain in the give packages.

`@EnableJpaRepositories(basePackages="com.xxx.sss")`

That is also the same for Entity classes. we can use @EntityScan to enable entity classes in given packages.

`@EntityScan(basePackages = {"com.xxx.aaa", "com.yyy.bbb"})`

