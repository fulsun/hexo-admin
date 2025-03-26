package pers.fulsun.hexoadmin.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import pers.fulsun.hexoadmin.exception.BizException;
import pers.fulsun.hexoadmin.exception.SystemException;
import pers.fulsun.hexoadmin.vo.Result;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static pers.fulsun.hexoadmin.exception.ErrorCodeEnum.*;

class GlobalWebExceptionHandlerTest {

    @InjectMocks
    private GlobalWebExceptionHandler exceptionHandler;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void handleValidationExceptions_ShouldReturnFieldErrors() throws Exception {
        // 模拟参数校验异常
        FieldError fieldError = new FieldError("object", "fieldName", "error message");
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(null, "object");
        bindingResult.addError(fieldError);

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        // 执行异常处理
        Map<String, String> result = exceptionHandler.handleValidationExceptions(ex);

        // 验证响应
        assertEquals(1, result.size());
        assertEquals("error message", result.get("fieldName"));
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void exceptionHandler_ShouldReturnBizErrorResult() {
        // 模拟业务异常
        BizException ex = new BizException(PARAM_VALIDATION_ERROR, "参数错误");

        // 执行异常处理
        Result result = exceptionHandler.exceptionHandler(ex);

        // 验证响应结构
        assertFalse(result.isSuccess());
        assertEquals(PARAM_VALIDATION_ERROR.getCode(), result.getCode());
        assertEquals(PARAM_VALIDATION_ERROR.getMessage(), result.getMessage());
    }

    @Test
    void systemExceptionHandler_ShouldReturnSystemErrorResult() {
        // 模拟系统异常
        SystemException ex = new SystemException(SYSTEM_INTERNAL_ERROR, "系统错误");

        // 执行异常处理
        Result result = exceptionHandler.systemExceptionHandler(ex);

        // 验证响应
        assertFalse(result.isSuccess());
        assertEquals(SYSTEM_INTERNAL_ERROR.getCode(), result.getCode());
        assertEquals(SYSTEM_INTERNAL_ERROR.getMessage(), result.getMessage());
    }

    @Test
    void shouldCoverAllErrorCodes() {
        // 验证所有预定义的错误码都被处理
        Arrays.stream(ErrorCodeEnum.values()).forEach(errorCode -> {
            Exception ex = errorCode.isBizError() ? 
                new BizException(errorCode, "test") : 
                new SystemException(errorCode, "test");

            Result result = errorCode.isBizError() ?
                exceptionHandler.exceptionHandler((BizException) ex) :
                exceptionHandler.systemExceptionHandler((SystemException) ex);

            assertEquals(errorCode.getCode(), result.getCode());
            assertEquals(errorCode.getMessage(), result.getMessage());
        });
    }
}