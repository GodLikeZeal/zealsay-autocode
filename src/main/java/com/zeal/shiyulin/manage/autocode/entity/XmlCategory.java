/**
 * Copyright &copy; 2012-2014 zealAll rights reserved.
 */
package com.zeal.shiyulin.manage.autocode.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * 生成方案Entity
 * 
 * @author zeal
 * @version 2013-10-15
 */
@XmlRootElement(name = "category")
public class XmlCategory extends Dict{

	private static final long serialVersionUID = 1L;
	private List<String> template; // 主表模板
	private List<String> childTableTemplate;// 子表模板

	private String value; // 数据值

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String CATEGORY_REF = "category-ref:";

	public XmlCategory() {
		super();
	}

	@XmlElement(name = "template")
	public List<String> getTemplate() {
		return template;
	}

	public void setTemplate(List<String> template) {
		this.template = template;
	}

	@XmlElementWrapper(name = "childTable")
	@XmlElement(name = "template")
	public List<String> getChildTableTemplate() {
		return childTableTemplate;
	}

	public void setChildTableTemplate(List<String> childTableTemplate) {
		this.childTableTemplate = childTableTemplate;
	}

}
