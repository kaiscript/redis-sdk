package io.kaiscript.redis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaiscript on 2017/9/17.
 */
public class JedisClientTest {

    JedisPoolConfig config;
    Pool pool;
    JedisClient client;

    @Before
    public void before() {
        config = new JedisPoolConfig();
        pool = new JedisPool(config,"127.0.0.1",6379);
        List<Pool<Jedis>> pools = new ArrayList<>();
        pools.add(pool);
        DefaultJedisManager manager = new DefaultJedisManager();
        manager.setPools(pools);
        client = new JedisClient();
        client.setManager(manager);
    }

    @Test
    public void test() {
        String key = "test";
        String value = "value";
        client.set(key,value);
        Assert.assertEquals(client.get(key),value);
    }

    @Test
    public void msetTest() {
        client.mset("111", "666", "222", "888");
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        List<String> res = client.mget(list.toArray(new String[list.size()]));
        Assert.assertEquals(res.get(0), "666");
        Assert.assertEquals(res.get(1), "888");
    }

}
