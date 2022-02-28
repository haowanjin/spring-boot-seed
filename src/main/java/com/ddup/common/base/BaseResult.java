package com.ddup.common.base;

import com.ddup.common.enums.BaseResultEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应结果
 * 如果使用注解@JsonInclude(JsonInclude.Include.NON_NULL)： 则会保证序列化json的时候,如果是null的对象,key也会消失
 *
 * @author hwj
 * @date 2018/3/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult<T> implements Serializable {

    private Integer code;

    private boolean success;

    private String message;

    private T data;

    /**
     * 判断是否是成功结果
     * JsonIgnore使之不在json序列化结果当中
     *
     * @return 是否为成功结果
     */
    public boolean isSuccess() {
        return BaseResultEnum.SUCCESS.getCode() == this.code;
    }

}