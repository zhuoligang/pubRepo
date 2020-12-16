简介
---
币和分布式架构方案，集服务注册、服务发现、统一网关、Mybatis逆向工程、业务基本架构、消息机制等功能，根据项目具体情况选用架构。

代码提交注意事项：
---
1、有且仅有一个主分支，提供给用户使用的正式版本，都是在主分支上发布；<br>
2、开发必须在develop分支开发，开发完成需求想正式对外发布，就在master分支上，对develop分支进行“合并“；<br>

提交记录规范：
---
1、代码提交记录需写明具体对应的需求、bug；<br>
2、必须填写codereview:代码审核人员姓名简拼；<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格式如下：<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;type : subject<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;codereview:ybc

><code>type值：</code><br>
feat：新功能<br>
fix：修复bug<br>
doc：文档改变<br>
style：代码格式改变<br>
refactor：某个已有功能重构<br>
perf：性能优化<br>
test：增加测试<br>
revert：撤销上一次的commit<br>
<code>subject值：</code><br>
内容为需求、bug概述，并列出对应的编号<br>