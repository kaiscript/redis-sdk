package io.kaiscript.redis;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Created by kaiscript on 2017/9/17.
 */
public class JedisClient {

    private JedisManager manager;

    public void set(String key,String value) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        set(key, value,-1);
    }

    public void set(String key, String value, int expire) {
        try (Jedis jedis = manager.getJedis()) {
            if (jedis != null) {
                jedis.setex(key, expire, value);
            }
        } catch (Exception e) {
            throw new JedisException(e);
        }
    }

    public long del(String key) {
        try(Jedis jedis = manager.getJedis()) {
            if (jedis != null) {
                return jedis.del(key);
            }
        } catch (Exception e) {
            throw new JedisException(e);
        }
        return 0;
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

    public long incr(String key) {
        try(Jedis jedis = manager.getJedis()){
            if (jedis != null) {
                return jedis.incr(key);
            }
        } catch (Exception e){
            throw new JedisException(e);
        }
        return 0;
    }

    public long decr(String key) {
        try (Jedis jedis = manager.getJedis()) {
            if (jedis != null) {
                return jedis.decr(key);
            }
        } catch (Exception e) {
            throw new JedisException(e);
        }
        return 0;
    }

    public JedisManager getManager() {
        return manager;
    }

    public void setManager(JedisManager manager) {
        this.manager = manager;
    }

}
