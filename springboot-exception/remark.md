全局异常

@ControllerAdvice 捕获 Controller 层抛出的异常，如果添加 @ResponseBody 返回信息则为JSON 格式。

@RestControllerAdvice 相当于 @ControllerAdvice 与 @ResponseBody 的结合体。

@ExceptionHandler 统一处理一种类的异常，减少代码重复率，降低复杂度。