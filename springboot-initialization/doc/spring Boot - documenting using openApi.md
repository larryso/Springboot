# Documenting a Spring REST API Using OpenAPI 

## Setting up springdoc-openapi

```
implementation ('org.springdoc:springdoc-openapi-ui:1.6.11')
```

After setting up the dependency, by default, no furhter configuration is required,  we can access the swagger files or directory to the generated UI:

http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs

To change the default configuration, we can do it by adding the [neccessary properties](https://springdoc.org/#springdoc-openapi-core-properties), including the [UI params](https://springdoc.org/#swagger-ui-properties) to our application.yml