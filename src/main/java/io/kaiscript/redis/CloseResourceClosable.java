package io.kaiscript.redis;

/**
 * Created by kaiscript on 2017/9/17.
 */
public class CloseResourceClosable implements AutoCloseable{

    @Override
    public void close() throws Exception {
        System.out.println("test close");
    }

}
