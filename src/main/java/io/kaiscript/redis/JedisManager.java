package io.kaiscript.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by kaiscript on 2017/9/17.
 */
public interface JedisManager {

    Jedis getJedis();

    void returnResource(Jedis jedis);

}
