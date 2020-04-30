package cn.edu.tsu.consumer.service;

import cn.edu.tsu.common.model.UmsAdmin;
import cn.edu.tsu.provider.api.UmsAdminService;
import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author Gregorio
 * @date 2020/4/28 17:53
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsAdmin umsAdmin = umsAdminService.selectByName(username);

        //为所有用户添加 USER 权限
        List<GrantedAuthority> grantedAuthorityList= Lists.newArrayList();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        grantedAuthorityList.add(grantedAuthority);

        if (umsAdmin!=null){
            //用户存在
            return new User(umsAdmin.getUsername(),umsAdmin.getPassword(),grantedAuthorityList);
        }

        return null;
    }
}
