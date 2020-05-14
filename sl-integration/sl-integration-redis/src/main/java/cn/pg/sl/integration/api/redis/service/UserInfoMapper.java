package cn.pg.sl.integration.api.redis.service;

import cn.pg.sl.integration.api.redis.util.RedisUtils;
import com.pg.sl.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/12 4:40 下午
 */
@Component
public class UserInfoMapper {

    @Autowired
    RedisUtils redisUtils;

    public UserInfo getUserById(Integer id) {
        String s = redisUtils.get(String.valueOf(id));
        return GsonUtil.toObj(s, UserInfo.class);
    }

    public void updateUser(UserInfo userInfo) {
        redisUtils.set(
                String.valueOf(userInfo.getId()),
                GsonUtil.toJson(userInfo)
        );
    }

    public void deleteUserById(Integer id) {
        redisUtils.delete(
                String.valueOf(id));

    }
}
