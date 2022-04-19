package com.stla.figure.deploy;

import com.alibaba.fastjson.JSONObject;
import com.stla.figure.bean.xdUser;
import com.stla.figure.utility.AESUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class TokemInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${loginTimRedis}")
    private Integer loginTimRedis;

    Logger log = LoggerFactory.getLogger(TokemInterceptor.class);

    //存放鉴权信息的Header名称，默认是Authorization
    private String Authorization = "token";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    /**
     * 存放用户名称和对应的key
     */
    public static final String USER_KEY = "USER_KEY";

    public static final String TOKEN = "TOKEN";

    public static final String MESSAGE  = "MESSAGE";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //验证token
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {
            //String token = request.getParameter(Authorization);
            String token = request.getHeader(Authorization);
            log.info("获取到的token为: {} ", token);
            String username = null;
            if (token != null && token.length() != 0) {
                //从redis中根据键token来获取绑定的username
                username = redisTemplate.opsForValue().get(token);
                log.info("从redis中获取的用户名称为: {}", username);
            }
            //判断username不为空的时候
            if (username != null && !username.trim().equals("")) {
                //重新设置Redis中的token过期时间
                Long expire = redisTemplate.getExpire(token);
                if (expire < 600){
                    redisTemplate.opsForValue().set(token, username, loginTimRedis, TimeUnit.SECONDS);
                    log.info("重置成功!");
                }

                //解析并注入用户信息
                String userBean = AESUtils.Decrypt(username.replace("\"", ""), "stladjmjszyzancc");
                Integer replace =Integer.parseInt(userBean.substring(0,userBean.indexOf(",")));
                request.setAttribute(USER_KEY,replace);
                request.setAttribute(MESSAGE,username);
                request.setAttribute(TOKEN,token);
                return true;
            } else {
                JSONObject jsonObject = new JSONObject();
                PrintWriter out = null;
                try {
                    response.setStatus(unauthorizedErrorCode);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    jsonObject.put("code", ((HttpServletResponse) response).getStatus());
                    //鉴权失败后返回的错误信息，默认为401 unauthorized
                    jsonObject.put("message", "The login timeout");
                    out = response.getWriter();
                    out.println(jsonObject);

                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                }

            }

        }

        request.setAttribute(USER_KEY, null);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
