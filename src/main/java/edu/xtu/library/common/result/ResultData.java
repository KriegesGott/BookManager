package edu.xtu.library.common.result;

import edu.xtu.library.common.exception.ProjectException;
import lombok.Data;

@Data
public class ResultData<T> {

	private int status;
	private String message;
	private T data;

	public static ResultData<String> success(){
		ResultData<String> resultData = new ResultData<>();
		resultData.setStatus(200);
		resultData.setMessage("success");
		return resultData;
	}

	public ResultData<T> success(T data){
		ResultData<T> resultData = new ResultData<>();
		resultData.setStatus(200);
		resultData.setMessage("success");
		resultData.setData(data);
		return resultData;
	}

	public static ResultData<String> fail(){
		ResultData<String> resultData = new ResultData<>();
		resultData.setStatus(500);
		resultData.setMessage("系统错误");
		return resultData;
	}

	public static ResultData<String> fail(ProjectException e){
		ResultData<String> resultData = new ResultData<>();
		resultData.setStatus(e.getCode());
		resultData.setMessage(e.getMessage());
		return resultData;
	}
}
