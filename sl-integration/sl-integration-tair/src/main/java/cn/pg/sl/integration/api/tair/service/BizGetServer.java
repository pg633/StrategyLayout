package cn.pg.sl.integration.api.tair.service;

import cn.pg.sl.integration.api.IUnitFuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/6 12:49 下午
 */
@Service("biz_getKeyValue_celler")
public class BizGetServer implements IUnitFuncService {



    @Override
    public Object execute(Map<String, Object> calculateParamMap) {
        return "asd";
    }
}
