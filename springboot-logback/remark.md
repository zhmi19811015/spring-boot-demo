springboot 默认日志级别INFO  WARN  ERROR

如果需要输出更多日志级别：
1、命令模式：java -jar app.jar --debug=true  这种优先级最高
2、资源文件配置:application.properties 配置 debug=true 即可。该配置只对 嵌入式容器、Spring、Hibernate生效，
我们自己的项目想要输出 DEBUG 需要额外配置（配置规则：logging.level.=）

logging.level.root = WARN
logging.level.org.springframework.web = DEBUG
logging.level.org.hibernate = ERROR

#比如 mybatis sql日志
logging.level.org.mybatis = INFO
logging.level.mapper所在的包 = DEBUG

日志输出格式配置

logging.pattern.console： 定义输出到控制台的格式（不支持JDK Logger）
logging.pattern.file： 定义输出到文件的格式（不支持JDK Logger）


**文件保存**

默认情况下，SpringBoot 仅将日志输出到控制台，不会写入到日志文件中去。如果除了控制台输出之外还想写日志文件，
则需要在application.properties 设置logging.file 或 logging.path 属性

logging.file： 将日志写入到指定的 文件 中，默认为相对路径，可以设置成绝对路径
logging.path： 将名为 spring.log 写入到指定的 文件夹 中，如（/var/log）

日志文件在达到 10MB 时进行切割，产生一个新的日志文件（如：spring.1.log、spring.2.log），新的日志依旧输出到 spring.log 中去，默认情况下会记录 ERROR、WARN、INFO 级别消息。

logging.file.max-size： 限制日志文件大小
logging.file.max-history： 限制日志保留天数
