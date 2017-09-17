package io.kaiscript.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by kaiscript on 2017/9/17.
 */
public abstract class AbstractJedisManager implements JedisManager{

    public void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
