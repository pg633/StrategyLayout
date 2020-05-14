package cn.pg.sl.integration.api.redis.service;

import com.google.common.collect.Lists;
import com.pg.sl.util.GsonUtil;
import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/12 4:21 下午
 */
@Service("userService")
public class UserServiceImpl  {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * @Cacheable
     * 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中
     * unless 表示条件表达式成立的话不放入缓存
     */
    @Cacheable(value = "city" , keyGenerator = "keyGenerator")
    public List<City> searchById(City id) {
        System.out.println("没有执行缓存");
        List<City> objects = Lists.newArrayList();
        objects.add(id);
        return objects;
    }




    @Cacheable(value = "test", key = "#id")
    public UserInfo getUserById(Integer id) {

        return new UserInfo();



//                userInfoMapper.getUserById(id);
    }

    //修改了数据库的数据，同时更新缓存。先调用目标方法，然后缓存方法结果。
    @CachePut(value = "test",key="#result.id")  //只能是result.id
    public UserInfo updateUser(UserInfo userInfo) {
//        userInfoMapper.updateUser(userInfo);
        return userInfo;
    }

    //删除数据之后，清除缓存
    @CacheEvict(value = "test", key = "#id")
    public String deleteUser(Integer id) {
        userInfoMapper.deleteUserById(id);
        return "已删除";
    }

    @CachePut(value = "city",key = "#result.id")
    public City putRedis(City city) {
        return city;
    }
}