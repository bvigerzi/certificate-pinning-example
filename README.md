# Certificate Pinning Example App

## Pinning with real certificate

```log
2021-07-15 18:40:50.627 22378-12631/com.example.sampleapp W/System.err:   Peer certificate chain:
2021-07-15 18:40:50.627 22378-12631/com.example.sampleapp W/System.err:     sha256/wLvcII1uFREqX5vHO9uYY6wbYdCtBg2nxcQCkxfAIdk=: CN=sni.cloudflaressl.com,O=Cloudflare\, Inc.,L=San Francisco,ST=California,C=US
2021-07-15 18:40:50.627 22378-12631/com.example.sampleapp W/System.err:     sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=: CN=Cloudflare Inc ECC CA-3,O=Cloudflare\, Inc.,C=US
2021-07-15 18:40:50.627 22378-12631/com.example.sampleapp W/System.err:     sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=: CN=Baltimore CyberTrust Root,OU=CyberTrust,O=Baltimore,C=IE
2021-07-15 18:40:50.627 22378-12631/com.example.sampleapp W/System.err:   Pinned certificates for triplee.tech:
2021-07-15 18:40:50.627 22378-12631/com.example.sampleapp W/System.err:     sha256/AAAA
```

## Pinning with Burpsuite MiTM

- Requires enabling user-certificates through network configuration on newer operating systems

```log
2021-07-15 19:00:41.822 12641-16010/com.example.sampleapp W/System.err: javax.net.ssl.SSLPeerUnverifiedException: Certificate pinning failure!
2021-07-15 19:00:41.822 12641-16010/com.example.sampleapp W/System.err:   Peer certificate chain:
2021-07-15 19:00:41.822 12641-16010/com.example.sampleapp W/System.err:     sha256//ixgr6MMNrnKpNw7Z/QDrCB97il+b0DJnI7iUZ5L9Ms=: CN=triplee.tech,OU=PortSwigger CA,O=PortSwigger,C=PortSwigger
2021-07-15 19:00:41.822 12641-16010/com.example.sampleapp W/System.err:     sha256//ixgr6MMNrnKpNw7Z/QDrCB97il+b0DJnI7iUZ5L9Ms=: CN=PortSwigger CA,OU=PortSwigger CA,O=PortSwigger,L=PortSwigger,ST=PortSwigger,C=PortSwigger
2021-07-15 19:00:41.822 12641-16010/com.example.sampleapp W/System.err:   Pinned certificates for triplee.tech:
2021-07-15 19:00:41.822 12641-16010/com.example.sampleapp W/System.err:     sha256/AAAA
```

Without the user-certificates enabling xml, we see the following error on Android 11. According to Android developer docs, Android 6.0 and under trusts user-certificates by default. Android 7.0 and over only trusts the system certificates by default (it will ignore user installed certs). Source: [Android Docs](https://developer.android.com/training/articles/security-config#base-config)

```log
2021-07-15 19:13:05.321 19003-19879/tech.triplee.certificatepinningexample W/System.err: javax.net.ssl.SSLHandshakeException: java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.
2021-07-15 19:13:05.321 19003-19879/tech.triplee.certificatepinningexample W/System.err:     at com.android.org.conscrypt.SSLUtils.toSSLHandshakeException(SSLUtils.java:362)
2021-07-15 19:13:05.321 19003-19879/tech.triplee.certificatepinningexample W/System.err:     at com.android.org.conscrypt.ConscryptEngine.convertException(ConscryptEngine.java:1134)
```
