package io.kaiscript.redis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by kaiscript on 2017/9/17.
 */
public class JedisTest {

    JedisPoolConfig config;

    JedisPool pool;

    @Before
    public void before() {
        config = new JedisPoolConfig();
        pool = new JedisPool(config,"127.0.0.1",6379);
    }

    @Test
    public void testConnect() {
        Jedis jedis = pool.getResource();
        String value = "ttt";
        jedis.set("test", value);
        Assert.assertEquals(jedis.get("test"),value);
    }

    @Test
    public void testReturn() {
        Jedis jedis = pool.getResource();
        Assert.assertNotNull(jedis);
        jedis.close();
        String value = "ttt";
        jedis.set("test",value);
        Assert.assertEquals(jedis.get("test"),value);
    }

    @Test
    public void testClose() {
        Jedis jedis = pool.getResource();
        jedis.close();
        Assert.assertTrue(jedis.isConnected());
    }

    @Test
    public void testTry() {
        try (Jedis jedis = pool.getResource()){
            String value = "ttt";
            jedis.set("test",value);
            Assert.assertEquals(jedis.get("test"),value);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
