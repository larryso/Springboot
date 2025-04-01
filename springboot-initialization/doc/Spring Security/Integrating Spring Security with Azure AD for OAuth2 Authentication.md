# Integrating Spring Security with Azure AD for OAuth2 Resource Server

Here's how to configure Spring Security as an OAuth2 Resource Server using Azure AD for token validation, suitable for protecting REST APIs.

## 1. Azure AD Application Setup

1. Register your application in Azure Portal:

Go to Azure Portal

Navigate to Azure Active Directory > App registrations > New registration

Configure:

Name: Your application name

Supported account types: Select appropriate option (usually "Accounts in this organizational directory only")

Redirect URI:

Type: Web

URL: http://localhost:8080/login/oauth2/code/azure (development)

For production: Your production URL with same path

2. After registration, note:

Application (client) ID

Directory (tenant) ID

3. Create a client secret:

Go to Certificates & secrets

Click New client secret

Add description and select expiry

Copy the secret value (you won't see it again)

## 2. Spring Boot Application Setup

Step 1: Add Dependencies
```
implementation 'org.springframework.boot:spring-boot-starter-oauth2-resource-server'
implementation 'org.springframework.boot:spring-boot-starter-security'
```

Step 2: Configure Azure AD Properties
```yml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://login.microsoftonline.com/{tenant-id}/v2.0
          jwk-set-uri: https://login.microsoftonline.com/{tenant-id}/discovery/v2.0/keys
          audience: api://{application-client-id}
```
