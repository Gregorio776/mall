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
@Table(name = "ums_resource")
public class UmsResource implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "`name`")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "category_id")
    private Long categoryId;

    private static final long serialVersionUID = 1L;
}