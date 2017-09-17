package io.kaiscript.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.Pool;

import java.util.List;

/**
 * Created by kaiscript on 2017/9/17.
 */
public class DefaultJedisManager extends AbstractJedisManager {

    private List<Pool<Jedis>> pools;

    public Jedis getJedis() {
        for (Pool<Jedis> pool : pools) {
            try {
                Jedis jedis = pool.getResource();
                if (jedis != null) {
                    return jedis;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new JedisException("cannot get jedis from pool.");
    }

    public void setPools(List<Pool<Jedis>> pools) {
        this.pools = pools;
    }
}
