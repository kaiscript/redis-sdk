package io.kaiscript.redis;

import io.kaiscript.util.FSTSerializeUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.SafeEncoder;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void testmset() {
        Jedis jedis = pool.getResource();
        jedis.mset("123","66");
        System.out.println(jedis.get("123"));
    }

    @Test
    public void testMsetByte() {
        String key = "test";
        String value = "tt22";
        Jedis jedis = pool.getResource();
        byte[][] b = new byte[2][];
        b[0] = SafeEncoder.encode(key);
        b[1] = FSTSerializeUtil.serialize(value);
        jedis.mset(b);
        byte[] keyEncoding = SafeEncoder.encode(key);
        List<byte[]> mget = jedis.mget(keyEncoding);
        String deserialize = FSTSerializeUtil.deserialize(mget.get(0),String.class);
        Assert.assertEquals(value,deserialize);
    }

}
