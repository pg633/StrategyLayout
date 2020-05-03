namespace java.swift com.pg.sl.api.client

service SlUnitManager{

        map<string,string> calculate(1:list<string> unitNameList, 2:map<string,string> contextMap);
        list<string> batchCalculate(1:list<string> unitNameList, 2:list<map<string, string>> contextMapList);
        string store(1:list<string> unitNameList, 2:map<string, string> contextMap);
}


