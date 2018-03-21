package com.zeal.shiyulin.common.service; /**
 * Copyright &copy; 2012-2014 zealAll rights reserved.
 */



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service基类
 * @author zeal
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class BaseService {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

}
