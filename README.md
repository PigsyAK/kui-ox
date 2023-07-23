# Kui-Ox

夔牛（Kui Ox），`Burpsuite` 插件，渗透小辅助

### 参考链接

- https://portswigger.net/burp/documentation/desktop/extensions/creating
- https://github.com/PortSwigger/burp-extensions-montoya-api
- https://github.com/PortSwigger/burp-extensions-montoya-api-examples
- https://github.com/API-Security/APIKit
- ""
- https://houbb.github.io/2021/09/05/automaton

### 工作空间

插件有自己的工作空间，默认目录路径为：`${HOME}/.kui-ox`

- 存储配置文件
- 存储运行必要的二进制文件
- 临时文件
- 数据文件：自定义payload
- 等等

### 功能

- [ ] 正则匹配敏感信息，包括但不限于：邮箱、身份证、电话号码、内网地址、密码、特殊接口等
    - HaE --- 一款使用Java开发的信息高亮标记与提取插件，https://github.com/gh0stkey/HaE
- [ ] 在代理 `history` 中，指纹识别，识别web、cms、技术栈等
- [ ] HackBar，https://github.com/d3vilbug/HackBar
- [ ] Interact.sh 反链平台, https://github.com/wdahlenburg/interactsh-collaborator
- [ ] 脚本化的插件系统，https://docs.oracle.com/en/java/javase/11/scripting/scripting-languages-and-java.html
- [ ] BurpJSLinkFinder --- 从js里挖掘域名和url等隐藏接口信息, https://github.com/InitRoot/BurpJSLinkFinder
- [ ] 模糊测试，https://github.com/ggg4566/BurpBountyPlus
- [ ] burp-requests-v0.2.4.jar ---将burp的request直接转换成python的requests库格式代码（curl, wget, powershell等等）
- [ ] U2C(将 Unicode 形式的字符转换为中文
- [ ] 汉化
- [ ] XSS Payload

### TODO

- [ ] 日志记录 `logging`