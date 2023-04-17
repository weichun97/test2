package com.github.weichun97;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException, KeeperException {
        ZKManager zkManager = new ZKManagerImpl();
        zkManager.update("/test", "sadasd1".getBytes(StandardCharsets.UTF_8));
        String zNodeData = (String) zkManager.getZNodeData("/test", true);
        System.out.println(zNodeData);
    }
}
