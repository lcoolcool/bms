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
 *              2. (func)selectImports  选择组件？(pom.xml中配置的东西)
 *                 -> (func)getAutoConfigurationEntry -> (func)getCandidateConfigurations  获取所有候选配置
 *                      -------getCandidateConfigurations-------
 *                      protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
 *                          List<String> configurations = ImportCandidates.load(AutoConfiguration.class, this.getBeanClassLoader()).getCandidates();
 *                          Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports. If you are using a custom packaging, make sure that file is correct.");
 *                      return configurations;
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
 *
 */
@SpringBootApplication
public class BmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmsApplication.class, args);
    }

}
