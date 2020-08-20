package com.example.assignmentproject.network

import android.os.Build
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import timber.log.Timber
import java.io.IOException
import java.net.InetAddress
import java.net.Socket
import java.net.UnknownHostException
import java.security.KeyStore
import javax.net.ssl.*

class Tls12SocketFactory(private val delegate: SSLSocketFactory) : SSLSocketFactory() {
    companion object {
        /**
         * @return [X509TrustManager] from [TrustManagerFactory]
         *
         * @throws [NoSuchElementException] if not found. According to the Android docs for [TrustManagerFactory], this
         * should never happen because PKIX is the only supported algorithm
         */
        val trustManager by lazy {
            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(null as KeyStore?)
            trustManagerFactory.trustManagers.first { it is X509TrustManager } as X509TrustManager
        }
    }

    /**
     * Forcefully adds [TlsVersion.TLS_1_2] as an enabled protocol if called on an [SSLSocket]
     *
     * @return the (potentially modified) [Socket]
     */
    private fun Socket.patchForTls12(): Socket {
        return (this as? SSLSocket)?.apply {
            enabledProtocols += TlsVersion.TLS_1_2.javaName
        } ?: this
    }

    override fun getDefaultCipherSuites(): Array<String> {
        return delegate.defaultCipherSuites
    }

    override fun getSupportedCipherSuites(): Array<String> {
        return delegate.supportedCipherSuites
    }

    @Throws(IOException::class)
    override fun createSocket(s: Socket, host: String, port: Int, autoClose: Boolean): Socket? {
        return delegate.createSocket(s, host, port, autoClose)
            .patchForTls12()
    }

    @Throws(IOException::class, UnknownHostException::class)
    override fun createSocket(host: String, port: Int): Socket? {
        return delegate.createSocket(host, port)
            .patchForTls12()
    }

    @Throws(IOException::class, UnknownHostException::class)
    override fun createSocket(host: String, port: Int, localHost: InetAddress, localPort: Int): Socket? {
        return delegate.createSocket(host, port, localHost, localPort)
            .patchForTls12()
    }

    @Throws(IOException::class)
    override fun createSocket(host: InetAddress, port: Int): Socket? {
        return delegate.createSocket(host, port)
            .patchForTls12()
    }

    @Throws(IOException::class)
    override fun createSocket(address: InetAddress, port: Int, localAddress: InetAddress, localPort: Int): Socket? {
        return delegate.createSocket(address, port, localAddress, localPort)
            .patchForTls12()
    }
}

/**
 * If on [Build.VERSION_CODES.LOLLIPOP] or lower, sets [OkHttpClient.Builder.sslSocketFactory] to an instance of
 * [Tls12SocketFactory] that wraps the default [SSLContext.getSocketFactory] for [TlsVersion.TLS_1_2].
 *
 * Does nothing when called on [Build.VERSION_CODES.LOLLIPOP_MR1] or higher.
 *
 * For some reason, Android supports TLS v1.2 from [Build.VERSION_CODES.JELLY_BEAN], but the spec only has it
 * enabled by default from API [Build.VERSION_CODES.KITKAT]. Furthermore, some devices on
 * [Build.VERSION_CODES.LOLLIPOP] don't have it enabled, despite the spec saying they should.
 *
 * @return the (potentially modified) [OkHttpClient.Builder]
 */
fun OkHttpClient.Builder.enableTls12() = apply {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
        try {
            val sslContext = SSLContext.getInstance(TlsVersion.TLS_1_2.javaName)
            sslContext.init(null, arrayOf(Tls12SocketFactory.trustManager), null)
            sslSocketFactory(
                Tls12SocketFactory(sslContext.socketFactory),
                Tls12SocketFactory.trustManager
            )

            val connectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .build()

            val connectionSpecsList = mutableListOf<ConnectionSpec>()
            connectionSpecsList.add(connectionSpec)
            connectionSpecsList.add(ConnectionSpec.COMPATIBLE_TLS)
            connectionSpecsList.add(ConnectionSpec.CLEARTEXT)

            connectionSpecs(connectionSpecsList)

        } catch (e: Exception) {
            Timber.v(e, "Error while setting TLS 1.2 compatibility")
        }
    }
}
