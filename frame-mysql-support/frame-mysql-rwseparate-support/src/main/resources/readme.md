mysql 读写分离  
1、和separate一样都用的是京东的sharding-jdbc
改module用的是apache里面孵化版本，架构会根据版本的更新进行迭代，
spearate用的是io.shardingsphere版本  
2、该module和分库分表subdb版本是一致的  
3、还差把分库分表和读写分离做到一起的解决方案，也就是把rwseparate和subdb整合到一起需要
调查官方的整合方式

