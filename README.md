# 使用spring boot 2.0.2框架搭建的博客

部署到生产环境时的注意事项-v2.0
1. 指定 application-prod.properties 为主配置文件
2. InterceptorConfig 打开必需的注释
3. 打包: `mvn package -DskipTests`  
注: 静态资源修改(js,css,image)后, 同步到nginx根目录


