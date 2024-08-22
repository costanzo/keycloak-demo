# Demo project for Keycloak

## LDAP Setup

Use docker image: `https://hub.docker.com/r/bitnami/openldap`

```shell
docker pull bitnami/openldap:2.5
```

## Kerberos

Use docker image: `https://hub.docker.com/r/kerberos/kerberos`

```shell
docker pull kerberos/kerberos
```

## infinispan

Use docker image: `https://hub.docker.com/r/infinispan/server`

```shell
docker pull infinispan/server:15.0
```

Start infinispan server

```shell
docker run -d --name infinispan-server \
    -v /<path>/keycloak-model-infinispan-25.0.2.jar:/opt/infinispan/lib/keycloak-model-infinispan-25.0.2.jar \
    -v /<path>/keycloak-server-spi-25.0.2.jar:/opt/infinispan/lib/keycloak-server-spi-25.0.2.jar \
    -v /<path>/keycloak-core-25.0.2.jar:/opt/infinispan/lib/keycloak-core-25.0.2.jar \
    -v infinispan/infinispan.xml:/opt/infinispan/server/conf/infinispan.xml \
    -v infinispan:/user-config \
    -e IDENTITIES_BATCH="/user-config/identities.batch" -p 11222:11222 infinispan/server:15.0
```

In brower go to `http://localhost:11222`

Set environment variable before start kc:
- `KC_CACHE`: ispn
- `KC_CACHE_REMOTE_HOST`: localhost
- `KC_CACHE_REMOTE_PASSWORD`: keycloak
- `KC_CACHE_REMOTE_PORT`: 11222
- `KC_CACHE_REMOTE_TLS_ENABLED`: false
- `KC_CACHE_REMOTE_USERNAME`: keycloak

In the `cache-ispn.xml` file, add site name:

```xml
<cache-container name="keycloak">
    <transport lock-timeout="60000" stack="udp" site="mysite"/>
    ...
</cache-container>
```

## UI Dev Setup

In the `js/apps/admin-ui` directory, start the admin UI:

```shell
npm run dev
```

Set the following environment variables before launch the IDELanuncher:
- `KC_ACCOUNT_VITE_URL`: http://localhost:5173
- `KC_ADMIN_VITE_URL`: http://localhost:5174
- `KC_FEATURES`: login2,account3,admin-fine-grained-authz,transient-users,oid4vc-vci