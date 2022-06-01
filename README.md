# gms-java-sdk
gms java sdk

JAVA 发版 gradle需要用comile，这样发出去的implementation 'com.github.toseebuilder:gms-java-sdk:2.1.3-SNAPSHOT'无论是安卓项目还是java项目添加这个依赖的时候，开源库的需要的依赖也会被下载下来。
默认的impeletion在java项目中无论是maven和gradle可以正常使用。但是android项目则不能循环到依赖。
