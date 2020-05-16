import com.pg.sl.udf.api.AbstractGroovyAction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.CollectionUtils

/**
 * @author lianzheng04* @date 2020/5/15 1:16 下午
 * @version 1.0
 */
class FakeUserGroovy extends AbstractGroovyAction {
    private static final Logger logger = LoggerFactory.getLogger("FakeUserGroovy")
    private static final String indiName = "creditlist_dpid_whitelist_write";

    @Override
    Object doAction(Map<String, Object> params) throws Exception {
        if (CollectionUtils.isEmpty(params)) {
            return null
        }
        return params.get(indiName)
    }

    static void main(String[] args) {
        def map  = ['creditlist_dpid_whitelist_write1':'asd']
        def action = new FakeUserGroovy().doAction(map)
        println action
    }
}
