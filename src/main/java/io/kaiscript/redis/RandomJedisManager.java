package io.kaiscript.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.Pool;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenkai on 2017/9/18.
 */
public class RandomJedisManager extends AbstractJedisManager{

    @Override
    public Jedis getJedis() {
        Random random = new Random();
        int index = random.nextInt(pools.size());
        while (true){
            try {
                Pool<Jedis> pool = pools.get(index);
                Jedis jedis = pool.getResource();
                if (jedis != null) {
                    return jedis;
                }
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (Exception e) {
                throw new JedisException(e);
            }
            index = random.nextInt(pools.size());
        }
    }

}
