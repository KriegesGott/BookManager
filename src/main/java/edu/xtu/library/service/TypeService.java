package edu.xtu.library.service;

import javax.annotation.Resource;

import edu.xtu.library.dao.TypeDao;

import org.springframework.stereotype.Service;

@Service
public class TypeService {

	@Resource
	private TypeDao typeDao;

	public String getName(String code){
		String str = "";
		if (code.charAt(0) == 'T'){
			str = code.substring(0, 2);
		}else{
			str = code.substring(0, 1);
		}
		return typeDao.selectNameByCode(str);
	}

}
