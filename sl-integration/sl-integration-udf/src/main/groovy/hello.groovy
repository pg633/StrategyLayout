//import groovy.transform.PackageScope
//import org.junit.Test
//
//import java.util.concurrent.CountDownLatch
//
///**
// * @author lianzheng04* @date 2020/5/14 10:04 上午
// * @version 1.0
// */
//
//class hello {
//
//    /**
//
//     * @param args
//
//     */
//
//    static void main(def args) {
//
//        // TODO Auto-generated method stub
//        println "helloworld"
//
//        def var = "hello world"
//
//        println var
//
//        println var.class
//
//
//        new hello().repeat(var)
//    }
//
//    def repeat(vaL) {
////        for (int i = 0; i < 6; i++) {
////            println vaL
////        }
//        for (i in 0..5) {
//            println vaL
//        }
//    }
//
//    def repeat2(val, repeat = 3) {
//        for (i in 0..repeat) {
//            println "this is in ${i} : ${val}"
//        }
//    }
//
//    @Test
//    void func1() {
////        println  " int test func1"
//        repeat2("1")
//    }
//
//    @Test
//    void testCollections() {
//        def collect = ["a", "b", "c"]
////        除了声明时往集合中加入元素外，还能够用下面方式向集合中加入元素：
//        collect.add(1);
//        collect << "come on";
//        collect[collect.size()] = 100.0
//
////        Collection使用相似数组下标的方式进行检索：
//        println collect[0..2]
////      删除
//        collect = collect - 'a'         //在集合中减去元素a(第1个)
//        println collect[0]          //如今第1个元素变成b了
////        相同地，你能够往集合中加入还有一个集合或删除一个集合：
//        println collect[0]   //如今集合中仅有一个元素，即原来的最后一个元素
//        println collect[-1]  //也能够用负索引，证明最后一个元素就是第一个元素
//    }
//
//    @Test
//    void testMap() {
//        def map = ['name': 'john', 'age': 14, 'sex': 'boy']
////        加入项：
//        map = map + ['weight': 25]       //加入john的体重
//        map.put('length', 1.27)      //加入john的身高
//        map.father = 'Keller'         //加入john的父亲
//
////        能够用两种方式检索值：
//        println map['father']       //通过key作为下标索引
//        println map.length          //通过key作为成员名索引
//
//        println ""
//
////        map.each({ key, value ->    //key,value两个參数用于接受每一个元素的键/值
////            println "$key:$value"
////        })
////        map.each { println it }     //it是一个keyword，代表map集合的每一个元素
////        map.each {println it.getKey()+"--><" + it.getValue()}
////        除了用于迭代之外，闭包也能够单独定义：
//
//        def say = { word ->
//            println "Hi,$word!"
//        }
////        调用：
//        say('groovy')
//        say.call('groovy&grails')
//
//    }
//
//
//    class Person {
//        def name
//        def age
//
//        String toString() {
//            "$name:$age"
//        }
//
//    }
//
//    @Test
//    void testClass() {
//
//        def person2 = new Person(['name': 'gg', 'age': 22])
//        println person2
//        println person1?.name
//    }
//
//    int sum(int ... var) {
//        def total = 0
//        for (i in var) {
//            total += i
//        }
//        return total
//    }
//
//    class pp {
//        @PackageScope
//        String name;
//    }
//
//    class A {
//        static class B {}
//    }
//
//    @Test
//    void testArm() {
//        //执行Groovy代码
//        Eval.me("println 'hello world'");
//        //绑定自定义参数
//        Object result = Eval.me("age", 22, "if(age < 18){'未成年'}else{'成年'}");
//        println(result)
//        //绑定一个名为 x 的参数的简单计算
//        println(Eval.x(4, "2*x"))
//
//        //带有两个名为 x 与 y 的绑定参数的简单计算
//        println(Eval.xy(4, 5, "x+y"))
//
//    }
//    @Test
//    void testClassLofer(){
//        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
//        String scriptText = "class Hello { void hello() { println 'hello' } }";
//        //将Groovy脚本解析为Class对象
//        Class clazz = groovyClassLoader.parseClass(scriptText);
//        //Class clazz = groovyClassLoader.parseClass(new File("script.groovy"));
//        println( clazz.getName())
//        clazz.getMethod("hello").invoke(clazz.newInstance());
//
//    }
//
//
//}