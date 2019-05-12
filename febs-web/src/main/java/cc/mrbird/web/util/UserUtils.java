package cc.mrbird.web.util;

import cc.mrbird.security.domain.FebsSocialUserDetails;
import cc.mrbird.security.domain.FebsUserDetails;
import cc.mrbird.system.domain.MyUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static MyUser getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        MyUser user = new MyUser();
        if(principal instanceof FebsUserDetails){
            FebsUserDetails userDetails = (FebsUserDetails) principal;
            user.setUserId(userDetails.getUserId());
            user.setPassword(userDetails.getPassword());
            user.setUsername(userDetails.getUsername());
        }else{
            FebsSocialUserDetails userDetails = (FebsSocialUserDetails) principal;
            user.setUserId(userDetails.getUsersId());
            user.setPassword(userDetails.getPassword());
            user.setUsername(userDetails.getUsername());
        }
        return user;
    }
}
