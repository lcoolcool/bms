package org.example.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 标注SpringBoot应用：启动类下所有资源被导入
 *      @SpringBootConfiguration:  Springboot配置
 *          @Configuration:  Spring配置类，说明启动类也是一个配置类
 *              @Component:  说明这也是一个Spring的组件
 *
 *      @EnableAutoConfiguration:  自动装配
 *          @AutoConfigurationPackage:  自动配置包
 *              @Import({AutoConfigurationPackages.Registrar.class}):  自动配置包注册
 *          @Import({AutoConfigurationImportSelector.class}):  自动配置导入选择器
 *              1. 导入一些配置eg: Environment 环境
 *                            ResourceLoader 资源加载器
 *
 *              2. (func)selectImports  选择组件
 *                 -> (func)getAutoConfigurationEntry 获取配置实体 -> (func)getCandidateConfigurations  获取所有候选配置
 *                      -------getCandidateConfigurations-------
 *                      protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
 *                          List<String> configurations = ImportCandidates.load(AutoConfiguration.class, this.getBeanClassLoader()).getCandidates();
 *                          Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports. If you are using a custom packaging, make sure that file is correct.");
 *                          return configurations;
 *                      }
 *                      ----------------------------------------
 *                      List<String> configurations = ImportCandidates.load(AutoConfiguration.class, this.getBeanClassLoader()).getCandidates();
 *                           -> public static ImportCandidates load(Class<?> annotation, ClassLoader classLoader) {
 *                                  Assert.notNull(annotation, "'annotation' must not be null");
 *                                  ClassLoader classLoaderToUse = decideClassloader(classLoader);
 *                                  String location = String.format("META-INF/spring/%s.imports", annotation.getName());
 *                                  Enumeration<URL> urls = findUrlsInClasspath(classLoaderToUse, location);
 *                                  List<String> importCandidates = new ArrayList();
 *
 *                                  while(urls.hasMoreElements()) {  # 读取META-INF/spring/%s.imports下所有的包
 *                                      URL url = (URL)urls.nextElement();
 *                                      importCandidates.addAll(readCandidateConfigurations(url));
 *                                  }
 *
 *                                  return new ImportCandidates(importCandidates);
 *                              }
 *                          -> 读取在文件包路径下的包：spring-boot-autoconfigure: META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports:  自动配置核心文件
 *                          ** 全部配置类为什么没 有全部生效？
 *                              ->每一个配置类都有注解： @ConditionalOnClass: 满足条件才会生效
 *      @ComponentScan: 扫描当前主启动类同级的包
 */
@SpringBootApplication
public class BmsApplication {

    public static void main(String[] args) {
        //SpringApplication类
        /**主要做了以下事情(无参构造初始化方法->)
         * 1. 推断应用的项目是普通项目还是Web项目
         * 2. 查找并加载所有可用初始化器，设置到initializers属性中(this.setInitializers(this.getSpringFactoriesInstances(ApplicationContextInitializer.class));)
         * 3. 找出所有应用程序的监听器，设置到listeners属性中(this.setListeners(this.getSpringFactoriesInstances(ApplicationListener.class));)
         * 4. 推断并设置main方法的定义类，找到运行的主类
         */
        //SpringApplication下的run(应用入口类， 命令行参数)方法：并不是运行了一个main方法，而是开启了一个服务
        /**run方法做了哪些事情
         * step1. headless系统属性设置
         * step2. 初始化监听器(SpringApplicationRunListeners listeners = this.getRunListeners(args);)
         * step3. 启动已准备好的监听器(listeners.starting(bootstrapContext, this.mainApplicationClass);)
         * step4. 装配环境参数
         *        ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
         *        ConfigurableEnvironment environment = this.prepareEnvironment(listeners, bootstrapContext, applicationArguments);
         * step5. 打印banner图案(Banner printedBanner = this.printBanner(environment);)
         * step6. 构建上下文
         * step7. 上下文前置处理 prepareContext (this.prepareContext(bootstrapContext, context, environment, listeners, applicationArguments, printedBanner);) ->配置监听
         *        environment 环境配置
         *        initialize 初始化设置，可拓展
         *        资源获取并加载
         * step8. 上下文刷新
         *        bean工厂加载
         *        通过工厂生产Bean
         *        刷新生命周期
         * step9. 上下文后置结束处理 afterRefresh (this.afterRefresh(context, applicationArguments);) -> 计时器结束，监听结束
         * step10. 发布应用上下文，启动完成
         * step11. 执行Runner运行器(this.callRunners(context, applicationArguments);)
         * step12. 发布应用上下文就绪并返回
         * SpringApplication启动结束
         */
        SpringApplication.run(BmsApplication.class, args);
    }

}
