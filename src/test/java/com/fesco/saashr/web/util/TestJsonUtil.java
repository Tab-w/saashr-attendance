package com.fesco.saashr.web.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangXingYu
 * @date 2018-01-04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJsonUtil {

    @Test
    public void testObject() {
        TestPerson testPerson = new TestPerson();
        testPerson.setId(1);
        testPerson.setName("张三");
        testPerson.setAge(23);
        testPerson.setGender(1);

        TestPerson child = new TestPerson();
        child.setId(2);
        child.setName("李四-child");
        child.setAge(2);
        child.setGender(1);

        List<TestPerson> children = new ArrayList<>();
        children.add(child);
        testPerson.setChildren(children);

        String testPersonsJson = JsonUtil.object2json(testPerson);
        System.out.println(testPersonsJson);
        //{"age":23,"children":[{"age":2,"children":[],"gender":1,"id":2,"name":"李四 child"}],"gender":1,"id":1,"name":"张三"}
        TestPerson testPerson2 = (TestPerson) JsonUtil.json2object(testPersonsJson, TestPerson.class);
        System.out.println(testPerson2.toString());
        //TestPerson {Hash = 535600435, id=1, name=张三, age=23, gender=1, children=[TestPerson {Hash = 57866172, id=2, name=李四 child, age=2, gender=1, children=[]}]}
    }

    @Test
    public void testArray() {
        TestPerson[] testPersons = new TestPerson[10];
        TestPerson testPerson;
        TestPerson child;
        for (int i = 0; i < testPersons.length; i++) {
            testPerson = new TestPerson();
            testPerson.setId(i);
            testPerson.setName("张三" + i);
            testPerson.setAge(20 + i);
            testPerson.setGender(i > 5 ? 1 : 0);

            child = new TestPerson();
            child.setId(100 + i);
            child.setName("李四" + i + "-child");
            child.setAge(i);
            child.setGender(i < 5 ? 1 : 0);

            List<TestPerson> children = new ArrayList<>(1);
            children.add(child);
            testPerson.setChildren(children);

            testPersons[i] = testPerson;
        }

        String testPersonsJson = JsonUtil.array2json(testPersons);
        System.out.println(testPersonsJson);
        //        [{"age":20,"children":[{"age":0,"gender":1,"id":100,"name":"李四 0-child"}],"gender":0,"id":0,"name":"张三0"},
        //        {"age":21,"children":[{"age":1,"gender":1,"id":101,"name":"李四 1-child"}],"gender":0,"id":1,"name":"张三1"},
        //        {"age":22,"children":[{"age":2,"gender":1,"id":102,"name":"李四 2-child"}],"gender":0,"id":2,"name":"张三2"},
        //        {"age":23,"children":[{"age":3,"gender":1,"id":103,"name":"李四 3-child"}],"gender":0,"id":3,"name":"张三3"},
        //        {"age":24,"children":[{"age":4,"gender":1,"id":104,"name":"李四 4-child"}],"gender":0,"id":4,"name":"张三4"},
        //        {"age":25,"children":[{"age":5,"gender":0,"id":105,"name":"李四 5-child"}],"gender":0,"id":5,"name":"张三5"},
        //        {"age":26,"children":[{"age":6,"gender":0,"id":106,"name":"李四 6-child"}],"gender":1,"id":6,"name":"张三6"},
        //        {"age":27,"children":[{"age":7,"gender":0,"id":107,"name":"李四 7-child"}],"gender":1,"id":7,"name":"张三7"},
        //        {"age":28,"children":[{"age":8,"gender":0,"id":108,"name":"李四 8-child"}],"gender":1,"id":8,"name":"张三8"},
        //        {"age":29,"children":[{"age":9,"gender":0,"id":109,"name":"李四 9-child"}],"gender":1,"id":9,"name":"张三9"}]
        TestPerson[] testPersons2 = new TestPerson[]{};
        testPersons2 = JsonUtil.json2array(testPersonsJson, TestPerson.class, testPersons2);
        for (TestPerson person : testPersons2) {
            System.out.println(person);
            //            TestPerson {Hash = 60314053, id=0, name=张三0, age=20, gender=0, children=[TestPerson {Hash = 746247411, id=100, name=李四 0-child, age=0, gender=1, children=null}]}
            //            TestPerson {Hash = 110687402, id=1, name=张三1, age=21, gender=0, children=[TestPerson {Hash = 1037732398, id=101, name=李四 1-child, age=1, gender=1, children=null}]}
            //            TestPerson {Hash = 2081284649, id=2, name=张三2, age=22, gender=0, children=[TestPerson {Hash = 111832087, id=102, name=李四 2-child, age=2, gender=1, children=null}]}
            //            TestPerson {Hash = 1196877260, id=3, name=张三3, age=23, gender=0, children=[TestPerson {Hash = 775199986, id=103, name=李四 3-child, age=3, gender=1, children=null}]}
            //            TestPerson {Hash = 1035681719, id=4, name=张三4, age=24, gender=0, children=[TestPerson {Hash = 1755588291, id=104, name=李四 4-child, age=4, gender=1, children=null}]}
            //            TestPerson {Hash = 405587614, id=5, name=张三5, age=25, gender=0, children=[TestPerson {Hash = 318052965, id=105, name=李四 5-child, age=5, gender=0, children=null}]}
            //            TestPerson {Hash = 1985468683, id=6, name=张三6, age=26, gender=1, children=[TestPerson {Hash = 1057778492, id=106, name=李四 6-child, age=6, gender=0, children=null}]}
            //            TestPerson {Hash = 1097482869, id=7, name=张三7, age=27, gender=1, children=[TestPerson {Hash = 2079743503, id=107, name=李四 7-child, age=7, gender=0, children=null}]}
            //            TestPerson {Hash = 857825275, id=8, name=张三8, age=28, gender=1, children=[TestPerson {Hash = 1864931112, id=108, name=李四 8-child, age=8, gender=0, children=null}]}
            //            TestPerson {Hash = 2084706642, id=9, name=张三9, age=29, gender=1, children=[TestPerson {Hash = 890054387, id=109, name=李四 9-child, age=9, gender=0, children=null}]}
        }
    }

    @Test
    public void testList() {
        List<TestPerson> testPersons = new ArrayList<>(10);
        TestPerson testPerson;
        TestPerson child;
        for (int i = 0; i < 10; i++) {
            testPerson = new TestPerson();
            testPerson.setId(i);
            testPerson.setName("张三" + i);
            testPerson.setAge(20 + i);
            testPerson.setGender(i > 5 ? 1 : 0);

            child = new TestPerson();
            child.setId(100 + i);
            child.setName("李四" + i + "-child");
            child.setAge(i);
            child.setGender(i < 5 ? 1 : 0);

            List<TestPerson> children = new ArrayList<>(1);
            children.add(child);
            testPerson.setChildren(children);

            testPersons.add(testPerson);
        }

        String testPersonsJson = JsonUtil.list2json(testPersons);
        System.out.println(testPersonsJson);
        //        [{"age":20,"children":[{"age":0,"gender":1,"id":100,"name":"李四0-child"}],"gender":0,"id":0,"name":"张三0"},
        //        {"age":21,"children":[{"age":1,"gender":1,"id":101,"name":"李四1-child"}],"gender":0,"id":1,"name":"张三1"},
        //        {"age":22,"children":[{"age":2,"gender":1,"id":102,"name":"李四2-child"}],"gender":0,"id":2,"name":"张三2"},
        //        {"age":23,"children":[{"age":3,"gender":1,"id":103,"name":"李四3-child"}],"gender":0,"id":3,"name":"张三3"},
        //        {"age":24,"children":[{"age":4,"gender":1,"id":104,"name":"李四4-child"}],"gender":0,"id":4,"name":"张三4"},
        //        {"age":25,"children":[{"age":5,"gender":0,"id":105,"name":"李四5-child"}],"gender":0,"id":5,"name":"张三5"},
        //        {"age":26,"children":[{"age":6,"gender":0,"id":106,"name":"李四6-child"}],"gender":1,"id":6,"name":"张三6"},
        //        {"age":27,"children":[{"age":7,"gender":0,"id":107,"name":"李四7-child"}],"gender":1,"id":7,"name":"张三7"},
        //        {"age":28,"children":[{"age":8,"gender":0,"id":108,"name":"李四8-child"}],"gender":1,"id":8,"name":"张三8"},
        //        {"age":29,"children":[{"age":9,"gender":0,"id":109,"name":"李四9-child"}],"gender":1,"id":9,"name":"张三9"}]
        List<TestPerson> testPersons2 = JsonUtil.json2list(testPersonsJson, TestPerson.class);
        for (TestPerson person : testPersons2) {
            System.out.println(person.toString());
            //            TestPerson {Hash = 746247411, id=0, name=张三0, age=20, gender=0, children=[TestPerson {Hash = 110687402, id=100, name=李四0-child, age=0, gender=1, children=null}]}
            //            TestPerson {Hash = 1037732398, id=1, name=张三1, age=21, gender=0, children=[TestPerson {Hash = 2081284649, id=101, name=李四1-child, age=1, gender=1, children=null}]}
            //            TestPerson {Hash = 111832087, id=2, name=张三2, age=22, gender=0, children=[TestPerson {Hash = 1196877260, id=102, name=李四2-child, age=2, gender=1, children=null}]}
            //            TestPerson {Hash = 775199986, id=3, name=张三3, age=23, gender=0, children=[TestPerson {Hash = 1035681719, id=103, name=李四3-child, age=3, gender=1, children=null}]}
            //            TestPerson {Hash = 1755588291, id=4, name=张三4, age=24, gender=0, children=[TestPerson {Hash = 405587614, id=104, name=李四4-child, age=4, gender=1, children=null}]}
            //            TestPerson {Hash = 318052965, id=5, name=张三5, age=25, gender=0, children=[TestPerson {Hash = 1985468683, id=105, name=李四5-child, age=5, gender=0, children=null}]}
            //            TestPerson {Hash = 1057778492, id=6, name=张三6, age=26, gender=1, children=[TestPerson {Hash = 1097482869, id=106, name=李四6-child, age=6, gender=0, children=null}]}
            //            TestPerson {Hash = 2079743503, id=7, name=张三7, age=27, gender=1, children=[TestPerson {Hash = 857825275, id=107, name=李四7-child, age=7, gender=0, children=null}]}
            //            TestPerson {Hash = 1864931112, id=8, name=张三8, age=28, gender=1, children=[TestPerson {Hash = 2084706642, id=108, name=李四8-child, age=8, gender=0, children=null}]}
            //            TestPerson {Hash = 890054387, id=9, name=张三9, age=29, gender=1, children=[TestPerson {Hash = 208300334, id=109, name=李四9-child, age=9, gender=0, children=null}]}
        }
    }

    @Test
    public void testMap() {
        TestPerson testPerson = new TestPerson();
        testPerson.setId(1);
        testPerson.setName("张三");
        testPerson.setAge(23);
        testPerson.setGender(1);

        TestPerson child = new TestPerson();
        child.setId(2);
        child.setName("李四-child");
        child.setAge(2);
        child.setGender(1);

        List<TestPerson> children = new ArrayList<>();
        children.add(child);
        testPerson.setChildren(children);

        Map<String, TestPerson> map1 = new HashMap<>();
        map1.put("key", testPerson);
        String testPersonJson = JsonUtil.map2json(map1);
        System.out.println(testPersonJson);
        //{"key1":{"age":23,"children":[{"age":2,"gender":1,"id":2,"name":"李四-child"}],"gender":1,"id":1,"name":"张三"}}
        Map map2 = JsonUtil.json2map(testPersonJson);
        System.out.println(map2.get("key"));
        //{"gender":1,"children":[{"gender":1,"name":"李四-child","id":2,"age":2}],"name":"张三","id":1,"age":23}
    }
}