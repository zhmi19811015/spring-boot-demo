1. 一个完整的Spring Boot Starter可能包含以下组件：
* autoconfigure模块：包含自动配置的代码
* starter模块：提供对autoconfigure模块的依赖，以及一些其它的依赖

在springboot官方文档中，特别提到，我们需要创建两个module ，其中一个是autoconfigure module  
一个是 starter module ，其中 starter module 依赖 autoconfigure module

2.命名

springboot 官方建议springboot官方推出的starter 以spring-boot-starter-xxx的格式来命名，
第三方开发者自定义的starter则以xxxx-spring-boot-starter的规则来命名

3.开发Starter步骤

* 创建Starter项目
* 定义Starter需要的配置（Properties）类
* 编写自动配置类
* 编写spring.factories文件加载自动配置类
* 编写配置提示文件spring-configuration-metadata.json（不是必须的）

4.springboot特有的常见的条件依赖注解有：

 * @ConditionalOnBean，仅在当前上下文中存在某个bean时，才会实例化这个Bean。
 * @ConditionalOnClass，某个class位于类路径上，才会实例化这个Bean。
 * @ConditionalOnExpression，当表达式为true的时候，才会实例化这个Bean。
 * @ConditionalOnMissingBean，仅在当前上下文中不存在某个bean时，才会实例化这个Bean。
 * @ConditionalOnMissingClass，某个class在类路径上不存在的时候，才会实例化这个Bean。
 * @ConditionalOnNotWebApplication，不是web应用时才会实例化这个Bean。
 * @AutoConfigureAfter，在某个bean完成自动配置后实例化这个bean。
 * @AutoConfigureBefore，在某个bean完成自动配置前实例化这个bean。