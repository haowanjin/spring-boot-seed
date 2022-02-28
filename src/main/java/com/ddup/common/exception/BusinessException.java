package com.ddup.common.exception;

import com.ddup.common.enums.BaseResultEnum;
import lombok.*;

/**
 * 业务异常
 *
 * @author hwj
 * @date 2018/3/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    /**
     * 异常代码
     */
    private int errorCode = BaseResultEnum.SERVER_ERROR.getCode();
    /**
     * 异常信息
     */
    private String errorMessage;

    public BusinessException(String errorMessage) {
        this.errorCode = BaseResultEnum.SERVER_ERROR.getCode();
        this.errorMessage = errorMessage;
    }

    public BusinessException(String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.errorCode = BaseResultEnum.SERVER_ERROR.getCode();
    }

    public BusinessException(int errorCode, String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.errorCode = errorCode;
    }

}
