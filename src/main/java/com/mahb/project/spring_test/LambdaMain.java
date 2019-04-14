package com.mahb.project.spring_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LambdaMain {

	
	private static boolean flag1=true;
	
	public static void main(String[] args) {
		
		List<Student> students = getDataList();
		boolean flag2 = true;
		
		Filter_test filter = student -> {
			
			// 可以访问并修改静态 变量
			flag1 = false;
			
			// 可以访问 但不能修改 外部局部 变量；
			// 在 Lambda 表达式 内部  访问的外部 局部变量 会被隐式 声明为 final类型，因此 只能访问而不能修改；
			//flag2 = false;  编译报错
			flag1 = flag2 && false;
			
			// defaultFunctional();  不能访问接口内的 默认方法；
			
			return flag1 ;
			
		};
		
		//  使用 Lambda 表达式
		List<Student> L1 = filterStudent(students, (student) -> ((Student) student).getName().startsWith("叶")); 
		List<Student> L2 = filterStudent(students, (student) -> ((Student) student).getAge() > 18) ;
		
		// 花括号 块  表示 Lambda 体：
		List<Student> ls1 = filterStudent(students, student -> {
			
			String startString = "叶";
			return ((Student) student).getName().startsWith(startString);
		});
		
		List<Student> ageList2 = filterStudent(students, student ->{
			Random random = new Random(3);
			return random.nextBoolean();
		}); 
		
		
		// 该 静态方法  定义为  private static boolean test(Student student)  直接用 :: 访问，不加括号如下：
		Filter_test filter_test = LambdaMain::test;  
		
		
		LambdaMain lambdaMain =new LambdaMain();
		Filter_test test1 = lambdaMain::lammethod;
		
		
	}
	private boolean lammethod(Student student) {
		 System.out.println("实例方法的 Lambda 引用！" );
		 return true;
	}
	
	private static boolean test(Student student) {
        return student.getAge() > 20;
    }
	
	// 定义方法，接口参数 来进行校验；
	private static List<Student> filterStudent(List<Student> students, Filter_test filter){
		
		List<Student> students2 = new ArrayList<Student>();
		for (Student student : students) {
			if (filter.filter(student)) {
				students2.add(student) ;
			}			
		}		
		return students2;
		
	}
	
	
	
	private static List<Student> getDataList() {
		
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student("leavesC", 9));
        studentList.add(new Student("叶应是叶", 17));
        studentList.add(new Student("叶", 18));
        studentList.add(new Student("叶", 14));
        studentList.add(new Student("叶应是叶", 4));
        studentList.add(new Student("叶应是叶", 17));
        studentList.add(new Student("叶应是叶", 5));
        studentList.add(new Student("leavesC", 13));
        studentList.add(new Student("陈", 0));
        studentList.add(new Student("叶应是叶", 12));
        studentList.add(new Student("leavesC", 15));
        studentList.add(new Student("leavesC", 20));
        studentList.add(new Student("叶应是叶", 8));
        studentList.add(new Student("陈", 1));
        studentList.add(new Student("叶应是叶", 14));
        studentList.add(new Student("叶应是叶", 4));
        studentList.add(new Student("leavesC", 15));
        studentList.add(new Student("叶", 17));
        studentList.add(new Student("叶应是叶", 4));
        studentList.add(new Student("陈", 13));
        studentList.add(new Student("叶应是叶", 20));
        studentList.add(new Student("叶", 10));
        studentList.add(new Student("leavesC", 4));
        studentList.add(new Student("陈", 22));
        studentList.add(new Student("leavesC", 7));
        return studentList;
        
        
    }
}
