package io.kaiscript.redis;

import io.kaiscript.util.FSTSerializeUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by chenkai on 2017/9/18.
 */
public class NormalTest implements Serializable{

    @Test
    public void test() {
        Random random = new Random();
        int i = random.nextInt(2);
        System.out.println(i);
        i = random.nextInt(2);
        System.out.println(i);
    }

    @Test
    public void testSerialize() {
        User user = new User();
        byte[] bytes = FSTSerializeUtil.serialize(user);
        User u = FSTSerializeUtil.deserialize(bytes, User.class);
        Assert.assertEquals(user.getId(),u.getId());
        Assert.assertEquals(user.getName(),u.getName());
    }

    class User implements Serializable{
        private int id;
        private String name;
        public User(){
            id = 1;
            name = "test";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
