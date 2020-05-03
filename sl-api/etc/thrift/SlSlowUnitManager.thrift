namespace java.swift com.pg.sl.api.client


service SlSlowUnitManager{
        /**
        * 获取慢单元
        **/
        map<string,string> calculate(1:list<string> unitNameList,2: map<string,string> contextMap);

}


