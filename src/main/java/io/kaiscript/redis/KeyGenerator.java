package io.kaiscript.redis;

/**
 *
 * Created by kaiscript on 2017/9/21.
 */
public interface KeyGenerator<T> {

    String key(T t);

}
