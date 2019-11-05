[![JDK](http://img.shields.io/badge/JDK-v8.0-yellow.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Spring](http://img.shields.io/badge/Spring-5.1.2-yellow.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Spring Boot](http://img.shields.io/badge/Spring_Boot-1.5.12-yellow.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Build](http://img.shields.io/badge/Build-Maven_2-green.svg)](https://maven.apache.org/)
[![License](http://img.shields.io/badge/License-Apache_2-red.svg)](http://www.apache.org/licenses/LICENSE-2.0)

Micro-Services Project
======================

* [x] [Spring Boot](http://#)
* [x] [Spring Security](http://#)
* [x] [Spring Oauth2](http://#)
* [x] [Spring Config](http://#)
* [x] [Spring Eureka](http://#)
* [x] [Spring Feign](http://#)
* [x] [Spring Histrix](http://#)

#### How to start each service
1. Install maven in operator system.
2. using maven command to compile project.
   - change to root directory (Micro-Service)
   - execute maven command:`mvn package -Dmaven.test.skip=true`
3. after compile and you will find jar file in each project target directory, e.g.,
   - oauth-server:jar file in `oauth-server/target`
4. you have to follow start sequence:
   1. eureka-server:`java -jar ./eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar`
   2. config-server:`java -jar ./config-server/target/config-server-0.0.1-SNAPSHOT.jar`
   3. oauth-server:`java -jar ./oauth-server/target/oauth-server-0.0.1-SNAPSHOT.jar`
   4. resource-api-jdbc-server:`java -jar ./resource-api-jdbc-server/target/oauth-jdbc-client-0.0.1-SNAPSHOT.jar`
   5. resource-api-jwt-server:`java -jar ./resource-api-jwt-server/target/oauth-jwt-client-0.0.1-SNAPSHOT.jar`
   6. oauth-sso:`java -jar ./oauth-sso/target/oauth-sso-0.0.1-SNAPSHOT.jar`
   7. oauth-web:`java -jar ./oauth-web/target/oauth-web-0.0.1-SNAPSHOT.jar`
   8. zuul-server:`java -jar ./zuul-server/target/zuul-server-0.0.1-SNAPSHOT.jar`


#### Services list
---
<table>
<tbody><tr>
<td>Server name</td>  <td>Comment</td>  <td>port</td>
</tr>
<tr>
<td>config-server</td>  <td>Cloud config server</td>  <td>8000</td>
</tr>
<tr>
<td>eureka-server</td>  <td>Discovery and register service center</td>  <td>8001</td>
</tr>
<tr>
<td>oauth-server</td>  <td>Securing access server</td>  <td>8002</td>
</tr>
<tr>
<td>resource-api-jdbc-server</td>  <td>Provider RESTFul API via JDBC access</td>  <td>8010</td>
</tr>
<tr>
<td>resource-api-jwt-server</td>  <td>Provider RESTFul API via JWT access</td>  <td>8011</td>
</tr>
<tr>
<td>loadbalance-1</td>  <td>Load balance server 1</td>  <td>8012</td>
</tr>
<tr>
<td>loadbalance-2</td>  <td>Load balance server 2</td>  <td>8013</td>
</tr>
<tr>
<td>oauth-sso</td>  <td>Admin manage system</td>  <td>8080</td>
</tr>
<tr>
<td>oauth-web</td>  <td>Admin manage system</td>  <td>8081</td>
</tr>
<tr>
<td>zuul-server</td>  <td>Gateway server</td>  <td>8090</td>
</tr>
<tr>
<td>hystrix-dashboard</td>  <td>Hystrix monitor server</td>  <td>8100</td>
</tr>
<tr>
<td>hystrix-feign</td>  <td>Hystrix Feign Server</td>  <td>8101</td>
</tr>
<tr>
<td>feign-server</td>  <td>Light load balance server</td>  <td>8102</td>
</tr>
</tbody></table>

### Eureka Server
---
#### Eureka web login info
  - Username:`admin`
  - Password:`admin`

#### Eureka monitor Url:
  - http://127.0.0.1:8001

### Config Repo
---
##### Config file list
1. application.yml : common config environment
2. oauth-server-[dev, pro].yml : oauth-server config environment
3. resource-api-jdbc-server-[dev, pro].yml : resource-api-jdbc-server config environment
4. resource-api-jwt-server-[dev, pro].yml : resource-api-jwt-server config environment
5. oauth-sso-client-[dev, pro].yml : oauth-sso config environment
6. oauth-web-client-[dev, pro].yml : oauth-web config environment
6. zuul-server-[dev, pro].yml : zuul-server config environment
7. loadlbalance-1-[dev, pro].yml : loadbalance-1 config environment
8. loadlbalance-2-[dev, pro].yml : loadbalance-2 config environment
9. hystrix-dashboard-[dev, pro].yml : hystrix-dashboard config environment
10. hystrix-feign-[dev, pro].yml : hystrix-feign config environment
11. feign-server-[dev, pro].yml : zfeign-server config environment

### Config Server
---
The HTTP service has resources in the following form:
> /{application}/{profile}[/{label}]
  - http://127.0.0.1:8000/resource-api-jdbc-server/dev/develop

> /{application}-{profile}.yml
  - http://127.0.0.1:8000/resource-api-jdbc-server-dev.yml

> /{label}/{application}-{profile}.yml
  - http://127.0.0.1:8000/develop/resource-api-jdbc-server-dev.yml

> /{application}-{profile}.properties
  - http://127.0.0.1:8000/resource-api-jdbc-server-dev.properties

> /{label}/{application}-{profile}.properties
  - http://127.0.0.1:8000/develop/oauth-jdbc-client-dev.properties

> /{application}-{profile}.json
  - http://127.0.0.1:8000/oauth-jdbc-client-dev.json

> /{label}/{application}-{profile}.json
  - http://127.0.0.1:8000/develop/oauth-jdbc-client-dev.json

### Admin manage system login info
---
  - Username:`admin`
  - Password:`admin`

### Grant Types
---
##### 1. Authorization Code
   - Client id:`auth_test`
   - Client secret:`secret`
##### 2. resource owner password credentials
   - Client id:`password_test`
   - Client secret:`secret`
##### 3. implicit
   - Client id:`implicit_test`
   - Client secret:`secret`
##### 4. client credentials
   - Client id:`client_test`
   - Client secret:`secret`

### Get Token URL(HTTP Method:POST)
---
#### 1. Authorization Url
__Get code__
  - http://127.0.0.1:8002/oauth/authorize?response_type=code&redirect_uri=http://127.0.0.1:8081/api/test1&client_id=auth_test&client_secret=secret

__Get Token__
  - http://127.0.0.1:8002/oauth/token?grant_type=authorization_code&code=ti6eIJ&redirect_uri=http://127.0.0.1:8081/api/test1&client_id=auth_test&client_secret=secret
#### 2. resource owner password credentials Url
__Get Token__
  - http://127.0.0.1:8002/oauth/token?grant_type=password&username=admin&password=admin&client_id=password_test&client_secret=secret
#### 3. implicit Url
__Get Token__
  - http://127.0.0.1:8002/oauth/authorize?response_type=token&client_id=implicit_test&redirect_uri=http://127.0.0.1:8081/api/test1
#### 4. client credentials
__Get Token__
  - http://127.0.0.1:8002/oauth/token?grant_type=client_credentials&client_id=client_test&client_secret=secret

### Request Url(HTTP Method:GET,POST)
---
  - http://127.0.0.1:8002/service2?access_token=token_id

### Check Token Url(HTTP Method:GET,POST)
---
  - http://127.0.0.1:8002/oauth/check_token?token=token_id

### Refresh Token Url(HTTP Method:POST)
---
  - http://127.0.0.1:8002/oauth/token?grant_type=refresh_token&refresh_token=refresh_token_id&client_id=auth_test&client_secret=secret

### Web management
---
  - http://127.0.0.1:8080

### Feign server Test
---
1. Start up loadbalance-1 and loadbalance-2 servers
2. Start up Feign server or Hystrix Feign server
3. Get access_token
4. Call test Url

  __Feign server__
  - http://127.0.0.1:8102/load/feign?access_token=token_id
  
  __Hystrix Feign server__
  - http://127.0.0.1:8101/load/feign?access_token=token_id
