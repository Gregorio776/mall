package cn.edu.tsu.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
@Data
@Table(name = "ums_admin_login_log")
public class UmsAdminLoginLog implements Serializable {
    @Column(name = "id")
    private Long id;

    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "ip")
    private String ip;

    @Column(name = "address")
    private String address;

    @Column(name = "user_agent")
    private String userAgent;

    private static final long serialVersionUID = 1L;
}