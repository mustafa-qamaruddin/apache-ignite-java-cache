package com.qubits;

import java.util.Arrays;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello, Apache Ignite!" );
        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder tcMp = new TcpDiscoveryMulticastIpFinder();
        tcMp.setLocalAddress("localhost");
        spi.setIpFinder(tcMp);
        IgniteConfiguration cfg = new IgniteConfiguration();
        cfg.setClientMode(false);
        cfg.setDiscoverySpi(spi);
        Ignite ignite = Ignition.start(cfg);
        IgniteCache<Integer, String> cache = ignite.getOrCreateCache("myCacheName");
        for ( int i = 1; i <= 100; i++) {
            cache.put(i, Integer.toString(i));
        }
        System.out.println("================================================");
        for ( int i = 1; i <= 100; i++) {
            System.out.println("Cache get: " + cache.get(i));
        }
        System.out.println("================================================");
        ignite.close();
    }
}
