如果项目因为插件，plugin运行不起来的时候，需要做如下操作：
1.先删除关于oplugin 的一些配置如：根目录下的配置，和app，目录下的配置
2.在根目录下运行 ./gradlew clean uploadArchives 命令即可
