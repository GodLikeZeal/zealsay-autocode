package com.zeal.shiyulin.common.utils; /**
 * Copyright &copy; 2012-2014 zealAll rights reserved.
 */


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * FreeMarkers工具类
 * 
 * @author zeal
 * @version 2013-01-15
 */
public class FreeMarkers {
	public static String renderString(String templateString,
			Map<String, ?> model) {
		try {
			StringWriter result = new StringWriter();
			Template t = new Template("name", new StringReader(templateString),
					new Configuration());
			t.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static String renderTemplate(Template template, Object model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static Configuration buildConfiguration(String directory)
			throws IOException {
		Configuration cfg = new Configuration();
		Resource path = new DefaultResourceLoader().getResource(directory);
		cfg.setDirectoryForTemplateLoading(path.getFile());
		return cfg;
	}

	public static void generatorFile(ServletContext context,
			Map<String, Object> map, String templatePath, String templateName,
			String htmlFile) throws IOException, TemplateException {
		Configuration freemarkerCfg = new Configuration();
		// 设置要解析的模板所在的目录，并加载模板文件
		freemarkerCfg
				.setServletContextForTemplateLoading(context, templatePath);
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");

		// 获取模板,并设置编码方式
		Template template = freemarkerCfg.getTemplate(templateName);
		template.setEncoding("UTF-8");
		
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(htmlFile), "UTF-8"));

		// 合并数据模型与模板
		template.process(map, out);

		out.flush();
		out.close();
	}

}
