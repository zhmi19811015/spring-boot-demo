spring框架还提供了很多@Condition给我们用，当然总结用语哪种好理解，看给位读者喽

1. @ConditionalOnBean（仅仅在当前上下文中存在某个对象时，才会实例化一个Bean）
2. @ConditionalOnClass（某个class位于类路径上，才会实例化一个Bean）
3. @ConditionalOnExpression（当表达式为true的时候，才会实例化一个Bean）
4. @ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
5. @ConditionalOnMissingClass（某个class类路径上不存在的时候，才会实例化一个Bean）
6. @ConditionalOnNotWebApplication（不是web应用）

另一种总结

1. @ConditionalOnClass：该注解的参数对应的类必须存在，否则不解析该注解修饰的配置类；
2. @ConditionalOnMissingBean：该注解表示，如果存在它修饰的类的bean，则不需要再创建这个bean；可以给该注解传入参数例如@ConditionOnMissingBean(name = "example")，这个表示如果name为“example”的bean存在，这该注解修饰的代码块不执行。
