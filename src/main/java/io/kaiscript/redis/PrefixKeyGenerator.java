package io.kaiscript.redis;

/**
 * Created by kaiscript on 2017/9/21.
 */
public class PrefixKeyGenerator<T> implements KeyGenerator<T> {

    private static final String UNDERLINE = "_";
    private String prefix = "";

    public PrefixKeyGenerator(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String key(T t) {
        return prefix+UNDERLINE+t.toString();
    }

}
