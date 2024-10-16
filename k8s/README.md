# Run Keycloak in Kubernets Cluster

Start keycloak Server.

```shell
kc apply -f keycloak.yaml
```

Add ingress to Keycloak.

```shell
```

Change hosts file and add the following line. For windows the hosts file path is `C:\Windows\System32\drivers\etc\hosts`.

```shell
127.0.0.1 kc.k8s.local
```