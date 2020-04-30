package cn.edu.tsu.consumer.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginInfo implements Serializable {
    private String name;
    private String avatar;
    private String nickName;
}
