package cn.edu.tsu.consumer.controller;

import cn.edu.tsu.common.dto.CommonResult;
import cn.edu.tsu.common.dto.ResponseResult;
import cn.edu.tsu.common.util.MapperUtils;
import cn.edu.tsu.common.util.OkHttpClientUtil;
import cn.edu.tsu.consumer.dto.LoginParam;
import cn.edu.tsu.provider.api.UmsAdminService;
import com.google.common.collect.Maps;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author Gregorio
 * @date 2020/4/28 18:21
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    private static final String URL_OAUTH_TOKEN = "http://localhost:9002/oauth/token";

    @Value("${consumer.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${consumer.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${consumer.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource(name = "userDetailsServiceBean")
    public UserDetailsService userDetailsService;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;

    @Resource
    public TokenStore tokenStore;


    @Reference(version="1.0.0")
    private UmsAdminService umsAdminService;

    @PostMapping(value = "/login")
    public ResponseResult<Map<String,Object>> login(@RequestBody LoginParam loginParam, HttpServletRequest request){
        //封装结果集
        Map<String,Object> result= Maps.newHashMap();
        //验证密码
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if(userDetails==null||!passwordEncoder.matches(loginParam.getPassword(),userDetails.getPassword())){
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL,"failed");
        }

        Map<String,String> map =Maps.newHashMap();
        map.put("username",loginParam.getUsername());
        map.put("password",loginParam.getPassword());
        map.put("grant_type",oauth2GrantType);
        map.put("client_id",oauth2ClientId);
        map.put("client_secret",oauth2ClientSecret);

        try {
            //使用 okhttp 去获取 token
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN,map);
            //Objects.requireNonNull() 用来判空
            String jsonString= Objects.requireNonNull(response.body()).string();

            //使用工具类将json转成对象
            Map<String, Object> resultMap = MapperUtils.json2map(jsonString);
            //将获得的token传给前端
            result.put("token",resultMap.get("access_token"));

        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseResult<Map<String, Object>>(ResponseResult.CodeStatus.OK,"登陆成功",result);
    }

    @GetMapping("/logout")
    public CommonResult<?> logout(HttpServletRequest request){
        String token = request.getParameter("token");
        OAuth2AccessToken accessToken =tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(accessToken);
        return CommonResult.success(null,"退出登录");
    }
}
