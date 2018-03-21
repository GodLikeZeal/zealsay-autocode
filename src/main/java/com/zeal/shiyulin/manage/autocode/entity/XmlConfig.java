/**
 * Copyright &copy; 2012-2014 zealAll rights reserved.
 */
package com.zeal.shiyulin.manage.autocode.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * 生成方案Entity
 * @author zeal
 * @version 2013-10-15
 */
@XmlRootElement(name="config")
public class XmlConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<XmlCategory> categoryList;	// 代码模板分类
	private List<Dict> javaTypeList;		// Java类型
	private List<Dict> queryTypeList;		// 查询类型
	private List<Dict> showTypeList;		// 显示类型

	public XmlConfig() {
		super();
	}

	@XmlElementWrapper(name = "category")
	@XmlElement(name = "category")
	public List<XmlCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<XmlCategory> categoryList) {
		this.categoryList = categoryList;
	}

	@XmlElementWrapper(name = "javaType")
	@XmlElement(name = "dict")
	public List<Dict> getJavaTypeList() {
		return javaTypeList;
	}

	public void setJavaTypeList(List<Dict> javaTypeList) {
		this.javaTypeList = javaTypeList;
	}

	@XmlElementWrapper(name = "queryType")
	@XmlElement(name = "dict")
	public List<Dict> getQueryTypeList() {
		return queryTypeList;
	}

	public void setQueryTypeList(List<Dict> queryTypeList) {
		this.queryTypeList = queryTypeList;
	}

	@XmlElementWrapper(name = "showType")
	@XmlElement(name = "dict")
	public List<Dict> getShowTypeList() {
		return showTypeList;
	}

	public void setShowTypeList(List<Dict> showTypeList) {
		this.showTypeList = showTypeList;
	}
	
}