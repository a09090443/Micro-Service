package com.zipe.utils.velocity;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public final class VelocityUtil {
	public static VelocityEngine ve;

	/**
	 * @param path
	 * @throws Exception
	 */
	public VelocityUtil() {
		Properties p = new Properties();
		p.put("resource.loader", "file");
		p.put(Velocity.INPUT_ENCODING, "utf-8");
		p.put(Velocity.OUTPUT_ENCODING, "utf-8");
		p.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		p.put("file.resource.loader.path", "/home/zipe");
		if (null == ve) {
			ve = new VelocityEngine();
			ve.init(p);
		}
	}

	/**
	 * @param tmplateFilePath
	 */
	public VelocityUtil(String tmplateFilePath) {
		Properties p = new Properties();
		p.put("resource.loader", "file");
		p.put(Velocity.INPUT_ENCODING, "utf-8");
		p.put(Velocity.OUTPUT_ENCODING, "utf-8");
		p.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
		p.put("file.resource.loader.path", tmplateFilePath);

		if (null == ve) {
			ve = new VelocityEngine();
			ve.init(p);
		}
	}

	/**
	 * @param templateName
	 * @param outputFile
	 * @param map
	 * @throws Exception
	 */
	public static void writeTemplateOutput(String templateName, String outputFile, Map<String, Object> map)
			throws Exception {
		try {
			Template template = ve.getTemplate(templateName);

			VelocityContext context = new VelocityContext();
			Set<String> keys = map.keySet();
			for (String key : keys) {
				context.put(key, map.get(key));
			}

			StringWriter writer = new StringWriter();
			template.merge(context, writer);

			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"), 1024 * 1024);
				bw.write(writer.toString());
				bw.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bw != null) {
					bw.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != ve) {
				ve = null;
			}
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		VelocityUtil createHtml = new VelocityUtil("/home/zipe");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("requestId", "GPC00001");
		map.put("fileName", "222");
		map.put("startTime", "333");
		map.put("callBackWsdl", "4444");

		try {
			createHtml.writeTemplateOutput("RF.xml", "/home/zipe/test.xml", map);

			VelocityUtil createHtml2 = new VelocityUtil("/home/zipe/tmp");

			createHtml2.writeTemplateOutput("RF.xml", "/home/zipe/test2.xml", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}