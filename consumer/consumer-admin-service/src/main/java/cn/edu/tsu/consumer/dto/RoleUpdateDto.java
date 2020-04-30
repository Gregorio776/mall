package cn.edu.tsu.consumer.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Gregorio
 * @date 2020/4/29 19:13
 */
@Data
public class RoleUpdateDto implements Serializable {
    private Long adminId;
    private List<Long> roleIds;
}
