package io.kaiscript.util;

import org.nustaq.serialization.FSTConfiguration;

/**
 * Created by chenkai on 2017/9/18.
 */
public class FSTSerializeUtil {

    private static FSTConfiguration config = FSTConfiguration.createDefaultConfiguration();

    public static byte[] serialize(Object object) {
        return config.asByteArray(object);
    }

    public static <T> T deserialize(byte[] bytes,Class<T> t){
        return (T)config.asObject(bytes);
    }

}
