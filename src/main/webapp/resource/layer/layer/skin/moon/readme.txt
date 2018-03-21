使用方法
1.下载下来压缩包之后解压到layer的skin目录即可

2.下载之后使用下面代码加载css文件

layer.config({extend:'skin/moon/style.css'});
3全局使用请配置全局参数
//在引入css的基础上配置skin参数，如下所示

layer.config({

	skin:'layer-ext-moon',

	extend:'skin/moon/style.css'

});
2.2局部使用 指定skin参数为layer-ext-moon 即可