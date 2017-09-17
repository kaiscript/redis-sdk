package io.kaiscript.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Created by kaiscript on 2017/9/17.
 */
public class JedisClient {

    private JedisManager manager;

    public void set(String key,String value) {
        try(Jedis jedis = manager.getJedis()) {
            if (jedis != null) {
                jedis.set(key, value);
            }
        } catch (Exception e) {
            throw new JedisException(e);
        }
    }


    public String get(String key) {
        try(Jedis jedis = manager.getJedis()){
            if (jedis != null) {
                return jedis.get(key);
            }
        } catch (Exception e){
            throw new JedisException(e);
        }
        return "";
    }

    public JedisManager getManager() {
        return manager;
    }

    public void setManager(JedisManager manager) {
        this.manager = manager;
    }

}
