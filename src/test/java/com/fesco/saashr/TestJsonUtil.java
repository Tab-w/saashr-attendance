package com.fesco.saashr;

import com.fesco.saashr.web.model.Person;
import com.fesco.saashr.web.util.JsonUtil;
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
        Person person = new Person();
        person.setId(1);
        person.setName("James陈");
        person.setAge(23);
        person.setGender(1);

        Person child = new Person();
        child.setId(2);
        child.setName("James陈 儿子");
        child.setAge(2);
        child.setGender(1);

        List<Person> children = new ArrayList<>();
        children.add(child);
        person.setChildren(children);

        String personsJson = JsonUtil.object2json(person);
        System.out.println(personsJson);
        //{"age":23,"children":[{"age":2,"children":[],"gender":1,"id":2,"name":"James陈 儿子"}],"gender":1,"id":1,"name":"James陈"}
        Person person2 = (Person) JsonUtil.json2object(personsJson, Person.class);
        System.out.println(person2.toString());
        //Person{id=1, name='James陈', age=23, gender=1, children=[Person{id=2, name='James陈 儿子', age=2, gender=1, children=[]}]}
    }

    @Test
    public void testArray() {
        Person[] persons = new Person[10];
        Person person;
        Person child;
        for (int i = 0; i < persons.length; i++) {
            person = new Person();
            person.setId(i);
            person.setName("James陈" + i);
            person.setAge(20 + i);
            person.setGender(i > 5 ? 1 : 0);

            child = new Person();
            child.setId(100 + i);
            child.setName("James陈 " + i + "儿子");
            child.setAge(i);
            child.setGender(i < 5 ? 1 : 0);

            List<Person> children = new ArrayList<>(1);
            children.add(child);
            person.setChildren(children);

            persons[i] = person;
        }

        String personsJson = JsonUtil.array2json(persons);
        System.out.println(personsJson);
        //[{"age":20,"children":[{"age":0,"gender":1,"id":100,"name":"James陈 0儿子"}],"gender":0,"id":0,"name":"James陈0"},{"age":21,"children":[{"age":1,"gender":1,"id":101,"name":"James陈 1儿子"}],"gender":0,"id":1,"name":"James陈1"},{"age":22,"children":[{"age":2,"gender":1,"id":102,"name":"James陈 2儿子"}],"gender":0,"id":2,"name":"James陈2"},{"age":23,"children":[{"age":3,"gender":1,"id":103,"name":"James陈 3儿子"}],"gender":0,"id":3,"name":"James陈3"},
        // {"age":24,"children":[{"age":4,"gender":1,"id":104,"name":"James陈 4儿子"}],"gender":0,"id":4,"name":"James陈4"},{"age":25,"children":[{"age":5,"gender":0,"id":105,"name":"James陈 5儿子"}],"gender":0,"id":5,"name":"James陈5"},{"age":26,"children":[{"age":6,"gender":0,"id":106,"name":"James陈 6儿子"}],"gender":1,"id":6,"name":"James陈6"},{"age":27,"children":[{"age":7,"gender":0,"id":107,"name":"James陈 7儿子"}],"gender":1,"id":7,"name":"James陈7"},
        // {"age":28,"children":[{"age":8,"gender":0,"id":108,"name":"James陈 8儿子"}],"gender":1,"id":8,"name":"James陈8"},{"age":29,"children":[{"age":9,"gender":0,"id":109,"name":"James陈 9儿子"}],"gender":1,"id":9,"name":"James陈9"}]
        Person[] persons2 = new Person[]{};
        persons2 = JsonUtil.json2array(personsJson, Person.class, persons2);
        for (int i = 0; i < persons2.length; i++) {
            System.out.println(persons2[i].toString());
            //Person{id=0, name='James陈0', age=20, gender=0, children=[Person{id=100, name='James陈 0儿子', age=0, gender=1, children=null}]}
            //Person{id=1, name='James陈1', age=21, gender=0, children=[Person{id=101, name='James陈 1儿子', age=1, gender=1, children=null}]}
            //Person{id=2, name='James陈2', age=22, gender=0, children=[Person{id=102, name='James陈 2儿子', age=2, gender=1, children=null}]}
            //Person{id=3, name='James陈3', age=23, gender=0, children=[Person{id=103, name='James陈 3儿子', age=3, gender=1, children=null}]}
            //Person{id=4, name='James陈4', age=24, gender=0, children=[Person{id=104, name='James陈 4儿子', age=4, gender=1, children=null}]}
            //Person{id=5, name='James陈5', age=25, gender=0, children=[Person{id=105, name='James陈 5儿子', age=5, gender=0, children=null}]}
            //Person{id=6, name='James陈6', age=26, gender=1, children=[Person{id=106, name='James陈 6儿子', age=6, gender=0, children=null}]}
            //Person{id=7, name='James陈7', age=27, gender=1, children=[Person{id=107, name='James陈 7儿子', age=7, gender=0, children=null}]}
            //Person{id=8, name='James陈8', age=28, gender=1, children=[Person{id=108, name='James陈 8儿子', age=8, gender=0, children=null}]}
            //Person{id=9, name='James陈9', age=29, gender=1, children=[Person{id=109, name='James陈 9儿子', age=9, gender=0, children=null}]}
        }
    }

    @Test
    public void testList() {
        List<Person> persons = new ArrayList<>(10);
        Person person;
        Person child;
        for (int i = 0; i < 10; i++) {
            person = new Person();
            person.setId(i);
            person.setName("James陈" + i);
            person.setAge(20 + i);
            person.setGender(i > 5 ? 1 : 0);

            child = new Person();
            child.setId(100 + i);
            child.setName("James陈 " + i + "儿子");
            child.setAge(i);
            child.setGender(i < 5 ? 1 : 0);

            List<Person> children = new ArrayList<>(1);
            children.add(child);
            person.setChildren(children);

            persons.add(person);
        }

        String personsJson = JsonUtil.list2json(persons);
        System.out.println(personsJson);
        //[{"age":20,"children":[{"age":0,"gender":1,"id":100,"name":"James陈 0儿子"}],"gender":0,"id":0,"name":"James陈0"},{"age":21,"children":[{"age":1,"gender":1,"id":101,"name":"James陈 1儿子"}],"gender":0,"id":1,"name":"James陈1"},{"age":22,"children":[{"age":2,"gender":1,"id":102,"name":"James陈 2儿子"}],"gender":0,"id":2,"name":"James陈2"},{"age":23,"children":[{"age":3,"gender":1,"id":103,"name":"James陈 3儿子"}],"gender":0,"id":3,"name":"James陈3"},
        // {"age":24,"children":[{"age":4,"gender":1,"id":104,"name":"James陈 4儿子"}],"gender":0,"id":4,"name":"James陈4"},{"age":25,"children":[{"age":5,"gender":0,"id":105,"name":"James陈 5儿子"}],"gender":0,"id":5,"name":"James陈5"},{"age":26,"children":[{"age":6,"gender":0,"id":106,"name":"James陈 6儿子"}],"gender":1,"id":6,"name":"James陈6"},{"age":27,"children":[{"age":7,"gender":0,"id":107,"name":"James陈 7儿子"}],"gender":1,"id":7,"name":"James陈7"},
        // {"age":28,"children":[{"age":8,"gender":0,"id":108,"name":"James陈 8儿子"}],"gender":1,"id":8,"name":"James陈8"},{"age":29,"children":[{"age":9,"gender":0,"id":109,"name":"James陈 9儿子"}],"gender":1,"id":9,"name":"James陈9"}]
        List<Person> persons2 = JsonUtil.json2list(personsJson, Person.class);
        for (int i = 0; i < persons2.size(); i++) {
            System.out.println(persons2.get(i).toString());
            //Person{id=0, name='James陈0', age=20, gender=0, children=[Person{id=100, name='James陈 0儿子', age=0, gender=1, children=null}]}
            //Person{id=1, name='James陈1', age=21, gender=0, children=[Person{id=101, name='James陈 1儿子', age=1, gender=1, children=null}]}
            //Person{id=2, name='James陈2', age=22, gender=0, children=[Person{id=102, name='James陈 2儿子', age=2, gender=1, children=null}]}
            //Person{id=3, name='James陈3', age=23, gender=0, children=[Person{id=103, name='James陈 3儿子', age=3, gender=1, children=null}]}
            //Person{id=4, name='James陈4', age=24, gender=0, children=[Person{id=104, name='James陈 4儿子', age=4, gender=1, children=null}]}
            //Person{id=5, name='James陈5', age=25, gender=0, children=[Person{id=105, name='James陈 5儿子', age=5, gender=0, children=null}]}
            //Person{id=6, name='James陈6', age=26, gender=1, children=[Person{id=106, name='James陈 6儿子', age=6, gender=0, children=null}]}
            //Person{id=7, name='James陈7', age=27, gender=1, children=[Person{id=107, name='James陈 7儿子', age=7, gender=0, children=null}]}
            //Person{id=8, name='James陈8', age=28, gender=1, children=[Person{id=108, name='James陈 8儿子', age=8, gender=0, children=null}]}
            //Person{id=9, name='James陈9', age=29, gender=1, children=[Person{id=109, name='James陈 9儿子', age=9, gender=0, children=null}]}
        }
    }

    @Test
    public void testMap() {
        Person person = new Person();
        person.setId(1);
        person.setName("James陈");
        person.setAge(23);
        person.setGender(1);

        Person child = new Person();
        child.setId(2);
        child.setName("James陈 儿子");
        child.setAge(2);
        child.setGender(1);

        List<Person> children = new ArrayList<Person>();
        children.add(child);
        person.setChildren(children);

        Map<String, Person> map1 = new HashMap<String, Person>();
        map1.put("key1", person);
        String personJson = JsonUtil.map2json(map1);
        System.out.println(personJson);
        //{"key1":{"age":23,"children":[{"age":2,"gender":1,"id":2,"name":"James陈 儿子"}],"gender":1,"id":1,"name":"James陈"}}
        Map map2 = JsonUtil.json2map(personJson);
        System.out.println(map2.get("key1"));
        //{"gender":1,"children":[{"gender":1,"name":"James陈 儿子","id":2,"age":2}],"name":"James陈","id":1,"age":23}
    }
}