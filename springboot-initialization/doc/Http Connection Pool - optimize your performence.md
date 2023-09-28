# Considering Http Connection Pool for opertimizing your application performence 

Connection pooling refers to reusage of existing pre-established connections to make HTTP requests, rather than creating a new connection for each service requests, may it be a connection to a remote REST API endpoint or a backened database instance.

## The HTTP Connection Lifecycle

![](./imgs/HTTP-Connection-Lifecycle.png.crdownload)

#### DNS Resolution
When calling another service, it usually has some kind of hostname (e.g. reqres.in). In order to call this service, this hostname needs to be resolved into an IP address.

DNS resolution typically happens over the UDP transport, which is a connectionless protocol unlike TCP, which means there is no handshake necessary between the client and the DNS server and instead the request can just be sent and then wait for the response.

#### TCP Connect
For data to flow between a client and server, the TCP handshake needs to occur. This handshake is used to initialise values which are later used to ensure reliability in the data exchange. This enables checks to take place which ensure that data is being received intact and without errors, and allows for retransmissions to occur if there are any problems. Once this handshake is completed the connection is said to be in an “established” state.

#### TLS Handshake
If you are securing your connections with TLS, then a TLS handshake occurs next. This handshake is much more heavy weight than the TCP handshake. Fairly large pieces of data need to be transferred between client and server such as the certificate being used by the server, and in Mutual TLS (client certificate authentication) the client certificate also needs to be sent to the server. Cryptographic functions need to be executed at each time which can be CPU intensive and also block if there is a lack of “entropy” available.

Once the TLS handshake is completed, the actual HTTP request can now be sent.

#### HTTP Request/Response
This is the part that we are actually bothered about most of the time. The client sends a request (containing elements such as the HTTP method and path, along with any request headers and a body if required). The server receives this requests, processes it however it needs to, and sends the response (containing a status code, headers and body). The client can then parse this response to obtain the data requested.

#### Close
As we started this conversation with a handshake, we now need to say goodbye to the server and close the connection. Again this is a 3 way handshake similar to the start of the conversation.

## Connection Pooling

Connection Pooling is a feature available in a number of HTTP client libraries, So how does it work?

Takeing an example of 2 requests, the first request will be processed as normal through the lifecycle, when the second request made to the same host, it can skip DNS resolution, and TCP TLS handshake.


