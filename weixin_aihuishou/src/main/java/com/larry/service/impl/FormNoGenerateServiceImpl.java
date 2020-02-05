package com.larry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larry.consts.FormNumConstants;
import com.larry.service.FormNoGenerateService;
import com.larry.utils.FormNumSerialUtil;
import com.larry.utils.FormNumTypeEnum;
import com.larry.utils.RedisUtil;
@Service
public class FormNoGenerateServiceImpl implements FormNoGenerateService{
	@Autowired
    private RedisUtil redisutil;


	public String generateFormNo(FormNumTypeEnum formNoTypeEnum) {
		 String formNoPrefix = FormNumSerialUtil.getFormNumPrefix(formNoTypeEnum);
		 String cacheKey = FormNumSerialUtil.getCacheKey(formNoPrefix);
		 Long incrementalSerial = redisutil.incr(cacheKey, 1);
		 redisutil.expire(cacheKey, FormNumConstants.DEFAULT_CACHE_DAYS);

		 String serialWithPrefix = FormNumSerialUtil
	                .completionSerial(formNoPrefix, incrementalSerial, formNoTypeEnum);

		return FormNumSerialUtil.completionRandom(serialWithPrefix, formNoTypeEnum);

	}

}
