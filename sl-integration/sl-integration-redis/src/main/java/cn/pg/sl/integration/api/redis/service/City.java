package cn.pg.sl.integration.api.redis.service;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/13 7:22 下午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private Integer id;
    private String name;
}
