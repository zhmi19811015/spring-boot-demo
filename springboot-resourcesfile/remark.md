boot 静态文件处理

## 最笨方式

就是将静态资源通过流直接返回给前端，我们在maven工程的resources的根目录下建立一个html的目录，
然后我们把html文件放在该目录下，并且规定任何访问路径以/static/开头的即访问该目录下的静态资源

##  Spring boot默认静态资源访问方式

Spring boot默认对/**的访问可以直接访问四个目录下的文件：

- classpath:/public/
- classpath:/resources/
- classpath:/static/
- classpath:/META-INFO/resouces/

在pom.xml指定资源目录即可：

<resources>
    <resource>
        <directory>src/main/resources</directory>
    </resource>
</resources>

http://localhost:8080/1.html
http://localhost:8080/2.html
http://localhost:8080/3.html
http://localhost:8080/4.html


##  自定义静态资源目录
自定义一个静态资源目录，我们定义一个images的目录来存放图片，所有/image/**的路径都会访问images目录下的资源

`
@Configuration
public class ImageMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations("classpath:/images/");
    }
}
`
WebMvcConfigurerAdapter是Spring提供的一个配置mvc的适配器，里面有很多配置的方法，addResourceHandlers就是专门处理静态资源的方法

其实除了上面的办法还有一种更简单的办法，就是直接在application.yml中配置即可：

`spring:
   mvc:
     static-path-pattern: /image/**
   resources:
     static-locations: classpath:/images/
     `
     
static-path-pattern：访问模式，默认为/**，多个可以逗号分隔

static-locations：资源目录，多个目录逗号分隔，默认资源目录为classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/

注意，这个配置会覆盖Spring boot默认的静态资源目录，例如如果按示例中配置，则无法再访问static、public、resources等目录下的资源了。



