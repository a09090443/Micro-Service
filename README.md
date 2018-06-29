Micro-Services Project
======================
#### Services list
---
1. oauth-server
   - port:8081
2. oauth-jdbc-client
   - port:8082
3. oauth-jwt-client
   - port:8083
4. oauth-sso
   - port:8084
5. config-server
   - port:8888
6. config-repo

### Config Repo
---
##### Config file list
1. application.yml : common config environment
2. oauth-server-[dev, pro].yml : oauth-server config environment
3. oauth-jdbc-client-[dev, pro].yml : oauth-jdbc-client config environment
4. oauth-jwt-client-[dev, pro].yml : oauth-jwt-client config environment
5. oauth-sso-client-[dev, pro].yml : oauth-sso config environment
6. oauth-web-client-[dev, pro].yml : oauth-web config environment

### Config Server
---
The HTTP service has resources in the following form:
> /{application}/{profile}[/{label}]
  - http://127.0.0.1:8888/oauth-jdbc-client/dev/spring_config

> /{application}-{profile}.yml
  - http://127.0.0.1:8888/oauth-jdbc-client-dev.yml

> /{label}/{application}-{profile}.yml
  - http://127.0.0.1:8888/spring_config/oauth-jdbc-client-dev.yml

> /{application}-{profile}.properties
  - http://127.0.0.1:8888/oauth-jdbc-client-dev.properties

> /{label}/{application}-{profile}.properties
  - http://127.0.0.1:8888/spring_config/oauth-jdbc-client-dev.properties

> /{application}-{profile}.json
  - http://127.0.0.1:8888/oauth-jdbc-client-dev.json

> /{label}/{application}-{profile}.json
  - http://127.0.0.1:8888/spring_config/oauth-jdbc-client-dev.json

### Login Info
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
  - http://127.0.0.1:8081/oauth/authorize?response_type=code&redirect_uri=http://127.0.0.1:8081/api/test1&client_id=auth_test&client_secret=secret
  
  - http://127.0.0.1:8081/oauth/token?grant_type=authorization_code&code=ti6eIJ&redirect_uri=http://127.0.0.1:8081/api/test1&client_id=auth_test&client_secret=secret
#### 2. resource owner password credentials Url
  - http://127.0.0.1:8081/oauth/token?grant_type=password&username=admin&password=admin&client_id=password_test&client_secret=secret
#### 3. implicit Url
  - http://127.0.0.1:8081/oauth/authorize?response_type=token&client_id=implicit_test&redirect_uri=http://127.0.0.1:8081/api/test1
#### 4. client credentials
  - http://127.0.0.1:8081/oauth/token?grant_type=client_credentials&client_id=client_test&client_secret=secret

### Request Url(HTTP Method:GET,POST)
---
  - http://127.0.0.1:8082/service2?access_token=token_id

### Check Token Url(HTTP Method:GET,POST)
---
  - http://localhost:8081/oauth/check_token?token=token_id

### Refresh Token Url(HTTP Method:POST)
---
  - http://localhost:8081/oauth/token?grant_type=refresh_token&refresh_token=refresh_token_id&client_id=auth_test&client_secret=secret

### Web management
---
  - http://localhost:8084