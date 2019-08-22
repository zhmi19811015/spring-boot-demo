**Springboot+Spring-Security+JWT+Redis实现restful Api的权限管理以及token管理**

使用说明

1、执行test中方法 注册用户
2、localhost:8917/auth/login  post请求 获取jwt-token  参数Body-json
{
    "username":"admin",
    "password":"123456",
    "rememberMe":1
}

3、localhost:8917/tasks  get请求 headers参数Authorization等于返回的token