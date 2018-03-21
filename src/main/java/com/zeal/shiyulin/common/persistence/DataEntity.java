package com.zeal.shiyulin.common.persistence; /**
 * Copyright &copy; 2012-2014 zealAll rights reserved.
 */



import com.zeal.shiyulin.common.utils.IdGen;
import org.hibernate.validator.constraints.Length;


/**
 * 数据Entity类
 * 
 * @author zeal
 * @version 2014-05-16
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;

	protected String remarks; // 备注

	public DataEntity() {
		super();
	}

	public DataEntity(String id) {
		super(id);
	}

	@Length(min = 0, max = 255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert() {
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord) {
			setId(IdGen.uuid());
		}
	}

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate() {

	}

}
