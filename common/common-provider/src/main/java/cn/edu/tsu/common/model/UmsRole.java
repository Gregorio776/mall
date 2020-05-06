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
@Table(name = "ums_role")
public class UmsRole implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Long id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "admin_count")
    private Integer adminCount;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "`status`")
    private Integer status;

    @Column(name = "sort")
    private Integer sort;

    private static final long serialVersionUID = 1L;
}