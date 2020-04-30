package cn.edu.tsu.interceptor;

/**
 * @author Gregorio
 * @date 2020/4/29 10:00
 */

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * feign拦截器
 * 在进行 feign 请求 Http 服务时需要携带 token 信息
 * 否则将没有权限访问
 *
 * 该拦截器就是将token写入feign请求头中，让feign也可以携token
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {


        //获取request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        //设置请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                requestTemplate.header(name,value);
            }
        }

        //设置请求体，这里主要为了传递 token
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder body =new StringBuilder();
        if (parameterNames != null) {
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = request.getParameter(name);

                //将 token 加入请求头
                if("access_token".equals(name)){
                    requestTemplate.header("authorization", "Bearer " + value);

                }

                //其他加入请求体
                else {
                    body.append(name).append('=').append(value).append('&');
                }
            }
        }

        //设置请求体
        if(body.length()>0){
            //去掉最后一位 &
            body.deleteCharAt(body.length()-1);
            requestTemplate.body(Request.Body.bodyTemplate(body.toString(), Charset.defaultCharset()));
        }


    }
}
