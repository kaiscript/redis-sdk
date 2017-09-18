package io.kaiscript.redis;

import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import java.util.List;

/**
 * Created by kaiscript on 2017/9/17.
 */
public abstract class AbstractJedisManager implements JedisManager{

    protected List<Pool<Jedis>> pools;

    public void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
