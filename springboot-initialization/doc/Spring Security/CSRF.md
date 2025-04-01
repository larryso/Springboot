# Cross Site Request Forgery (CSRF)

Cross Site Request Forgery (CSRF) is an attack that forces authenticated users to submit a request to a Web application which they are currently authenticated.

In a CSRF attack, an attacker assumes the victim’s identity and uses it to perform actions on behalf of the user. For example, a successful CSRF attack can force a user to transfer funds, change their email address, or change their password. Attackers typically use social engineering schemes to trick users into executing these attacks. For example, a user might receive an email or a text message with a link that urges them to immediately take action.

For more about CSRF attack, please visit [https://brightsec.com/blog/cross-site-request-forgery-csrf/](https://brightsec.com/blog/cross-site-request-forgery-csrf/)

## How can an application prevent a Cross Site Request Forgery attack?
To defeat a CSRF attack, applications need a way to determine if the HTTP request is legitimately generated from the application's user interface.
The Best way to achieve this is through a CSRF token.

A CSRF Token is a secret, unique and unpredictable value a server-side application generates in order to protect CSRF vulnerable resources.

The tokens are generated and submitted by the server-side application in a subsequent HTTP request made by the client.

After the request is made, the server side application compares the two tokens found in the user session and in the request. If the token is missing or does not match the value within the user session, the request is rejected, the user session terminated and the event logged as a potential CSRF attack.

For more about CSRF Token, Please visit [https://brightsec.com/blog/csrf-token/](https://brightsec.com/blog/csrf-token/)

## How Spring Security prevent CSRF

Spring Security protect CSRF attacks by default 

For reference document, please visit [https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#csrf-components](https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#csrf-components)

