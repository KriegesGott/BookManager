package edu.xtu.library.common.utils;

public class DepartmentToType {

	public static String convert(String department){
		if (department.equals("机械工程学院")){
			return "机械、仪表工业";
		}else if (department.equals("土木工程与力学学院")){
			return "交通运输";
		}
		else if (department.equals("马克思主义学院")){
			return "马克思主义、列宁主义、毛泽东思想、邓小平";
		}
		else if (department.equals("商学院")){
			return "经济";
		}
		else if (department.equals("数学与计算科学院")){
			return "数理科学和化学";
		}
		else if (department.equals("物理与光电工程学院")){
			return "自然科学总论";
		}
		else if (department.equals("公共管理学院")){
			return "社会科学总论";
		}
		else if (department.equals("文学与新闻学院")){
			return "文学";
		}
		else if (department.equals("外国语学院")){
			return "语言、文字";
		}
		else if (department.equals("化学学院")){
			return "化学工业";
		}
		else if (department.equals("化工学院")){
			return "化学工业";
		}
		else if (department.equals("材料科学与工程学院")){
			return "自然科学总论";
		}
		else if (department.equals("环境与资源学院")){
			return "环境科学、安全科学";
		}
		else if (department.equals("碧泉书院·哲学与历史文化学院")){
			return "历史、地理";
		}
		else if (department.equals("自动化与电子信息学院")){
			return "无线电电子学、电信技术";
		}
		else if (department.equals("计算机学院·网络空间安全学院")){
			return "自动化技术、计算机技术";
		}
		else {
			return "政治、法律";
		}
	}
}
