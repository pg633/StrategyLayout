package cn.pg.sl.cache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/13 4:05 下午
 */
@Service
public class TestSpringCacheService {
    private Map<String, String> m = new HashMap<String,String>() {
        {
            put("1", "1");
            put("2", "2");
            put("3", "3");
            put("4", "4");
        }
    };
    boolean isOnce = true;

    @Cacheable(cacheNames = {"emp"},key = "#id",condition = "#id>1",unless = "#result==null")
    public String queryEmp(Integer id) {
        if (isOnce) {
            isOnce = false;
            return m.get(String.valueOf(id));
        }else{
            return m.get("3");
        }

    }

    @CachePut(value ="emp",key = "id")
    public  String updateEmp(int id){

        return "1";
    }
    @CacheEvict(value = "emp",beforeInvocation = true)
    public void deleteEmp(int id){
        return;
    }


}
