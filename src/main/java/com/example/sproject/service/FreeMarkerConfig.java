package com.example.sproject.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class FreeMarkerConfig {
    private static Configuration cfg;
    private static ServletContext sc;

    public static void setServletContext(ServletContext servletContext) {
        sc = servletContext;
    }

    public static Configuration getCfg() {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setServletContextForTemplateLoading(
                    sc, "/WEB-INF/templates");
        }
        return cfg;
    }

    public static void render(HttpServletRequest request,
                              HttpServletResponse response,
                              String path,
                              Map<String, Object> root) throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        Configuration cfg = FreeMarkerConfig.getCfg();
        Template tmpl = cfg.getTemplate(path);
        try {
            response.setContentType("text/html");
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
