package edu.xtu.library.common.utils;

public class DepartmentToType {

	public static String convert(String department){
		if (department.equals("机械工程学院")){
			return "机械";
		}else if (department.equals("土木工程与力学学院")){
			return "土木";
		}
		else if (department.equals("马克思主义学院")){
			return "政治";
		}
		else if (department.equals("商学院")){
			return "商务";
		}
		else if (department.equals("数学与计算科学院")){
			return "数学";
		}
		else if (department.equals("物理与光电工程学院")){
			return "物理";
		}
		else if (department.equals("公共管理学院")){
			return "管理";
		}
		else if (department.equals("文学与新闻学院")){
			return "文学";
		}
		else if (department.equals("外国语学院")){
			return "外语";
		}
		else if (department.equals("化学学院")){
			return "化学";
		}
		else if (department.equals("化工学院")){
			return "化学";
		}
		else if (department.equals("材料科学与工程学院")){
			return "材料";
		}
		else if (department.equals("环境与资源学院")){
			return "环境";
		}
		else if (department.equals("碧泉书院·哲学与历史文化学院")){
			return "政治";
		}
		else if (department.equals("自动化与电子信息学院")){
			return "电子";
		}
		else if (department.equals("计算机学院·网络空间安全学院")){
			return "计算机";
		}
		else {
			return "法律";
		}
	}
}
