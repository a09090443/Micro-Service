package com.localhost.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应Response工具类
 */
public class ResponseUtils {

    /**
     * 响应Http请求
     * @param response
     * @param resData
     * @throws JsonProcessingException
     */
    public static void responseData(HttpServletResponse response, Object resData) throws JsonProcessingException {
        //将实体对象转换为jackson Object转换
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(resData);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
