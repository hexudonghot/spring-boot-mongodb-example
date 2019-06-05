import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionX {
//        //1.分组计数
//        List<Student> list1= Arrays.asList(
//                new Student(1,"one","zhao"),new Student(2,"one","qian"),new Student(3,"two","sun"));
//        //1.1根据某个属性分组计数
//        Map<String,Long> result1=list1.stream().collect(Collectors.groupingBy(Student::getGroupId,Collectors.counting()));
//        System.out.println(result1);
//        //1.2根据整个实体对象分组计数,当其为String时常使用
//        Map<Student,Long> result2=list1.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//        System.out.println(result2);
//        //1.3根据分组的key值对结果进行排序、放进另一个map中并输出
//        Map<String,Long> xMap=new HashMap<>();
//        result1.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByKey().reversed()) //reversed不生效
//                .forEachOrdered(x->xMap.put(x.getKey(),x.getValue()));
//        System.out.println(xMap);
//
//        //2.分组，并统计其中一个属性值得sum或者avg:id总和
//        Map<String,Integer> result3=list1.stream().collect(
//                Collectors.groupingBy(Student::getGroupId,Collectors.summingInt(Student::getId))
//        );
//        System.out.println(result3);


// 分组统计
        public static void main(String[] args) {
            User user1 = new User("zhangsan", "beijing", 10);
            User user2 = new User("zhangsan", "beijing", 20);
            User user3 = new User("lisi", "shanghai", 30);
            List<User> list = new ArrayList<User>();
            list.add(user1);
            list.add(user2);
            list.add(user3);
            Map<String, List<User>> collect = list.stream().collect(Collectors.groupingBy(e -> fetchGroupKey(e)));



            for (List<User> value : collect.values())
            {

                for (int i=0;i<value.size();i++)
                {
                    System.out.println(value.get(i).getAddress());
                }
            }



                System.out.println(collect);
        }
        public static String fetchGroupKey(User user)
        {
            return user.getName() +"#"+ user.getAddress();
        }

    }
